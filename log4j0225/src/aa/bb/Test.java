package aa.bb;

import org.apache.log4j.Logger;

public class Test {

	private static final Logger logger = Logger.getLogger(Test.class);

		public static void main (String[]args)
		{
			logger.debug("**********debug");
			logger.info("**********info");
			logger.warn("**********warn");
			logger.error("**********error");

			int age = 24;
			logger.info("main(String[]) - int age=" + age);

			if (logger.isInfoEnabled()) {
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
