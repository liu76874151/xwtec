<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../taglibs.jsp" %>
<%
String selectRowId = request.getParameter("selectRowId");
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  </head>
  
  <body>
    <input type="hidden" id="selectRowId" value="<%=selectRowId %>">
   	<div id="main_div" style="padding:10px;overflow:auto;">
	    <div class="breadcrumb-iframe">缓存类型--修改</div>
	    <form name="cacheTypeModForm" id="cacheTypeModForm" action="" method="post">
 		    <!-- 展示信息内容区域开始 -->
 		    <table class="tb">
 			  <tr>
 				  <th align="right" width="10%"><span class="required">*</span>缓存类型编码：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50"  name="typeNum" id="typeNum" readonly/><span class="errorMsg"></span></td>
	          </tr>
	          
	          <tr>
	              <th align="right"><span class="required">*</span>缓存类型名称：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="typeName" id="typeName"/><span class="errorMsg"></span></td>
	          </tr>
	          
	          <tr>
 				<th align="right">缓存类型描述：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="desc" id="desc"/><span class="errorMsg"></span></td>
	          </tr>
    		</table>
		    <!-- 展示信息内容区域结束 -->
		    
 		   <!-- 按钮操作区域开始 -->
           <table width="100%" height="40">
		     <tr>
               <td align="center">
	            <a href="javascript:void(0)" class="btn" onclick="modSubmit();">确认提交</a>
	            <a href="javascript:void(0)" class="btn" onclick="document.cacheTypeModForm.reset();">清除重填</a>
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
			    ValidateUtil.validate({
			    	targetForm : "cacheTypeModForm",
			    	rules : {
						typeNum    : {required  : true, minlength : 1, maxlength : 1000} ,
						typeName   : {required  : true, minlength : 1, maxlength : 1000},
						desc       : {maxlength : 2000}  
					},
					messages : {
						typeNum    : {required  : "请输入缓存类型编码", minlength : "长度必须大于等于{0}", maxlength : "长度不能超过{0}"},
						typeName   : {required  : "请输入缓存类型名称", minlength : "长度必须大于等于{0}", maxlength : "长度不能超过{0}"},
						desc       : {maxlength : "长度不能超过{0}"}
					}
			    });
				cacheTypeMod.init();
			}
      );
      
      var cacheTypeMod = 
     {
	     init : function()
				{
				    var selectRowId = $("#selectRowId")[0].value;
					$.singleReq({
						data : 
						{
							"reqUrl" : "cacheTypeManage",
							"reqMethod" : "queryCacheTypeInfoById",
							"selectRowId" : selectRowId
						},
						success : function(ret)
						{
						    if(ret.retCode == 0)
						    {
						        var cacheTypeInfo = ret.retObj.cacheTypeInfo;
								$("#typeNum").val(cacheTypeInfo.typeNum);
								$("#typeName").val(cacheTypeInfo.typeName);
								$("#desc").val(cacheTypeInfo.desc);
						    }
							
						}
					});
					
				}
     };
  
     function modSubmit() {
        if(!$('#cacheTypeModForm').valid())
	    {
	        return;
	    }
		var typeNum = $("#typeNum").val();
		var typeName = $("#typeName").val();
		var desc = $("#desc").val();
		
	    $.singleReq({
			data : 
			{
				"reqUrl"         : "cacheTypeManage",
				"reqMethod"      : "modCacheTypeInfo",
				"typeNum"        : typeNum,
				"typeName"       : typeName,
				"desc"           : desc
			},
			success : function(ret)
			{
			    if(ret.retCode == 0)
			    {
			        UOMPComp.showSuccessDialog("修改成功！",  ""); 
				    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
			    }
			    else
			    {
			        UOMPComp.showFailedDialog("修改失败！", "");
			    }
			}
		});
   }
  </script>
</html>
