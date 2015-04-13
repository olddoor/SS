<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zyl.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <style>
    #all{
    margin:100px auto;
    width:500px; /* 必须制定宽度 */
    height:100px;
}
#string{
    margin:0px auto;
    width:300px; /* 必须制定宽度 */
    height:100px;
    background-color:red;
    text-align:center; /* 文字居中 */
    font-size:32px; /* 文字大小 */
    color:white; /* 文字颜色 */
}
#image{
    margin:0px auto;
    width:300px; /* 必须制定宽度 */
    background-color:white;
    text-align:center; /* 图像居中 */
    padding-top:20px; /* 图像上填充 */
    padding-bottom:20px; /* 图像下填充 */
}
    </style>
</head>
<body>
<%
//若已登录,则进入管理页面 main.jsp. 否则提示
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("user");
	if (sessionInfo != null) {
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
%>
<%		String basePath=request.getContextPath();  
		String msg=(String)request.getAttribute("msg");
		if(msg!=null&&!msg.equals("")){
			out.print("<a  style=' font-size:25px;color:blue'>"+msg+"</a><br/>");
		}
%>
		<div id="all">
		<a  style=" font-size:25px;color:blue">欢迎使用XX系统</a><br/><br/>
		<form action="<%=basePath%>/userServlet" method="post">
		<input type="hidden" name="reqType" value="login">
		<fieldset style="width:300px">
		<legend>登录</legend>
		<ul class="g-formlist">
			<li>
				<label class="mark" for="form-name">登录名</label>
				<div>
					<input type="text"  name="loginName" class="g-text-entry" placeholder="请输入4-10字符" />
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
					
		</ul>
		</fieldset>
		<div align="center"><input type="submit" value="登陆"/></div>
		<a href="<%=basePath%>/addUser.jsp">新增用户</a>
		<a href="<%=basePath%>/userServlet?reqType=select">用户列表</a>
		</form>
		</div>
</body>
</html>