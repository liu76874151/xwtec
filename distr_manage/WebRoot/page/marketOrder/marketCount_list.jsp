<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>营销案统计查询</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
 	  <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
    <script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
   
     
    <script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar_start.js"></script>
      
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/marketOrder/marketCount_list.js"></script>
</head>
  
  <body>
  <input type="hidden" id="sql" value=""/>
   <div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>营销案统计--查询</div>
    <fieldset class="defaultFieldset">
        <legend>营销案统计查询</legend>
        <div   style="width: 100%" >
        </div>
       
			<form id="queryForm" name='queryForm' action="" method="post">
			<div>
			
			</div>
           <table width="100%" class="defaultTable tableStyle1" border="1px">
                <tr>
                  
                    
                  
                  <th width="">营销案类型：</th>
                    <td width="" >
                    	<select name="type" id="type" style="width: 90px">
                    	<option value="">--请选择--</option>
                    	<option value="0">一般营销案</option>
                    	<option value="1">宽带营销案</option>
                    
                    	</select>
                    </td>
                     <th width=""> 一级营销案：</th>
                    <td width="">
                    <select name="marketFirstPkid" id="marketFirstPkid" onchange="component.initmarketSecod()" style="width: 150px">
                    	<option value="">--请选择--</option>
                    	</select>
                    </td>
                  <th width=""> 二级营销案：</th>
                    <td width="">
                        <select name="marketSecondPkid" id="marketSecondPkid" style="width: 150px">
                    	<option value="">--请选择--</option>
                    	</select>
                    </td>
                    <th width="">地市：</th>
                    <td width="" >
                    	<select name="city" id="city" style="width: 90px">
                    	<option value="">--请选择--</option>
                    	</select>
                    </td>
           
                </tr>
                
                <tr>
              
               <th width="">开始时间：</th>
               <td width=""><input type="text"  name="beginTime" id="beginTime"  value=""></td>
               
                <th width="">结束时间：</th>
               <td   width=""><input type="text"  name="endTime" id="endTime"   value=""></td>
				 <th width="">渠道：</th>
               <td  width="" colspan="3">
               <input type="checkbox"  name="channelType"  value="4"/>网厅 
               <input type="checkbox" name="channelType"  value="5"/>掌厅 
               <input type="checkbox" name="channelType"  value="6"/>短厅 
               </td>
              

                </tr> 
             
                <tr>
                    <td colspan="8" style="text-align:center;" width="100%">
                        <a href="javascript:void(0)" class="btn" onclick="component.query();">查 询</a>
                        <a href="javascript:void(0)" class="btn" onclick="document.queryForm.reset();">重 置</a>
                         <a href="javascript:void(0)" class="btn" onclick="component.marketCountToExcel();">导出</a>
                    </td>
                </tr>
            </table> 
        </form>
    </fieldset>
    <div id="toolbar"></div>
    <div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
    <div id="paging"></div>
         
</div>

  </body>
</html>
