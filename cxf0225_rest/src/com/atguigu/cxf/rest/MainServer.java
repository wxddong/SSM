package com.atguigu.cxf.rest;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class MainServer
{
	public static void main(String[] args)
	{
		JAXRSServerFactoryBean jAXRSServerFactoryBean = new JAXRSServerFactoryBean();

		jAXRSServerFactoryBean.setAddress("http://locasfdflhost:8888/cxf_rest");
		jAXRSServerFactoryBean.setResourceClasses(EmployeeServerImpl.class);

		jAXRSServerFactoryBean.create().start();
	}
}
