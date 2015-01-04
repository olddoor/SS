package com.zyl.demo.ss1.service;

import java.util.List;
import java.util.Map;

import com.zyl.demo.ss1.entity.User;

public interface UserService {

	public void save(User u)throws Exception;
	public void update(User u)throws Exception ;
	public void delete(User u)throws Exception ;
	public List<User> getUsers(Map<String,String> m) throws Exception;
	public User login(String loginName,String password);
	public User exitsUser(String loginName);
	/**
	 * @param loginName
	 * @param userName
	 * @param cellNO
	 * @param firstResult 开始页数
	 * @param fetchSize	每页数量
	 * @return
	 * @throws Exception 
	 */
	public List<User> getUsers(String loginName, String userName,String cellNO,String firstResult,String maxResults) throws Exception;
	/**
	 * 符合条件的记录数
	 * @throws Exception 
	 */
	public Long count(Map<String,String> m) throws Exception;
	public Long count(String loginName, String userName,String cellNO) throws Exception;
	public User getUser(Long id);

}
