package com.dhcc.thread;

/**
 * 
 * 线程的实现的2中方式。
 * 第一种方式为继承thread类，
 * 第二种方式为实现runable接口。
 * 两种方式的区别，继承thread类不适合资源共享，实现runable接口，则
 * 很容易的实现资源共享。
 * @author zx
 * @createDate 2014-4-30
 * @since TODO: 来源版本
 *
 */
public class ThreadTest3 extends Thread{
	
	private int count = 5;
	
	public void run(){
		
		for(int i = 0 ;i < 7;i++){
			
			if(count >0){
				System.out.println("count= "+count--);
			}
		}
	}
	
	public static void main(String[] args) {
		
		ThreadTest3 t1 = new ThreadTest3();
		ThreadTest3 t2 = new ThreadTest3();
		ThreadTest3 t3 = new ThreadTest3();
		t1.start();
		t2.start();
		t3.start();
	}
	

}
