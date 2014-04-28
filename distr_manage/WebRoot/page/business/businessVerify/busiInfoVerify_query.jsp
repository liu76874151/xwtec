<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>业务审核</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/dhtmlxcombo.css"></link>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_filter.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_mcol.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/dhtmlxcombo.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/ext/dhtmlxcombo_whp.js"></script>    
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessVerify/busiInfoVerify_query.js"></script>
</head>
<body style="overflow: auto;">
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>业务信息审核--查询</div>
    <fieldset class="defaultFieldset">
        <legend>业务审核查询</legend>
        <form id="queryForm" name='queryForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
                <tr>
                    <th width="10%">业务类型：</th>
                    <td >
                    <div id="combo_zone" style="width:200px; height:30px;"></div>
                    <span class="errorMsg"></span></td>
                    <th width="10%" >业务名称：</th>
                    <td ><input type="text" maxlength="100" name="busiName" id="busiName" value=""><span class="errorMsg"></span></td>
                    <th width="20%" >业务编码：</th>
                    <td ><input type="text" maxlength="100" name="busiNum" id="busiNum" value=""><span class="errorMsg"></span></td>
                </tr>
                <tr>
					<th align="right">资费类别：</th>
					<td>
						<select id="feeType" name="feeType"  style="width: 150px;">
							<option value="">-请选择-</option>
							<option value="0">免费</option>				
							<option value="1">包月</option>
							<option value="2">包年</option>
							<option value="3">单次</option>
							<option value="4">按数量计费</option>
							<option value="5">其它</option>
						</select>
					<span class="errorMsg"></span> </td>
					<th>办理类别：</th>
					<td>
						<select id="processType" name="processType" style="width: 150px;">
							<option value="">-请选择-</option>
							<option value="0">查询</option>
							<option value="1">开关</option>
							<option value="2">其它</option>				
						</select>
						<span class="errorMsg"></span>
					</td>
					<th>地区：</th>
					<td>
						<select id="city" name="city">
							<option value="">-请选择-</option>
						</select>
						<span class="errorMsg"></span>
					</td>
                </tr>
                <tr>
                    <th >地市审核状态：</th>
                    <td >
                    	<select id="localVerifyState" name="localVerifyState">
                    		<option value="">-请选择-</option>
                    		<option value="0">审核通过</option>
                    		<option value="1">审核不通过</option>
                    	</select>
                    <span class="errorMsg"></span></td>
                    <th >省级审核状态：</th>
                    <td>
                    	<select id="proVerifyState" name="proVerifyState">
                    		<option value="">-请选择-</option>
                    		<option value="0">审核通过</option>
                    		<option value="1">审核不通过</option>
                    	</select>
                    	<span class="errorMsg"></span></td>
                   	<th >业务状态：</th>
                    <td >
                   		<select id="state" name="state">
                   			<option value="">-请选择-</option>
                   			<option value="2">待审核</option>
                   			<option value="3">审核不通过</option>
                   			<option value="4">审核通过</option>
                   		</select>
                    <span class="errorMsg"></span></td>
                    <%--
                    <th >线上测试状态：</th>
                    <td >
                    	<select id="busiTestState" name="busiTestState">
                    		<option value="">-请选择-</option>
                    		<option value="0">测试通过</option>
                    		<option value="1">测试不通过</option>
                    	</select>
                    <span class="errorMsg"></span></td>--%>
                </tr>
                <tr>
                    <th >品牌：</th>
                    <td >
                   		<input name="brandNum" type="checkbox" value="QQT">全球通
                    	<input name="brandNum" type="checkbox" value="DGDD">动感地带
                    	<input name="brandNum" type="checkbox" value="SZX">神州行
                    <span class="errorMsg"></span></td>
                    <th >渠道：</th>
                    <td colspan="3">
                    	<input name="channelNum" type="checkbox" value="03" onclick="component.queryBusiType(0,5000);" >短厅
                    	<input name="channelNum" type="checkbox" value="02" onclick="component.queryBusiType(0,5000);">掌厅
                    	<input name="channelNum" type="checkbox" value="01" onclick="component.queryBusiType(0,5000);">网厅
                    <span class="errorMsg"></span></td>
                </tr>
                
                <tr>
                    <td colspan="6" style="text-align:center;">
                        <a href="javascript:void(0)" class="btn" onclick="component.query();">查 询</a>
                        <a href="javascript:void(0)" class="btn" onclick="document.queryForm.reset();">重 置</a>
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
    <div id="toolbar"></div>
    
    <div id="gridbox" style="width:600px;height:280px;background-color:white;width: 100%"></div>
   
    <div id="paging"></div>
</div>
</body>
</html>