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
                pageletNum: {required: true, minlength: 1, maxlength: 20},
                pageletName: {required: true, minlength: 1, maxlength: 20},
                step: {required: true, minlength: 1, maxlength: 1}
            },
            messages: {
                pageletNum: {required: "请输入分块编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                pageletName: {required: "请输入分块名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                step: {required: "请输入分块步骤", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
            }
        });
    },

    initForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "pageletInfo",
                "reqMethod": "findOnePageletInfo",
                "pageletNum": $("#pkid_1").val(),
                "version": $("#pkid_2").val()
            },
            success: function (ret) {
                var result = ret.retObj;
                $("#pageletNum").val(result.pageletNum);
                $("#pageletName").val(result.pageletName);
                $("#version").val(result.version);
                $("#vmTemplate").val(result.vmTemplate);
                $("#step").val(result.step);
                $("#desc").val(result.desc);
            }
        });
    },

    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }
        var data = this.constructData("pageletInfo", "updatePageletInfo");

        $.singleReq({
            data: data,
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
    },

    constructData: function (reqUrl, reqMethod) {
        var pageletNum = $("#pageletNum").val();
        var pageletName = $("#pageletName").val();
        var version = $("#version").val();
        var vmTemplate = $("#vmTemplate").val();
        var step = $("#step").val();
        var desc = $("#desc").val();

        var data = {
            "reqUrl": reqUrl,
            "reqMethod": reqMethod,
            "pageletNum": pageletNum,
            "pageletName": pageletName,
            "version": version,
            "vmTemplate": vmTemplate,
            "step": step,
            "desc": desc
        }
        return data;
    }
};




