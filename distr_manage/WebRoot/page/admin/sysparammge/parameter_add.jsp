<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../../taglibs.jsp"%>
<html>
	<head>
		<title>系统参数</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css" />
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	</head>
	<body>
		<div id="main_div" style="padding: 10px; overflow: auto;">
			<div class="breadcrumb">
				<span></span>系统参数--添加
			</div>
			<form name="addForm" id="addForm" action="" method="post">
				<!-- 展示信息内容区域开始 -->
				<table width="98%" class="tb">
					<tr>
						<th align="right">所属系统:</th>
						<td class="form_table_content" colspan="3">
							<select name="sysnum" id="sysnum">
								<option value="">
									---请选择---
								</option>
							</select>
							<span class="errorMsg"></span>
						</td>
					</tr>
					<tr>
						<th align="right">参数名称:</th>
						<td class="form_table_content">
							<input type="text" class="form_input" maxlength="100" name="csname" id="csname" /><span class="errorMsg"></span>
						</td>
						<th align="right">参数值:</th>
						<td class="form_table_content">
							<input type="text" class="form_input" maxlength="100" name="csz" id="csz" /><span class="errorMsg"></span>
						</td>
					</tr>
					<tr>
						<th align="right">备注:</th>
						<td class="form_table_content" colspan="3">
							<textarea class="form_text" name="bz" id="bz"></textarea><span class="errorMsg"></span>
						</td>
					</tr>
				</table>
				<!-- 展示信息内容区域结束 -->
				<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
					<tr>
						<td align="center">
							<a href="javascript:void(0)" class="btn" onclick="addSubmit();">确认提交</a>
						</td>
					</tr>
				</table>
				<!-- 按钮操作区域结束 -->
			</form>
		</div>
	</body>
<script type="text/javascript">
$(document).ready(
		function() {
			ValidateUtil.validate({
		    	targetForm : "addForm",
		    	   rules : {
	 		          sysnum : {required : true} ,
	 		          csname : {required : true} ,
	 		          csz : {required : true} 
		           },
		           messages : {
			          sysnum : {required : "请选择所属系统"},
			          csname : {required : "请输入参数名称"},
			          csz : {required : "请输入参数值"}
		           }
		    });
			$.singleReq( {
				data : {
					"reqUrl" : "sysparmmage",
					"reqMethod" : "getSysArr"
				},
				success : function(ret) {
					var jsonArr = eval("(" + ret.retObj + ")");
					for ( var i = 0; i < jsonArr.length; i++) {
						$("#sysnum").append(
								"<option value=\"" + jsonArr[i].sysnum + "\">"
										+ jsonArr[i].sysname + "</option>");
					}
				}
			});
		});

function addSubmit() {
    if($('#addForm').valid()){
	var sysnum = $("#sysnum").val();
	var csname = $("#csname").val();
	var csz = $("#csz").val();
	var bz = $("#bz").val();
	$.singleReq( {
		data : {
			"sysnum" : sysnum,
			"csname" : csname,
			"csz" : csz,
			"bz" : bz,
			"reqUrl" : "sysparmmage",
			"reqMethod" : "addSysParms"
		},
		success : function(ret) {
			if ("true" == ret.retObj) {
				top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
  			    top.UOMPDialog.hideSubPage();
				UOMPComp.showSuccessDialog("添加成功");
			} else {
				UOMPComp.showFailedDialog("添加失败");
			}
		}
	});
	}
}
</script>
</html>