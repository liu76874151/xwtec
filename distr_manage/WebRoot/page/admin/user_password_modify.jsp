<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理员信息修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css"
			href="${contextPath}/resource/css/frame.css" />
		<script language="javascript" type="text/javascript"
			src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script language="javascript" type="text/javascript"
			src="${contextPath}/resource/scripts/uomp/admin/user_password_modify.js"></script>
		<script language="javascript" type="text/javascript"
			src="${contextPath}/resource/scripts/main.js"></script>
			<script type="text/javascript" src="${contextPath}/frame_res/js/jquery-easyui/jquery.easyui.min.js"></script>
			<script type="text/javascript" src="${contextPath}/frame_res/js/constant.js"></script>
			<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	</head>
	<body>
		<input type="hidden" value="" name="groupId" id="groupId">
		<div title="" style="padding: 10px; overflow: auto;">
			<div class="breadcrumb">
				<span>修改密码</span>
			</div>
			<fieldset class="defaultFieldset">
				<legend>
					修改密码
				</legend>
				<form id="modifyForm" name='modifyForm' action="" method="post">
					<table width="98%" class="defaultTable tableStyle1">
						<tr>
							<th width="40%">
							<span class="required">*</span>您的旧密码： 
							</th>
							<td>
								<input type="text" maxlength="100" name="oldPasswd" id="oldPasswd"> <span class="errorMsg"></span>
							</td>
						</tr>
						<tr>
							<th width="40%">
							<span class="required">*</span>输入新密码：
							</th>
							<td>
								<input type="text" maxlength="100" name="newPasswd" id="newPasswd"> <span class="errorMsg"></span>
							</td>
						</tr>
						<tr>
							<th width="40%">
							<span class="required">*</span>	新密码确认：
							</th>
							<td>
								<input type="text" maxlength="100" name="comfiPasswd" id="comfiPasswd"> <span class="errorMsg"></span>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: center;">
								<a href="javascript:void(0)" class="btn"
									onclick="userPasswordVComponent.modifyPasswd();">确认提交</a>
								<a href="javascript:void(0)" class="btn"
									onclick="document.modifyForm.reset();">重 置</a>
							</td>
						</tr>
					</table>
				</form>
			</fieldset>
		</div>
	</body>

</html>