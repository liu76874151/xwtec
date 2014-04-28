<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户组信息</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/ext/dhtmlxtree_xw.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  </head>
  
  <body>
    <!--tree定义开始-->
    <input type="hidden" id="treeStrHidden" value="">
	<div id="templet_left" style="height:100%;">
	    <div id="group_tree" style="height: 100%"> 
        </div>
	</div>
	
	<!-- 主页面 -->
	<div id="templet_main" style="height:100%">
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//初始化布局
			initLayout();
	
			//加载树  调用后台方法，获取数据
			getTreeData();
		});
		//树对象
		var groupInfoQTree;
		//布局对象
		var groupInfoLayout;
		
		//初始化布局
		function initLayout(){
			groupInfoLayout = new dhtmlXLayoutObject(document.body, "2U");
			groupInfoLayout.cells("a").setText("菜单导航区");
			groupInfoLayout.cells("a").hideHeader();
			groupInfoLayout.cells("a").setWidth(230);
			groupInfoLayout.cells("a").attachObject("templet_left");

			groupInfoLayout.cells("b").setText("主显示区");
			groupInfoLayout.cells("b").hideHeader();
			groupInfoLayout.cells("b").attachObject("templet_main");

			//生成树对象
			loadTree();
		}

		//生成树对象
		function loadTree(){
			groupInfoQTree = DhtmlxUtis.createTree(group_tree, this.tonclick);
		}

		//加载树  调用后台方法，获取数据
		function getTreeData()
		{
			$.singleReq({
				data :
				{
					"reqUrl" : "privilegeinfo",
					"reqMethod" : "queryUserInfo"
				},
				success : function(ret)
				{
					if(ret)
					{
						if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS)
						{
							groupInfoQTree.loadJSONObject(ret.retObj,function(){
							groupInfoQTree.loadOpenStates();
							});
						}
						else
						{
							UOMPComp.showFailedDialog(ret.resMsg);
						}
						
					}
				}
			});
		}

		//树点击事件
		function tonclick(id) {
			var groupId = groupInfoQTree.getUserData(id, "groupId");
			var userType = groupInfoQTree.getUserData(id, "userType");
			if(userType != "1")
			{
				userType = "0";
			}
			groupInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/user_privilege_edit.jsp?userId="+groupId+"&userType=" + userType);
		}
	</script>
  </body>
</html>
