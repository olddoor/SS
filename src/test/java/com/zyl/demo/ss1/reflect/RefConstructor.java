package com.zyl.demo.ss1.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
/**
 * 一、通过Class类获取成员变量、成员方法、接口、超类、构造方法等
 * @author Z10
 */
public class RefConstructor {
    public static void main(String args[]) throws Exception {
        RefConstructor ref = new RefConstructor();
        ref.getConstructor();
 
    }
 
    public void getConstructor() throws Exception {
        Class c = 
        c = Class.forName("java.lang.Long");//返回与带有给定字符串名的类或接口相关联的 Class 对象
        Class cs[] = {java.lang.String.class};
 
        System.out.println("\n-------------------------------\n");
 /**
  * 返回一个 Constructor 对象，它反映此 Class 对象所表示的类的指定公共构造方法。parameterTypes 参数是 Class 对象的一个数组，
  * 这些 Class 对象按声明顺序标识构造方法的形参类型。 
  */
        Constructor cst1 = c.getConstructor(cs);//Class cs[] = {java.lang.String.class};
        System.out.println("1、通过参数获取指定Class对象的构造方法：");
        System.out.println(cst1.toString());
 
        Constructor cst2 = c.getDeclaredConstructor(cs);
        System.out.println("2、通过参数获取指定Class对象所表示的类或接口的构造方法：");
        System.out.println(cst2.toString());
 
        Constructor cst3 = c.getEnclosingConstructor();
        System.out.println("3、获取本地或匿名类Constructor 对象，它表示基础类的立即封闭构造方法。");
        if (cst3 != null) System.out.println(cst3.toString());
        else System.out.println("-- 没有获取到任何构造方法！");
 
        Constructor[] csts = c.getConstructors();
        System.out.println("4、获取指定Class对象的所有构造方法：");
        for (int i = 0; i < csts.length; i++) {
            System.out.println(csts[i].toString());
        }
 
        System.out.println("\n-------------------------------\n");
 
        Type types1[] = c.getGenericInterfaces();
        System.out.println("1、返回直接实现的接口：");
        for (int i = 0; i < types1.length; i++) {
            System.out.println(types1[i].toString());
        }
 
        Type type1 = c.getGenericSuperclass();
        System.out.println("2、返回直接超类：");
        System.out.println(type1.toString());
 
        Class[] cis = c.getClasses();
        System.out.println("3、返回超类和所有实现的接口：");
        for (int i = 0; i < cis.length; i++) {
            System.out.println(cis[i].toString());
        }
 
        Class cs1[] = c.getInterfaces();
        System.out.println("4、实现的接口");
        for (int i = 0; i < cs1.length; i++) {
            System.out.println(cs1[i].toString());
        }
 
        System.out.println("\n-------------------------------\n");
 
        Field fs1[] = c.getFields(); //成员变量/字段
        System.out.println("1、类或接口的所有可访问公共字段：");
        for (int i = 0; i < fs1.length; i++) {
            System.out.println(fs1[i].toString());
        }
 
        Field f1 = c.getField("MIN_VALUE");
        System.out.println("2、类或接口的指定已声明指定公共成员字段：");
        System.out.println(f1.toString());
 
        Field fs2[] = c.getDeclaredFields();
        System.out.println("3、类或接口所声明的所有字段：");
        for (int i = 0; i < fs2.length; i++) {
            System.out.println(fs2[i].toString());
        }
 
        Field f2 = c.getDeclaredField("serialVersionUID");
        System.out.println("4、类或接口的指定已声明指定字段：");
        System.out.println(f2.toString());
 
        System.out.println("\n-------------------------------\n");
 
        Method m1[] = c.getMethods(); //方法
        System.out.println("1、返回类所有的公共成员方法：");
        for (int i = 0; i < m1.length; i++) {
            System.out.println(m1[i].toString());
        }
 
        Method m2 = c.getMethod("longValue", new Class[]{});
        System.out.println("2、返回指定公共成员方法：");
        System.out.println(m2.toString());
    }
}
 
