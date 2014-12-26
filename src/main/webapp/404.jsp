<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<%
     Random a=new Random(); 
	 int num=a.nextInt(100);
	 String url="images/404-1.jpg";
	 if(num%2==0){
	 	url="images/404-2.jpg";
	 }
     %>
<body style="background-image:url(<%=url %>);background-repeat:no-repeat;fixed 410px 100px;background-position: top 30px center;">
</body>
</html>