$(document).ready(function () {
        component.viewForm();
    }
);

var component = {
    viewForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "pageletInfo",
                "reqMethod": "findOnePageletInfo",
                "pageletNum": $("#pkid_1").val(),
                "version": $("#pkid_2").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#pageletNum").text(result.pageletNum);
                    $("#pageletName").text(result.pageletName);
                    $("#version").text(result.version);
                    $("#vmTemplate").text(result.vmTemplate);
                    $("#step").text(result.step);
                    $("#desc").text(result.desc);
                }
                else {
                    alert(ret.retMsg);
                }
            }
        });
    }
}
