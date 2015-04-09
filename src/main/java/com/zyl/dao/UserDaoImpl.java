package com.zyl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import com.zyl.entity.User;
import com.zyl.util.HibernateUtil;
import com.zyl.util.util_Date;


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
	
	public Long count(String loginName, String userName,String cellNO) throws Exception{
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
	 * @throws Exception 
	 */
	/*
	 * 参数名传递方式,从而避免查询对数据库的依赖
	 * 使用：冒号(:) + 参数名，设置参数名
	 * 例如： select id, name from Student where name like :myname
	 */
	public Long count(Map<String, String> m) throws Exception{
		StringBuffer HQL=new StringBuffer(" select count(*) from User u WHERE 1=1");
		Session session = HibernateUtil.currentSession(); 
		Query query=null;
			String loginName=m.get("loginName");
			String userName=m.get("userName");
			String cellNO=m.get("cellNO");
			String sex=m.get("sex");
			String updateDate_Begin=m.get("updateDate_Begin");
			String updateDate_End=m.get("updateDate_End");
			
			if(loginName!=null&&!loginName.equals("")){
				HQL=HQL.append(" AND u.loginName like :loginName" );
			}
			if(userName!=null&&!userName.equals("")){
				HQL=HQL.append(" AND u.userName like :userName" );
			}
			if(cellNO!=null&&!cellNO.equals("")){
				HQL=HQL.append(" AND u.cellNO like :cellNO " );
			}
			if(sex!=null&&!sex.equals("")){
				HQL=HQL.append(" AND u.sex = :sex" );
			}
			if(updateDate_Begin!=null&&!updateDate_Begin.equals("")){
				HQL=HQL.append(" AND u.updateDate >= :updateDate_Begin " );
			}
			if(updateDate_End!=null&&!updateDate_End.equals("")){
				HQL=HQL.append(" AND u.updateDate <= :updateDate_End " );
			}
			
			query=session.createQuery(HQL.toString());
			
			if(loginName!=null&&!loginName.equals("")){
				query.setParameter("loginName", "%"+loginName+"%");
			}
			if(userName!=null&&!userName.equals("")){
				query.setParameter("userName", "%"+userName+"%");
			}
			if(cellNO!=null&&!cellNO.equals("")){
				query.setParameter("cellNO", "%"+cellNO+"%");
			}
			if(sex!=null&&!sex.equals("")){
				query.setParameter("sex", sex);
			}
			/*
			 * setParameter()方法包含三个参数，分别是命名参数名称，命名参数实际值，以及命名参数映射类型。对于某些参数类型
			 * setParameter
			 * ()方法可以更具参数值的Java类型，猜测出对应的映射类型，因此这时不需要显示写出映射类型，像上面的例子，可以直接这样写：
			 * query.setParameter(“customername”,name);但是对于一些类型就必须写明映射类型，比如
			 * java.util.Date类型，因为它会对应Hibernate的多种映射类型，比如Hibernate.DATA或者
			 * Hibernate.TIMESTAMP。
			 */
			if(updateDate_Begin!=null&&!updateDate_Begin.equals("")){
				query.setParameter("updateDate_Begin", util_Date.string2date(updateDate_Begin, util_Date.Format_date), 
						StandardBasicTypes.DATE);
			}
			if(updateDate_End!=null&&!updateDate_End.equals("")){
				query.setParameter("updateDate_End", util_Date.string2date(updateDate_End, util_Date.Format_date), 
						StandardBasicTypes.DATE);
			}
		return (Long) query.uniqueResult();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(Map<String, String> m) throws Exception {
		int maxResults=10;
		int firstResult=0;
		String order="desc";
		String sort="id";
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
			if(m.get("sort")!=null){
				sort=m.get("sort");
			}
			if(m.get("order")!=null){
				order=m.get("order");
			}
		}
		StringBuffer HQL=new StringBuffer("  from User u WHERE 1=1");
		Session session = HibernateUtil.currentSession(); 
		
		String loginName=m.get("loginName");
		String userName=m.get("userName");
		String cellNO=m.get("cellNO");
		String sex=m.get("sex");
		String updateDate_Begin=m.get("updateDate_Begin");
		String updateDate_End=m.get("updateDate_End");
		if(loginName!=null&&!loginName.equals("")){
			HQL=HQL.append(" AND u.loginName like :loginName" );
		}
		if(userName!=null&&!userName.equals("")){
			HQL=HQL.append(" AND u.userName like :userName" );
		}
		if(cellNO!=null&&!cellNO.equals("")){
			HQL=HQL.append(" AND u.cellNO like :cellNO " );
		}
		if(sex!=null&&!sex.equals("")){
			HQL=HQL.append(" AND u.sex = :sex" );
		}
		if(updateDate_Begin!=null&&!updateDate_Begin.equals("")){
			HQL=HQL.append(" AND u.updateDate >= :updateDate_Begin " );
		}
		if(updateDate_End!=null&&!updateDate_End.equals("")){
			HQL=HQL.append(" AND u.updateDate <= :updateDate_End " );
		}
		HQL=HQL.append(" order by "+sort+" "+order);
		
		Query query=session.createQuery(HQL.toString());
		
		if(loginName!=null&&!loginName.equals("")){
			query.setParameter("loginName", "%"+loginName+"%");
		}
		if(userName!=null&&!userName.equals("")){
			query.setParameter("userName", "%"+userName+"%");
		}
		if(cellNO!=null&&!cellNO.equals("")){
			query.setParameter("cellNO", "%"+cellNO+"%");
		}
		if(sex!=null&&!sex.equals("")){
			query.setParameter("sex", sex);
		}
		/*
		 * setParameter()方法包含三个参数，分别是命名参数名称，命名参数实际值，以及命名参数映射类型。对于某些参数类型
		 * setParameter
		 * ()方法可以更具参数值的Java类型，猜测出对应的映射类型，因此这时不需要显示写出映射类型，像上面的例子，可以直接这样写：
		 * query.setParameter(“customername”,name);但是对于一些类型就必须写明映射类型，比如
		 * java.util.Date类型，因为它会对应Hibernate的多种映射类型，比如Hibernate.DATA或者
		 * Hibernate.TIMESTAMP。
		 */
		if(updateDate_Begin!=null&&!updateDate_Begin.equals("")){
			query.setParameter("updateDate_Begin", util_Date.string2date(updateDate_Begin, util_Date.Format_date), 
					StandardBasicTypes.DATE);
		}
		if(updateDate_End!=null&&!updateDate_End.equals("")){
			query.setParameter("updateDate_End", util_Date.string2date(updateDate_End, util_Date.Format_date), 
					StandardBasicTypes.DATE);
		}
		query.setFirstResult(firstResult);//开始数量
		query.setMaxResults(maxResults);//每页数
		return query.list();
	}
	
	public User getUser(String id){
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
