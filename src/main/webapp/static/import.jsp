<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 本jsp用于引入常用通用的相关文件:js,插件,css等 -->

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<%String version = "20131115";%>
<%
//处理cookie, 根据已选的显示对应的样式.
Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "bootstrap";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>
<script type="text/javascript">
	var myJSContext = myJSContext || {};  //a == undefined 或 a == null时候，取b的值，否则取a的值
	myJSContext.contextPath = '<%=contextPath%>';
	myJSContext.basePath = '<%=basePath%>';
	myJSContext.version = '<%=version%>';
	myJSContext.pixel_0 = '<%=contextPath%>/static/jslib/style/images/pixel_0.gif';//0像素的背景，一般用于占位. 解决img不设置src而用css设置背景图片时,ie加载后出现图标的问题.
	
	
	/**
	 * 新增panel. 因为springmvc默认不拦截js等静态资源.所以相关请求无法使用springmvc拦截.
	 * 这里以Servlet拦截跳转作为替代.
	 */
	function addPanel(region,targetDiv_Id,src){
	   var options = {
	        region: region
	    };
	    if (region=='north' || region=='south'){
	        options.height = 50;
	    } else {
	        options.width = 100;
	        options.split = true;
	        options.title = $('#region option:selected').text();
	    }
	    if(src){
	        //options.href=src;
	    	options.href='/urlServlet';
	    }
	    $("#"+	targetDiv_Id).layout('add', options);
	   
	}
	function removePanel(region,targetDiv_Id){
		 $("#"+	targetDiv_Id).layout('remove', region);
	}
</script>

<%
	//String easyuiTheme = "default";//指定如果用户未选择样式，那么初始化一个默认样式
%>
<%-- 引入my97日期时间控件 --%>
<script type="text/javascript" src="<%=contextPath%>/static/jslib/jsPlugin/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<%-- 引入jQuery --%>
<%
String User_Agent = request.getHeader("User-Agent");
out.print("============="+User_Agent);
if (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE") > -1 && (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1 || StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 7") > -1 || StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 8") > -1)) {
	out.print("=====1======");
	out.println("<script src='" + contextPath + "/static/jslib/script/jquery-1.9.1.js' type='text/javascript' charset='utf-8'></script>");
} else {
	out.print("=====2======");
	out.println("<script src='" + contextPath + "/static/jslib/script/jquery-2.0.3.js' type='text/javascript' charset='utf-8'></script>");
}
%>
<%-- 引入jquery扩展 --%>
<script type="text/javascript" src="<%=contextPath%>/static/jslib/jsPlugin/util/syExtJquery.js" charset="utf-8"></script>


<%-- 引入EasyUI --%>
<link id="easyuiTheme" rel="stylesheet" href="<%=contextPath%>/static/jslib/easyui/themes/bootstrap/easyui.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/static/jslib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/jslib/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<%-- 引入easyui扩展 --%>
<script src="<%=contextPath%>/static/jslib/jsPlugin/util/syExtEasyUI.js" type="text/javascript" charset="utf-8"></script>
<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="<%=contextPath%>/static/jslib/style/syExtIcon.css" type="text/css">
<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="<%=contextPath%>/static/jslib/style/syExtCss.css" type="text/css">

<%-- 引入javascript扩展 --%>
<script src="<%=contextPath%>/static/jslib/jsPlugin/util/syExtJavascript.js" type="text/javascript" charset="utf-8"></script>
