package com.zyl.common.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDao<T> {
	/**
	 * 多线程下使用可能有线程安全的问题,因为hibernateTemplate内部有一些类变量.特别是fetchSize和maxResults
	 */
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
					
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public Session getCurrentSession(){
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}
	
	public void save(Object entity){
		 getHibernateTemplate().save(entity);
	}
	public void delete(Object entity){
		 getHibernateTemplate().delete(entity);
	}
	public void saveOrUpdate(Object entity){
		 getHibernateTemplate().saveOrUpdate(entity);
	}
	public T get(Class<T> T, Serializable id){
		 return getHibernateTemplate().get(T, id);
	}
	
	
	
}
