<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>业务标签</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxPopup/codebase/skins/dhtmlxpopup_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css"/>	
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css"/>
		
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
		
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript"src="${contextPath}/resource/scripts/uompDialog.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessTag/businessTag-modify.js"></script>
</head>
<body>
<div title="" style="padding:10px;overflow:auto;">
<input type="hidden" value="${param.tagId }" id="tagId" name="tagId">
    <div class="breadcrumb"><span></span>业务标签--修改</div>
    <fieldset class="defaultFieldset">
        <legend>业务标签修改</legend>
        <form id="addForm" name='addForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
                <tr>
                    <th width="20%">业务标签名称：</th>
                    <td width="50%"><input type="text" maxlength="50" name="tagName" id="tagName" value=""><span class="errorMsg"></span></td>
                </tr>
                <tr>
                	<th>业务标签英文名：</th>
                    <td><input type="text" maxlength="50" name="tagEname" id="tagEname" value=""><span class="errorMsg"></span></td>
                </tr>
                <tr>
                	<th>标签说明：</th>
                    <td>
                    	<textarea rows="8" cols="50" id="tagDesc" name="tagDesc" onkeyup="this.value = this.value.slice(0, 250)"></textarea><span class="errorMsg"></span>
                    </td>
                </tr>
            </table>
            <!-- 按钮操作区域开始 -->
			<table width="100%" height="40">
				<tr>
			    	<td align="center">
					<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					<a href="javascript:void(0)" class="btn" onClick="document.addForm.reset();">清除重填</a>
					</td>
				</tr>
	   		</table>
        </form>
    </fieldset>
</div>
</body>
</html>