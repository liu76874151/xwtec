<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%@page import="java.net.URLDecoder"%>
<%
String funcId = URLDecoder.decode(request.getParameter("funcId"),"utf8");
String jbNum = URLDecoder.decode(request.getParameter("jbNum"),"utf8");
String subSysNum = URLDecoder.decode(request.getParameter("subSysNum"),"utf8");
%>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css">
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js" language="javascript"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/dhtmlxform.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/custom_menu_view.js"></script>
  </head>
  
  <body>
    <input type="hidden" id="funcId1" name="funcId" value="<%=funcId %>">
    <input type="hidden" id="jbNum1" name="jbNum" value="<%=jbNum %>">
    <input type="hidden" id="subSysNum1" name="subSysNum" value="<%=subSysNum%>">
      <div style="padding:10px;overflow:auto;">
  	  		<div  class="breadcrumb-iframe"><span></span>自定义菜单--查看</div>
		  		<!-- 展示信息内容区域开始 -->
			    <table  width="96%" cellspacing="1" class="form_table">
	    		<tr>
	      			<th class="view_info_title" align="left" style="width:20%">子系统编码：</th>
		  			<td class="view_info_content" id="subSysNum" style="width:30%"></td>
		  			<th class="view_info_title"  align="left" style="width:20%">子系统名称：</th>
		  		    <td class="view_info_content" id="subSysName" style="width:30%"></td>
		  		</tr>
	    		<tr>
	      			<th class="view_info_title"  align="left" style="width:20%">功能编码：</th>
		  		    <td class="view_info_content" id="funcId"  style="width:30%"></td>
		  			<th class="view_info_title"  align="left" style="width:20%">功能名称：</th>
				    <td class="view_info_content" id="funcName"  style="width:30%"></td>
				</tr>
				<tr>
		  			<th class="view_info_title"  align="left" style="width:20%">功能名称：</th>
					<td class="view_info_content" colspan="3" id="moudlSortName"  style="width:30%"></td>
		  		</tr>
		  		<tr>
		  		    <th class="view_info_title"  align="left" style="width:20%">功能URL：</th>
					<td class="view_info_content" colspan="3" id="funcUri"  style="width:30%"></td>
		  		</tr>
		 		<tr>
	  	   			<th class="view_info_title"  align="left" style="width:20%">备注：</th>
		   			<td class="view_info_content" id="bz"  style="width:80%" colspan="3"></td>

	  	 		</tr>
       </table>		
     <!--详细信息区 结束-->
   </div>
  </body>
</html>