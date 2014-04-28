<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>

    <title>修改内容</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
         <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
         <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
         <script type="text/javascript" src="${contextPath}/resource/scripts/CKEditor/ckeditor.js"></script>
      <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/content/document/doc_edit.js"></script>
</head>
<body style="overflow: auto;">
 <input type="hidden" id="docId" value="${param.pkid}">  
 <input type="hidden" id="chanId" value="${param.chanId}">  
 <input type="hidden" id="marketFirstPkid" />  
 <input type="hidden" id="marketFirstCode" />  
<div id="main_div" style="padding:10px;height: 1200px;">
    <div class="breadcrumb"><span></span>内容--修改</div>
        	<form name="editForm" id="editForm" action="${contextPath}/actionDispatcher.do?reqUrl=contentDoc&reqMethod=filesuploadReName" method="post" 
        	target="hidIframe" enctype="multipart/form-data">
        	<input type="hidden" name="hiddenAttach" id="hiddenAttach">
        	<input type="hidden" name="hiddenLogo" id="hiddenLogo">
        	<input type="hidden" name="hiddenDocId" id="hiddenDocId">
        	<input type="hidden" name="hiddenIsUpdate" id="hiddenIsUpdate">
			<table class="tb" style="position:relative;" id="editTab">
				<tr>
	 	  			<th align="right">内容类型：</th>
					<td class="form_table_content2">
				 	<input type="text" name="type" id="type" class="form_input" />
					</td>
	 			</tr>
			<tr>
	 	  			<th align="right" >标题：</th>
					<td class="form_table_content2">	
					 <input type="text" name="title" id="title" class="form_input"  onchange="component.addOption(this)"  />							
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right" >副标题：</th>
					<td class="form_table_content2" ><input type="text" class="form_input"  name="subhead"  id="subhead" /></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right" >显示标题：</th>
					<td class="form_table_content2" ><input type="text" class="form_input"  name="showtitle"  id="showtitle" /></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right" >摘要：</th>
					<td class="form_table_content2" ><input type="text" class="form_input"  name="summary"  id="summary" /></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right" >作者：</th>
					<td class="form_table_content2" ><input type="text" class="form_input"  name="author"  id="author"/></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right" >链接地址：</th>
					<td class="form_table_content2" ><input type="text" class="form_input"  name="url"  id="url"/></td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">HTML内容：</th>
					<td class="form_table_content2">
					<textarea rows="10" cols="60" class=""  name="content"  id="content"></textarea>
					</td>
	 			</tr>
	 			
	 			<tr>
	 	  			<th align="right">下载等级：</th>
					<td class="form_table_content2">
					<input type="radio"   name="level" id="level1" value="1" />&nbsp;1
					<input type="radio"   name="level" id="level2"  value="2" />&nbsp;2
					<input type="radio"   name="level" id="level3"   value="3" />&nbsp;3
					<input type="radio"   name="level" id="level4"  value="4" />&nbsp;4
					<input type="radio"   name="level" id="level5"  value="5" />&nbsp;5
					</td>
	 			</tr>
	 			<tr>
  	   				<th align="right" >图片LOGO：</th>
	   				<td class="form_table_content2" > &nbsp;
						<input type="file" name="files" id="logo" class="qinggoudan_input021" >
						<input type="hidden" name="hiddenLogo1" id="hiddenLogo1">
			       <span class="errorMsg"></span>
			       <br>
					<img alt="" style="display:none; width: 240;height: 55" name="logoImg" id="logoImg"  src="">
			       </td>
			       </tr>
			    <tr>
			    <th align="right" >资费标准：</th>
				<td class="form_table_content2" ><input type="text" class="form_input"  name="feeStandard"  id="feeStandard"/></td>
			    </tr>   
 				 <tr>
			    <th align="right" >业务名称：</th>
				<td class="form_table_content2" ><input type="text" class="form_input"  name="busiName"  id="busiName"/></td>
			    </tr>  
			     <tr>
			    <th align="right" >业务介绍：</th>
				<td class="form_table_content2" ><input type="text" name="busiIntro"  id="busiIntro" class="form_input"  /></td>
			    </tr>  
			   <tr>
			    <th align="right" >业务资费：</th>
				<td class="form_table_content2" ><input type="text" name="busiFee"  id="busiFee" class="form_input"  /></td>
			    </tr> 
			     <tr>
			    <th align="right" >原业务资费：</th>
				<td class="form_table_content2" ><input type="text" name="busiOldfee"  id="busiOldfee" class="form_input"  /></td>
			    </tr> 
			    <tr>
			    <th align="right" >业务品牌：</th>
				<td class="form_table_content2" ><input type="text" name="bandName"  id="bandName" class="form_input"  /></td>
			    </tr> 
			    <tr >
			    <th align="right" >业务地市：</th>
				<td class="form_table_content2" ><input type="text" name="areaName"  id="areaName" class="form_input"  /></td>
			    </tr> 
			    <tr >
			    <th align="right" >业务链接：</th>
				<td class="form_table_content2" ><input type="text" name="busiUrl"  id="busiUrl" class="form_input"  /></td>
			    </tr> 
			     <tr>
	 	  			<th align="right">展示位置：</th>
					<td class="form_table_content2">
					<table>
						<tr>
							<td>
								<select size="8"  name="sortNum" id='sortNum' style="width: 400px" >

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
	 	  			<th align="right">版本说明：</th>
					<td class="form_table_content2">
					<textarea rows="3" cols="60" class=""  name="versionDesc"  id="versionDesc"></textarea>
					</td>
	 			</tr>
	 			<tr>
	 	  			<th align="right">使用说明：</th>
					<td class="form_table_content2">
					<textarea rows="3" cols="60" class=""  name="useDesc"  id="useDesc"></textarea>
					</td>
	 			</tr>
	 			<tr>
	 			<th align="right" id="addAttach">添加附件：</th>
	 			<td class="form_table_content2">
					<input type="button"  id="addAttachButton" value="添加"  onclick="component.initAttach()">
					</td>
	 			</tr>
			</table>
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