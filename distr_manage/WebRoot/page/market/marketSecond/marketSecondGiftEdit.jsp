<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>礼品编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="礼品编辑">
	
	<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/resource/css/frame.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript"	src="<%= request.getContextPath() %>/resource/scripts/main.js"></script>
  </head>
  <body>
    <div style="padding: 10px; overflow: auto;">
		<div class="breadcrumb">
			<span></span>礼品信息--修改
		</div>
		<form name="editForm" id="editForm" action="" method="post">
		<input type="hidden" id="channel" name="channel" value="<%=request.getParameter("channel") %>">
		<table class="tb">
			<tr>
				<th align="right" width="20%">礼品名称：</th>
				<td colspan="3" class="form_table_content"><input type="text" class="form_input" maxlength="50" name="giftName" value="" id="giftName" /><span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th align="right">礼品介绍：</th>
				<td colspan="3" class="form_table_content">
				<textarea rows="5" cols="30"  name="giftComment" id="giftComment" >
				</textarea><span class="errorMsg"></span>
				</td>
 			</tr>
		</table>
		<!-- 按钮操作区域开始 -->
		<table width="100%" height="40">
			<tr>
		    	<td align="center">
				<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认</a>
				</td>
			</tr>
   		</table>
   		<!-- 按钮操作区域结束 -->
		</form>	
	</div>
  </body>
  <script type="text/javascript">
 $(document).ready(function () {
      component.initForm();
      component.initValidate();
  }
);

var component = {
		// 初始化校验规则
		initValidate: function () {
	    ValidateUtil.validate({
	        targetForm: "editForm",
	        rules: {
	    		giftName: {required: true, minlength: 1, maxlength: 200}
	        },
	        messages: {
	        	giftName: {required: "请输入礼品名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
	        }
	    });
	},

  initForm: function () {
 		var channel=$("#channel").val();
		var option = $("#bossGiftInfo"+channel+" option:selected",opener.document);
		var giftComment = option.attr("giftComment");
		var giftName = option.text();

		$("#giftName").val(giftName);
		$("#giftComment").text(giftComment);
  },

  saveForm: function () {
     var channel=$("#channel").val();
	  var option = $("#bossGiftInfo"+channel+"  option:selected",opener.document);
	  var giftComment = $("#giftComment").val();
	  var giftName = $("#giftName").val();

	  option.attr("giftComment",giftComment);
	  option.text(giftName);
	  window.close();
  }
};
  </script>
</html>
