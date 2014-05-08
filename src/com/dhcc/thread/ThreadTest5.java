package com.dhcc.thread;

// ~ File Information
// ====================================================================================================================

public class ThreadTest5 implements Runnable {

	public void run() {
		
		for(int i = 0 ; i < 3 ; i++){
			
			System.out.println(Thread.currentThread().getName());
		}
		
	}
	
	public static void main(String[] args) {
		
		ThreadTest5 t5 = new ThreadTest5();
		
		//new Thread(t5, "线程A").start();
		//new Thread(t5,"线程B").start();
		//new Thread(t5).start();
		Thread thread = new Thread(t5);
		System.out.println("线程启动之前》》》》》"+thread.isAlive());
		thread.start();
		System.out.println("线程启动之后》》》》》"+thread.isAlive());
		
	}


}
