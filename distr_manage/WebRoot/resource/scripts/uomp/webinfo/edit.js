$(document).ready(function () {
        component.initForm();
    }
);

var component =
{
   

    initForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "webinfo",
                "reqMethod": "selectByPrimaryKey",
                "channelNum": $("#pkid").val()
            },
            success: function (ret) {
                var result = ret.retObj;
                $("#title").val(result.title);
                $("#notice").val(result.notice);
                $("#state_"+result.state).attr("checked",true);
            }
        });


    },

    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }
        var channelNum = $("#channelNum").val();
        var title = $("#title").val();
        var notice = $("#notice").val();
        var state = $("input[name=state]:checked").val();
       

        $.singleReq({
            data: {
                "reqUrl": "webinfo",
                "reqMethod": "updateByPrimaryKey",
                "channelNum": channelNum,
                "title": title,
                "notice": notice,
                "state": state
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    UOMPComp.showSuccessDialog("修改成功！", "");
                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
                }
            }
        });
    }
};




