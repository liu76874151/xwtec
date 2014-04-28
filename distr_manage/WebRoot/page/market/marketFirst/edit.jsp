<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>

    <title>修改一级营销方案</title>
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
 <input type="hidden" id="marketFirstCode"  value="${param.marketFirstCode}"/>  
  <input type="hidden" id="isViewPage" value="${param.isView}"> 
 <input type="hidden" id="marketFirstName" />  
<div id="main_div" style="padding:10px;">

    <div class="breadcrumb" id="mypageTitle">一级营销案--修改</div>
 
       
        	<form name="editForm" id="editForm" action="${contextPath}/actionDispatcher.do?reqUrl=marketFirst&reqMethod=filesuploadReName" method="post" 
            target="hidIframe" enctype="multipart/form-data">
            <input type="hidden" id="marketFirstPkid" name="marketFirstPkid" />  
			<table class="tb" style="position:relative;">
				<tr>
	 	  			<th  align="right">渠道：</th>
					<td class="form_table_content2" >
					<input type="checkbox"  maxlength="50" name="channalData" value="4" id="channalData_4" disabled="disabled"/>&nbsp;<label for="channalData_4">网厅</label> 
					<input type="checkbox"  maxlength="50" name="channalData" value="5" id="channalData_5" disabled="disabled"/>&nbsp;<label for="channalData_5">掌厅</label> 
					<input type="checkbox"  maxlength="50" name="channalData" value="6" id="channalData_6" disabled="disabled"/>&nbsp;<label for="channalData_6">短厅</label> 
					</td>
	 			</tr>
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
					<input type="text" class="form_input" maxlength="50" name="viewName"  id="viewName" onblur="editOption(this)"/>
					<span class="errorMsg"></span>
					</td>
	 			</tr>
	 		
  	 			<tr>
  	   				<th align="right" >一级营销案图片 ：</th>
  	   				
	   				<td class="form_table_content2" > &nbsp;
						<input type="file" name="files" id="yxImgDir" class="qinggoudan_input021" maxlength="50">
						<input type="hidden"  id="yxImgDir_old" name="yxImgDir_old">
						&nbsp;&nbsp;&nbsp;&nbsp;合适尺寸：240X55&nbsp;&nbsp;大小：&lt;10K<span class="errorMsg"></span>
						<br>
						<img alt="" style="display:none; width: 240;height: 55" name="yxImg" id="yxImg"  src="">
						
						</td>
  	 			</tr>
  	 	
	 			<tr>
	 	  			<th align="right">一级营销方案活动对象：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="toObject" id="toObject" /></td>
	 			</tr>
	 			<!--  <tr style="visibility: hidden;">
	 	  			<th align="right">奖品：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="prize" id="prize" /></td>
	 			</tr>-->
	 		
	 			<tr>
	 	  			<th align="right">品牌：</th>
					<td class="form_table_content2">
					<input type="checkbox"  maxlength="50" name="isInBrand" id="isInBrand0" value="QQT" />&nbsp;<label for="isInBrand0">全球通 </label> 
					<input type="checkbox"  maxlength="50" name="isInBrand" id="isInBrand1"  value="DGDD" />&nbsp;<label for="isInBrand1">动感地带 </label> 
					<input type="checkbox"  maxlength="50" name="isInBrand" id="isInBrand2"  value="SZX" />&nbsp; <label for="isInBrand2">神州行</label> 
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">一级营销方案开始时间：</th>
					<td class="form_table_content2">
						<input type="text" id="beginTime" name="beginTime" >
					</td>
	 			</tr>
	 		　　<tr>
	 	  			<th align="right">一级营销方案结束时间：</th>
					<td class="form_table_content2">
						<input type="text" id="endTime" name="endTime" onblur="endTimeOnblur(this)">
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">一级营销方案说明：</th>
					<td class="form_table_content2">
					<textarea rows="3" cols="50" class=""  name="activityComment"  id="activityComment"></textarea>
					</td>
	 			</tr>
	 	
	 			<tr>
	 	  			<th align="right">一级营销方案温馨提示：</th>
					<td class="form_table_content2">
					<textarea rows="3" cols="50" class=""  name="sweetPrompt"  id="sweetPrompt"></textarea>
					</td>
	 			</tr>
	 			
	 				
	 			<tr>
	 	  			<th align="right">营销案类型：</th>
					<td class="form_table_content2">
					<input type="radio"  maxlength="50" name="type" id="type0" value="0" />&nbsp;<label for="type0">一般营销案</label> 
					<input type="radio"  maxlength="50" name="type" id="type1"  value="1" />&nbsp;<label for="type1">开户宽带营销案</label> 
					<input type="radio"  maxlength="50" name="type" id="type2"  value="2" />&nbsp;<label for="type2">续费宽带营销案</label> 
					</td>
	 			</tr>
	 		
	 			<tr>
	 	  			<th align="right">特殊营销案：</th>
					<td class="form_table_content2">
					<input type="radio"  maxlength="50" name="specialFlag"  value="0" id="specialFlag_0" />&nbsp;<label for="specialFlag_0">普通营销案</label> 
					<input type="radio"  maxlength="50" name="specialFlag"  value="1" id="specialFlag_1" />&nbsp;<label for="specialFlag_1">流量升档营销案</label> 
					<input type="radio"  maxlength="50" name="specialFlag"  value="2" id="specialFlag_2" />&nbsp;<label for="specialFlag_2">流量经营营销案</label> 
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">专区展示：</th>
					<td class="form_table_content">
					<input type="checkbox"  maxlength="50" name="ztMarketShowChannel" value="a" id="a" />&nbsp; <label for="a">优惠活动专区</label> 
					<input type="checkbox"  maxlength="50" name="ztMarketShowChannel" value="b" id="b" />&nbsp;<label for="b">掌厅首页</label> 
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
					<input type="checkbox"  maxlength="50" name="tChannal" id="tChannal3" value="1" />&nbsp;<label for="tChannal3">网厅</label> 
					<input type="checkbox"  maxlength="50" name="tChannal" id="tChannal2"  value="2" />&nbsp;<label for="tChannal2">网村组服站</label> 
					<input type="checkbox"  maxlength="50" name="tChannal" id="tChannal1" value="3" />&nbsp;<label for="tChannal1">校园e站</label> 
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
			<textarea rows="8" cols="60" class=""  name="marketContent"  id="marketContent"></textarea>
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
								<select size="8"  name="marketOrder" id="marketOrder"  style="width: 160px ;height:140px" >
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
    	<div id="tab_2" name="掌厅">
    	<table class="tb"  >
    	<tr>
    		<th align="right" width="11%">是否是全省营销案：</th>
    		<td class="form_table_content">
    			<input type="radio"  maxlength="50" name="allProSignFlag"   value="0" id="allProSign_0"  onchange="component.addSignStr(false)"/>&nbsp;<label for="allProSign_0">否</label> 
    			<input type="radio"  maxlength="50" name="allProSignFlag"  value="1" id="allProSign_1"  onchange="component.addSignStr(true)" />&nbsp;<label for="allProSign_1">是</label> 
    			</br>
    			<span style="color:red">注意：如果是全省营销案，请填写字母组成的营销案类型名称，长度不超过10</br>如：<strong>CZSDYP</strong>-全省类充值送电影票；<strong>CZSHF</strong>-全省类充值送话费；<strong>LLSDSHF</strong>-全省类流量升档送话费；<strong>LLJYYXA</strong>-全省类流量经营营销案；</span>
    			<div id="allProSignDiv" style="display:none">
    				全省营销案类型名称：<input type="text" name="allProSignFlag" id="allProSignFlag" />
    			</div>
    		</td>
    	</tr>
    	<tr>
	 	  			<th align="right">展示位置：</th>
					<td class="form_table_content">
					<div id="ztMarketOrderDiv">
					<table>
						<tr>
							<td>
								<select size="8"  name="ztMarketOrder" id="ztMarketOrder"  style="width: 160px ;height:140px" >
								</select>
							</td>
							<td>
								<input type="button" value="上移" id="ztBtnMoveUp" ><br>
								<input type="button" value="下移" id="ztBtnMoveDown" >
							</td>
						</tr>
					</table>
					</div>
					</td>
	 			</tr>
    	</table>
    	</div>
        <div id="tab_3" name="短厅">
         <table class="tb"  >
         <tr>
         <th  align="right">指令内容：</th>
					<td class="form_table_content2" >
					<input type="text"  name="instructionContent" value="" id="instructionContent"/> 
					</td>
         </tr>
           <tr>
         <th  align="right">扩展码：</th>
					<td class="form_table_content2" >
					<input type="text"  name="spreadCode" value="" id="spreadCode"/> 
					</td>
         </tr>
         </table>
        
        </div>
        
    </div>
    
			<!-- 按钮操作区域开始 -->
			<table width="100%" height="40" id="saveBtn">
				<tr>
			    	<td align="center">
					<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					</td>
				</tr>
	   		</table>
		
			
		
	   		<!-- 按钮操作区域结束 -->
			</form>	
        
        
        
        
    	</div>
    	
  
  <iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>

</body>
</html>