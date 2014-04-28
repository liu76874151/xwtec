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

    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/page_manage/component/pagelet_info_edit.js"></script>
</head>

<body>
<input type="hidden" id="pkid_1" value="${param.pkid_1}">
<input type="hidden" id="pkid_2" value="${param.pkid_2}">
<div id="main_div" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>组件模板--修改</div>
    <form name="editForm" id="editForm" action="" method="post">
        <!-- 展示信息内容区域开始 -->
        <table width="98%" class="tb">
            <tr>
                <th align="right">分块编码：</th>
                <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="pageletNum"  value="" id="pageletNum" readonly="readonly"/></td>
                <th align="right">分块名称：</th>
                <td class="form_table_content"><input type="text" class="form_input" maxlength="50"  name="pageletName" value="" id="pageletName"/></td>
            </tr>
            <tr>
                <th align="right">分块版本：</th>
                <td class="form_table_content" colspan="3"><select name="version" id="version" disabled="disabled">
                    <option value="1">普版</option>
                    <option value="2">标准版</option>
                    <option value="3">触屏版</option>
                </select></td>
            </tr>
            <tr>
                <th align="right">模板内容：</th>
                <td class="form_table_content" colspan="3"><textarea name="vmTemplate" id="vmTemplate" rows="5" class="form_input" style="width: 100%;height: 120px"></textarea></td>
            </tr>
            <tr>
                <th align="right">分块步骤：</th>
                <td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="100" name="step" id="step"/></td>
            </tr>
            <tr>
                <th align="right">备注：</th>
                <td class="form_table_content" colspan="3"><textarea name="desc" id="desc" rows="5" class="form_input" style="width: 100%;height: 120px"></textarea></td>
            </tr>
        </table>
        <!-- 展示信息内容区域结束 -->
        <!-- 按钮操作区域开始 -->
        <table width="100%" height="40">
            <tr>
                <td align="center">
                    <a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
                    <a href="javascript:void(0)" class="btn" onClick="document.editForm.reset();">清除重填</a>
                </td>
            </tr>
        </table>
        <!-- 按钮操作区域结束 -->
    </form>
</div>
</body>
</html>