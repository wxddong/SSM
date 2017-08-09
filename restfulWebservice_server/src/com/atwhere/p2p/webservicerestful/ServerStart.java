package com.atwhere.p2p.webservicerestful;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class ServerStart {
	public static void main(String[] args) {
        JAXRSServerFactoryBean jAXRSServerFactoryBean = new JAXRSServerFactoryBean();
        jAXRSServerFactoryBean.setAddress("http://localhost:8888/cxf_rest");
        jAXRSServerFactoryBean.setResourceClasses(MyRestfulServiceImpl.class);
        jAXRSServerFactoryBean.create().start();  

	}
}
