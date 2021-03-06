package com.zyl.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.Exception.commonException;
import com.zyl.Exception.login_Exception;
import com.zyl.entity.Grid;
import com.zyl.entity.User;
import com.zyl.entity.msgStr;
import com.zyl.service.UserService;
import com.zyl.service.UserServiceImpl;
import com.zyl.util.JsonMapper;
import com.zyl.util.JsonUtils;
import com.zyl.util.util_copy;

@Controller
@RequestMapping("user")
@SessionAttributes (value={"userSession"})   //名字为userSession的变量放入session
public class UserController extends BaseController {
//	private UserService userService = new UserServiceImpl();
	
	private UserService userService ;
	
	public UserService getUserService() {
		return userService;
	}
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response){
		return "index";//跳转到登陆页面index.jsp
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(User u,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();  
		String loginName=u.getLoginName();
		String password=u.getPassword();
		msgStr msg=new msgStr();
		if(loginName==null||loginName.equals("")||password==null||password.equals("")){
			msg.setMsg("用户名或者密码不可为空");
		}
		/*以下定义的变量,跟类上的@SessionAttributes 定义的需要存到session 中的属性名称相同或类型相同,
		所以在请求完成后这个属性将添加到session 属性中。*/
		User user=userService.login(loginName, password);
		if(user==null){
			msg.setMsg("用户名或者密码有误");
			//跳转回登录页面
			mav.addObject("msg", msg);
		}else{
			//跳转成功页面
			mav.setViewName("main");
			mav.addObject("userSession", user);
			msg.setSuccess(true);
		}
		return mav;
	}
	
	/**
	 * 用户列表
	 * response.getWriter().write 输出json
	 */
	@RequestMapping(value="getUserList",method=RequestMethod.POST)
	@ResponseBody  //处理器功能处理方法的返回值作为响应体（通过HttpMessageConverter进行类型转换）
	public String getUserList(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
//		使用fastjson组件将obj->jsonstr
//		String str=JsonUtils.obj2Str_ByFilter(grid, null, null, null, null);
		//使用jackson组件将obj->jsonstr
		return JsonMapper.nonDefaultMapper().toJson(grid);
	}
	
	
	/**
	 * 用户列表
	 * 使用springmvc中的配置jackson工具将内容输出到@ResponseBody
	 */
	@RequestMapping(value="getUserList2",method=RequestMethod.POST)
	@ResponseBody  //处理器功能处理方法的返回值作为响应体（通过HttpMessageConverter进行类型转换）
	public Map getUserList2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,String> m = new HashMap();
		Map<String,Object> m2 = new HashMap();
		Long total=0L;
		List<User> us=new ArrayList<User>();
		m=util_copy.httpReq_2_map(request);//request parameters to map
		try {
			total=userService.count(m);//记录数
			us=userService.getUsers(m);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		m2.put("total", total.toString());
		m2.put("rows", us);
		return m2;
	}
	
	/**
	 * 用户列表
	 * 使用默认自带的jason工具将内容输出到@ResponseBody
	 */
	@RequestMapping(value="getUserList3",method=RequestMethod.POST)
	@ResponseBody  //处理器功能处理方法的返回值作为响应体（通过HttpMessageConverter进行类型转换）
	public ModelAndView getUserList3(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mav = new ModelAndView();  
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
		mav.addObject("data", grid);
		
		return mav;
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
	 * 新增/修改
	 * @param request
	 * @param response
	 * @param u
	 * @throws IOException 
	 */
	@RequestMapping(value="saveORupdate",method=RequestMethod.POST)
	public void saveORupdate(HttpServletRequest request,HttpServletResponse response,User u) throws IOException{
		msgStr msg=new msgStr();
		String id=u.getId();
		try {
			if (id!=null) {//update
				User oldUser = userService.getUser(id);
				//bean copy :if not null then copy 
				BeanUtilsBean copy= new util_copy();
				copy.copyProperties(oldUser, u);
				oldUser.setUpdateDate(new Date());
				userService.save(oldUser);
			} else {//save
				User user = userService.exitsUser(u.getLoginName());
				if (user!=null) {
					//用户已存在
					msg.setMsg("loginname已存在");
					throw new commonException();
				} else {
					u.setUpdateDate(new Date());
					userService.save(u);
				}
			}
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsgDetail(e.getMessage());
			msg.setSuccess(false);
		}
		this.writeJson(msg, response);
	}
	
	@RequestMapping(value="createVcard",method=RequestMethod.POST)
	public void createVcard(HttpServletRequest request,HttpServletResponse response) throws IOException{
		msgStr msg=new msgStr();
		userService.createVcard();
		this.writeJson(msg, response);
	}
	
}
