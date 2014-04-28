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
	    
	    if(component.queryBusiExtra()){
	    	UOMPComp.showTipDialog("属性编码已经存在！", "");
	    	return;
	    }
	    
		var attrKey = $("#attrKey").val().toUpperCase();
		var attrName = $("#attrName").val();
		var attrType = $("#attrType option:selected[value!='-请选择-']").val();
		if(attrType==""){
			UOMPComp.showFailedDialog("请选择属性类型！", "");
			return;
		}
		var attrDesc = $("#attrDesc").val();
		
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "addBusiExtra",
                "attrKey": attrKey,
                "attrName": attrName,
                "attrType": attrType,
                "attrDesc": attrDesc
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
		}, $.validator.format("请输入规范的属性标识"));  
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
                attrKey: {required: true, minlength: 1, maxlength: 50,isCode:"attrKey"},
                attrName: {required: true, minlength: 1, maxlength: 200},
                attrType: {required: true}
            },
            messages: {
            	attrKey: {required: "请输入属性标识", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"请输入规范的属性标识"},
            	attrName: {required: "请输入属性名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	attrType: {required: "请选择属性类型"}
            }
        });
        
    },
    queryBusiExtra : function() {
		var attrKey = $("#attrKey").val().toUpperCase();
		var flag = true;
		$.singleSync({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryBusinessExtraByKey",
                "attrKey": attrKey
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