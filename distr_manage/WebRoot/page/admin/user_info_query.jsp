<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户</title>
	<script src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript" type="text/javascript"></script>
	<script src="${contextPath}/resource/scripts/main.js" language="javascript" type="text/javascript"></script>
	
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css">		
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxMenu/codebase/skins/dhtmlxmenu_dhx_skyblue.css">
	
	
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/dhtmlxaccordion.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_start.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxMenu/codebase/dhtmlxmenu.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxMenu/codebase/ext/dhtmlxmenu_ext.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/user_info_query.js"></script>
  </head>
  
  <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
   <!--tree定义开始-->
    <input type="hidden" id="treeStrHidden" value="">
	<div id="templet_left" style="height:100%;">
	    <div id="group_tree" style="height: 100%"  class="dhtmlxTree" > 
    
        </div>
        
         <div style="height: 250px;"><div id="menuObj"></div></div>
	</div>
	
	<!-- 主页面 -->
	<div id="templet_main" style="width:100%; height:100%">
		
	</div>
  </body>
</html>
