package com.zyl.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zyl.dao.GroupDaoImpl;
import com.zyl.entity.Group;
@Component(value="groupService")
public class GroupServiceImpl implements GroupService {
	private GroupDaoImpl groupDao;

	public GroupDaoImpl getGroupDao() {
		return groupDao;
	}
	@Resource(name="groupDao")
	public void setGroupDao(GroupDaoImpl groupDao) {
		this.groupDao = groupDao;
	}

	
	@Override
	public void save(Group g) throws Exception {
		groupDao.save(g);
	}

	@Override
	public void update(Group g) throws Exception {
		groupDao.update(g);
	}

	@Override
	public void delete(Group g) throws Exception {
		groupDao.delete(g);

	}

	@Override
	public List<Group> getGroups(Map<String, String> m) throws Exception {
		return groupDao.getGroups(m);
	}

	@Override
	public Group getGroup(String id) throws Exception {
		return groupDao.getGroup(id);
	}


}
