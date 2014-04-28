<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>组件管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"/>

    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>

    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/page_manage/component/page_comp_info_query.js"></script>
</head>
<body>
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>组件管理--查询</div>
    <fieldset class="defaultFieldset">
        <legend>组件查询</legend>
        <form id="queryForm" name='queryForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
                <tr>
                    <th width="20%">组件编码：</th>
                    <td width="30%"><input type="text" maxlength="100" name="compNum" id="compNum" value=""></td>
                    <th width="20%">组件名称：</th>
                    <td width="30%"><input type="text" maxlength="100" name="compName" id="compName" value=""></td>
                </tr>
                <tr>
                    <th>组件类型：</th>
                    <td>
                        <select name="compType" id="compType">
                            <option value="">--请选择--</option>
                            <option value="1">静态组件</option>
                            <option value="2">动态组件</option>
                            <option value="3">货架组件</option>
                            <option value="4">二次确认组件</option>
                            <option value="5">处理结果组件</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="text-align:center;">
                        <a href="javascript:void(0)" class="btn" onclick="component.query();">查 询</a>
                        <a href="javascript:void(0)" class="btn" onclick="document.queryForm.reset();">重 置</a>
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
    <div id="toolbar"></div>
    <div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
    <div id="paging"></div>
</div>
</body>
</html>