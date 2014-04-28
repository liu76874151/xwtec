/**
 * 一级预约营销案CRM编码
 */
var remarketFirstCode;
/**
 * 从CRM获取到的礼品信息ID
 */
var giftIds;
/**
 * 从CRM获取到的礼品信息名称
 */
var giftNames;
/**
 * 从CRM获取到的业务信息ID
 */
var bisIds;
/**
 * 从CRM获取到的业务名称
 */
var bisNames;
/**
 * 从CRM获取到的礼品和业务信息备注
 */
var fBz;
/**
 * 二级预约营销案BOSS编码
 */
var fmarketLevel2Boss
var z;
window.dhx_globalImgPath = GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/imgs/";

$(document).ready(function() {
	remarketFirstCode = $("#remarketFirstName").attr("code");
	// 二级预约boss
	component.queryREBossMarketSecond();
	// 日期控件
	var myCalendar = new dhtmlXCalendarObject([ "beginTime", "endTime" ]);
	// myCalendar.setPosition($("#beginTime").offset().top - 600,
	// $("#beginTime").offset().left + 100);
	component.initFirstMarketInfo();
});

var component = {
	initFirstMarketInfo:function(){
		var pkid = $("#remarketFirstName").attr("pkid");
		$.singleReq({
			data:{
				"reqUrl":"reservationFirst",
				"reqMethod":"queryByPrimaryKey",
				"pkid":pkid
			},
			success:function(ret){
				if(ret.retCode == 0){
					var result = ret.retObj;
					$("#remarketFirstName").val(result.marketFirstName);
				}
			}
		});
	},
	// 查询CRM二级预约营销案
	queryREBossMarketSecond : function() {

		$.singleReq({
			data : {
				"reqUrl" : "rebossMarket",
				"reqMethod" : "queryCrmSecondInfoList",
				"crmFirstCode" : remarketFirstCode,
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var option = "<option value='"
								+ result[int].fmarketLevel2Id + "' >"
								+ result[int].fmarketLevel2Name + "</option>";
						$("#remarketSecondNameSel").append(option);
					}
					z = dhtmlXComboFromSelect("remarketSecondNameSel");
					z.enableFilteringMode(true);
					z.attachEvent("onChange", function() {
						component.crmOnchange(z.getSelectedValue())
					});
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	// 查询BOSS礼品包和业务包
	queryREBossGift : function(crmSecondCode) {
		$.singleReq({
			data : {
				"reqUrl" : "rebossMarket",
				"reqMethod" : "queryCrmSecondGiftInfoList",
				"crmSecondCode" : crmSecondCode,
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					// 礼品包
					$("#giftId option").remove();
					giftIds = "";
					giftNames = "";
					for ( var int = 0; int < result.length; int++) {
						if (result[int].fgoodsId != undefined) {
							if(int > 0){
								giftIds += ",";
								giftNames += ",";
							}
							giftIds += result[int].fgoodsId;
							giftNames += result[int].fgoodsName;
							var option = "<option value='"
									+ result[int].fgoodsId + "' >"
									+ result[int].fgoodsName + "</option>";
							$("#giftId").append(option);
						}
					}
					// 业务包
					$("#bisId option").remove()
					bisIds = "";
					bisNames = "";
					for ( var int = 0; int < result.length; int++) {
						if (result[int].fbusiId != undefined) {
							if(int > 0){
								bisIds += ",";
								bisNames += ",";
							}
							bisIds += result[int].fbusiId;
							bisNames += result[int].fbusiName;
							var option = "<option value='"
									+ result[int].fbusiId + "' >"
									+ result[int].fbusiName + "</option>";
							$("#bisId").append(option);
						}
					}
					// 预约现有充值金额
					var moneyVal = "";
					fBz = "";
					fmarketLevel2Boss = "";
					if (result != null && result != undefined && result != "") {
						moneyVal = result[0].fmoneyVal;
						$("#fmoneyvalue").html(moneyVal);
						fBz = result[0].fbz;
						fmarketLevel2Boss = result[0].fmarketLevel2Boss;
					}

				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	//保存二级预约营销案
	saveForm: function () {
	    if (!$('#addForm').valid()) {
	        return;
	    }
	    var marketFirstPkId = $("#remarketFirstName").attr("pkid");
	    var marketSecondCode = z.getSelectedValue();
	    var marketSecondName = z.getSelectedText();
	    var viewName = $("#viewName").val();
	    var toObject = $("#toObject").val();
	    var activityComment = $("#activityComment").val();
	    var moneyVal = $("#fmoneyvalue").html();
	    if(moneyVal == "暂无"){
	    	moneyVal = "0";
	    }
	    var beginTime = $("#beginTime").val();
	    var endTime = $("#endTime").val();
	    var notice = $("#notice").text();
	    $.singleReq({
	        data: {
	            "reqUrl": "reservationMarketSecond",
	            "reqMethod": "addReservationMarketSecond",
	            "marketFirstPkid":marketFirstPkId,
	            "marketSecondCode":marketSecondCode,
	            "marketSecondName":marketSecondName,
	            "viewName":viewName,
	            "toObject":toObject,
	            "activityComment":activityComment,
	            "moneyVal":moneyVal,
	            "beginTime":beginTime,
	            "endTime":endTime,
	            "notice":notice,
	            "giftIds":giftIds,
	            "giftNames":giftNames,
	            "bisIds":bisIds,
	            "bisNames":bisNames,
	            "fBz":fBz,
	            "fmarketLevel2Boss":fmarketLevel2Boss,
	            "isListView":1,
	            "isInGroup":"0",
	            "verifyState":"0",
	            "proVerifyState":"0",
	            "localVerifyState":"0"
	        },
	        success: function (ret) {
	            if (ret.retCode == 0) {
	                UOMPComp.showSuccessDialog("添加成功！", "");
	                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
	            }
	            else {
	                UOMPComp.showFailedDialog("添加失败！", "");
	            }
	        }
	    })
	}
}