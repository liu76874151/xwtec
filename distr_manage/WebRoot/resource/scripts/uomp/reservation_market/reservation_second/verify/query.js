var marketFirstPkid;
var marketFirstCode;
var marketFirstName;
$(document).ready(function () {
	marketFirstPkid = $("#marketFirstPkid").val();
    marketFirstCode = $("#marketFirstCode").val();
    marketFirstName = $("#marketFirstName").val();
    component.initDataGrid();
   // component.initToolBar();
});

/**
 * 用户查询
 */
var component = {

    /**
	 * 创建datagrid对象
	 * 
	 */
		initDataGrid: function () {
	        datagrid = DhtmlxUtis.createGrid('gridbox', {
				"header" : "序号,pkid,渠道,地市,二级方案名称,BOSS二级方案名称,活动对象,开始时间,结束时间,配置时间,配置工号,列表展示状态,线上测试状态,地市审核状态,省级审核状态",
				"initWidth" : "0,0,80,80,80,80,80,80,80,80,80,80,80,80,*",
				"colAlign" : "center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left",
				"colTypes" : "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,link",
				"colSorting" : "str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str",
				"colKeys" : [ "marketSecondPkId",{
					"key": "channelData",
				                "formatter": function (v) {
				                	return v.replace(/4/, "网厅").replace(/5/, "掌厅").replace(/6/, "短厅");
				                }
				},"areaName","viewName","marketSecondName","toObject","beginTime","endTime","createTime","cfgUserId",
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
				            },
				            'testOper','localOper','proOper'
				              ]
			});
	        datagrid.setColumnHidden(1,true);
	        datagrid.setColumnHidden(5,true);
	        this.paging = new dhtmlxGridPaging('paging');
	        this.paging.initPaging(component.query);
	    },
		verifyMarketSecondCity : function(pkid,flag){
			var verify='2';
			if(flag){
				verify='1';
			}
			if(verify == '2')
			{
				component.openAuditPanel("local");
				return;
			}
			
			if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "reservationMarketSecond",
	                            "reqMethod": "updateMarketSecondForVerify",
	                            "marketSecondPkId": pkid,
	                            "localVerifyState":verify
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
		 * 省级审核
		 */
		verifyMarketSecondPro : function(pkid,flag){
			var verify='2';
			if(flag){
				verify='1';
			}
			if(verify == '2')
			{
				component.openAuditPanel("pro");
				return;
			}
			
			if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "reservationMarketSecond",
	                            "reqMethod": "updateMarketSecondForVerify",
	                            "marketSecondPkId": pkid,
	                            "proVerifyState":verify
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
		 * 审核框
		 */
		openAuditPanel:function(level){
			
			var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 1).getValue();
		    var size={"height":"60","width":"400"};
		    top.UOMPDialog.showSubPage("二级预约营销案-审核", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_second/verify/auditContent.jsp?marketPkId="+pkid+"&level="+level,null,size);
		},
	    
	    /**
		 * 查询
		 */
	    query: function (start, end) {
	    	var state=1;
	    	if (start == undefined) {
	            start = 0;
	        }
	        if (end == undefined) {
	            end = 10;
	        }
	        var marketFirstPkid = $("#marketFirstPkid").val();
	        var data =
	        {
	        		"reqUrl" : "reservationMarketSecond",
	    			"reqMethod" : "queryMarketSecondForVerify",
	    			"state":"1",
	                "marketFirstPkid": marketFirstPkid,
	                "start": start,
	                "end": end
	        };

	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    }
	
}