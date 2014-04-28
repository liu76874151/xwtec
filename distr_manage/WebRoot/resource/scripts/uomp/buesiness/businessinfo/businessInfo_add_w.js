$().ready(function() {
//	  comp.queryRelaAreaList(0,500,'01');
	  comp.queryRelaBrandList(0,500,'01');
    	  });
  	function showTime(){
  	  	var val = $("input[name=dateTime_01]:checked").val();
  	  	if(val=="0"){
			$("#date_time_01").css({display:"none"});
			$("#startTime_01").val("");
			$("#endTime_01").val("");
  	  	 }else{
  	  		$("#date_time_01").css({display:"block"});
  	  	}
  	}
  	var comp = {
  	queryRelaAreaList : function(start, end,channelNum) {//地市查询
  			if (start == undefined) {
  				start = 0;
  			}
  			if (end == undefined) {
  				end = 10;
  			}
  				
  			$.singleSync( {
  				data : {
  					"reqUrl" : "area",
  					"reqMethod" : "queryCityList",
  					"start" : start,
  					"end" : end
  				},
  				success : function(ret) {
  					if (ret.retCode == 0) {
  						var result = ret.retObj.records;
  						$("#releCityList_"+channelNum).empty();
  						for ( var int = 0; int < result.length; int++) {
  							var ck = "<input name='relaCity"+channelNum+"' type='checkbox' channelNum='"+channelNum+"' value='"+ result[int].areaNum+"' />" + result[int].areaName+"&nbsp;&nbsp;";
  							$("#releCityList_"+channelNum).append(ck);
  							if(int==0){
  								$("#releCityList_"+channelNum).append("<br>");
  							}
  						}

  					} else {
  						UOMPComp.showFailedDialog("失败！", "");
  					}
  				}
  			});
  		},
  		queryRelaBrandList : function(start, end,channelNum) {//品牌查询
  			var jb = "1,2";
  			if (start == undefined) {
  				start = 0;
  			}
  			if (end == undefined) {
  				end = 10;
  			}
  				
  			$.singleSync( {
  				data : {
  					"reqUrl" : "brand",
  					"reqMethod" : "queryBrandListEx",
  					"jb":jb,
  					"start" : start,
  					"end" : end
  				},
  				success : function(ret) {
  					if (ret.retCode == 0) {
  						var result = ret.retObj;
  						$("#releBrandList_"+channelNum).empty();
  						for ( var int = 0; int < result.length; int++) {
  							var ck = "<input name='relaBrand"+channelNum+"' type='checkbox' channelNum='"+channelNum+"' value='"+ result[int].brandNum+"' />" + result[int].brandName+"&nbsp;&nbsp;";
  							$("#releBrandList_"+channelNum).append(ck);
  							if(int==0){
  								$("#releBrandList_"+channelNum).append("<br>");
  							}
  						}

  					} else {
  						UOMPComp.showFailedDialog("失败！", "");
  					}
  				}
  			});
  		}
  	};