<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<jsp:include page="/static/import.jsp"></jsp:include>
        <meta charset="UTF-8">
    </head>
    <body>
        <div style="margin:20px 0;">
            <span>Select Region Panel:</span>
            <select id="region">
                <option value="north">North</option>
                <option value="south">South</option>
                <option value="east">East</option>
                <option value="west">West</option>
            </select>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addPanel()">Add</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="removePanel()">Remove</a>
        </div>
        <div id="cc" class="easyui-layout" style="width:700px;height:350px;">
            <div data-options="region:'north'" style="height:50px"></div>
            <div data-options="region:'south',split:true" style="height:50px;"></div>
            <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
            <div data-options="region:'west',split:true" title="West" style="width:100px;"></div>
            <div data-options="region:'center',title:'Center'"></div>
        </div>
        <script type="text/javascript">
            function addPanel(){
                var region = $('#region').val();
                var options = {
                    region: region
                };
                if (region=='north' || region=='south'){
                    options.height = 50;
                } else {
                    options.width = 100;
                    options.split = true;
                    options.title = $('#region option:selected').text();
                    options.href=myJSContext.contextPath +'/src/jqueryJsp/treeGroup';
                    options.method='post';
                }
                $('#cc').layout('add', options);
            }
            function addPanel1(){
            	
            }
            function removePanel(){
                $('#cc').layout('remove', $('#region').val());
            }
        </script>
    </body>
    </html>