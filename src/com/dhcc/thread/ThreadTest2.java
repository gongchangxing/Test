package com.dhcc.thread;

/**
 * 
 * 线程的实现的2中方式。
 * 第一种方式为继承thread类，
 * 第二种方式为实现runable接口。
 * 
 * @author zx
 * @createDate 2014-4-30
 * @since TODO: 来源版本
 *
 */
public class ThreadTest2 implements Runnable{
	
	private String name;

	public ThreadTest2(){
		
	}
	
	public ThreadTest2(String name){
		this.name = name;
	}
	
	public void run(){
		
		for(int i = 0 ;i < 4;i++){
			System.out.println(name+"运行"+i);
		}
	}
	
	public static void main(String[] args) {
		
		ThreadTest2 t1 = new ThreadTest2("thread1");
		ThreadTest2 t2 = new ThreadTest2("thread2");
		Thread demo1 = new Thread(t1);
		Thread demo2 = new Thread(t2);
		demo1.start();
		demo2.start();
		
	}
	

}
