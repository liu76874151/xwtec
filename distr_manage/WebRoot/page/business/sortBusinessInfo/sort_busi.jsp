<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<title>业务排序</title>
	<link rel="stylesheet" href="${contextPath}/resource/scripts/uomp/buesiness/sortBusinessInfo/themes/ui-lightness/minified/jquery.ui.core.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/dhtmlxcombo.css"></link>
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script src="${contextPath}/resource/scripts/uomp/buesiness/sortBusinessInfo/ui/minified/jquery.ui.core.min.js"></script>
	<script src="${contextPath}/resource/scripts/uomp/buesiness/sortBusinessInfo/ui/minified/jquery.ui.widget.min.js"></script>
	<script src="${contextPath}/resource/scripts/uomp/buesiness/sortBusinessInfo/ui/minified/jquery.ui.mouse.min.js"></script>
	<script src="${contextPath}/resource/scripts/uomp/buesiness/sortBusinessInfo/ui/minified/jquery.ui.sortable.min.js"></script>
	<script src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
	<script src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	<script src="${contextPath}/resource/scripts/uomp/buesiness/sortBusinessInfo/sort_businessInfo.js"></script>
	<style>
	#sortable { list-style-type: none; margin: 0; padding: 1px; width: 100%; }
	#sortable li { margin: 3px 3px 3px 0; padding: 1px; float: left; width: 70px; height: 60px; font-size: 14px; text-align: center; }
	.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
	    background: repeat-x scroll 50% 50% #E0ECFF;
	    border: 1px solid #D3D3D3;
	    color: #555555;
	    font-weight: normal;
	}
	</style>
	<script>
		$(function() {
			$( "#sortable" ).sortable();
			$( "#sortable" ).disableSelection();
		});
	</script>
</head>
<body style="overflow:auto;padding: 2px;">
<div>
    <div class="breadcrumb"><span></span>业务信息测试--查询</div>
    <fieldset class="defaultFieldset">
        <legend>业务测试查询</legend>
        <form id="queryForm" name='queryForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
                <tr>
                    <th width="10%" >业务名称：</th>
                    <td width="20%">
                    	<input type="text" maxlength="100" name="busiName" id="busiName">
                    	<span class="errorMsg"></span>
                    </td>
                    <th width="10%" >业务编码：</th>
                    <td >
	                    <input type="text" maxlength="50" name="busiNum" id="busiNum">
	                    <span class="errorMsg"></span>
                    </td>
                    <th >渠道：</th>
                    <td>
                    	<select id="channelNum" name="channelNum" style="width: 100px;">
                    		<option value="02">-掌厅-</option>
                    		<option value="01">-网厅-</option>
                    		<option value="03">-短厅-</option>
                    	</select>
                    <span class="errorMsg"></span></td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align:center;">
                        <a href="javascript:void(0)" class="btn" onclick="component.query();">查 询</a>
                        <a href="javascript:void(0)" class="btn" onclick="document.queryForm.reset();">重 置</a>
                        <a href="javascript:void(0)" class="btn" onclick="component.save();">保存</a>
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
    <ul id="sortable"></ul>
</div>
</body>
</html>
