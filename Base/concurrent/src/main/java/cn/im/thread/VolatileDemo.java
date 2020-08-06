package cn.im.thread;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * JMM：原子性、可见性、有序性
 * volatile关键字只保证可见性，不保证操作的原子性
 */
public class VolatileDemo {
	
	public static int count = 0;
	public static AtomicInteger autoInt = new AtomicInteger();
	volatile static int countVolatile = 0;
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("main Thread: "+Thread.currentThread().getId()+"-"+Thread.currentThread().getName());
		
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				public void run() {
					long before = System.currentTimeMillis();
					for (int j = 0; j < 5000; j++) {
						count++; 
						autoInt.getAndIncrement();
						countVolatile++;
					}
					long current = System.currentTimeMillis();
					System.out.println(Thread.currentThread().getId()+" 耗时 "+current+"-"+before+"="+ (current-before));
				}
			}).start();
		}
		
		Thread.sleep(3000);
		
		//解决多线程同步问题，可通过原子类实现
		System.out.println("static count="+count); 
		System.out.println("AtomicInteger autoInt="+autoInt);
		System.out.println("volatile countVolatile="+countVolatile);
	}
	
	
	
	static public class MyThread implements Runnable {

		public void run() {
				System.out.println("sub Thread: "+Thread.currentThread().getId()+"-"+Thread.currentThread().getName());
		}
		
	}
}
