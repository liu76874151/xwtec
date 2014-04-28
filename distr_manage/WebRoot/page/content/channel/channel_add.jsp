<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>

    <title>新增频道</title>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/content/channel/channel_add.js"></script>

</head>
<body style="overflow: auto;">
   <input type="hidden" id="hiddenChanId" value="${param.chanId}">
   <input type="hidden" id="hiddenParentId" value="${param.parentId}">
   <input type="hidden" id="hiddenChannelNum" value="${param.channelNum}"> 
   <input type="hidden" id="hiddenType" value="${param.type}"> 
<div id="main_div" style="padding:10px;overflow:auto;">

    <div class="breadcrumb"><span></span>频道--新增</div>
 

      <form name="addForm" id="addForm"  action="${contextPath}/actionDispatcher.do?reqUrl=content&reqMethod=filesuploadReName" method="post" 
        	target="hidIframe" enctype="multipart/form-data">
        	 
			   <input type="hidden" id="chanId" name="chanId">	
			   <input type="hidden" id="fileName" name="fileName">	
			   		
			
			<table class="tb" style="position:relative;" id="addTab">
		
			<tr>
	 	  			<th align="right" >专区名称：</th>
					<td class="form_table_content2">	
					 <input type="text" name="chanName" id="chanName" class="form_input" maxlength="50"  />							
					</td>
	 			</tr>
	 			<tr>
	 	  
				
	 	  			<th align="right" >专区显示名称：</th>
					<td class="form_table_content2" ><input type="text" class="form_input" maxlength="50" name="showName"  id="showName" onchange="component.addOption(this)"/></td>
	 			</tr>
	 		<tr>
	 	  			<th align="right" >父专区：</th>
					<td class="form_table_content2">	
					<select name="parentId" id="parentId" onchange="component.querySubChannel()"> <option value="-1">--请选择--</option></select>
					</td>
	 			</tr>
	 			<tr>
	 					<th align="right">渠道：</th>
			      		<td  class="form_table_content2">
				      	 	<select name="channelNum" id="channelNum">
	                    	<option value="">--请选择--</option>
	                    	<option value="01">网厅</option>
							<option value="02">掌厅</option>
	                    	<option value="03">短厅</option>
	                    	</select>
			      		</td>
			    	</tr>
  	 			<tr>
	 	  			<th align="right">专区类型：</th>
					<td class="form_table_content2">
					<input type="radio"  maxlength="50" name="type" id="type1" value="1" />&nbsp;普通
					<input type="radio"  maxlength="50" name="type" id="type2" value="2" />&nbsp;下载
					<input type="radio"  maxlength="50" name="type" id="type3"  value="3" />&nbsp;链接
					<input type="radio"  maxlength="50" name="type" id="type4" value="4" />&nbsp;业务
					</td>
	 			</tr>
  	 	
	 			<tr class="TMP">
	 	  			<th align="right">概览页面模板（标准版）：</th>
					<td class="form_table_content2">
					<select id="overviewTmp" >
					<option value="">--请选择--</option>
					
					</select>
					</td>
	 			</tr>
	 			<tr class="TMP">
	 	  			<th align="right">细览页面模板（标准版）：</th>
					<td class="form_table_content2">
					<select id="detailTmp"><option value="">--请选择--</option></select>
					</td>
	 			</tr>
	 			<tr class="TMP">
	 	  			<th align="right">概览页面模板（触屏版）：</th>
					<td class="form_table_content2">
					<select id="overviewTmp2" ><option value="">--请选择--</option></select>
					</td>
	 			</tr>
	 			<tr class="TMP">
	 	  			<th align="right">细览页面模板（触屏版）：</th>
					<td class="form_table_content2">
					<select id="detailTmp2" ><option value="">--请选择--</option></select>
					</td>
	 			</tr>
	 			<tr >
	 	  			<th align="right">存放位置：</th>
					<td class="form_table_content2">
					 <input type="text" name="depository" id="depository" class="form_input" maxlength="50"  />							
					</td>
	 			</tr>
	 			
	 	<tr>
	 	  			<th align="right">展示位置：</th>
					<td class="form_table_content2">
					<table>
						<tr>
							<td>
								<select size="8"  name="sortNum" id='sortNum' style="width: 120px" >

								</select>
							</td>
							<td>
								<input type="button" value="上移" id="btnMoveUp" ><br>
								<input type="button" value="下移" id="btnMoveDown">
							</td>
						</tr>
					</table>
					
					</td>
	 			</tr>
	 		
	 			<tr>
	 	  			<th align="right">专区描述：</th>
					<td class="form_table_content2">
					<textarea rows="3" cols="50" class=""  name="desc"  id="desc"></textarea>
					</td>
	 			</tr>
	 			
	 			
	 
	 			<tr>
  	   				<th align="right" >附件上传：</th>
  	   				
	   				<td class="form_table_content2" > &nbsp;
						<input type="file" name="files" id="logo" class="qinggoudan_input021" maxlength="50">
			       <span class="errorMsg"></span></td>
  	 			</tr>
			</table>
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


<iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
</body>
</html>