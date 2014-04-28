var channelNum;
var cityIdResult;
var zoneIdResult;
$(document).ready(function() {
	component.initOrgInfo();
	component.initCity();
//	component.initZoneInfo();
	
});

var component = {
		initCity : function() {
			$.singleReq({
				data : {
					"reqUrl" : "area",
					"reqMethod" : "queryAreaCityList"
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						$.each(result, function(i, item) {
							var selectedStr = "";
							if(cityIdResult == item.bossCode){
								selectedStr = "selected='selected'";
							}
							$("#cityId").append(
									"<option "+selectedStr+" value=" + item.bossCode + ">"
											+ item.areaName + "</option>");

						});

					}
					var obj = $("#area [value='0']");// ---是不是省级用户 bossCode=0
					// if(obj[0]){ $("#isSJ").val("1");}
					component.initZoneInfo();
				}

			});

		},
		initZoneInfo : function() {
			var cityId = $("#cityId").find("option:selected").val();
			$.singleReq({
				data : {
					"reqUrl" : "zoneInfo",
					"reqMethod" : "queryZoneInfoList",
					"cityId" : cityId
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						$.each(result, function(i, item) {
							var selectedStr = "";
							if(zoneIdResult == item.zoneId){
								selectedStr = "selected=true";
							}
							$("#zoneId").append(
									"<option "+selectedStr+" value=" + item.zoneId + ">"
											+ item.zoneName + "</option>");
						});

					}
				}
			});
		},
		/**
		 * 初始渠道信息
		 */
		initOrgInfo:function(){
			var orgCode = $("#orgCode").val();
			$.singleReq({
				data : {
					"reqUrl" : "orgHandle",
					"reqMethod" : "queryOrgByPkid",
					"orgCode" : orgCode
				},
				success : function(ret) {
					if (ret.retCode == 0) {
						var result = ret.retObj;
						$("#orgName").val(result.orgName);
						$("#linkTel").val(result.linkTel);
						$("#orgAddr").val(result.orgAddr);
						$("#cityId").val(result.cityId);
						$("#zoneId").val(result.zoneId);
						$("#state").val(result.state);
						$("#orgType").val(result.orgType);
						cityIdResult = result.cityId;
						zoneIdResult = result.zoneId;
					}
				}
			});
			
		},
		updateOrgInfo:function(){
			var orgName = $("#orgName").val();
			var orgCode = $("#orgCode").val();
			var linkTel = $("#linkTel").val();
			var cityId = $("#cityId").find("option:selected").val();
			var zoneId = $("#zoneId").find("option:selected").val();
			//var orgType = $("#orgType").find("option:selected").val();
			var orgType = $("#orgType").val();
			
			$.singleReq({
		        data: {
		            "reqUrl": "orgHandle",
		            "reqMethod": "updateOrgInfo",
		            "orgName":orgName,
		            "orgCode":orgCode,
		            "linkTel":linkTel,
		            "cityId":cityId,
		            "zoneId":zoneId,
		            "orgType":orgType
		        },
		        success: function (ret) {
		            if (ret.retCode == 0) {
		                UOMPComp.showSuccessDialog("营业厅修改成功！", "");
		                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
		            }
		            else {
		                UOMPComp.showFailedDialog("营业厅修改失败！", "");
		            }
		        }
		    })
		}
}