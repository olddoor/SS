package com.zyl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.zyl.entity.User;


public interface UserDao {
	@Transactional
	public void save(User u)throws Exception ;
	@Transactional
	public void update(User u) throws Exception;
	@Transactional
	public void delete(User u) throws Exception;
	public List<User> getUsers(Map<String,String> m) throws Exception;
	/**
	 * 符合条件的记录数
	 * @throws Exception 
	 */
	//query返回的是个对象.所以这里返回值用包装类而非基本类型
	public Long count(Map<String,String> m) throws Exception;
	public Long count(String loginName, String userName,String cellNO) throws Exception;
	public User get(String id);
	public User login(String loginName,String password);
	public User exitsUser(String loginName);
}
