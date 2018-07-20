/******************************************************************************
 * Copyright (C) 2017  ShenZhen INNOPRO Co.,Ltd
 * All Rights Reserved.
 * 本软件为精华隆智慧感知科技（深圳）股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.guofusong.basic.dataocean.hbase.conn;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * @ClassName: HBASEConnection
 * @version 2.0
 * @Desc:
 * @author guofusong
 * @date 2018年5月9日下午1:52:03
 * @history v2.0
 *
 */
public class HBaseConnectionFactory {

	private boolean isCustom;

	private static Connection connection;

	private Configuration configuration;

	private HBaseConfiguration baseConfiguration;

	public HBaseConnectionFactory() {
	}

	public HBaseConnectionFactory(String name) {
		HBaseConfiguration.addDefaultResource(name);
	}

	public HBaseConnectionFactory(Configuration configuration) {
		org.apache.hadoop.hbase.HBaseConfiguration.addHbaseResources(configuration);
	}

	public HBaseConnectionFactory(HBaseConfiguration configuration) {
		this.baseConfiguration = configuration;
		isCustom = true;
	}

	public Connection create() throws IOException {
		if (connection == null) {
			synchronized (Connection.class) {
				if (connection == null) {
					try {
						if (isCustom) {
							connection = ConnectionFactory.createConnection(baseConfiguration);
						} else {
							configuration = org.apache.hadoop.hbase.HBaseConfiguration.create();
							connection = ConnectionFactory.createConnection(configuration);
						}
					} catch (IOException e) {
						System.err.println("create connection fail !");
						throw e;
					}
				}
			}
		}
		return connection;
	}
}
