<%@ page language="java" import="java.util.*"   pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<html>
  <head>
    
    <title>自定义菜单查询页面</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxMenu/codebase/skins/dhtmlxmenu_dhx_skyblue.css">
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/dhtmlxaccordion.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_start.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxMenu/codebase/dhtmlxmenu.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxMenu/codebase/ext/dhtmlxmenu_ext.js"></script>    
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/content/content.js"></script>
    
  
    </head>
   <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
   <!--tree定义开始  -->
   <input type="hidden" id="channelNum" value="${param.channelNum}">
    <input type="hidden" id="treeStrHidden" value="">
	<div id="templet_left" style="height:100%;">
	    <div id="custom_tree" style="height: 100%"  class="dhtmlxTree" > 
        </div>
        <div style="height: 5px;"><div id="menuObj"></div></div>
	</div>
	<!-- 主页面 -->
	<div id="templet_main" style="height:100%">
	 	<div id="main_tabbar" style="height:100%;">
	    </div>    
	</div>
	<!-- 当前鼠标的位置 -->
    <input type="hidden" id=mouseXPosition><input type="hidden" id=mouseYPosition>
  </body>
</html>
