$().ready(function() {
	component.initValidate();
});
jQuery.extend({
    singleSync: (function () {
        var default_options = {
            "type": "post",
            "timeout": "320000",
            "contentType": "application/x-www-form-urlencoded; charset=UTF-8",
            "url": GLOBAL_INFO.COMMON_REQ_URI,
            "async":false,
            "dataType": "json",
            "success": function (data) {
                alert("Ajax Success!");
            },
            "error": function (request, textStatus, errorThrown) {
            },
            "complete": function () {
            }
        };
        return function (user_options) {
            var options = {};
            $.extend(options, default_options, user_options);
            $.ajax(options);
        };
    })()
});
var component = {
	saveForm : function() {
		if (!$('#addForm').valid()) {
	        return;
	    }
	    if(component.queryBusiDeduct()){
	    	UOMPComp.showTipDialog("扣费方式编码已经存在！", "");
	    	return;
	    }
		var deductNum = $("#deductNum").val().toUpperCase();
		var deductWay = $("#deductWay").val();
		var bz = $("#bz").val();
		if(!(/^[A-Za-z_]+$/).test(deductNum)){
		    UOMPComp.showTipDialog("英文名输入不正确！", "");
	    	return;
		}
		
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "addBusiDeduct",
                "deductNum": deductNum,
                "deductWay": deductWay,
                "bz": bz
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
	},
	//初始化校验规则
    initValidate: function () {
    	jQuery.validator.addMethod("isCode", function (value, element) { 
			
			var tel = /^[A-Za-z_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format("请输入规范的扣费方式编码"));  
        ValidateUtil.validate({
            targetForm: "addForm",
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
    queryBusiDeduct : function() {
		var deductNum = $("#deductNum").val().toUpperCase();
		var flag = true;
		$.singleSync({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryBusiDeductBykey",
                "deductNum": deductNum
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                	if(result==undefined){
                		flag = false;
                	}else{
                		flag = true;
                	}
                }
                else {
                    flag = true;
                }
            }
        });
        return flag;
	}
}