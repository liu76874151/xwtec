var myForm;
$(document).ready(function() {
	component.initChannelInfo();
});

var component = {
		/**
		 * 初始渠道信息
		 */
		initChannelInfo:function(){
			var groupId = $("#groupId").val();
			$.singleReq({
				data : {
					"reqUrl" : "target",
					"reqMethod" : "queryTargetGroupInfoByPkid",
					"groupId" : groupId
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						$("#groupName").val(result.groupName);
						$("#city").val(result.city);
						$("#areaName").val(result.areaName);
						$("#groupName_sig").val(result.groupName);
						
					}
				}
			});
		},
		/**
		 * 查询手机号
		 */
		queryPhoneNumber:function(){
			var phoneNumber = $("#phoneNumber").val();
			var groupId = $("#groupId").val();
			$.singleReq({
				data : {
					"reqUrl" : "target",
					"reqMethod" : "queryPhonNumber",
					"groupId" : groupId,
					"phoneNumber":phoneNumber
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						UOMPComp.showTipDialog("找到 "+result.count+" 个匹配的号码", "");
					}
				}
			});
		},
		/***
		 * 
		 */
		deletePhoneNumber:function(){
			var groupId = $("#groupId").val();
			$.singleReq({
				data : {
					"reqUrl" : "target",
					"reqMethod" : "deletePhoneNumber",
					"groupId" : groupId
				},
				success : function(ret) {
					if (ret.retCode == 0) {
 		                UOMPComp.showSuccessDialog(" 清空成功！", "");
 		                //top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
 		            }
 		            else {
 		                UOMPComp.showFailedDialog(" 清空失败！", "");
 		            }
				}
			});
		},
		/**
		 * 添加单个号码
		 */
		addPhoneNumber:function(){
			var phoneNumber = $("#phoneNumber_add").val();
			var groupId = $("#groupId").val();
			$.singleReq({
				data : {
					"reqUrl" : "target",
					"reqMethod" : "addPhoneNumber",
					"groupId" : groupId,
					"phoneNumber":phoneNumber
				},
				success : function(ret) {
					if (ret.retCode == 0) {
 		                UOMPComp.showSuccessDialog(phoneNumber + " 保存成功！", "");
 		                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
 		            }
 		            else {
 		                UOMPComp.showFailedDialog(phoneNumber + " 保存失败！", "");
 		            }
				}
			});
		},
		uploadFile:function(){
//			$.singleReq({
//				data : {
//					"reqUrl" : "target",
//					"reqMethod" : "importData"
//				},
//				success : function(ret) {
//					if (ret.retCode == 0) {
// 		                UOMPComp.showSuccessDialog("组织信息保存成功！", "");
// 		                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
// 		            }
// 		            else {
// 		                UOMPComp.showFailedDialog("组织信息保存失败！", "");
// 		            }
//				}
//			});
			
			$("#addForm").submit();

			var times = 3000;
			var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("导入成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
		}

}