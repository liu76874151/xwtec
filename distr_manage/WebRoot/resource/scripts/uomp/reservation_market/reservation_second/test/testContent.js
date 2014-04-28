$().ready(function() {
});
var component = {
	audit : function() {
		var level = $("#level").val();
		component.submitTest();
	},
	submitTest:function(){
		var marketPkId = $("#marketPkId").val();
		var auditContent = $("#auditContent").val();
		$.singleReq({
			data : {
				"reqUrl" : "reservationMarketSecond",
				"reqMethod" : "updateTestState",
				"marketSecondPkId" : marketPkId,
				"testOnlineState":2,
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
	}
}