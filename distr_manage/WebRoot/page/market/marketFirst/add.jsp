<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>

    <title>新增一级营销方案</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/dhtmlxcombo.css"></link>
     <script type="text/javascript" src="${contextPath}/resource/scripts/CKEditor/ckeditor.js"></script>
       
    
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/dhtmlxcombo.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/ext/dhtmlxcombo_whp.js"></script> 
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.addMethod.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/market/marketFirst/add.js"></script>

</head>
<body style="overflow: auto;">

	<div id="main_div" style="padding:10px;overflow:auto;">
 <div class="breadcrumb" ><span></span>一级营销案--新增</div>
 
     	<form name="addForm" id="addForm" action="${contextPath}/actionDispatcher.do?reqUrl=marketFirst&reqMethod=filesUpload" method="post" 
            target="hidIframe" enctype="multipart/form-data">
			   <input type="hidden" id="isSJ" value="${param.isSJ}">
			   <input type="hidden" name="marketFirstPkid"  id="marketFirstPkid" value="">
			   <input type="hidden" name="yxImgDirName" id="yxImgDirName"  value="">
			<table class="tb" style="position:relative;" >
			<tr>
	 	  			<th  align="right" width="15%">渠道：</th>
					<td class="form_table_content" >
					<input type="checkbox"  maxlength="50" name="channalData" value="4" id="channalData_4" />&nbsp;<label for="channalData_4">网厅 </label> 
					<input type="checkbox"  maxlength="50" name="channalData" value="5" id="channalData_5" checked="checked"  />&nbsp;<label for="channalData_5">掌厅 </label> 
					<input type="checkbox"  maxlength="50" name="channalData" value="6" id="channalData_6" />&nbsp;<label for="channalData_6">短厅 </label> 
					</td>
	 			</tr>
			<tr >
	 	  			<th align="right" ><span style="color:red">*&nbsp;</span>BOSS一级方案名称：</th>
					<td class="form_table_content2" id="bossCodeDiv">*输入boss编码快速选择*
					 <div id="marketFirstNameSel" style="width:200px; height:30px; "></div>
					</td>
	 			</tr>
				<tr>
	 	  			<th align="right" ><span style="color:red">*&nbsp;</span>一级方案名称：</th>
					<td class="form_table_content" >
					<input type="text" class="form_input" maxlength="100" name="viewName"  id="viewName" onblur="addOption(this)"/>
					<span class="errorMsg"></span>
					</td>
	 			</tr>
	 		
  	 			<tr>
  	   				<th align="right" >一级营销案图片 ：</th>
  	   				
	   				<td class="form_table_content" > &nbsp;
						<input type="file" name="file" id="yxImgDir" class="qinggoudan_input021">
						
						&nbsp;&nbsp;&nbsp;&nbsp;合适尺寸：240X55&nbsp;&nbsp;大小：&lt;10K<span class="errorMsg"></span></td>
  	 			</tr>
  	 	
	 			<tr>
	 	  			<th align="right">一级营销方案活动对象：</th>
					<td class="form_table_content">
					<input type="text" class="form_input" maxlength="100" name="toObject" id="toObject" />
					<span class="errorMsg"></span>
					</td>
	 			</tr>
	 			<!--<tr>
	 	  			<th align="right">奖品：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="prize" id="prize" /></td>
	 			</tr>  -->
	 		<tr>
	 	  			<th align="right">品牌：</th>
					<td class="form_table_content">
					<input type="checkbox"  maxlength="50" name="isInBrand" value="QQT" id="QQT" checked="checked"/>&nbsp; <label for="QQT">全球通</label> 
					<input type="checkbox"  maxlength="50" name="isInBrand" value="DGDD" id="DGDD" checked="checked"/>&nbsp;<label for="DGDD">动感地带</label> 
					<input type="checkbox"  maxlength="50" name="isInBrand" value="SZX" id="SZX" checked="checked"/>&nbsp; <label for="SZX">神州行 </label> 
					</td>
	 			</tr>
	 			
	 		<tr>
	 	  			<th align="right">一级营销方案开始时间：</th>
					<td class="form_table_content">
						<input type="text" id="beginTime" name="beginTime" >
					</td>
	 			</tr>
	 		　　<tr>
	 	  			<th align="right">一级营销方案结束时间：</th>
					<td class="form_table_content">
						<input type="text" id="endTime" name="endTime">
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">一级营销方案说明：</th>
					<td class="form_table_content">
					<textarea rows="3" cols="50" class=""  name="activityComment"  id="activityComment"  ></textarea>
					</td>
	 			</tr>
	 	
	 			<tr>
	 	  			<th align="right">一级营销方案温馨提示：</th>
					<td class="form_table_content">
					<textarea rows="3" cols="50" class=""  name="sweetPrompt"  id="sweetPrompt"></textarea>
					</td>
	 			</tr>
	 			
	 				
	 		
	 			<tr>
	 	  			<th align="right">营销案类型：</th>
					<td class="form_table_content">
					<input type="radio"  maxlength="50" name="type"  value="0" id="type_0" checked="checked" />&nbsp;<label for="type_0">一般营销案</label> 
					<input type="radio"  maxlength="50" name="type"  value="1" id="type_1" />&nbsp;<label for="type_1">开户宽带营销案</label> 
					<input type="radio"  maxlength="50" name="type"  value="2" id="type_2" />&nbsp;<label for="type_2">续费宽带营销案</label> 
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">特殊营销案：</th>
					<td class="form_table_content">
					<input type="radio"  maxlength="50" name="specialFlag"  value="0" id="specialFlag_0" checked="checked" />&nbsp;<label for="specialFlag_0">普通营销案</label> 
					<input type="radio"  maxlength="50" name="specialFlag"  value="1" id="specialFlag_1" />&nbsp;<label for="specialFlag_1">流量升档营销案</label> 
					<input type="radio"  maxlength="50" name="specialFlag"  value="2" id="specialFlag_2" />&nbsp;<label for="specialFlag_2">流量经营营销案</label> 
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">专区展示：</th>
					<td class="form_table_content">
					<input type="checkbox"  maxlength="50" name="ztMarketShowChannel" value="a" id="a" checked="checked"/>&nbsp; <label for="a">优惠活动专区</label> 
					<input type="checkbox"  maxlength="50" name="ztMarketShowChannel" value="b" id="b" />&nbsp;<label for="b">掌厅首页</label> 
					</td>
	 			</tr>
		</table>
	
			</form>	
        
        
        <br>
          <div id="tab" class="dhtmlxTabBar"  style="width:100%; " imgpath="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/" oninit="doOnInit()" >   
        <div id="tab_1" name="网厅" >
        <table class="tb"  >
	 		   <tr>
	 	  			<th  align="right" width="12%">网厅渠道选择：</th>
					<td class="form_table_content" >
					<input type="checkbox"  maxlength="50" name="tChannal" value="1" id="tChannal_1"/>&nbsp;<label for="tChannal_1">网厅 </label> 
					<input type="checkbox"  maxlength="50" name="tChannal" value="2" id="tChannal_2"/>&nbsp;<label for="tChannal_2">网村组服站 </label> 
					<input type="checkbox"  maxlength="50" name="tChannal" value="3"  id="tChannal_3"/>&nbsp;<label for="tChannal_3">校园e站 </label> 
					</td>
	 			</tr>

				<tr>
					<th  align="right">网厅模版类型：</th>
					<td class="form_table_content">
						<select id="templateName">
							<option value="">--请选择--</option>
						</select>
				</td>			
			</tr>
			<tr>
			<th  align="right">模板内容 ：</th>
			<td >
			<textarea rows="8" cols="60" class=""  name="marketContent"  id="marketContent" ></textarea>
			<!-- <div id="editorObj" style="width: 100%; height: 300px; border: #909090 1px solid;"></div> --> 
			</td>
			</tr>	
				<tr>
	 	  			<th align="right">展示位置：</th>
					<td class="form_table_content">
					<div id="marketOrderDiv">
					<table>
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
    			<input type="radio"  maxlength="50" name="allProSignFlag"  checked="checked" value="0" id="allProSign_0"  onchange="component.addSignStr(false)"/>&nbsp;<label for="allProSign_0">否</label> 
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
					<input type="text"  name="instructionContent" value="10086;CXTCLL;" id="instructionContent"/> 
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
			
	   
        	<table width="100%" height="40">
				<tr>
			    	<td align="center" >
					<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					<a href="javascript:void(0)" class="btn" onClick="document.addForm.reset();">清除重填</a>
					</td>
				</tr>
	   		</table>
	   				<!-- 按钮操作区域结束 -->
</div>  
  <iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
</body>
</html>