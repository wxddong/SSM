package com.atwhere.p2p.webservicerestful;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.annotations.Param;

import com.atwhere.p2p.webservicexmlandsoap.Student;

@Path(value="/student")
public interface MyRestfulService {
	
	@Path(value="/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Student getStudent(@PathParam("id") String id);
}
