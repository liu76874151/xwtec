var tableId=0;
var tbId;//二级boss的table id
var boosBizArr,boosBizSecondListArr;
$(document).ready(function () {
	 tbId=$("#tbId").val();
	 var obj = window.dialogArguments;
	 boosBizArr = $.parseJSON(obj.boosBizStr);
	 boosBizSecondListArr = $.parseJSON(obj.boosBizSecondListStr);
	 component.initTb(boosBizArr,boosBizSecondListArr);
	 
});


var component = {
	initTb:function(boosBizArr,boosBizSecondListArr){
	if(boosBizArr){
		$("#bizCode").val(boosBizArr[0].bizCode);
		$("#bizName").val(boosBizArr[0].bizName);
	}
	if(boosBizSecondListArr){
		for ( var j = 0; j < boosBizSecondListArr.length; j++) {
			component.addBizSecond();
			$("#tbBiz"+j+" #bizCodeSecond").val(boosBizSecondListArr[j].bizCode);
			$("#tbBiz"+j+" #bizNameSecond").val(boosBizSecondListArr[j].bizName);
		}
	}
	},	
	addBizSecond:function(){
	var table='<div id="div'+tableId+'"><table width="98%" class="tb" id="tbBiz'+tableId+'">'+'<tr>'+'<th align="right">业务编码：</th>'+'<td class="">'+
	'<input type="text" class="form_input" maxlength="10" name="bizCodeSecond" value="" id="bizCodeSecond" />'+
	'<span class="errorMsg"></span></td>'+'<th align="right">业务名称：</th>'+'<td class="">'+
	'<input type="text" class="form_input" maxlength="10" name="bizNameSecond" value="" id="bizNameSecond" />'+
	'<span class="errorMsg"></span></td>'+
	'<th><input type="button" value="删除" onclick="component.removeTable('+tableId+')"/></th>'+
	'</tr>'+'</table><br/>';
	 $("#bizSecondDiv").append(table);
	 tableId++;
	},
	removeTable:function(tableId){
		  $("#div"+tableId).remove();
	},
	getBizSecond:function(){
		var bizSecondArray = new Array();
		var parentBizCode=$("#bizCode").val();
			for(var j = 0; j < tableId; j++){
				if($("#tbBiz"+j)[0]){
				var bizSecondContent = {};
				bizSecondContent.bizCode=$("#tbBiz"+j+" #bizCodeSecond").val();
				bizSecondContent.bizName=$("#tbBiz"+j+" #bizNameSecond").val();
				bizSecondContent.parentBizCode=parentBizCode;
				bizSecondArray.push(bizSecondContent);
				}
			}
			return $.toJSON(bizSecondArray);
			},
			
		  saveForm: function () {
				var bizContent = {};
				var bizArray = new Array();
				bizContent.bizCode=$("#bizCode").val();
				bizContent.bizName=$("#bizName").val();
				bizArray.push(bizContent);
		  var tbId=$("#tbId").val();
		  	if (!$('#addForm').valid()) {
		          return;
		      }
		  
			if("undefined"!=bizCode){
				$("#tb"+tbId+" #boosBizStr", opener.document).val($.toJSON(bizArray));
				$("#tb"+tbId+" #bizBtn", opener.document).val("编辑业务");
				$("#tb"+tbId+" #boosBizSecondListStr", opener.document).val(component.getBizSecond());
				window.close();
			}else{
				//alert("请选择礼品类型");
			}
			
		  }
}
