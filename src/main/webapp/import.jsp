<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 本jsp用于引入常用通用的相关文件:js,插件,css等 -->

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<script type="text/javascript">
	var myJSContext = myJSContext || {};  //a == undefined 或 a == null时候，取b的值，否则取a的值
	myJSContext.contextPath = '<%=contextPath%>';
	myJSContext.basePath = '<%=basePath%>';
</script>
<%
	//String easyuiTheme = "default";//指定如果用户未选择样式，那么初始化一个默认样式
%>
<%-- 引入jQuery --%>
<script type="text/javascript" src="<%=contextPath%>/jslib/script/jquery-1.9.1.js"></script>
<%-- 引入EasyUI --%>
<link id="easyuiTheme" rel="stylesheet" href="<%=contextPath%>/jslib/easyui/themes/default/easyui.css" type="text/css">
<link id="easyuiTheme" rel="stylesheet" href="<%=contextPath%>/jslib/easyui/themes/icon.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/jslib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<!-- 自定义工具js -->
<script type="text/javascript" src="<%=contextPath%>/jslib/jsPlugin/util/syExtJquery.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/jsPlugin/util/syExtEasyUI.js" charset="utf-8"></script>
<%-- 引入my97日期时间控件 --%>
<script type="text/javascript" src="<%=contextPath%>/jslib/jsPlugin/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<%-- 引入easyui扩展 --%>
<script src="<%=contextPath%>/jslib/syExtEasyUI.js" type="text/javascript" charset="utf-8"></script>



<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="<%=contextPath%>/jslib/style/syExtIcon.css" type="text/css">
<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="<%=contextPath%>/jslib/style/syExtCss.css" type="text/css">