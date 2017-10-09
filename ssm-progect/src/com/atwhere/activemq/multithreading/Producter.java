package com.atwhere.activemq.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producter {

	//ActiveMQ的默认用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	//ActiveMQ的默认密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	//ActiveMQ的链接地址
	private static final String BROKEN_URL = "tcp://192.168.0.104:61616";

	AtomicInteger count = new AtomicInteger(0);

	//链接工厂
	ConnectionFactory connectionFactory;
	//链接对象
	Connection connection;
	//事物管理
	Session session;
	ThreadLocal<MessageProducer> threadLocal = new ThreadLocal();

	public void init(){
		try{
			//创建一个连接工厂
			connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
			//从工厂中创建一个连接
			connection = connectionFactory.createConnection();
			//开启连接
			connection.start();
			//创建一个事物（这里可以通过参数设置事物的级别）
			session = connection.createSession(true,session.SESSION_TRANSACTED);

		}catch(JMSException  e){
			e.printStackTrace();
		}
	}

	public void sendMessage(String disname){
		try {
			//创建一个消息队列
			Queue queue = session.createQueue(disname);
			//消息生产者
			MessageProducer messageProducter = null;
			if(threadLocal.get()!=null){
				messageProducter = threadLocal.get();
			}else {
				messageProducter = session.createProducer(queue);
				threadLocal.set(messageProducter);
			}

			while(true){
				Thread.sleep(1000);
				int num = count.getAndIncrement();
				//创建一条消息
				TextMessage msg = session.createTextMessage(Thread.currentThread().getName()+" producter:我是大帅哥，我现在正在生产东西！，count:"+num);
				System.out.println(Thread.currentThread().getName() + " producter:我是大帅哥，我现在正在生产东西！，count:" + num);
				//发送消息
				messageProducter.send(msg);
				//提交事务
				session.commit();
			}

		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}





	public static void main(String[] args){
		AtomicInteger count = new AtomicInteger(5);
		for (int i=0;i<=6;i++){
			int num = count.getAndIncrement();
			System.out.println(num);
		}
	}
}
