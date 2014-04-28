$(document).ready(function()
	{
	    ValidateUtil.validate({
		   	targetForm : "cacheAddForm",
		   	rules : {
				memKey    : {required : true, minlength : 1, maxlength : 200} ,
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
		cacheManageMod.init();
	}
);

var cacheManageMod = 
{
    init : function()
			{
			    var selectRowId = $("#selectRowId")[0].value;
				$.singleReq({
					data : 
					{
						"reqUrl" : "cacheManage",
						"reqMethod" : "queryCacheManageInfoById",
						"selectRowId" : selectRowId
					},
					success : function(ret)
					{
						var cacheManageInfo = ret.retObj.cacheManageInfo;
						
						$("#memKey").val(cacheManageInfo.memKey);
						$("#lableMemKey").text(cacheManageInfo.memKey);
						$("#cachedName").val(cacheManageInfo.cachedName);
						$("#daoName").val(cacheManageInfo.daoName);
					    $("#daoMethod").val(cacheManageInfo.daoMethod);
						$("#isNeedParam").val(cacheManageInfo.isNeedParam);
						$("#keyType").val(cacheManageInfo.keyType);
						$("#expireInSeconds").val(cacheManageInfo.expireInSeconds);
						$("#bz").val(cacheManageInfo.bz);
						
					}
				});
				
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
};

function modSubmit()
{
   if(!$('#cacheAddForm').valid())
	{
	    return;
	}
    var memKey = $("#memKey")[0].value;
	var cachedName = $("#cachedName")[0].value;
    var daoName =  $("#daoName")[0].value;
    var daoMethod = $("#daoMethod")[0].value;
    var isNeedParam = $("#isNeedParam")[0].value;
    var keyType = $("#keyType")[0].value;
    var expireInSeconds = $("#expireInSeconds")[0].value;
    var bz = $("#bz")[0].value;
    
        $.singleReq({
			data : 
			{
				"reqUrl"           : "cacheManage",
				"reqMethod"        : "modCacheManageInfo",
				"memKey"           : memKey,
				"cachedName"       : cachedName,
				"daoName"          : daoName,
				"daoMethod"        : daoMethod,
				"isNeedParam"      : isNeedParam,
				"keyType"          : keyType,
				"expireInSeconds"  : expireInSeconds,
				"bz"               : bz
			},
			success : function(ret)
			{
			    if(ret.retCode == 0)
			    {
			        UOMPComp.showSuccessDialog("修改成功！",  ""); 
				    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
			    }
			    else
			    {
			        UOMPComp.showFailedDialog("修改失败！",  ""); 
			    }
			}
        }); 
}


