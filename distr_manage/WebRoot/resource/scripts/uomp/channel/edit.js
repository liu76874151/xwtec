var channelNum;
$(document).ready(function() {
	component.initChannelInfo();
});

var component = {
		/**
		 * 初始渠道信息
		 */
		initChannelInfo:function(){
			var channelNum = $("#channelNum").val();
			$.singleReq({
				data : {
					"reqUrl" : "channel",
					"reqMethod" : "queryChannelByNum",
					"channelNum" : channelNum
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						$("#channelName").val(result.channelName);
						$("#channelNum").val(result.channelNum);
						$("#channelEname").val(result.channelEname);
						
					}
				}
			});
			
		},
		updateChannel:function(){
			var channelName = $("#channelName").val();
			var channelNum = $("#channelNum").val();
			var channelEname = $("#channelEname").val();
			
			$.singleReq({
		        data: {
		            "reqUrl": "channel",
		            "reqMethod": "updateChannelInfo",
		            "channelName":channelName,
		            "channelNum":channelNum,
		            "channelEname":channelEname
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
		    })
		}
}