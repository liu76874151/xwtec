<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.net.URLDecoder"%>
<%@include file="../../taglibs.jsp"%>
<html>
<head>

<title>修改二级预约营销方案</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/css/frame.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>


<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">

<script
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css" />
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar_start.js"></script>

<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/main.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/uomp/reservation_market/reservation_second/edit.js"></script>

</head>
<body style="overflow: auto;">
	<input type="hidden" id="marketSecondPkid" name="marketSecondPkid" value="${param.marketSecondPkid}">
	<div id="main_div" style="padding:10px;height: 1200px;">

		<div class="breadcrumb">
			<span></span>二级预约营销案--修改
		</div>

		
				<form name="addForm" id="addForm" action="" method="post">



					<table class="tb" style="position:relative;">

						<tr>
							<th align="right" width="20%">一级预约方案名称：</th>
							<td class="form_table_content">${param.marketFirstName}</td>
						</tr>
						<tr>
							<th align="right">二级预约方案名称：</th>
							<td class="form_table_content"><input type="text"
								name="remarketSecondNameSel" id="remarketSecondNameSel" class="form_input"
								maxlength="50"  disabled/></td>
						</tr>
						<tr>
							<th align="right">网营二级预约方案名称：</th>
							<td class="form_table_content"><input type="text"
								class="form_input" maxlength="50" name="viewName" id="viewName"
								onblur="addOption(this)" disabled/>
							</td>
						</tr>
						<tr>
							<th align="right">活动对象：</th>
							<td class="form_table_content"><input type="text"
								class="form_input" maxlength="50" name="toObject" id="toObject"
								onblur="addOption(this)" disabled/>
							</td>
						</tr>
						<tr>
							<th align="right">活动说明：</th>
							<td class="form_table_content"><textarea rows="3"
									cols="50" class="" name="activityComment" id="activityComment" disabled></textarea>
							</td>
						</tr>
						<tr>
							<th align="right">BOSS礼品包：</th>
							<td class="form_table_content">
								<div id="bossGiftId">
									<select id="giftId" name="giftInfo" size="6" style="width:150">
									</select>
								</div></td>
						</tr>
						<tr>
							<th align="right">BOSS业务包：</th>
							<td class="form_table_content">
								<div id="bossGiftId">
									<select id="bisId" name="bisInfo" size="6" style="width:150">
									</select>
								</div></td>
						</tr>
						<tr>
							<th align="right">注意事项：</th>
							<td class="form_table_content"><textarea rows="3"
									cols="50" class="" name="notice" id="notice" disabled></textarea>
							</td>
						</tr>
						<tr>
							<th align="right">预约现有充值金额：</th>
							<td class="form_table_content" id="fmoneyvalue">暂无</td>
						</tr>
						<tr>
							<th align="right">开始时间：</th>
							<td class="form_table_content"><input type="text"
								id="beginTime" name="beginTime" disabled></td>
						</tr>
						<tr>
							<th align="right">结束时间：</th>
							<td class="form_table_content"><input type="text"
								id="endTime" name="endTime" disabled></td>
						</tr>
					</table>

					<!-- 按钮操作区域结束 -->
				</form>

	</div>
	<script>

	</script>
</body>
</html>