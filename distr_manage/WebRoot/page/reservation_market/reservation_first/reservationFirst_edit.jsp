<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>

    <title>修改二级营销方案</title>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/reservation_market/reservation_first/reservationFirst_edit.js"></script>

</head>
<body style="overflow: auto;">
 <input type="hidden" id="pkid" value="${param.pkid}">  
 <input type="hidden" id="marketFirstCode" />  
<div id="main_div" style="padding:10px;height: 1200px;">
    <div class="breadcrumb"><span></span>一级营销案--修改</div>
        	<form name="editForm" id="editForm"  action="${contextPath}/actionDispatcher.do?reqUrl=reservationFirst&reqMethod=fileUpload" method="post" 
            target="hidIframe" enctype="multipart/form-data">
            			<input type="hidden" name="fileName" id="fileName"  value=""/>
            			<input type="hidden" name="marketFirstPkid" id="marketFirstPkid"  value=""/>
	
			<table class="tb" style="position:relative;">
		
			<tr>
	 	  			<th align="right"  width="20%">CRM一级预约方案名称：</th>
					<td class="form_table_content">	
					 <input type="text" name="marketFirstName" id="marketFirstName" class="form_input" maxlength="50"  readonly="readonly" />							
					 </td>
	 			</tr>
				<tr>
	 	  			<th align="right" >一级预约方案名称：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="50" name="viewName"  id="viewName" onblur="component.editOption(this)"/></td>
	 			</tr>
	 			<tr>
	 			<th align="right" >奖品：</th>
				<td class="form_table_content" ><input type="text" class="form_input" maxlength="50" name="prize"  id="prize"/></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">一级预约营销方案活动对象：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="toObject" id="toObject" /></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">一级预约营销方案说明：</th>
					<td class="form_table_content">
					<textarea rows="3" cols="50" class=""  name="activityComment"  id="activityComment"></textarea>
					</td>
	 			</tr>
	 	
	 			<tr>
	 	  			<th align="right">一级预约营销方案温馨提示：</th>
					<td class="form_table_content">
					<textarea rows="3" cols="50" class=""  name="tips"  id="tips"></textarea>
					</td>
	 			</tr>
	 			
	 				<tr>
	 	  			<th align="right">一级预约营销方案开始时间：</th>
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
	 	  			<th align="right">预约类型：</th>
					<td class="form_table_content">
					 <input name="type"  id="type1" type="radio" value="1"/>校验预约营销案
                     <input name="type"  id="type2" type="radio" value="2"/>一般预约营销案
					</td>
	 			</tr>
		
			 	<tr>
	 	  			<th align="right">营业厅展示：</th>
					<td class="form_table_content">
						<div id="officeId">
						</div>
					</td>
	 			</tr>
	 						
	 			<tr>
	 	  			<th align="right">品牌：</th>
					<td class="form_table_content">
					<div id="brandId"></div>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">预付费，后付费配置：</th>
					<td class="form_table_content">
					  <input name="paymode"  id="paymode1" type="radio" value="1" />预付费
                     <input name="paymode"  id="paymode0" type="radio" value="0"/>后付费
                     <input name="paymode"  id="paymode2" type="radio" checked value="2"/>无限制
					
					</td>
	 			</tr>
	 			
				<tr>
	 	  			<th align="right">新增用户目标组织：</th>
					<td class="form_table_content">
						<table>
							<tr>
								<td>未选择列表:</td>
								<td></td>
								<td>已选择列表:</td>
							</tr>
							<tr>
								<td>
									<select id="targetGroupInfoList" size="8" style="width: 200px;">
									</select>
								</td>
								<td>
									<input type="button" value=" > " id="" onclick="component.addTargetGroupInfo();"><br>
									<input type="button" value=" < " id=""  onclick="component.removeTargetGroupInfo();">
								</td>
								<td>
									<select id="selecTargetGroupInfo" name="selecTargetGroupInfo" size="9" style="width: 200px;">
									</select>
								</td>
							</tr>						
						</table>
					</td>
	 			</tr>
	 			<tr>
	 	  		<th align="right">展示位置：</th>
					<td class="form_table_content">
					<table>
						<tr>
							<td>
								<select size="8"  name="marketOrder" id='marketOrder'  style="width: 100" >

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
			 	 	<th  align="right">渠道：</th>
					<td class="form_table_content" >
						<input type="checkbox"  maxlength="50" name="channalData"  id="channalData_4" value="4" />&nbsp;网厅
						<input type="checkbox"  maxlength="50" name="channalData" value="5" />&nbsp;掌厅
					</td>
				</tr>
			</table>
			
			<br>
			<div id="tab" class="dhtmlxTabBar" style="width:100%;height:200px;"
				imgpath="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/">
				<div id="tab_4" name="网厅">
					<table id="wt_tab" class="tb" style="margin:5px">
						<tr>
							<th align="right">网厅渠道选择：</th>
							<td class="form_table_content2"><input type="checkbox"
								maxlength="50" name="tChannal" id="tChannal_1" value="1" />&nbsp;网厅 <input
								type="checkbox" maxlength="50" name="tChannal"  id="tChannal_2" value="2" />&nbsp;网村组服站
								<input type="checkbox" maxlength="50" name="tChannal"  id="tChannal_3" value="3" />&nbsp;校园e站
							</td>
						</tr>
						
						<tr>
							<th align="right">一级预约营销案图片 ：</th>
							<td class="form_table_content2" id="imgTd">&nbsp; 
							<table style="width:100%" id="imgControl" style="display:block">
								<tr>
									<td><img alt=""  id="imgDir_src" src="${contextPath}" style="width: 240px;height: 55px"></td>
									<td>&nbsp;</td>
									<td><a href="javascript:void(0)" class="btn" onClick="component.reupload(true);">重新上传</a></td>
								</tr>
							</table>
							<table id="unimgControl" style="display:none">
								<tr>
									<td><input type="file" name="file" id="imgDir" class="qinggoudan_input021" maxlength="50"></td>
									<td>&nbsp;</td>
									<td><a href="javascript:void(0)" class="btn" onClick="component.reupload(false);">取消上传</a></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				</div>

			</div>
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
        <iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
        
        
        
    	</div>
    	
  


</body>
</html>