<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>页面模板维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>

    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"/>

    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>

    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar_start.js"></script>

    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>

    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/page_manage/page/page_tmp_info_view.js"></script>
</head>

<body>
<input type="hidden" id="pkid_1" value="${param.pkid_1}">
<input type="hidden" id="pkid_2" value="${param.pkid_2}">
<div id="main_div" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>页面模板--查看</div>
    <div id="tab" class="dhtmlxTabBar"  style="width:100%; height:400px;" imgpath="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/">
        <div id="tab_1" name="页面模板基本信息">
                <!-- 展示信息内容区域开始 -->
                <table width="98%" class="tb">
                    <tr>
                        <th align="right">页面模板编码：</th>
                        <td class="form_table_content" id="pageTmpNum"></td>
                        <th align="right">页面模板名称：</th>
                        <td class="form_table_content" id="pageTmpName"></td>
                    </tr>
                    <tr>
                        <th align="right">支持版本：</th>
                        <td class="form_table_content" colspan="3" id="version"></td>
                    </tr>
                    <tr>
                        <th align="right">模板路径：</th>
                        <td class="form_table_content" colspan="3" id="tmplatePath"></td>
                    </tr>
                    <tr>
                        <th align="right">是否关联业务：</th>
                        <td class="form_table_content" colspan="3" id="isRelaBusi"></td>
                    </tr>
                    <tr>
                        <th align="right">类型：</th>
                        <td class="form_table_content" colspan="3" id="type"></td>
                    </tr>
                    <tr>
                        <th align="right">备注：</th>
                        <td class="form_table_content" colspan="3" id="desc"></td>
                    </tr>
                </table>
                <!-- 展示信息内容区域结束 -->
        </div>
        <div id="tab_2" name="页面模板关联组件">
            <div id="gridbox_comp" style="height:380px;background-color:white;width: 100%"></div>
        </div>
        <div id="tab_3" name="页面模板关联业务">
            <div id="gridbox_busi" style="height:380px;background-color:white;width: 100%"></div>
        </div>
    </div>
</div>
</body>
</html>