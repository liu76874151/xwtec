<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>业务信息-查看</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"	href="${contextPath}/resource/css/frame.css" />
		<script type="text/javascript"	src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript"	src="${contextPath}/resource/scripts/main.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessinfo/businessInfo_view.js"></script>
	</head>

	<body>
		<input type="hidden" id="pkid" value="${param.funId}">
		<div style="padding: 10px; overflow: auto;">
			<div class="breadcrumb">
				<span></span>业务信息--查看
			</div>
			<!-- 展示信息内容区域开始 -->
			<table width="96%" cellspacing="1" class="form_table">
				<tr>
					<td class="view_info_title" style="width: 20%">
						业务名称：
					</td>
					<td class="view_info_content" style="width: 80%" >
						<input type="text" id="busiName"  disabled="disabled"/> &nbsp;<button>复制业务信息</button>
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						业务编码：
					</td>
					<td class="view_info_content" style="width: 80%">
						<input type="text"  id="busisNum"  disabled="disabled"/> 
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						名称拼音码(简拼)：
					</td>
					<td class="view_info_content" style="width: 80%">
						<input type="text"  id="busiNameJianpin" disabled="disabled"/> 
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						开通生效方式 ：
					</td>
					<td class="view_info_content" style="width: 80%" >
						<input type="checkbox" id="effectWay0" value="0" disabled="disabled">&nbsp;即时生效
						<input type="checkbox" id="effectWay1" value="1" disabled="disabled">&nbsp;次日生效
						<input type="checkbox" id="effectWay2" value="2" disabled="disabled">&nbsp;次月生效
						<input type="checkbox" id="effectWay3" value="3" disabled="disabled">&nbsp;次次月生效
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						关闭生效方式 ：
					</td>
					<td class="view_info_content" style="width: 80%" >
						<input type="checkbox" id="neffectWay0" value="0" disabled="disabled">&nbsp;即时生效
						<input type="checkbox" id="neffectWay1" value="1" disabled="disabled">&nbsp;次日生效
						<input type="checkbox" id="neffectWay2" value="2" disabled="disabled">&nbsp;次月生效
						<input type="checkbox" id="neffectWay3" value="3" disabled="disabled">&nbsp;次次月生效
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						业务状态：
					</td>
					<td class="view_info_content" style="width: 80%">
						<input type="text"  id="state" disabled="disabled"/> 
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						支付方式：
					</td>
					<td class="view_info_content" style="width: 80%">
						<input type="text"  id=""/> 
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						资费类别：
					</td>
					<td class="view_info_content" style="width: 80%">
						<input type="text"  id=""/> 
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						扣费方式：
					</td>
					<td class="view_info_content" style="width: 80%">
						<input type="text"  id=""/> 
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						积分所需数量：
					</td>
					<td class="view_info_content" style="width: 80%">
						<input type="text"  id=""/> 
					</td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						M值支付所需数量：
					</td>
					<td class="view_info_content" style="width: 80%">
						<input type="text"  id=""/> 
					</td>
				</tr>
			</table>
			<!--详细信息区 结束-->
			</div>
	</body>
</html>
