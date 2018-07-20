package com.guofusong.basic.dataocean.hbase.conn;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;

public class HBaseConfiguration extends Configuration{
	
	public void set(String name,String value){
		super.set(name, value);
	}
	
	public void addConf(FileInputStream inputStream) throws IOException{
		Properties prop = new Properties();
		prop.load(inputStream);
		addConf(prop);
	}
	
	public void addConf(Properties properties){
		Set<Entry<Object, Object>> set = properties.entrySet();
		for (Entry<Object, Object> entry : set) {
			set(entry.getKey()+"", entry.getValue()+"");
		}
	}

}
