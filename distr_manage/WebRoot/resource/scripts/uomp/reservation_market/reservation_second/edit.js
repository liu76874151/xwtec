/**
 * 二级预约营销案主键id
 */
var marketSecondPkid = '';

$().ready(function() {
	marketSecondPkid = $("#marketSecondPkid").val();
	//初始化日期控件
	var myCalendar = new dhtmlXCalendarObject( [ "beginTime", "endTime" ]);
	//查询二级预约营销案
	component.queryReservationMarketSecnd();
});

var component = {
		//根据主键查询二级预约营销案
		queryReservationMarketSecnd : function(){
			$.singleReq({
	            data: {
	                "reqUrl": "reservationMarketSecond",
	                "reqMethod": "queryReservationMarketSecondByPkid",
	                "marketSecondPkid": marketSecondPkid
	            },
	            success: function (ret) {
	                var result = ret.retObj;
	                //二级预约营销案boss名称
	                var remarketSecondName = result.marketSecondName;
	                $("#remarketSecondNameSel").val(remarketSecondName);
	                //展示名称
	                var viewName = result.viewName;
	                $("#viewName").val(viewName);
	                //活动对象
	                var toObject = result.toObject;
	                $("#toObject").val(toObject);
	                //活动说明
	                var activityComment = result.activityComment;
	                $("#activityComment").val(activityComment);
	                //BOSS礼品包ID和BOSS礼品包名称
	                if(result.bossGiftId != null && result.bossGiftId != undefined){
		                var option = "<option value='"
							+ result.bossGiftId + "' >"
							+ result.bossGiftName + "</option>";
		                $("#giftId").append(option);
	            	}
	                //BOSS业务ID和BOSS业务名称
	            	if(result.bizCode != null && result.bizCode != undefined){
		                var option = "<option value='"
							+ result.bizCode + "' >"
							+ result.bizName + "</option>";
		                $("#bisId").append(option);
	            	}
	                //注意事项
	                var notice = result.notice;
	                $("#notice").val(notice);
	                //充值金额
	                var moneyVal = result.moneyVal;
	                $("#fmoneyvalue").html(moneyVal);
	                //开始时间
	                var beginTime = result.beginTime;
	                $("#beginTime").val(beginTime);
	                //结束时间
	                var endTime = result.endTime;
	                $("#endTime").val(endTime);
	                $("#marketFirstName").html(result.marketFirstName);
	            }
			})
		},
		//修改二级预约营销案
		saveForm : function() {
			if (!$('#addForm').valid()) {
		        return;
		    }
		    var viewName = $("#viewName").val();
		    var toObject = $("#toObject").val();
		    var activityComment = $("#activityComment").val();
		    var beginTime = $("#beginTime").val();
		    var endTime = $("#endTime").val();
		    var notice = $("#notice").val();
		    $.singleReq({
		        data: {
		            "reqUrl": "reservationMarketSecond",
		            "reqMethod": "updateReservationMarketSecond",
		            "marketSecondPkId":marketSecondPkid,
		            "viewName":viewName,
		            "toObject":toObject,
		            "activityComment":activityComment,
		            "beginTime":beginTime,
		            "endTime":endTime,
		            "notice":notice,
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
};