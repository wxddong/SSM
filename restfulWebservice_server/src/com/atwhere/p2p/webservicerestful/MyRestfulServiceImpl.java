package com.atwhere.p2p.webservicerestful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
public class MyRestfulServiceImpl implements MyRestfulService{

	
//	@Override
//	@GET
//	@Path(value="/get/{id}")
//	@Produces("application/xml")
	public Student getStudent(String id) {
		System.out.println(id);
		return new Student(6,"小明",66);
	}
	
    
}
