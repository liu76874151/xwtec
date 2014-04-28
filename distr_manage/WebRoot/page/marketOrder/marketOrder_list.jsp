<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>营销案订单查询</title>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/marketOrder/marketOrder_list.js"></script>
</head>
  
  <body>
  <input type="hidden" id="sql" value=""/>
   <div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>营销案订单--查询</div>
    <fieldset class="defaultFieldset">
        <legend>营销案订单查询</legend>
        <div   style="width: 100%" >
        </div>
       
			<form id="queryForm" name='queryForm' action="" method="post">
			<div>
			
			</div>
           <table width="100%" class="defaultTable tableStyle1" border="1px">
                <tr>
                    <th width="10%">地市：</th>
                    <td width="5%">
                    	<select name="city" id="city">
                    	<option value="">--请选择--</option>
                    	</select>
                    </td>
                    
                  
                   <th width="10%">订单状态：</th>
                    <td width="5%">
                   <input type="checkbox"  name="PayStatus"  value="0"/>未充值
                   <input type="checkbox" name="PayStatus"  value="1"/>充值成功
                    </td>
                     <th width="16%"> 一级营销案名称：</th>
                    <td width="18%"><input type="text" maxlength="100" name="marketFirstName" id="marketFirstName" value=""></td>
                  <th width="16%"> 二级营销案名称：</th>
                    <td width="18%"><input type="text" maxlength="100" name="marketSecondName" id="marketSecondName" value=""></td>
                </tr>
                <tr>
               <th width="10%">开始时间：</th>
               <td width="5%"><input type="text"  name="beginTime" id="beginTime"  value=""></td>
               
                <th width="10%">结束时间：</th>
               <td   width="5%"><input type="text"  name="endTime" id="endTime"  value=""></td>
				 <th width="10%">渠道：</th>
               <td  width="5%">
               <input type="checkbox"  name="channelType"  value="4"/>网厅 
               <input type="checkbox" name="channelType"  value="5"/>掌厅 
               <input type="checkbox" name="channelType"  value="6"/>短厅 
               </td>
               
               <th width="10%">营销案类型：</th>
                    <td width="×%" >
                    	<select name="type" id="type">
                    	<option value="">--请选择--</option>
                    	<option value="0">一般营销案</option>
                    	<option value="1">宽带营销案</option>
                    
                    	</select>
                    </td>

                </tr> 
                <tr>
                    <td colspan="8" style="text-align:center;" width="100%">
                      <table>
                       <tr>
                    <th width=""> 客户手机号码：</th>
                    <td width=""><input type="text" maxlength="100" name="telNum" id="telNum" value=""></td>
                  
                  
                   <th width="">支付方式：</th>
                    <td width="">
		                <input type="checkbox"  name="payType"  value="3"/>话费
		               <input type="checkbox" name="payType"  value="1"/>在线支付
		               <input type="checkbox" name="payType"  value="2"/>银联
		               <input type="checkbox" name="payType"  value="4"/>支付宝
                    </td>
                      <th width="">品牌：</th>
                    <td width="">
		                <input type="checkbox"  name="brandId"  value="1"/>全球通
		               <input type="checkbox" name="brandId"  value="2"/>神州行
		               <input type="checkbox" name="brandId"  value="3"/>动感地带 
                    </td>
                    
                  <th width=""> 是否存在物流：</th>
                    <td width="">
                    <input type="radio" value="0" />&nbsp;否
                    <input type="radio" value="1" />&nbsp;是
                    </td>
            
                </tr>
                      </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" style="text-align:center;" width="100%">
                        <a href="javascript:void(0)" class="btn" onclick="component.query();">查 询</a>
                        <a href="javascript:void(0)" class="btn" onclick="document.queryForm.reset();">重 置</a>
                        <a href="javascript:void(0)" class="btn" onclick="component.marketOrderToExcel();">导出</a>
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
