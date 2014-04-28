<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../taglibs.jsp" %>
<html>
  <head>
    <title>文件上传demo</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/dhtmlxform.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>

  </head>
  <script type="text/javascript">
	$().ready(function(){
		$('#uploadBtn').unbind('click');
		$('#uploadBtn').bind('click',function(){
			var imeiFile = $('input[name=files]').val();
			if(!imeiFile){
				UOMPComp.showSuccessDialog('请选择导入文件。');
			}else if(!imeiFile.match(/^.+[\.txt|\.xls|\.xlsx]$/)){
				UOMPComp.showSuccessDialog('请选择正确的导入文件。');
			}else{
				document.addForm.submit();
			}	
		});
	});

	$().ready(function(){
		var formData = [
         	{type: "file", name: "file"},
         	{type: "button", name: "upload", value: "Upload"}
         ];
          
         var myForm = new dhtmlXForm("dhxForm", formData);
         myForm.attachEvent("onButtonClick", function(id){
         	if (id == "upload") {
         		document.getElementById("realForm").submit();
         	}
         });
          
         function myCallBack(state, fileName, fileSize) {
         	// see below what is this needed for
         }
	});
  	
  </script>
  <body>
  <form id="realForm" method="post" enctype="multipart/form-data" action="${contextPath}/actionDispatcher.do?reqUrl=fileuploadHandler&reqMethod=fileUpload" target="upload_area">
	<div id="dhxForm">
 
	</div>
  </form>
  <iframe name="upload_area" frameBorder="0" height="0"></iframe>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <hr>
   <form id="addForm" name="addForm" action="${contextPath}/actionDispatcher.do?reqUrl=fileuploadHandler&reqMethod=filesUpload" method="post" enctype="multipart/form-data" target="hidIframe">
  	       文件一: <input type="file" name="files"/><br/> 
  	       文件一: <input type="file" name="files"/><br/> 
  	       文件一: <input type="file" name="files"/><br/> 
   	<input type="button" value="upload" id="uploadBtn">
   </form>
   <iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
  </body>
</html>
