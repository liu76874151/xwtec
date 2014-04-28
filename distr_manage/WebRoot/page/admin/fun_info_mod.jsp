<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String subSysName = request.getParameter("subSysName");
String jbNum = request.getParameter("jbNum");
String funcId = request.getParameter("funcId");
%>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css">
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/dhtmlxform.js"></script>  
  </head>
  
  <body>
    <input type="hidden" id="subSysName" name="subSysName" value="<%=subSysName %>">
    <input type="hidden" id="jbNum" name="jbNum" value="<%=jbNum %>">
    <input type="hidden" id="funcId" name="funcId" value="<%=funcId %>">
    <div id="main_div" style="padding:10px;overflow:auto;">
 		<div class="breadcrumb-iframe">模块信息--修改</div>
		<form name="funEditForm" id="funEditForm" action="" method="post">
    		<!-- 展示信息内容区域开始 -->
  			<table class="tb">
    		<tr>
      			<th align="right"><span class="required">*</span>功能ID：</th>
	  			<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="funId" id="funId"/><span class="errorMsg"></span></td>
	  			<th align="right">功能名称：</th>
	  			<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="funcName" id="funcName"/><span class="errorMsg"></span></td>
			</tr>
			<tr>
	          <th align="right"><span class="required">*</span>功能类型：</th>
	          <td class="form_table_content">
	         	<select name="funcType" id="funcType">
				   	<option value="">--请选择--</option>
				   	<option value="0">模板</option>
			       	<option value="1">页面</option>
			       	<option value="2">子页面</option>
			       	<option value="3">操作</option>
			 	</select>
			  </td>
	        </tr>
			<tr>
	  			<th align="right">功能实现URI：</th>
				<td class="form_table_content" colspan="3">
					<input type="text" class="form_input" maxlength="200" name="funcURI" id="funcURI"/>
					<span class="errorMsg"></span>
				</td>
	  		</tr>
	 		<tr>
  	   			<th align="right">备注：</th>
	   			<td class="form_table_content" colspan="3" >
	   				<textarea class="form_text" name="bz" id="bz"></textarea>
	   				<span class="errorMsg"></span>
	   			</td>
  	 		</tr>
       		</table>
   			<!-- 展示信息内容区域结束 -->
   			
    		<!-- 按钮操作区域开始 -->
			<table width="100%" height="40">
	 		<tr>
	    		<td align="center">
		  			<a href="javascript:void(0)" class="btn" onclick="editSubmit();">确认提交</a>
		  			<a href="javascript:void(0)" class="btn" onclick="document.funEditForm.reset();">清除重填</a>
				</td>
	  		</tr>
       		</table>
      	 	<!-- 按钮操作区域结束 -->
      	</form>
	 </div>
  </body>
  <script type="text/javascript">
   $(document).ready(function()
		{
			funModPage.init();
		}
	);

	var funModPage = 
	{
	    init : function()
				{
				    var funcId = $("#funcId")[0].value;
					$.singleReq({
						data : 
						{
							"reqUrl" : "funinfo",
							"reqMethod" : "queryFunInfo",
							"funId" : funcId
						},
						success : function(ret)
						{
						    if(ret.retCode == 0)
						    {
						        var funInfo = ret.retObj.funInfo;
							    var funcType = funInfo.funcType
							    $("#funId").val(funInfo.funcId);
							    $("#funcName").val(funInfo.funcName);
							    $("#funcURI").val(funInfo.funcURI);
							    $("#bz").val(funInfo.bz);
							    $("#funcType").val(funInfo.funcType);
						    }
							else
							{
							    alert(ret.resMsg);
							}
							
						}
					});
					
				}
	};
	
	function editSubmit()
	{
	        var subSysName = $("#subSysName")[0].value;
			var jbNum = $("#jbNum")[0].value;
		    var funcId =  $("#funcId")[0].value;
		    var funcName = $("#funcName")[0].value;
		    var funcURI = $("#funcURI")[0].value;
		    var bz = $("#bz")[0].value;
		    var funcType = $("#funcType")[0].value;
		    
		    if(funcId == null || funcId == "" || funcType == null || funcType == "")
		    {
		        UOMPComp.showFailedDialog("功能ID或功能类型不能为空！", "");
		    }
		    else
		    {
		        $.singleReq({
					data : 
					{
						"reqUrl"    : "funinfo",
						"reqMethod" : "modFunInfo",
						"funcId"    : funcId,
						"funcName"  : funcName,
						"funcURI"   : funcURI,
						"bz"        : bz,
						"subSysName": subSysName,
						"jbNum"     : jbNum,
						"funcType"  : funcType
					},
					success : function(ret)
					{
						UOMPComp.showSuccessDialog("修改成功！",  ""); 
						window.parent.location.reload(); 
						
					}
		        });
		    }
	}
	
	//参数校验
	ValidateUtil.validate({
	    	targetForm : "funEditForm",
	    	rules : {
				funcId : {required : true, minlength : 2, maxlength : 50},
				bz     : {maxlength : 2000}
			},
			messages : {
				funcId : {required : "请输入用户名", minlength : "必须大于{0}", maxlength : "不能超过{0}"},
				bz     : {maxlength : "不能超过{0}"}
			}
	});
  </script>
</html>