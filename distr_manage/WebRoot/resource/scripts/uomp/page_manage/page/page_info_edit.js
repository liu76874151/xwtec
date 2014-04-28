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
                pageNum: {required: true, minlength: 1, maxlength: 20},
                pageName: {required: true, minlength: 1, maxlength: 20},
                pageContent: {required: true}
            },
            messages: {
                pageNum: {required: "请输入页面编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                pageName: {required: "请输入页面名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                pageContent: {required: "请页面内容"}
            }
        });
    },

    initForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "pageInfo",
                "reqMethod": "findOnePageInfo",
                "pageNum": $("#pkid_1").val(),
                "version": $("#pkid_2").val()
            },
            success: function (ret) {
                var result = ret.retObj;
                $("#pageNum").val(result.pageNum);
                $("#pageName").val(result.pageName);
                $("#version").val(result.version);
                $("#pageContent").val(result.pageContent);
                $("#confirmComp").val(result.confirmComp);
                $("#resultComp").val(result.resultComp);
                $("#type").val(result.type);
                $("#desc").val(result.desc);
            }
        });
    },

    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }

        var data = this.constructData("pageInfo", "updatePageInfo");

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
        var pageNum = $("#pageNum").val();
        var pageName = $("#pageName").val();
        var version = $("#version").val();
        var pageContent = $("#pageContent").val();
        var confirmComp = $("#confirmComp").val();
        var resultComp = $("#resultComp").val();
        var type = $("#type").val();
        var desc = $("#desc").val();

        var data = {
            "reqUrl": reqUrl,
            "reqMethod": reqMethod,
            "pageNum": pageNum,
            "pageName": pageName,
            "version": version,
            "pageContent": pageContent,
            "confirmComp": confirmComp,
            "resultComp": resultComp,
            "type": type,
            "desc": desc
        }
        return data;
    }
};




