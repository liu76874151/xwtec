<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String userId = request.getParameter("userId");
String userType = request.getParameter("userType");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限修改</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css">
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/user_privilege_edit.js"></script>
  </head>
  
  <body>
	<input type="hidden" value="<%=userId %>" name="userId" id="userId">
	<input type="hidden" value="<%=userType %>" name="userType" id="userType">
	<div id="main_div" style="padding:10px;overflow:auto;height:100%">
		<div class="breadcrumb"><span></span>权限管理</div>
		<!-- 权限信息树 -->
	    <div id="privilege_tree" style="width:99%;height:80%;" class="form_text"> 
	    </div>
	    <table width="100%" height="40">
		<tr>
    		<td align="center">	  
    			<a href="javascript:void(0)" class="btn" onclick="openAllTree();">全部展开</a>
    			<a href="javascript:void(0)" class="btn" onclick="closeAllTree();">全部收缩</a>
	  			<a href="javascript:void(0)" class="btn" onclick="doAllCheck();">全选</a>
				<a href="javascript:void(0)" class="btn" onclick="doReset();">清除重填</a>
				<a href="javascript:void(0)" class="btn" onclick="doSubmit();">确认</a>
			</td>
		</tr>
    	</table>
	</div>
  </body>
</html>