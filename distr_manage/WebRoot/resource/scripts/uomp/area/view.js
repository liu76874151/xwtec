$(document).ready(function () {
        component.viewForm();
    }
);


var component = {
    viewForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "area",
                "reqMethod": "findOneArea",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#areaNum").val(result.areaNum);
                    $("#areaName").val(result.areaName);
                    $("#areaJbNum").val(result.areaJbNum);
                    $("#areaBz").val(result.areaBz);
                    $("#areaBossCode").val(result.areaBossCode);
                    $("#areaBossOrgid").val(result.areaBossOrgid);
                    $("#areaJb_"+result.areaJb).attr("checked",true);
                }
                else {
                    alert(ret.retMsg);
                }
            }
        });
    }
}
