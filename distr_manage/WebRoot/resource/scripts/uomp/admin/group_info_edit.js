$(document).ready(function(){
	ValidateUtil.validate({
    	targetForm : "addForm",
    	rules : {
			groupName : {required : true, minlength : 6}
		},
		messages : {
			groupName : {required : "用户组名称", minlength : "长度必须大于等于{0}"}
		}
    });
	groupInfoVComponent.init();
});

var groupInfoVComponent = {
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
		var groupId = $("#groupId").val();
		var areaNum = $("#areaNum").val();
		$.singleReq({
			data :
			{
				"reqUrl" : "groupinfo",
				"groupId" : groupId,
				"areaNum" : areaNum,
				"reqMethod" : "editGroupInfo"
			},
			success : function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						if(ret.retObj){
							var groupInfo = ret.retObj.groupInfo;
							var cityInfoBean = ret.retObj.cityInfoBean;
							$("#jbNum").val(groupInfo.jbNum);
							$("#jb").val(groupInfo.jb);

							$("#groupIdVal").val(groupInfo.groupId);
							$("#groupName").val(groupInfo.groupName);
							$("#bz").val(groupInfo.bz);

							if(cityInfoBean){
								$("#groupAreaName").val(cityInfoBean.areaName);
								$("#groupArea").val(cityInfoBean.areaNum);
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
			var groupId = $("#groupIdVal").val();
			var groupName = $("#groupName").val();
			var groupArea = $("#groupArea").val();
			var bz = $("#bz").val();
			var jbNum = $("#jbNum").val();
			var jb = $("#jb").val();

			$.singleReq({
				data :
				{
					"reqUrl" : "groupinfo",
					"groupId" : groupId,
					"groupName" : groupName,
					"groupArea" : groupArea,
					"bz" : bz,
					"jbNum" : jbNum,
					"jb" : jb,
					"reqMethod" : "updateGroupInfo"
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
								window.parent.location.reload();
								//groupInfoQComponent.groupInfoQTree.openItem("1223");
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
