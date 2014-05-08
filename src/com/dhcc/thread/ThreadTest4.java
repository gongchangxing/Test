package com.dhcc.thread;
/**
 * 使用继承runable接口的方式，
 * 更易实现资源的共享，如买票系统的剩余票数问题。
 *
 * 
 * @author zx
 * @createDate 2014-4-30
 * @since TODO: 来源版本
 *
 */

public class ThreadTest4 implements Runnable {
	
	private int ticket = 5;

	public void run() {
		
		for(int i = 0 ; i < 20 ; i++){
			
			if(this.ticket >0 ){
				
				System.out.println(Thread.currentThread().getName()+"正在卖票"+this.ticket--);
			}
			
		}
		
	}
	
}
