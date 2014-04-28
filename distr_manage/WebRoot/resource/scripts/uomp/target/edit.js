var myForm;
$(document).ready(function() {
	component.initCalendar();
	component.initCity();
	component.initGroupInfo();
});

var component = {
		//日期控件
		initCalendar:function () {
			var t = new dhtmlXCalendarObject(["beginTime","endTime"]);
		},
		initGroupInfo:function(){
			var groupId = $("#groupId").val();
			$.singleReq({
				data:{
					"reqUrl":"target",
					"reqMethod":"queryTargetGroupInfoByPkid",
					"groupId":groupId
				},
				success:function(ret){
					if(ret.retCode == 0){
						var result = ret.retObj;
						$("#groupName").val(result.groupName);
						$("#description").val(result.description);
						$("#city").val(result.city);
//						$("#groupType").val(result.groupType);
						$("#beginTime").val(result.beginTime);
						$("#endTime").val(result.endTime);
					}
				}
			});
		},
		/**
	     * 初始化地市条件
	     */
	    initCity:function(){
		    $.singleReq({
		         data: {
		             "reqUrl": "area",
		             "reqMethod": "queryCityList"
		         },
		         success: function (ret) {
		             if (ret.retCode == 0) {
		                 var result = ret.retObj.records; 
		               $.each(result, function(i, item) {
		                	 $("#city").append("<option value="+item.areaBossCode+">"+item.areaName+"</option>");
		               });
		             }
		             else {
		             }
		         }
		     });
		},
		/**
		 * 保存组织
		 */
		updateGroup:function(){
			var groupId = $("#groupId").val();
			var groupName = $("#groupName").val();
			var description = $("#description").val();
			var city = $("#city").find("option:selected").val();
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
//			var groupType = $("#groupType").find("option:selected").val();
			$.singleReq({
				data:{
					"reqUrl":"target",
					"reqMethod":"updateGroupInfo",
					"groupId":groupId,
					"groupName":groupName,
					"description":description,
					"city":city,
					"beginTime":beginTime,
					"endTime":endTime
//					"groupType":groupType
				},
				success : function(ret) {
					if (ret.retCode == 0) {
 		                UOMPComp.showSuccessDialog("组织信息保存成功！", "");
 		                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
 		            }
 		            else {
 		                UOMPComp.showFailedDialog("组织信息保存失败！", "");
 		            }
				}
			});
		},
		uploadFile:function(){
			$("#addForm").submit();
		}

}