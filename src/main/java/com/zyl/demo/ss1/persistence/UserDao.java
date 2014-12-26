package com.zyl.demo.ss1.persistence;

import java.util.List;
import java.util.Map;

import com.zyl.demo.ss1.entity.User;

public interface UserDao {
	public void save(User u)throws Exception ;
	public void update(User u) throws Exception;
	public void delete(User u) throws Exception;
	public List<User> getUsers(Map<String,String> m);
	/**
	 * 符合条件的记录数
	 */
	//query返回的是个对象.所以这里返回值用包装类而非基本类型
	public Long count(Map<String,String> m);
	public Long count(String loginName, String userName,String cellNO);
	public User getUser(Long id);
	public User login(String loginName,String password);
	public User exitsUser(String loginName);
}
