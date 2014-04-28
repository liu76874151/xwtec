var blockDS;
var blockCount;
var blockNumArray;
var myForm;
var submitBack=0;//提交图片返回标识
$(document).ready(function() {
	component.queryFloorBlockds();
});

var component = {
		/**
		 * 根据渠道查询模版
		 */
		queryTemplateByChannel:function(){
			//渠道选择
			var channelNum = $("#channelNum").find("option:selected").val();
			$("#tempNum").html("");
			$.singleReq({
				data : {
					"reqUrl" : "floor",
					"reqMethod" : "queryFloorTemplateAll",
					"channelNum" : channelNum,
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						
						$("#tempNum").append("<option>-请选择-</option>");
						for ( var int = 0; int < result.length; int++) {
							var option = "<option value='"
									+ result[int].tempNum + "' >"
									+ result[int].tempName + "</option>";
							$("#tempNum").append(option);
						}

					} else {
						UOMPComp.showFailedDialog("失败！", "");
					}
				}
			});
		},
		
		addBlockNumInfo:function(blockNum,i){
			 return  "<tr>"+
			"<th align='right'>类别：</th>"+
			"<td class='form_table_content2'><input type='radio' name='contentType_"+blockNum+"_"+i+"' value='1' checked='checked'/>文字&nbsp;<input type='radio' name='contentType_"+blockNum+"_"+i+"' value='2'/>链接</td>"+
		"</tr>"+
		"<tr>"+
			"<th align='right'>标题：</th>"+
			"<td class='form_table_content2'><input type='text' class='form_input' maxlength='50' name='contentTitle_"+blockNum+"_"+i+"'  id='contentTitle_"+blockNum+"_"+i+"' /></td>"+
		"</tr>"+
		"<tr>"+
			"<th align='right'>描述：</th>"+
			"<td class='form_table_content2'><input type='text' class='form_input' maxlength='50' name='blockDesc_"+blockNum+"_"+i+"'  id='blockDesc_"+blockNum+"_"+i+"' /></td>"+
		"</tr>"+
		"<tr>"+
			"<th align='right'>业务编码：</th>"+
			"<td class='form_table_content2'><input type='text' class='form_input' maxlength='50' name='busiNum_"+blockNum+"_"+i+"'  id='busiNum_"+blockNum+"_"+i+"' /></td>"+
		"</tr>"+
		"<tr>"+
			"<th align='right'>链接：</th>"+
			"<td class='form_table_content2'><input type='text' class='form_input' maxlength='50' name='blockUrl_"+blockNum+"_"+i+"'  id='blockUrl_"+blockNum+"_"+i+"' /></td>"+
		"</tr>"+
		"<tr>"+
			"<th align='right'>图片：</th>"+
			"<td class='form_table_content2'><input type='file' maxlength='50' name='files'  id='contentImg_"+blockNum+"_"+i+"' /></td>"+
		"</tr>"+
		"<tr>"+
			"<th align='right'>排序号：</th>"+
			"<td class='form_table_content2'><input type='text' class='form_input' style='width:70px' maxlength='50' name='sortNum_"+blockNum+"_"+i+"'  id='sortNum_"+blockNum+"_"+i+"' value=''/></td>"+
		"</tr>";
			
			/**
			"<tr>"+
				"<th align='right'>登录前数据源：</th>"+
				"<td class='form_table_content2'>" +
					"<select name='beforeDs_"+i+"' id='bef_ds_"+i+"'>"+
					"</select>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<th align='right'>登录后数据源：</th>"+
				"<th align='left' class='form_table_content2'>"+
					"<select name='afterDs_"+i+"' id='ds_"+i+"'>"+
					"</select>"+
				"</th>"+
			"</tr>"+
			**/
		},
		
		addBlockInfo:function(blockNum){
			var index=0;
				if($("#index_"+blockNum).val()!=undefined){
					index=parseInt($("#index_"+blockNum).val());}
			var blockNumInfo=component.addBlockNumInfo(blockNum,index+1);
			$("#blockInfoTab_"+blockNum).append(blockNumInfo);
			$("#index_"+blockNum).val(index+1);
		},
		/**
		 * 根据选择的模版编码获取模版图片路径
		 */
		getTempImg:function(tempNum){
			$.singleReq({
				data : {
					"reqUrl" : "floorTemplate",
					"reqMethod" : "queryFloorTemplateByPk",
					"tempNum" : tempNum,
				},
				success : function(ret) {
					//示意图路径
					var imgSrc = ret.retObj.tempImg;
					var imgObj = "<img id='floorTempImg' src='../../"+imgSrc+"'/><input type='hidden' id='tempImgSRC' value='"+imgSrc+"'/>";
					$("#tempImg").html(imgObj);
					//区块信息
					var blockNums = ret.retObj.blockNums;
					 blockNumArray = blockNums.split(",");
					blockCount = blockNumArray.length;
					for(var i = 0; i < blockNumArray.length; i++){
						var blockNum = blockNumArray[i];
						var blockNumInfo = "<span>&nbsp;区块："+blockNum+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
								"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<a href='javascript:void(0)' class='btn' onClick=component.addBlockInfo('"+blockNum+"')>添加</a>";
						var blockInfo = "<table class='tb' style='position:relative;' >"
						+
						"<tr>"+
						"<th align='right'>区块标题：</th>"+
						"<td class='form_table_content2'><input type='text'  id='iContentTitle_"+blockNum+"'/></td>"+
						"</tr><tr>"+
						"<th align='right'>区块描述：</th>"+
						"<td class='form_table_content2'><input type='text'  id='iBlockDesc_"+blockNum+"'/></td>"+
						"</tr><tr>"+
						"<th align='right'>区块链接：</th>"+
						"<td class='form_table_content2'><input type='text'  id='iBlockUrl_"+blockNum+"'/></td>"
						+"</tr><tr>"+
						"<th align='right'>区块内容：</th>"+
						"<td ><table class='tb' style='position:relative;' id='blockInfoTab_"+blockNum+"'>"
						+component.addBlockNumInfo(blockNum,0)+
						"</table></td></tr>"+
						"<input type='hidden' name='blockNum_"+blockNum+"' id='blockNum_"+blockNum+"' value='"+blockNum+"'/>"+
						"<input type='hidden' id='index_"+blockNum+"' value='0'/>"+
						"</table>";
						$("#blockInfos").append(blockNumInfo);
						$("#blockInfos").append(blockInfo);
//						$("#ds_"+i).append(blockDS);
//						$("#bef_ds_"+i).append(blockDS);
					}
				}
			});
			//上传初始化
			component.uploadInit();
		},
		/**
		 * 获取区块数据源
		 */
		queryFloorBlockds:function(){
			blockDS = "";
			$.singleReq({
				data : {
					"reqUrl" : "floor",
					"reqMethod" : "queryBlockDsList"
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						blockDS = "<option>-请选择-</option>";
						for ( var int = 0; int < result.length; int++) {
							var option = "<option value='"
									+ result[int].dsService + "' >"
									+ result[int].dsName + "</option>";
							blockDS += option;
						}
					}
					else{
						UOMPComp.showFailedDialog("失败！", "");
					}
				}
			});
		},
		/**
		 * 保存楼层
		 */
		addFloor:function(){
			if(submitBack==1){
				return;
			}else{
				submitBack=1;
				}
			var floorName = $("#floorName").val();
			var channelNum = $("#channelNum").find("option:selected").val();
			var tempNum = $("#tempNum").find("option:selected").val();
			var floorImg = $("#tempImgSRC").val();
			var floorNum = $("#floorNum").find("option:selected").val();
			var floorSeq = $("#floorNum").find("option:selected").text();
			var floorBz = $("#floorBz").val();
			var floorBlockDaParam = component.getBlockDAinfo();
			var floorBlockContentParam = component.getBlockContentInfo();
			$.singleReq({
				data:{
					"reqUrl":"floor",
					"reqMethod":"addFloorInfo",
					"floorName":floorName,
					"channelNum":channelNum,
					"tempNum":tempNum,
					"floorImage":floorImg,
					"floorNum":floorNum,
					"floorBz":floorBz,
					"floorSeq":floorSeq,
					"floorBlockDaParam":floorBlockDaParam,
					"floorBlockContentParam":floorBlockContentParam
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
		            	var times=0;
		            	if(result.fileNames!=""){
		            	$("#fileNames").val(result.fileNames);
		            	$("#contentIds").val(result.contentIds);
		            	$("#addForm").submit();
		            	times=3000;
		            	}
		            	var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("楼层保存成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
		                
 		               // UOMPComp.showSuccessDialog("楼层保存成功！", "");
 		              //  top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
 		            }
 		            else {
 		                UOMPComp.showFailedDialog("楼层保存失败失败！", "");
 		            }
					//开始上传文件
				}
			});
		},
		/**
		 * 记录方案
		 */
		savePlan:function(floorBlockFaArray,floorId){
			var floorBlockFaJSON = $.toJSON(floorBlockFaArray);
			
			var floorName = $("#floorName").val();
			var channelNum = $("#channelNum").find("option:selected").val();
			var tempNum = $("#tempNum").find("option:selected").val();
			var floorImg = $("#floorTempImg").attr("src");
			var floorNum = $("#floorNum").find("option:selected").val();
			var floorSeq = $("#floorNum").find("option:selected").text();
			var floorBz = $("#floorBz").val();
			
			$.singleReq({
				data:{
					"reqUrl":"floorPlan",
					"reqMethod":"addFloorPlan",
					"floorBlockFaParam":floorBlockFaJSON,
					"floorId":floorId,
					"channelNum":channelNum,
					"floorNum":floorNum,
					"floorName":floorName,
					"floorSeq":floorSeq,
					"floorImage":floorImg,
					"tempNum":tempNum,
					"floorBz":floorBz
				},
				success : function(ret) {
					 if (ret.retCode == 0) {
			                UOMPComp.showSuccessDialog("保存成功！", "");
			                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
			            }
			            else {
			                UOMPComp.showFailedDialog("保存失败！", "");
			            }
				}
			});
		},
		/**
		 * 获取区块内容信息
		 */
		getBlockContentInfo:function(){
			var floorBlockContentArray = new Array();
			var blockNum;
			var index;
			var contentImg;
			if(blockCount > 0){
				for(var j = 0; j < blockCount; j++){
					 blockNum=blockNumArray[j];
					 index=parseInt($("#index_"+blockNum).val());
					for ( var i = 0; i < index+1; i++) {
						var floorBlockContent = {};
					//区块ID
					floorBlockContent.blockId = blockNum;
					//区块类别
					floorBlockContent.contentType = $('input:radio[name="contentType_'+blockNum+'_'+i+'"]:checked').val();
					//区块标题
					floorBlockContent.contentTitle = $("#contentTitle_"+blockNum+"_"+i).val();
					//区块说明
					floorBlockContent.contentDesc = $("#blockDesc_"+blockNum+"_"+i).val();
					//业务编码
					floorBlockContent.busiNum = $("#busiNum_"+blockNum+"_"+i).val(); 
					//图片
					 contentImg=$("#contentImg_"+blockNum+"_"+i).val();
					floorBlockContent.contentImg= contentImg; 
					//链接地址
					floorBlockContent.contentUrl = $("#blockUrl_"+blockNum+"_"+i).val();
					//内容排序号
					floorBlockContent.sortNum = $("#sortNum_"+blockNum+"_"+i).val();
					//状态：默认可用
					floorBlockContent.state = "0";
					floorBlockContentArray.push(floorBlockContent);
					}
				}
			}
			return  $.toJSON(floorBlockContentArray);
		},
		/**
		 * 获取区块档案信息
		 */
		getBlockDAinfo:function(){
			var floorBlockDaArray = new Array();
			var blockNum;
			var index;
			if(blockCount > 0){
				for(var j = 0; j < blockCount; j++){
					blockNum=blockNumArray[j];
				
					var floorBlockDa = {};
					//区块编码
					floorBlockDa.blockNum = blockNum;
					//区块名称
					floorBlockDa.blockName = $("#iContentTitle_"+blockNum).val();
					//区块链接
					floorBlockDa.blockUrl = $("#iBlockUrl_"+blockNum).val();
					//区块描述
					floorBlockDa.blockDesc = $("#iBlockDesc_"+blockNum).val();
					//登录前数据源
//					floorBlockDa.beforeDs = $("#bef_ds_"+i).find("option:selected").val();
					//登录后数据源
//					floorBlockDa.afterDs = $("#ds_"+i).find("option:selected").val();
					//采集状态默认关闭
					floorBlockDa.collectState = "0";
					
					floorBlockDaArray.push(floorBlockDa);
				
					}
			}
			return  $.toJSON(floorBlockDaArray);
		},
		uploadInit:function(){
			var formData = [ 
			   {
					type : "file",
					name : "files"
			   },
			   {type: "button", name: "upload", value: "Upload"}
			];
			myForm = new dhtmlXForm("dhxForm", formData);
		}
}