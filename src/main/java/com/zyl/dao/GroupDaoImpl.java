package com.zyl.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zyl.common.dao.BaseDao;
import com.zyl.common.dao.GenericHibernateDao;
import com.zyl.entity.Group;
import com.zyl.entity.User;
import com.zyl.util.util_Date;

@Component(value="groupDao")
public class GroupDaoImpl extends BaseDao {
	
	public Group getGroup(String id) throws Exception {
		return getHibernateTemplate().get(Group.class, id);
	}
	public void save(Group g) throws Exception {
		 getHibernateTemplate().saveOrUpdate(g);
	}
	public void update(Group g) throws Exception {
		 getHibernateTemplate().saveOrUpdate(g);
	}
	public void delete(Group g) throws Exception {
		 getHibernateTemplate().delete(g);
	}
	public List<Group> getGroups(Map<String, String> m) throws Exception {
		String hql=" from Group";
		List<Group> gs= getHibernateTemplate().find(hql);
		return gs;
	}
	public List<Group> getTree(Map m) {
			List<Group> list=getHibernateTemplate().executeFind(new HibernateCallback(){        
			            //实现HibernateCallback接口必须实现的方法   
				public Object doInHibernate(Session session)throws HibernateException, SQLException{
					String hql=" from Group";
					Query query = session.createQuery(hql);
					List<Group> list=query.list();
					return query.list();
				}});
		return list;
	}
	public List<Group> getTree(Map m) {
		String hql=" select id,name as text, children as children from Group";
		return getHibernateTemplate().f
	}
	
}
