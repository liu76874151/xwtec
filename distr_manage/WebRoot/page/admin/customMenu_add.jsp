<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<%
String subSysName = request.getParameter("subSysName");
String jbNum = request.getParameter("jbNum");
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
    <br><input type="hidden" id="subSysName" name="subSysName" value="<%=subSysName %>">
    <input type="hidden" id="jbNum" name="jbNum" value="<%=jbNum %>">
   	<div id="main_div" style="padding:10px;overflow:auto;">
	    <div class="breadcrumb-iframe">自定义菜单--添加</div>
	    <form name="funAddForm" id="funAddForm" action="" method="post">
 		    <!-- 展示信息内容区域开始 -->
 		    <table width="98%" class="tb">
 			  <tr>
 				<th align="right">功能ID：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="funcId" id="funcId" value="" /><span class="errorMsg"></span></td>
	            <th align="right">功能名称：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="funcName" id="funcName" value="" /><span class="errorMsg"></span></td>
	          </tr>
	          <tr>
	            <th align="right">功能类型：</th>
	            <td>
		         	<select name="funcType" id="funcType">
					   	<option value="">--请选择--</option>
					   	<option value="0">模块</option>
				       	<option value="1">页面</option>
				       	<option value="2">子页面</option>
				       	<option value="3">操作</option>
				 	</select>
			    </td>
	          </tr>
	          <tr>
		        <th align="right">功能实现URI：</th>
	              <td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="100" id="funcURI" name="funcURI" /><span class="errorMsg"></span></td>
	          </tr>
	          <tr>
  				<th align="right">备注：</th>
				  <td class="form_table_content" colspan="3" ><textarea class="form_text" name="bz" id="bz"></textarea><span class="errorMsg"></span></td>
			  </tr>
    		</table>
		    <!-- 展示信息内容区域结束 -->
		    
 		   <!-- 按钮操作区域开始 -->
           <table width="100%" height="40">
		     <tr>
               <td align="center">
	            <a href="javascript:void(0)" class="btn" onclick="addSubmit();">确认提交</a>
	            <a href="javascript:void(0)" class="btn" onclick="document.funAddForm.reset();">清除重填</a>
	           </td>
		     </tr>
    	   </table>
    	   <!-- 按钮操作区域结束 -->
    	</form>
    </div>
  </body>
  
  <script type="text/javascript">
  // 点击提交按钮
  function addSubmit() {
    var subSysName = $("#subSysName")[0].value;
    var jbNum = $("#jbNum")[0].value;
    var funcId = $("#funcId")[0].value;
    var funcName = $("#funcName")[0].value;
    var funcURI = $("#funcURI")[0].value;
    var bz = $("#bz")[0].value;
    var funcType = $("#funcType")[0].value;
    
    $.singleReq({
			data : 
			{
				"reqUrl"     : "funinfo",
				"reqMethod"  : "addFunInfo",
				"funcId"     : funcId,
				"funcName"   : funcName,
				"funcURI"    : funcURI,
				"bz"         : bz,
				"subSysName" : subSysName,
				"jbNum"      : jbNum,
				"funcType"    : funcType
			},
			success : function(ret)
			{
			    alert(ret.resMsg);
			}
		});
   }

   //参数校验
   ValidateUtil.validate({
	    	targetForm : "funAddForm",
	    	rules : {
			//	funcId : {required : true, min : 2, max : 50},
			//	bz : { max : 2000}
			},
			messages : {
			//	funcId : {required : "请输入用户名", min : "必须大于{0}", max : "不能超过{0}"},
			//	bz     : {max : "不能超过{0}"}
			}
	    });
  </script>
</html>
