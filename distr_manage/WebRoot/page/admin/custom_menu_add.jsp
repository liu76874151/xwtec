<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%@page import="java.net.URLDecoder"%>
<%
String jbNum = URLDecoder.decode(request.getParameter("jbNum"),"utf8");
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>自定义菜单添加</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css"/>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/util.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/custom_menu_add.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
 </head>
  
  <body>
    <input type="hidden" id="jbNum" name="jbNum"  value="<%=jbNum %>" /> 
    <div class="breadcrumb-iframe">自定义菜单--添加</div>
	<div id="parentId" style="position:static; width:100%;height:100%;  top: 0px; left: 0px; aborder: #B5CDE4 1px solid;"></div>
    <div id="privilegeIdObj" style="width:100%; height: 100%; display: none; font-family: Tahoma; font-size: 11px;">
         <!-- 用户拥有的权限树 -->
        <div style="width: 100%; height: 100%;">
            <label class="view_info_title" style="height: 15px; width:100%;;text-align: left; ">已拥有的权限</label>
            <div id="privilege_tree" style="width:100%;height:100%;" > </div>
        </div>
    </div>
    <div id="czqyObj"  align="center" style="width: 100%; height: 100%;">
      <div align="center">
	        <label class="view_info_title" style="height: 15px; width: 100%;text-align: center;">操作选项</label>
	        <table width="100%;" height="50%" align="center">
			<tr align="center">
	    		<td align="center">	  
	    			<a href="javascript:void(0)" style="" class="btn" onclick="getFunIdToRight();">增加-＞</a>
				</td>
			</tr>
			<tr align="center">
				<td align="center">	 
	    			<a href="javascript:void(0)" class="btn" onclick="removeFunIdFromRight();"> ＜－移除</a>
	    		</td>
			</tr>
			<tr align="center">
				<td align="center">	 
	    			<a href="javascript:void(0)" class="btn" onclick="doAllCheck();">全选</a>
	    		</td>
			</tr>
			<tr align="center">
	    		<td align="center">	  
	    			<a href="javascript:void(0)" class="btn" onclick="doReset();">清除重选</a>
				</td>
			</tr>
			<tr align="center">
	    		<td align="center">	  
	    		<a href="javascript:void(0)" class="btn" onclick="doSubmit();">确认提交</a>
				</td>
			</tr>		
	     </table>
       <div>
    </div>
     <div id="sjlbObj"  align="center" style="width:100%; height: 100%;">
         <div style="overflow: auto">
              <label class="view_info_title" style="height: 15px; width: 100%;text-align: left">已选择的模块</label>
              <div id="gridbox" style="width:100%; height:700px; background-color: white; "></div>
         </div>
     </div>
  </body>
</html>