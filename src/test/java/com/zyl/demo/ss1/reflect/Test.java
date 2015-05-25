package com.zyl.demo.ss1.reflect;

import java.lang.reflect.Field;

public class Test {
	 public static void main(String[] args) throws Exception {
//	        Field field = String.class.getDeclaredField("value");
//	        field.setAccessible(true);
//	        field.set("hello!", "cheers".toCharArray());
//	        System.out.println("hello!");
	        
	        Field field = null;
	        field = Integer.class.getDeclaredField("value");
	        field.setAccessible(true);
	        field.set(42, 43);
	        System.out.printf("6 * 7 = %d\n", 6 * 7);
	    }
}
