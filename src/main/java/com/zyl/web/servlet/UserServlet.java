package com.zyl.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyl.Exception.login_Exception;
import com.zyl.entity.Grid;
import com.zyl.entity.User;
import com.zyl.entity.msgStr;
import com.zyl.service.UserService;
import com.zyl.service.UserServiceImpl;
import com.zyl.util.JsonUtils;
import com.zyl.util.util_copy;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Servlet请求方法总入口
	 */
	@Override 
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		String reqType = request.getParameter("reqType");
		msgStr msg=new msgStr();
		try {
			if (reqType != null && !reqType.equals("")) {
				if(reqType.equals("login")) {
					User u=this.login(request, response);
					HttpSession session =request.getSession();
					session.setAttribute("user", u);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
					dispatcher.forward(request, response);
				}
				if (reqType.equals("reg")) {
					this.addUser(request, response);
				} else if (reqType.equals("update")) {
					this.updateUser(request, response);
				} else if (reqType.equals("delete")) {
					this.deleteUser(request, response);
				//获取数据
				} else if (reqType.equals("selectAll")) { 
					this.getUserList(request, response);
				} else if (reqType.equals("selectUser")){
					this.getUser(request, response);
				}
			}
			msg.setSuccess(true);
			msg.setMsg("操作成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg(e.getMessage());
		}
		String str=JsonUtils.toJSONString(msg);
		JsonUtils.write(response, str);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * 入口
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

	private User login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException,login_Exception{
		String loginName=request.getParameter("loginName");
		String password=request.getParameter("password");
		if(loginName==null||loginName.equals("")){
			throw new login_Exception("login name can not be null! ");
		}
		if(password==null||password.equals("")){
			throw new login_Exception("password can not be null! ");
		}
		User u=userService.login(loginName, password);
		if(u==null){
			throw new login_Exception("loginName or password may not be right ");
		}
		return u;
	}
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			User u = new User();
			String loginName=request.getParameter("loginName");
			User u1=userService.exitsUser(loginName);
			if(u1!=null){
				throw new login_Exception("loginName dose'nt exits ");
			}
			u.setLoginName(loginName);
			u.setPassword(request.getParameter("password"));
			u.setUpdateDate(new Date());
			u.setUserName(request.getParameter("userName"));
			u.setCellNO(request.getParameter("cellNO"));
			u.setSex(request.getParameter("sex"));
			userService.save(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id=request.getParameter("id");
			if(id!=null&&!id.equals("")){
				User u = userService.getUser(id);
				u.setLoginName(request.getParameter("loginName"));
				u.setPassword(request.getParameter("password"));
				u.setUserName(request.getParameter("userName"));
				userService.update(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			User u = userService.getUser(id);
			if (u != null && !u.equals("")) {
				userService.delete(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有用户
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@SuppressWarnings("null")
	private void getUserList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,String> m = new HashMap();
		m=util_copy.httpReq_2_map(request);
		Long total=userService.count(m);//记录数
		List<User> us=new ArrayList();
		try {
			us = userService.getUsers(m);
		} catch (Exception e) {
			e.printStackTrace();
		} //获取记录
		Grid grid = new Grid();
		grid.setTotal(Long.valueOf(total));
		grid.setRows(us);
		
		String str=JsonUtils.obj2Str_ByFilter(grid, null, null, null, null);//含时间格式化
		JsonUtils.write(response, str);
	}
	
	
	@SuppressWarnings("unused")
	private void getUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		User u=null;
		if(id!=null&&!id.equals("")){
			u=userService.getUser(id);
		}
		String str=JsonUtils.toJSONString(u);
		JsonUtils.write(response, str);
	}
}
