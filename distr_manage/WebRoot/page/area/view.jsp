<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>地市信息维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/area/view.js"></script>
</head>

<body>
<input type="hidden" id="pkid" value="${param.pkid}">
<div style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>地市信息--查看</div>
    <!-- 展示信息内容区域开始 -->
    <table width="96%" cellspacing="1" class="form_table">
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
				<th align="right">地市名称：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="areaName" value="" id="areaName" /><span class="errorMsg"></span> </td>
	 			
				</tr>	
	    		<tr>
  	  				<th align="right">地市级别编码：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="areaJbNum" value="" id="areaJbNum" />
					<span class="errorMsg"></span></td>
					</tr>
				<tr>
  	  				<th align="right">地市编码：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="areaNum" value="" id="areaNum" readonly="readonly"/>
					<span class="errorMsg"></span></td>
					</tr>
				<tr>
				
  	  				<th align="right">地市BOSS编码：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="areaBossCode" value="" id="areaBossCode" />
					<span class="errorMsg"></span></td>
					</tr>
				
	 			
	 			
	 			<tr>
  	   				<th align="right">备注：</th>
	   				<td class="form_table_content2" ><textarea name="areaBz" id="areaBz" rows="5" cols="40" style="resize:none"></textarea></td>
  	 			</tr>
            
        
    </table>
    <!--详细信息区 结束-->
</div>
</body>
</html>