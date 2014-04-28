<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../../taglibs.jsp" %>
<html>
	<head>
    	<title>模板维护</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <%@page import="com.xwtech.uomp.base.pojo.sso.LoginRequestBean"%>
		<%@page import="com.xwtech.uomp.base.util.SSOUtil"%>
		<%@page import="com.xwtech.uomp.base.pojo.admin.UserInfoBean"%>
        <script type="text/javascript" src="${contextPath}/resource/scripts/CKEditor/ckeditor.js"></script>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.addMethod.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/marketTemplate/template_add.js"></script>
    </head>
     <%
	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean,request).getUserInfoBean();
	String city = userInfoBean.getUserAreaCode();
	pageContext.setAttribute("userCityCode",city);
	%>
  	<body>
  	<input type="hidden" id="category" value="1">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb"><span></span>模板信息--新增</div>
  			 <input type="hidden" id="userCityCode" value="${pageScope.userCityCode}" >
	  		<form name="addForm" id="addForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb">
  	  			<tr>
  	  				<th align="right"><span style="color:red">&nbsp;*&nbsp;</span>模板名称：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="100" name="templateName" value="" id="templateName" />
					
					<span class="errorMsg"></span></td>
						</tr>
							<tr>
					<th align="right">地市：</th>
					<td class="form_table_content2" id="city">
					 </td>
	 			</tr>

	 			<tr>
	 			<th align="right">是否有效：</th>
					<td class="form_table_content2">
					<input type="radio" name="state" value="1" checked="checked"/>有效
					<input type="radio" name="state"  value="0"/>无效
					 </td>
					 </tr>
					<!--  <tr>
  	   				<th align="right">渠道：</th>
	   				<td class="form_table_content2" >
					<input type="radio" name="channalData" value="4" checked="checked"/>网厅
					<input type="radio" name="channalData" value="5"/>掌厅
					<input type="radio" name="channalData" value="6"/>短厅
                      </td>
  	 			</tr> -->
	 			
	 			<tr>
			<th  align="right">模板内容 ：</th>
			<td >
			<textarea rows="8" cols="60" class=""  name="content"  id="content"></textarea>
			<!-- <div id="editorObj" style="width: 100%; height: 300px; border: #909090 1px solid;"></div> --> 
			</td>
			</tr>
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
	  			<tr>
				    <td align="center">
						<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					  	<a href="javascript:void(0)" class="btn" onClick="document.addForm.reset();">清除重填</a>
					</td>
	  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
  	</body>
</html>