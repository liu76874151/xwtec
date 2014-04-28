$().ready(function() {
	component.initValidate();
	component.queryByKey();
});

var component = {
	saveForm : function() {
		if (!$('#editForm').valid()) {
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
                "reqMethod": "updateBusiExtra",
                "attrKey": attrKey,
                "attrName": attrName,
                "attrType": attrType,
                "attrDesc": attrDesc
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
    queryByKey : function() {
		var attrKey = $("#attrKey").val().toUpperCase();
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryBusinessExtraByKey",
                "attrKey": attrKey
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
//                    $("#attrKey").val(result.attrKey);
                    $("#attrName").val(result.attrName);
                    $("#attrDesc").val(result.attrDesc);
                    $("#attrType").val(result.attrType);
                }
            }
        });
	}
    
}