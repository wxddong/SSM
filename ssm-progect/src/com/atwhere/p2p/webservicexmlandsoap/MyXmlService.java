package com.atwhere.p2p.webservicexmlandsoap;

import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface MyXmlService {
    public @WebResult(name="getStudentResultValue") ArrayList<Student> getStudents(@WebParam(name="studentName")String name,@WebParam(name="studentAge")int age);
    
    public Student getStudent(int id);
}
