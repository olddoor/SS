<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="script/style.css" type="text/css">
</head>
<script>
function hidemenu(){
	if(parent.menuState == 0){
		parent.menuState = 1;
		parent.mleft.cols='*,100%';
		parent.mtitle.location.reload();
	}else{
			parent.menuState = 0;	
	}
}
</script>
<body bgcolor="#0096e1" text="#000000">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr valign="bottom" align="right"> 
    <td colspan="3" height="40" bgcolor="#0096e1"><a href="#" onclick="hidemenu()"><img src="images/lefticon.gif" border=0 alt="隐藏菜单列表"></a>&nbsp;</td>
  </tr>
  <tr> 
    <td width="1"></td>
    <td width="96%"><IFRAME  name="LIST" WIDTH="100%" HEIGHT="90%" MARGINWIDTH=0 MARGINHEIGHT=0 HSPACE=0 VSPACE=0 FRAMEBORDER=0 SCROLLING=auto BORDERCOLOR="#000000" SRC="adminTree.html"> 
      </IFRAME></td>
    <td width="3"></td>
  </tr>
</table>
</body>
</html>