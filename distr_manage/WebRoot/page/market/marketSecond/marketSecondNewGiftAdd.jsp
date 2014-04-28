<%@ page isELIgnored="false" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新增礼品</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="新增礼品">
	
	<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/resource/css/frame.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript"	src="<%= request.getContextPath() %>/resource/scripts/main.js"></script>
  </head>
  <body>

    <div style="padding: 10px; overflow: auto;">
		<div class="breadcrumb">
		<c:out value="${contextPath}"/> 
			<span></span>礼品信息--新增<%= request.getContextPath() %>---${pageContext.request.contextPath}
		</div>
		<form name="addForm" id="addForm" action="" method="post">
		<input type="hidden" id="channel" name="channel" value="<%=request.getParameter("channel") %>">
		<table class="tb">
			<tr>
				<th align="right" style="width: 20%">礼品名称：</th>
				<td colspan="3" class="form_table_content"><input type="text" class="form_input" maxlength="50" name="giftName" value="" id="giftName" /><span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
 				<th align="right">礼品数量：</th>
				<td colspan="3" class="form_table_content">
				<input name="giftNum" value="" id="giftNum" /><span class="errorMsg"></span></td>
 			</tr>
 			
			<tr>
 	  			<th align="right">礼品类型：</th>
				<td id="giftTypes" class="form_table_content">
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
      component.initValidate();
      component.queryGiftInfoTypes();
  }
);

var component = {
		// 初始化校验规则
		initValidate: function () {
	    ValidateUtil.validate({
	        targetForm: "addForm",
	        rules: {
	    		giftName: {required: true, minlength: 1, maxlength: 40}
	        },
	        messages: {
	        	giftName: {required: "请输入礼品名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
	        }
	    });
	},

  saveForm: function () {
  var channel=$("#channel").val();
  	if (!$('#addForm').valid()) {
          return;
      }
    var giftName = $("#giftName").val();
	var giftNum = $("#giftNum").val();
	var gifttype = $("input[name=giftType]:checked").val();
	var giftSendType = $("input[name=giftType]:checked").attr("giftSendType");
	if("undefined"!=gifttype){
		var option = $("<option>").text(giftName);
		option.val("");
		option.attr("state","1");
		option.attr("gifttype",gifttype);
		option.attr("giftNum",giftNum);
		option.attr("giftSendType",giftSendType);
		$("#newAddGiftInfo"+channel, opener.document).append(option);
		window.close();
	}else{
		alert("请选择礼品类型");
	}
	
  },
  queryGiftInfoTypes: function(start, end){
	  if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 100;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "giftInfoHandler",
				"reqMethod" : "queryGiftInfoTypeList",
				"state" : '0',
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var giftType = result[int].giftType;
						var productName = result[int].productName;
						var giftSendType = result[int].giftSendType;
						var giftType = result[int].giftType;
						var radio  = $("<input type='radio' name='giftType' >").val(giftType);
						radio.attr("giftSendType",giftSendType);
						radio.attr("giftType",giftType);
						if((int+1)%3==0){
							$("#giftTypes").append(radio).append(productName).append("<br>");
						}else{
							$("#giftTypes").append(radio).append(productName).append("&nbsp;");
						}
					}
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
  }
};
</script>
</html>
