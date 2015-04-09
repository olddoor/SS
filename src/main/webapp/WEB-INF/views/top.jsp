<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zyl.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
<!--   <body leftMargin=0 rightMargin=0 topMargin=0 >
    <div id="outer1">
      <div id="inner0" class="div-inline">
        <div id="inner1" class="div-inline">
          <a>
            XX系统欢迎您:
          </a>
          <a>
            ${sessionScope.user.getUserName()}
          </a>
        </div>
        <div id="inner4"class="div-inline">
          <a href="mailto:test@139.com">
            <img src="images/c3.gif"  border=0  alt="求助中心">
          </a>
          <img src="images/c2.gif"  alt="系统简介">
          <img src="images/c1.gif"  alt="操作手册">
        </div>
      </div>
    </body> -->
    <div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 5px;">
	<%
		/*if (sessionInfo != null) {
			out.print(sy.util.base.StringUtil.formateString("欢迎您，{0}", sessionInfo.getUser().getLoginname()));
		}
		*/
	%>
</div>
<div style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'ext-icon-rainbow'">更换皮肤</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'ext-icon-cog'">控制面板</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'ext-icon-disconnect'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="sy.changeTheme('default');" title="default">default</div>
	<div onclick="sy.changeTheme('gray');" title="gray">gray</div>
	<div onclick="sy.changeTheme('metro');" title="metro">metro</div>
	<div onclick="sy.changeTheme('bootstrap');" title="bootstrap">bootstrap</div>
	<div onclick="sy.changeTheme('black');" title="black">black</div>
	<div class="menu-sep"></div>
	<div onclick="sy.changeTheme('metro-blue');" title="metro-blue">metro-blue</div>
	<div onclick="sy.changeTheme('metro-gray');" title="metro-gray">metro-gray</div>
	<div onclick="sy.changeTheme('metro-green');" title="metro-green">metro-green</div>
	<div onclick="sy.changeTheme('metro-orange');" title="metro-orange">metro-orange</div>
	<div onclick="sy.changeTheme('metro-red');" title="metro-red">metro-red</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'" onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">我的信息</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="lockWindowFun();">锁定窗口</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoutFun();">退出系统</div>
</div>
  </html>
