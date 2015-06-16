package com.zyl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zyl.dao.UserDaoImpl;
import com.zyl.entity.User;
import com.zyl.util.QRCodeUtil;
@Component(value="userService")
public class UserServiceImpl implements UserService {
	
	private UserDaoImpl userDao;

	public UserDaoImpl getUserDao() {
		return userDao;
	}
	@Resource(name="userDao")
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public void save(User u)throws Exception {
		userDao.saveOrUpdate(u);
	}

	public void update(User u)throws Exception  {
		userDao.saveOrUpdate(u);
	}

	public void delete(User u) throws Exception {
		userDao.delete(u);
	}

	public List<User> getUsers(Map<String, String> m) throws Exception {
		List<User> us = new ArrayList<User>();
		if(m==null||m.equals("")){
			 us=userDao.getUsers(null);
		}else{
			us=userDao.getUsers(m);
		}
		return us;
	}
	
	public Long count (Map<String, String> m) throws Exception{
		return userDao.count(m);
	}
	
	public User getUser(String id){
		return (User) userDao.get(User.class, id);
	}

	@Override
	public Long count(String loginName, String userName, String cellNO) throws Exception {
		return userDao.count(loginName,userName,cellNO);
	}


	@Override
	public List<User> getUsers(String loginName, String userName,
			String cellNO, String firstResult, String maxResults) throws Exception {
		Map<String, String> m=new HashMap<String ,String>();
		m.put("loginName", loginName);
		m.put("userName", userName);
		m.put("cellNO", cellNO);
		m.put("firstResult", firstResult);
		m.put("maxResults", maxResults);
		return this.getUsers(m);
	}

	@Override
	public User login(String loginName, String password) {
		return userDao.login(loginName, password);
	}

	@Override
	public User exitsUser(String loginName) {
		return userDao.exitsUser(loginName);
	}
	@Override
	public String createVcard() {
		try {
			List<User>us =userDao.getUsers();
			for(User u:us){
				if(u.getLoginName()!=null&&!u.getLoginName().equals("")){
					QRCodeUtil qt=new QRCodeUtil();
					qt.encode(u.getLoginName(), u.getLoginName());
					qt.decode(u.getLoginName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
