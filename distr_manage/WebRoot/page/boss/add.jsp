<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<html>
	<head>
    	<title>boss编码新增</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   	 	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
   		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
    	<script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.addMethod.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>	
        <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/boss/add.js"></script>
    </head>
    
  	<body style="overflow: auto;">
   		
	<div id="main_div" style="padding:10px;">
  			<div class="breadcrumb"><span></span>一级boss编码--新增</div>
  			
	  		<form name="addForm" id="addForm" action="" method="post">
	    		<!-- 展示信息内容区域开始  class="defaultTable tableStyle1"-->
	    		<table width="98%" class="tb">
	    			<tr>
  	  				<th align="right">boss编码：</th>
					<td class="">
					<input type="text" class="form_input" maxlength="10" name="marketFirstCode" value="" id="marketFirstCode" />
					
					<span class="errorMsg"></span></td>
					<th align="right">boss名称：</th>
					<td class="">
					<input type="text" class="form_input" maxlength="10" name="marketFirstName" value="" id="marketFirstName" />
					
					<span class="errorMsg"></span></td>
					
					</tr>
					<tr>
					<th align="right">开始时间：</th>
					<td class="">
					<input type="text" class="form_input" maxlength="10" name="startTimeFirst" value="" id="startTimeFirst" />
					
					<span class="errorMsg"></span></td>
					<th align="right">结束时间：</th>
					<td class="">
					<input type="text" class="form_input" maxlength="10" name="endTimeFirst" value="" id="endTimeFirst" />
					
					<span class="errorMsg"></span></td>
					</tr>
					
	    			<tr>
					<th align="right">营销案类型：</th>
					<td class="">
					<input type="radio" name="type" value="0" id="type_0" checked="checked" /><label for="type_0">一般营销案 </label>
               		<input type="radio"  name="type" value="1" id="type_1"/><label for="type_1">宽带营销案 </label>
					<span class="errorMsg"></span></td>
					<th align="right">地市：</th>
					<td class="">
					<select name="cityId" id="cityId"><option value="">--请选择--</option></select>
					<span class="errorMsg"></span></td>
					</tr>
  	  			
        		</table>
        		<br/>
        		<div class="breadcrumb"><span></span>二级boss编码--新增&nbsp;&nbsp;&nbsp;<input type="button" value="添加" onclick="component.addMarketSecond()"/></div>
        		<div id="marketSecondDiv">
        		
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