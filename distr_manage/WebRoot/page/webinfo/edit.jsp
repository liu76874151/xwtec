<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<html>
	<head>
    	<title>网站基本信息维护</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
         <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.addMethod.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/webinfo/edit.js"></script>
    </head>
    
  	<body>
  	<input type="hidden" id="pkid" value="${param.channelNum}">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb"><span></span>网站基本信息--修改</div>
	  		<form name="editForm" id="editForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb">
	    		<tr>
  	  				<th align="right">渠道编码：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="10" name="channelNum" readonly="readonly" value="${param.channelNum}" id="channelNum" />
					
					<span class="errorMsg"></span></td>
					</tr>
				
	    
				
	    		<tr>
	    		<th align="right">站点状态：</th>
					<td class="form_table_content2">
					<input type="radio" value="0" name="state" id="state_0" ><label  for="state_0"> 开启</label>
					<input type="radio" value="1" name="state" id="state_1"><label  for="state_1"> 应急</label>
					<input type="radio" value="2" name="state" id="state_2"><label  for="state_2"> 测试</label>
					<span class="errorMsg"></span>
					</td>
	    		</tr>
	    		<tr>
				<th align="right">应急公告标题：</th>
					<td class="form_table_content2"><input type="text" class="form_input" maxlength="200" name="title" value="" id="title" />
					
					<span class="errorMsg"></span> </td>
	 			
				</tr>	
	    		
				
	 			
	 			
	 			<tr>
  	   				<th align="right">应急公告：</th>
	   				<td class="form_table_content2" ><textarea name="notice" id="notice" rows="5" cols="40" style="resize:none"></textarea></td>
  	 			</tr>
  	 		
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
	  			<tr>
				    <td align="center">
						<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					  	<!-- <a href="javascript:void(0)" class="btn" onClick="document.editForm.reset();">清除重填</a> -->
					</td>
	  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
  	</body>
</html>