package com.atwhere.activemq.multithreading;

public class TestComsumer {
	public static void main(String[] args) {
		Comsumer comsumer = new Comsumer();
		comsumer.init();
		TestComsumer testComsumer = new TestComsumer();
		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
	}

	private class ComsumerMq implements Runnable {
		Comsumer comsumer;

		public ComsumerMq(Comsumer comsumer) {
			this.comsumer = comsumer;
		}

		@Override
		public void run() {
			while (true) {
				try {
					comsumer.getMessage("Jaycekon-MQ");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
