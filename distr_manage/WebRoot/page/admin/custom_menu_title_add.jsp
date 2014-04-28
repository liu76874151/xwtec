<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%@page import="java.net.URLDecoder"%>
<%
String funcId = URLDecoder.decode(request.getParameter("funcId"),"utf8");
String jbNum = URLDecoder.decode(request.getParameter("jbNum"),"utf8");
%>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css">
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  </head>
  
  <body>
    <input type="hidden" id="funcId" name="funcId" value="<%=funcId %>">
    <input type="hidden" id="jbNum" name="jbNum" value="<%=jbNum %>">
   	<div id="main_div" style="padding:10px;overflow:auto;">
	    <div class="breadcrumb-iframe">自定义菜单标题--添加</div>
	    <form name="addForm" id="addForm" action="" method="post">
 		    <!-- 展示信息内容区域开始 -->
 		    <table class="tb">
 			  <tr>
 				<th align="center" style="width: 10%;"><span class="required">*</span>菜单分类名称：</th>
	            <td class="form_table_content" style="width: 60%;">
	            <input type="text" class="form_input" maxlength="20" style="width: 40%;" name="mouldSortName" id="mouldSortName"/>
	            <span class="errorMsg"></span></td>
	          </tr>
	          <tr>
  				  <th align="center" style="width: 10%;">备注：</th>
				  <td class="form_table_content" style="width: 60%;">
					  <textarea class="form_text" name="bz" id="bz" style="size: 50%;width:60%;  height: 50px;"></textarea>
					  <span class="errorMsg"></span>
				  </td>
			  </tr>
    		</table>
		    <!-- 展示信息内容区域结束 -->
		    
 		   <!-- 按钮操作区域开始 -->
           <table width="100%" height="40">
		     <tr>
               <td align="center">
	            <a href="javascript:void(0)" class="btn" onclick="addSubmit();">确认提交</a>
	            <a href="javascript:void(0)" class="btn" onclick="document.addForm.reset();">清除重填</a>
	           </td>
		     </tr>
    	   </table>
    	   <!-- 按钮操作区域结束 -->
    	</form>
    </div>
  </body>
  
  <script type="text/javascript">
    //参数校验
  ValidateUtil.validate({
    	targetForm : "addForm",
    	rules : {
			mouldSortName : {required : true, minlength : 2, maxlength : 20},
			bz : {required : false, maxlength : 500}
		},
		messages : {
			mouldSortName : {required : "请输入菜单分类名称", minlength : "必须大于{0}", maxlength : "不能超过{0}"},
			bz     : {maxlength : "备注字数不能超过{0}"}
		}
    });
  // 点击提交按钮
  function addSubmit(){
	var mouldSortName = $("#mouldSortName").val();
    var jbNum = $("#jbNum").val();
    var funcId = $("#funcId").val();
    var bz = $("#bz").val();
   // alert("mouldSortName="+mouldSortName+" bz ="+bz);
	if($('#addForm').valid()){
	 	$.singleReq({
		data : 
		{
			"reqUrl"     : "customMenu",
			"reqMethod"  : "addCustomMenu",
			"mouldSortName"   : mouldSortName,
			"funcId"     : funcId,
			"bz"         : bz,
			"jbNum"      : jbNum,
			"type"       : 0	
		},
		success : function(ret)
		{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						var resMsg = ret.resMsg;
						var retCode = ret.retCode;
						UOMPComp.showSuccessDialog(resMsg,"");
						if(GLOBAL_INFO.SYS_FAILED == retCode){
						}else if(GLOBAL_INFO.SYS_SUCCESS){
							 window.parent.location.reload(); 
						}
					}else{
						if(ret.resMsg){
							UOMPComp.showFailedDialog(ret.resMsg,"");
						}else{
				
							UOMPComp.showFailedDialog("系统异常","");
						}
					}
				}		
		}
	});
}
}


  </script>
</html>
