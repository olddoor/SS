<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/static/import.jsp"></jsp:include>
<head>
</head>
<body>
    <div style="margin:20px 0;"></div>
    <div class="easyui-panel" style="padding:5px">
        <ul class="easyui-tree" data-options=" 
        	        url:myJSContext.contextPath +'/group/selectTree',
                    method:'post',
                    animate:true,
                    toggleOnClick: true,
            		enableContextMenu: false
                ">
        </ul>
    </div>
</body>
</html>