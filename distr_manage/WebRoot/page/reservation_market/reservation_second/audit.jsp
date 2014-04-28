<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>关联的二级预约营销方案</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/reservation_market/reservation_second/audit.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
</head>
<body>
<div title="" style="padding:10px;overflow:auto;">
	<input type="hidden" id="marketFirstPkid" value="${param.marketFirstPkid}"/>
	<input type="hidden" id="marketFirstCode" value="${param.marketFirstCode}"/>
	<input type="hidden" id="marketFirstName" value="${param.marketFirstName}"/>
    <fieldset class="defaultFieldset">
        <legend>二级预约营销案审核</legend>
        <div   style="width: 100%" >
        </div>
       
			<form id="queryForm" name='queryForm' action="" method="post">
			<div>
			
			</div>
           <table width="100%" class="defaultTable tableStyle1" border="1px">
                <tr>
                    <th width="10%">所属地市：</th>
                    <td width="5%">
                    	<select name="area" id="area">
                    	<option value="">--请选择--</option>
                    	</select>
                    </td>
                    
                   
                  
                   <th width="15%">二级预约营销案名称：</th>
                    <td width="5%">
                    	<input type="text" maxlength="100" name="viewName" id="viewName" value="">
                    </td>
                     <th width="10%"> 审核状态：</th>
                    <td width="16%">
                    	<select name="verifyState" id="verifyState">
                    		<option value="">-所有状态-</option>
                    		<option value="0">未审核</option>
                    		<option value="1">审核通过</option>
                    		<option value="2">审核不通过</option>
                    	</select>
                    </td>
                    <th width="10%">展示状态：</th>
                    <td width="×%" >
                    	<select name="isListView" id="isListView">
                    	<option value="">-所有状态-</option>
                    	<option value="0">未展示</option>
                    	<option value="1">已展示</option>
                    	</select>
                    </td>
            
                </tr>
                
                <tr>
               <th width="8%">渠道：</th>
               <td width="18%">
						<input type="checkbox" value="0"  name="tChannal">网厅
						<input type="checkbox" value="1" name="tChannal">掌厅
						<input  type="checkbox" value="2"  name="tChannal">短厅
			  </td>
               <th width="8%">开始时间：</th>
               <td width="5%"><input type="text"  name="beginTime" id="beginTime" readonly="readonly" value=""></td>
               
                <th width="12%">结束时间：</th>
               <td  colspan="3"><input type="text"  name="endTime" id="endTime" readonly="readonly"  value=""><span class="errorMsg"></span> </td>

			
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
    
    <div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
   
    <div id="paging"></div>
</div>
</body>
</html>