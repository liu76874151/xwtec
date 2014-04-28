$(document).ready(function () {
        component.initForm();
       // component.initValidate();
    }
);

var component = {
		// 初始化校验规则
//		initValidate: function () {
//	    ValidateUtil.validate({
//	        targetForm: "editForm",
//	        rules: {
//	            brandName: {required: true, minlength: 1, maxlength: 200}
//	        },
//	        messages: {
//	        	brandName: {required: "请输入品牌名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
//	        }
//	    });
//	},

    initForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "advPosition",
                "reqMethod": "findOneAdvPositionBean",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                var result = ret.retObj;
                $("#positionName").val(result.positionName);
                $("#positionNum").val(result.positionNum);
                $("#channelNum option[value='"+result.channelNum+"']").attr("selected",true);
                $("#state"+result.state).attr("checked",true);
                $("#positionType"+result.positionType).attr("checked",true);
                if(result.positionImage){
                $("#deImg").attr('src',GLOBAL_INFO.CONTEXTPATH +"/upload/"+result.positionImage) ;
                $("#deImg").css('display','block');}
            }
        });
    },

    saveForm: function () {
    	if (!$('#editForm').valid()) {
            return;
        }
        var positionNum = $("#pkid").val();
        var positionName = $("#positionName").val();
        var channelNum = $("#channelNum").val();
        var state = $("input[name='state']:checked").val();
        var positionType = $("input[name='positionType']:checked").val();
        var positionImage=$("#positionImage").val();
        if(positionImage==""){
        	positionImage=$("#positionImage_old").val();
        }


        $.singleReq({
            data: {
                "reqUrl": "advPosition",
                "reqMethod": "updateByPrimaryKeySelective",
                "positionNum": positionNum,
                "positionName": positionName,
                "state": state,
                "positionType": positionType,
                "positionImage": positionImage,
                "positionType": positionType,
                "channelNum":channelNum
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                	if(positionImage){
                	var fileType = positionImage.substring(positionImage.indexOf("."),positionImage.length);
            		$("#fileName").val(result.fileName +""+fileType);
            		$("#hiddenPositionNum").val(result.positionNum);
                	var times=0;
                	
                		$("#editForm").submit();
                		times=1000;	
                	}
                   	
                var t=setTimeout('UOMPComp.showSuccessDialog("修改成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times)
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
                }
            }
        });
    }
};




