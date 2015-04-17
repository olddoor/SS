package com.zyl.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 自动赋值工具类
 * 
 * @author Z10
 *
 */
public class util_copy extends BeanUtilsBean {

	/**
	 * bean之间属性复制,null不复制.
	 * 用法: 父类指向子类:
	 * BeanUtilsBean copy= new util_copy();
	   copy.copyProperties(oldUser, u);//copyProperties(,)里面会调用被覆盖的copyProperties(, ,)
	 */
	 @Override
	 public void copyProperty(Object dest, String name, Object value)
	            throws IllegalAccessException, InvocationTargetException {
	        if(value==null)return;
	        super.copyProperty(dest, name, value);
	    }
	

	/**
	 * 用于将httpRequest请求参数封装为map<name,value>的集合
	 */
	public static Map<String, String> httpReq_2_map(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		Map<String, String> m = new HashMap();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			m.put(name, value);
		}
		return m;
	}

}
