package com.zyl.demo.ss1.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zyl.demo.ss1.entity.User;
import com.zyl.demo.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void save(User u) throws Exception {
		Session session = HibernateUtil.currentSession(); //实际就是使用opensession+线程绑定
		Transaction t = session.beginTransaction();
		try {
			session.save(u);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			throw e;
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
	}

	@Override
	public void update(User u) throws Exception {
		Session session = HibernateUtil.currentSession(); //实际就是使用opensession+线程绑定
		Transaction t = session.beginTransaction();
		try {
			session.update(u);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			throw e;
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
	}

	@Override
	public void delete(User u) throws Exception{
		Session session = HibernateUtil.currentSession(); //实际就是使用opensession+线程绑定
		Transaction t = session.beginTransaction();
		try {
			session.delete(u);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			throw e;
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
	}
	
	public Long count(String loginName, String userName,String cellNO){
		Map<String, String> m=new HashMap<String ,String>();
		m.put("loginName", loginName);
		m.put("userName", userName);
		m.put("cellNO", cellNO);
		return this.count(m);
	}
	
	/**
	 * 获取条件的记录数
	 * @param m
	 * @return Integer
	 */
	public Long count(Map<String, String> m){
		StringBuffer HQL=new StringBuffer(" select count(*) from User u WHERE 1=1");
		if(m==null||m.equals("")){
			Session session = HibernateUtil.currentSession(); 
			Query query=session.createQuery(HQL.toString());
			return (Long) query.uniqueResult();
		}else{
			String loginName=m.get("loginName");
			String userName=m.get("userName");
			String cellNO=m.get("cellNO");
			String sex=m.get("sex");
			String updateDate_Begin=m.get("updateDate_Begin");
			String updateDate_End=m.get("updateDate_End");
			Session session = HibernateUtil.currentSession();
			if(loginName!=null&&!loginName.equals("")){
				HQL=HQL.append(" AND u.loginName like '%"+loginName+"%'" );
			}
			if(userName!=null&&!userName.equals("")){
				HQL=HQL.append(" AND u.userName like '%"+userName+"%'" );
			}
			if(cellNO!=null&&!cellNO.equals("")){
				HQL=HQL.append(" AND u.cellNO like '%"+cellNO+"%'" );
			}
			if(sex!=null&&!sex.equals("")){
				HQL=HQL.append(" AND u.sex = '"+sex+"'" );
			}
			if(updateDate_Begin!=null&&!updateDate_Begin.equals("")){
				HQL=HQL.append(" AND to_char(u.updateDate,'yyyy-mm-dd') > '"+updateDate_Begin+"'" );
			}
			if(updateDate_End!=null&&!updateDate_End.equals("")){
				HQL=HQL.append(" AND to_char(u.updateDate,'yyyy-mm-dd)' < '"+updateDate_End+"'" );
			}
			Query query=session.createQuery(HQL.toString());
			return (Long) query.uniqueResult();
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(Map<String, String> m) {
		int maxResults=10;
		int firstResult=0;
		if(m!=null&&!m.isEmpty()){
			if(m.get("row")!=null){ //每页数量
				String maxResults_Str = m.get("row");
				if (maxResults_Str != null && !maxResults_Str.equals("")) {
					maxResults=(Integer.valueOf(maxResults_Str)).intValue();
				}
			}
			if(m.get("page")!=null){//第几页
				String page = m.get("page");
				if (page != null && !page.equals("")){
					firstResult=(Integer.valueOf(page).intValue()-1)*maxResults;  //从第几个开始取数
				}
			}
		}
		StringBuffer HQL=new StringBuffer("  from User u WHERE 1=1");
		if(m==null||m.equals("")){
			Session session = HibernateUtil.currentSession(); 
			Query query=session.createQuery(HQL.toString());
			return query.list();
		}else{
			String loginName=m.get("loginName");
			String userName=m.get("userName");
			String cellNO=m.get("cellNO");
			String sex=m.get("sex");
			String updateDate_Begin=m.get("updateDate_Begin");
			String updateDate_End=m.get("updateDate_End");
			Session session = HibernateUtil.currentSession();
			if(loginName!=null&&!loginName.equals("")){
				HQL=HQL.append(" AND u.loginName like '%"+loginName+"%'" );
			}
			if(userName!=null&&!userName.equals("")){
				HQL=HQL.append(" AND u.userName like '%"+userName+"%'" );
			}
			if(cellNO!=null&&!cellNO.equals("")){
				HQL=HQL.append(" AND u.cellNO like '%"+cellNO+"%'" );
			}
			if(sex!=null&&!sex.equals("")){
				HQL=HQL.append(" AND u.sex = '"+sex+"'" );
			}
			if(updateDate_Begin!=null&&!updateDate_Begin.equals("")){
				HQL=HQL.append(" AND u.updateDate >= :updateDate_Begin ");
			}
			if(updateDate_End!=null&&!updateDate_End.equals("")){
				HQL=HQL.append(" AND u.updateDate <= :updateDate_End" );
			}
			
			Query query=session.createQuery(HQL.toString());
			query.setDate("updateDate_Begin", updateDate_Begin);
			
			
			query.setFirstResult(firstResult);//开始数量
			query.setMaxResults(maxResults);//每页数
			return query.list();
		}
	}
	
	public User getUser(Long id){
		Session session = HibernateUtil.currentSession(); 
		User u= (User) session.get(User.class, id);
		return u;
	}
	
	public User exitsUser(String loginName) {
		Session session = HibernateUtil.currentSession(); 
		Query query=session.createQuery(" from User u where u.loginName='"+loginName+"'");
		List<User>us=query.list();
		if(us.size()>0){
			return us.get(0);
		}else{
			return null;
		}
	}
	@Override
	public User login(String loginName, String password) {
		Session session = HibernateUtil.currentSession(); 
		Query query=session.createQuery(" from User u where u.loginName='"+loginName+"' and u.password='"+password+"'");
		List<User>us=query.list();
		if(us.size()>0){
			return us.get(0);
		}else{
			return null;
		}
	}
}
