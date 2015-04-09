<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="com.zyl.demo.ss1.service.*"%>
<%@ page import="com.zyl.demo.ss1.entity.*"%>

<%
String basePath=request.getContextPath(); 
String id =(request.getParameter("id"));
UserService userService=new UserServiceImpl();
User u=userService.getUser(id);
userService.delete(u);
%>

<html>
	<head>
		<title>删除用户</title>
	</head>
	<body>
		<center>
			恭喜您, 删除成功!
			<br>
			<span id="delay" style="backupground:red">10</span>秒钟后跳转到上一页面, 或者请点击下面的链接 自己跳转
			<br>
			<a href="userList.jsp">back</a>
		</center>
		<script type="text/javascript">	
			var delay = 10;
			function goBack() {
				document.getElementById("delay").innerHTML=delay;
				delay --;
				if(delay == 0) {}
				else {setTimeout(goBack, 1000);
				}
			  }
			goBack();
			window.location="<%=basePath%>/userList.jsp";
		</script>
	</body>
</html>