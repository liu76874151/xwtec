<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../taglibs.jsp" %>
<html>
<head>

    <title>查看广告信息</title>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/adv/adv_edit.js"></script>
</head>
<body style="overflow: auto;">
 <input type="hidden" id="hidAdvNum" value="${param.advNum}">   
 <input type="hidden" id="hidUseState" value="${param.useState}">   
 <input type="hidden" id="hidChannelNum" value="${param.channelNum}">   
 <input type="hidden" id="hidAuditState" value="${param.auditState}">   
 <input type="hidden" id="hidPositionNum" value="${param.positionNum}"> 
   
<div id="main_div" style="padding:10px;height: 1200px;">
    <div class="breadcrumb"><span></span>广告信息--查看</div>
        	<form name="editForm" id="editForm" action="${contextPath}/actionDispatcher.do?reqUrl=advInfo&reqMethod=filesuploadReName" method="post" 
        	target="hidIframe" enctype="multipart/form-data">
        	<input type="hidden" name="retobj" id="retobj">
			<table class="tb" style="position:relative;" id="editTab">
					<tr>
	 					<th align="right">渠道：</th>
			      		<td  class="form_table_content2">
				      	 	<select name="channelNum" id="channelNum" disabled >
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
			      		</td>
			    	</tr>
				<tr>
	 					<th align="right">位置：</th>
			      		<td  class="form_table_content2">
				      	 	<select name="positionNum" id="positionNum" disabled >
	                    	<option value="">--请选择--</option>
	                    	</select>
			      		</td>
			    	</tr>
			    	<!--  
			    	<tr>
	 	  			<th align="right">运行状态：</th>
					<td class="form_table_content2">
					<input type="radio"  maxlength="50" name="useState" id="useState" value=""  checked="checked"/>&nbsp;全部
					<input type="radio"  maxlength="50" name="useState" id="useState1" value="1" />&nbsp;启用
					<input type="radio"  maxlength="50" name="useState" id="useState0" value="0" />&nbsp;停用
					</td>
	 			</tr>
			    	<tr>
			
	 	  			<th align="right" >广告名称：</th>
					<td class="form_table_content2">	
					 <input type="text" name="advName" id="advName"   class="form_input" maxlength="50" onchange="component.addOption()" />	
					 &nbsp;	&nbsp;
					 <a href="javascript:void(0)" class="btn" onClick="">查询</a>
			      							
					</td>
	 			</tr>
			    	 -->
			    	
			    	 <tr class="showImgs">
	 	  			<th align="right">展示位置：</th>
					<td class="form_table_content2">
					<table>
						<tr>
							<td>
								<select size="8"  name="showXh" id='showXh' style="width: 240px" disabled>

								</select>
							</td>
							<td>
								<input type="button" value="上移" id="btnMoveUp" disabled><br>
								<input type="button" value="下移" id="btnMoveDown" disabled>
							</td>
						</tr>
					</table>
					
					</td>
	 			</tr>
			    
			</table>
			
			</form>	
        
        
        
        
    	</div>
    	
  

<iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
</body>
</html>