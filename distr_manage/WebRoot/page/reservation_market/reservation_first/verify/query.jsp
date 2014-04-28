<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@page import="java.net.URLDecoder"%>
<%@include file="../../../taglibs.jsp" %>
<html>
<head>
    <title>一级预约营销方案审批</title>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css"></link>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/reservation_market/reservation_first/marketFirst_verify.js"></script>
</head>
<body>
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>一级预约营销方案审核</div>
     <fieldset class="defaultFieldset">
        <legend>查询</legend>
        <form id="queryForm" name='queryForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
                <tr>
                    <th>所属城市：</th>
                    <td>
                    	<select id="city">
                    		<option value="">-请选择-</option>
                    	</select>
                    </td>
                    <th >二级方案名称：</th>
                    <td >
                    	<input id="viewName" name="viewName">
                    </td>
                    <th>审核状态：</th>
                    <td>
                    	<select id="verifyState">
                    		<option value="">-请选择-</option>
                    		<option value="0">待审核</option>
                    		<option value="1">审核通过</option>
                    		<option value="2">待审未通过</option>
                    	</select>
                    </td>
                    <th >展示状态：</th>
                    <td>
                    	<select id="isListView">
                    		<option value="">-请选择-</option>
                    		<option value="0">不展示</option>
                    		<option value="1">展示</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                	<th>开始时间：</th>
                    <td>
                    	
                    	<input id="beginTime" name="beginTime">
                    </td>
                	<th>-结束时间：</th>
                    <td>
                    	<input id="endTime" name="endTime">
                    </td>
                    <th >渠道：</th>
                    <td colspan="3">
                    	<select id="channalData">
                    		<option value="">-请选择-</option>
                    		<option value="4">网厅</option>
                    		<option value="5">掌厅</option>
                    		<option value="6">短厅</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" style="text-align:center;">
                        <a href="javascript:void(0)" class="btn" onclick="component.query();">查 询</a>
                        <a href="javascript:void(0)" class="btn" onclick="document.queryForm.reset();">重 置</a>
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
    
    <div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
   
    <div id="paging"></div>
    
</div>
</body>
</html>