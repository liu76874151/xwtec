<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../../taglibs.jsp" %>
<html>
	<head>
    	<title>新增短地址</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
       <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
    
    <script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar_start.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/busiShortAdd/busiShortAdd_add.js"></script>

      </head>
  	<body>
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb"><span></span>短地址信息--新增</div>
	  		<form name="addForm" id="addForm"  method="post" >
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb" border=1>
  	  			<tr>
  	  				<th align="right" width="20%" >渠道编码：</th>
					<td class="form_table_content" >
					<select name="channelNum" id="channelNum">
	                    	<option value="">--请选择--</option>
	                    	<option value="01">网厅</option>
	                    	    <optgroup label="掌厅" title="掌厅">
							        <option value="0201">掌厅普版</option>
							        <option value="0202">掌厅标准版</option>
							        <option value="0203">掌厅触屏版</option>
							        <option value="0204">掌厅APP</option>
							    </optgroup>
	                    	<option value="03">短厅</option>
	                    	</select>
						<span class="errorMsg"></span></td>
					</tr>
					<tr>
  	  				<th align="right" width="20%">短地址渠道标识：</th>
					<td class="form_table_content" >
						<select name="shortChannel" id="shortChannel"  onchange="component.initBusiNum(this)">
	                    	<option value="">--请选择--</option>
	                    	
	                    	</select>
						<span class="errorMsg"></span></td>
					</tr>
  	  			<tr>
  	  				<th align="right" width="20%">业务编码：</th>
					<td class="form_table_content" >
						<select name="busiNum" id="busiNum" style="width: 160px">
	                    	<option value="">--请选择--</option>
	                    	
	                    	</select>
						<span class="errorMsg"></span></td>
					</tr>
					
					<tr>
	 	  			<th align="right" >业务短地址标识：</th>
					<td class="form_table_content2">	
					 <input type="text" name="shortBusi" id="shortBusi"   class="form_input" maxlength="50" />	
					 <span class="errorMsg"></span>						
					</td>
	 			</tr>
	 			<tr>
	 			<th align="right" width="20%">运行状态：</th>
					<td class="form_table_content" >
						<input type="radio" name="state" checked="checked" value="0"> 启用
						<input type="radio" name="state" value="1"> 停用
					</td>
  	 			</tr>
  	 			<tr>
	 			<th align="right" width="20%">生效开始时间：</th>
					<td class="form_table_content" >
						<input type="text" name="startTime" id="startTime" class=""  ><span class="errorMsg"></span>
					</td>
  	 			</tr>
  	 			<tr>
	 			<th align="right" width="20%">生效结束时间：</th>
					<td class="form_table_content" >
						<input type="text" name="endTime" id="endTime" class=""  ><span class="errorMsg"></span>
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