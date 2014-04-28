<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>一级营销案方案管理</title>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/market/marketFirst/query.js"></script>
</head>
  
  <body>
  <input type="hidden" id="isSJ" value="0"/>
   <div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>一级营销案--查询</div>

           <!--  <div id="tab" class="dhtmlxTabBar"  style="width:100%; height:100%;" imgpath="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/">   
        <div id="tab_1" name="掌厅">-->
    <fieldset class="defaultFieldset">
        <legend>一级营销案查询</legend>
        <div   style="width: 100%" >
        </div>
       
			<form id="queryForm" name='queryForm' action="" method="post">
			<div>
			
			</div>
           <table width="100%" class="defaultTable tableStyle1" border="1px">
                <tr>
                    <th >所属地市：</th>
                    <td >
                    	<select name="area" id="area">
                    	<option value="">--请选择--</option>
                    	</select>
                    </td>
                    
                   
                  
                   <th >展示状态：</th>
                    <td >
                    	<select name="isListView" id="isListView">
                    	<option value="">--请选择--</option>
                    	<option value="0">展示</option>
                    	<option value="1">不展示</option>
                    	</select>
                    </td>
                     <th> 掌厅一级方案名称：</th>
                    <td ><input type="text" maxlength="100" name="viewName" id="viewName" value=""></td>
                    <th >营销案类型：</th>
                    <td  >
                    	<select name="activityType" id="activityType">
                    	<option value="">所有营销案</option>
                    	<option value="0">一般营销案</option>
                    	<option value="1">所有宽带营销案</option>
                    	<option value="2">开户宽带营销案</option>
                    	<option value="3">续费宽带营销案</option>
                    	</select>
                    </td>
            
                </tr>
                
                <tr>
              
               <th >开始时间：</th>
               <td ><input type="text"  name="beginTime" id="beginTime" readonly="readonly" value=""></td>
               
                <th >结束时间：</th>
               <td  ><input type="text"  name="endTime" id="endTime" readonly="readonly"  value=""></td>
				 <th >渠道：</th>
               <td  >
               <input type="checkbox"  name="channalData"  id="channalData_4" value="4"/> <label for="channalData_4">网厅</label>
               <input type="checkbox" name="channalData"  id="channalData_5" value="5"/> <label for="channalData_5">掌厅</label>
               <input type="checkbox" name="channalData"  id="channalData_6" value="6"/><label for="channalData_6">短厅 </label>
               </td>
               
                <th >营销案类别：</th>
               <td>
               <input type="radio" name="unityFlag" value="0" id="unityFlag_0"/><label for="unityFlag_0">地市营销案 </label>
               <input type="radio"  name="unityFlag" value="1" id="unityFlag_1"/><label for="unityFlag_1">统一营销案 </label>
               </td>

                </tr> 
                <tr>
                    <td colspan="8" style="text-align:center;" width="100%">
                        <a href="javascript:void(0)" class="btn" onclick="component.query();">查 询</a>
                        <a href="javascript:void(0)" class="btn" onclick="document.queryForm.reset();">重 置</a>
                    </td>
                </tr>
            </table> 
        </form>
    </fieldset>
    <div id="toolbar"></div>
    <div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
    <div id="paging"></div>
          <!-- 	</div>
    	<div id="tab_3" name="网厅"></div>
        <div id="tab_2" name="短厅"></div></div>-->
</div>

  </body>
</html>
