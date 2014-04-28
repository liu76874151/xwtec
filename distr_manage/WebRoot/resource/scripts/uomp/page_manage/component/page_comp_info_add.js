$(document).ready(function () {
    component.initValidate();
    component.initForm();
});

var component = {

    //初始化校验规则
    initValidate: function () {
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
                compNum: {required: true, minlength: 1, maxlength: 20},
                compName: {required: true, minlength: 1, maxlength: 30},
                compType: {required: true}
            },
            messages: {
                compNum: {required: "请输入组件编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                compName: {required: "请输入组件名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                compType: {required: "请选择组件类型"}
            }
        });
    },

    //初始化表单
    initForm: function () {

    },

    saveForm: function () {
        if (!$('#addForm').valid()) {
            return;
        }

        var data = this.constructData("pageCompInfo", "savePageCompInfo");

        $.singleReq({
            data: data,
            success: function (ret) {
                if (ret.retCode == 0) {
                    UOMPComp.showSuccessDialog("添加成功！", "");
                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("添加失败！", "");
                }
            }
        });
    },

    constructData: function (reqUrl, reqMethod) {
        var compNum = $("#compNum").val();
        var compName = $("#compName").val();
        var compType = $("#compType").val();
        var compUrl = $("#compUrl").val();
        var containerId = $("#containerId").val();
        var desc = $("#desc").val();

        var data = {
            "reqUrl": reqUrl,
            "reqMethod": reqMethod,
            "compNum": compNum,
            "compName": compName,
            "compType": compType,
            "compUrl": compUrl,
            "containerId": containerId,
            "desc": desc
        }
        return data;
    }
}
