<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"   %>
<%@ include file="taglibs.jsp" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="com.xwtech.uomp.base.pojo.sso.ResultInfoBean" %>
<%@ page import="com.xwtech.uomp.base.constants.RequestConstants" %>
<%
  HashMap map = (HashMap)request.getAttribute(RequestConstants.INFORMATION);
  ResultInfoBean resultInfo = (ResultInfoBean)map.get(RequestConstants.RESULT_INFO);
  
%>

<html>
<head>
<title>系统状态信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body bgColor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr valign="bottom" > 
    <td height="48" colspan="2" >
	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="275" height="48" valign="bottom" background="">
            <table width="275" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="1" height="16">&nbsp;</td>
                <td width="275" align="center" valign="top"><img src="" width="270" height="3"> 
                </td>
              </tr>
              <tr> 
                <td height="28">&nbsp;</td>
                <td><img src="" width="9" height="9" border="0">&nbsp;<font  color="#5D5D5D"></font> 
                </td>
              </tr>
            </table>
          </td>
          <td width="100%" align="center" valign="bottom" background=""> 
           <table width="98%" border="0" cellspacing="0" cellpadding="0">
             <tr> 
               <td height="16" align="left" valign="top"><img src="" width="260" height="3"></td>
             </tr>
             <tr> 
               <td height="28" align="right" valign="top">&nbsp;</td>
             </tr>
            </table>
          </td>
        </tr>
      </table>
	</td>
  </tr>
  <tr> 
    <td width="1%" height="99%" background="">&nbsp;</td>
    <td width="99%" align="center" valign="middle">
     <%if(!resultInfo.getIsAlert()){ %>
	  <!--程序设计代码开始-->
	  <table width="530" height="315" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" valign="top" background="">
		    <table width="96%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="14%" height="120">&nbsp;</td>
                <td width="80%">&nbsp;</td>
                <td width="6%">&nbsp;</td>
              </tr>
              <tr>
                <td height="177">&nbsp;</td>
                <td align="left" valign="top">
	                <span style="font-size:14px;line-height: 24px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                  <font COLOR="#FF0000"><b>信息提示：</b></font>
	                  <%if(resultInfo.getPromptInfo()!=null){ %>
	                   <%=resultInfo.getPromptInfo()%>
	                  <%}%>
	                  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请点击
	                  <a href="#" onClick="pageHandle();"><b><u><font color="#0000FF">&nbsp;这 里&nbsp;</font></u></b></a>，进行跳转！
	                </span>
	                <br><br>
	                <br><br>
                </td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <BR>
	  <!--程序设计代码结束-->
	 <%}%>
    </td>
  </tr>
</table>
</body>
</html>



<%
  //超时提示信息
  if(resultInfo.getIsOverTime()){
     if(resultInfo.getIsAlert()){
	
        out.println("<script>alert('【系统提示】\\n\\n"+ resultInfo.getAlertInfo() +"');");
        out.println("	top.location='" + request.getContextPath() + "/index.jsp';");
        out.println("</script>");
     }else{
        out.println("<script>");
        out.println("function pageHandle(){");
  		out.println("	top.location='"+request.getContextPath()+"/index.jsp';");
		out.println("}");
        out.println("</script>");
     }
     return;
  }
  
  //没有权限提示信息
  if(resultInfo.getIsPopedom()){
      if(resultInfo.getIsAlert()){
	 
        out.println("<script>alert('【系统提示】\\n\\n"+ resultInfo.getAlertInfo() +"');");
        //out.println("	history.back();");
        out.println("</script>");
     }else{
        out.println("<script>");
        out.println("function pageHandle(){");
  		//out.println("	history.back();");
		out.println("}");
       // out.println("setTimeout(\"pageHandle()\",3000);");
        out.println("</script>");
     }
     return;
  }
  
  
  //关闭当前窗口
  if(resultInfo.getIsCloseWindow()){
     if(resultInfo.getIsAlert()){
	
        out.println("<script>alert('【系统提示】\\n\\n"+ resultInfo.getAlertInfo() +"');");
        out.println("	window.close();");
        
        //out.println("	window.opener.location.reload();");
        out.println("</script>");
     }else{
        out.println("<script>");
        out.println("function pageHandle(){");
  		out.println("	window.close();");
  		//out.println("	XW_dialog.close();");
  		//out.println("window.opener.location.reload();");
  		
		out.println("}");
       // out.println("setTimeout(\"pageHandle()\",3000);");
        out.println("</script>");
     }
     return;
  }
  
  
  //关闭当前窗口
  if(resultInfo.getIsOpenTopWindow()){
     if(resultInfo.getIsAlert()){
	
        out.println("<script>alert('【系统提示】\\n\\n"+ resultInfo.getAlertInfo() +"');");
        if(resultInfo.isCasFlag()){
        	out.println("	window.top.location.href = '" + resultInfo.getCasUrl() + request.getContextPath() + resultInfo.getGotoUrlForward()+"';");
        }else{
        	out.println("	window.top.location.href = '" + request.getContextPath() + resultInfo.getGotoUrlForward()+"';");
        }
        out.println("</script>");
     }else{
        out.println("<script>");
        out.println("function pageHandle(){");
        out.println("	window.top.location.href = '" + request.getContextPath() + resultInfo.getGotoUrlForward()+"';");
		out.println("}");
        //out.println("setTimeout(\"pageHandle()\",3000);");
        out.println("</script>");
     }
     return;
  }

  //前进跳转提示信息
  if(resultInfo.getIsRedirect()){
     if(resultInfo.getIsAlert()){
	
        out.println("<script>alert('【系统提示】\\n\\n"+ resultInfo.getAlertInfo() +"');");
        out.println("	window.location = '" + request.getContextPath() + resultInfo.getGotoUrlForward()+"';");
        out.println("</script>");
     }else{

        out.println("<script>");
        out.println("function pageHandle(){");
  		out.println("	window.location = '" + request.getContextPath() + resultInfo.getGotoUrlForward()+"';");
		out.println("}");
        //out.println("setTimeout(\"pageHandle()\",3000);");
        out.println("</script>");
     }
     return;
  }
  
  
  //是否刷新父窗口
  if(resultInfo.getIsRefreshParentWindow()){

     if(resultInfo.getIsAlert()){
        out.println("<script>alert('【系统提示】\\n\\n"+ resultInfo.getAlertInfo() +"');");
        out.println("	parent.window.location.href = parent.window.location.href;");
        out.println("</script>");
     }else{

        out.println("<script>");
        out.println("function pageHandle(){");
		
  		out.println("	parent.window.location.href = parent.window.location.href;");
		out.println("}");
        //out.println("setTimeout(\"pageHandle()\",3000);");
        out.println("</script>");
     }
     return;
  }
  
  //是否刷新创始者窗口
  if(resultInfo.getIsRefreshOpenerWindow()){
     if(resultInfo.getIsAlert()){
	
        out.println("<script>alert('【系统提示】\\n\\n"+ resultInfo.getAlertInfo() +"');");
        out.println("opener.window.location.href = opener.window.location.href;");
        out.println("</script>");
     }else{
        out.println("<script>");
        out.println("function pageHandle(){");
  		out.println("	opener.window.location.href = opener.window.location.href;");
		out.println("}");
       // out.println("setTimeout(\"pageHandle()\",3000);");
        out.println("</script>");
     }
     return;
  }
  

  //

%>

