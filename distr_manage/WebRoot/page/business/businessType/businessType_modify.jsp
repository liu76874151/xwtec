<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>业务类型</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessType/businessType-modify.js"></script>
</head>

<body>
<input type="hidden" id="jbNum" value="${param.jbNum }">
<input type="hidden" id="parjbNum" value="${param.parjbNum }">
<input type="hidden" id="jb" value="${param.jb }">
<input type="hidden" id="channelNum" value="${param.channelNum }">
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>业务类型--修改</div>
    <fieldset class="defaultFieldset">
        <legend>业务类型修改</legend>
        <form id="addForm" name='addForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
                <tr>
                    <th width="10%">业务类型编码：</th>
                    <td width="30%">
                    <input type="text" id="busiTypeNum" name="busiTypeNum" maxlength="15" value="${param.busiTypeNum }" readonly="readonly">
                    <span class="errorMsg"></span></td>
                </tr>
                <tr>
                    <th width="10%">业务类型名称：</th>
                    <td width="30%">
                    <input type="text" id="busiTypeName" name="busiTypeName" maxlength="100" size="30">
                    <span class="errorMsg"></span></td>
                </tr>
                <tr>
                    <th width="10%">业务类型说明：</th>
                    <td width="30%">
                    <textarea rows="5" cols="50" id="busiTypeDesc" name="busiTypeDesc"></textarea>
                    <span class="errorMsg"></span></td>
                </tr>
                <tr>
                    <th width="10%">业务类型营销：</th>
                    <td width="30%">
                    <textarea rows="5" cols="50" id="busiTypeYx" name="busiTypeYx"></textarea>
                    <span class="errorMsg"></span></td>
                </tr>
                <tr>
                    <th width="10%">加载页面类型：</th>
                    <td width="30%">
                    <select id="loadType" name="loadType">
                    	<option value="0">普通页面</option>
                    	<option value="1">业务页面</option>
                    	<option value="2">业务列表页面</option>
                    	<option value="3">页面链接</option>
                    </select>
                    <span class="errorMsg"></span></td>
                </tr>
                <tr>
                    <th width="10%">是否末级：</th>
                    <td width="30%">
                    <select id="mj" name="mj">
                    	<option value="0">否</option>
                    	<option value="1">是</option>
                    </select>
                    <span class="errorMsg"></span></td>
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