<%@ page language="java" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../../taglibs.jsp" %>
<html>
	<head>
    	<title>广告位信息修改</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
   	    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/adv/advPosition/advPostion_edit.js"></script>
  
    </head>
  	<body>
  	<input type="hidden" id="pkid" value="${param.pkid}">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb"><span></span>广告位信息--修改</div>
	  		<form name="editForm" id="editForm" action="${contextPath}/actionDispatcher.do?reqUrl=advPosition&reqMethod=filesUpload" method="post" 
            target="hidIframe" enctype="multipart/form-data">
            <input type="hidden" name="fileName" id="fileName">
            <input type="hidden" name="hiddenPositionNum" id="hiddenPositionNum">
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb" id="editTab">
			
  	  			<tr>
  	  				<th align="right" width="20%" >渠道编码：</th>
					<td class="form_table_content" colspan="3">
					<select name="channelNum" id="channelNum">
	                    	<option value="">--请选择--</option>
	                    	<option value="01">网厅</option>
	                    	    <optgroup label="掌厅" title="掌厅">
							        <option value="0201">掌厅普版</option>
							        <option value="0202">掌厅标准版</option>
							        <option value="0203">掌厅触屏版</option>
							        <option value="0204">掌厅APP</option>
							    </optgroup>
	                    	<option value="03">短厅</option>
	                    	</select>
						<span class="errorMsg"></span></td>
					</tr>
					
  	  			<tr>
  	  				<th align="right" width="20%">广告位名称：</th>
					<td class="form_table_content" colspan="3">
						<input type="text" class="form_input"  name="positionName" value="" id="positionName" />
						<span class="errorMsg"></span></td>
					</tr>
					<tr>
  	  				<th align="right" width="20%">广告位编码：</th>
					<td class="form_table_content" colspan="3">
						<input type="text" class="form_input"  name="positionNum" value="" id="positionNum"  readonly="readonly"/>
						<span class="errorMsg"></span></td>
					</tr>

	 			<tr>
	 			<th align="right" width="20%">广告位运行状态：</th>
					<td class="form_table_content" colspan="3">
						<input type="radio" name="state" id="state0" value="0"> 启用
						<input type="radio" name="state" id="state1"  value="1"> 停用
					</td>

  	 			</tr>
	 			<tr>
  	   				<th align="right" width="20%">广告类型：</th>
	   				<td class="form_table_content" colspan="3" >
	   					<input type="radio" name="positionType" id="positionType1"  value="1"> 多图
						<input type="radio" name="positionType"  id="positionType2"  value="2"> 单图
						<input type="radio" name="positionType"  id="positionType3"  value="3"> 文字
					</td>
  	 			</tr>
	 			<tr>
  	   				<th align="right" width="20%">广告位示意图：</th>
	   				<td class="form_table_content" colspan="3" > <input type="file" id="positionImage" name="file" /><span class="errorMsg"></span>
  	 				<br>
						<img alt="" style="display:none; width: 240;height: 55" name="deImg" id="deImg"  src=""></td>
					
  	 			</tr>
	 			
	 
	 		
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
	  			<tr>
				    <td align="center">
						<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
					  	<a href="javascript:void(0)" class="btn" onClick="document.addForm.reset();">清除重填</a>
					</td>
	  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
			<iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
  	</body>
</html>