package com.guofusong.basic.dataocean.exception;

/**
 * 配置异常
 * @author Administrator
 *
 */
public class ConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4081621447952379390L;
	
	public ConfigurationException(){
		super();
	}
	
	public ConfigurationException(String message){
		super(message);
	}
	
}
