package com.atwhere.p2p.springtask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTaskBaseAnnotation {

	/**
	 * 定时执行 每隔十秒触发一次
	 */
	@Scheduled(cron = "*/10 * * * * *")
	public void show() {
		System.out.println("Annotation：is show run------10seconds / time");
	}

	/**
	 * 心跳更新。启动时执行一次，之后每隔2秒执行一次
	 */
	@Scheduled(fixedRate = 1000 * 2)
	public void print() {
		System.out.println("Annotation：print run---- 1000*2");
	}

}
