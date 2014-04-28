<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%@page import="java.net.URLDecoder"%>
<%
String funcId = URLDecoder.decode(request.getParameter("funcId"),"utf8");
String jbNum = URLDecoder.decode(request.getParameter("jbNum"),"utf8");
String subSysNum = URLDecoder.decode(request.getParameter("subSysNum"),"utf8");
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
    <input type="hidden" id="funcId1" name="funcId" value="<%=funcId %>">
    <input type="hidden" id="jbNum1" name="jbNum" value="<%=jbNum %>">
    <input type="hidden" id="subSysNum1" name="subSysNum" value="<%=subSysNum %>">
    <div id="main_div" style="padding:10px;overflow:auto;">
 		<div class="breadcrumb-iframe">自定义菜单--修改</div>
		<form name="customMenuEditForm" id="funEditForm" action="" method="post">
    		<!-- 展示信息内容区域开始 -->
  			 	    <table width="98%" class="tb">
	    		<tr>
	      			<th align="right">子系统编码：</th>
		  			<td class="form_table_content" id="subSysNum">
		  			     
		  			</td>
		  			<th align="right">子系统名称：</th>
		  			<td class="form_table_content"  id="subSysName">
		  			     
		  		    </td>
				</tr>
	    		<tr>
	      			<th align="right">功能编码：</th>
		  			<td class="form_table_content" id="funcId" ></td>
		  			<th align="right">功能名称：</th>
		  			<td class="form_table_content" id="funcName" ></td>
				</tr>
				<tr>
		  			<th align="right">功能URL：</th>
					<td class="form_table_content" colspan="3" td="funcUri">
						
					</td>
		  		</tr>
				<tr>
		  			<th align="right">菜单分类名称：</th>
					<td class="form_table_content" colspan="3">
						<input type="text" class="form_input" maxlength="20" name="moudleSortName"  id="moudleSortName"  value=""/><span class="errorMsg"></span>
					</td>
		  		</tr>
		  	
		 		<tr>
	  	   			<th align="right">备注：</th>
		   			<td class="form_table_content" colspan="3" >
		   				<textarea class="form_text" name="bz" id="bz" id="bz"></textarea><span class="errorMsg"></span>
		   			</td>
	  	 		</tr>
       </table>		    
       		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
		 		<tr>
		    		<td align="center">
			  			<a href="javascript:void(0)" class="btn" onclick="editSubmit();">确认提交</a>
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
			customMenuPage.init();
		}
	);

	var customMenuPage = 
	{
	    init : function()
				{
	    	     var jbNum  = $("#jbNum1").val();
	        	 var funcId = $("#funcId1").val();
	   		     var subSysNum = $("#subSysNum1").val();
			   		$.singleReq({
			   			data : 
			   			{
			   				"reqUrl" : "customMenu",
			   				"jbNum" :  jbNum,
			   				"funcId" :  funcId,
			   				"subSysNum" :  subSysNum,
			   	    		"reqMethod" : "viewCustomMenu"
			   			},
			   			success : function(ret)
			   			{
			   				var customMenuList = ret.retObj.customMenuList;
			   				if(customMenuList !=null && customMenuList !=""){
			   					var customMenuBean = customMenuList[0];
			   					$("#subSysNum").html(customMenuBean.subSysNum);
			   					$("#subSysName").html(customMenuBean.subSysName);
			   					$("#funcId").html(customMenuBean.funcId);
			   					$("#funcName").html(customMenuBean.funcName);
			   					$("#moudleSortName").val(customMenuBean.moudleSortName);
			   					$("#funcUri").html(customMenuBean.funcUri);
			   					$("#bz").val(customMenuBean.bz);	
			   				 }		   				
			   			}
			   		});
				}
	};
	
	function editSubmit()
	{
			var jbNum = $("#jbNum1").val();
			var moudleSortName =$("#moudleSortName").val();;
			var subSysNum =$("#subSysNum1").val();;
			var funcId = $("#funcId1").val();;
			var bz = $("#bz").val();
			$.singleReq({
				data : 
				{
					"reqUrl":"customMenu",
					"reqMethod" :"updateCustomMenu",
					"jbNum":jbNum,
					"subSysNum":subSysNum,
					"funcId":funcId,
					"bz":bz,
					"moudleSortName":moudleSortName
					
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
  </script>
</html>