var blockDS;
var blockCount;
var myForm;
$(document).ready(function() {
	 component.initCity();
	 component.initZoneInfo();
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
						$("#cityId").append(
								"<option value=" + item.bossCode + ">"
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
						$("#zoneId").append(
								"<option value=" + item.zoneId + ">"
										+ item.zoneName + "</option>");
					});

				}
			}
		});
	},
	/**
	 * 保存渠道
	 */
	addOrg : function() {
		var orgName = $("#orgName").val();
		var orgAddr = $("#orgAddr").val();
		var linkTel = $("#linkTel").val();
		var cityId = $("#cityId").find("option:selected").val();
		var zoneId = $("#zoneId").find("option:selected").val();
		var state = $("#state").find("option:selected").val();
		//var orgType = $("#orgType").find("option:selected").val()
		var orgType = $("#orgType").val()
		$.singleReq({
			data : {
				"reqUrl" : "orgHandle",
				"reqMethod" : "addOrgInfo",
				"orgName" : orgName,
				"orgAddr" : orgAddr,
				"linkTel" : linkTel,
				"zoneId":zoneId,
				"cityId":cityId,
				"state":state,
				"orgType":orgType
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					UOMPComp.showSuccessDialog("营业厅保存成功！", "");
					top.UOMPDialog.subPageCallback
							&& top.UOMPDialog.subPageCallback();
				} else {
					UOMPComp.showFailedDialog("营业厅保存失败！", "");
				}
			}
		});
	}
}