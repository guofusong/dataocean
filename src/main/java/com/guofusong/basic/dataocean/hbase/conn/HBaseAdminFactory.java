/******************************************************************************
 * Copyright (C) 2017  ShenZhen INNOPRO Co.,Ltd
 * All Rights Reserved.
 * 本软件为精华隆智慧感知科技（深圳）股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.guofusong.basic.dataocean.hbase.conn;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * @ClassName: HBaseAdminFactory
 * @version 2.0 
 * @Desc: 
 * @author guofusong
 * @date 2018年5月9日下午2:04:29
 * @history v2.0
 *
 */
public class HBaseAdminFactory {

	private HBaseConnectionFactory connectionFactory;
	
	private static HBaseAdmin admin;
	
	public HBaseAdminFactory(){}
	
	public HBaseAdminFactory(HBaseConnectionFactory connectionFactory){
		this.connectionFactory = connectionFactory;
	}
	
	public HBaseAdmin create() throws IOException{
		if(admin == null){
			synchronized (HBaseAdmin.class) {
				if(admin == null){
					try{
						Connection connection = connectionFactory.create();
						admin = (HBaseAdmin) connection.getAdmin();
					}catch(Exception e){
						System.err.println("create hbaseadmin fail !");
						throw e;
					}
				}
			}
		}
		return admin;
	} 
	
}
