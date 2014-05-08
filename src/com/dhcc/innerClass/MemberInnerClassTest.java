package com.dhcc.innerClass;


// ~ File Information
// ====================================================================================================================

public class MemberInnerClassTest {

		public static void main(String[] args) {
			MemberInner.Inner2 inner = new MemberInner().new Inner2();
			
			inner.doSomething();
		}
}
