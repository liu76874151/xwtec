<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>业务扩展属性</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessExtra/busiExtra-query.js"></script>
</head>
<body>
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>业务扩展属性--查询</div>
    <fieldset class="defaultFieldset">
        <legend>业务扩展属性查询</legend>
        <form id="queryForm" name='queryForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
           	 	<tr>
                    <th>属性标识：</th>
                    <td width="30"><input type="text" maxlength="100" name="attrKey" id="attrKey" value=""></td>
                    <th>属性名称：</th>
                    <td><input type="text" maxlength="100" name="attrName" id="attrName" value=""></td>
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
    
    <div id="gridbox" style="width:600px;height:230px;background-color:white;width: 100%"></div>
   
    <div id="paging"></div>
</div>
</body>
</html>