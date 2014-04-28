$(document).ready(function() {
	component.queryFloorPageInfo();
});

var component = {
		/**
		 * 查询楼层组件
		 */
		queryFloorPageInfo:function(){
			$.singleReq({
				data : {
					"reqUrl" : "floorTemplate",
					"reqMethod" : "queryFloorCompInfoList",
					"compType" : "3",//查询楼层组件
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						$("#floorComp").html("");
						$("#floorComp").append("<option>-请选择-</option>");
						for ( var int = 0; int < result.length; int++) {
							var option = "<option value='"
									+ result[int].compNum + "' >"
									+ result[int].compName + "</option>";
							$("#floorComp").append(option);
						}

					} else {
						UOMPComp.showFailedDialog("失败！", "");
					}
				}
			});
		},
		/**
		 * 保存模版
		 */
		addFloorTemplate:function(){
			$("#submitBut").removeAttr("onClick");
			//模版名称
			var tempName = $("#tempName").val();
			//渠道选择
			var channelNum = $("#channelNum").find("option:selected").val();
			//模版区块信息
			var blockNums = $("#blockNums").val();
			//组建选择
			var floorComp = $("#floorComp").find("option:selected").val();
			//启停状态
			var state = $('input[name="state"]:checked').val();
			//模版示意图
			var tempImg = $("#tempImg").val();
			$.singleReq({
		        data: {
		            "reqUrl": "floorTemplate",
		            "reqMethod": "addFloorTemplate",
		            "tempName":tempName,
		            "channelNum":channelNum,
		            "blockNums":blockNums,
		            "floorComp":floorComp,
		            "state":state
		        },
		        success: function (ret) {
		            if (ret.retCode == 0) {
		            	var result = ret.retObj;
		            	var times=0;
		            	if(tempImg){
		            		var fileType = tempImg.substring(tempImg.indexOf("."),tempImg.length);
		            		$("#fileName").val(result.fileName +""+fileType);
		            		$("#tempNum").val(result.floorTemplateNum);
		            		$("#addForm").submit();
		            		times=3000;	
		            	}
		            	var t=setTimeout('UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
		            }
		            else {
		                UOMPComp.showFailedDialog("添加失败！", "");
		            }
		        }
			});
		}
}