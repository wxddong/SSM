package com.atwhere.p2p.webservicerestful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path(value="/student")
public interface MyRestfulService {
	@GET
	@Path(value="/get/{id}") 	
//	@Produces("application/xml")
//	@Produces(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML)
	public Student getStudent(@PathParam("id") String id);
}
