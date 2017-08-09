package com.atguigu.cxf.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public class EmployeeServerImpl implements EmployeeServer
{
	@Override
	@GET
	@Path("/employee/{id}")
	@Produces("application/json")
	public Employee getEmployeeById(@PathParam("id") Integer id)
	{
		return new Employee(id, "li6",26);
	}

}
