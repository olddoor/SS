package com.zyl.demo.ss1.servlet;

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

import com.zyl.demo.ss1.Exception.login_Exception;
import com.zyl.demo.ss1.entity.Grid;
import com.zyl.demo.ss1.entity.SessionInfo;
import com.zyl.demo.ss1.entity.User;
import com.zyl.demo.ss1.service.UserService;
import com.zyl.demo.ss1.service.UserServiceImpl;
import com.zyl.demo.util.JsonUtils;
import com.zyl.demo.util.util_copy;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String reqType = request.getParameter("reqType");
		if (reqType != null && !reqType.equals("")) {
			if (reqType.equals("reg")) {
				this.addUser(request, response);
			} else if (reqType.equals("update")) {
				this.updateUser(request, response);
			} else if (reqType.equals("delete")) {
				this.deleteUser(request, response);
			} else if (reqType.equals("select")) {//获取数据
				try {
					this.getUserList(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/userList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 入口
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String reqType = request.getParameter("reqType");
		if (reqType != null && !reqType.equals("")) {
			if (reqType.equals("login")) {
				User u=null;
				try {
					u=this.login(request, response);
				} catch (login_Exception e) {
					//登录失败
					e.printStackTrace();
					request.setAttribute("msg",e.getMessage());
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
				request.getSession().setAttribute("user", u);
				SessionInfo info= new SessionInfo();
				info.setUser(u);
				request.getSession().setAttribute("SessionInfo", info);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
				dispatcher.forward(request, response);
			} else if (reqType.equals("reg")) {
				this.addUser(request, response);
			} else if (reqType.equals("update")) {
				this.updateUser(request, response);
			} else if (reqType.equals("delete")) {
				this.deleteUser(request, response);
			} else if (reqType.equals("select")) {//获取数据
				try {
					this.getUserList(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
		User u=userService.exitsUser(loginName);
		if(u==null){
			throw new login_Exception("loginName dose'nt exits ");
		}
		u=userService.login(loginName, password);
		if(u==null){
			throw new login_Exception("loginName or password may not be right ");
		}
		return u;
	}
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			User u = new User();
			u.setLoginName(request.getParameter("loginName"));
			u.setPassword(request.getParameter("password"));
			u.setUpdateDate(new Date());
			u.setUserName(request.getParameter("userName"));
			userService.save(u);
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("addUser.jsp").forward(request,response);
		}
	}

	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String idstr=request.getParameter("id");
			if(idstr!=null&&!idstr.equals("")){
				User u = userService.getUser(Long.parseLong(idstr));
				u.setLoginName(request.getParameter("loginName"));
				u.setPassword(request.getParameter("password"));
				u.setUserName(request.getParameter("userName"));
				userService.update(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		   try {
			String id = request.getParameter("id");
			Long id1 = Long.valueOf(id);
			User u = userService.getUser(id1);
			if (u != null && !u.equals("")) {
				userService.delete(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", e.getMessage());
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
		String json=this.grid(response, us, total,true);
	}
	/**
	 * 
	 * @param list
	 * @param write是否直接写输出流
	 * @return
	 * @throws IOException 
	 */
	public String grid(HttpServletResponse response,List list,long total,boolean write) throws IOException{
		Grid grid = new Grid();
		grid.setTotal(Long.valueOf(total));
		grid.setRows(list);
		String json=JsonUtils.obj2Json_ByFilter(grid, null, null, null, null);
		if(write){
			grid.write(response, json);
		}
		return json;
	}
	
	
	@SuppressWarnings("unused")
	private void getUser(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		User u=null;
		if(id!=null&&!id.equals("")){
			u=userService.getUser(Long.valueOf(id));
		}
		request.setAttribute("user", u);
	}
}
