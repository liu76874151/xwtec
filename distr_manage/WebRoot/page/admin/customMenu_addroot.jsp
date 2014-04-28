<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String funcId = request.getParameter("funcId");
String jbNum = request.getParameter("jbNum");
String subSysNum = request.getParameter("subSysNum");
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
    <br>
	    <input type="hidden" id="funcId1" name="funcId" value="<%=funcId %>">
	    <input type="hidden" id="jbNum1" name="jbNum" value="<%=jbNum %>">
	    <input type="hidden" id="subSysNum1" name="subSysNum" value="<%=subSysNum %>">
   	<div id="main_div" style="padding:10px;overflow:auto;">
	    <div class="breadcrumb-iframe">自定义菜单分类--添加</div>
	    <form name="customAddRootForm" id="customAddRootForm" action="" method="post">
 		    <!-- 展示信息内容区域开始 -->
 		    <table  class="tb">
 			  <tr>
	              <th align="right" <span class="required">*</span> >菜单分类名称：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="moudleSortName" id="moudleSortName" value="" /><span class="errorMsg"></span></td>
	          </tr>
	          <tr>
  				<th align="right">备注：</th>
				  <td class="form_table_content" colspan="3" ><textarea class="form_text" name="bz" id="bz"></textarea></td>
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
    var moudleSortName = $("#moudleSortName").val();
    var bz = $("#bz").val();
    
    var funcName = $("#funcName")[0].value;
    var funcURI = $("#funcURI")[0].value;
    var bz = $("#bz")[0].value;
    var funcType = $("#funcType")[0].value;
    
    $.singleReq({
			data : 
			{
				"reqUrl"     : "funinfo",
				"reqMethod"  : "addFunInfo",
				"moudleSortName" : moudleSortName,
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
