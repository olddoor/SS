package com.zyl.demo.ss1;
/**
0
1
3
4
10
 */
public class testFinally {

	public static void main(String[] args) {
		System.out.println("0");
		testFinally t=new testFinally();
//		t.finally1();
		System.out.println(t.finally4());
		System.out.println("10");

	}
	
	public void finally1(){
		try {
			System.out.println("1");
		} catch (Exception e) {
			System.out.println("2");
		}finally{
			System.out.println("3");
		}
		System.out.println("4");
	}
	
	public String finally2(){
		try {
			System.out.println("1");
		} catch (Exception e) {
			System.out.println("2");
		}finally{
			System.out.println("3");
		}
		System.out.println("4");
		return "5";
	}
	
	public String finally4(){
		try {
			System.out.println("1");
			
		} catch (Exception e) {
			System.out.println("2");
			
		}finally{
			System.out.println("3");
		}
		System.out.println("4");
		return "over";
	}

}
