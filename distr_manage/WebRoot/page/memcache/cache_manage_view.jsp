<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<%
String selectRowId = request.getParameter("selectRowId");
%>
<html>
	<head>
    	<title>缓存设置</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" ></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/memcache/cache_manage_view.js"></script>
  	</head>
  	<body onload="view(${selectRowId})">
  	<input type="hidden" id="selectRowId" value="<%=selectRowId %>">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb">缓存设置--查看</div>
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="96%" cellspacing="1" class="form_table">
  	  			<tr>
  	  				<td class="view_info_title"  width="20%">缓存key：</td>
  	  				<td class="view_info_content" style="width:80%" id="memKey"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">缓存类型名称：</td>
	 				<td class="view_info_content" style="width:80%" id="cachedName"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">DAO类的名称：</td>
	 				<td class="view_info_content" style="width:80%" id="daoName"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">DAO类中的方法名称：</td>
	 				<td class="view_info_content" style="width:80%" id="daoMethod"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">key值是否需要参数：</td>
	 				<td class="view_info_content" style="width:80%" id="isNeedParam"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">数据类型：</td>
	 				<td class="view_info_content" style="width:80%" id="keyType"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">存放的时间：</td>
	 				<td class="view_info_content" style="width:80%" id="expireInSeconds"></td>
	 			</tr>
	 			
	 			<tr>
	 				<td class="view_info_title">备注：</td>
	 				<td class="view_info_content" style="width:80%" id="bz"></td>
	 			</tr>
        		</table>
	   			<!-- 展示信息内容区域结束 -->
		</div>
  	</body>
</html>