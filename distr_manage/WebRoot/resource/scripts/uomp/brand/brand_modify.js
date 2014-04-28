$(document).ready(function () {
        component.initForm();
        component.initValidate();
    }
);

var component = {
		// 初始化校验规则
		initValidate: function () {
		jQuery.validator.addMethod("isCode", function (value, element) { 
			
			var tel = /^[A-Za-z_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format("请输入规范的品牌编码"));  
        ValidateUtil.validate({
            targetForm: "editForm",
            rules: {
                brandName: {required: true, minlength: 1, maxlength: 200},
                brandNum:{required: true, minlength: 1, maxlength: 10,isCode:"brandNum"},
                memo:{required: false, minlength: 0, maxlength: 1000}
            },
            messages: {
            	brandName: {required: "请输入品牌名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	brandNum: {required: "请输入品牌编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"请输入规范的品牌编码"},
            	memo:{ minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
            }
        });
	},

    initForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "brand",
                "reqMethod": "queryOneBrand",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                var result = ret.retObj;
                $("#brandNum").val(result.brandNum);
                $("#brandName").val(result.brandName);
				$("#jbNum").val(result.jbNum);
				$("#mj").val(result.mj);
				$("#memo").val(result.memo);
				$("#bossCode").val(result.bossCode);
            }
        });
    },

    saveForm: function () {
    	if (!$('#editForm').valid()) {
            return;
        }
        var brandNum = $("#brandNum").val();
		var brandName = $("#brandName").val();
		var jbNum = $("#jbNum").val();
//		var jb = $("#jb").val();
		var mj = $("#mj").val();
		var memo = $("#memo").val();
		var bossCode = $("#bossCode").val();

        $.singleReq({
            data: {
                "reqUrl": "brand",
                "reqMethod": "updateBrand",
                "brandNum": brandNum,
                "brandName": brandName,
                "jbNum": jbNum,
//                "jb": jb,
                "mj": mj,
                "memo": memo,
                "bossCode":bossCode
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




