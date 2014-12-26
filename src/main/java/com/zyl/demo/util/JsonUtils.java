package com.zyl.demo.util;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zyl.demo.util.fastjson.FastjsonFilter;

/**
 * 基于fastjson(SSHE孙宇的代码)
 * @author Z10
 *
 */
public class JsonUtils {
	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 基于fastjson进行json及bean的转换
	 * @param object
	 * @param logger 传入转换对象的logger实例.可为空
	 * @param includesProperties
	 *            需要转换的属性
	 * @param excludesProperties
	 *            不需要转换的属性
	 * @param User_Agent 为兼容IE6的JSON,传入请求文件的头部进行判断
	 * 范例:getRequest().getHeader("User-Agent");
	 * @return 
	 */
	public static String obj2Json_ByFilter(Object object, String[] includesProperties, String[] excludesProperties,Logger logger,String User_Agent) {
		FastjsonFilter filter = new FastjsonFilter();// excludes优先于includes
		if (excludesProperties != null && excludesProperties.length > 0) {
			filter.getExcludes().addAll(Arrays.<String> asList(excludesProperties));
		}
		if (includesProperties != null && includesProperties.length > 0) {
			filter.getIncludes().addAll(Arrays.<String> asList(includesProperties));
		}
		if(logger!=null){
			logger.info("对象转JSON：要排除的属性[" + includesProperties + "]要包含的属性[" + excludesProperties + "]");
		}
		String json;
		if (User_Agent!=null&&!User_Agent.equals("")&&StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1) {
			// 使用SerializerFeature.BrowserCompatible特性会把所有的中文都会序列化为\\uXXXX这种格式，字节数会多一些，但是能兼容IE6
			json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BrowserCompatible);
		} else {
			// 使用SerializerFeature.WriteDateUseDateFormat特性来序列化日期格式的类型为yyyy-MM-dd hh24:mi:ss
			// 使用SerializerFeature.DisableCircularReferenceDetect特性关闭引用检测和生成
			json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
		}
		//logger.info("转换后的JSON字符串：" + json);
//			getResponse().setContentType("text/html;charset=utf-8");
//			getResponse().getWriter().write(json);
//			getResponse().getWriter().flush();
//			getResponse().getWriter().close();
		return json;
	}
	public <T> T  json2obj(JSON json,Class<T> clazz){
		return JSON.toJavaObject(json, clazz);
	}

}
