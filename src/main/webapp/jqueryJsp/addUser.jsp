<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	out.println(contextPath);
	String uid = request.getParameter("uid");
	if (uid == null) {
		uid = "";
	}
%>
<html>
<jsp:include page="../import.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
</head>
<style>
* {
  margin:1;
  padding:1;
 }
 table {
  font-family:Verdana, Arial, Helvetica, sans-serif;
  font-size:14px;
  border-collapse:collapse;
 }
 th,td {
  border:2px solid #ccc;
  padding:5px 20px;
 }
</style>
<body>
<script type="text/javascript">
	//重置
	function reset(){
		$('#form input[name!=reqType]').val('');
	}
	//提交
	var submitNow = function($dialog, $grid, $pjq) {
		var url = '<%=contextPath%>'+'/userServlet';
		if ($(':input[name="id"]').val().length > 0) {
			url = url+"?reqType=update"; //更新
		} else {
			url = url+"?reqType=reg";//新增
		}
		$.post(url,sy.serializeObject($('form')),function(result){
			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load'); //grid重新刷新
				$dialog.dialog('destroy');//关闭之前的新增/修改窗口
			}else{
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		},'json');
	};
	
	
$(function() {
	if ($(':input[name="id"]').val().length > 0) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post('<%=contextPath%>' + '/userServlet', {
			id : $(':input[name="id"]').val(),
			reqType:"selectUser"
		}, function(result) {
			if (result.id != undefined) {
				$('form').form('load', {
					'data.id' : result.id,
					'userName' : result.userName,
					'loginName' : result.loginName,
					'password' : result.password,
					'password2' : result.password,
					'sex' : result.sex,
					'cellNO' : result.cellNO
				});
				$("#password2").textbox("disable");
			}
			parent.$.messager.progress('close');
		}, 'json');
	}
});
</script>
	<form  method="post" id="form" class="form">
	<input name="id" value="<%=uid%>" readonly="readonly" type="hidden"/>
	<fieldset>
		<legend>用户信息</legend>
		<table>
			<tr>
				<td>登录名</td>
				<td><input class="easyui-textbox"  name="loginName" data-options="required:true"/></td>
			</tr>
			<tr>
				<td>姓 名</td>
				<td><input class="easyui-textbox"   name="userName" data-options="required:true"/></td>
			</tr>
			<tr>
				<td>密 码</td>
				<td><input class="easyui-textbox" type="password" name="password" data-options="required:true"/></td>
			</tr><tr>	
			    <td>密码确认</td>
				<td><input class="easyui-textbox"  id="password2" type="password" name="password2" data-options="required:true"/></td>
			</tr>
			<tr>
					<td>性 别</td>
					<td><select class="easyui-combobox" name="sex"
						data-options="panelHeight:'auto',editable:false ,required:true"
						style="width: 145px;">
							<option value="1">男</option>
							<option value="0">女</option>
					</select></td>
			</tr><tr>
					<td>电话号码</td>
					<td><input class="easyui-textbox" name="cellNO"/></td>
			</tr>
			</table>		
	</fieldset>
	</form>
</body>
</html>