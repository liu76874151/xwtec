<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>业务标签</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/busiTag/css/sysStyle.css" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessTag/businessTag-query.js"></script>
</head>
<body style="overflow: auto;">
<input type="hidden" id="channelNumP" name="channelNumP" value="${param.channelNum }">
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>业务标签--管理</div>
     <!-- 按钮操作区域开始 -->
	<table width="100%" height="40">
		<tr>
	    	<td align="right">
			<a href="javascript:void(0)" class="btn" onClick="component.addBusiTag();">新增</a>
			<a href="javascript:void(0)" class="btn" onClick="component.deleteBusiTag();">删除</a>
			</td>
		</tr>
	</table>
     <div class="service-classification">
  <!--网厅个人登录菜单分类-->
  <div class="classification-list">
    <div class="classification-list-body">
      <div class="classification-item" id="busiTag">
        <dl class="classification-item-con three-classification" id="toggle_box3" >
          <dd class="classification-item-detail">
            <p>
            </p>
          </dd>
        </dl>
      </div>
    </div>
  </div>
  <!--网厅个人登录菜单分类-->
</div>
</div>
</body>
</html>