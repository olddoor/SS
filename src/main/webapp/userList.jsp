<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="com.zyl.demo.ss1.entity.*" %>
<%@ page language="java" import="com.zyl.demo.ss1.service.*" %>
<%@ page language="java" import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>user	list</title>
				<style type="text/css" id="defaultPopStyle">
						.cPopText { font-family: Tahoma, Verdana; background-color: #FFFFCC; border: 1px #000000 solid; font-size: 12px; padding-right: 4px; padding-left: 4px; height: 20px; padding-top: 2px; padding-bottom: 2px; visibility: hidden; filter: Alpha(Opacity=80)}.STYLE1 {color: #0000FF}
				 </style>
        		<style type="text/css">
					a{ text-decoration: none; color: #000000 }
					a:hover{ text-decoration: underline }
					body{ scrollbar-base-color: #F3F6FA; scrollbar-arrow-color: #4D76B3; font-size: 16px; background-color: #ffffff }
					table{ font: 14px Verdana,Tahoma; color: #FAF7F9 }
					input,select,textarea	{ font: 14px Verdana,Tahoma; color: #000000; font-weight: normal; background-color: #F3F6FA }
					select{ font: 11px Verdana,Tahoma; color: #000000; font-weight: normal; background-color: #F3F6FA }
					.nav{ font: 12px Verdana,Tahoma; color: #000000; font-weight: bold }
					.nav a{ color: #000000 }
					.header{ font: 14px Verdana,Tahoma; color: #FFFF77 font-weight: bold; background-image: url("images/green/bg01.gif") }
					.header a{ color: #FFFFFF }
					.category{ font: 11px Verdana,Tahoma; color: #000000; background-color: #EFEFEF }
					.tableborder{ background: #4D76B3; border: 0px solid #4D76B3 } 
					.singleborder{ font-size: 0px; line-height: 0px; padding: 0px; background-color: #F3F6FA }
					.smalltxt{ font: 11px Verdana,Tahoma }
					.outertxt{ font: 12px Verdana,Tahoma; color: #000000 }
					.outertxt a{ color: #000000 }
					.bold{ font-weight: bold }
					.altbg1{ background: #F3F6FA }
					.altbg2{ background: #FFFFFF }
					.mybtn14_3  
						{   
							color: #000000; 
						}  
					
				</style>
</head>
<script language="javascript" type="text/javascript"> 
//加法函数
	function openWin(url,o){
		window.showModalDialog(url,o,
		'height=50,width=100,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
	}
	function jumpWin(url){
		window.location=url;
	}
	function previousPage(){
		//调用时document.pageForm.submit()时,实际是通过id直接提交.
		//var previousPage=document.getElementById("pageNo").value;
		var page=document.forms["pageForm"].pageNo.value;
		document.forms["pageForm"].newPage.value=page-1;
		document.pageForm.submit();//表单提交
	}
	function nextPage(){
		//var nextPage=document.getElementById("nextPage").value;
		var page=document.forms["pageForm"].pageNo.value;
		var num1 = parseInt(page);
		var num2 = 1;
		var total = num1 + 1;
		document.forms["pageForm"].newPage.value=total;
		document.pageForm.submit();//表单提交
	}
	function subMit(){
		document.forms["pageForm"].newPage.value=1;
		document.pageForm.submit();//表单提交
	}
    function AddInt(){
        var rtn = 0;
        for(var i=0;i<arguments.length;i++){
            if(isNaN(arguments[i]))continue;
            rtn+=parseInt(arguments[i]);
        }
        return rtn;
    }
    function resetMethod(){
    	document.getElementById("loginName2").value="";
    }
</script>
<body>
<%	
	String basePath=request.getContextPath(); 
	final int PAGE_SIZE = 10; //每页数量
	final int PAGES_PER_TIME = 1;//从第几条数据开始
	int pageNo = 1; //当前页
	String nowPage =request.getParameter("pageNo");
	String newPage =request.getParameter("newPage");
	//out.print("newPageNO "+newPage);
	//out.print("</br>");
	//如果有新的当前页标记,则用新的.否则用默认
	if (newPage != null && !newPage.trim().equals("")) {
		try {
			pageNo = Integer.parseInt(newPage);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
	}
	if (pageNo <= 0){
			pageNo = 1;
		}
	
	//String loginName, String userName ,	String cellNO	
    String loginName=request.getParameter("loginName");
	if(loginName==null){
		loginName="";
	}
	//out.print("loginName "+loginName);
//	out.print("</br>");
	//out.print("NOW PAGE "+pageNo);
	//out.print("</br>");
    String userName=request.getParameter("userName");
    String cellNO=request.getParameter("cellNO");
    String firstResult="0"; //从第几条数据开始
    
    String maxResults=String.valueOf(PAGE_SIZE);
	int totalRecords =0; //总记录数
	UserService userService=new UserServiceImpl();
	totalRecords=userService.count(loginName,userName,cellNO);
	
	//计算总页数=(总记录数+每页显示数-1)/每页显示数
	int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE; 
	if (pageNo > totalPages){
		pageNo = totalPages;
	}
	
	if(newPage!=null){
		if(Integer.parseInt(newPage)>totalPages){
			newPage = String.valueOf(totalPages);
		}
    	int result=(Integer.parseInt(newPage)-1)*PAGE_SIZE;
    	firstResult=String.valueOf(result);
    }
	//else if(nowPage!=null){
   // 	int result=(Integer.parseInt(nowPage))*PAGE_SIZE;
    //	firstResult=String.valueOf(result);
    //}
	
	List<User> users = new ArrayList<User>();
	//out.print("</br>");
	//out.print("firstResult"+firstResult);
	////out.print("</br>");
	//out.print("maxResults"+maxResults);
	users=userService.getUsers( loginName,  userName, cellNO,  firstResult,  maxResults);
		int size=0;
		size=users.size();
	//	out.print("</br>");
	//	out.print("pageNo-------- "+pageNo);	
%>	
	<form name="pageForm" action="<%=basePath%>/userList.jsp" method="post">
	<table align="center" border="0" cellpadding="0" cellspacing="0" width="97%">
			<tbody>
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td height="3"></td>
								</tr>
								<tr>
									<td>
										<table class="tableborder" cellpadding="2" cellspacing="1">
											<tbody>
												<tr class="smalltxt" bgcolor="white">
													<td class="header">
														<div class="mybtn14_3">  
														&nbsp;总记录数<%=totalRecords %>&nbsp;
														</div>
													</td>
													<td class="header">
														<div class="mybtn14_3"> 
														当前页数&nbsp;<%=pageNo %>/<%=totalPages %>&nbsp;
														</div>
														<input id="pageNow" type="hidden" value=<%=pageNo%>></input>
													</td>
													<td>
														&nbsp;
														<a href="#" onclick="previousPage()">上一页</a>&nbsp;
													</td>
													<%
													int start = ((pageNo - 1) / (PAGES_PER_TIME)) * PAGES_PER_TIME + 1;
													
													for(int i=start; i<start+PAGES_PER_TIME; i++) {
														if(i > totalPages) {
															break;
														}
														if(pageNo == i) {
													 %>
															<!-- <td bgcolor="black">&nbsp;<u><b><%=i %></b></u>&nbsp;</td>-->
														<%
														}else{
														 %>
															<td>&nbsp;
																<a href="userList.jsp?pageNo=<%=i%>"><%=i%></a>&nbsp;
															</td>
													<%
														}
													}
													 %>
													<td>
														&nbsp;
														<a href="#" onclick="nextPage()">下一页</a>&nbsp;
													</td>
												</tr>
												<tr class="smalltxt" bgcolor="white" >
													 <td class="header"><div class="mybtn14_3">查询过滤条件</div></td>
													 <td class="header">
													 	<div class="mybtn14_3">登录名<input type="text" size="5" id="loginName2" name="loginName" value="<%=loginName%>"></input></div>
													</td>
													 <td class="header">
													 <input type="button" value="提交" onclick="subMit()"></td>
													 <td class="header">
													 <input type="button" value="重置"  onclick="resetMethod()"/></td>
													 	<input type="hidden" id="pageNo" name="pageNo" value=<%=pageNo%> />
														<input type="hidden" id="newPage" name="newPage"  value=<%=newPage%> />
													</tr>
													<tr class="smalltxt" bgcolor="white" >
														<td colspan="4"><a style="color:red" href="index.jsp">返回首页</a></td>
													</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td height="3"></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<table class="tableborder" align="center" cellpadding="4" cellspacing="1" width="80%">
			<tbody>
			  <tr class="header">
              <td align="center" width="5%"> id </td>
              <td align="center" width="5%"> 登录名 </td>
              <td align="center" width="5%"> 姓名 </td>
              <td align="center" width="8%"> 密码 </td>
              <td align="center" width="10%">更新时间</td>
              <td align="center" width="20%">操作</td>
              </tr>

            <%
	if(size>0){
		for(User a:users){
            %>  
		<form name="list" action="/userList.jsp" method="post">
              <tr>
              <td class="altbg1" align="center">
              	<span class="altbg2">
              <input name="id" type="text" size="8"value="<%=a.getId()%>"disabled="true" />
              	</span></td>
			  <td class="altbg1" align="center"><span class="altbg1">
                <input name="loginName" type="text" size="10"value="<%=a.getLoginName()%>" disabled="true" />
              </span></td>
              <td class="altbg1" align="center"><span class="altbg2">
                <input name="userName" type="text"size="14" value="<%=a.getUserName()%>"disabled="true"  />
              </span></td>
              <td class="altbg1" align="center"><span class="altbg2">
              <input name="password" type="text"size="14" value="<%=a.getPassword()%>"disabled="true"  />
              </span></td>
              <td class="altbg1" align="center"><span class="altbg2">
              <input name="updateDate" type="text"size="30" value="<%=a.getUpdateDate()%>"disabled="true" />
              </span></td>
              <td class="altbg1" align="center" style="width:100px;">
              	<span class="altbg1"><a href="javascript:void(0)" onclick="javascript:jumpWin('editUser.jsp?id=<%=a.getId()%>')">修改</a></span>
                 &nbsp;&nbsp;
				<a target="detail" href="UserDelete.jsp?id=<%=a.getId()%>" onClick="return confirm('真的要删除?')">删除</a>
               </td>
              </tr>
              
				<%
				}
				%>
</form>
            <%
			}else{
			%>
            <tr>
              <td colspan="6">没有任何数据！！</td>
            </tr>
            <%
            	}
             %>
          </tbody>
        </table>
        <table align="center" border="0" cellpadding="0" cellspacing="0"
			width="97%">
			<tbody>
				<tr>
				  <td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<a name="bottom" />
</body>
</html>
