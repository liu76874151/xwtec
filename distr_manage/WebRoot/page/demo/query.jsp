<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<html>
	<head>
    	<title>用户组维护</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
		<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
		<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>        
		<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	</head>
  	<body>
  		<div title="" style="padding:10px;overflow:auto;">
	 		<div class="breadcrumb"><span></span>用户信息--查询</div>
	  		<fieldset class="defaultFieldset">
	  			<legend>用户查询</legend>
	    		<form id="queryForm" name='queryForm' action="" method="post">
		      		<table width="98%" class="defaultTable tableStyle1">
		        	<tr> 
			      		<th width="20%">管理员名称：</th>
			      		<td width="30%"><input type="text" maxlength="100" name="userName" value="12314123"><span class="errorMsg"></span></td>
			  	  		<th width="20%">所属组：</th>
			      		<td width="30%"><input type="text" maxlength="100" name="userGroup" value=""></td>
			    	</tr>
			    	<tr>
			    		<th>用户类型：</th>
			      		<td>
				         	<select name="userType" id="userType">
							   	<option value="">--请选择--</option>
						       	<option value="0">管理员</option>
						       	<option value="1">普通用户</option>
						 	</select>
			      		</td>
			      	</tr>
			    	<tr>
		          		<td colspan="4" style="text-align:center;">
				        	<a href="javascript:void(0)" class="btn" onclick="alert($('#queryForm').valid());">查 询</a>
				        	<a href="javascript:void(0)" class="btn"  onclick="document.queryForm.reset();">重 置</a>
		          		</td>
		        	</tr>
					</table>
	       		</form>
			</fieldset>
			<div id="toolbar"></div>
			<div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
		</div>
  	</body>
  	<script type="text/javascript">
  		var mygrid = new dhtmlXGridObject('gridbox');
		mygrid.setImagePath(GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/imgs/");
		mygrid.setHeader("Sales, Book Title, Author");
		mygrid.setInitWidths("70,250,*");
		mygrid.setColAlign("right,left,left");
		mygrid.setColTypes("dyn,ed,ed");
		mygrid.setColSorting("int,str,str");
		mygrid.init();
		mygrid.setSkin("dhx_skyblue");
		mygrid.load("../common/data.json", "json");
		
		var userinfoToolBar=new dhtmlXToolbarObject('toolbar');
		//userinfoToolBar.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/imgs/");
		userinfoToolBar.addButton("查看", 0, "查看", GLOBAL_INFO.CONTEXTPATH+"/resource/img/view.gif",  GLOBAL_INFO.CONTEXTPATH+"/resource/img/view.gif");
		userinfoToolBar.addSeparator("sep0", 1);
		userinfoToolBar.addButton("新增", 2, "新增", GLOBAL_INFO.CONTEXTPATH+"/resource/img/new.gif",  GLOBAL_INFO.CONTEXTPATH+"/resource/img/new.gif");
		userinfoToolBar.addSeparator("sep1", 3);
		userinfoToolBar.addButton("修改", 4, "修改",  GLOBAL_INFO.CONTEXTPATH+"/resource/img/paste.gif",  GLOBAL_INFO.CONTEXTPATH+"/resource/img/paste.gif");
		userinfoToolBar.addSeparator("sep2", 5);
		userinfoToolBar.addButton("删除", 6,  "删除", GLOBAL_INFO.CONTEXTPATH+"/resource/img/close.gif",  GLOBAL_INFO.CONTEXTPATH+"/resource/img/close.gif");
		userinfoToolBar.setIconSize(18);
		userinfoToolBar.attachEvent("onClick", function(id) {
		var selectRowId = mygrid.getSelectedRowId();
		if(selectRowId || id == "新增"){
			alert(id+", "+selectRowId);
		}else{
			alert("请选中需操作的数据");
		}
	    });
	    
	    ValidateUtil.validate({
	    	targetForm : "queryForm",
	    	rules : {
				funcId:{required : true, min : 2} 
			},
			messages : {
				funcId: {required : "请输入用户名", min : "必须大于{0}"}
			}
	    });
  	</script>
</html>