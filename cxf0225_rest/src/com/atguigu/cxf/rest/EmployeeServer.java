package com.atguigu.cxf.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path(value="/crm")
public interface EmployeeServer
{
	@GET
	@Path(value="/employee/{id}")
	@Produces("application/json")
	public Employee getEmployeeById(@PathParam("id") Integer id);
}
