<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>学生信息维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/demo/student/view.js"></script>
</head>

<body>
<input type="hidden" id="pkid" value="${param.pkid}">
<div style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>学生信息--查看</div>
    <!-- 展示信息内容区域开始 -->
    <table width="96%" cellspacing="1" class="form_table">
        <tr>
            <td class="view_info_title" style="width:20%">姓名：</td>
            <td class="view_info_content" style="width:80%" id="stuName"></td>
        </tr>
        <tr>
            <td class="view_info_title">年龄：</td>
            <td class="view_info_content" id="stuAge"></td>
        </tr>
        <tr>
            <td class="view_info_title">性别：</td>
            <td class="view_info_content" id="stuSex"></td>
        </tr>
        <tr>
            <td class="view_info_title">籍贯：</td>
            <td class="view_info_content" id="stuNative"></td>
        </tr>
        <tr>
            <td class="view_info_title">联系电话：</td>
            <td class="view_info_content" id="stuTel"></td>
        </tr>
        <tr>
            <td class="view_info_title">家庭住址：</td>
            <td class="view_info_content" id="stuAddr"></td>
        </tr>
    </table>
    <!--详细信息区 结束-->
</div>
</body>
</html>