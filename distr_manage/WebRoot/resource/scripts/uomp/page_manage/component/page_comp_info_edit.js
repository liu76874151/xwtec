$(document).ready(function () {
        component.initValidate();
        component.initForm();
    }
);

var component =
{
    //初始化校验规则
    initValidate: function () {
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
                compNum: {required: true, minlength: 1, maxlength: 20},
                compName: {required: true, minlength: 1, maxlength: 3},
                compType: {required: true}
            },
            messages: {
                compNum: {required: "请输入组件编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                compName: {required: "请输入组件名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                compType: {required: "请选择组件类型"}
            }
        });
    },

    initForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "pageCompInfo",
                "reqMethod": "findOnePageCompInfo",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                var result = ret.retObj;
                $("#compNum").val(result.compNum);
                $("#compName").val(result.compName);
                $("#compType").val(result.compType);
                $("#compUrl").val(result.compUrl);
                $("#containerId").val(result.containerId);
                $("#desc").val(result.desc);
            }
        });
    },

    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }
        var compNum = $("#compNum").val();
        var compName = $("#compName").val();
        var compType = $("#compType").val();
        var compUrl = $("#compUrl").val();
        var containerId = $("#containerId").val();
        var desc = $("#desc").val();

        $.singleReq({
            data: {
                "reqUrl": "pageCompInfo",
                "reqMethod": "updatePageCompInfo",
                "compNum": compNum,
                "compName": compName,
                "compType": compType,
                "compUrl": compUrl,
                "containerId": containerId,
                "desc": desc
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




