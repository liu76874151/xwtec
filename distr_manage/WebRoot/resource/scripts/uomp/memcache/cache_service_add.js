function add() {
	var num = $("input[name=num]").val();
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
	 if($('#addForm').valid()){

    $.singleReq({
			data : 
			{
				"reqUrl"     : "cacheService",
				"reqMethod"  : "saveCacheService",
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
			        UOMPComp.showFailedDialog(ret.resMsg, "");
			    }
			}
		}); 
	 }
};

function getTypeName() {
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