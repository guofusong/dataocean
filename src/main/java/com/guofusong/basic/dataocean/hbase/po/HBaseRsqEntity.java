/******************************************************************************
 * Copyright (C) 2017  ShenZhen INNOPRO Co.,Ltd
 * All Rights Reserved.
 * 本软件为精华隆智慧感知科技（深圳）股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.guofusong.basic.dataocean.hbase.po;

/**
 * hbase数据添加模板类
 * @author Administrator
 *
 */
public class HBaseRsqEntity {

	private String row;
	
	private String family;
	
	private String qualifier;
	
	private String value;
	
	private Long timestamp;

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "HBaseRsqEntity [row=" + row + ", family=" + family
				+ ", qualifier=" + qualifier + ", value=" + value
				+ ", timestamp=" + timestamp + "]";
	}
	
}
