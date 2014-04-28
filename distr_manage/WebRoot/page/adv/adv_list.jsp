<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>广告管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_filter.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_mcol.js"></script>
    
    <!-- 
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_pgn.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_splt.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_drag.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_hmenu.js"></script>
     -->
    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/adv/adv_list.js"></script>
</head>
  
  <body> 

   <div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>广告--查询</div>

       
    <fieldset class="defaultFieldset">
        <legend>广告查询</legend>
        <div   style="width: 100%" >
        </div>
       
			<form id="queryForm" name='queryForm' action="" method="post">
			<div>
			
			</div>
           <table width="100%" class="defaultTable tableStyle1" border="1px">
						<tr>
							<th>
								广告编码：
							</th>
							<td>
								<input type="text" name="advNum" id='advNum' value="">
								<span class="errorMsg"></span>
							</td>

							<th>
								广告名称：
							</th>
							<td>
								<input type="text" name="advName" id='advName' value="">
								<span class="errorMsg"></span>
							</td>
							<th>
								地区：
							</th>
							<td>
								<select name="advAreaNum" id="advAreaNum">
									<option value="">
										--请选择--
									</option>
								</select>
							</td>
							<th>
								位置：
							</th>
							<td>
								<select name="positionNum" id="positionNum">
									<option value="">
										--请选择--
									</option>
								</select>
							</td>
						</tr>
						<tr>


							<th>
								运行状态：
							</th>
							<td>
								<select name="useState" id="useState">
									<option value="">
										--请选择--
									</option>
									<option value="1">
										启用
									</option>
									<option value="0">
										停用
									</option>
								</select>
							</td>

							<th>
								审核状态：
							</th>
							<td>
								<select name="auditState" id="auditState">
									<option value="">
										--请选择--
									</option>
									<option value="0">
										未审核
									</option>
									<option value="1">
										审核通过
									</option>
									<option value="2">
										审核不通过
									</option>
								</select>
							</td>
							<th>
								品牌：
							</th>
							<td>
								<select name="advBrandNum" id="advBrandNum">
									<option value="">
										--请选择--
									</option>
								</select>
							</td>
							<td colspan="2"></td>

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
        
</div>

  </body>
</html>
