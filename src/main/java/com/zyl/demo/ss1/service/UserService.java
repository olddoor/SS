package com.zyl.demo.ss1.service;

import java.util.List;
import java.util.Map;

import com.zyl.demo.ss1.entity.User;

public interface UserService {

	public void save(User u)throws Exception;
	public void update(User u)throws Exception ;
	public void delete(User u)throws Exception ;
	public List<User> getUsers(Map<String,String> m);
	public User login(String loginName,String password);
	public User exitsUser(String loginName);
	/**
	 * @param loginName
	 * @param userName
	 * @param cellNO
	 * @param firstResult 开始页数
	 * @param fetchSize	每页数量
	 * @return
	 */
	public List<User> getUsers(String loginName, String userName,String cellNO,String firstResult,String maxResults);
	/**
	 * 符合条件的记录数
	 */
	public Long count(Map<String,String> m);
	public Long count(String loginName, String userName,String cellNO);
	public User getUser(Long id);

}
