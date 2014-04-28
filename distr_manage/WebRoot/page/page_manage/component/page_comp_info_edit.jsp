<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>修改页面组件</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>

    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>

    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/page_manage/component/page_comp_info_edit.js"></script>
</head>

<body>
<input type="hidden" id="pkid" value="${param.pkid}">
<div id="main_div" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>页面组件--修改</div>
    <form name="editForm" id="editForm" action="" method="post">
        <!-- 展示信息内容区域开始 -->
        <table width="98%" class="tb">
            <tr>
                <th align="right">组件编码：</th>
                <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="compNum" value="" id="compNum"/></td>
                <th align="right">组件名称：</th>
                <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="compName" value="" id="compName"/></td>
            </tr>
            <tr>
                <th align="right">组件类型：</th>
                <td class="form_table_content" colspan="3">
                    <select name="compType" id="compType">
                        <option value="1">静态组件</option>
                        <option value="2">动态组件</option>
                        <option value="3">货架组件</option>
                        <option value="4">二次确认组件</option>
                        <option value="5">处理结果组件</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="right">组件URL：</th>
                <td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="50" name="compUrl" value="" id="compUrl"/></td>
            </tr>
            <tr>
                <th align="right">关联区域：</th>
                <td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="100" name="containerId" id="containerId"/></td>
            </tr>
            <tr>
                <th align="right">备注：</th>
                <td class="form_table_content" colspan="3"><textarea name="desc" id="desc" rows="5" class="form_input"></textarea></td>
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