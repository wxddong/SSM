package com.atwhere.p2p.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Student {
	private int id;
	private String name ;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date birthday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday="
				+ birthday + "]";
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	
	
	
}
