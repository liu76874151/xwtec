var blockDS;
var blockCount;
var submitBack=0;//提交图片返回标识
var blockNumArray=new Array();
var blockTempSelect;
var flag;
$(document).ready(function() {
	component.queryFloorBlockds();
	component.initFloorInfo();
	component.initFloorBlockInfo();
	var floorId = $("#floorId").val();
	flag = component.queryUseableFloorFa(floorId);
});

var component = {
		/**
		 * 初始化楼层信息
		 */
		initFloorInfo:function(){
			var floorId = $("#floorId").val();
			$.singleReq({
				data : {
					"reqUrl" : "floor",
					"reqMethod" : "getFloorByPKid",
					"floorId" : floorId
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						//楼层名称
						$("#floorName").val(result.floorName);
						//渠道
						$("#channelNum").val(result.channelNum);
						//模版初始化
						component.queryTemplateByChannel(result.tempNum);
						var imgSRC = result.floorImage;
						$("#tempImg").html("<img src='"+imgSRC+"'/>");
						$("#floorNum").val(result.floorNum);
						$("#floorBz").val(result.floorBz);
					}
				}
			});
			
		},
		/**
		 * 初始化楼层区块信息
		 */
		initFloorBlockInfo:function(){
			var floorId = $("#floorId").val();
			$.singleReq({
				data : {
					"reqUrl" : "floor",
					"reqMethod" : "queryBlockByFloorId",
					"floorId" : floorId
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						blockCount = result.length;
						for ( var i = 0; i < result.length; i++) {
							blockNumArray[i]=result[i].blockNum;
							var blockNumInfo ="<span>&nbsp;区块："+result[i].blockNum+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
							"<a href='javascript:void(0)' class='btn' onClick=component.addBlockInfo('"+result[i].blockNum+"')>添加</a>";
							var blockInfo = $("<table class='tb' style='position:relative;'>"+
									
									"<tr>"+
									"<th align='right'>区块标题：</th>"+
									"<td class='form_table_content2'><input type='text'  id='iContentTitle_"+result[i].blockNum+"' value='"+result[i].blockName+"'/></td>"+
									"</tr><tr>"+
									"<th align='right'>区块描述：</th>"+
									"<td class='form_table_content2'><input type='text'  id='iBlockDesc_"+result[i].blockNum+"' value='"+result[i].blockDesc+"'/></td>"+
									"</tr><tr>"+
									"<th align='right'>区块链接：</th>"+
									"<td class='form_table_content2'><input type='text'  id='iBlockUrl_"+result[i].blockNum+"' value='"+result[i].blockUrl+"'/></td>"
									+"</tr><tr>"+
									"<th align='right'>区块内容：</th>"+
									"<td ><table class='tb' style='position:relative;' id='blockInfoTab_"+result[i].blockNum+"'>"+
									component.addBlockNumInfo(result[i].blockNum,result[i].floorBlockContentList.length,result[i].floorBlockContentList)+
									"</table></td></tr>"+
									"<input type='hidden' id='index_"+result[i].blockNum+"' value='"+result[i].floorBlockContentList.length+"'/>"+
									"<input type='hidden' name='blockNum_"+result[i].blockNum+"' id='blockNum_"+result[i].blockNum+"' value='"+result[i].blockNum+"'/>"+
									"<input type='hidden' name='blockId_"+result[i].blockNum+"' id='contentId_"+result[i].blockNum+"' value='"+result[i].blockId+"'/>"+
									"<input type='hidden' name='collectState_"+result[i].blockNum+"' id='collectState_"+result[i].blockNum+"' value='"+result[i].collectState+"'/>"+
							"</table>");
							$("#blockInfos").append(blockNumInfo);
							$("#blockInfos").append(blockInfo);
							//$("#ds_"+i).append(blockDS);
							//$("#bef_ds_"+i).append(blockDS);
							//$("#ds_"+i).val(result[i].afterDs);
							//$("#bef_ds_"+i).val(result[i].beforeDs);
							$("input[name='contentTitle_"+result[i].blockNum+"_"+i+"'][value='"+result[i].floorBlockContentList.contentType+"']").attr("checked",'checked');
						}
					}
				}
			});
		},
		addBlockInfo:function(blockNum){
			var length=$("#index_"+blockNum).val();
			if(length==undefined){
				length=0;
			}
			var content=component.addBlockNumInfo(blockNum,parseInt(length)+1);
			$("#blockInfoTab_"+blockNum).append(content);
			//alert(content+"====content=====");
			$("#index_"+blockNum).val(parseInt(length)+1)
			
		},
		addBlockNumInfo:function(blockNum,length,result){
			var index=0;
			if(result==undefined&&length!=undefined){
				index=length-1;
			}
			var content="";
			var checked1,checked2,dispaly;
			for ( var i = index; i < length; i++) {
				dispaly="display:none";
				checked1="";
				checked2="";
				
				if(result!=undefined&&result[i].contentType!=undefined){
				if(result[i].contentType==1){
					checked1="checked";
				}else if((result[i].contentType==2)){
					checked2="checked";
				}
				}
				
				if(result!=undefined&&result[i].contentImg!=undefined){
					dispaly="display:block" ;	
				}
				content+= 
					"<tr>"+
					"<th align='right'>类别：</th>"+
					"<input type='hidden' id='contentId_"+blockNum+"_"+i+"' value='"+(result==undefined||result[i].contentId==undefined?'':result[i].contentId)+  "' />"+
					"<td class='form_table_content2'>"+"<input type='radio' name='contentType_"+blockNum+"_"+i+"' value='1' "+checked1+"/>" +
					"文字&nbsp;<input type='radio' name='contentType_"+blockNum+"_"+i+"' value='2' "+checked2+"/>链接</td>"+
				"</tr>"+
				"<tr>"+
					"<th align='right'>标题：</th>"+
					"<td class='form_table_content2'><input type='text' class='form_input' maxlength='50' name='contentTitle_"+blockNum+"_"+i+"'  id='contentTitle_"+blockNum+"_"+i+"' value='"+(result==undefined||result[i].contentTitle==undefined?'':result[i].contentTitle)+"'/></td>"+
				"</tr>"+
				"<tr>"+
					"<th align='right'>描述：</th>"+
					"<td class='form_table_content2'><input type='text' class='form_input' maxlength='50' name='blockDesc_"+blockNum+"_"+i+"'  id='blockDesc_"+blockNum+"_"+i+"' value='"+(result==undefined||result[i].contentDesc==undefined?'':result[i].contentDesc)+"'/></td>"+
				"</tr>"+
				"<tr>"+
					"<th align='right'>业务编码：</th>"+
					"<td class='form_table_content2'><input type='text' class='form_input' maxlength='50' name='busiNum_"+blockNum+"_"+i+"'  id='busiNum_"+blockNum+"_"+i+"' value='"+(result==undefined||result[i].busiNum==undefined?'':result[i].busiNum)+"'/></td>"+
				"</tr>"+
				"<tr>"+
					"<th align='right'>链接：</th>"+
					"<td class='form_table_content2'><input type='text' class='form_input' maxlength='50' name='blockUrl_"+blockNum+"_"+i+"'  id='blockUrl_"+blockNum+"_"+i+"' value='"+(result==undefined||result[i].contentUrl==undefined?'':result[i].contentUrl)+"'/></td>"+
				"</tr>"+
				"<tr>"+
					"<th align='right'>图片：</th>"+
					"<td class='form_table_content2'>" +
					"<img id='contentImg_"+blockNum+"_"+i+"' style='width: 240;height: 55;"+dispaly+";'  src='"+GLOBAL_INFO.CONTEXTPATH +"/upload/"+(result==undefined||result[i].contentImg==undefined?'':result[i].contentImg)+"'/>" +
					"<input type='file' maxlength='50' name='files'  id='contentImgId_"+blockNum+"_"+i+"' />"+
					"<input type='hidden' id='contentImgSrc_"+blockNum+"_"+i+"' value='"+(result==undefined||result[i].contentImg==undefined?'':result[i].contentImg)+"'/></td>"+
				"</tr>"+
				"<tr>"+
					"<th align='right'>排序号：</th>"+
					"<td class='form_table_content2'><input type='text' class='form_input' style='width:70px' maxlength='50' name='sortNum_"+blockNum+"_"+i+"'  id='sortNum_"+blockNum+"_"+i+"' value='"+(result==undefined||result[i].sortNum==undefined?'':result[i].sortNum)+"'/></td>"+
				"</tr>";
				
				
			}
			return content;
		},
		/**
		 * 根据渠道查询模版
		 */
		queryTemplateByChannel:function(tempNum){
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
							var selectedFlag = "";
							if(result[int].tempNum == tempNum){
								selectedFlag = "selected = 'selected'";
								blockTempSelect =result[int].tempNum;
							}
							var option = "<option value='"
									+ result[int].tempNum + "' "+selectedFlag+">"
									+ result[int].tempName + "</option>";
							$("#tempNum").append(option);
						}

					} else {
						UOMPComp.showFailedDialog("失败！", "");
					}
				}
			});
		},
		/**
		 * 获取数据源
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
		updateFloor:function(){
			if(submitBack==1){return;}else{submitBack=1;}
			var floorId = $("#floorId").val();
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
					"reqMethod":"updateFloorInfo",
					"floorId":floorId,
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
		            	$("#editForm").submit();
		            	times=3000;
		            	}
						var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("楼层保存成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
		                
						//UOMPComp.showSuccessDialog("楼层保存成功！","");
						//top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
					} else {
						UOMPComp.showFailedDialog("楼层保存失败！","");
					}
					// 开始上传文件
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
			var blockCount=blockNumArray.length;
			var contentImgId;
			var contentImgSrc;
			if(blockCount > 0){
				for(var j = 0; j < blockCount; j++){
					 blockNum=blockNumArray[j];
					 index=parseInt($("#index_"+blockNum).val());
					for ( var i = 0; i < index; i++) {
						var floorBlockContent = {};
					//区块ID
					floorBlockContent.blockId = blockNum;
					//区块内容ID
					floorBlockContent.contentId = $("#contentId_"+blockNum+"_"+i).val();
					//区块内容类别
					floorBlockContent.contentType = $('input:radio[name="contentType_'+blockNum+'_'+i+'"]:checked').val();
					//区块内容标题
					floorBlockContent.contentTitle = $("#contentTitle_"+blockNum+"_"+i).val();
					//区块内容说明
					floorBlockContent.contentDesc = $("#blockDesc_"+blockNum+"_"+i).val();
					//业务编码
					floorBlockContent.busiNum = $("#busiNum_"+blockNum+"_"+i).val(); 
					//图片
					contentImgId=$("#contentImgId_"+blockNum+"_"+i).val();
					contentImgSrc=$("#contentImgSrc_"+blockNum+"_"+i).val();
					contentImgId=contentImgId.substr(contentImgId.lastIndexOf("\\")+1);
					contentImgSrc=contentImgSrc.substr(contentImgSrc.lastIndexOf("\\")+1);
					floorBlockContent.contentImg= (contentImgId==""||contentImgId==undefined)?contentImgSrc:contentImgId; 
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
		getBlockDAinfo : function() {
			var floorBlockDaArray = new Array();
			var blockNum;
			var index;
			var blockCount=blockNumArray.length;
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
	/**
	 * 根据选择的模版编码获取模版图片路径
	 */
	getTempImg:function(tempNum){
		if(blockTempSelect != tempNum){
			UOMPComp.showConfirmDialog("【系统提示】\n\n您确定重新选择模版吗？点击“取消”则返回！", {
                "yes": function () {
                	$("#blockInfos").empty();
                	$.singleReq({
            			data : {
            				"reqUrl" : "floorTemplate",
            				"reqMethod" : "queryFloorTemplateByPk",
            				"tempNum" : tempNum,
            			},
            			success : function(ret) {
            				// 示意图路径
            				var imgSrc = ret.retObj.tempImg;
            				var imgObj = "<img id='floorTempImg' src='../../"+imgSrc+"'/><input type='hidden' id='tempImgSRC' value='"+imgSrc+"'";
            				$("#tempImg").html(imgObj);
            				// 区块信息
            				var blockNums = ret.retObj.blockNums;
            				var blockNumArray = blockNums.split(",");
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
        						+component.addBlockNumInfo(blockNum,1)+
        						"</table></td></tr>"+
        						"<input type='hidden' name='blockNum_"+blockNum+"' id='blockNum_"+blockNum+"' value='"+blockNum+"'/>"+
        						"<input type='hidden' id='index_"+blockNum+"' value='0'/>"+
        						"</table>";
        						$("#blockInfos").append(blockNumInfo);
        						$("#blockInfos").append(blockInfo);
//        						$("#ds_"+i).append(blockDS);
//        						$("#bef_ds_"+i).append(blockDS);
        					}
            			}
            		});
                },
                "no": function () {
                }
			});
		}
		else{
			$("#blockInfos").empty();
			component.initFloorBlockInfo();
		}
		// 上传初始化
		//component.uploadInit();
	},
	/**
	 * 记录方案
	 */
	savePlan:function(floorBlockFaArray,floorId,updateFlag){
		var floorBlockFaJSON = $.toJSON(floorBlockFaArray);
		
		var floorName = $("#floorName").val();
		var channelNum = $("#channelNum").find("option:selected").val();
		var tempNum = $("#tempNum").find("option:selected").val();
		var floorImg = $("#floorTempImgSrc").val();
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
				"floorBz":floorBz,
				"updateFlag":updateFlag
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
	 * 查询可用楼层
	 */
	queryUseableFloorFa:function(floorId){
		$.singleReq({
			data:{
				"reqUrl":"floorPlan",
				"reqMethod":"queryUseableFloorFa",
				"floorId":floorId
			},
			success : function(ret) {
				 if (ret.retCode == 0) {
		                var result = ret.retObj;
		                if(result != null&&result.floorId != null && result.floorId != undefined){
		                	//alert(result+"---"+result.floorId);
		                	flag = true;
		                }
		            }
		            else {
		                flag = false;
		            }
			}
		});
	}
}