package com.guofusong.basic.dataocean.hadoop.conn;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guofusong.basic.dataocean.exception.ConfigurationException;

public class FileSystemFactory {
	
	private static final Logger log = LoggerFactory.getLogger(FileSystemFactory.class);
	
	private Configuration configuration;
	
	public FileSystemFactory(Configuration configuration) throws ConfigurationException{
		if(configuration == null)
			throw new ConfigurationException("empty configuration !");
		this.configuration = configuration;
	}
	
	public FileSystemFactory(HadoopConfiguration hadoopConfiguration) throws ConfigurationException{
		if(hadoopConfiguration ==null)
			throw new ConfigurationException("empty configuration !");
		Iterator<Entry<String, String>> iterator = hadoopConfiguration.iterator();
		Configuration configuration = new Configuration();
		while(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			configuration.set(entry.getKey(), entry.getValue());
		}
		this.configuration = configuration;
	}
	
	public FileSystem get() throws IOException{
		return FileSystem.get(configuration);
	}
	
	public static void close(FileSystem fileSystem){
		if(fileSystem ==null)
			return;
		try {
			fileSystem.close();
		} catch (IOException e) {
			log.error("close hdfs filesystem fail ! [{}]", e.getMessage());
		}
	}
	
}
