package com.dhcc.thread;

/**
 * 测试线程的强制执行。
 * @author zx
 * @createDate 2014-4-30
 * @since TODO: 来源版本
 *
 */

public class ThreadTest6 implements Runnable {

	public void run() {

		for (int i = 0; i < 3; i++) {

			System.out.println(Thread.currentThread().getName());
		}

	}

	public static void main(String[] args) {

		ThreadTest6 t6 = new ThreadTest6();

		Thread demo = new Thread(t6,"线程");

		demo.start();

		for (int i = 0; i < 50; i++) {

			if (i > 10) {
				try {
					demo.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		System.out.println("main线程执行》》"+i);
		}
	}

}
