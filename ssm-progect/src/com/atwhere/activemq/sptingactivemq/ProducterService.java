package com.atwhere.activemq.sptingactivemq;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ProducterService {

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	public void sendMessage(Destination destination,String msg){
		System.out.println(Thread.currentThread().getName()+" 向队列"+destination.toString()+"发送消息------>"+msg);
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
