<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<%
String selectRowId = request.getParameter("selectRowId");
%>
<html>
	<head>
    	<title>缓存类型</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" ></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  	</head>
  	<body>
  	<input type="hidden" id="selectRowId" value="<%=selectRowId %>">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb">缓存类型--查看</div>
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="96%" cellspacing="1" class="form_table">
  	  			<tr>
  	  				<td class="view_info_title"  width="20%">缓存类型编码：</td>
  	  				<td class="view_info_content" style="width:80%" id="typeNum"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">缓存类型名称：</td>
	 				<td class="view_info_content" style="width:80%" id="typeName"></td>
	 			</tr>
	 			<tr>
	 				<td class="view_info_title">缓存类型描述：</td>
	 				<td class="view_info_content" style="width:80%" id="desc"></td>
	 			</tr>
        		</table>
	   			<!-- 展示信息内容区域结束 -->
		</div>
  	</body>
  	<script type="text/javascript">
  	$(document).ready(function()
		{
			view();
		}
    );
    
  	function view() {
        $.singleReq({
			data : 
			{
				"reqUrl"     : "cacheTypeManage",
				"reqMethod"  : "queryCacheTypeInfoById",
				"selectRowId": $("#selectRowId").val()
			},
			success : function(ret)
			{
			    if(ret.retCode == 0)
			    {
			        var cacheTypeInfo = ret.retObj.cacheTypeInfo;
					$("#typeNum").text(cacheTypeInfo.typeNum);
					$("#typeName").text(cacheTypeInfo.typeName);
					$("#desc").text(cacheTypeInfo.desc);
			    }
				else
				{
				    UOMPComp.showFailedDialog("查询失败！", "");
				}   
			}
		});
     }
  	</script>
</html>