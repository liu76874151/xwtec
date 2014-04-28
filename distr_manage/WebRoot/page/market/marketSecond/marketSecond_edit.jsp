<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<%@page import="java.net.URLDecoder"%>
<html>
<head>
    <title>修改二级营销方案</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    
    
   
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
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css"></link>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/market/marketSecond_edit.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/ztree/jquery.ztree.excheck-3.5.js"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/ztree/zTreeStyle.css"></link>
    
</head>
<body style="overflow: auto;">
<input type="hidden" id="marketSecondPkid" name="marketSecondPkid" value="${param.marketSecondPkid }">
<input type="hidden" id="tchannal" name="tchannal" value="${param.tchannal }">
<input type="hidden" id="marketFirstchannal" name="marketFirstchannal" value="${param.marketFirstchannal}">
<input type="hidden" value="${param.marketFirstCode}" id="marketFirstCode">
<input type="hidden" value="${param.marketFirstPkid}" id="marketFirstPkid">
<input type="hidden" value="${param.brand}" id="brand">
<input type="hidden" value="${param.isViewPage}" id="isViewPage">
 <input type="hidden" id="cityName" value="<%=URLDecoder.decode(request.getParameter("cityName"),"utf-8") %>">
<div id="main_div" style="padding:10px;height: 1650px;">
    <div class="breadcrumb" id="pageTitle">二级营销案--修改</div>
            <form name="addForm" id="addForm" action="" method="post">
			<table class="tb">
				<br/>
				<tr>
	 	  			<th  align="right">渠道：</th>
					<td class="form_table_content2" id="channalDatas">
					</td>
	 			</tr>
				<tr>
	 	  			<th align="right" width="8%">一级营销方案名称：</th>
					<td class="form_table_content" width="500"><input type="text" class="form_input" maxlength="50" name="marketFirstName" code="" value="" id="marketFirstName" disabled="disabled"/></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS二级方案名称：</th>
					<td class="form_table_content">
					<input type="text" class="form_input" maxlength="50" name="marketSecondName" value="" id="marketSecondName" code=""/>
	 			<!-- 
					 <span class="errorMsg"></span>
					<br><br>
					<select id="bossMarketSecondName" style="width: 260px;" onchange="component.setBossSecondName();">
						<option value="">请选择</option>
					</select>
	 			 -->
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">二级方案名称：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="viewName" value="" id="viewName" onblur="component.editOption(this)"/></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">活动对象：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="toObject" value="" id="toObject" /></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">活动说明：</th>
					<td class="form_table_content">
					<textarea  rows="3" cols="50" class=""  name="activityComment" value="" id="activityComment"></textarea>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">注意事项：</th>
					<td class="form_table_content">
					<textarea rows="3" cols="50" class=""  name="notice"   id="notice"></textarea>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">温馨提示：</th>
					<td class="form_table_content">
					<textarea rows="3" cols="50" class=""  name="sweetPrompt"  id="sweetPrompt"></textarea>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">业务包是否必选：</th>
					
					<td class="form_table_content">
					<input type="radio" name="isNeedCashBooks" value="0" id="isNeedCashBooks_0" ><label for="isNeedCashBooks_0"> 是</label>
					<input type="radio" name="isNeedCashBooks" value="1" id="isNeedCashBooks_1"><label for="isNeedCashBooks_1"> 否</label>
					</td>
					
	 			</tr>
	 		<!-- 	<tr>
	 	  			<th align="right">BOSS已有礼品包：</th>
					<td class="form_table_content">
					<table>
						<tr>
							<td>
								<select id="bossGiftInfo" size="8" style="width: 260px;">
								
								</select>
							</td>
							<td>
								<input type="button" value="编辑" onclick="component.editBossGift();"><br>
								<input type="button" value="移除" onclick="component.removeBossGift();">
							</td>
						</tr>
					</table>
					
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">新增礼品包：</th>
					<td class="form_table_content">
					<table>
						<tr>
							<td>
								<select size="8" id="newAddGiftInfo" style="width: 260px;">
								
								</select>
							</td>
							<td>
								<input type="button" value="新增"  onclick="component.addGift();"><br>
								<input type="button" value="编辑"  onclick="component.editGift();"><br>
								<input type="button" value="移除"  onclick="component.removeGift();">
							</td>
						</tr>
					</table>
					</td>
	 			</tr> -->
	 			<tr>
	 				<th align="right">
						领取方式：
					</th>
					<td class="form_table_content">
						&nbsp;
						<table>
							<tr>
								<td width="80">
									<input type="checkbox" name="giftGetTypes" id='giftGetTypes1' gtype="simple" value="1" onclick="component.showOrgInfoDisplay();">
									<label for="giftGetTypes1">自取</label>&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td width="80">
									<input type="checkbox" name="giftGetTypes" id='giftGetTypes2' gtype="simple" value="2" >
									<label for="giftGetTypes2">实物寄送</label>
								</td>
								<td>
									<input type="checkbox" name="giftGetTypes" id='giftGetTypes3' gtype="simple" value="3">
									<label for="giftGetTypes3">电子券</label>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<input type="checkbox" name="giftGetTypes" id="giftGetTypes4" type="qt" value="4" onclick="component.changeGiftTypes();">
									<label for="giftGetTypes4">其他</label>（如选择此项，则无法同时选择上述三种领取方式）
								</td>
							</tr>
							<tr height="60">
								<td colspan="3">
									<table id="orgInfoDisplay" style="display: none;">
										
										<tr>
											<td>请选择营业厅</td>
											<td>
												<!-- <select id="cityList"></select> -->
											</td>
										</tr>
										<tr>
											<td colspan="2" id="yingyeting">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right"><input type="checkbox" value="1" name="isNeedMoney" id="isNeedMoney_1" onclick="component.changPayType();"><label for="isNeedMoney_1">充值营销案：</label></th>
					<td class="form_table_content"><input type="checkbox"  maxlength="50" name="payname" value="1" id="payname_1"/>&nbsp;<label for="payname_1">在线支付</label>
					<input type="checkbox"  maxlength="50" name="payname" value="2" id="payname_2"/>&nbsp;<label for="payname_2">银联电子支付</label>
					<input type="checkbox"  maxlength="50" name="payname" value="3" id="payname_3"/>&nbsp;<label for="payname_3">话费直接扣减</label>
					<input type="checkbox"  maxlength="50" name="payname" value="4" id="payname_4"/>&nbsp;<label for="payname_4">支付宝</label></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS现有充值金额：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="bossMoneyVal" value="" id="bossMoneyVal" /></td>
	 			</tr>
	 			<!-- 
	 			<tr>
	 	  			<th align="right">是否需要充值：</th>
					<td class="form_table_content">
					<input type="radio" name="isNeedMoney" value="0" id="isNeedMoneyF" checked="checked"/>否
					<input type="radio" name="isNeedMoney" value="1" id="isNeedMoneyT"/>是
					</td>
	 			</tr>
	 			 -->
	 			<tr>
	 	  			<th align="right">网厅充值优先展示：</th>
					<td class="form_table_content">
						<input type="radio" value="0" name="firstTop" id="firstTop_0"><label for="firstTop_0">正常</label>
						<input type="radio" value="1" name="firstTop" id="firstTop_1"><label for="firstTop_1">优先</label>
						<br>
						（一个地市只能有一个二级营销案优先展示）（详情请参考操作文档）
					</td>
					
	 			</tr>
	 			<!-- 
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
									<input type="button" value=">" id="" onclick="component.addTargetGroupInfo();"><br>
									<input type="button" value="<" id=""  onclick="component.removeTargetGroupInfo();">
								</td>
								<td>
									<select id="selecTargetGroupInfo" size="9" style="width: 200px;">
									</select>
								</td>
							</tr>						
						</table>
					</td>
	 			</tr> -->
	 			<tr>
	 	  			<th align="right">叠加优惠：</th>
					<td class="form_table_content">
						<input type="radio" name="NESTIFICATION_TYPE" value="1" id="NESTIFICATION_TYPE_1">&nbsp;<label for="NESTIFICATION_TYPE_1">参与抽奖</label>
						<input type="radio" name="NESTIFICATION_TYPE" value="2" id="NESTIFICATION_TYPE_2">&nbsp;<label for="NESTIFICATION_TYPE_2">赠送话费</label>
						<input type="radio" name="NESTIFICATION_TYPE" value="3" id="NESTIFICATION_TYPE_3">&nbsp;<label for="NESTIFICATION_TYPE_3">体验业务</label>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">开始时间：</th>
	 	  			<td class="form_table_content">
	 	  				<input type="text" id="beginTime" value=""/>
	 	  			</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">结束时间：</th>
					<td class="form_table_content"><input type="text" id="endTime" value=""/>
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">协议名称：</th>
					<td class="form_table_content">
						<select id="marketTemplate" style="width: 300px;" onchange="component.setPactTemplateContent();">
							<option value="" >请选择</option>
						</select>
					</td>
	 			</tr>
	 			<tr>
			    	<th align="right" width="8%">协议内容：</th>
			    	<td>
	 				<table class="tb">
	 					<tr> 
							<td class="form_table_content" width="500">
							<textarea rows="12" cols="70" id="pactTemplateContent"></textarea>
							</td>
						</tr>
			    	</table>
			    	</td>
				</tr>
			</table>
			<!-- 按钮操作区域开始 -->
	   		<!-- 按钮操作区域结束 -->
			</form>	
			<br/><br/>
			      <div id="tab" class="dhtmlxTabBar"  style="width:100%; height: 1050px;" imgpath="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/" oninit="doOnInit()">   
	  <div id="tab_1" name="网厅">
	  <table class="tb"  >
         <tr>
	 	  			<th align="right">新增用户目标组织：</th>
					<td >
						<table>
							<tr>
								<td>未选择列表:</td>
								<td></td>
								<td>已选择列表:</td>
							</tr>
							<tr>
								<td>
									<select id="targetGroupInfoList4" size="8" style="width: 200px; " >
									</select>
								</td>
								<td>
									<input type="button" value=">" id="" onclick="component.addTargetGroupInfo('4');"><br>
									<input type="button" value="<" id=""  onclick="component.removeTargetGroupInfo('4');">
								</td>
								<td>
									<select id="selecTargetGroupInfo4" size="9" style="width: 200px;">
									</select>
								</td>
							</tr>						
						</table>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS已有捆绑业务包：</th>
					<td class="form_table_content">
						<ul id="treeWT" class="ztree"></ul>
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">BOSS已有礼品包：</th>
					<td class="form_table_content">
					<ul id="treeGiftWT" class="ztree"></ul>
					
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">新增礼品包：</th>
					<td class="form_table_content">
					<table>
						<tr>
							<td>
								<select size="8" id="newAddGiftInfo4" style="width: 260px;">
								
								</select>
							</td>
							<td>
								<input type="button" value="新增"  onclick="component.addGift(4);"><br>
								<input type="button" value="编辑"  onclick="component.editGift(4);"><br>
								<input type="button" value="移除"  onclick="component.removeGift(4);">
							</td>
						</tr>
					</table>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">展示位置：</th>
					<td class="form_table_content">
					<div id="wtMarketOrderDiv">
					<table>
						<tr>
							<td>
								<select size="8"  name="wtMarketOrder" id="wtMarketOrder"  style="width: 160px ;height:140px" >
								</select>
							</td>
							<td>
								<input type="button" value="上移" id="wtBtnMoveUp" ><br>
								<input type="button" value="下移" id="wtBtnMoveDown" >
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
	 	  			<th align="right">新增用户目标组织：</th>
					<td >
						<table>
							<tr>
								<td>未选择列表:</td>
								<td></td>
								<td>已选择列表:</td>
							</tr>
							<tr>
								<td>
									<select id="targetGroupInfoList5" size="8" style="width: 200px;">
									</select>
								</td>
								<td>
									<input type="button" value=">" id="" onclick="component.addTargetGroupInfo('5');"><br>
									<input type="button" value="<" id=""  onclick="component.removeTargetGroupInfo('5');">
								</td>
								<td>
									<select id="selecTargetGroupInfo5" size="9" style="width: 200px;">
									</select>
								</td>
							</tr>						
						</table>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS已有捆绑业务包：</th>
					<td class="form_table_content">
						<ul id="treeZT" class="ztree"></ul>
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">BOSS已有礼品包：</th>
					<td class="form_table_content">
					<ul id="treeGiftZT" class="ztree"></ul>
					
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">新增礼品包：</th>
					<td class="form_table_content">
					<table>
						<tr>
							<td>
								<select size="8" id="newAddGiftInfo5" style="width: 260px;">
								
								</select>
							</td>
							<td>
								<input type="button" value="新增"  onclick="component.addGift(5);"><br>
								<input type="button" value="编辑"  onclick="component.editGift(5);"><br>
								<input type="button" value="移除"  onclick="component.removeGift(5);">
							</td>
						</tr>
					</table>
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
         <th  align="right">配置金额(元)：</th>
					<td  >
					<input class="form_input" type="text"  name="dtConfigureMoney" value="" id="dtConfigureMoney"/> 
					</td>
         </tr>
          <tr>
         <th  align="right">品牌</th>
					<td  id="dtBrandId">
					</td>
         </tr>
         
         <tr>
	 	  			<th align="right">新增用户目标组织：</th>
					<td >
						<table>
							<tr>
								<td>未选择列表:</td>
								<td></td>
								<td>已选择列表:</td>
							</tr>
							<tr>
								<td>
									<select id="targetGroupInfoList6" size="8" style="width: 200px;">
									</select>
								</td>
								<td>
									<input type="button" value=">" id="" onclick="component.addTargetGroupInfo('6');"><br>
									<input type="button" value="<" id=""  onclick="component.removeTargetGroupInfo('6');">
								</td>
								<td>
									<select id="selecTargetGroupInfo6" size="9" style="width: 200px;">
									</select>
								</td>
							</tr>						
						</table>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS已有捆绑业务包：</th>
					<td class="form_table_content">
						<ul id="treeDT" class="ztree"></ul>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS已有礼品包：</th>
					<td class="form_table_content">
						<ul id="treeGiftWT" class="ztree"></ul>
					
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">新增礼品包：</th>
					<td class="form_table_content">
					<table>
						<tr>
							<td>
								<select size="8" id="newAddGiftInfo6" style="width: 260px;">
								
								</select>
							</td>
							<td>
								<input type="button" value="新增"  onclick="component.addGift(6);"><br>
								<input type="button" value="编辑"  onclick="component.editGift(6);"><br>
								<input type="button" value="移除"  onclick="component.removeGift(6);">
							</td>
						</tr>
					</table>
					</td>
	 			</tr>
           <tr>
         <th  align="right">指令：</th>
					<td  >
					<input type="checkbox" id="cmdEnableFlag" ><label for="cmdEnableFlag">&nbsp;启用</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" class="form_input"  name="cmdCotent" value="" id="cmdCotent"/> 
					</td>
         </tr>
         
          <tr>
         <th  align="right">二次确认扩展码：</th>
					<td  >
					<input type="text" class="form_input"  name="dtSpCode" value="" id="dtSpCode"/> 
					</td>
         </tr>
          <tr>
         <th  align="right">二次确认附加短信：</th>
					<td  >
					<input type="text" class="form_input"  name="dtConfirmExtraMsg" value="" id="dtConfirmExtraMsg"/> 
					</td>
         </tr>
          <tr>
         <th  align="right">办理成功附加短信：</th>
					<td >
					<input type="text" class="form_input"  name="dtSuccMsg" value="" id="dtSuccMsg"/> 
					</td>
         </tr>
           <tr>
         <th  align="right">是否下发短信：</th>
					<td >
					<input type="radio"  name="dtSendSuccMsgEnable" value="1" id="dtSendSuccMsgEnable_1"/> <label for="dtSendSuccMsgEnable_1">&nbsp;是&nbsp;</label>
					<input type="radio"  name="dtSendSuccMsgEnable" value="0" id="dtSendSuccMsgEnable_0"/> <label for="dtSendSuccMsgEnable_0">&nbsp;否&nbsp;</label>
					</td>
         </tr>
         
            <tr>
         <th  align="right">是否热点推荐：</th>
					<td >
					<input type="radio"  name="dtIsRecommend" value="1" id="dtIsRecommend_1"/> <label for="dtIsRecommend_1">&nbsp;是&nbsp;</label>
					<input type="radio"  name="dtIsRecommend" value="0" id="dtIsRecommend_0"/> <label for="dtIsRecommend_0">&nbsp;否&nbsp;</label>
					</td>
         </tr>
           <tr>
         <th  align="right">互斥业务配置：</th>
					<td >
					<input type="checkbox" id="dtMutexEnable" ><label for="dtMutexEnable">&nbsp;启用</label> &nbsp;&nbsp;
					需要关闭的业务:&nbsp;&nbsp;<input class="form_input" type="text"  name="dtUnallowBusi" value="" id="dtUnallowBusi"/> <br/><br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						互斥的业务:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="form_input"  type="text"  name="dtMutexBusi" value="" id="dtMutexBusi"/> 
						<br/><br/>
					</td>
         </tr>
         
           <tr>
         <th  align="right">营销案生效前提示短信：</th>
					<td  >
					<textarea rows="" cols="" style="width: 350px ;resize:none" id="dtBeforeEffectMsg" ></textarea>
					</td>
         </tr>
            <tr>
         <th  align="right">营销案到期后提示短信：</th>
					<td >
					<textarea rows="" cols="" style="width: 350px;resize:none" id='dtExpireMsg'></textarea>
					</td>
         </tr>
            <tr>
         <th  align="right">营销公务短信：</th>
					<td >
					<textarea rows="" cols="" style="width: 350px;resize:none" id='busiMarketSms'></textarea>
					</td>
         </tr>
         </table>
        </div>
        
    </div>
        <table width="100%" height="40" id="saveBtn">
				<tr>
			    	<td align="center">
					<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">保存</a>
					</td>
				</tr>
	   		</table>
	   		  
   
    
</div>    
<!-- 
<table class="tb">
			    	<th align="right" width="8%">协议内容：</th>
	 				<tr> 
						<td class="form_table_content" width="500">
							<div id="editorObj" style="width: 100%; height: 300px; border: #909090 1px solid;"></div>
							<div id="skinData">
			     				<a href='javascript:void(0)' onclick='component.initEditor("dhx_skyblue")'>dhx_skyblue</a> &nbsp;&nbsp;
							<a href='javascript:void(0)' onclick='component.initEditor("dhx_blue")'>dhx_blue</a> &nbsp;&nbsp;
							</div>
						</td>
					</tr>
			    </table>
 -->
</body>
</html>