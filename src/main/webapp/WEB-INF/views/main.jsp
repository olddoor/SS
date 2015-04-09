<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zyl.entity.*"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="<%=contextPath%>/static/import.jsp"></jsp:include>
<% String basePath=request.getContextPath(); %>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
%>
<head>
<script type="text/javascript">
		var mainMenu ;//菜单栏中的ul
		var mainTabs ;//tab
		
		$(function() { //// 相当于$(document).ready(function(){ } )  dom加载完毕后开始执行
			//添加菜单树
			mainMenu = $('#mainMenu').tree({
				url : myJSContext.contextPath + '/jsonData/treeData.json',
				onClick: function(node){
					if (node.attributes.url){ //如果节点url不为空
						var src = myJSContext.contextPath + node.attributes.url;
						var tabs = $('#mainTabs');
						var opts = {
							title : node.text,
							closable : true,
							iconCls : node.iconCls,
							border : false,
							fit : true,
							content : sy.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src)
							//这里面板内容加载除了content以外也可以使用href(但只加载body里面的内容)
						};
						//如果同名title的tab已存在,则激活显示即可.
						if (tabs.tabs('exists', opts.title)) {
							tabs.tabs('select', opts.title);
						} 
						//如没有同名title的tab存在,则创建一个新的tab.
						else {
							tabs.tabs('add', opts);
						}
					}
				}
			});
			
			//添加tab
			mainTabs = $('#mainTabs').tabs({
				 fit : true,  //适应父容器的大小
				 border : false, //显示边框
				 tools:[{
						text : '刷新',
						iconCls : 'icon-refresh'
						},{
						text : '关闭',
						iconCls : 'icon-cancel'
				}]
			})
			});
</script>	
</head>
<body id="mainLayout" class="easyui-layout">
    <div data-options="region:'north',href:'top.jsp'" style="height:100px; overflow: hidden;" ></div>
    <div data-options="region:'south' , title:'South Title',split:true">
    </div>
    <div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>
    <div data-options="region:'west',split:true" title="菜单栏" style="width:200px;" >
    	<ul id="mainMenu"></ul>
    </div>
    <div data-options="region:'center'" style="overflow: hidden;">
      <div id="mainTabs">
        <div title="欢迎" data-options="iconCls:'ext-icon-heart'" id="data_Div">
          <iframe src="<%=contextPath%>/welcome.jsp"
             allowTransparency="true" style="border: 0; width: 100%; height: 99%;" frameBorder="0">
          </iframe>
        </div>
      </div>
    </div>
    
</body>
</html>