package com.atguigu.dao;

import org.apache.log4j.Logger;

public class UserDaoImpl
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	
	public void getUserInfo()
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("getUserInfo() - start");
		}
		//---------------------------
		
		if (logger.isDebugEnabled())
		{
			logger.debug("getUserInfo() - end");
		}
	}
	
	public static void main(String[] args) 
	{
		logger.debug("**********debug");
		logger.info("**********info");
		logger.warn("**********warn");
		logger.error("**********error");
	
		int age = 24;
		logger.info("main(String[]) - int age=" + age);
		System.out.println("**************age :"+age);
		
		
		if (logger.isInfoEnabled())
		{
			logger.info("main(String[]) - int age=" + age);
		}
		
		
		
		
		
		
		
		
//		try
//		{
//			logger.debug("1111111111");
//			int age = 10/2;
//			logger.debug("***********: "+age);
//			logger.debug("22222222222");
//		} catch (Exception e){
//			e.printStackTrace();
//			logger.error(e,e.getCause());
//		}
	}
}
