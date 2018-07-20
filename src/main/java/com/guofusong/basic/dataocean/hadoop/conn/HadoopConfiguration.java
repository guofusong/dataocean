package com.guofusong.basic.dataocean.hadoop.conn;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;

public class HadoopConfiguration extends Configuration{

	public void set(String key,String value){
		super.set(key, value);
	}
	
	public void addConf(Properties properties){
		if(properties == null)
			return;
		Set<Entry<Object, Object>> entries = properties.entrySet();
		for (Entry<Object, Object> entry : entries) {
			set(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
		}
	}
	
	public void addConf(FileInputStream fileInputStream) throws IOException{
		if(fileInputStream == null)
			return;
		Properties prop = new Properties();
		prop.load(fileInputStream);
		addConf(prop);
	}
	
	
}
