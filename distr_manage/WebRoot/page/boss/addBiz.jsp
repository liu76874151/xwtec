<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<html>
	<head>
    	<title>业务编码新增</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   	 	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
    	<script src="<%= request.getContextPath() %>/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resource/css/frame.css"/>
        <script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/jquery.validate.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/jquery.validate.addMethod.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/main.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/jquery.json-2.4.min.js"></script>	
        <script type="text/javascript" src="$<%= request.getContextPath() %>/resource/scripts/jquery.validate.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/uomp/boss/addBiz.js"></script>
    </head>
    
  	<body style="overflow: auto;">
   	<input type="hidden" id="tbId" name="tbId" value="<%=request.getParameter("id") %>">	
   	<input type="hidden" id="cityId" name="cityId" value="<%=request.getParameter("cityId") %>">	
   	<input type="hidden" id="marketSecondCode" name="marketSecondCode" value="<%=request.getParameter("marketSecondCode") %>">	
	<div id="main_div" style="padding:10px;">
  			<div class="breadcrumb"><span></span>一级业务编码--新增</div>
  			
	  		<form name="addForm" id="addForm" action="" method="post">
	    		<!-- 展示信息内容区域开始  class="defaultTable tableStyle1"-->
	    		<table width="98%" class="tb">
	    			<tr>
  	  				<th align="right">业务编码：</th>
					<td class="">
					<input type="text" class="form_input" maxlength="10" name="bizCode" value="" id="bizCode" />
					
					<span class="errorMsg"></span></td>
					<th align="right">业务名称：</th>
					<td class="">
					<input type="text" class="form_input" maxlength="10" name="bizName" value="" id="bizName" />
					
					<span class="errorMsg"></span></td>
					
					</tr>
					
  	  			
        		</table>
        		<br/>
        		<div class="breadcrumb"><span></span>二级业务编码--新增&nbsp;&nbsp;&nbsp;<input type="button" value="添加" onclick="component.addBizSecond()"/></div>
        		<div id="bizSecondDiv">
        		</div>
        		
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