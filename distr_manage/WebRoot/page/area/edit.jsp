<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<html>
	<head>
    	<title>用户组维护</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <%@page import="com.xwtech.uomp.base.pojo.sso.LoginRequestBean"%>
		<%@page import="com.xwtech.uomp.base.util.SSOUtil"%>
		<%@page import="com.xwtech.uomp.base.pojo.admin.UserInfoBean"%>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
         <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.addMethod.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/area/edit.js"></script>
    </head>
    <%
	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean,request).getUserInfoBean();
	String city = userInfoBean.getUserAreaCode();
	pageContext.setAttribute("userCityCode",city);
	%>
  	<body>
  	<input type="hidden" id="pkid" value="${param.pkid}">
   		<div id="main_div" style="padding:10px;overflow:auto;">
   		 <input type="hidden" id="userCityCode" value="${pageScope.userCityCode}" >
  			<div class="breadcrumb"><span></span>地市信息--修改</div>
	  		<form name="editForm" id="editForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb">
	    		<tr>
  	  				<th align="right"><span style="color:red">&nbsp;*&nbsp;</span>地市级别编码：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="10" name="areaJbNum" value="" id="areaJbNum" />
					
					<span class="errorMsg"></span></td>
					</tr>
				
	    		<tr>
  	  				<th align="right"><span style="color:red">&nbsp;*&nbsp;</span>地市编码：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="10" name="areaNum" value="" id="areaNum" />
					
					<span class="errorMsg"></span></td>
					</tr>
				
	    		<tr>
	    		<th align="right">级别：</th>
					<td class="form_table_content2">
					<input type="radio" value="1" name="areaJb" id="areaJb_1" checked="checked"><label  for="areaJb_1"> 省级</label>
					<input type="radio" value="2" name="areaJb" id="areaJb_2"><label  for="areaJb_2"> 地区</label>
					<input type="radio" value="3" name="areaJb" id="areaJb_3"><label  for="areaJb_3"> 市</label>
					<span class="errorMsg"></span>
					</td>
	    		</tr>
	    		<tr>
				<th align="right"><span style="color:red">&nbsp;*&nbsp;</span>地市名称：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="200" name="areaName" value="" id="areaName" />
					
					<span class="errorMsg"></span> </td>
	 			
				</tr>	
	    		
				
	 			
	 			
	 			<tr>
  	   				<th align="right">备注：</th>
	   				<td class="form_table_content2" ><textarea name="areaBz" id="areaBz" rows="5" cols="40" style="resize:none"></textarea></td>
  	 			</tr>
  	 		
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
	  			<tr>
				    <td align="center">
						<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					  	<!-- <a href="javascript:void(0)" class="btn" onClick="document.editForm.reset();">清除重填</a> -->
					</td>
	  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
  	</body>
</html>