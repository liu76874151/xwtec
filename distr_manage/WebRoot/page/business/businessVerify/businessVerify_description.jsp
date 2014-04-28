<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>
<html>
<head>
    <title>业务审核理由</title>
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
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript">
	    $().ready(function(){
			initValidate();
	   	});
    	function verifyProcess(){
    		if (!$('#addForm').valid()) {
    	        return;
    	    }
        	var verify = $("#verify").val();
        	var channelNum = $("#channelNum").val();
        	var busiNum = $("#busiNum").val();
        	var description = $("#description").val();
        	//description
    		$.singleReq({
                data: {
                    "reqUrl": "businessInfoHandler",
                    "reqMethod": "verifyBusinessInfo",
                    "busiNum": busiNum,
                    "channelNum" : channelNum,
                    "description":description,
                    "state":verify
                },
                success: function (ret) {
                    if (ret) {
                        if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                        	UOMPComp.showSuccessDialog("操作完成！", "");
                            top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                        } else {
                            if (ret.resMsg) {
                                UOMPComp.showFailedDialog(ret.resMsg, "");
                            } else {
                                UOMPComp.showFailedDialog("系统异常", "");
                            }
                        }
                    }
                }
            });
        }
    	//初始化校验规则
        function initValidate() {
            ValidateUtil.validate({
                targetForm: "addForm",
                rules: {
            		description:{required: true, minlength: 1, maxlength: 500}
                },
                messages: {
                	description: {required: "请输入审核理由", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
                	
                }
            });
            
        }
    </script>
</head>
<body>
<input id="verify" name="verify" value="${param.verify }" type="hidden">
<input id="busiNum" name="busiNum" value="${param.busiNum }" type="hidden">
<input id="channelNum" name="channelNum" value="${param.channelNum }" type="hidden">
<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>业务审核理由</div>
    <fieldset class="defaultFieldset">
        <legend>业务审核理由</legend>
        <form id="addForm" name='editForm' action="" method="post">
            <table width="98%" class="defaultTable tableStyle1">
                <tr>
                	<th width="20%">审核不通过理由：</th>
                    <td width="30%">
                    	<textarea rows="8" cols="50" id="description" name="description"></textarea><span class="errorMsg"></span>
                    </td>
                </tr>
            </table>
            <!-- 按钮操作区域开始 -->
			<table width="100%" height="40">
				<tr>
			    	<td align="center">
					<a href="javascript:void(0)" class="btn" onClick="verifyProcess();">确认提交</a>
					</td>
				</tr>
	   		</table>
        </form>
    </fieldset>
</div>
</body>
</html>