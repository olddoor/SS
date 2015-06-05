package com.zyl.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.zyl.entity.Group;
import com.zyl.entity.User;
import com.zyl.entity.msgStr;
import com.zyl.service.GroupService;
import com.zyl.util.JsonMapper;
import com.zyl.util.JsonUtils;
import com.zyl.util.util_copy;

@Controller
@RequestMapping("group")
public class GroupController extends BaseController {
	
	private GroupService service;

	public GroupService getService() {
		return service;
	}
	@Resource(name="groupService")
	public void setService(GroupService service) {
		this.service = service;
	}
	
	@RequestMapping(value="selectGroup",method=RequestMethod.POST)
	public void selectGroup(HttpServletRequest request,HttpServletResponse response,User u) throws IOException{
		String id=request.getParameter("id");
		Group g=null;
		if(id!=null&&!id.equals("")){
			try {
				g=service.getGroup(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		this.writeJson(g, null, null, response);
	}
	
	@RequestMapping(value="selectAll",method=RequestMethod.POST)
	public void selectAll(HttpServletRequest request,HttpServletResponse response,User u) throws IOException{
		List<Group> gs=null;
		try {
			gs = service.getGroups(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.writeJson(gs,null,null, response); //利用封装的fastjson
	}
	
	@RequestMapping(value="selectTree",method=RequestMethod.POST)
	public void selectTree(HttpServletRequest request,HttpServletResponse response,User u) throws IOException{
		List<Group> gs=null;
		try {
			gs = service.getTree(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String[] excludesProperties = new String[] {"fatherGroup"}; 
//		for(Group g:gs)
//			if(g.getChildren()!=null){
//				Set<Group> s=g.getChildren();
//				for (Group obj: s) {  
//					System.out.println(obj.getName());
//				}  
//			}
		for(Group g:gs){
			System.out.println(g.getName());
			System.out.println("son:"+g.getChildren().size());
			for(Group son:g.getChildren()){
				System.out.println("son_"+son.getName());
			}
		}
		this.writeJson(gs, null, null, response); //利用封装的fastjson
	}
	
	@RequestMapping(value="selectTree",method=RequestMethod.POST)
	public void selectTree(HttpServletRequest request,HttpServletResponse response,User u) throws IOException{
		List<Group> gs=null;
		try {
			gs = service.getTree(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.writeJson(gs, response); //利用封装的fastjson
	}
	
	@RequestMapping(value="saveORupdate",method=RequestMethod.POST)
	public void saveORupdate(HttpServletRequest request,HttpServletResponse response,Group g) throws IOException{
		msgStr msg=new msgStr();
		try {
		if(g.getId()==null){
				g.setUpdateDate(new Date());
				service.save(g);
		}else{
				Group old=service.getGroup(g.getId());
				//bean copy :if not null then copy 
				BeanUtilsBean copy= new util_copy();
				copy.copyProperties(old, g);
				old.setUpdateDate(new Date());
				service.save(old);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
		}
		msg.setSuccess(true);
		msg.setMsg("操作成功");
		this.writeJson(msg,null,null, response);
	}
	
}
