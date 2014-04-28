$(document).ready(function(){
	subSystemViewComponent.initViewSubsystem();
	
});


/**
 * 
 */
var subSystemViewComponent = {	
	/**
	 * 查询子系统列表
	 */
	initViewSubsystem : function(){
	    var jbNum  = $("#jbNum1").val();
     	var funcId = $("#funcId1").val();
		var subSysNum = $("#subSysNum1").val();
		   $.singleReq({
			data : 
			{
				"reqUrl" : "customMenu",
				"jbNum" :  jbNum,
				"funcId" :  funcId,
				"subSysNum" :  subSysNum,
	    		"reqMethod" : "viewCustomMenu"
			},
			success : function(ret)
			{
				var liscum = ret.retObj.customMenuList;
				if(liscum !=null && liscum !=""){
					var customMenuBean = liscum[0];
				    $("#subSysNum").text(customMenuBean.subSysNum);
					$("#subSysName").html(customMenuBean.subSysName);
					$("#funcId").html(customMenuBean.funcId);
					$("#funcName").html(customMenuBean.funcName);
					$("#moudlSortName").html(customMenuBean.moudleSortName);
					$("#funcUri").html(customMenuBean.funcUri);
					$("#bz").html(customMenuBean.bz);
				 }	
			}
		});
		
	}
};