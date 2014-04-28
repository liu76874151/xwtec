$(document).ready(function () {
    component.initValidate();
    component.initForm();
});
jQuery.validator.addMethod("isImage", function (value, element) { 
	var positionType= $("input[name='positionType']:checked").val();
	if(positionType!=3){
    var RegExp = /^.+\.(jpg|bmp|gif|jpeg|png)$/; 
    return (value==null||value==""||value==undefined)? true:RegExp.test(value);
      }else{
    	return true;
    }
}, $.validator.format("只支持jpg，gif，bmp，jpeg格式的图片!"));  
var component = {

    // 初始化校验规则

    initValidate: function () {
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
        	    positionName: {required: true, minlength: 1, maxlength: 200},
        	    positionImage: {required: false,isImage: "#positionImage"},
        	    //positionNum:{required: true, minlength: 1, maxlength: 10},
        	    channelNum:{required: true}
            },
            messages: {
            	positionName: {required: "请输入广告位名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	positionImage: {required: "请选择图片",isImage:"只支持jpg，gif，bmp，jpeg，png格式的图片!"},
            	//positionNum:{required: "请输入广告位编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	channelNum:{required: "请选择渠道"}
            }
        });
    },

    // 初始化表单
    initForm: function () {

    },

    saveForm: function () {
        if (!$('#addForm').valid()) {
            return;
        }
        
        var positionName = $("#positionName").val();
        var positionNum = $("#positionNum").val();
        var positionImage = $("#positionImage").val();
        var state = $("input[name='state']:checked").val();
        var positionType= $("input[name='positionType']:checked").val();
        //var positionNum =$("#positionNum").val();
	    var channelNum= $("#channelNum").val();
      

        $.singleReq({
            data: {
                "reqUrl": "advPosition",
                "reqMethod": "saveadvPosition",
                "positionType": positionType,
                "state": state,
                "positionImage": positionImage,
                "positionName": positionName,
                "positionNum": positionNum,
                "channelNum": channelNum
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                	if(positionImage){
                	var fileType = positionImage.substring(positionImage.indexOf("."),positionImage.length);
            		$("#fileName").val(result.fileName +""+fileType);
            		$("#hiddenPositionNum").val(result.positionNum);
                	var times=0;
                	
                		$("#addForm").submit();
                		times=1000;	
                	}
                	
            	var t=setTimeout('UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times)
                    //UOMPComp.showSuccessDialog("添加成功！", "");
                    //top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("添加失败！", "");
                }
            }
        });
    }
}
