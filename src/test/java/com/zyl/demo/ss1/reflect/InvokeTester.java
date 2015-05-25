package com.zyl.demo.ss1.reflect;

import java.lang.reflect.Method;
/**http://lavasoft.blog.51cto.com/62575/43218
 * 三、用反射机制调用对象的方法
 * 在例程InvokeTester类的main()方法中，运用反射机制调用一个InvokeTester对象的add()和echo()方法
	add()方法的两个参数为int 类型，获得表示add()方法的Method对象的代码如下：
	Method addMethod=classType.getMethod("add",new Class[]{int.class,int.class});
	Method类的invoke(Object obj,Object args[])方法接收的参数必须为对象，如果参数为基本类型数据，
		必须转换为相应的包装类型的对象。
	invoke()方法的返回值总是对象，如果实际被调用的方法的返回类型是基本类型数据，
		那么invoke()方法会把它转换为相应的包装类型的对象，再将其返回。
	在本例中，尽管InvokeTester 类的add()方法的两个参数以及返回值都是int类型，调用add Method 对象的invoke()方法时，只能传递Integer 类型的参数，并且invoke()方法的返回类型也是Integer 类型，Integer 类是int 基本类型的包装类：
	Object result=addMethod.invoke(invokeTester,
	new Object[]{new Integer(100),new Integer(200)});
	System.out.println((Integer)result); //result 为Integer类型

 *
 */
public class InvokeTester {
    public int add(int param1, int param2) {
        return param1 + param2;
    }
 
    public String echo(String msg) {
        return "echo: " + msg;
    }
 
    public static void main(String[] args) throws Exception {
        Class<?> classType = InvokeTester.class;
        Object invokeTester = classType.newInstance();
 
        // Object invokeTester = classType.getConstructor(new
        // Class[]{}).newInstance(new Object[]{});
        //获取InvokeTester类的add()方法
        Method addMethod = classType.getMethod("add", new Class[]{int.class, int.class});
        //调用invokeTester对象上的add()方法
        Object result = addMethod.invoke(invokeTester, new Object[]{new Integer(100), new Integer(200)});
        System.out.println((Integer) result);
 
 
        //获取InvokeTester类的echo()方法
        Method echoMethod = classType.getMethod("echo", new Class[]{String.class});
        //调用invokeTester对象的echo()方法
        result = echoMethod.invoke(invokeTester, new Object[]{"Hello"});
        System.out.println((String) result);
    }
}

