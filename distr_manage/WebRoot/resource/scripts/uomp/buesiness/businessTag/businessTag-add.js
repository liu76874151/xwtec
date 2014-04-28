$().ready(function() {
	component.initValidate();
});

var component = {
	saveForm : function() {
		if (!$('#addForm').valid()) {
	        return;
	    }
	    if (this.selectBusiTagBean()) {
	    	UOMPComp.showTipDialog("该标签已存在！", "");
	    	return;
	    }
		var tagName = $("#tagName").val();
		var tagEname = $("#tagEname").val();
		var tagDesc = $("#tagDesc").val();
		
		if(!(/^[A-Za-z_]+$/).test(tagEname)){
		    UOMPComp.showTipDialog("英文名输入不正确！", "");
	    	return;
		}
		
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "addBusiTag",
                "tagName": tagName,
                "tagEname": tagEname,
                "tagDesc": tagDesc
            },
            success: function (ret) {
                if (ret.retCode == 0) {
//                    UOMPComp.showSuccessDialog("添加成功！", "");
                	
                    window.opener.location.reload();
                    window.close();
//                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
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
			var tel = /^[A-Za-z0-9_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format("请输入规范的标签英文名"));
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
                tagName: {required: true, minlength: 1, maxlength: 50},
                tagEname: {required: true, minlength: 1, maxlength: 50,isCode:"tagEname"},
                tagDesc:{required: false, minlength: 0, maxlength: 250}
            },
            messages: {
            	tagName: {required: "请输入标签名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	tagEname: {required: "请输入标签英文名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"请输入规范的标签英文名"},
            	tagDesc: {required: "请输入审核理由", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
            }
        });
        
    },
    selectBusiTagBean: function(){
    	var tagName = $("#tagName").val();
		var flag = true;
		$.singleSync({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "selectBusiTagBean",
                "tagState":"1",
                "tagName": tagName
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
                    flag = true;//验证失败
                }
            }
        });
        return flag;
	}
}