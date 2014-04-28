<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<html>
	<head>
    	<title>用户组维护</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
  	</head>
  	<body>
   		<div id="main_div" style="padding:10px;overflow:auto;">
 			<div class="breadcrumb"><span></span>模块信息--修改</div>
			<form name="editForm" id="editForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	  			<table width="98%" class="tb">
	    		<tr>
	      			<th align="right">功能ID：</th>
		  			<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="funcId" value="111111111" readonly /></td>
		  			<th align="right">功能名称：</th>
		  			<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="funcName" value="11111111111" /></td>
				</tr>
				<tr>
		  			<th align="right">功能实现RUI：</th>
					<td class="form_table_content" colspan="3">
						<input type="text" class="form_input" maxlength="100" name="funcUri" value="11111111" />
					</td>
		  		</tr>
		 		<tr>
	  	   			<th align="right">备注：</th>
		   			<td class="form_table_content" colspan="3" >
		   				<textarea class="form_text" name="bz" id="bz">1111111111</textarea>
		   			</td>
	  	 		</tr>
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
		 		<tr>
		    		<td align="center">
			  			<a href="javascript:void(0)" class="btn" onclick="editSubmit();">确认提交</a>
			  			<a href="javascript:void(0)" class="btn" onclick="document.editForm.reset();">清除重填</a>
					</td>
		  		</tr>
        		</table>
       	 		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
	</body>
</html>