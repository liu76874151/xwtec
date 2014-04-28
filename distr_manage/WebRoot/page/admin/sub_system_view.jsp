<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@ include file="../taglibs.jsp"%>
<%
String sysNum= request.getParameter("sysNum");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>子系统管理查询页面</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
<script src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript" type="text/javascript"></script>
<script src="${contextPath}/resource/scripts/main.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/sub_system_view.js"></script>


</head>

<body>
  <input type="hidden" id="sysNum" name="sysNum" value="<%=sysNum %>">
  <div style="padding:10px;overflow:auto;">
  	  		<div class="breadcrumb"><span></span>子系统档案信息--查看</div>
		  		<!-- 展示信息内容区域开始 -->
			    <table  class="tb">
	    		<tr>
	      			<th align="right">子系统编码：</th>
		  			<td class="form_table_content">
		  			      <input type="text" class="form_input" maxlength="2" name="sysNum" id="sysNum1" value="" readonly="readonly"/>
		  			</td>
		  			<th align="right">子系统名称：</th>
		  			<td class="form_table_content">
		  			      <input type="text" class="form_input" maxlength="50" name="sysName" id="sysName"  value="" readonly="readonly"/>
		  		    </td>
				</tr>
	    		<tr>
	      			<th align="right">子系统标题：</th>
		  			<td class="form_table_content"><input type="text" class="form_input" maxlength="20" name="sysTitle"  id="sysTitle" value="" readonly="readonly"/></td>
		  			<th align="right">子系统序号：</th>
		  			<td class="form_table_content"><input type="text" class="form_input" maxlength="5" name="xh" id="xh"  value="" readonly="readonly"/></td>
				</tr>
				<tr>
		  			<th align="right">子系统URL：</th>
					<td class="form_table_content" colspan="3">
						<input type="text" class="form_input" maxlength="2000" name="sysUri"  id="sysUri"  value="" readonly="readonly"/>
					</td>
		  		</tr>
		 		<tr>
	  	   			<th align="right">备注：</th>
		   			<td class="form_table_content" colspan="3" >
		   				<textarea class="form_text" name="bz" id="bz" id="bz" readonly="readonly"></textarea>
		   			</td>
	  	 		</tr>
        		</table>		
		   	<!--详细信息区 结束-->
	 		</div>
 
</body>
</html>
