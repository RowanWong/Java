package cn.im.thread;

/**
 * 多线程
 * @author Kevin
 */
public class ThreadDemo {

	public static void main(String[] args) {
		
		new MutliThread("thread1").start();
	}
}

/**
 * 实现Runnable接口，便于扩展
 * @author Kevin
 *
 */
class MyThread implements Runnable{
	private int ticket = 100;
	private String name;
	MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		while (ticket > 0) {
			System.out.println(ticket--+"  is selled by :" + name);
		}
	}
} 

/**
 * 继承Thread类
 * @author Kevin
 *
 */
class MutliThread extends Thread{
    private int ticket=100;//每个线程都拥有100张票
    MutliThread(String name){
        super(name);//调用父类带参数的构造方法
    }
    public void run(){
        while(ticket>0){
            System.out.println(ticket--+" is saled by "+Thread.currentThread().getName());
        }
    }
}
