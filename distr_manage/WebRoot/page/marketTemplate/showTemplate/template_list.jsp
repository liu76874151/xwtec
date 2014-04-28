<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>模板</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@page import="com.xwtech.uomp.base.pojo.sso.LoginRequestBean"%>
	<%@page import="com.xwtech.uomp.base.util.SSOUtil"%>
	<%@page import="com.xwtech.uomp.base.pojo.admin.UserInfoBean"%>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
           <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.addMethod.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/marketTemplate/template_list.js"></script>
</head>
  <%
	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean,request).getUserInfoBean();
	String city = userInfoBean.getUserAreaCode();
	pageContext.setAttribute("userCityCode",city);
	%>
  <body>
    <input type="hidden" id="category" value="1">
   <div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb" id="titleView"><span></span>模板信息--查询</div>
    <input type="hidden" id="userCityCode" value="${pageScope.userCityCode}" >
    <fieldset class="defaultFieldset">
        <legend>模板查询</legend>
			<form id="queryForm" name='queryForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
                <tr>
                    <th width="20%">模板名称：</th>
                    <td width="30%"><input type="text" maxlength="100" name="templateName" id="templateName" ></td>
                    <th width="20%">所属城市：</th>
                    <td width="30%">
                    <select name="city" id="city" >
                    <option value=''>--请选择--</option>
                    </select>
                    </td>
                </tr>
                
                <tr>
                  <th width="20%">状态：</th>
                    <td width="30%">
                 		<select id="state">
							<option value=''>--请选择--</option>
							<option value='0'>无效</option>
							<option value='1'>有效</option>
						</select>
                    </td>
                     <td colspan="2"></td>
                </tr>
                <tr>
                    <td colspan="4" style="text-align:center;">
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
