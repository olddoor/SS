package com.zyl.demo.ss1;

public class testException {
/**
 * 证明try catch中可以在finally把事情做完后, 再次往上抛出异常
 */
	public static void main(String[] args) {
		testException t=new testException();
		try {
			t.getException();
		} catch (Exception e) {
			System.out.println(" >>>>>>>>>>>>>error happen"+e.getMessage());
			return;
		}
		System.out.println(" everythins is ok");
	}

	public void getException() throws Exception {
		int x = 1;
		int y = 0;
		try {
			int result = x / y;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" ==========报错咯");
			throw e;
		} finally {
			System.out.println(" ========the method getException called over!");
		}
	}

}
