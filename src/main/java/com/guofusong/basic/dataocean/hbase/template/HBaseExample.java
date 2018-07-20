package com.guofusong.basic.dataocean.hbase.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.guofusong.basic.dataocean.hbase.conn.HBaseConnectionFactory;


/**
 * @ClassName: HBaseTemplate
 * @version 2.0
 * @Desc: 数据操作模板
 * @author guofusong
 * @date 2018年5月9日下午1:51:47
 * @history v2.0
 *
 */
public class HBaseExample {

	protected Connection connection;
	
	protected HBaseAdmin admin;
	
	protected HBaseConnectionFactory hbaseConnectionFactory;
	
	public HBaseExample(HBaseConnectionFactory hbaseConnectionFactory) throws IOException{
		this.hbaseConnectionFactory = hbaseConnectionFactory;
		if(connection == null)
			connection = hbaseConnectionFactory.create();
		if(admin == null)
			admin = (HBaseAdmin) connection.getAdmin();
	}
	
	/**
	 * 
	 * @param table
	 * @param columns
	 * @return
	 * @throws IOException
	 */
	public boolean create(String table,String... columns) throws IOException{
		TableName tableName = TableName.valueOf(table);
		HTableDescriptor descriptor = new HTableDescriptor(tableName);
		for (String string : columns) 
			descriptor.addFamily(new HColumnDescriptor(string));
		HTableDescriptor descriptor2 = new HTableDescriptor(tableName, descriptor);
		if(!admin.tableExists(tableName))
			admin.createTable(descriptor2);
		return admin.tableExists(tableName);
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 * @throws IOException
	 */
	public boolean drop(String table) throws IOException{
		TableName tableName = TableName.valueOf(table);
		if(admin.tableExists(tableName)){
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		}
		return !admin.tableExists(tableName);
	}
	
	/**
	 * 
	 * @param table
	 * @throws IOException
	 */
	public void disable(String table) throws IOException{
		TableName tableName = TableName.valueOf(table);
		admin.disableTable(tableName);
	}
	
	/**
	 * 
	 * @param table
	 * @throws IOException
	 */
	public void enable(String table) throws IOException{
		TableName tableName = TableName.valueOf(table);
		admin.enableTable(tableName);
	}
	
	/**
	 * 
	 * @param table
	 * @param row
	 * @param colnmu
	 * @param qualifier
	 * @param value
	 * @throws IOException
	 */
	public void put(String table, String row, String colnmu, String qualifier,Long timestamp, String value) throws IOException {
		Table t = connection.getTable(TableName.valueOf(table));
		Put put = new Put(Bytes.toBytes(row));
		if(timestamp == null || timestamp < 0)
			put.addColumn(Bytes.toBytes(colnmu), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		else
			put.addColumn(Bytes.toBytes(colnmu), Bytes.toBytes(qualifier),timestamp,Bytes.toBytes(value));
		t.put(put);
	}
	
	/**
	 * 
	 * @param table
	 * @param row
	 * @return
	 * @throws IOException
	 */
	public Result get(String table, String row) throws IOException {
		Table t = connection.getTable(TableName.valueOf(table));
		Get get = new Get(Bytes.toBytes(row));
		Result result = t.get(get);
		return result;
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 * @throws IOException
	 */
	public List<Result> scan(String table) throws IOException {
		Table t = connection.getTable(TableName.valueOf(table));
		Scan scan = new Scan();
		ResultScanner scanner = t.getScanner(scan);
		List<Result> results = new ArrayList<>();
		for (Result item : scanner) 
			results.add(item);
		return results;
	}
	
	/**
	 * 
	 * @param table
	 * @param row
	 * @throws IOException
	 */
	public void delete(String table,String row) throws IOException{
		Table t = connection.getTable(TableName.valueOf(table));
		Delete delete = new Delete(Bytes.toBytes(row));
		t.delete(delete);
	}
	
	/**
	 * 
	 * @param table
	 * @param row
	 * @param family
	 * @param qualifier
	 * @throws IOException
	 */
	public void delete(String table,String row,String family,String qualifier) throws IOException{
		Table t = connection.getTable(TableName.valueOf(table));
		Delete delete = new Delete(Bytes.toBytes(row));
		delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
		t.delete(delete);
	}

}
