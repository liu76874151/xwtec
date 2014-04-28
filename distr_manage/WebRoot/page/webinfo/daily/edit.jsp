<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../../taglibs.jsp" %>
<html>
	<head>
    	<title>彩信日报--修改</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        
        <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
         <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.addMethod.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/webinfo/daily/edit.js"></script>
    </head>
   
  	<body>
  	<input type="hidden" id="type" value="${param.type}">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb"><span></span>彩信日报--修改</div>
	  		<form name="editForm" id="editForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb">
	    		<tr>
							<th  align="right" width="15%">日期：</th>
							<td class="form_table_content" >
							<input type="text"  name="beginTime" id="beginTime" readonly="readonly" value="${param.id}"></td>
						</tr>
						<tr>
							<th  align="right" width="15%">编号：</th>
							<td class="form_table_content" >
							<input type="text"  name="num" id="num" readonly="readonly"  value="${param.num}"></td>
						</tr>
						<tr>
			 	  			<th  align="right" width="15%">类型：</th>
							<td class="form_table_content" >
							<input type="radio"   name="type" value="1" id="type1" disabled />&nbsp;<label for="type1">标题</label> 
							<input type="radio"   name="type" value="2" id="type2"  disabled />&nbsp;<label for="type2">内容 </label> 
							</td>
		 			</tr>
					<tr>
							<th  align="right" width="15%">内容：</th>
							<td class="form_table_content" >
							<textarea rows="8" cols="55"  name="value" id="value" style="resize:none"></textarea>
							</td>
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