package com.guofusong.basic.dataocean.hbase.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.guofusong.basic.dataocean.hbase.conn.HBaseConnectionFactory;
import com.guofusong.basic.dataocean.hbase.po.HBaseRsqEntity;


/**
 * @ClassName: HBaseTemplate
 * @version 2.0
 * @Desc: 数据操作模板
 * @author guofusong
 * @date 2018年5月9日下午1:51:47
 * @history v2.0
 *
 */
public class HBaseTemplate extends HBaseExample {
	
	public HBaseTemplate(HBaseConnectionFactory hbaseConnectionFactory) throws IOException{
		super(hbaseConnectionFactory);
	}

	/**
	 * 添加一行数据
	 * @param table
	 * @param row
	 * @param rsqEntities
	 * @throws IOException
	 */
	public void put(String table,String row,HBaseRsqEntity[] rsqEntities) throws IOException{
		Table t = connection.getTable(TableName.valueOf(table));
		Put put = new Put(Bytes.toBytes(row));
		for (HBaseRsqEntity hBaseRsqEntity : rsqEntities) {
			if(hBaseRsqEntity.getTimestamp() == null || hBaseRsqEntity.getTimestamp() < 0)
				put.addColumn(Bytes.toBytes(hBaseRsqEntity.getFamily()), Bytes.toBytes(hBaseRsqEntity.getQualifier()), Bytes.toBytes(hBaseRsqEntity.getValue()));
			else
				put.addColumn(Bytes.toBytes(hBaseRsqEntity.getFamily()), Bytes.toBytes(hBaseRsqEntity.getQualifier()),hBaseRsqEntity.getTimestamp(),Bytes.toBytes(hBaseRsqEntity.getValue()));
		}
		t.put(put);
	}
	
	/**
	 * 添加多行数据
	 * @param table
	 * @param map
	 * @throws IOException
	 */
	public void put(String table,Map<String,HBaseRsqEntity[]> map) throws IOException{
		Table t = connection.getTable(TableName.valueOf(table));
		List<Put> puts = assembPut(map);
		t.put(puts);
	}
	
	/**
	 * 添加多行数据
	 * @param table
	 * @param rsqEntities
	 * @throws IOException
	 */
	public void put(String table,HBaseRsqEntity[] rsqEntities) throws IOException{
		Table t = connection.getTable(TableName.valueOf(table));
		List<Put> puts = assembPut(rsqEntities);
		t.put(puts);
	}
	
	/**
	 * 扫描指定表、family、qualifier 数据
	 * @param table
	 * @param family
	 * @param qualifier
	 * @return
	 * @throws IOException
	 */
	public List<Result> scan(String table,String family,String qualifier) throws IOException{
		Table t = connection.getTable(TableName.valueOf(table)); 
		Scan scan = new Scan();
		scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
		ResultScanner scanner = t.getScanner(scan);
		List<Result> results = new ArrayList<>();
		for (Result item : scanner) 
			results.add(item);
		return results;
	}
	
	private List<Put> assembPut(Map<String,HBaseRsqEntity[]> map){
		Set<Entry<String, HBaseRsqEntity[]>> entries = map.entrySet();
		List<Put> list = new ArrayList<>(entries.size());
		for (Entry<String, HBaseRsqEntity[]> entry : entries) {
			Put put = new Put(Bytes.toBytes(entry.getKey()));
			for (HBaseRsqEntity rsqEntity : entry.getValue()) {
				if(rsqEntity.getTimestamp() == null || rsqEntity.getTimestamp() < 0)
					put.addColumn(Bytes.toBytes(rsqEntity.getFamily()), Bytes.toBytes(rsqEntity.getQualifier()), Bytes.toBytes(rsqEntity.getValue()));
				else
					put.addColumn(Bytes.toBytes(rsqEntity.getFamily()), Bytes.toBytes(rsqEntity.getQualifier()),rsqEntity.getTimestamp(),Bytes.toBytes(rsqEntity.getValue()));
			}
			list.add(put);
		}
		return list;
	}
	
	private List<Put> assembPut(HBaseRsqEntity[] rsqEntities){
		Map<String,Put> map = new HashMap<>();
		for (HBaseRsqEntity hBaseRsqEntity : rsqEntities) {
			Put tempPut = null;
			if(map.containsKey(hBaseRsqEntity.getRow()))
				tempPut = map.get(hBaseRsqEntity.getRow());
			else
				tempPut = new Put(Bytes.toBytes(hBaseRsqEntity.getRow()));
			if(hBaseRsqEntity.getTimestamp() == null || hBaseRsqEntity.getTimestamp() < 0)
				tempPut.addColumn(Bytes.toBytes(hBaseRsqEntity.getFamily()), Bytes.toBytes(hBaseRsqEntity.getQualifier()), Bytes.toBytes(hBaseRsqEntity.getValue()));
			else
				tempPut.addColumn(Bytes.toBytes(hBaseRsqEntity.getFamily()), Bytes.toBytes(hBaseRsqEntity.getQualifier()),hBaseRsqEntity.getTimestamp(),Bytes.toBytes(hBaseRsqEntity.getValue()));
			map.put(hBaseRsqEntity.getRow(), tempPut);
		}
		return new ArrayList<Put>(map.values());
	}
	
}
