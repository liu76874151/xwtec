var blockDS;
var blockCount;
var myForm;
$(document).ready(function() {
//	component.queryFloorBlockds();
});

var component = {
		
		/**
		 * 保存渠道
		 */
		addChannel:function(){
			var channelEname = $("#channelEname").val();
			var channelName = $("#channelName").val();
			var channelNum = $("#channelNum").val();
			$.singleReq({
				data:{
					"reqUrl":"channel",
					"reqMethod":"addChannelInfo",
					"channelEname":channelEname,
					"channelName":channelName,
					"channelNum":channelNum
				},
				success : function(ret) {
					if (ret.retCode == 0) {
 		                UOMPComp.showSuccessDialog("渠道保存成功！", "");
 		                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
 		            }
 		            else {
 		                UOMPComp.showFailedDialog("渠道保存失败！", "");
 		            }
				}
			});
		}
}