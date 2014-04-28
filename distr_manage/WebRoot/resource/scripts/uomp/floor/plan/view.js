var count = 0;
var channelNum;
$(document).ready(function() {
	component.initPlan();
});


var component = {
		initPlan:function(){
			var planId = $("#planId").val();
			$.singleReq({
				data : {
					"reqUrl" : "floorPlan",
					"reqMethod" : "queryFloorPlanByPkId",
					"planId" : planId
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						//方案标号
						$("#planNum").val(result[0].planNum);
						//渠道
						$("#channelName").val(result[0].channelName);
						//渠道编码
						channelNum = result[0].channelNum;
						//方案状态
						var state = result[0].state;
						var stateStr = "";
						if(state == "1"){
							stateStr = "方案不可用";
						}else if(state == "0"){
							stateStr = "方案可用";
						}
						$("input[name=state][value='"+result[0].state+"']").attr("checked",'checked');
						for(var i = 0; i < result.length; i++){
							var floorInfo = $('<table class="tb" style="position:relative;margin-left:5px">'+
									'<tr>'+
										'<th align="right" style="width:100px">楼层名称：</th>'+
										'<td class="form_table_content" style="padding-left:5px;width:500px">'+result[i].floorName+'</td>'+
									'</tr>'+
									
									'<tr>'+
										'<th align="right" style="width:100px">楼层：</th>'+
										'<td class="form_table_content" style="padding-left:5px;width:500px">'+result[i].floorNum+'</td>'+
									'</tr>'+
									
									'<tr>'+
										'<th align="right" style="width:100px">备注：</th>'+
										'<td class="form_table_content" style="padding-left:5px;width:500px">'+result[i].floorBz+'</td>'+
									'</tr>'+
									
									'<tr>'+
										'<th align="right" style="width:100px">模版名称：</th>'+
										'<td class="form_table_content" style="padding-left:5px;width:500px">'+result[i].tempName+'</td>'+
									'</tr>'+
									
									'<tr>'+
										'<th align="right" style="width:100px">示意图：</th>'+
										'<td class="form_table_content" style="padding-left:5px;width:500px"><img src="../../../'+result[i].floorImage+'"/></td>'+
									'</tr>'+
									'</table><br>'
									);
							$("#floorInfos").append(floorInfo);
						}
						
						//楼层名称
						$("#floorName").val(result.floorName);
						
						//模版名称
						$("#tempName").val(result.tempName);
						var imgSRC = result.floorImage;
						if(imgSRC == undefined){
							$("#tempImg").html("无图片");
						}else{
							$("#tempImg").html("<img src='"+GLOBAL_INFO.CONTEXTPATH+imgSRC+"'/>");
						}
						$("#floorNum").val(result.floorNum);
						$("#floorBz").val(result.floorBz);
//						component.initFloorBlockFa(result.floorId);
					}
				}
			});
		},
		initFloorBlockFa:function(floorId){
			$.singleReq({
				data:{
					"reqUrl":"floorPlan",
					"reqMethod":"queryFloorBlockByFloorId",
					"floorId":floorId
				},
				success:function(ret){
					if(ret.retCode == 0){
						var result = ret.retObj;
						for ( var i = 0; i < result.length; i++) {
							count++;
							var collectState = result[i].collectState;
							var blockNumInfo = $("<span>&nbsp;区块："+result[i].blockNum+"</span>");
							var blockInfo = $("<table class='tb' style='position:relative;'>"+
								
								"<tr>"+
									"<th align='right' width='200px'>标题：</th>"+
									"<td class='form_table_content2'>"+result[i].blockName+"</td>"+
								"</tr>"+
								"<tr>"+
									"<th align='right'>描述：</th>"+
									"<td class='form_table_content2'>"+result[i].blockDesc+"</td>"+
								"</tr>"+
								"<tr>"+
									"<th align='right'>链接：</th>"+
									"<td class='form_table_content2'>"+result[i].blockUrl+"</td>"+
								"</tr>"+
								"<tr>"+
									"<th align='right'>登录前数据源：</th>"+
									"<td class='form_table_content2'>" +
										result[i].beforeDs+
									"</td>"+
								"</tr>"+
								"<tr>"+
									"<th align='right'>登录后数据源：</th>"+
									"<td align='left' class='form_table_content2'>"+
										result[i].afterDs+
									"</td>"+
								"</tr>"+
								"<tr>"+
								"<th align='right'>采集状态：</th>"+
								"<td align='left' class='form_table_content2'>"+
										"<input type='radio' name='collectState_"+i+"'  value='0'/>关闭"+
										"<input type='radio' name='collectState_"+i+"'  value='1'/>开启"+
								"</td>"+
							"</tr>"+
							"<input type='hidden' name='blockId_"+i+"' id='blockId_"+i+"' value='"+result[i].blockId+"'/>"+
							"</table>");
							$("#blockInfos").append(blockNumInfo);
							$("#blockInfos").append(blockInfo);
							$("input[name=collectState_"+i+"][value='"+result[i].collectState+"']").attr("checked",'checked');
						}
					}
				}
			});
		},
		/**
		 * 更改采集状态
		 */
		updateCollectState:function(){
			var floorBlockFaParam = component.getParams();
			$.singleReq({
				data:{
					"reqUrl":"floorPlan",
					"reqMethod":"updateFloorBlockCollectState",
					"floorBlockFaParam":floorBlockFaParam
				},
				success: function (ret) {
		            if (ret.retCode == 0) {
		                UOMPComp.showSuccessDialog("更新成功！", "");
		                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
		            }
		            else {
		                UOMPComp.showFailedDialog("更新失败！", "");
		            }
		        }
			});
		},
		getParams:function(){
			var floorBlockFaArray = new Array();
			for(var i = 0; i < count; i++){
				var floorBlockFa = {};
				floorBlockFa.blockId = $("#blockId_"+i).val();
				floorBlockFa.collectState = $('input[name="collectState_'+i+'"]:checked').val();
				floorBlockFaArray.push(floorBlockFa);
			}
			return $.toJSON(floorBlockFaArray);
		},
		/**
		 * 改变可用状态
		 */
		changeState:function(){
			var planId = $("#planId").val();
			var state = $("input[name=state]:checked").val();
			$("#channelName").val
			UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变可用状态吗？点击“取消”则返回！", {
                "yes": function () {
					$.singleReq({
						data : {
							"reqUrl" : "floorPlan",
							"reqMethod" : "changeState",
							"planId" : planId,
							"state":state,
							"channelNum":channelNum
						},
						success : function(ret) {
						    if (ret) {
						    	if (ret.retCode == 0) {
									UOMPComp.showSuccessDialog("状态更新成功！","");
									top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
								} else {
									UOMPComp.showFailedDialog("状态更新失败！","");
								}
                            }
						}
					});
                },
                "no": function () {
                }
			});
		}
}