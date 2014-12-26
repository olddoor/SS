<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<TITLE></TITLE>
<LINK REL=stylesheet HREF="script/toc.css" TYPE="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script/AdminTree.js"></script>
<STYLE TYPE='text/css'>
.level1 {margin-left:30;}
.level2 {display:none;margin-left:38;}
</STYLE>
</HEAD>
<BODY onLoad="init()" topmargin="0" leftmargin="0" rightmargin="0">
<DIV CLASS="level1" ID='head2Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head2");'><IMG border=0 name=imEx  src="images/arrowUp.gif" id=ttt>ÕÊºÅ¹ÜÀí</a>
</DIV>

<DIV CLASS="level2" ID='head2Child'>
	<A href="UserList.jsp" id=ttt target=main><LI>Ñ§ÉúÁÐ±í</LI></a>
	<A href="TeacherList.jsp" id=ttt target=main><LI>µ¼Ê¦ÁÐ±í</LI></a>
</DIV>

<DIV CLASS="level1" ID='head3Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head3");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> ÂÛÎÄ¹ÜÀí</a>
</DIV>

<DIV CLASS="level2" ID='head3Child'>
	<A href="lunwenList.jsp" id=ttt target=main><LI>ÂÛÎÄÁÐ±í</LI></a>
	<A href="lwnull.jsp" id=ttt target=main><LI>Î´Ñ¡ÂÛÎÄ</LI></a>
</DIV>

<DIV CLASS="level1" ID='head4Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head4");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> Ñ§Éú¹ÜÀí</a>
</DIV>

<DIV CLASS="level2" ID='head4Child'>
	<A href="yjsqxs.jsp" id=ttt target=main><LI>ÒÑÉêÇë¿ÎÌâÑ§Éú</LI></a>
	<A href="wsqktxs.jsp" id=ttt target=main><LI>Î´ÉêÇë¿ÎÌâÑ§Éú</LI></a>
</DIV>

<DIV CLASS="level1" ID='head6Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head6");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> ÏµÍ³¹«¸æ</a>
</DIV>

<DIV CLASS="level2" ID='head6Child'>
	<A href="xtxx.jsp" id=ttt target=main><LI>¹«¸æÐÅÏ¢</LI></a>
	<A href="xtadd.jsp" id=ttt target=main><LI>¹«¸æÌí¼Ó</LI></a>
	<A href="../example/example2.jsp" id=ttt target=main><LI>×ÊÁÏÉÏ´«</LI></a>
	<A href="../mydown/e.jsp" id=ttt target=main><LI>×ÊÁÏÏÂÔØ</LI></a>
</DIV>

<DIV CLASS="level1" ID='head7Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head7");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> Í³¼Æ·ÖÎö</a>
</DIV>

<DIV CLASS="level2" ID='head7Child'>
	<A href="../servlet/SalesCountServlet" id=ttt target=main><LI>ÏúÁ¿Í³¼Æ</LI></a>
</DIV>

<DIV CLASS="level1" ID='head8Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head8");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> Ö±²¥¹ÜÀí</a>
</DIV>

<DIV CLASS="level2" ID='head8Child'>
      <A href="Live.jsp" id=ttt target=main><LI>Ö±²¥×éÖ¯</LI></a>
      <A href="LiveWorkstation.jsp" id=ttt target=main><LI>Ö±²¥¹¤×÷Õ¾¹ÜÀí</LI></a>
</DIV>

</BODY>
</html>
