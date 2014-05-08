package com.dhcc.innerClass;

/** 成员内部类，作为外部类的成员，可以直接使用外部类的所有成员和方法，即使是private的。同时，外部类要方位内部类的
 * 
 * 成员变量和方法，则需要通过内部类的对象来获取。
 * 
 * 成员内部类，不能含有static的变量和方法，因为成员内部类需要先创建了外部类，才能创建它自己的。
 * 
 * 在成员内部类要引用外部对象时，使用outr.this 来表示外部类对象。
 *
 * 而需要创建内部类对象，可以使用outer.inner  obj = outerobj.new inner();
 *
 */

public class Outer {

	public static void main(String[] args) {
		
		Outer outer = new Outer();
		Outer.Inner inner = outer.new Inner();
		inner.print("outer.new");
		
		inner = outer.getInner();
		inner.print("out.get");

	}

	// 个人推荐使用getxxx()来获取成员内部类，尤其是该内部类的构造函数无参数时
	
	public Inner getInner(){
		return new Inner();
	}

	public class Inner {
		public void print(String str) {
			System.out.println(str);
		}
	}
}
