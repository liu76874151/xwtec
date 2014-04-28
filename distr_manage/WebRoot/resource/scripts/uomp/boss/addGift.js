var tableId=0;
var tbId;//二级boss的table id
var boosGiftInfoArr,boosGiftInfoListArr;

$(document).ready(function () {
	 tbId=$("#tbId").val();
	 var obj = window.dialogArguments;
	   boosGiftInfoArr = $.parseJSON(obj.boosGiftInfoStr);
	 boosGiftInfoListArr = $.parseJSON(obj.boosGiftInfoListStr);
	 component.initTb(boosGiftInfoArr,boosGiftInfoListArr);
});


var component = {
		initTb:function(boosGiftInfoArr,boosGiftInfoListArr){
	if(boosGiftInfoArr){
		$("#bossGiftId").val(boosGiftInfoArr[0].bossGiftId);
		$("#giftName").val(boosGiftInfoArr[0].giftName);
	}
	if(boosGiftInfoListArr){
		for ( var j = 0; j < boosGiftInfoListArr.length; j++) {
			component.addGiftSecond();
			$("#tbGift"+j+" #bossGiftIdSecond").val(boosGiftInfoListArr[j].bossGiftId);
			$("#tbGift"+j+" #giftNameSecond").val(boosGiftInfoListArr[j].giftName);
		}
	}
	},	
		
	addGiftSecond:function(){
	var table='<div id="div'+tableId+'"><table width="98%" class="tb" id="tbGift'+tableId+'">'+'<tr>'+'<th align="right">礼品编码：</th>'+'<td class="">'+
	'<input type="text" class="form_input" maxlength="10" name="bossGiftIdSecond" value="" id="bossGiftIdSecond" />'+
	'<span class="errorMsg"></span></td>'+'<th align="right">礼品名称：</th>'+'<td class="">'+
	'<input type="text" class="form_input" maxlength="10" name="giftNameSecond" value="" id="giftNameSecond" />'+
	'<span class="errorMsg"></span></td>'+
	'<th><input type="button" value="删除" onclick="component.removeTable('+tableId+')"/></th>'+
	'</tr>'+'</table><br/>';
	 $("#giftSecondDiv").append(table);
	 tableId++;
	},
	removeTable:function(tableId){
		  $("#div"+tableId).remove();
	},
	getGiftSecond:function(){
		var giftSecondArray = new Array();
		var bossParentGiftId=$("#bossGiftId").val();
			for(var j = 0; j < tableId; j++){
				if($("#tbGift"+j)[0]){
				var giftSecondContent = {};
				//giftSecondContent.giftNameSecond=$("#tbGift"+j+" #giftNameSecond").val();
				giftSecondContent.giftComment=$("#tbGift"+j+" #giftNameSecond").val();
				giftSecondContent.giftName=$("#tbGift"+j+" #giftNameSecond").val();
				giftSecondContent.bossGiftId=$("#tbGift"+j+" #bossGiftIdSecond").val();
				giftSecondContent.bossParentGiftId=bossParentGiftId;
				giftSecondArray.push(giftSecondContent);
				}
			}
			return $.toJSON(giftSecondArray);
			},
			
		  saveForm: function () {
				var giftContent = {};
				var giftArray = new Array();
				giftContent.bossGiftId=$("#bossGiftId").val();
				giftContent.giftName=$("#giftName").val();
				giftArray.push(giftContent);
		  var tbId=$("#tbId").val();
		  	if (!$('#addForm').valid()) {
		          return;
		      }
		  
			if("undefined"!=bossGiftId){
				$("#tb"+tbId+" #boosGiftInfoStr", opener.document).val($.toJSON(giftArray));
				$("#tb"+tbId+" #boosGiftInfoListStr", opener.document).val(component.getGiftSecond());
				$("#tb"+tbId+" #giftBtn", opener.document).val("编辑礼品");
				window.close();
			}else{
				//alert("请选择礼品类型");
			}
			
		  }
}
