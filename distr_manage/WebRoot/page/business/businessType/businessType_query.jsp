<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>业务扣费方式</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/businessType/css/sysStyle.css" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
    
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_filter.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_mcol.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessType/businessType-query.js"></script>
</head>
<body style="overflow: auto;">
<input type="hidden" id="channelNumP" name="channelNumP" value="${param.channelNum }">
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>业务分类--查询</div>
    <fieldset class="defaultFieldset">
        <legend>业务分类查询</legend>
        <form id="queryForm" name='queryForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
           	 	<tr>
                    <th width="15%">分类名称：</th>
                    <td width="20%"><input type="text" maxlength="200" name="busiTypeName" id="busiTypeName" value=""></td>
                    <th width="15%">渠道：</th>
                    <td width="20%">
                    	<select id="channelNum" name="channelNum" onchange="component.queryBusiType(0,1000);">
                    		<option value="01">网厅</option>
                    		<option value="02">掌厅</option>
                    	</select>
                    </td>
                    <th width="15%">分类级别：</th>
                    <td>
                    	<select id="jb" name="jb">
                    		<option value="">-请选择-</option>
                    		<option value="1">1</option>
                    		<option value="2">2</option>
                    		<option value="3">3</option>
                    		<option value="4">4</option>
                    		<option value="5">5</option>
                    		<option value="6">6</option>
                    		<option value="7">7</option>
                    		<option value="8">8</option>
                    		<option value="9">9</option>
                    		<option value="10">10</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align:center;">
                        <a href="javascript:void(0)" class="btn" onclick="component.queryBusiType(0,1000);">查 询</a>
                        <a href="javascript:void(0)" id="confirmBt" style="display: none;" class="btn" onclick="component.getCheckedValue();">确定选择</a>
                        <a href="javascript:void(0)" class="btn" id="reset" onclick="document.queryForm.reset();">重 置</a>
                        <a href="javascript:void(0)" class="btn" onclick="component.deleteBusiType();">删除</a>
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
    <!-- 
    <div id="toolbar"></div>
    
    <div id="gridbox" style="width:600px;height:230px;background-color:white;width: 100%"></div>
   
    <div id="paging"></div>
     -->
     
     <div class="service-classification">
  <!--网厅个人登录菜单分类-->
  <div class="classification-list">
    <div class="classification-list-body">
      <div class="classification-item" id="busiTypeTb">
        
      </div>
    </div>
  </div>
  <!--网厅个人登录菜单分类-->
</div>
<script>
//显示隐藏
function $G(Read_Id) { return document.getElementById(Read_Id) }
function Effect(ObjectId,thisID){
	if ($G(ObjectId).style.display == 'none'){
		Start(ObjectId,'Opens');
		$G(thisID).className = "arrow-up"
	}else{
		Start(ObjectId,'Close');
		$G(thisID).className = "arrow-down"
	}
}
function Closed(ObjectId,thisID){
		Start(ObjectId,'Close');
		$G(thisID).className = "arrow-down"
}
function Start(ObjId,method){
	if (method == "Close"){
		$G(ObjId).style.display = "none";
		$("#"+ObjId).empty();
	}
	else if (method == "Opens"){
		$G(ObjId).style.display= "block";
	}
}
</script>
</div>
</body>
</html>