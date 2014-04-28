<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>学生信息修改</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/demo/student/edit.js"></script>
</head>

<body>
<input type="hidden" id="pkid" value="${param.pkid}">
<div id="main_div" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>学生信息--修改</div>
    <form name="editForm" id="editForm" action="" method="post">
        <!-- 展示信息内容区域开始 -->
        <table width="98%" class="tb">
            <tr>
                <th align="right">姓名：</th>
                <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="stuName" value="" readonly id="stuName"/></td>
                <th align="right">年龄：</th>
                <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="stuAge" value="" id="stuAge"/></td>
            </tr>
            <tr>
                <th align="right">性别：</th>
                <td class="form_table_content" colspan="3"><select name="stuSex" id="stuSex">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select></td>
            </tr>
            <tr>
                <th align="right">籍贯：</th>
                <td class="form_table_content" colspan="3"><select name="stuNative" id="stuNative">
                    <option value="0">北京</option>
                    <option value="1">上海</option>
                    <option value="2">江苏</option>
                    <option value="3">浙江</option>
                </select></td>
            </tr>
            <tr>
                <th align="right">联系电话：</th>
                <td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="100" name="stuTel" value="" id="stuTel"/></td>
            </tr>
            <tr>
                <th align="right">家庭住址：</th>
                <td class="form_table_content" colspan="3"><textarea name="stuAddr" id="stuAddr" rows="5" style="width: 400px;height: 100%"></textarea></td>
            </tr>
        </table>
        <!-- 展示信息内容区域结束 -->
        <!-- 按钮操作区域开始 -->
        <table width="100%" height="40">
            <tr>
                <td align="center">
                    <a href="javascript:void(0)" class="btn" onclick="component.saveForm();">确认提交</a>
                    <a href="javascript:void(0)" class="btn" onclick="document.editForm.reset();">清除重填</a>
                </td>
            </tr>
        </table>
        <!-- 按钮操作区域结束 -->
    </form>
</div>
</body>
</html>