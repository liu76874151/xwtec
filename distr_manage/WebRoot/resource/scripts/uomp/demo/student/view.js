$(document).ready(function () {
        component.viewForm();
    }
);

var component = {
    viewForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "student",
                "reqMethod": "findOneStudent",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#stuName").text(result.stuName);
                    $("#stuAge").text(result.stuAge);
                    $("#stuSex").text(result.stuSex);
                    $("#stuNative").text(result.stuNative);
                    $("#stuTel").text(result.stuTel);
                    $("#stuAddr").text(result.stuAddr);
                }
                else {
                    alert(ret.retMsg);
                }
            }
        });
    }
}
