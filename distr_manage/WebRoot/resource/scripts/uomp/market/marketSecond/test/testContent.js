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
				"reqUrl" : "marketSecond",
				"reqMethod" : "updateTestState",
				"marketSecondPkid" : marketPkId,
				"testOnlineState":2,
				"auditContent":auditContent
				//"verifyState":verifyState
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