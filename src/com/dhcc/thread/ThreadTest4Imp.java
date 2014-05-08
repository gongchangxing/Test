package com.dhcc.thread;


public class ThreadTest4Imp {

	public static void main(String[] args) {
		
		ThreadTest4 t4 = new ThreadTest4();
		new Thread(t4, "1号窗口").start();
		new Thread(t4, "2号窗口").start();
		new Thread(t4, "3号窗口").start();
	}

}
