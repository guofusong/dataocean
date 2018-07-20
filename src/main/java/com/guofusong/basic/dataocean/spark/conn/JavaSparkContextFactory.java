package com.guofusong.basic.dataocean.spark.conn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import scala.collection.Seq;

public class JavaSparkContextFactory {
	
	private JavaSparkContext context;
	
	private SparkConf conf;
	
	public JavaSparkContextFactory(){
		conf = new SparkConf();
	}
	
	public JavaSparkContextFactory(SparkConf conf){
		this.conf = conf;
	}
	
	public void appName(String appName){
		conf.setAppName(appName);
	}
	
	public void master(String master){
		conf.setMaster(master);
	}
	
	public void setJars(Seq<String> jars){
		conf.setJars(jars);
	}
	
	public void remove(String key){
		conf.remove(key);
	}

	public void set(String key,String value){
		conf.set(key, value);
	}
	
	public void set(String key,String value,boolean silent){
		conf.set(key, value, silent);
	}
	
	public JavaSparkContext get(){
		if(context == null)
			context = new JavaSparkContext(conf);
		return context;
	}
	
}
