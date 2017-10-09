package com.atwhere.activemq.multithreading;

public class TestProducter {
	public static void main(String[] args) {
		Producter producter = new Producter();
		producter.init();

		TestProducter testProducter = new TestProducter();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Thread 1
		new Thread(testProducter.new ProducterMq(producter)).start();
		//Thread 2
		new Thread(testProducter.new ProducterMq(producter)).start();
		//Thread 3
		new Thread(testProducter.new ProducterMq(producter)).start();
		//Thread 4
		new Thread(testProducter.new ProducterMq(producter)).start();
		//Thread 5
		new Thread(testProducter.new ProducterMq(producter)).start();

	}

	public class ProducterMq implements Runnable {

		Producter producter;

		public ProducterMq(Producter producter) {
			this.producter = producter;
		}

		@Override
		public void run() {
			while (true) {
				try {
					producter.sendMessage("Jaycekon-MQ");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
