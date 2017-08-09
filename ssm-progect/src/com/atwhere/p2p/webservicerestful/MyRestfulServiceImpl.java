package com.atwhere.p2p.webservicerestful;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import com.atwhere.p2p.webservicexmlandsoap.Student;

public class MyRestfulServiceImpl implements MyRestfulService{

	
//	@Override
//	@Path(value="/get/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@GET
	public Student getStudent(String id) {
		System.out.println(id);
		return new Student(6,"小明",66);
	}
	
	public static void main(String[] args) {
        JAXRSServerFactoryBean jAXRSServerFactoryBean = new JAXRSServerFactoryBean();
        jAXRSServerFactoryBean.setAddress("http://localhost:8888/cxf0225_rest");
        jAXRSServerFactoryBean.setResourceClasses(MyRestfulServiceImpl.class);
        jAXRSServerFactoryBean.create().start();  

	}
    
}
