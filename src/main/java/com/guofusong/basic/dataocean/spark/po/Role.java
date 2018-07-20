package com.guofusong.basic.dataocean.spark.po;

import java.io.Serializable;


/**
 * 测试用电影角色类
 * @author Administrator
 *
 */
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159527933302391135L;
	
	private String name;
	
	private String sex;
	
	private Integer age;
	
	private Integer year;
	
	private String from;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", sex=" + sex + ", age=" + age
				+ ", year=" + year + ", from=" + from + "]";
	}
	
}
