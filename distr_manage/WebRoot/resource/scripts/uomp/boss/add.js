var tableId=0;
//var boosBizStr,boosBizSecondListStr;

$(document).ready(function () {
	var t = new dhtmlXCalendarObject(["startTimeFirst","endTimeFirst"]);
	component.init();
});


var component = {

    
		init :function(){
	 $.singleReq({
       data: {
           "reqUrl": "zoneInfo",
           "reqMethod": "queryBossAreaList"
       },
       success: function (ret) {
           if (ret.retCode == 0) {
               var result = ret.retObj; 
             $.each(result, function(i, item) {
              	 $("#cityId").append("<option value="+item.bossCode+">"+item.zoneName+"</option>");
          	  
             });
             
           }
          
       }
      
   });
	
	},
    // 初始化表单
    initForm: function (areaJb,areaJbNum) {
    $.singleReq({
    	data:{
    	"reqUrl": "area",
        "reqMethod": "queryAreaList",
        "areaJb":areaJb,
        "areaJbNum":areaJbNum,
        "start": 0,
        "end": 999
    },
    	success:function(ret){
    	 if (ret.retCode == 0) {
         	var result = ret.retObj.records; 
         	if(areaJb==1){
             $.each(result, function(i, item) {
            	 $("#province").append("<option value="+item.areaJbNum+">"+item.areaName+"["+item.areaJbNum+"]</option>");
                 
             });}else if(areaJb==2){
            	 $("#zone").empty().prepend("<option value=''>--请选择--</option>");	
            	 $.each(result, function(i, item) {
                	 $("#zone").append("<option value="+item.areaJbNum+">"+item.areaName+"["+item.areaJbNum+"]</option>");
                     
                 });
             }
					}
    }
    });	
    	
    
       },

    addMarketSecond:function(){
    	   var table='<div id ="div'+tableId+'"><table width="98%" class="tb" id="tb'+tableId+'">'+
    	   '<tr>'+
    	   '<th align="right">boss编码：</th>'+
    	   '<td class="">'+
    	   '<input type="text" class="form_input" maxlength="10" name="marketSecondCode" value="" id="marketSecondCode" />'+
    	   '</td>'+
    	   '<th align="right">boss名称：</th>'+
    	   '<td class="">'+
    	   '<input type="text" class="form_input" maxlength="10" name="marketSecondName" value="" id="marketSecondName" />'+
    	   
    	   '</td>'+
    	   '<th width="10%" rowspan="2" ><input type="button" value="添加礼品" id="giftBtn" onclick="component.addGift('+tableId+')"/>'+
    	   '<input name="boosBizStr" id="boosBizStr"s type="hidden">'+
    	   '<input name="boosBizSecondListStr" id="boosBizSecondListStr" type="hidden">'+
    	   '<input name="boosGiftInfoStr" id="boosGiftInfoStr" type="hidden">'+
    	   '<input name="boosGiftInfoListStr" id="boosGiftInfoListStr" type="hidden">'+
    	   '<br/><br/><input type="button" value="添加业务" onclick="component.addBiz('+tableId+')" id="bizBtn"/></th>'+
    	   '<td width="5%" rowspan="2"><input type="button" value="删除" onclick="component.removeMarketSecond('+tableId+')" /></td>'+
    	   '</tr>'+
    	   '<tr>'+
    	   '<th align="right">充值金额：</th>'+
    	   '<td class="">'+
    	   '<input type="text" class="form_input" maxlength="10" name="moneyVal" value="" id="moneyVal" />'+
    	   '</td>'+
    	   '<th align="right">品牌：</th>'+
    	   '<td class="">'+
    	   '<input type="checkbox"   name="brandId" value="1" id="QQT" checked="checked"/>&nbsp; <label for="QQT">全球通</label>'+ 
    	   '<input type="checkbox"   name="brandId" value="2" id="DGDD" checked="checked"/>&nbsp;<label for="DGDD">动感地带</label>'+ 
    	   '<input type="checkbox"   name="brandId" value="3" id="SZX" checked="checked"/>&nbsp; <label for="SZX">神州行 </label>'+ 
    	   '</td>'+
    	   '</tr>'+
    	   '</table><br/></div>';
    	 
    	   $("#marketSecondDiv").append(table);
    	   tableId++;
       },
       removeMarketSecond:function(id){
    	   $("#div"+id).remove();
       },
       addBiz:function(id){
    	   
    	   var tid="#tb"+id;
    	   var marketSecondCode=$(tid+" #marketSecondCode").val();
    	  var  boosBizStr=$(tid+" #boosBizStr").val();
    	  var boosBizSecondListStr=$(tid+" #boosBizSecondListStr").val();
    	   var cityId=$(" #cityId").val();
    	   if(!marketSecondCode){alert("marketSecondCode is null");return;};
    	   var obj = new Object();
    	   obj.boosBizStr=boosBizStr;
    	   obj.boosBizSecondListStr=boosBizSecondListStr;
    	   window.showModalDialog('addBiz.jsp?marketSecondCode='+marketSecondCode+'&id='+id+'&cityId='+cityId,obj,
    			   					"dialogTop=60px;dialogLeft=100px;dialogWidth=700px;dialogHeight=500px");

       },
       addGift:function(id){
    	   var tid="#tb"+id;
    	   var  boosGiftInfoStr=$(tid+" #boosGiftInfoStr").val();
    	   var boosGiftInfoListStr=$(tid+" #boosGiftInfoListStr").val();
    	   var marketSecondCode=$(tid+" #marketSecondCode").val();
    	   if(!marketSecondCode){alert("marketSecondCode is null");return;};
    	   var obj = new Object();
    	   obj.boosGiftInfoStr=boosGiftInfoStr;
    	   obj.boosGiftInfoListStr=boosGiftInfoListStr;
    	   window.showModalDialog('addGift.jsp?marketSecondCode='+marketSecondCode+'&id='+id+'&cityId='+cityId,obj,
				"dialogTop=60px;dialogLeft=100px;dialogWidth=700px;dialogHeight=500px");
//
//    	   window.open(
//					'addGift.jsp?marketSecondCode='+marketSecondCode+'&id='+id+'&cityId='+cityId,
//					'addGift',
//					'height=500, width=700, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
      },
      getMarketSecond:function(){
    	  var marketSecondArray = new Array();
    	  var marketFirstCode=$("#marketFirstCode").val();
    	  var startTime=$("#startTimeFirst").val();
    	  var endTime=$("#endTimeFirst").val();
    	  var cityId=$("#cityId").val();
    	  for ( var j = 0; j < tableId; j++) {
    		  if($("#tb"+j)[0]){
  				var marketSecondContent = {};
  				var brandId="";
  				marketSecondContent.marketFirstCode=marketFirstCode;
  				marketSecondContent.startTime=startTime;
  				marketSecondContent.endTime=endTime;
  				marketSecondContent.cityId=cityId;
  				marketSecondContent.marketSecondCode=$("#tb"+j+" #marketSecondCode").val();
  				marketSecondContent.marketSecondName=$("#tb"+j+" #marketSecondName").val();
  				marketSecondContent.boosBizStr=$("#tb"+j+" #boosBizStr").val();
  				marketSecondContent.boosBizSecondListStr=$("#tb"+j+" #boosBizSecondListStr").val();
  				marketSecondContent.boosGiftInfoStr=$("#tb"+j+" #boosGiftInfoStr").val();
  				marketSecondContent.boosGiftInfoListStr=$("#tb"+j+" #boosGiftInfoListStr").val();
  				marketSecondContent.moneyVal=$("#tb"+j+" #moneyVal").val();
  				 $("#tb"+j+" input[name='brandId']:checked").each(function () {
  					brandId=brandId+this.value+",";
  			    });
  				marketSecondContent.brandId=brandId;
  				}
    		
    		  marketSecondArray.push(marketSecondContent);
		}
    	  return $.toJSON(marketSecondArray);
      },
    saveForm: function () {
        if (!$('#addForm').valid()) {
            return;
        }
        var marketSecond=component.getMarketSecond();
       
        var marketFirstCode=$("#marketFirstCode").val();
        var marketFirstName=$("#marketFirstName").val();
  	  	var startTime=$("#startTimeFirst").val();
  	  	var endTime=$("#endTimeFirst").val();
  	  	var cityId=$("#cityId").val();
  	  var type = $("input[name='type']:checked").val();
        $.singleReq({
            data: {
                "reqUrl": "bossMarket",
                "reqMethod": "addBossCodeList",
                "marketSecond": marketSecond,
                "marketFirstName": marketFirstName,
                "marketFirstCode": marketFirstCode,
                "startTime": startTime,
                "endTime": endTime,
                "cityId": cityId,
                "type": type
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
        });
    }
}
