<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>

    <title>查看一级营销方案</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
     <script type="text/javascript" src="${contextPath}/resource/scripts/CKEditor/ckeditor.js"></script>
       
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.addMethod.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/market/marketFirst/edit.js"></script>

</head>
<body style="overflow: auto;">
 <input type="hidden" id="pkid" value="${param.pkid}"> 
 <input type="hidden" id="isSJ" value="${param.isSJ}"> 
 <input type="hidden" id="unityFlag" value="${param.unityFlag}"> 
 <input type="hidden" id="cityId" value="${param.cityId}"> 
 <input type="hidden" id="isView" value="${param.isView}"> 
 <input type="hidden" id="marketFirstCode" />  
 <input type="hidden" id="marketFirstName" />  
<div id="main_div" style="padding:10px;">

    <div class="breadcrumb" id="pageTitle"><span></span>一级营销案--查看</div>
 
       
        	<form name="editForm" id="editForm" action="${contextPath}/actionDispatcher.do?reqUrl=marketFirst&reqMethod=filesuploadReName" method="post" 
            target="hidIframe" enctype="multipart/form-data">
            <input type="hidden" id="marketFirstPkid" name="marketFirstPkid" />  
			<table class="tb" style="position:relative;">
				<tr>
	 	  			<th align="right" ><span style="color:red">*&nbsp;</span>BOSS一级方案名称：</th>
					<td class="form_table_content2">	
					<div id="bossCodeDiv">	
					 <input type="hidden" name="marketFirstName2" id="marketFirstName2" class="form_input" maxlength="50"  />							
					  <br/><select name="marketFirstNameSel" id="marketFirstNameSel" disabled="disabled"> </select>
					 </div>
					</td>
	 			</tr>
				<tr>
	 	  			<th align="right" ><span style="color:red">*&nbsp;</span>网营一级方案名称：</th>
					<td class="form_table_content2" >
					<input type="text" class="form_input" disabled="disabled" name="viewName"  id="viewName" />
					<span class="errorMsg"></span>
					</td>
	 			</tr>
	 		
  	 			<tr>
  	   				<th align="right" >一级营销案图片 ：</th>
  	   				
	   				<td class="form_table_content2" > &nbsp;
						<input type="hidden"  id="yxImgDir_old" name="yxImgDir_old">
						&nbsp;&nbsp;&nbsp;&nbsp;合适尺寸：240X55&nbsp;&nbsp;大小：&lt;10K<span class="errorMsg"></span>
						<br>
						<img alt="" style="display:none; width: 240;height: 55" name="yxImg" id="yxImg"  src="">
						
						</td>
  	 			</tr>
  	 	
	 			<tr>
	 	  			<th align="right">一级营销方案活动对象：</th>
					<td class="form_table_content2"><input type="text" class="form_input" disabled="disabled" name="toObject" id="toObject" /></td>
	 			</tr>
	 			<!--  <tr style="visibility: hidden;">
	 	  			<th align="right">奖品：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="prize" id="prize" /></td>
	 			</tr>-->
	 		
	 			<tr>
	 	  			<th align="right">品牌：</th>
					<td class="form_table_content2">
					<input type="checkbox"  disabled="disabled"  name="isInBrand" id="isInBrand0" value="QQT" />&nbsp;<label for="isInBrand0">全球通 </label> 
					<input type="checkbox"  disabled="disabled"  name="isInBrand" id="isInBrand1"  value="DGDD" />&nbsp;<label for="isInBrand1">动感地带 </label> 
					<input type="checkbox"  disabled="disabled"  name="isInBrand" id="isInBrand2"  value="SZY" />&nbsp; <label for="isInBrand2">神州行</label> 
					</td>
	 			</tr>
	 		
	 			<tr>
	 	  			<th align="right">一级营销方案说明：</th>
					<td class="form_table_content2">
					<textarea rows="3" cols="50" class=""  name="activityComment" disabled="disabled"  id="activityComment"></textarea>
					</td>
	 			</tr>
	 	
	 			<tr>
	 	  			<th align="right">一级营销方案温馨提示：</th>
					<td class="form_table_content2">
					<textarea rows="3" cols="50" class=""  name="sweetPrompt"  disabled="disabled"  id="sweetPrompt"></textarea>
					</td>
	 			</tr>
	 			
	 				<tr>
	 	  			<th align="right">一级营销方案开始时间：</th>
					<td class="form_table_content2">
						<input type="text" id="beginTime" name="beginTime" disabled="disabled" >
					</td>
	 			</tr>
	 		　　<tr>
	 	  			<th align="right">一级营销方案结束时间：</th>
					<td class="form_table_content2">
						<input type="text" id="endTime" name="endTime" disabled="disabled" >
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">营销案类型：</th>
					<td class="form_table_content2">
					<input type="radio"  disabled="disabled"  name="type" id="type0" value="0" />&nbsp;<label for="type0">一般营销案</label> 
					<input type="radio"  disabled="disabled"  name="type" id="type1"  value="1" />&nbsp;<label for="type1">开户宽带营销案</label> 
					<input type="radio"  disabled="disabled"  name="type" id="type2"  value="2" />&nbsp;<label for="type2">续费宽带营销案</label> 
					</td>
	 			</tr>
	 			<tr>
	 	  			<th  align="right">渠道：</th>
					<td class="form_table_content2" >
					<input type="checkbox"  disabled="disabled"  name="channalData" value="4" id="channalData_4"  disabled="disabled"/>&nbsp;<label for="channalData_4">网厅</label> 
					<input type="checkbox"  disabled="disabled"  name="channalData" value="5" id="channalData_5" disabled/>&nbsp;<label for="channalData_5">掌厅</label> 
					<input type="checkbox"  disabled="disabled"  name="channalData" value="6" id="channalData_6" disabled/>&nbsp;<label for="channalData_6">短厅</label> 
					</td>
	 			</tr>
			</table>
			
			<br>
          <div id="tab" class="dhtmlxTabBar"  style="width:100%;" imgpath="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/" oninit="doOnInit()" >   
        <div id="tab_1" name="网厅" >
        <table class="tb"  >
	 		   <tr>
	 	  			<th  align="right">网厅渠道选择：</th>
					<td class="form_table_content2" >
					<input type="checkbox"  disabled="disabled"  name="tChannal" id="tChannal3" value="1" />&nbsp;<label for="tChannal3">网厅</label> 
					<input type="checkbox"  disabled="disabled"  name="tChannal" id="tChannal2"  value="2" />&nbsp;<label for="tChannal2">网村组服站</label> 
					<input type="checkbox"  disabled="disabled"  name="tChannal" id="tChannal1" value="3" />&nbsp;<label for="tChannal1">校园e站</label> 
					</td>
	 			</tr>

				<tr>
					<th  align="right">网厅模版类型：</th>
					<td class="form_table_content2">
						<select id="templateName" onchange=" component.templateNameOnchange(this);">
							<option value="">--请选择--</option>
						
						</select>
				</td>			
			</tr>
			<tr>
			<th  align="right">网厅模板内容 ：</th>
			<td >
			<textarea rows="8" cols="60" class=""  name="marketContent"  id="marketContent" disabled="disabled" ></textarea>
			<!-- <div id="editorObj" style="width: 100%; height: 300px; border: #909090 1px solid;"></div> --> 
			</td>
			</tr>	
				<tr>
	 	  			<th align="right">展示位置：</th>
					<td class="form_table_content2">
					<div id="marketOrderDiv">
					<table  id="marketOrderTab">
						<tr>
							<td>
								<select size="8"  name="marketOrder" id="marketOrder"  style="width: 160px ;height:140px" disabled="disabled">
								</select>
							</td>
							<td>
								<input type="button" value="上移" id="btnMoveUp" ><br>
								<input type="button" value="下移" id="btnMoveDown" >
							</td>
						</tr>
					</table>
					</div>
					</td>
	 			</tr>			
	   		</table>
    	</div>
    	<!-- <div id="tab_2" name="掌厅"></div>-->
        <div id="tab_3" name="短厅">
         <table class="tb"  >
         <tr>
         <th  align="right">指令内容：</th>
					<td class="form_table_content2" >
					<input type="text"  name="instructionContent" value="" id="instructionContent" disabled="disabled"/> 
					</td>
         </tr>
           <tr>
         <th  align="right">扩展码：</th>
					<td class="form_table_content2" >
					<input type="text"  name="spreadCode" value="" id="spreadCode" disabled="disabled"/> 
					</td>
         </tr>
         </table>
        
        </div>
        
    </div>
    
			<!-- 按钮操作区域开始 
			<table width="100%" height="40">
				<tr>
			    	<td align="center">
					<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					</td>
				</tr>
	   		</table>
		-->
			
		
	   		<!-- 按钮操作区域结束 -->
			</form>	
        
        
        
        
    	</div>
    	
  
  <iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>

</body>
</html>