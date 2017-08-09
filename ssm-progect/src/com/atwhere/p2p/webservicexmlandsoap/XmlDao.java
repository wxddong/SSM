package com.atwhere.p2p.webservicexmlandsoap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class XmlDao {
   public ArrayList<Student> getStudents(String name,int age){
	   ArrayList<Student> list = new ArrayList<Student>();
	   list.add(new Student(1,"小明",11));
	   list.add(new Student(2,"小红",22));
	   return list;
   }
}
