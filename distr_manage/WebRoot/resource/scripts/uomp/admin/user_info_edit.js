$(document).ready(function(){
	ValidateUtil.validate({
    	targetForm : "addForm",
    	rules : {
	 		loginPwd : {required : true, minlength : 6} ,
	 		userName : {required : true}
		},
		messages : {
			loginPwd : {required : "请输入登陆密码", minlength : "长度必须大于等于{0}"},
			userName : {required : "请输入用户名称"}
		}
    });

	userInfoVComponent.init();
});

var userInfoVComponent = {
	jbNum : null,
	jb    : null,

	//初始化
	init : function(){
		//调用后台方法，获取数据
		this.getFormData();
	},

	//调用后台方法，获取数据
	getFormData : function()
	{
		var loginNameVal = $("#loginNameVal").val();
		var areaNum = $("#areaNum").val();
		$.singleReq({
			data :
			{
				"reqUrl" : "userInfo",
				"loginName" : loginNameVal,
				"reqMethod" : "editUserInfo"
			},
			success : function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						if(ret.retObj){
							var userInfo = ret.retObj.userInfo;
							var cityInfoBean = ret.retObj.cityInfoBean;
							if(userInfo){
								$("#loginName").val(userInfo.loginName);
								$("#pwd").val(userInfo.loginPwd);
								$("#userName").val(userInfo.userName);
								$("#phone").val(userInfo.phone);
								//用户状态（0-禁用； 1-启用）
								if(userInfo.userState == "1"){
									$("input[name=userState]:eq(0)").attr("checked",'checked');

								}else if(userInfo.userState == "0"){
									$("input[name=userState]:eq(1)").attr("checked",'checked');
								}

								$("#groupId").val(userInfo.userGroup);
								$("#bz").val(userInfo.bz);
							}
							if(cityInfoBean){
								$("#areaName").val(cityInfoBean.areaName);
								$("#userArea").val(cityInfoBean.areaNum);
							}
						}
					}else{
						UOMPComp.showFailedDialog(ret.resMsg,"");
					}
				}
			}
		});
	},

	//修改数据提交
	groupInfoEditSubmit : function(){
		if($('#addForm').valid()){
			var loginName = $("#loginName").val();
			var loginPwd = $("#loginPwd").val();
			var userName = $("#userName").val();
			var phone = $("#phone").val();
			var areaName = $("#userArea").val();
			var userState = $('input[name="userState"]:checked').val();
			var bz = $("#bz").val();
			var pwd = $("#pwd").val();
			var groupId = $("#groupId").val();
			var changePwd = "";
			if($("#changePwd").is(":checked")){
				changePwd = $("#changePwd").val();
			}
			$.singleReq({
				data :
				{
					"reqUrl" : "userInfo",
					"loginName" : loginName,
					"loginPwd" : loginPwd,
					"userName" : userName,
					"userGroup" : groupId,
					"pwd"      : pwd,
					"userState" : userState,
					"changePwd" : changePwd,
					"phone" : phone,
					"bz" : bz,
					"userArea" : areaName,
					"reqMethod" : "updateUserInfo"
				},
				success : function(ret)
				{
					if(ret){
						if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
							var resMsg = ret.resMsg;
							var retCode = ret.retCode;
							UOMPComp.showSuccessDialog(resMsg,"");
							if(GLOBAL_INFO.SYS_FAILED == retCode){

							}else if(GLOBAL_INFO.SYS_SUCCESS){
								top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
							}
						}else{
							UOMPComp.showFailedDialog(ret.resMsg,"");
						}
					}
				}
			});
		}
	},

	/**
	 * 修改密码 判断
	 */
	boxChange : function(){
		var box = document.getElementById("changePwd");
		var password = document.getElementById("loginPwd");
		if(box.checked)
		{
			$("#loginPwd").val("");
			password.disabled = false;
			password.focus();
		}
		else
		{
			password.disabled = true;
			password.value = "●●●●●●●●●●●●●";
		}
	}
}
