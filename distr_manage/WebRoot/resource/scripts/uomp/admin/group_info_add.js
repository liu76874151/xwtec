$(document).ready(function(){
	ValidateUtil.validate({
    	targetForm : "addForm",
    	rules : {
			groupIdVal : {required : true} ,
			groupName : {required : true, minlength : 6}
		},
		messages : {
			groupIdVal : {required : "请输入用户组编码"},
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
				"reqMethod" : "createGroupInfo"
			},
			success : function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						if(ret.retObj){
							var groupInfo = ret.retObj.groupInfo;
							$("#jbNum").val(groupInfo.jbNum);
							$("#jb").val(groupInfo.jb);
							var listCityInfo = ret.retObj.listCityInfo;
							if(listCityInfo){
								var cityInfo = null;
								var optionStr = "";
								for(var j = 0; j < listCityInfo.length; j++){
									cityInfo = listCityInfo[j];
									optionStr += "<option value='"+cityInfo.areaNum+"'>"+cityInfo.areaName+"</option>";
								}
								$("#groupArea").html(optionStr);
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
			var supGroupId = $("#groupId").val();
			var groupName = $("#groupName").val();
			var groupArea = $("#groupArea").val();
			var bz = $("#bz").val();
			var jbNum = $("#jbNum").val();
			var jb = $("#jb").val();

			$.singleReq({
				data :
				{
					"reqUrl" : "groupinfo",
					"supGroupId" : supGroupId,
					"groupId" : groupId,
					"groupName" : groupName,
					"groupArea" : groupArea,
					"bz" : bz,
					"jbNum" : jbNum,
					"jb" : jb,
					"reqMethod" : "saveGroupInfo"
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
