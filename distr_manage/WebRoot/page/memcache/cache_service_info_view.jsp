<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<%
String selectRowId = request.getParameter("selectRowId");
%>
<html>
	<head>
    	<title>新增缓存服务</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript" ></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/main.js" language="javascript" ></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/memcache/cache_service_view.js"></script>
  	</head>
  	<body onload="view(${selectRowId})">
  	<input type="hidden" id="selectRowId" value="<%=selectRowId %>">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb">缓存服务--查看</div>
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="96%" cellspacing="1" class="form_table">
  	  			<tr>
  	  				<td class="view_info_title"  width="20%">缓存编码：</td>
  	  				<td class="view_info_content" style="width:80%" id="num"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">缓存类型名称：</td>
	 				<td class="view_info_content" style="width:80%" id="typeNum"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">服务器列表：</td>
	 				<td class="view_info_content" style="width:80%" id="servers"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">服务器权重：</td>
	 				<td class="view_info_content" style="width:80%" id="weights"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">初始连接数：</td>
	 				<td class="view_info_content" style="width:80%" id="initConn"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">最小连接数：</td>
	 				<td class="view_info_content" style="width:80%" id="minConn"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">最大连接数：</td>
	 				<td class="view_info_content" style="width:80%" id="maxConn"></td>
	 			</tr>
	 			
	 			<tr>
	 				<td class="view_info_title">最大处理时间：</td>
	 				<td class="view_info_content" style="width:80%" id="maxIdle"></td>
	 			</tr>
	 				<tr>
	 				<td class="view_info_title">主线程睡眠时间：</td>
	 				<td class="view_info_content" style="width:80%" id="mainSleep"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">tcp参数nagle：</td>
	 				<td class="view_info_content" style="width:80%" id="nagle"></td>
	 			</tr>
	 			
	 			<tr>
	 				<td class="view_info_title">tcp参数socketTO：</td>
	 				<td class="view_info_content" style="width:80%" id="socketTo"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">tcp参数socketConnectTO：</td>
	 				<td class="view_info_content" style="width:80%" id="socketConnectTo"></td>
	 			</tr>
	 			
        		</table>
	   			<!-- 展示信息内容区域结束 -->
		</div>
  	</body>
</html>