function view() {
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
					$("#num").text(cacheService.num);
					$("#typeNum").text(cacheService.typeNum);
					$("#servers").text(cacheService.servers);
					$("#weights").text(cacheService.weights);
					$("#initConn").text(cacheService.initConn);
					$("#minConn").text(cacheService.minConn);
					$("#maxConn").text(cacheService.maxConn);
					$("#maxIdle").text(cacheService.maxIdle);
					$("#mainSleep").text(cacheService.mainSleep);
					$("#nagle").text(cacheService.nagle);
					$("#socketTo").text(cacheService.socketTo);
					$("#socketConnectTo").text(cacheService.socketConnectTo);
				}
			}
		});
}