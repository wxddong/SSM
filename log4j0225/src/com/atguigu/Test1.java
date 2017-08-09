package com.atguigu;

import org.apache.log4j.Logger;

public class Test1 {
	private static final Logger logger = Logger.getLogger(Test1.class);
	public static void main(String[] args){
		logger.debug("**********debug");
		logger.info("**********info");
		logger.warn("**********warn");
		logger.error("**********error");
	}
}
