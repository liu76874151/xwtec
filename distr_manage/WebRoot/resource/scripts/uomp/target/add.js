
var myForm;
$(document).ready(function() {
	component.initCalendar();
	component.initCity();
});

var component = {
		//日期控件
		initCalendar:function () {
			var t = new dhtmlXCalendarObject(["beginTime","endTime"]);
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
		addGroup:function(){
			var groupName = $("#groupName").val();
			var description = $("#description").val();
			var city = $("#city").find("option:selected").val();
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
//			var groupType = $("#groupType").find("option:selected").val();
			$.singleReq({
				data:{
					"reqUrl":"target",
					"reqMethod":"addGroupInfo",
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