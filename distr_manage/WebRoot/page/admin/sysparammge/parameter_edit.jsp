<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@page import="java.net.URLDecoder"%>
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
				<span></span>系统参数--修改
			</div>
			<form name="addForm" id="addForm" action="" method="post">
				<!-- 展示信息内容区域开始 -->
				<input type="hidden" class="form_input" id="sysnum" value="${param.sysnum}" />
				<table width="98%" class="tb">
					<tr>
						<th align="right"> 所属系统: </th>
						<td class="form_table_content" colspan="3">
							<input type="text" class="form_input" maxlength="100" name="sysname" id="sysname" readonly="readonly" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<th align="right"> 参数名称: </th>
						<td class="form_table_content">
							<input type="text" class="form_input" maxlength="100" name="csname" id="csname" readonly="readonly" value="${param.csname}" disabled="disabled" />
						</td>
						<th align="right"> 参数值: </th>
						<td class="form_table_content">
							<input type="text" class="form_input" maxlength="100" name="csz" id="csz" /><span class="errorMsg"></span>
						</td>
					</tr>
					<tr>
						<th align="right"> 备注: </th>
						<td class="form_table_content" colspan="3">
							<textarea class="form_text" name="bz" id="bz"></textarea>
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
$(document).ready(function() {
	ValidateUtil.validate({
		    	targetForm : "addForm",
		    	   rules : {
	 		          csz : {required : true} 
		           },
		           messages : {
			          csz : {required : "请输入参数值"}
		           }
		    });
	
	$.singleReq( {
		data : {
			"sysnum" : $("#sysnum").val(),
			"csname" : $("#csname").val(),
			"reqUrl" : "sysparmmage",
			"reqMethod" : "getSysParmsById"
		},
		success : function(ret) {
			var jsonObj = eval("(" + ret.retObj + ")");
			$("#sysname").attr("value", jsonObj.subSysName);
			$("#csz").attr("value", jsonObj.csz);
			$("#bz").attr("value", jsonObj.bz);
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
			"reqMethod" : "modifySysParms"
		},
		success : function(ret) {
			if ("true" == ret.retObj) {
				top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
  			    top.UOMPDialog.hideSubPage();
  			    UOMPComp.showSuccessDialog("修改成功");
			} else {
				UOMPComp.showFailedDialog("修改失败");
			}
		}
	});
	}
}
</script>
</html>