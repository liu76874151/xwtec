$(document).ready(function(){

	ValidateUtil.validate({
    	targetForm : "addForm",
    	rules : {
	 		loginName : {required : true} ,
	 		loginPwd : {required : true, minlength : 6} ,
	 		userName : {required : true},
	 		channelNum : {required:true}
		},
		messages : {
			loginName : {required : "请输入登陆名称"},
			loginPwd : {required : "请输入登陆密码", minlength : "长度必须大于等于{0}"},
			userName : {required : "请输入用户名称"},
			channelNum : {required : "请选择渠道"}
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
		//获取渠道数据
		//this.getChannelData();
	},

	getChannelData:function()
	{
		$.singleReq({
			data:
			{
				"reqUrl":"channel",
				"reqMethod":"queryAllChannels",
				"start": "0",
	            "end": "1000"
			},
			success:function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						if(ret.retObj){
							var result = ret.retObj.records;
							var temp = "";
							var optionHTML = "<option>-请选择-</option>";
							//optionHTML += "<option value='0'>全渠道</option>";
							$.each(result, function(i, item) {
								var channelNum = item.channelNum;
								if(channelNum.indexOf(temp) < 0){
									optionHTML += "<option value='"+item.channelNum+"'>"+item.channelName+"</option>";
								}
								temp = channelNum;
							});
							$("#channelNum").html(optionHTML);
						}
					}
				}
			}
		});
	},
	
	//调用后台方法，获取数据
	getFormData : function()
	{
		var groupId = $("#groupId").val();
		var areaNum = $("#areaNum").val();
		$.singleReq({
			data :
			{
				"reqUrl" : "userInfo",
				"groupId" : groupId,
				"areaNum" : areaNum,
				"reqMethod" : "createUserInfo"
			},
			success : function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						if(ret.retObj){
							var listCityInfo = ret.retObj.listCityInfo;
							if(listCityInfo){
								var cityInfo = null;
								var optionStr = "";
								for(var j = 0; j < listCityInfo.length; j++){
									cityInfo = listCityInfo[j];
									optionStr += "<option value='"+cityInfo.areaNum+"' boss_code='"+cityInfo.f_boss_code+"'>"+cityInfo.areaName+"</option>";
								}
								$("#areaName").html(optionStr);
							}
						}
					}else{
						UOMPComp.showFailedDialog(ret.resMsg,"");
					}
				}
			}
		});
	},

	//增加数据提交
	groupInfoAddSubmit : function(){
		if($('#addForm').valid()){
			var loginName = $("#loginName").val();
			var loginPwd = $("#loginPwd").val();
			var userName = $("#userName").val();
			var phone = $("#phone").val();
			var areaName = $("#areaName option:selected").val();
			var userState = $('input[name="userState"]:checked').val();
			var bz = $("#bz").val();
			var groupId = $("#groupId").val();
			var userAreaCode = document.getElementById("userAreaCode").value;
			var channelNum = document.getElementById("channelNum").value;
			$.singleReq({
				data :
				{
					"reqUrl" : "userInfo",
					"loginName" : loginName,
					"changePwd" : "1",
					"loginPwd" : loginPwd,
					"userName" : userName,
					"userGroup" : groupId,
					"userState" : userState,
					"phone" : phone,
					"bz" : bz,
					"userArea" : areaName,
					"userAreaCode":userAreaCode,
					"channelNum":channelNum,
					"reqMethod" : "saveUserInfo"
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
	}
}

function setF_boss_code(obj)
{
	var boss_code_val = $(obj).children('option:selected').attr("boss_code");
	document.getElementById("userAreaCode").value = boss_code_val;
}
