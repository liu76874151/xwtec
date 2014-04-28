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
		<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/memcache/cache_service_edit.js"></script>
  	</head>
  	<body onload="query()">
  		<input type="hidden" id="selectRowId" value="<%=selectRowId %>">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb-iframe">缓存服务--编辑</div>
  			<input type="hidden" name="num" id="num">
	  		<form name="addForm" id="addForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb">
  	  			<tr>
  	  				<th align="right" width="10%">缓存编码：</th>
					<td class="form_table_content">  <span  id="numLable" name="numLable" > </span> </td>
	 			</tr>
	 			<tr>
	 				<th align="right">缓存类型名称：</th> 
					<td class="form_table_content">
				         	<select name="typeNum" id="typeNum" class="form_input">
                
						 	</select>
			      	</td>
	 			</tr>
	 			<tr>
	 				<th align="right">服务器列表：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="servers" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">服务器权重：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="weights" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">初始连接数：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="initConn" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">最小连接数：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="minConn" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">最大连接数：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="maxConn" /></td>
	 			</tr>
	 			
	 			<tr>
	 				<th align="right">最大处理时间：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="maxIdle" /></td>
	 			</tr>
	 				<tr>
	 				<th align="right">主线程睡眠时间：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="mainSleep" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">tcp参数nagle：</th>
					<td class="form_table_content">
					<select id="nagle" name="nagle">  
					   <option value="true"> true </option>
					   <option value="false"> false </option>
					</select>
					</td>
	 			</tr>
	 			
	 			<tr>
	 				<th align="right">tcp参数socketTO：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="socketTo" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">tcp参数socketConnectTO：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="socketConnectTo" /></td>
	 			</tr>
	 			
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
	  			<tr>
				    <td align="center">
					  <a href="javascript:void(0)" class="btn" onclick="update();">确认提交</a>
					  <a href="javascript:void(0)" class="btn" onclick="document.addForm.reset();">清除重填</a>
					</td>
	  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
  	</body>
</html>