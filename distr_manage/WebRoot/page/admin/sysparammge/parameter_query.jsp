<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../../taglibs.jsp"%>
<html>
	<head>
		<title>系统参数</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	</head>
	<body>
		<div title="" style="padding: 10px; overflow: auto;">
			<div class="breadcrumb">
				<span></span> 系统参数管理
			</div>
			<fieldset class="defaultFieldset">
				<legend>系统参数查询 </legend>
				<form id="queryForm" name='queryForm' action="" method="post">
					<table width="98%" class="defaultTable tableStyle1">
						<tr>
							<th width="20%">所属系统:</th>
							<td width="30%">
								<select name="sysnum" id="sysnum">
									<option value="">
										---请选择---
									</option>
								</select>
							</td>
							<th width="20%">参数名称:</th>
							<td width="30%">
								<input type="text" maxlength="100" name="csname" id="csname" value="">
							</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: center;">
								<a href="javascript:void(0)" class="btn" onclick="query()">查 询</a>
								<a href="javascript:void(0)" class="btn" onclick="document.queryForm.reset();">重 置</a>
							</td>
						</tr>
					</table>
				</form>
			</fieldset>
			<div id="toolbar"></div>
			<div id="gridbox" style="width: 600px; height: 270px; background-color: white; width: 100%"></div>
		</div>
	</body>
	<script type="text/javascript">
	var mygrid;
	$(document).ready(
		function() {
			mygrid = DhtmlxUtis.createGrid('gridbox', {
				"header"     : "序号,子系统编码,子系统名称,参数名称,参数值,备注",
				"initWidth"  : "80,80,120,120,120,*",
				"colAlign"   : "center,center,center,center,center,center",
				"colTypes"   : "ro,ro,ro,ro,ro,ro",
				"colSorting" : "str,str,str,str,str,str"
			});
			var userinfoToolBar = DhtmlxUtis.createTableToolbar('toolbar', mygrid, {
				"add" : {
					"title" : "系统参数-新增",
					"url" : GLOBAL_INFO.CONTEXTPATH + "/page/admin/sysparammge/parameter_add.jsp", 
					"callback" : function(){
				           query();
					}
				},
				"modify" : {
					"url" : GLOBAL_INFO.CONTEXTPATH + "/page/admin/sysparammge/parameter_edit.jsp", 
					"param" : [
						{"name" : "sysnum", "colIndex" : "1"},
						{"name" : "csname", "colIndex" : "3"}
					],
					"callback" : function(){
					       query();
					}
				},
				"delete" : {"url" : "", "click" : function(){
					var selectRowId = mygrid.getSelectedRowId();
					var sysnum =mygrid.cells(selectRowId, 1).getValue();
					var csname =mygrid.cells(selectRowId, 3).getValue();
					$.singleReq( {
							data : {
								"sysnum" : sysnum,
								"csname" : csname,
								"reqUrl" : "sysparmmage",
								"reqMethod" : "delSysParms"
							},
							success : function(ret) {
								if ("true" == ret.retObj) {
									UOMPComp.showSuccessDialog("删除成功");
									query();
								} else {
									UOMPComp.showFailedDialog("删除失败");
								}
							}
						});
				}}
			});
			loadDate("", "");
			$.singleReq({
				data : {
					"reqUrl" : "sysparmmage",
					"reqMethod" : "getSysArr"
				},
				success : function(ret) {
					var jsonArr = eval("(" + ret.retObj + ")");
					for ( var i = 0; i < jsonArr.length; i++) {
						$("#sysnum").append("<option value=\"" + jsonArr[i].sysnum+ "\">" + jsonArr[i].sysname+ "</option>");
					}
				}
			});
});

function loadDate(sysnum, csname) {
	$.singleReq( {
		data : {
			"sysnum" : sysnum,
			"csname" : csname,
			"reqUrl" : "sysparmmage",
			"reqMethod" : "query"
		},
		success : function(ret) {
			var jsonArr = eval("(" + ret.retObj + ")");
			var datas = [];
			for ( var i = 0; i < jsonArr.length; i++) {
				var subSysNum = jsonArr[i].subSysNum;
				var subSysName = jsonArr[i].subSysName;
				var csmc = jsonArr[i].csmc;
				var csz = jsonArr[i].csz;
				var bz = jsonArr[i].bz;
				var data = [ i + 1, subSysNum, subSysName, csmc, csz, bz ];
				datas[i] = data;
			}
			mygrid.clearAll();
			mygrid.parse(datas, "jsarray");
		}
	});
}
function query() {
	loadDate($('#sysnum').val(), $('#csname').val());
}
</script>
</html>
