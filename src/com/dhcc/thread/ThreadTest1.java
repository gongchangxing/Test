package com.dhcc.thread;

/**
 * 
 * 线程的实现的2中方式。
 * 第一种方式为继承thread类，
 * 第二种方式为实现runable接口。
 * 两种方式的区别，继承thread类不适合资源共享，实现runable接口，则
 * 很容易的实现资源共享。
 * 实现Runnable接口比继承Thread类所具有的优势：
	1）：适合多个相同的程序代码的线程去处理同一个资源
	2）：可以避免java中的单继承的限制
	3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立。
 * @author zx
 * @createDate 2014-4-30
 * @since TODO: 来源版本
 *
 */
public class ThreadTest1 extends Thread{
	
	private String name;

	public ThreadTest1(){
		
	}
	
	public ThreadTest1(String name){
		this.name = name;
	}
	
	public void run(){
		
		for(int i = 0 ;i < 4;i++){
			System.out.println(name+"运行"+i);
		}
	}
	
	public static void main(String[] args) {
		
		ThreadTest1 t1 = new ThreadTest1("zhangsan");
		ThreadTest1 t2 = new ThreadTest1("lisi");
		t1.start();
		t2.start();
		
	}
	

}
