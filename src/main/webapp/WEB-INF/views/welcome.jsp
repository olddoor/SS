<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<html>
<head>
<script type="text/javascript" src="<%=contextPath%>/static/jslib/jsPlugin/coolclock/excanvas.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/jslib/jsPlugin/coolclock/coolclock.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/jslib/jsPlugin/coolclock/moreskins.js"></script>
</head>/jslib/
<body onload="CoolClock.findAndCreateClocks()">
	这是欢迎页面.我加了一个时钟</br>
	<canvas id="clk1" class="CoolClock:classic"></canvas>
	搞定
</body>
</html>
