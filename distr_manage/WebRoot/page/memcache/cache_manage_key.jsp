<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String paraLength = request.getParameter("paraLength");
String memKeyOne = request.getParameter("memKeyOne");
String memKey = request.getParameter("memKey");
 %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  </head>
  
  <body onload="init()">
    <input type="hidden" id="paraLength" value="<%=paraLength %>">
    <input type="hidden" id="memKeyOne" value="<%=memKeyOne %>">
    <input type="hidden" id="memKey" value="<%=memKey %>">
   	<div id="main_div" style="padding:10px;overflow:auto;">
	    <div class="breadcrumb-iframe">是否填写具体缓存参数：
		<input type="radio" onclick ="selectIsNeedPara('0')" name="radio" id="radio1" value="0" checked="checked"/>否
		<input type="radio" onclick ="selectIsNeedPara('1')" name="radio" id="radio2" value="1" />是
	    </div>
	    <div id="paraDiv" style="display: none;">
		    <form name="cacheCleanForm" id="cacheCleanForm" action="" method="post">
		    
		        
	 		    <!-- 展示信息内容区域开始 -->
	 		    <table width="98%" class="tb">
	 		      <tr>
					<th align="right">
						请填写参数
					</th>
				  </tr>
				  
	 			  <tbody id="showResult">
				      
			      </tbody>
	    		</table>
			    <!-- 展示信息内容区域结束 -->
	    	</form>
        </div>
        <!-- 按钮操作区域开始 -->
	    <table width="100%" height="40">
	     <tr>
           <td align="center">
            <a href="javascript:void(0)" class="btn" onclick="toConfirm();">确认提交</a>
            <a href="javascript:void(0)" class="btn" onclick="document.cacheCleanForm.reset();">清除重填</a>
           </td>
	     </tr>
   	   </table>
   	   <!-- 按钮操作区域结束 -->
    </div>
  </body>
  
  <script type="text/javascript">
  var paraFlag = 0;
  //初始化
  function init()
  {
	  $('#showResult').empty();
	  
	  var resultMsg = "";
	  var paraLen = $('#paraLength').val();
	  if(paraLen != null && paraLen > 0)
	  {
		  for(var i = 0; i < paraLen; i++)
		  {
			  index = i+1;
			  resultMsg += "<tr><th align=\"right\" width=\"10%\">参  数  "+index+"：</th> <td class=\"form_table_content\"><input name=\"key"+index+"\" id=\"key"+index+"\" type=\"text\" class=\"form_input\" maxlength=\"50\"></td></tr>"; 
		 }
	  }
	  $('#showResult').append(resultMsg);
  }
  
  //确认操作
  function toConfirm()
  {
  	var count = 0;
  	var selBusi = "";
  	var keyVal = $('#memKeyOne').val();
  	$("input[id^='key']").each(
  		function()
  		{
  			var para = $(this).attr("value");
  			keyVal += "_"+para+"";
  		}
  	);
  	$.singleReq({
		data : 
		{
			"reqUrl"    : "cacheManage",
			"reqMethod" : "cleanCache",
			"paraFlag"  :  paraFlag,
			"memKeyVal" :  keyVal,
			"memKey"    :  $('#memKey').val()
		},
		success : function(ret)
		{
			if(ret){
				if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
					var resMsg = ret.resMsg;
					var retCode = ret.retCode;
					UOMPComp.showSuccessDialog(resMsg,"");
					if(GLOBAL_INFO.SYS_FAILED == retCode){
						top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
					}else if(GLOBAL_INFO.SYS_SUCCESS){
						top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
					}
				}else{
					if(ret.resMsg){
						UOMPComp.showFailedDialog(ret.resMsg,"");
						top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
					}else{
						UOMPComp.showFailedDialog("系统异常","");
						top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
					}
				}
			}
		}
	});
  }
  
  //选择是否填写参数
  function selectIsNeedPara(val)
  {
    if(val=="1")
    {
       $("#paraDiv").show();
       paraFlag = 1;
    }
    else 
    {
       $("#paraDiv").hide();
       paraFlag = 0;
    }
  }
  </script>
</html>
