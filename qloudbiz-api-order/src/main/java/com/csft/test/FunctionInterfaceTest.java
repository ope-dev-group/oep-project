package com.csft.test;

public class FunctionInterfaceTest {

	private void func(FunctionInterface functionInterface) {
		int x = 1;
		functionInterface.test(x);
	}
		
	public void testLambda(){
		
		//使用Lambda表达式代替上面的匿名内部类
		func((x) -> System.out.println("Hello World"+x));
	}
	
}
