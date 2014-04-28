var marketFirstPkid = '';
var cityId = '';
var tchannal = '';
var marketFirstName = '';
var marketFirstCode = '';
var brand = '';

$().ready(function() {
	marketFirstPkid = $("#marketFirstPkid").val();
	cityId = $("#cityId").val();
	tchannal = $("#tchannal").val();
	marketFirstName = $("#marketFirstName").val();
	marketFirstCode = $("#marketFirstCode").val();
	brand = $("#brand").val();
	
	component.initDataGrid();// 初始化dataGrid
});


var component = {
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,pkid,渠道,地市,二级方案名称,活动对象,开始时间,结束时间,配置时间,配置工号,列表展示状态,线上测试,地市审核状态,省级审核状态",
			"initWidth" : "60,80,80,80,*,80,80,80,80,80,80,80,80,80,80,80",
			"colAlign" : "center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,link,link",
			"colSorting" : "str,str,str,na,str,str,str,str,str,str,str,str,str,str,str,str",
			"colKeys" : [ "marketSecondPkId",{
				"key": "channelData",
                "formatter": function (v) {
                	return v.replace(/4/, "网厅").replace(/5/, "掌厅").replace(/6/, "短厅");
                }
},"areaName","viewName","toObject","beginTime","endTime","createTime","cfgUserId",
			              {
			                "key": "isListView",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "不展示";
			                    }
			                    else if (v == "1") {
			                        return "展示";
			                    }
			                }
			            },"testOper","localOper","proOper"
			              ]
		});
		datagrid.setColumnHidden(0,true);
		datagrid.setColumnHidden(1,true);
		
		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
	},
	
	/**
	 * 查询
	 */
	query : function(start, end) {
		//var marketFirstPkid = $("#marketFirstPkid").val();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"marketFirstPkid" : marketFirstPkid,
			"reqUrl" : "reservationMarketSecond",
			"reqMethod" : "queryMarketSecondTestList",
			"state":"1",
			"start" : start,
			"end" : end
		};
		
		try {
			var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
			component.paging.setTotalPage(page.totalRecord);
			component.paging.refresh(start, end);
		} catch (e) {
			alert(4);
		}
		
	},
    
	/**
	 * 测试
	 * @param pkid
	 * @param flag
	 */
	testMarketSecond : function(pkid,flag){
		var testState='2';
		if(flag){
			testState='1';
		}
		
		if(testState == '2')
		{
			component.openTestPanel();
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "reservationMarketSecond",
                            "reqMethod": "updateTestState",
                            "marketSecondPkId": pkid,
                            "testOnlineState":testState
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	
	/**
	 * 测试意见输入框
	 */
	openTestPanel:function(level){
		var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
	    var size={"height":"60","width":"400"};
	    top.UOMPDialog.showSubPage("二级预约营销案-测试", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_second/test/testContent.jsp?level="+level+"&marketPkId="+pkid,null,size);
	}
}