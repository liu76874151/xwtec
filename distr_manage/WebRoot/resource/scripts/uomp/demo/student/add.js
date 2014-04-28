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
                stuName: {required: true, minlength: 1, maxlength: 20},
                stuAge: {required: true, minlength: 1, maxlength: 3},
                stuSex: {required: true},
                stuNative: {required: true}
            },
            messages: {
                stuName: {required: "请输入姓名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                stuAge: {required: "请输入年龄", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                stuSex: {required: "请选择性别"},
                stuNative: {required: "请选择籍贯"}
            }
        });
    },

    //初始化表单
    initForm: function () {
//        $.singleReq({
//            data: {
//                "reqUrl": "?",
//                "reqMethod": "??"
//            },
//            success: function (ret) {
//                if (ret) {
//                    if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
//                        if (ret.retObj) {
//                            var result = ret.retObj;
//                            //todo 加载界面上的一些字典项
//                        }
//                    } else {
//                        if (ret.resMsg) {
//                            UOMPComp.showFailedDialog(ret.resMsg, "");
//                        } else {
//                            UOMPComp.showFailedDialog("系统异常", "");
//                        }
//                    }
//                }
//            }
//        });
    },

    saveForm: function () {
        if (!$('#addForm').valid()) {
            return;
        }
        var stuName = $("#stuName").val();
        var stuAge = $("#stuAge").val();
        var stuSex = $("#stuSex").val();
        var stuNative = $("#stuNative").val();
        var stuTel = $("#stuTel").val();
        var stuAddr = $("#stuAddr").val();

        $.singleReq({
            data: {
                "reqUrl": "student",
                "reqMethod": "saveStudent",
                "stuName": stuName,
                "stuAge": stuAge,
                "stuSex": stuSex,
                "stuNative": stuNative,
                "stuTel": stuTel,
                "stuAddr": stuAddr
            },
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
    }
}
