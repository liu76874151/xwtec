$().ready(function() {
	component.initValidate();
	component.queryByKey();
});

var component = {
	saveForm : function() {
		if (!$('#editForm').valid()) {
	        return;
	    }
	    var deductNum = $("#deductNum").val();
		var deductWay = $("#deductWay").val();
		var bz = $("#bz").val();
		
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "updateBusiDeduct",
                "deductNum": deductNum,
                "deductWay": deductWay,
                "bz": bz
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
	},
	//初始化校验规则
    initValidate: function () {
        jQuery.validator.addMethod("isCode", function (value, element) { 
			
			var tel = /^[A-Za-z_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format("请输入规范的扣费方式编码"));  
        ValidateUtil.validate({
            targetForm: "editForm",
            rules: {
                deductNum: {required: true, minlength: 1, maxlength: 10,isCode:"deductNum"},
                deductWay: {required: true, minlength: 1, maxlength: 100},
                bz:{required: false, minlength: 0, maxlength: 1000}
            },
            messages: {
            	deductNum: {required: "请输入扣费方式编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"请输入规范的扣费方式编码"},
            	deductWay: {required: "请输入扣费方式名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	bz:{minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
            }
        });
        
    },
    queryByKey : function() {
		var deductNum = $("#deductNum").val();
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryBusiDeductBykey",
                "deductNum": deductNum
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                    $("#deductWay").val(result.deductWay);
                    $("#bz").val(result.bz);
                }
            }
        });
	}
    
}