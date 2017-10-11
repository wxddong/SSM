package com.atwhere.activemq.sptingactivemq;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageContorller {

	@Resource(name = "demoQueueDestination")
	private Destination destination;

	//队列消息生产者
	@Resource(name = "producerService")
	private ProducterService producterService;

	//队列消息消费者
	@Resource(name = "consumerService")
	private ComsumerService comsumerService;

	@RequestMapping(value = "/SendMessage", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void send(String msg) {
		System.out.println(Thread.currentThread().getName() + "------------send to jms Start");
		producterService.sendMessage(destination,msg);
		System.out.println(Thread.currentThread().getName() + "------------send to jms End");
	}

	@RequestMapping(value = "/ReceiveMessage", method = RequestMethod.GET)
	@ResponseBody
	public Object receive() {
		System.out.println(Thread.currentThread().getName() + "------------receive from jms Start");
		TextMessage tm = comsumerService.receive(destination);
		System.out.println(Thread.currentThread().getName() + "------------receive from jms End");
		return tm;
	}


}
