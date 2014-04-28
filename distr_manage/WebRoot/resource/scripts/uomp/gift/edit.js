$(document).ready(function() {
	component.initValidate();
	component.initInfo();
});

var component = {
		// 初始化校验规则
	initValidate : function() {
		ValidateUtil.validate({
			targetForm : "addForm",
			rules : {
				productName : {
					required : true,
					minlength : 1,
					maxlength : 20
				},
				applyCode : {
					maxlength : 20
				},
				giftSendType : {
					required : true,
					minlength : 1,
					maxlength : 5
				}
			},
			messages : {
				productName : {
					required : "请输入礼品类型",
					minlength : "长度必须大于等于{0}",
					maxlength : "长度不能超过{0}"
				},
				applyCode : {
					maxlength : "长度不能超过{0}"
				},
				giftSendType : {
					required : "请输入礼品下发编码",
					minlength : "长度必须大于等于{0}",
					maxlength : "长度不能超过{0}"
				}
			}
		});
	},
	/**
	 * 初始化礼品类型
	 */
	initInfo:function(){
		var giftType = $("#giftType").val();
		$.singleReq({
			data : {
				"reqUrl" : "giftInfoHandler",
				"reqMethod" : "queryGiftInfoTypeById",
				"giftType" : giftType
			},
			success:function(ret){
				if(ret.retCode == 0){
					var result = ret.retObj;
					$("#productName").val(result.productName);
					$("#applyCode").val(result.applyCode);
					$("#giftSendType").val(result.giftSendType);
					$("#giftTypeDesc").val(result.giftTypeDesc);
				}
			}
		});
	},
	/**
	 * 保存礼品类型
	 */
	updateGiftType : function() {
		if (!$('#addForm').valid()) {
            return;
        }
		var productName = $("#productName").val();
		var applyCode = $("#applyCode").val();
		var giftSendType = $("#giftSendType").val();
		var giftTypeDesc = $("#giftTypeDesc").val();
		var giftType = $("#giftType").val();
		$.singleReq({
			data : {
				"reqUrl" : "giftInfoHandler",
				"reqMethod" : "updateGiftTypeInfo",
				"productName" : productName,
				"applyCode" : applyCode,
				"giftSendType" : giftSendType,
				"giftTypeDesc":giftTypeDesc,
				"state":"0",
				"giftType":giftType
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					UOMPComp.showSuccessDialog("礼品类型修改成功！", "");
					top.UOMPDialog.subPageCallback
							&& top.UOMPDialog.subPageCallback();
				} else {
					UOMPComp.showFailedDialog("礼品类型修改失败！", "");
				}
			}
		});
	}
}