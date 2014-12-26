package com.zyl.demo.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 自动赋值工具类
 * @author Z10
 *
 */
public class util_copy {
	/**
	 * httpRequest请求参数封装为map<name,value>的集合
	 */
	public static Map<String,String> httpReq_2_map(HttpServletRequest request){
		Enumeration<String> names = request.getParameterNames();
		Map<String,String> m=new HashMap();
		while (names.hasMoreElements()){
			String name = names.nextElement();
			String value = request.getParameter(name);
			m.put(name, value);
		}
		return m;
	}
}
