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
		<title>ɾ���û�</title>
	</head>
	<body>
		<center>
			��ϲ��, ɾ���ɹ�!
			<br>
			<span id="delay" style="backupground:red">10</span>���Ӻ���ת����һҳ��, ����������������� �Լ���ת
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