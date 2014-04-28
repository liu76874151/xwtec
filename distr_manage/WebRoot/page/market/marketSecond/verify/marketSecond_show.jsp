<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@page import="java.net.URLDecoder"%>
<%@include file="../../../taglibs.jsp" %>
<html>
<head>
    <title>关联的二级营销方案</title>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
    
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
	
	
	
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uompDialog.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/market/marketSecond/verify/marketSecond_show.js"></script>
</head>
<body>
<input type="hidden" value="${param.marketFirstPkid }" id="marketFirstPkid">
<input type="hidden" value="${param.tchannal }" id="tchannal">
<input type="hidden" value="${param.brand }" id="brand">
<input type="hidden" value="<%=URLDecoder.decode(request.getParameter("marketFirstName"),"utf-8") %>" id="marketFirstName">
<input type="hidden" value="${param.marketFirstCode }" id="marketFirstCode"/>
<input type="hidden" value="${param.cityId }" id="cityId"/>
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>关联的二级营销方案</div>
    
    <div id="toolbar"></div>
    
    <div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
   
    <div id="paging"></div>
    
</div>
</body>
</html>