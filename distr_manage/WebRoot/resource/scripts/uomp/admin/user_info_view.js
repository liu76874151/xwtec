$(document).ready(function(){
	userInfoVComponent.init();
});

var userInfoVComponent = {
	//初始化
	init : function(){
		//调用后台方法，获取数据
		this.getFormData();
	},

	//调用后台方法，获取数据
	getFormData : function()
	{
		var loginName = $("#loginName").val();
		$.singleReq({
			data :
			{
				"reqUrl" : "userInfo",
				"loginName" : loginName,
				"reqMethod" : "viewUserInfo"
			},
			success : function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						if(ret.retObj && ret.retObj.userInfo){
							var userInfo = ret.retObj.userInfo;
							$("#loginNameVal").html(userInfo.loginName);
							$("#userName").html(userInfo.userName);
							$("#phone").html(userInfo.phone);
							$("#loginPwd").html(userInfo.loginPwd);
							$("#userGroup").html(userInfo.userGroup);
							$("#userState").html(userInfo.userState);
							$("#bz").html(userInfo.bz);

							//用户状态（0-禁用； 1-启用）
							if(userInfo.userState == "1"){
								$("#userState").html("启用");
							}else if(userInfo.userState == "0"){
								$("#userState").html("禁用");
							}
						}
						if(ret.retObj && ret.retObj.cityInfoBean){
							$("#areaName").html(ret.retObj.cityInfoBean.areaName);
						}
					}else{
						UOMPComp.showFailedDialog(ret.resMsg,"");
					}
				}
			}
		});
	}
}