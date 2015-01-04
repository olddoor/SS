<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zyl.demo.ss1.service.*"%>
<%@ page import="com.zyl.demo.ss1.entity.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
label{vertical-align:middle}
.inputcheckbox{vertical-align:middle;}
body{font-family:tahoma;font-size:18px;}
</style>
<title>新增用户</title>
</head>
<body>
<% 
	String basePath=request.getContextPath(); 
	String userId=request.getParameter("id");
	User u=new User();
	String reqType="reg";
%>
	<form action="<%=basePath%>/userServlet" method="post" id="form" autocomplete="off">
	<input type="hidden" name="reqType" value=<%=reqType%>>
	<fieldset style="width:300px">
		<legend>表单</legend>
		<ul class="g-formlist">
			<li>
				<label class="mark" for="form-name">登录名</label>
				<div>
					<input type="text"  name="loginName" class="g-text-entry" placeholder="请输入4-10字符" />
					<span class="tip" data-initial="请输入4-10字符"></span>
				</div>
			</li>
			<li>
				<label>姓 名</label>
				<div class="write">
					<input type="text"  name="userName" class="g-text-entry" placeholder="请输入4-10字符" />
					<span class="tip" data-initial="请输入4-10字符"></span>
				</div>
			</li>
			<li>
				<label>密 码</label>
				<div class="write">
					<input type="text"  name="password" placeholder="请输入6-30字符" />
					<span class="tip" data-initial="请输入6-30字符"></span>
				</div>
			</li>
			<%
			if(u==null){
			%> 
			<li>
				<label>确 认 密 码</label>
				<div class="write">
					<input type="text" name="password2" class="g-text-entry" placeholder="请再输入一遍密码" />
					<span class="tip" data-initial="请输入6-30字符"></span>
				</div>
			</li>
			<% }  %>
		</ul>
		<div align="center"><input type="reset"  value="重置" /> &nbsp; &nbsp;  &nbsp;  &nbsp;  <input type="submit" value="提交" /></div>
	</fieldset>
	</form>
</body>
</html>