package com.dhcc.innerClass;

// ~ File Information
// ====================================================================================================================

public class StaticInner {

	private static int a = 5;
	
	//静态内部类
	public static class Inner{
		//静态内部类可以访问外部类的静态成员.
		//并且它只能访问静态的.
		public void test(){
			System.out.println(a);
		}
		
	}

}
