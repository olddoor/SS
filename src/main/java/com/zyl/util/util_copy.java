package com.zyl.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
public class util_copy extends BeanUtils {

	private static Map cache = new HashMap();
	private static Log logger = LogFactory.getFactory().getInstance(
			util_copy.class);

	private util_copy() {

	}

	static {
		// 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
		// ConvertUtils.register(new SqlTimestampConverter(),
		// java.sql.Timestamp.class);
		// 注册util.date的转换器，即允许BeanUtils.copyProperties时的源目标的util类型的值允许为空
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
	}
	
	/**
	 * 用于对象属性值copy
	 * 继承apache的BeanUtils,避免调用copyProperties时因date为空而报错的情况
	 */
	public static void copyProperties(Object target, Object source)
			throws InvocationTargetException, IllegalAccessException {
		// 支持对日期copy
		org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
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
