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
    
</head>
<body style="overflow: auto;">
<input type="hidden" id="marketSecondPkid" name="marketSecondPkid" value="${param.marketSecondPkid }">
<input type="hidden" id="tchannal" name="tchannal" value="${param.tchannal }">
<input type="hidden" value="" id="city">
<div id="main_div" style="padding:10px;height: 1650px;">
    <div class="breadcrumb"><span></span>二级营销案--查看</div>
   <!--  <div id="tab" class="dhtmlxTabBar"  style="width:100%; height:100%;" imgpath="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/" oninit="doOnInit()">
       
        <div id="tab_1" name="网厅">
        
    	</div>
        <div id="tab_2" name="掌厅">--> 
            <form name="addForm" id="addForm" action="" method="post">
			<table class="tb">
				<br>
				<tr>
	 	  			<th  align="right">渠道：</th>
					<td class="form_table_content2" id="channalData">
					</td>
	 			</tr>
				<tr>
	 	  			<th align="right" width="8%">一级营销方案名称：</th>
					<td class="form_table_content" width="500"><input disabled="disabled" type="text" class="form_input" maxlength="50" name="marketFirstName" code="" value="" id="marketFirstName" disabled="disabled"/></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS二级方案名称：</th>
					<td class="form_table_content">
					<input disabled="disabled" type="text" class="form_input" maxlength="50" name="marketSecondName" value="" id="marketSecondName" code=""/>
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
	 	  			<th align="right">BOSS已有捆绑业务包：</th>
					<td class="form_table_content">
						<table>
							<tr>
								<td>未选择业务:</td>
								<td></td>
								<td>已选择业务:</td>
							</tr>
							<tr>
								<td>
									<select disabled="disabled" id="bossBindBizName" size="8" style="width: 200px;">
									</select>
								</td>
								<td>
									<input disabled="disabled" type="button" value="&gt;添加" onclick="component.addBusiness();"><br>
									<input disabled="disabled" type="button" value="&lt;删除"  onclick="component.removeBusiness();">
								</td>
								<td>
									<select disabled="disabled" id="selectBusiness" size="8" style="width: 200px;">
									</select>
								</td>
							</tr>						
						</table>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">二级方案名称：</th>
					<td class="form_table_content"><input disabled="disabled" type="text" class="form_input" maxlength="50" name="viewName" value="" id="viewName" /></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">活动对象：</th>
					<td class="form_table_content"><input disabled="disabled" type="text" class="form_input" maxlength="50" name="toObject" value="" id="toObject" /></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">活动说明：</th>
					<td class="form_table_content">
					<textarea disabled="disabled"  rows="3" cols="50" class=""  name="activityComment" value="" id="activityComment"></textarea>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">注意事项：</th>
					<td class="form_table_content">
					<textarea disabled="disabled" rows="3" cols="50" class=""  name="notice"   id="notice"></textarea>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">温馨提示：</th>
					<td class="form_table_content">
					<textarea disabled="disabled" rows="3" cols="50" class=""  name="sweetPrompt"  id="sweetPrompt"></textarea>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">现金账本：</th>
					
					<td class="form_table_content">
					<input disabled="disabled" type="radio" name="isNeedCashBooks" value="0" id="isNeedCashBooks_0" ><label for="isNeedCashBooks_0"> 不需要</label>
					<input disabled="disabled" type="radio" name="isNeedCashBooks" value="1" id="isNeedCashBooks_1"><label for="isNeedCashBooks_1"> 需要</label>
					</td>
					
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS已有礼品包：</th>
					<td class="form_table_content">
					<table>
						<tr>
							<td>
								<select disabled="disabled" id="bossGiftInfo" size="8" style="width: 260px;">
								
								</select>
							</td>
							<td>
								
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
								<select disabled="disabled" size="8" id="newAddGiftInfo" style="width: 260px;">
								
								</select>
							</td>
							<td>
								
							</td>
						</tr>
					</table>
					</td>
	 			</tr>
	 			<tr>
	 				<th align="right">
						领取方式：
					</th>
					<td class="form_table_content">
						&nbsp;
						<table>
							<tr>
								<td width="80">
									<input disabled="disabled" type="checkbox" name="giftGetTypes" id='giftGetTypes1' gtype="simple" value="1" onclick="component.showOrgInfoDisplay();">
									<label for="giftGetTypes1">自取</label>&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td width="80">
									<input disabled="disabled" type="checkbox" name="giftGetTypes" id='giftGetTypes2' gtype="simple" value="2" >
									<label for="giftGetTypes2">实物寄送</label>
								</td>
								<td>
									<input disabled="disabled" type="checkbox" name="giftGetTypes" id='giftGetTypes3' gtype="simple" value="3">
									<label for="giftGetTypes3">电子券</label>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<input disabled="disabled" type="checkbox" name="giftGetTypes" id="giftGetTypes4" type="qt" value="4" onclick="component.changeGiftTypes();">
									<label for="giftGetTypes4">其他</label>（如选择此项，则无法同时选择上述三种领取方式）
								</td>
							</tr>
							<tr height="60">
								<td colspan="3">
									<table id="orgInfoDisplay" style="display: none;">
										
										<tr>
											<td>请选择营业厅</td>
											<td>
												<select disabled="disabled" id="cityList"></select>
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
	 	  			<th align="right"><input disabled="disabled" type="checkbox" value="1" name="isNeedMoney" id="isNeedMoney_1" onclick="component.changPayType();"><label for="isNeedMoney_1">充值营销案：</label></th>
					<td class="form_table_content"><input disabled="disabled" type="checkbox"  maxlength="50" name="payname" value="1" id="payname_1"/>&nbsp;<label for="payname_1">在线支付</label>
					<input disabled="disabled" type="checkbox"  maxlength="50" name="payname" value="2" id="payname_2"/>&nbsp;<label for="payname_2">银联电子支付</label>
					<input disabled="disabled" type="checkbox"  maxlength="50" name="payname" value="3" id="payname_3"/>&nbsp;<label for="payname_3">话费直接扣减</label>
					<input disabled="disabled" type="checkbox"  maxlength="50" name="payname" value="4" id="payname_4"/>&nbsp;<label for="payname_4">支付宝</label></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">BOSS现有充值金额：</th>
					<td class="form_table_content"><input disabled="disabled" type="text" class="form_input" maxlength="50" name="bossMoneyVal" value="" id="bossMoneyVal" /></td>
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
						<input disabled="disabled" type="radio" value="0" name="firstTop" id="firstTop_0"><label for="firstTop_0">正常</label>
						<input disabled="disabled" type="radio" value="1" name="firstTop" id="firstTop_1"><label for="firstTop_1">优先</label>
						<br>
						（一个地市只能有一个二级营销案优先展示）（详情请参考操作文档）
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
									<select disabled="disabled" id="targetGroupInfoList" size="8" style="width: 200px;">
									</select>
								</td>
								<td>
									<input disabled="disabled" type="button" value=">" id="" onclick="component.addTargetGroupInfo();"><br>
									<input disabled="disabled" type="button" value="<" id=" "  onclick="component.removeTargetGroupInfo();">
								</td>
								<td>
									<select disabled="disabled" id="selecTargetGroupInfo" size="9" style="width: 200px;">
									</select>
								</td>
							</tr>						
						</table>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">叠加优惠：</th>
					<td class="form_table_content">
						<input disabled="disabled" type="radio" name="NESTIFICATION_TYPE" value="1" id="NESTIFICATION_TYPE_1">&nbsp;<label for="NESTIFICATION_TYPE_1">参与抽奖</label>
						<input disabled="disabled" type="radio" name="NESTIFICATION_TYPE" value="2" id="NESTIFICATION_TYPE_2">&nbsp;<label for="NESTIFICATION_TYPE_2">赠送话费</label>
						<input disabled="disabled" type="radio" name="NESTIFICATION_TYPE" value="3" id="NESTIFICATION_TYPE_3">&nbsp;<label for="NESTIFICATION_TYPE_3">体验业务</label>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">开始时间：</th>
	 	  			<td class="form_table_content">
	 	  				<input type="text" id="beginTime" value="" disabled="disabled"/>
	 	  			</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">结束时间：</th>
					<td class="form_table_content"><input type="text" id="endTime" value="" disabled="disabled"/>
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">协议名称：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="50" value="" id="pactTemplateName"  disabled="disabled"/><br><br>
						<select id="marketTemplate" style="width: 300px;" onchange="component.setPactTemplateContent();">
							<option value=""  disabled="disabled">请选择</option>
						</select>
					</td>
	 			</tr>
	 			<tr>
			    	<th align="right" width="8%">协议内容：</th>
			    	<td>
	 				<table class="tb">
	 					<tr> 
							<td class="form_table_content" width="500">
							<textarea rows="12" cols="70" id="pactTemplateContent" disabled="disabled"></textarea>
							</td>
						</tr>
			    	</table>
			    	</td>
				</tr>
			</table>
			<!-- 按钮操作区域开始 -->
	   		<!-- 按钮操作区域结束 -->
			</form>	
        
</body>
</html>