package com.dhcc.thread;

//线程的优先级。

public class ThreadTest7 implements Runnable {

	public void run() {
		
		for (int i = 0 ; i < 5 ; i++){
			System.out.println(Thread.currentThread().getName()+"运行"+i);
		}
		
	}

	public static void main(String[] args) {
		
		//ThreadTest7 t7 = new ThreadTest7();
		
		Thread demo1 = new Thread(new ThreadTest7(),"A");
		Thread demo2 = new Thread(new ThreadTest7(),"B");
		Thread demo3 = new Thread(new ThreadTest7(),"C");
		demo1.setPriority(8);
		demo2.setPriority(2);
		demo3.setPriority(6);
		demo1.start();
		demo2.start();
		demo3.start();
		
	}

}
