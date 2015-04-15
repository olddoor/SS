package com.zyl.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import com.zyl.entity.User;
/**
 * 用户登录效验
 * @author Z10
 */
public class SessionFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest hrequest = (HttpServletRequest)request;
		 HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
		  //判断是否是登陆页面  
         String servletPath = hrequest.getServletPath();  
         HttpSession session=hrequest.getSession();
         User u=(User) session.getAttribute("userSession");
		 //用户未登录则跳转到登陆
		 if(u==null&&!servletPath.contains("index.jsp")&&!servletPath.contains("login")){
			 hrequest.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request,response);
			 return;
		 }
		 chain.doFilter(request, response);
         return;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
