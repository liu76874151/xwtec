<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../../taglibs.jsp" %>
<html>
	<head>
    	<title>专区信息修改</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/content/channel/channel_edit.js"></script>
    </head>
  	<body style="overflow: auto;">
  	<input type="hidden" id="pkid" value="${param.pkid}">
  	
   		<div id="main_div" >
  			<div class="breadcrumb"><span></span>专区信息--修改</div>
	  		<form name="editForm" id="editForm"  action="${contextPath}/actionDispatcher.do?reqUrl=content&reqMethod=filesuploadReName" method="post" 
        	target="hidIframe" enctype="multipart/form-data">
        	  <input type="hidden" id="chanId" name="chanId" value="${param.chanId}">
			   <input type="hidden" id="fileName" name="fileName">	
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb" id="editTab">
				<tr>
	 	  			<th align="right" >专区名称：</th>
					<td class="form_table_content2">	
					 <input type="text" name="chanName" id="chanName" class="form_input" maxlength="50"  />							
					</td>
	 			</tr>
	 			<tr>
	 	  
				
	 	  			<th align="right" >专区显示名称：</th>
					<td class="form_table_content2" ><input type="text" class="form_input" maxlength="50" name="showName"  id="showName" onchange="component.addOption(this)"/></td>
	 			</tr>
	 		<tr>
	 	  			<th align="right" >父专区：</th>
					<td class="form_table_content2">	
					<select name="parentId" id="parentId" onchange="component.querySubChannel('','')"> <option value="-1">--请选择--</option></select>
					</td>
	 			</tr>
	 			<tr>
	 					<th align="right">渠道：</th>
			      		<td  class="form_table_content2">
				      	 	<select name="channelNum" id="channelNum">
	                    	<option value="" >--请选择--</option>
	                    	<option value="01">网厅</option>
							<option value="02">掌厅</option>
	                    	<option value="03">短厅</option>
	                    	</select>
			      		</td>
			    	</tr>
  	 			<tr>
	 	  			<th align="right">专区类型：</th>
					<td class="form_table_content2">
					<input type="radio"  maxlength="50" name="type" id="type1" value="1" />&nbsp;普通
					<input type="radio"  maxlength="50" name="type" id="type2" value="2" />&nbsp;下载
					<input type="radio"  maxlength="50" name="type" id="type3" value="3" />&nbsp;链接
					<input type="radio"  maxlength="50" name="type" id="type4" value="4" />&nbsp;业务
					</td>
	 			</tr>
  	 	
	 			<tr class="TMP">
	 	  			<th align="right">概览页面模板（标准版）：</th>
					<td class="form_table_content2">
					<select id="overviewTmp" >
					<option value="">--请选择--</option>
					
					</select>
					</td>
	 			</tr>
	 			<tr class="TMP">
	 	  			<th align="right">细览页面模板（标准版）：</th>
					<td class="form_table_content2">
					<select id="detailTmp"><option value="">--请选择--</option></select>
					</td>
	 			</tr>
	 			<tr class="TMP">
	 	  			<th align="right">概览页面模板（触屏版）：</th>
					<td class="form_table_content2">
					<select id="overviewTmp2" ><option value="">--请选择--</option></select>
					</td>
	 			</tr>
	 			<tr class="TMP">
	 	  			<th align="right">细览页面模板（触屏版）：</th>
					<td class="form_table_content2">
					<select id="detailTmp2" ><option value="">--请选择--</option></select>
					</td>
	 			</tr>
	 			
	 			<tr >
	 	  			<th align="right">存放位置：</th>
					<td class="form_table_content2">
					 <input type="text" name="depository" id="depository" class="form_input" maxlength="50"  />							
					</td>
	 			</tr>
	 	<tr>
	 	  			<th align="right">展示位置：</th>
					<td class="form_table_content2">
					<table>
						<tr>
							<td>
								<select size="8"  name="sortNum" id='sortNum' style="width: 120px" >

								</select>
							</td>
							<td>
								<input type="button" value="上移" id="btnMoveUp" ><br>
								<input type="button" value="下移" id="btnMoveDown">
							</td>
						</tr>
					</table>
					
					</td>
	 			</tr>
	 		
	 			<tr>
	 	  			<th align="right">专区描述：</th>
					<td class="form_table_content2">
					<textarea rows="3" cols="50" class=""  name="desc"  id="desc"></textarea>
					</td>
	 			</tr>
	 			
	 			
	 
	 			<tr>
  	   				<th align="right" >附件上传：</th>
  	   				
	   				<td class="form_table_content2" > &nbsp;
						<input type="file" name="files" id="logo" class="qinggoudan_input021" maxlength="50">
			       <span class="errorMsg"></span>
			       
						<img alt="" style="display:none; width: 240;height: 55" name="deImg" id="deImg"  src="">
						
			       </td>
  	 			</tr>
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
	  			<tr>
				    <td align="center">
						<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					</td>
	  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
		
<iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
  	</body>
</html>