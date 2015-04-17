package com.zyl.demo.ss1;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanUtils;

import com.zyl.entity.User;
import com.zyl.util.NullAwareBeanUtilsBean;
import com.zyl.util.util_copy;

public class testBeanCopyDateNull {
	public static void main(String args[]) throws InvocationTargetException, IllegalAccessException{
		testBeanCopyDateNull t=new testBeanCopyDateNull();
		User u=new User();
		u.setUserName("u");
		u.setId("id");
//		u.setUpdateDate(null);
		
		User u2=new User();
		u2.setUserName("u2");
		u2.setId("id2");
		u2.setLoginName("only 2");
		u2.setUpdateDate(new Date());
		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		notNull.copyProperties(u2, u);
		System.out.println(u2.getUserName());
		System.out.println(u2.getId());
		System.out.println(u2.getUpdateDate());
		System.out.println(u2.getLoginName());
	}
	
}
