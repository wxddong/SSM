package com.atwhere.activemq.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Comsumer {
	//ActiveMQ的默认用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	//ActiveMQ的默认密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	//ActiveMQ的链接地址
	private static final String BROKEN_URL = "tcp://192.168.0.104:61616";
	AtomicInteger count = new AtomicInteger();
	//链接工厂
	ConnectionFactory connectionFactory;
	//链接对象
	Connection connection;
	//事物管理
	Session session;
	ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();

	public void init() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void getMessage(String disname){
		try{
			Queue queue = session.createQueue(disname);
			MessageConsumer  messageComsumer = null;
			if (threadLocal.get() != null) {
				messageComsumer = threadLocal.get();
			} else {
				messageComsumer = session.createConsumer(queue);
				threadLocal.set(messageComsumer);
			}

			while(true){
				Thread.sleep(1000);
				TextMessage msg = (TextMessage) messageComsumer.receive();
				if(msg!=null){
					msg.acknowledge();
					System.out.println(Thread.currentThread().getName() + " Consumer:我是消费者，我正在消费Msg" + msg.getText() + "--->" + count.getAndIncrement());
				} else {
					break;
				}
			}

		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
