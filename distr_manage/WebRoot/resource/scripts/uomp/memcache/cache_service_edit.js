
function update() {
	var num = $("#selectRowId").val();
	var typeNum = $("#typeNum").val();
	var servers = $("input[name=servers]").val();
	var weights = $("input[name=weights]").val();
	var initConn = $("input[name=initConn]").val();
	var minConn = $("input[name=minConn]").val();
	var maxConn = $("input[name=maxConn]").val();
	var maxIdle = $("input[name=maxIdle]").val();
	var mainSleep = $("input[name=mainSleep]").val();
	var nagle =$("#nagle").val();
	var socketTo = $("input[name=socketTo]").val();
	var socketConnectTo =$("input[name=socketConnectTo]").val();
	
    $.singleReq({
			data : 
			{
				"reqUrl"     : "cacheService",
				"reqMethod"  : "updateCacheService",
				"num"        : num,
				"typeNum"    : typeNum,
				"servers"    : servers,
				"weights"    : weights,
				"initConn"   : initConn,
				"minConn"    : minConn,
				"maxConn"    : maxConn,
				"maxIdle"    : maxIdle,
				"mainSleep"  : mainSleep,
				"nagle"      : nagle,
				"socketTo"   : socketTo,
		"socketConnectTo"    : socketConnectTo
				
			},
			success : function(ret)
			{
				if(ret.retCode == 0)
			    {
			        UOMPComp.showSuccessDialog(ret.resMsg,  ""); 
				    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
			    }
			    else
			    {
			        UOMPComp.showFailedDialog(ret.resMsg,  ""); 
			    }
			}
		});
}

function query() {
	   $.singleReq({
			data : 
			{
				"reqUrl"     : "cacheService",
				"reqMethod"  : "queryCacheServiceById",
				"selectRowId": $("#selectRowId").val()
			},
			success : function(ret)
			{
				var cacheService = ret.retObj;
				if(cacheService !=null && cacheService !=""){
				    $("#numLable").html(cacheService.num);
					$("#num").val(cacheService.num);
					$("input[name=servers]").val(cacheService.servers);
					$("#typeNum").val(cacheService.typeNum);
					$("#nagle").val(cacheService.nagle);
					$("input[name=weights]").val(cacheService.weights);
					$("input[name=initConn]").val(cacheService.initConn);
					$("input[name=minConn]").val(cacheService.minConn);
					$("input[name=maxConn]").val(cacheService.maxConn);
					$("input[name=maxIdle]").val(cacheService.maxIdle);
					$("input[name=mainSleep]").val(cacheService.mainSleep);
					$("input[name=socketTo]").val(cacheService.socketTo);
					$("input[name=socketConnectTo]").val(cacheService.socketConnectTo);
					
				}
			}
		});
   
   
    $.singleReq({
			data : 
			{
				"reqUrl"     : "cacheTypeManage",
				"reqMethod"  : "queryCacheTypeManageInfoList"
				
			},
			success : function(ret)
			{
			   var retObjTemp =  eval("(" + ret.retObj + ")");
			   
			   for(var index = 0 ; index <retObjTemp.length ; index++)
			   {
			  
			    var typeName = retObjTemp[index].typeName;
			    var typeNum = retObjTemp[index].typeNum;
			  
			      $("#typeNum").append("<option value='"+typeNum+"' >"+ typeName+"</option>");
			     }
			  
			}
		});
		
		
}