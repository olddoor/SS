<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String uid = request.getParameter("id");
	if (uid == null) {
		uid = "";
	}
%>
<html>
<jsp:include page="/static/import.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
</head>

<body>
<script type="text/javascript">
	var uploader;//上传对象
	
	//重置
	function reset(){
		$('#form input[name!=reqType]').val('');
	}
	//提交
	var submitNow = function($dialog, $grid, $pjq) {
		var url = '<%=contextPath%>'+'/user/saveORupdate';
		/*if ($(':input[name="id"]').val().length > 0) {
			url = url+"?reqType=update"; //更新
		} else {
			url = url+"?reqType=reg";//新增
		}
		*/
		$.post(url,sy.serializeObject($('form')),function(result){
			parent.sy.progressBar('close');//关闭上传进度条
			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load'); //grid重新刷新
				$dialog.dialog('destroy');//关闭之前的新增/修改窗口
			}else{
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		},'json');
	};
	
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			if (uploader.files.length > 0) {
				uploader.start();
				uploader.bind('StateChanged', function(uploader) {// 在所有的文件上传完毕时，提交表单
					if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
						submitNow($dialog, $grid, $pjq);
					}
				});
			} else {
				submitNow($dialog, $grid, $pjq);
			}
		}
	};
	
$(function() {
	if ($(':input[name="id"]').val().length > 0) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post('<%=contextPath%>' + '/user/getUser', {
			id : $(':input[name="id"]').val()
			/*
				reqType:"selectUser"
			*/
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
	
	
	uploader = new plupload.Uploader({//上传插件定义
		browse_button : 'pickfiles',//选择文件的按钮
		container : 'container',//文件上传容器
		runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
		//flash_swf_url : sy.contextPath + '/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
		url : myJSContext.contextPath + '/src/pluploadServlet?fileFolder=/userPhoto',//上传文件路径
		max_file_size : '5mb',//100b, 10kb, 10mb, 1gb
		chunk_size : '10mb',//分块大小，小于这个大小的不分块
		unique_names : true,//生成唯一文件名
		// 如果可能的话，压缩图片大小
		/*resize : {
			width : 320,
			height : 240,
			quality : 90
		},*/
		// 指定要浏览的文件类型
		filters : [ {
			title : '图片文件',
			extensions : 'jpg,gif,png'
		} ]
	});
	uploader.bind('Init', function(up, params) {//初始化时
		//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
		$('#filelist').html("");
	});
	uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
		if (uploader.files.length > 1) {
			parent.$.messager.alert('提示', '只允许选择一张照片！', 'error');
			uploader.stop();
			return;
		}
		$('.ext-icon-cross').hide();
	});
	uploader.bind('FilesAdded', function(up, files) {//选择文件后
		$.each(files, function(i, file) {
			$('#filelist').append('<div id="' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ')<strong></strong>' + '<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor:pointer;" class="ext-icon-cross" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>');
		});
		up.refresh();
	});
	uploader.bind('UploadProgress', function(up, file) {//上传进度改变
		var msg;
		if (file.percent == 100) {
			msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制客户看到99%，等后台合并完成...
		} else {
			msg = file.percent;
		}
		$('#' + file.id + '>strong').html(msg + '%');

		parent.sy.progressBar({//显示文件上传滚动条
			title : '文件上传中...',
			value : msg
		});
	});
	uploader.bind('Error', function(up, err) {//出现错误
		$('#filelist').append("<div>错误代码: " + err.code + ", 描述信息: " + err.message + (err.file ? ", 文件名称: " + err.file.name : "") + "</div>");
		up.refresh();
	});
	uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
		var response = $.parseJSON(info.response);
		if (response.status) {
			$('#' + file.id + '>strong').html("100%");
			$(':input[name="data.photo"]').val(response.fileUrl);
		}
	});
	uploader.init();
	
	
});
</script>
	<form  method="post" id="form" class="form">
	<input name="id" value="<%=uid%>" readonly="readonly" type="hidden"/>
	<fieldset>
		<legend>用户信息</legend>
		<table class="table" style="width: 100%;">
			<tr>
				<td>登录名</td>
				<td><input class="easyui-textbox"  name="loginName" data-options="required:true"/></td>
				<td>姓 名</td>
				<td><input class="easyui-textbox"   name="userName" data-options="required:true"/></td>
			</tr>
			<tr>
				<td>密 码</td>
				<td><input class="easyui-textbox" type="password" name="password" data-options="required:true"/></td>
			    <td>密码确认</td>
				<td><input class="easyui-textbox"  id="password2" type="password" name="password2" data-options="required:true"/></td>
			<tr>
					<td>性 别</td>
					<td><select class="easyui-combobox" name="sex"
						data-options="panelHeight:'auto',editable:false ,required:true"
						style="width: 145px;">
							<option value="1">男</option>
							<option value="0">女</option>
					</select></td>
					<td>电话号码</td>
					<td><input class="easyui-textbox" name="cellNO"/></td>
			</tr>
			<tr>	<td>电子邮箱</td>
					<td><input class="easyui-textbox" name="email"/></td>
					<td>传真</td>
					<td><input class="easyui-textbox" name="fax"/></td>
			</tr>
			<tr>
					<th>照片上传</th>
					<td><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom'">选择文件</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div></td>
					<th></th>
					<td><input name="data.photo" readonly="readonly" style="display: none;" /> <img id="photo" src="" style="width: 200px; height: 200px;"></td>
				</tr>
			</table>		
	</fieldset>
	</form>
</body>
</html>