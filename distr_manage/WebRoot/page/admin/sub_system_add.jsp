<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@ include file="../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
        <title>子系统管理添加页面</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css" />
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/sub_system_add.js"></script>
    	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
</head>

<body>
  <div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb">
  			<span></span>子系统--添加
  			</div>
	  		<form name="addForm" id="addForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	    		<table class="tb">
  	  			<tr>
  	  				<th align="right"><span class="required">*</span>子系统编码：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="2" name="sysNum1"  id="sysNum1" /><span class="errorMsg"></span></td>
					
					<th align="right"><span class="required">*</span>子系统名称：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="20" name="sysName1" id="sysName1" /><span class="errorMsg"></span></td>
	 			</tr>
	 			<tr>
	 				<th align="right"><span class="required">*</span>子系统标题：</th>
					<td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="20" name="sysTitle1" id="sysTitle1"/><span class="errorMsg"></span></td>
	 		
	 			</tr>
	 			<tr>
	 				<th align="right"><span class="required">*</span>子系统URL：</th>
					<td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="2000" name="sysUri1" id="sysUri1" /><span class="errorMsg"></span></td>
	 		
	 			</tr>
	 			<tr>
	 				<th align="right"><span class="required">*</span>序号：</th>
					<td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="5" name="xh1" id="xh1"/><span class="errorMsg"></span></td>
	 			
	 			</tr>
	 			
	 			<tr>
  	   				<th align="right">备注：</th>
	   				<td class="form_table_content" colspan="3" ><textarea class="form_text" name="bz" id="bz1"></textarea><span class="errorMsg"></span></td>
  	 			</tr>
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
	  			<tr>
				    <td align="center">	  
					  <a href="javascript:void(0)" class="btn" onclick="addSubmit();">确认提交</a>
					  <a href="javascript:void(0)" class="btn" onclick="document.addForm.reset();">清除重填</a>
					</td>
	  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
</body>
</html>
