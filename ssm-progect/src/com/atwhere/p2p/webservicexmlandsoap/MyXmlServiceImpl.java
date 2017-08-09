package com.atwhere.p2p.webservicexmlandsoap;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyXmlServiceImpl implements MyXmlService{
	
	@Autowired
	private XmlDao xmlDao;

	@Override
	public ArrayList<Student> getStudents(String name, int age) {
		return xmlDao.getStudents(name, age);
	}

	@Override
	public Student getStudent(int id) {
		return new Student(4,"赵清发",88);
	}


}
