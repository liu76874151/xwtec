<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>页面信息维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>

    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>

    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/page_manage/page/page_info_view.js"></script>
</head>

<body>
<input type="hidden" id="pkid_1" value="${param.pkid_1}">
<input type="hidden" id="pkid_2" value="${param.pkid_2}">
<div style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>页面信息--查看</div>
    <!-- 展示信息内容区域开始 -->
    <table width="96%" cellspacing="1" class="form_table">
        <tr>
            <td class="view_info_title" style="width:20%">页面编码：</td>
            <td class="view_info_content" style="width:80%" id="pageNum"></td>
        </tr>
        <tr>
            <td class="view_info_title">页面名称：</td>
            <td class="view_info_content" id="pageName"></td>
        </tr>
        <tr>
            <td class="view_info_title">页面版本：</td>
            <td class="view_info_content" id="version"></td>
        </tr>
        <tr>
            <td class="view_info_title">页面内容：</td>
            <td class="view_info_content"><textarea name="pageContent" id="pageContent" rows="5" class="form_input" style="width: 100%; height: 100%" readonly="readonly"></textarea></td>
        </tr>
        <tr>
            <td class="view_info_title">二次确认组件：</td>
            <td class="view_info_content" id="confirmComp"></td>
        </tr>
        <tr>
            <td class="view_info_title">处理结果组件：</td>
            <td class="view_info_content" id="resultComp"></td>
        </tr>
        <tr>
            <td class="view_info_title">类型：</td>
            <td class="view_info_content" id="type"></td>
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