$(document).ready(function()
	{
		view();
	}
);

function view() {
    $.singleReq({
			data : 
			{
				"reqUrl"     : "cacheManage",
				"reqMethod"  : "queryCacheManageInfoById",
				"selectRowId": $("#selectRowId").val()
			},
			success : function(ret)
			{
			    if(ret.retCode == 0)
			    {
			        var cacheManageInfo = ret.retObj.cacheManageInfo;
					$("#memKey").text(cacheManageInfo.memKey);
					$("#cachedName").text(cacheManageInfo.cachedName);
					$("#daoName").text(cacheManageInfo.daoName);
					$("#daoMethod").text(cacheManageInfo.daoMethod);
					$("#isNeedParam").text(cacheManageInfo.isNeedParam);
					$("#keyType").text(cacheManageInfo.keyType);
					$("#expireInSeconds").text(cacheManageInfo.expireInSeconds);
					$("#bz").text(cacheManageInfo.bz);
			    }
				else
				{
				    alert(ret.retMsg);
				}  
			}
		});
}