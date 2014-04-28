$(document).ready(function(){
    ValidateUtil.validate({
    	targetForm : "cacheAddForm",
    	rules : {
			memKey    : {required : true, minlength : 1, maxlength : 200},
			daoName   : {required : true, minlength : 1, maxlength : 80},
			daoMethod : {required : true, minlength : 1, maxlength : 80},
			bz        : {maxlength : 200}  
		},
		messages : {
			memKey    : {required    : "请输入缓存key", minlength : "长度必须大于等于{0}", maxlength : "长度不能超过{0}"},
			daoName   : {required   : "请输入DAO类的名称", minlength : "长度必须大于等于{0}", maxlength : "长度不能超过{0}"},
			daoMethod : {required : "请输入DAO类中的方法名称", minlength : "长度必须大于等于{0}", maxlength : "长度不能超过{0}"},
			bz        : {maxlength : "长度不能超过{0}"}
		}
    });
    cacheManageInfoComponent.init();
});

var cacheManageInfoComponent = {

    //初始化
	init : function(){
		//调用后台方法，获取数据
		this.getFormData();
	},
	
	//调用后台方法，获取数据
	getFormData : function()
	{
	    $.singleReq({
			data : 
			{
				"reqUrl" : "cacheTypeManage",
				"reqMethod" : "queryCacheTypeInfoList"
			},
			success : function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						if(ret.retObj){
							var cacheTypeInfolist = ret.retObj.cacheTypeInfolist;
							if(cacheTypeInfolist){
								var cacheTypeInfo = null;
								var optionStr = "";
								for(var j = 0; j < cacheTypeInfolist.length; j++){
									cacheTypeInfo = cacheTypeInfolist[j];
									optionStr += "<option value='"+cacheTypeInfo.typeNum+"'>"+cacheTypeInfo.typeName+"</option>";
								}
								$("#cachedName").html(optionStr);
							}
						}
					}else{
						if(ret.resMsg){
							UOMPComp.showFailedDialog(ret.resMsg,"");
						}else{
							UOMPComp.showFailedDialog("系统异常","");
						}
					}
				}
			}
		});
	}
}

function addSubmit() {

    if(!$('#cacheAddForm').valid())
	{
	    return;
	}
	var memKey = $("#memKey").val();
	var cachedName = $("#cachedName").val();
	var daoName = $("#daoName").val();
	var daoMethod = $("#daoMethod").val();
	var isNeedParam = $("#isNeedParam").val();
	var keyType = $("#keyType").val();
	var expireInSeconds = $("#expireInSeconds").val();
	var bz = $("#bz").val();
	
	    $.singleReq({
			data : 
			{
				"reqUrl"             : "cacheManage",
				"reqMethod"          : "addCacheManageInfo",
				"memKey"             : memKey,
				"cachedName"         : cachedName,
				"daoName"            : daoName,
				"daoMethod"          : daoMethod,
				"isNeedParam"        : isNeedParam,
				"keyType"            : keyType,
				"expireInSeconds"    : expireInSeconds,
				"bz"                 : bz
			},
			success : function(ret)
			{
			    if(ret.retCode == 0)
			    {
			        UOMPComp.showSuccessDialog("添加成功！",  ""); 
			        top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
			    }
			    else
			    {
			        UOMPComp.showFailedDialog("添加失败！", "");
			    }
			}
		});
    
}