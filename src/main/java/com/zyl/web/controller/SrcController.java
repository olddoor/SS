package com.zyl.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SrcController extends BaseController {
	@RequestMapping(value="/src/{url}")
	public String src(@PathVariable(value="url") String url,
			HttpServletRequest request,HttpServletResponse response){
		if(url!=null&&!url.equals("")){
			return url;
		}else{
			return "jqueryJsp/404";
		}
	}
	
	@RequestMapping(value="/src/{module}/{url}") 
	public String srcJquery(@PathVariable(value="module") String module,@PathVariable(value="url") String url,
							HttpServletRequest request,HttpServletResponse response,Model model){
		StringBuffer jump=new StringBuffer();
		if(request.getParameter("id")!=null){
			model.addAttribute("id", request.getParameter("id"));
		}
		if(module!=null&&!module.equals("")){
			jump.append(module);
		}
		if(url!=null&&!url.equals("")){
			jump.append("/");
			jump.append(url);
		}else{
			return "jqueryJsp/404";
		}
		return jump.toString();
	}
	
}
