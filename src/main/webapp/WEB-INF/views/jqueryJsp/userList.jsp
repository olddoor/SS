<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<jsp:include page="../../../static/import.jsp"></jsp:include>
<head>
<title>用户管理</title>
<script type="text/javascript">
	/**
	相关操作按钮
	 **/
	//新建
	var addFun = function() {
		//在当前窗口的父窗口打开一个窗口
		var dialog = parent.sy.modalDialog({
			title : '添加用户信息',
			url : myJSContext.contextPath + '/src/jqueryJsp/addUser',//addUser.jsp /jqueryJsp/SyuserForm.jsp
			buttons : [
					{
						text : '添加',
						handler : function() {
							//即调用SyuserForm.jsp的submitForm方法
							dialog.find('iframe').get(0).contentWindow
									.submitNow(dialog, grid, parent.$);
						}
					}, {
						text : '重置',
						handler : function() {
							dialog.find('iframe').get(0).contentWindow.reset();
						}
					} ]
		}, '400', '450');//,
	};
	//查看
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看用户信息',
			url : myJSContext.contextPath + '/src/jqueryJsp/addUser?id=' + id
		}, '400', '300');
	};
	//修改
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改用户信息',
			url : myJSContext.contextPath + '/src/jqueryJsp/addUser?id=' + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitNow(
							dialog, grid, parent.$);
				}
			}
			]
		}, '400', '300');
	}
	//删除
	var removeFun=function (id){
		//窗口的父对象调用$.messager.confirm打开提示窗口
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(myJSContext.contextPath + '/user/delete', {
					id : id
					//reqType :'delete'
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
//用户组织绑定
	var grantOrganizationFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改机构',
			url : sy.contextPath + '/securityJsp/base/SyuserOrganizationGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
/******************************************************************/	
	var grid; //数据表格

	//定义datagrid 数据表格
	$(function() { //待dom都加载完毕后,进行easyui相关初始化
		//初始化datagrid
		grid = $('#grid')
				.datagrid(
						{
							url : myJSContext.contextPath + '/user/getUserList2',
							rownumbers : true,//显示行数
							pagePosition: 'bottom',
							singleSelect : true,//单选
							pagination : true,//设置true将在数据表格底部显示分页工具栏。
							pageSize : 5,
							pageList : [5, 10, 15],
							columns : [ [
									{
										field : 'id',
										title : 'ID',
										width : 100,
										sortable : true
									},
									{
										field : 'loginName',
										title : '登录名',
										width : 100,
										sortable : true
									},
									{
										field : 'userName',
										title : '用户名',
										width : 100,
										sortable : true
									},
									{
										field : 'password',
										title : '密码',
										width : 100,
										align : 'left'
									},
									{
										field : 'cellNO',
										title : '电话号码',
										width : 100,
										align : 'left',
										sortable : true
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
									},
									{
										field : 'enabled',
										title : '状态',
										width : 100,
										align : 'left',
										formatter : function(value, row, index) {
											if (value) {
												return '禁用';
											} else {
												return '正常';
											}
										}
									},
									{
										field : 'updateDate',
										title : '更新时间',
										width : 150,
										align : 'left',
										sortable : true
									},
									{
										title : '操作',
										field : 'action',
										width : '100',
										formatter : function(value, row) {
											var str = '';
											str += sy
													.formatString(
															'<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>',
															row.id);
											str += sy
													.formatString(
															'<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>',
															row.id);
											str += sy
													.formatString(
															'<img class="iconImg ext-icon-user" title="用户角色" onclick="grantRoleFun(\'{0}\');"/>',
															row.id);
											str += sy.formatString(
															'<img class="iconImg ext-icon-group" title="用户机构" onclick="grantOrganizationFun(\'{0}\');"/>',
															row.id);
											str += sy.formatString(
															'<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>',
															row.id);
											return str;
										}
									} ] ],
							toolbar : '#toolbar',
							onBeforeLoad : function(param) {
								//parent.$.messager.progress({
								//	text : '数据加载中....'
								//});
							},
							onLoadSuccess : function(data) {
								$('.iconImg').attr('src', myJSContext.pixel_0);//解决img src为空 出现空白图标的问题.
								//parent.$.messager.progress('close');
							},
							onDblClickRow: function(rowIndex, rowData){
								showFun(rowData.id);
							}
						});
		$("#d12").css({
			'height' : '10px',
			'border-spacing' : '0px 10px'
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	here is jqueryJSP/userlist.jsp
	<!-- 工具栏 -->
	<div id="toolbar" style="display: none;">
		<table>
			<tr id="d12">
				<td>
					<form id="searchForm" style="margin: 0px; display: inline">
						<table>
							<tr>
								<td>登录名</td>
								<td><input name="loginName" style="width: 60px;"></td>
								<td>用户名</td>
								<td><input name="userName" style="width: 60px;" /></td>
								<td>电话号码</td>
								<td><input name="cellNO" style="width: 85px;" /></td>
								<td>性别</td>
								<td><select name="sex" class="easyui-combobox"
									style="width: 70px;"
									data-options="panelHeight:'auto',editable:false"><option
											value="">请选择</option>
										<option value="1">男</option>
										<option value="0">女</option></td>
								<td>创建时间</td>
								<input name="reqType" value="selectAll" id="reqType" type="hidden" />
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
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-table_add',plain:true"
								onclick="">导入没做</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出没做</a></td>
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
