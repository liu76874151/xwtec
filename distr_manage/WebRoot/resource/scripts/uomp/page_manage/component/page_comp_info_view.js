$(document).ready(function () {
        component.viewForm();
    }
);

var component = {
    viewForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "pageCompInfo",
                "reqMethod": "findOnePageCompInfo",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#compNum").text(result.compNum);
                    $("#compName").text(result.compName);
                    $("#compType").text(result.compType);
                    $("#compUrl").text(result.compUrl);
                    $("#containerId").text(result.containerId);
                    $("#desc").text(result.desc);
                }
                else {
                    alert(ret.retMsg);
                }
            }
        });
    }
}
