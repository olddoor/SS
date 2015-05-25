package com.zyl.demo.ss1.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * 二、运行时复制对象
	例程ReflectTester 类进一步演示了Reflection API的基本使用方法。
ReflectTester类有一个copy(Object object)方法，这个方法能够创建一
个和参数object 同样类型的对象，然后把object对象中的所有属性拷贝到
新建的对象中，并将它返回.
	这个例子只能复制简单的JavaBean，假定JavaBean 
的每个属性都有public 类型的getXXX()和setXXX()方法。
 */
public class ReflectTester {
	
    public static void main(String[] args) throws Exception {
        Customer customer = new Customer("Tom", 21);
        customer.setId(new Long(1));
 
        Customer customerCopy = (Customer) new ReflectTester().copy(customer);
        System.out.println("Copy information:" + customerCopy.getId() + " " + customerCopy.getName() + " "
                + customerCopy.getAge());
    }
	
    public Object copy(Object object) throws Exception {
        // 获得对象的类型
        Class<?> classType = object.getClass();//返回此 Object 的运行时类。
        System.out.println("Class的名字是:" + classType.getName());
 
        // 通过默认构造方法创建一个新的对象
        Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
 
        // 获得对象的所有属性
        Field fields[] = classType.getDeclaredFields();
        System.out.println("该对象的所有属性的数量为:"+fields.length);
        System.out.println("=============");
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
 
            String fieldName = field.getName();
            	System.out.print("本变量的名字是"+fieldName);
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            	System.out.println("  本变量首字母大写是"+firstLetter);
            // 获得和属性对应的getXXX()方法的名字
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            	System.out.print("  拼接后的get方法名字是"+getMethodName);
            // 获得和属性对应的setXXX()方法的名字
            String setMethodName = "set" + firstLetter + fieldName.substring(1);
                System.out.println("  拼接后的set方法名字是"+setMethodName);
            // 获得和属性对应的getXXX()方法
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});
            // 获得和属性对应的setXXX()方法
            Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});
 
            // 调用原对象的getXXX()方法!!!
            Object value = getMethod.invoke(object, new Object[]{});
            System.out.println(fieldName + ":" + value);
            // 调用拷贝对象的setXXX()方法!!!
            setMethod.invoke(objectCopy, new Object[]{value});//赋值
            System.out.println("===本次结束,进入下一循环===");
        }
        return objectCopy;
    }
 
}
 
class Customer {
    private Long id;
 
    private String name;
 
    private int age;
    
    private boolean sex;
 
    public Customer() {
    }
 
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
}
