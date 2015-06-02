package com.zyl.service;

import java.util.List;
import java.util.Map;




import org.springframework.transaction.annotation.Transactional;

import com.zyl.entity.Group;
import com.zyl.entity.User;
public interface GroupService {
	@Transactional
	public void save(Group g)throws Exception;
	@Transactional
	public void update(Group g)throws Exception ;
	@Transactional
	public void delete(Group g)throws Exception ;
	public List<Group> getGroups(Map<String,String> m) throws Exception;
	
	public List<Group> getTree(Map m);
	/**
	 * @param loginName
	 * @param userName
	 * @param cellNO
	 * @param firstResult 开始页数
	 * @param fetchSize	每页数量
	 * @return
	 * @throws Exception 
	 */
	public Group getGroup(String id) throws Exception;

}
