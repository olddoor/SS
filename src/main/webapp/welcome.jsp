<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<html>
<jsp:include page="import.jsp"></jsp:include>
<head>
<script type="text/javascript" src="<%=contextPath%>/jsPlugin/coolclock/excanvas.js"></script>
<script type="text/javascript" src="<%=contextPath%>/jsPlugin/coolclock/coolclock.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/jsPlugin/coolclock/moreskins.js"></script>
</head>
<body>
	这是欢迎页面.我加了一个时钟
	<canvas class="CoolClock:Tes2"></canvas></br>
	搞定
</body>
</html>
