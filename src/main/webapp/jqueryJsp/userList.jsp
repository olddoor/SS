<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<jsp:include page="../import.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理</title>
<script type="text/javascript">

	var grid; //数据表格
	//定义datagrid 数据表格
	$(function() { //待dom都加载完毕后,进行easyui相关初始化
		//初始化datagrid
		grid = $('#grid').datagrid({
			//alert("tttt");
			url : myJSContext.contextPath + '/userServlet',
			queryParams:{
				reqType:'select'
			},
			rownumbers : true,//显示行数
			singleSelect : true,//单选
			pagination : true,//设置true将在数据表格底部显示分页工具栏。
			pageSize : 3,
			pageList : [ 10, 20, 50],
			columns : [ [ {
				field : 'id',
				title : 'ID',
				width : 100,
				sortable :true
			}, {
				field : 'loginName',
				title : '登录名',
				width : 100,
				sortable :true
			}, {
				field : 'userName',
				title : '用户名',
				width : 100,
				sortable :true
			}, {
				field : 'password',
				title : '密码',
				width : 100,
				align : 'left'
			},  {
				field : 'cellNO',
				title : '电话号码',
				width : 100,
				align : 'left',
				sortable :true
			}, 
			{
				width : '50',
				title : '性别',
				field : 'sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case '0':
						return '女';
					case '1':
						return '男';
					}
				}
			},{
				field : 'enabled',
				title : '状态',
				width : 100,
				align : 'left',
				formatter : function(value, row, index) {
					if(value){
						return '禁用';
					}else{
						return '正常';
					}
				}
			},{
				field : 'updateDate',
				title : '更新时间',
				width : 150,
				align : 'left',
				sortable :true
			} ] ],
			toolbar : '#toolbar'
		});
	$("#d12").css({'height':'10px','border-spacing':'0px 10px'});
	});
	

	/**
			相关操作按钮
	**/
		var addFun = function() {
			//在当前窗口的父窗口打开一个窗口
		var dialog = parent.sy.modalDialog({
			title : '添加用户信息',
			url : myJSContext.contextPath + '/jqueryJsp/addUser.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					//即调用SyuserForm.jsp的submitForm方法
					dialog.find('iframe').get(0).contentWindow.submitNow(dialog, grid, parent.$);
				}
			},{
				text : '重置',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.reset();
				}
			} ]
		},'400','450');
		
	};
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	here is jqueryJSP/userlist.jsp
	<!-- 工具栏 -->
	<div id="toolbar" style="display: none;">
		<table>
			<tr id="d12">
				<td>
					<form id="searchForm" style="margin:0px;display: inline">
						<table>
							<tr>
								<td>登录名</td>
								<td><input name="loginName" style="width: 60px;"></td>
								<td>用户名</td>
								<td><input name="userName" style="width: 60px;"  /></td>
								<td>电话号码</td>
								<td><input name="cellNO" style="width: 85px;"  /></td>
								<td>性别</td>
								<td><select name="sex" class="easyui-combobox" style="width: 70px;"
									data-options="panelHeight:'auto',editable:false"><option
											value="">请选择</option>
										<option value="1">男</option>
										<option value="0">女</option></td>
								<td>创建时间</td>
								<input name="reqType" value="select" id="reqType" type="hidden"/>
								<td><input name="updateDate_Begin" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
									readonly="readonly" style="width: 110px;" />-<input
									name="updateDate_End" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
									readonly="readonly" style="width: 110px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom',plain:true"
									onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom_out',plain:true"
									onclick="$('#searchForm input[name!=reqType]').val(''); grid.datagrid('load',sy.serializeObject($('#searchForm')));">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td id="d12">
					<table>
						<tr>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
