$().ready(function() {
});
var component = {
	audit : function() {
		var level = $("#level").val();
		if(level == "pro"){
			component.submitPro();
		}else{
			component.submitLocal();
		}
	},
	submitPro:function(){
		var marketPkId = $("#marketPkId").val();
		var auditContent = $("#auditContent").val();
		$.singleReq({
			data : {
				"reqUrl" : "marketSecond",
				"reqMethod" : "updateMarketSecondForVerify",
				"marketSecondPkid" : marketPkId,
				"proVerifyState" : 2,
				"auditContent":auditContent
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					UOMPComp.showSuccessDialog("提交成功！", "");
					top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
				} else {
					UOMPComp.showFailedDialog("提交失败！", "");
				}
			}
		});
	},
	submitLocal:function(){
		var marketPkId = $("#marketPkId").val();
		var auditContent = $("#auditContent").val();
		$.singleReq({
			data : {
				"reqUrl" : "marketSecond",
				"reqMethod" : "updateMarketSecondForVerify",
				"marketSecondPkid" : marketPkId,
				"localVerifyState" : 2,
				"auditContent":auditContent
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					UOMPComp.showSuccessDialog("审核成功！", "");
					top.UOMPDialog.subPageCallback
							&& top.UOMPDialog.subPageCallback();
				} else {
					UOMPComp.showFailedDialog("审核失败！", "");
				}
			}
		});
	}
}