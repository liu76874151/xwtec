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

    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/page_manage/page/page_info_edit.js"></script>
</head>

<body>
<input type="hidden" id="pkid_1" value="${param.pkid_1}">
<input type="hidden" id="pkid_2" value="${param.pkid_2}">
<div id="main_div" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>页面信息--修改</div>
    <form name="editForm" id="editForm" action="" method="post">
        <!-- 展示信息内容区域开始 -->
        <table width="98%" class="tb">
            <tr>
                <th align="right">页面编码：</th>
                <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="pageNum" value="" id="pageNum" readonly="readonly"/></td>
                <th align="right">页面名称：</th>
                <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="pageName" value="" id="pageName"/></td>
            </tr>
            <tr>
                <th align="right">页面版本：</th>
                <td class="form_table_content">
                    <select name="version" id="version" disabled="disabled">
                        <option value="2">标准版</option>
                        <option value="3">触屏版</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="right">页面内容</th>
                <td class="form_table_content" colspan="3"><textarea name="pageContent" id="pageContent" rows="5" class="form_input" style="width: 100%; height: 100%"></textarea></td>
            </tr>
            <tr>
                <th align="right">二次确认组件：</th>
                <td class="form_table_content">
                    <select name="confirmComp" id="confirmComp">
                        <option value="">否</option>
                        <option value="confirmComp">是</option>
                    </select>
                </td>
                <th align="right">结果组件：</th>
                <td class="form_table_content">
                    <select name="resultComp" id="resultComp">
                        <option value="">否</option>
                        <option value="resultComp">是</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="right">类型：</th>
                <td class="form_table_content" colspan="3">
                    <select name="type" id="type">
                        <option value="0">普版</option>
                        <option value="1">模板</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="right">备注：</th>
                <td class="form_table_content" colspan="3"><textarea name="desc" id="desc" rows="5" class="form_input" style="width: 100%; height: 100%"></textarea></td>
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