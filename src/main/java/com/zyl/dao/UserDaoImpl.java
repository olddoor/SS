package com.zyl.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zyl.common.dao.BaseDao;
import com.zyl.common.dao.GenericHibernateDao;
import com.zyl.entity.User;
import com.zyl.util.util_Date;

@Component(value="userDao")
public class UserDaoImpl extends BaseDao {

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
	@SuppressWarnings("unchecked")
	public Long count(Map<String, String> m) throws Exception{
		StringBuffer HQL=new StringBuffer(" select count(*) from User u WHERE 1=1");
			final String loginName=m.get("loginName");
			final String userName=m.get("userName");
			final String cellNO=m.get("cellNO");
			final String sex=m.get("sex");
			final String updateDate_Begin=m.get("updateDate_Begin");
			final String updateDate_End=m.get("updateDate_End");
			
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

			final String hqL=HQL.toString();
			List<User> toRet = getHibernateTemplate().executeFind(
					new HibernateCallback() {
						public Object doInHibernate(Session session) throws HibernateException, SQLException {
							Query query = session.createQuery(hqL);
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
								try {
									query.setParameter("updateDate_Begin", util_Date.string2date(updateDate_Begin, util_Date.Format_date), 
											StandardBasicTypes.DATE);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							if(updateDate_End!=null&&!updateDate_End.equals("")){
								try {
									query.setParameter("updateDate_End", util_Date.string2date(updateDate_End, util_Date.Format_date), 
											StandardBasicTypes.DATE);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							List<User> list = query.list();
							return list;
						}
					}
				);
			return toRet!=null?new Long(toRet.size()):0L;
	}
	

	@SuppressWarnings("unchecked")
	public List<User> getUsers(Map<String, String> m) throws Exception {
		int maxResults=10;
		int firstResult=0;
		String order="desc";
		String sort="id";
		if(m!=null&&!m.isEmpty()){
			if(m.get("rows")!=null){ //每页数量
				String rows = m.get("rows");
				maxResults=(Integer.valueOf(rows)).intValue();
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
		
		final String loginName=m.get("loginName");
		final String userName=m.get("userName");
		final String cellNO=m.get("cellNO");
		final String sex=m.get("sex");
		final String updateDate_Begin=m.get("updateDate_Begin");
		final String updateDate_End=m.get("updateDate_End");
		final int max_Results=maxResults;
		final int first_Result=firstResult;
		
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
		
		final String hql = HQL.toString();
		List<User> toRet = getHibernateTemplate().executeFind(
			new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
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
						try {
							query.setParameter("updateDate_Begin", util_Date.string2date(updateDate_Begin, util_Date.Format_date), 
									StandardBasicTypes.DATE);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(updateDate_End!=null&&!updateDate_End.equals("")){
						try {
							query.setParameter("updateDate_End", util_Date.string2date(updateDate_End, util_Date.Format_date), 
									StandardBasicTypes.DATE);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					query.setFirstResult(first_Result);//开始数量
					query.setMaxResults(max_Results);//每页数
					List<User> list = query.list();
					return list;
				}
			}
		);		
		return toRet;
	}
	
	public User exitsUser(String loginName) {
		String hql=" from User u where u.loginName='"+loginName+"'";
		List<User>us=getHibernateTemplate().find(hql);
		if(us.size()>0){
			return us.get(0);
		}else{
			return null;
		}
	}
	public User login(String loginName, String password) {
		String hql=" from User u where u.loginName='"+loginName+"' and u.password='"+password+"'";
		List<User>us=getHibernateTemplate().find(hql);
		if(us.size()>0){
			return us.get(0);
		}else{
			return null;
		}
	}

}
