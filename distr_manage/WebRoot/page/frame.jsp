<%@ page contentType="text/html; charset=UTF-8"  isELIgnored="false" %>
<%@include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>江苏移动统一管理平台</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_skyblue.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css"/>	
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css"/>
<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/dhtmlxaccordion.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_start.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/main.js" language="javascript"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/uompDialog.js" language="javascript"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/frame.js" language="javascript"></script>
</head>
<body>
	<!--头部区域定义开始-->
	<div id="fra_Top" class="main-top">
		<h1 class="siteLogo">江苏移动统一管理平台</h1>
		<div class="rightInfo">
	  		<ul class="topMenu">
	     		<p>
	     			<a href="#"><span class="main-top-ico ico-qhyh"></span> 切换用户</a> |
	     		    <a href="#" onclick="frameComponent.modifyPasswd();"><span class="main-top-ico ico-xgmm" ></span> 修改密码</a> |
	     		    <a href="javascript:frameComponent.logout();"><span class="main-top-ico ico-tc"></span> 退出</a>
	     		</p>
      			<p>
      				<span class="main-top-ico ico-yh"></span> 当前用户：<span class="rightInfo-username" id="userName">admin</span>
      			</p>
	  		</ul>
		</div>
	</div>
	<!--头部区域定义结束-->

	<!--菜单TAB条定义开始-->
	<div id="men_tabbar" style="height:100%;"></div>
	<div id="men_1"></div>
	<div id="men_2" style="overflow: auto;height: 100%;">
	     <div id="custem_tree_frame" style="width:100%;height:100%;overflow: auto;" > </div>
	</div>
	<!--菜单TAB条定义结束-->
	
	<!--主功能区域定义开始-->
	<div id="frm_main" style="height:100%;">
	    <div id="main_tabbar" style="height:100%;">
	    </div>
	</div>
</body>
</html>