<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@ include file="../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>子系统管理查询页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css" type="text/css"></link>
	<link rel="stylesheet" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css" type="text/css"></link>
    <link rel="stylesheet" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css" type="text/css"></link>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>        
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/dhtmlxform.js"></script>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript"  src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript"></script>
    <script type="text/javascript"  src="${contextPath}/resource/scripts/main.js" language="javascript" ></script>
 
 
 
 
    <%--<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/sys_source_query.js"></script>
     --%>
    
</head>
  
<body onload="doOnLoad()">
  
<div id="myForm" align="center">
	 <div id="xtgl_top" style="width: 100%;height: 30px;">
       <p align="center"><font style="font-size: 17px" >子系统管理</font></p>  
	 </div>

    <!-- 搜索 文本框及按钮 --> 
    <div id="searchForm"  style="width: 100%;height: 10px;"></div>
    
	<!--表格填充 -->
    <div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
</div>


     <script type="text/javascript">  
		   
		   var searchForm,formData,mygrid;
		   
		   mygrid = new dhtmlXGridObject('gridbox');
		   mygrid.setImagePath("../../scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/imgs/");
			 //设置第一行                    
		   mygrid.setHeader("<a href='${contextPath}/page/admin/sub_system_add.jsp' target='_self'>添加</a>&nbsp;&nbsp;<a href='javascript:window.location.reload();' class='btn refresh'>刷新</a>,#cspan,#cspan,#cspan,#cspan,#cspan,#cspan");  
		   mygrid.attachHeader("序号,子系统编码,子系统名称,子系统URL地址,序号,备注,单项操作");
			
		   mygrid.setInitWidths("80,80,120,300,80,80,*");
		   mygrid.setColAlign("center,center,center,center,center,center,center,center");
		   mygrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
		   mygrid.setColSorting("int,str,str,str,str,int,str"); 
		   mygrid.setSkin("dhx_skyblue");
		   mygrid.init();
		  
		   
		   
		   var data=null;
		   function doOnLoad() {
				    formData = [
		            {type:"settings",position:"label-top"},
					{type: "input", label: "子系统编码",name:"sysNumSearch", inputLeft:80 ,maxLength:20,position:"label-left",validate: "ValidNumeric" },
					{type: "newcolumn"},
					{type: "button", name: "search_subsystem", value: "查询", command: "save" ,offsetLeft:2 }
				];
			 
		     searchForm = new dhtmlXForm("searchForm", formData);
		     //当点击按钮，就会根据sysNum查询子系统
		     searchForm.attachEvent("onButtonClick", function(name, command){
		     if(name=="search_subsystem"){
		    	 var sysNum =searchForm.getItemValue("sysNumSearch");
		    		 $.singleReq({
		    				data : 
		    				{
		    					"reqUrl" : "subSystem",
		    					"sysNum" :  sysNum,
		    					"reqMethod" : "querySubSystemByNum"
		    				},
		    				success : function(ret)
		    				{
		    				       data=ret.retObj;	
		    				       alert(data);
		    				       
		    				       			
		    				}
		    			});
		    		return data;
		    	  } 
		     });
		}
	
		  
		   
var indexPage = 
{
	//当加载此页面，就会查询所有的子系统
	 queryAllSubsystem: function()
	{
		$.singleReq({
			data : 
			{
				"reqUrl" : "subSystem",
				"reqMethod" : "querySubSystemAll"
			},
			success : function(ret)
			{
				var data=ret.retObj;
				//如何遍历集合然后显示呢？
				
				 mygrid.load(data, "json");
		
			}
		});
	}
};




		   
     </script>
  </body>
</html>
