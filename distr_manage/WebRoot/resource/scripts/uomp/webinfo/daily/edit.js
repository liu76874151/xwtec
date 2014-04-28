$(document).ready(function () {
        component.initForm();
    }
);

var component =
{
	

    initForm: function () {
	$("#type"+$("#type").val()).attr("checked",true);
        $.singleReq({
            data: {
                "reqUrl": "dailyPaper",
                "reqMethod": "queryOneDailyPaper",
                "id": $("#beginTime").val(),
                "num": $("#num").val(),
                "type": $("#type").val()
               
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#value").val(result.value);
                }
                else {
                    alert(ret.retMsg);
                }
            }
        });

    },

    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }
        
      

        $.singleReq({
            data: {
                "reqUrl": "dailyPaper",
                "reqMethod": "updateDailyPaper",
                "id": $("#beginTime").val(),
                "num": $("#num").val(),
                "type": $("#type").val(),
                "value":$("#value").val()
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




