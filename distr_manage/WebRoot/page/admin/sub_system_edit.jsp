<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@ include file="../taglibs.jsp"%>
<%
String sysNum= request.getParameter("sysNum");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>子系统管理修改页面</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
<script src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript" type="text/javascript"></script>
<script src="${contextPath}/resource/scripts/main.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/sub_system_edit.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>


</head>

<body>
  <input type="hidden" id="sysNum" name="sysNum" value="<%=sysNum %>">
  <div id="main_div" style="padding:10px;overflow:auto;">
 			<div class="breadcrumb"><span></span>子系统信息--修改</div>
			<form name="editForm" id="editForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	  			<table  class="tb">
		    		<tr>
		      			<th align="right">子系统编码：</th>
			  			<td class="form_table_content" ><%=sysNum %></td>
			  			<th align="right"><span class="required">*</span>子系统名称：</th>
			  			<td class="form_table_content"><input type="text" class="form_input" maxlength="20" name="sysName" id="sysName"  value="" /><span class="errorMsg"></span></td>
					</tr>
		    		<tr>
		      			<th align="right"><span class="required">*</span>子系统标题：</th>
			  			<td class="form_table_content"><input type="text" class="form_input" maxlength="20" name="sysTitle"  id="sysTitle" value="" /><span class="errorMsg"></span></td>
			  			<th align="right"><span class="required">*</span>子系统序号：</th>
			  			<td class="form_table_content"><input type="text" class="form_input" maxlength="5" name="xh" id="xh"  value="" /><span class="errorMsg"></span></td>
					</tr>
					<tr>
			  			<th align="right"><span class="required">*</span>子系统URL：</th>
						<td class="form_table_content" colspan="3">
							<input type="text" class="form_input" maxlength="1000" name="sysUri"  id="sysUri"  value="" /><span class="errorMsg"></span>
						</td>
			  		</tr>
			 		<tr>
		  	   			<th align="right">备注：</th>
			   			<td class="form_table_content" colspan="3" >
			   				<textarea class="form_text" name="bz" id="bz" id="bz" maxlength="1000"  ></textarea><span class="errorMsg"></span>
			   			</td>
		  	 		</tr>
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
		 		<tr>
		    		<td align="center">
			  			<a href="javascript:void(0)" class="btn" onclick="subSystemEditComponent.editSubmit();">确认提交</a>
			  			<a href="javascript:void(0)" class="btn" onclick="document.editForm.reset();">清除重填</a>
					</td>
		  		</tr>
        		</table>
       	 		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
  
  
  
</body>
</html>
