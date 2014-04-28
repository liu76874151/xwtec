/**
 * 模版主键
 */
var tempNum;
/**
 * 组件编码
 */
var tempImgSRC;
var floorComp;
$().ready(function() {
	tempNum = $("#tempNum").val();
	component.queryFloorTemplateInfo();
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
						$("#floorComp").html();
						$("#floorComp").append("<option>-请选择-</option>");
						for ( var int = 0; int < result.length; int++) {
							var selectStr = "";
							if(result[int].pageNum == floorComp){
								selectStr = "selected='selected'";
							}
							var option = "<option value='"
									+ result[int].compNum + "' "+selectStr+">"
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
		 * 根据主键查询模版信息
		 */
		queryFloorTemplateInfo:function(){
			$.singleReq({
	            data: {
	                "reqUrl": "floorTemplate",
	                "reqMethod": "queryFloorTemplateByPk",
	                "tempNum": tempNum
	            },
	            success: function (ret) {
	            	var result = ret.retObj;
	            	$("#tempName").val(result.tempName);
	            	var channelNum = result.channelNum;
	            	 $("#channelNum").find("option[value='"+channelNum+"']").attr("selected",true);
	            	 $("#blockNums").val(result.blockNums);
	            	 floorComp = result.floorComp;
	            	//启停状态
	     			$("input[name=state][value="+result.state+"]").attr("checked",'checked');
	     			//示意图路径
	     			$("#tempImg").attr("src","../../.."+result.tempImg);
	     			tempImgSRC = "../../.."+result.tempImg;
	            }
			});
		},
		/**
		 * 修改模版
		 */
		updateFloorTemplateInfo:function(){
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
		            "reqMethod": "updateFloorTemplate",
		            "tempNum":tempNum,
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
		            	//alert(tempImg);
		            	if(tempImg != undefined){
		            		var fileType = tempImg.substring(tempImg.indexOf("."),tempImg.length);
		            		$("#fileName").val(result.fileName +""+fileType);
		            		$("#tempNum").val(result.floorTemplateNum);
		            		//alert($("#tempNum").val());
		            		$("#addForm").submit();
		            		times=3000;	
		            	}
		            	var t=setTimeout('UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
		            }
		            else {
		                UOMPComp.showFailedDialog("修改失败！", "");
		            }
		        }
			});
		},
		/**
		 * 替换img为上传控件
		 */
		changeFilePlu:function(){
			if($("#tempImgTD").html().indexOf("img") > 0){
				$("#tempImgTD").html();
				var fileHTML = '<input type="file" name="file" id="tempImg" class="qinggoudan_input021" maxlength="50">'
					+'<a href="javascript:void(0)" class="btn" onClick="component.changeFilePlu();">取消</a>';
				$("#tempImgTD").html(fileHTML);
			}
			else
			{
				$("#tempImgTD").html();
				var fileHTML = '<img id="tempImg" alt="示意图" src="'+tempImgSRC+'"><a href="javascript:void(0)" class="btn" onClick="component.changeFilePlu();">重新上传</a>';
				$("#tempImgTD").html(fileHTML);
			}
			
		}
}