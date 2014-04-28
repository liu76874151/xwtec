$(document).ready(function(){
	groupInfoVComponent.init();
});

var groupInfoVComponent = {
	//初始化
	init : function(){
		//调用后台方法，获取数据
		this.getFormData();
	},

	//调用后台方法，获取数据
	getFormData : function()
	{
		var groupId = $("#groupId").val();
		$.singleReq({
			data :
			{
				"reqUrl" : "groupinfo",
				"groupId" : groupId,
				"reqMethod" : "viewGroupInfo"
			},
			success : function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						if(ret.retObj){
							var groupInfo = ret.retObj.groupInfo;
							if(groupInfo){
								$("#groupIdVal").html(groupInfo.groupId);
								$("#groupName").html(groupInfo.groupName);
								$("#groupArea").html(groupInfo.groupArea);
								$("#bz").html(groupInfo.bz);
								$("#jbNum").html(groupInfo.jbNum);
								$("#jb").html(groupInfo.jb);
								if(groupInfo.mj == "1"){
									$("#mj").html("是");
								}else if(groupInfo.mj == "0"){
									$("#mj").html("否");
								}
							}
							if(ret.retObj && ret.retObj.cityBean){
								$("#groupArea").html(ret.retObj.cityBean.areaName);
							}
						}
					}else{
						UOMPComp.showFailedDialog(ret.resMsg,"");
					}
				}
			}
		});
	}
}