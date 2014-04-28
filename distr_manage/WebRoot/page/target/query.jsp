<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../taglibs.jsp"%>
<html>
<head>
<title>目标用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
 	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
    <script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
   
     
    <script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar_start.js"></script>
      
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/target/query.js"></script>
</head>
<body>
	<div title="" style="padding:10px;overflow:auto;">
		<fieldset class="defaultFieldset">
			<legend>目标用户查询</legend>
			<div style="width: 100%"></div>

			<form id="queryForm" name='queryForm' action="" method="post">
				<div></div>
				<table width="100%" class="defaultTable tableStyle1" border="1px">
					<tr>
						<th width="10%">所属地市：</th>
							<td width="×%">
							<select name="area" id="area">
								<option value="">-全部-</option>
							</select></td>
						<th width="15%">组织名称：</th>
						<td width="15%"><input type="text" maxlength="100"
							name="groupName" id="groupName" value=""></td>
						
						<th width="8%">状态：</th>
						<td width="2-%"><select name="state" id="state">
								<option value="">-所有状态-</option>
								<option value="0" >不可用</option>
								<option value="1" selected="selected">可用</option>
						</select>
						</td>
					</tr>
					<tr>
						<th width="10%">开始时间：</th>
			               <td width="5%"><input type="text"  name="beginTime" id="beginTime" readonly="readonly" value=""></td>
			               
			                <th width="10%">结束时间：</th>
			               <td   width="5%"><input type="text"  name="endTime" id="endTime" readonly="readonly"  value=""></td>
							<td colspan="2"></td>
					</tr>
					<tr>
                    <td colspan="6" style="text-align:center;" width="100%"><a
							href="javascript:void(0)" class="btn"
							onclick="component.query();">查 询</a> <a href="javascript:void(0)"
							class="btn" onclick="document.queryForm.reset();">重 置</a></td>
                </tr>
				</table>
			</form>
		</fieldset>
		<div id="toolbar"></div>
		<div id="gridbox"
			style="width:600px;height:270px;background-color:white;width: 100%"></div>

		<div id="paging"></div>
	</div>
</body>
</html>