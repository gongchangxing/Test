package com.dhcc.innerClass;

import java.util.Date;

// ~ File Information
// ====================================================================================================================

public class AnnoymouseInnerClassTest {

		public static void main(String[] args) {
			
			AnnoymouseInnerClass test = new AnnoymouseInnerClass();
			
			//打印日期
			String str = test.getDate(new Date());
			System.out.println(str);
			System.out.println("_____________");
			
			//使用匿名内部类
			String str2 = test.getDate(new Date(){});
			// 使用了花括号，但是不填入内容，执行结果和上面的完全一致
            // 生成了一个继承了Date类的子类的对象
			System.out.println(str2);
	        System.out.println("----------------");
	        
	        // 使用匿名内部类，并且重写父类中的方法
	        String str3 = test.getDate(new Date(){
	        	
	        	@Deprecated
	        	@Override
	        	public String toLocaleString(){
	        		 return "Hello: " + super.toLocaleString();
	        	}
	        });
			
			System.out.println(str3);
		}
}
