$(document).ready(function(){
	subSystemViewComponent.initViewSubsystem();
	
});


/**
 * 子系统查询
 */
var subSystemViewComponent = {	
	/**
	 * 查询子系统列表
	 */
	initViewSubsystem : function(){
		var sysNum = $("#sysNum")[0].value;
		
		
		
		
		$.singleReq({
			data : 
			{
				"reqUrl" : "subSystem",
				"sysNum" :  sysNum,
				"reqMethod" : "querySubSystemByNum"
			},
			success : function(ret)
			{
				var subSystemList = ret.retObj.subSystemList;
				if(subSystemList !=null && subSystemList !=""){
					var subSystemInfo = subSystemList[0];
					$("#sysNum1").val(subSystemInfo.sysNum);
					$("#sysName").val(subSystemInfo.sysName);
					$("#sysTitle").val(subSystemInfo.sysTitle);
					$("#sysUri").val(subSystemInfo.sysUri);
					$("#xh").val(subSystemInfo.xh);
					$("#bz").val(subSystemInfo.bz);	
				 }
	
			}
		});
	}
};