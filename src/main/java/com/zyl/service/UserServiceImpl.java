package com.zyl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zyl.dao.UserDao;
import com.zyl.dao.UserDaoImpl;
import com.zyl.entity.User;
@Component(value="userService")
public class UserServiceImpl implements UserService {
	
//	private UserDao userDao = new UserDaoImpl();//后续用spring替代
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void save(User u)throws Exception {
		userDao.save(u);
	}

	public void update(User vo)throws Exception  {
		User oldUser=userDao.get(vo.getId());
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
		return userDao.get(id)!=null ?userDao.get(id):null ;
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

}
