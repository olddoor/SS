<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html> <!-- 这句很重要,通知使用W3C标准模式渲染 否则出现 easyui-combotree的显示异常及在IE8下无法使用的异常-->
<html>
<head>
<jsp:include page="<%=contextPath%>/static/import.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = myJSContext.contextPath + '/groupServlet?reqType=update';
			} else {
				url = myJSContext.contextPath + '/groupServlet?reqType=add';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					$grid.treegrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	var showIcons = function() {
		var dialog = parent.sy.modalDialog({
			title : '浏览小图标',
			url : myJSContext.contextPath + '/jslib/style/icons.jsp',
			buttons : [ {
				text : '确定',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.selectIcon(
							dialog, $('#iconCls'));
				}
			} ]
		});
	};
	
	
	$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(myJSContext.contextPath + '/groupServlet?reqType=selectGroup',
							{
								id : $(':input[name="data.id"]').val()
							},
							function(result) {
								if (result.id != undefined) {
									$('form').form('load',
										{'id' : result.id,
										 'name' : result.name,
												'address' : result.address,
												'fatherGroup.id' : result.fatherGroup ? result.fatherGroup.id : '',
												'iconCls' : result.iconCls,
														'seq' : result.seq,
														'code' : result.code
													});
									$('#iconCls').attr('class', result.iconCls);//设置背景图标
								}
								parent.$.messager.progress('close');
							}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>组织信息</legend>
			<table class="table" style="width: 100%;">
				<input name="data.id" value="<%=id%>" readonly="readonly"
					type="hidden" />
				<tr>
					<th>组织编码</th>
					<td><input name="code" /></td>
				</tr>
				<tr>
					<th>组织名称</th>
					<td><input name="name" class="easyui-validatebox"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<th>组织地址</th>
					<td><input name="address" /></td>
				</tr>
				<tr>
					<th>顺序</th>
					<td><input name="data.seq" class="easyui-numberspinner"
						data-options="required:true,min:0,max:100000,editable:false"
						style="width: 155px;" value="100" /></td>
				</tr>
				<tr>
					<th>上级组织</th>
					<td><select id="fatherGroup_id" name="fatherGroup.id"
						class="easyui-combotree"
						data-options="editable:false,idField:'id',textField:'name',parentField:'pid',url:'<%=contextPath%>/groupServlet?reqType=selectAll'"
						style="width: 155px;"></select><img class="iconImg ext-icon-cross"
						onclick="$('#fatherGroup_id').combotree('clear');" title="清空" /></td>
				</tr>
				<tr>
					<th>组织图标</th>
					<td><input id="iconCls" name="iconCls" readonly="readonly"
						style="padding-left: 18px; width: 134px;" /> <img
						class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img
						class="iconImg ext-icon-cross"
						onclick="$('#iconCls').val('');$('#iconCls').attr('class','');"
						title="清空" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>