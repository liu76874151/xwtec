$(document).ready(function () {
       component.initValidate();
        component.initForm();
    }
);

var component =
{
		   // 初始化校验规则
	    initValidate: function () {
	        ValidateUtil.validate({
	            targetForm: "editForm",
	            rules: {
	        		areaNum: {required: true, minlength: 1, maxlength: 10,isUppercase:"#areaNum"},
	        	    areaName: {required: true, minlength: 1, maxlength: 200,isChcharacter: "#areaName"},
	        	    areaJbNum: {required: true, minlength: 1, maxlength: 10,isNum: "#areaJbNum"},
	        	    areaBossCode: {required: false,  maxlength: 1000,isNum: "#areaBossCode"},
	        	    areaBz: {required: false,stringMaxLength:"2000"},
	            },
	            messages: {
	            	areaNum: {required: "请输入地市编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
	            	areaName: {required: "请输入名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
	            	areaJbNum: {required: "请输入地市级别编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
	            	areaBossCode: {required: "请输入", maxlength: "长度不能超过{0}"},
	            }
	        });
	    },

    initForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "area",
                "reqMethod": "findOneArea",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#areaNum").val(result.areaNum);
                    $("#areaName").val(result.areaName);
                    $("#areaJbNum").val(result.areaJbNum);
                    $("#areaBz").val(result.areaBz);
                    $("#areaBossCode").val(result.areaBossCode);
                    $("#areaBossOrgid").val(result.areaBossOrgid);
                    $("#areaJb_"+result.areaJb).attr("checked",true);
                }
                else {
                    alert(ret.retMsg);
                }
            }
        });

//        $.singleReq({
//            data: {
//                "reqUrl": "?",
//                "reqMethod": "??"
//            },
//            success: function (ret) {
//                if (ret) {
//                    if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
//                        if (ret.retObj) {
//                            var result = ret.retObj;
//                            //todo 加载界面上的一些字典项
//                        }
//                    } else {
//                        if (ret.resMsg) {
//                            UOMPComp.showFailedDialog(ret.resMsg, "");
//                        } else {
//                            UOMPComp.showFailedDialog("系统异常", "");
//                        }
//                    }
//                }
//            }
//        });
    },

    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }
        if($("#userCityCode").val()!=0){
       	 UOMPComp.showFailedDialog("省级用户才能修改！", "");	
       	 return;
       }
        var areaNum = $("#areaNum").val();
        var areaName = $("#areaName").val();
        var areaJbNum = $("#areaJbNum").val();
        var areaJb =$("input[name='areaJb']:checked").val();
        var areaMj =(areaJb=="3"?"1":"0");
        var areaBossCode = $("#areaBossCode").val();
        var areaBz = $("#areaBz").val();
        var areaBossOrgid = $("#areaBossOrgid").val();

        $.singleReq({
            data: {
                "reqUrl": "area",
                "reqMethod": "updateArea",
                "areaNum": areaNum,
                "areaName": areaName,
                "areaJbNum": areaJbNum,
                "areaJb": areaJb,
                "areaBossCode": areaBossCode,
                "areaBz": areaBz,
                "areaBossOrgid":areaBossOrgid,
                "areaMj":areaMj
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




