$(document).ready(function() {
	component.initValidate();
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
					maxlength : 4
				},
				giftTypeDesc:{
					maxlength:1000
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
				},
				giftTypeDesc:{
					maxlength:"长度不能超过{0}"
				}
			}
		});
	},
	/**
	 * 保存渠道
	 */
	addGiftType : function() {
		if (!$('#addForm').valid()) {
            return;
        }
		var productName = $("#productName").val();
		var applyCode = $("#applyCode").val();
		var giftSendType = $("#giftSendType").val();
		var giftTypeDesc = $("#giftTypeDesc").val();
		$.singleReq({
			data : {
				"reqUrl" : "giftInfoHandler",
				"reqMethod" : "addGiftTypeInfo",
				"productName" : productName,
				"applyCode" : applyCode,
				"giftSendType" : giftSendType,
				"giftTypeDesc":giftTypeDesc,
				"state":"0"
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					UOMPComp.showSuccessDialog("礼品类型保存成功！", "");
					top.UOMPDialog.subPageCallback
							&& top.UOMPDialog.subPageCallback();
				} else {
					UOMPComp.showFailedDialog("礼品类型保存失败！", "");
				}
			}
		});
	}
}