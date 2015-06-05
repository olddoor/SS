package com.zyl.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zyl.entity.Grid;
import com.zyl.entity.msgStr;
import com.zyl.util.JsonUtils;

/**
 * Controller的基类.用于定义一些Controller通用的功能封装.
 * 比如log,分页对象模型.
 */
public abstract class BaseController {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 使用封装好的json工具(fastjson)输出
	 * @param obj
	 * @param response
	 * @throws IOException
	 */
	public void writeJson(Object obj, String[] includesProperties, String[] excludesProperties,HttpServletResponse response) throws IOException{
		String str=JsonUtils.obj2Str_ByFilter(obj, includesProperties, excludesProperties, null, null);//含时间格式化
		JsonUtils.write(response, str);
	}
}
