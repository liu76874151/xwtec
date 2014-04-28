$(document).ready(function () {
        funPage.init();
    }
);

var funPage =
{
    init: function () {
        var funId = $("#funId")[0].value;
        $.singleReq({
            data: {
                "reqUrl": "funinfo",
                "reqMethod": "queryFunInfo",
                "funId": funId
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var funInfo = ret.retObj.funInfo;
                    $("#funcId").html(funInfo.funcId);
                    $("#funcName").html(funInfo.funcName);
                    $("#subSysName").html(funInfo.subSysName);
                    $("#funcType").html(funInfo.funcType);
                    var funcType = funInfo.funcType;
                    if (funcType == '0') {
                        $("#funcType").html('模块');
                    } else if (funcType == '1') {
                        $("#funcType").html('入口页面');
                    } else if (funcType == '2') {
                        $("#funcType").html('子页面');
                    } else if (funcType == '3') {
                        $("#funcType").html('业务操作');
                    }
                    $("#funcURI").html(funInfo.funcURI);
                    $("#jbNum").html(funInfo.jbNum);
                    $("#jb").html(funInfo.jb);
                    $("#bz").html(funInfo.bz);
                    $("#mj").html(funInfo.mj);
                }
                else {
                    alert(ret.resMsg);
                }
            }
        });

    }
};