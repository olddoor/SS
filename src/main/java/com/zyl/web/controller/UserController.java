package com.zyl.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.Exception.commonException;
import com.zyl.Exception.login_Exception;
import com.zyl.entity.Grid;
import com.zyl.entity.User;
import com.zyl.entity.msgStr;
import com.zyl.service.UserService;
import com.zyl.service.UserServiceImpl;
import com.zyl.util.JsonUtils;
import com.zyl.util.util_copy;

@Controller
@RequestMapping("user")
@SessionAttributes (value={"userSession"})   //名字为userSession的变量放入session
public class UserController extends BaseController {
	private UserService userService = new UserServiceImpl();
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response){
		return "index";//跳转到登陆页面index.jsp
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(User u,HttpServletRequest request, HttpServletResponse response ){
		ModelAndView mav = new ModelAndView();  
		String loginName=u.getLoginName();
		String password=u.getPassword();
		msgStr msg=new msgStr();
		if(loginName==null||loginName.equals("")||password==null||password.equals("")){
			msg.setMsg("用户名或者密码不可为空");
		}
		/*以下定义的变量,跟类上的@SessionAttributes 定义的需要存到session 中的属性名称相同或类型相同,
		所以在请求完成后这个属性将添加到session 属性中。*/
		User userSession=userService.login(loginName, password);
		if(userSession==null){
			msg.setMsg("用户名或者密码有误");
			//跳转回登录页面
			mav.addObject("msg", msg);
		}else{
			//跳转成功页面
			mav.setViewName("index");
			msg.setSuccess(true);
		}
		return mav;
	}
	
	/**
	 * 用户列表
	 * response.getWriter().write 输出json
	 */
	@RequestMapping(value="getUserList",method=RequestMethod.POST)
	public void getUserList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,String> m = new HashMap();
		Long total=0L;
		List<User> us=new ArrayList<User>();
		m=util_copy.httpReq_2_map(request);//request parameters to map
		try {
			total=userService.count(m);//记录数
			us=userService.getUsers(m);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//封装输出对象
		Grid grid = new Grid();
		grid.setTotal(Long.valueOf(total));
		grid.setRows(us);
		//使用封装好的json工具(fastjson)输出
		String str=JsonUtils.obj2Str_ByFilter(grid, null, null, null, null);//含时间格式化
		JsonUtils.write(response, str);
	}
	
	/**
	 * 查询单个用户信息
	 */
	@RequestMapping(value="getUser",method=RequestMethod.POST)
	public void getUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		User u=null;
		if(id!=null&&!id.equals("")){
			u=userService.getUser(id);
		}
		String str=JsonUtils.toJSONString(u);
		JsonUtils.write(response, str);
	}
	
	/**
	 * 增加
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response,User u) throws IOException{
			msgStr msg=new msgStr();
		try {
			User user=userService.exitsUser(u.getLoginName());
			if(user==null){
				userService.save(u);
				msg.setSuccess(true);
			}else{
				//用户已存在
				msg.setMsg("loginname已存在");
				throw new commonException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsgDetail(e.getMessage());
		}
			this.writeJson(msg, response);
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		msgStr msg=new msgStr();
		try {
			String id = request.getParameter("id");
			User u = userService.getUser(id);
			if (u != null && !u.equals("")) {
				userService.delete(u);
			}
			msg.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsgDetail(e.getMessage());
			msg.setSuccess(false);
		}
		this.writeJson(msg, response);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public void update(HttpServletRequest request,HttpServletResponse response,User u) throws IOException{
		msgStr msg=new msgStr();
		try {
			try {
				User oldUser = userService.getUser(u.getId());
				//bean copy :if not null then copy 
				util_copy.copyProperties(oldUser, u);
				userService.save(oldUser);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsgDetail(e.getMessage());
		}
		this.writeJson(msg, response);
	}
}
