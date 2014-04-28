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

    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/page_manage/component/page_comp_info_view.js"></script>
</head>

<body>
<input type="hidden" id="pkid" value="${param.pkid}">
<div style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>页面组件--查看</div>
    <!-- 展示信息内容区域开始 -->
    <table width="96%" cellspacing="1" class="form_table">
        <tr>
            <td class="view_info_title" style="width:20%">组件编码：</td>
            <td class="view_info_content" style="width:80%" id="compNum"></td>
        </tr>
        <tr>
            <td class="view_info_title">组件名称：</td>
            <td class="view_info_content" id="compName"></td>
        </tr>
        <tr>
            <td class="view_info_title">组件类别：</td>
            <td class="view_info_content" id="compType"></td>
        </tr>
        <tr>
            <td class="view_info_title">组件URL：</td>
            <td class="view_info_content" id="compUrl"></td>
        </tr>
        <tr>
            <td class="view_info_title">关联区域：</td>
            <td class="view_info_content" id="containerId"></td>
        </tr>
        <tr>
            <td class="view_info_title">备注：</td>
            <td class="view_info_content" id="desc"></td>
        </tr>
    </table>
    <!--详细信息区 结束-->
</div>
</body>
</html>