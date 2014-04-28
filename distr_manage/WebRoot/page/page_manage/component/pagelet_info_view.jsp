<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>页面组件维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>

    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>

    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/page_manage/component/pagelet_info_view.js"></script>
</head>

<body>
<input type="hidden" id="pkid_1" value="${param.pkid_1}">
<input type="hidden" id="pkid_2" value="${param.pkid_2}">
<div style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>页面组件--查看</div>
    <!-- 展示信息内容区域开始 -->
    <table width="96%" cellspacing="1" class="form_table">
        <tr>
            <td class="view_info_title" style="width:20%">分块编码：</td>
            <td class="view_info_content" style="width:80%" id="pageletNum"></td>
        </tr>
        <tr>
            <td class="view_info_title">分块名称：</td>
            <td class="view_info_content" id="pageletName"></td>
        </tr>
        <tr>
            <td class="view_info_title">分块版本：</td>
            <td class="view_info_content" id="version"></td>
        </tr>
        <tr>
            <td class="view_info_title">模板内容：</td>
            <td class="view_info_content"><textarea name="vmTemplate" id="vmTemplate" rows="5" class="form_input" style="width: 100%;height: 120px" readonly="readonly"></textarea></td>
        </tr>
        <tr>
            <td class="view_info_title">分块步骤：</td>
            <td class="view_info_content" id="step"></td>
        </tr>
        <tr>
            <td class="view_info_title">备注：</td>
            <td class="view_info_content"><textarea name="desc" id="desc" rows="5" class="form_input" style="width: 100%;height: 120px" readonly="readonly"></textarea></td>
        </tr>
    </table>
    <!--详细信息区 结束-->
</div>
</body>
</html>