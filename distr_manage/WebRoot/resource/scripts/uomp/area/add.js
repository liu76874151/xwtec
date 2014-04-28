$(document).ready(function () {
    component.initValidate();
});


var component = {

    // 初始化校验规则
    initValidate: function () {
        ValidateUtil.validate({
            targetForm: "addForm",
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

    // 初始化表单
    initForm: function (areaJb,areaJbNum) {
    $.singleReq({
    	data:{
    	"reqUrl": "area",
        "reqMethod": "queryAreaList",
        "areaJb":areaJb,
        "areaJbNum":areaJbNum,
        "start": 0,
        "end": 999
    },
    	success:function(ret){
    	 if (ret.retCode == 0) {
         	var result = ret.retObj.records; 
         	if(areaJb==1){
             $.each(result, function(i, item) {
            	 $("#province").append("<option value="+item.areaJbNum+">"+item.areaName+"["+item.areaJbNum+"]</option>");
                 
             });}else if(areaJb==2){
            	 $("#zone").empty().prepend("<option value=''>--请选择--</option>");	
            	 $.each(result, function(i, item) {
                	 $("#zone").append("<option value="+item.areaJbNum+">"+item.areaName+"["+item.areaJbNum+"]</option>");
                     
                 });
             }
					}
    }
    });	
    	
    
       },

    saveForm: function () {
        if (!$('#addForm').valid()) {
            return;
        }
        if($("#userCityCode").val()!=0){
        	 UOMPComp.showFailedDialog("省级用户才能添加！", "");	
        	 return;
        }
        var areaNum = $("#areaNum").val();
        var areaName = $("#areaName").val();
        var areaJbNum = $("#areaJbNum").val();
        var areaJb =$("input[name='areaJb']:checked").val();
        var areaMj=(areaJb=="3"?"1":"0");
        var areaBossCode = $("#areaBossCode").val();
        var areaBz = $("#areaBz").val();

        $.singleReq({
            data: {
                "reqUrl": "area",
                "reqMethod": "saveArea",
                "areaNum": areaNum,
                "areaName": areaName,
                "areaJbNum": areaJbNum,
                "areaJb": areaJb,
                "areaBossCode": areaBossCode,
                "areaMj": areaMj,
                "areaBz": areaBz
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
    }
}
