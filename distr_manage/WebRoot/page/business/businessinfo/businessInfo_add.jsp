<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.apache.commons.lang.time.DateFormatUtils"%>
<%@include file="../../taglibs.jsp"%>
<%
	Calendar theCa = Calendar.getInstance();
	theCa.setTime(new Date());
	String currenTime = DateFormatUtils.format(theCa.getTime(), "yyyy-MM-dd");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>业务信息-添加</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    
    
   
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar_start.js"></script>
	<!-- ckeditor-->
	<script type="text/javascript" src="${contextPath}/resource/scripts/CKEditor/ckeditor.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css"></link>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessinfo/businessInfo_add.js"></script>
	<script type="text/javascript">
  	var currenTime = "<%=currenTime%>";
<%-- back            unique
	function checkTime(startTime,endTime){//date validiate
	      /*var startTime="2013-12-12 18:00:00";
	      var endTime="2013-12-12 18:00:01";*/
	      var date = new Date(currenTime.replace("-", "/").replace("-", "/"));  
	      var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
	      var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
			if (start < date) {
				return false;
			}
	      if(end<start){
	          return false;  
	      }  
	      return true;  
	  }  
--%>
	function checkTime(startTime,endTime){//date validiate
	    /*var startTime="2013-12-12 18:00:00";
	    var endTime="2013-12-12 18:00:01";*/
	    var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
	    var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
	    if(end<start){
	        return false;  
	    }  
	    return true;  
	}
	</script>
	</head>
  
  <body style="overflow: auto;">
  <input type="hidden" id="jbNum" name="jbNum" value="${param.jbNum }">
  <input type="hidden" id="busiExtraJson" name="busiExtraJson" >
  <input type="hidden" id="busiTagJson" name="busiTagJson">
  <input type="hidden" id="busiAreaJson" name="busiAreaJson">
  <input type="hidden" id="businessTypeJson_01" name="businessTypeJson_01">
  <input type="hidden" id="businessTypeJson_02" name="businessTypeJson_02">
  <input type="hidden" id="relaBusiInfoJson01" name="relaBusiInfoJson01">
  <input type="hidden" id="relaBusiInfoJson02" name="relaBusiInfoJson02">
  <input type="hidden" id="busiAreaJson01" name="busiAreaJson01">
  <input type="hidden" id="busiAreaJson02" name="busiAreaJson02">
    <div style="padding: 10px; overflow: auto;">
		<div class="breadcrumb">
			<span></span>业务基础信息:共性(掌、网)--新增
		</div>
		<form name="addForm" id="addForm" method="post"  enctype="multipart/form-data" action="${contextPath}/actionDispatcher.do?reqUrl=fileuploadHandler&reqMethod=filesUpload" target="upload_area">
		<table class="tb">
			<tr>
 	  			<th width="10%" align="right">业务名称：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="busiName" value="" id="busiName" />
				<span class="errorMsg"></span></td>
			</tr>
			<tr>
				<th width="10%" align="right">业务编码：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="busiNum" value="" id="busiNum" />
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">名称拼音编码(简拼)：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="200" name="busiNamePym2" value="" id="busiNamePym2" />
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th class="view_info_title" style="width: 10%">
					开通生效方式 ：
				</th>
				<td class="view_info_content" >
					<input type="checkbox" name="effectWay" value="0">&nbsp;即时生效
					<input type="checkbox" name="effectWay" value="1">&nbsp;次日生效
					<input type="checkbox" name="effectWay" value="2">&nbsp;次月生效
					<input type="checkbox" name="effectWay" value="3">&nbsp;次次月生效
				</td>
			</tr>
			<tr>
				<th class="view_info_title" style="width: 10%">
					关闭生效方式 ：
				</th>
				<td class="view_info_content" >
					<input type="checkbox" name="neffectWay" value="0">&nbsp;即时生效
					<input type="checkbox" name="neffectWay" value="1">&nbsp;次日生效
					<input type="checkbox" name="neffectWay" value="2">&nbsp;次月生效
					<input type="checkbox" name="neffectWay" value="3">&nbsp;次次月生效
				</td>
			</tr>
			<tr>
				<th width="10%" align="right">业务状态：</th>
				<td class="form_table_content">
				<select id="state" name="state" style="width: 150px;">
					<option value="2" selected="selected">待审核</option>
				</select>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">支付方式：</th>
				<td class="form_table_content">
				<select id="suppPayWay" name="suppPayWay" style="width: 150px;">
					<option value="0">话费支付</option>				
					<option value="1">积分或M值支付</option>
					<option value="2">两种都支持</option>
					<option value="3">其它</option>
				</select>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">资费类别：</th>
				<td class="form_table_content">
				<select id="feeType" name="feeType"  style="width: 150px;">
					<option value="0">免费</option>				
					<option value="1">包月</option>
					<option value="2">包年</option>
					<option value="3">单次</option>
					<option value="4">按数量计费</option>
					<option value="5">其它</option>
				</select>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">资费：</th>
				<td class="form_table_content">
				<input type="text" class="form_input" maxlength="10" name="fee" id="fee" />
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">扣费方式：</th>
				<td class="form_table_content">
				<select id="deductWay" name="deductWay" style="width: 150px;">
				</select>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">积分所需数量：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="10" name="feeScoreQ" value="" id="feeScoreQ" />
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">M值支付所需数量：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="10" name="feeScoreM" value="" id="feeScoreM" />
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">办理类别：</th>
				<td class="form_table_content">
				<select id="processType" name="processType" style="width: 150px;">
					<option value="0">查询</option>
					<option value="1">开关</option>
					<option value="2">其它</option>				
				</select>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">是否末级：</th>
				<td class="form_table_content">
				<select id="mj" name="mj" style="width: 150px;">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">搜索关键词：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="500" name="searchWords" id="searchWords" />
				多个关键词以空格隔开
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">客户端下载地址：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="1000" name="busiClientUrl" value="" id="busiClientUrl" />
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">业务宣传语：</th>
				<td class="form_table_content"><input type="text" class="form_input" name="busiAdvl" value="" id="busiAdvl" />
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">特点：</th>
				<td class="form_table_content">
				<textarea rows="2" cols="30" id="busiFeature" name="busiFeature"></textarea>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">优惠活动：</th>
				<td class="form_table_content">
				<textarea rows="2" cols="30" id="busiPrivilege" name="busiPrivilege"></textarea>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">温馨提示：</th>
				<td class="form_table_content">
				<textarea id="prompt" name="prompt"></textarea>
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">扩展属性：</th>
				<td class="form_table_content">
					<span id="businessExtraList"></span><input id="exattrBt" type="button" value="选择">
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">业务标签：</th>
				<td class="form_table_content">
					<span id="businessTagList"></span><input id="tagsBt" type="button" value="选择">
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">城市：</th>
				<td class="form_table_content">
				<span id="cityList"></span><input id="areasBt" type="button" value="选择">
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">品牌：</th>
				<td class="form_table_content" id="brandList">
					
				<span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
				<th width="10%" align="right">渠道：</th>
				<td class="form_table_content">
					<input name="channel" type="checkbox" value="01" onclick="showTabs();">网厅&nbsp;&nbsp;
					<input name="channel" type="checkbox" value="02" onclick="showTabs();">掌厅
				<span class="errorMsg"></span> </td>
 			</tr>
		</table>
		<div class="breadcrumb">
			<span></span>业务基础信息:个性
		</div>
		<%--<div id="tab" class="dhtmlxTabBar"  style="width:100%; height:100%;" imgpath="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/" oninit="doOnInit()">
	        <div id="tab_01" name="网厅">
				<%@include file="businessInfo_add_w.jsp" %>
	    	</div>
	        <div id="tab_02" name="掌厅">
	        	<%@include file="businessInfo_add_z.jsp" %>
	        </div>
	        <div id="tab_other" name="">
	        </div>
	    </div>
		--%>
		<div id="tab" style="width:100%; height:105%;position: relative;overflow:hidden;display: none;"></div>
		<div id='tab_01' name="网厅">
			<%@include file="businessInfo_add_w.jsp" %>
		</div>
		<div id='tab_02'  name="掌厅">
			<%@include file="businessInfo_add_z.jsp" %>
		</div>
		<div id="tab_other" name="">
	        </div>
		
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
	<iframe name="upload_area" frameBorder="0" height="0"></iframe>
  </body>
</html>
