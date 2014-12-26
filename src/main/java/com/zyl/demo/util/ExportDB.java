package com.zyl.demo.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
/**
 * 用于加载配置文件,对应创建表.
 * 在web project中.可以通过<property name="hibernate.hbm2ddl.auto">update</property>实现.
 * 在实际操作表的时,将比对更新表结构.
 * @author Z10
 */
public class ExportDB {
	public static void main(String[] args){
		/*
		 * org.hibernate.cfg.Configuration类的作用：
		 * 读取hibernate配置文件(hibernate.cfg.xml或hiberante.properties)的.
		 * new Configuration()默认是读取hibernate.properties
		 * 所以使用new Configuration().configure();来读取hibernate.cfg.xml配置文件
		 */
		Configuration cfg = new Configuration().configure();
		
		/*
		 * org.hibernate.tool.hbm2ddl.SchemaExport工具类：
		 * 需要传入Configuration参数
		 * 此工具类可以将类导出生成数据库表
		 */
		SchemaExport export = new SchemaExport(cfg);
		
		/*
		 * 开始导出
		 * 第一个参数：script 是否打印DDL信息
		 * 第二个参数：export 是否导出到数据库中生成表
		 */
		export.create(true, true);
		
	}

}
