<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
<jsp:include page="<%=contextPath%>/static/import.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加组织信息',
			url : myJSContext.contextPath + '/jqueryJsp/addGroup.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		},'500', '350');//
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看组织信息',
			url : myJSContext.contextPath + '/jqueryJsp/addGroup.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		console.log("222222222222222222222222222");
		console.log(id);
		var dialog = parent.sy.modalDialog({
			title : '编辑组织信息',
			url : myJSContext.contextPath + '/jqueryJsp/addGroup.jsp?id=' + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(myJSContext.contextPath + '/groupServlet', {
					id : id,
					reqType:'delete'
				}, function() {
					grid.treegrid('reload');
				}, 'json');
			}
		});
	};
	var grantFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '组织授权',
			url : sy.contextPath + '/securityJsp/base/SyorganizationGrant.jsp?id=' + id,
			buttons : [ {
				text : '授权',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var redoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('expandAll', node.id);
		} else {
			grid.treegrid('expandAll');
		}
	};
	var undoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('collapseAll', node.id);
		} else {
			grid.treegrid('collapseAll');
		}
	};
	$(function() {
		grid = $('#grid').treegrid({ //treegrid作者修改过的方法.支持平滑取数
			title : '',
			url : myJSContext.contextPath + '/groupServlet?reqType=selectAll',//'/jqueryJsp/t.json',
			idField : 'id',
			treeField : 'name',
			parentField : 'pid',
			rownumbers : true,
			pagination : false,
			//sortName : 'seq',
			//sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '200',
				title : '组织名称',
				field : 'name'
			} ] ],
			columns : [ [ {
				width : '150',
				title : '图标名称',
				field : 'iconCls'
			}, {
				width : '150',
				title : '组织编码',
				field : 'code'
			}, {
				width : '200',
				title : '组织地址',
				field : 'address'
			},  {
				width : '150',
				title : '修改时间',
				field : 'updateDate'
			}, /* {
				width : '60',
				title : '排序',
				field : 'seq'
			}, */
			{
				title : '操作',
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.id);
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.id);
						str += sy.formatString('<img class="iconImg ext-icon-group_key" title="授权" onclick="grantFun(\'{0}\');"/>', row.id);
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除111" onclick="removeFun(\'{0}\');"/>', row.id);
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(row, data) {
				$('.iconImg').attr('src', myJSContext.pixel_0);
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>