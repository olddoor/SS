package com.zyl.demo.ss1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zyl.demo.ss1.entity.User;
import com.zyl.demo.ss1.persistence.UserDao;
import com.zyl.demo.ss1.persistence.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	public void save(User u)throws Exception {
		userDao.save(u);
	}

	public void update(User vo)throws Exception  {
		User oldUser=userDao.getUser(vo.getId());
		if(vo.getLoginName()!=null&&!vo.getLoginName().equals("")){
			oldUser.setLoginName(vo.getLoginName());
		}
		if(vo.getUserName()!=null&&!vo.getUserName().equals("")){
			oldUser.setUserName(vo.getUserName());
		}
		if(vo.getPassword()!=null&&!vo.getPassword().equals("")){
			oldUser.setPassword(vo.getPassword());
		}
		userDao.update(oldUser);
	}

	public void delete(User u) throws Exception {
		userDao.delete(u);
	}

	public List<User> getUsers(Map<String, String> m) {
		List<User> us = new ArrayList<User>();
		if(m==null||m.equals("")){
			 us=userDao.getUsers(null);
		}else{
			us=userDao.getUsers(m);
		}
		return us;
	}
	
	public Long count (Map<String, String> m){
		return userDao.count(m);
	}
	
	public User getUser(Long id){
		return userDao.getUser(id)!=null ?userDao.getUser(id):null ;
	}

	@Override
	public Long count(String loginName, String userName, String cellNO) {
		return userDao.count(loginName,userName,cellNO);
	}


	@Override
	public List<User> getUsers(String loginName, String userName,
			String cellNO, String firstResult, String maxResults) {
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

}
