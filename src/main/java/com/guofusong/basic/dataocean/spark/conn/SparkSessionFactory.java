package com.guofusong.basic.dataocean.spark.conn;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.Builder;

public class SparkSessionFactory {
	
	private Builder builder;
	
	public SparkSessionFactory(){
		builder = SparkSession.builder();
	}
	
	public SparkSessionFactory(Builder builder){
		this.builder = builder;
	}
	
	public void appName(String appName){
		builder.appName(appName);
	}
	
	public void master(String master){
		builder.master(master);
	}
	
	public void config(String key,String value){
		builder.config(key, value);
	}
	
	public void config(String key,boolean value){
		builder.config(key, value);
	}
	
	public void config(String key,double value){
		builder.config(key, value);
	}
	
	public void config(String key,long value){
		builder.config(key, value);
	}
	
	public SparkSession get(){
		SparkSession spark = builder.getOrCreate();
		return spark;
	}
	
}
