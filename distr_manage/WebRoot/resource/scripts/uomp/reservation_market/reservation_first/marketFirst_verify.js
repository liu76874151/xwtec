$().ready(function() {
	var myCalendar = new dhtmlXCalendarObject( [ "beginTime", "endTime" ]);
	component.initDataGrid();// 初始化dataGrid
	component.areaInfo();
});


var component = {
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,pkid,渠道,地市,一级方案名称,活动对象,开始时间,结束时间,配置时间,配置工号,关联二级,列表展示状态,线上测试,地市审核状态,省级审核状态",
			"initWidth" : "60,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,*",
			"colAlign" : "center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,link",
			"colSorting" : "str,str,str,na,str,str,str,str,str,str,str,str,str,str,str,str,str",
			"colKeys" : [ "marketFirstPkid",{
				"key": "channalData",
                "formatter": function (v) {
                	return v.replace(/4/, "网厅").replace(/5/, "掌厅").replace(/6/, "短厅");
                }
},"city","viewName","toObject","beginTime","endTime","createTime","cfgUserId","linkOper",
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
		datagrid.setColumnHidden(1,true);
		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
	},
	/**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid);
    },
	/**
	 * 查询
	 */
	query : function(start, end) {
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		var city = $("#city option:selected[value!='-请选择-']").val();
		var verifyState = $("#verifyState option:selected[value!='-请选择-']").val();
		var isListView = $("#isListView option:selected[value!='-请选择-']").val();
		var channalData = $("#channalData option:selected[value!='-请选择-']").val();
		
		var viewName = $("#viewName").val();
		
		
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"reqUrl" : "reservationFirst",
			"reqMethod" : "queryMarketFirstAuditList",
			"state":"1",
			"city":city,
			"isListView":isListView,
			"verifyState":verifyState,
			"viewName":viewName,
			"beginTime":beginTime,
			"endTime":endTime,
			"channalData":channalData,
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
	 * 省级审核
	 * @param pkid
	 * @param flag
	 */
	verifyMarketFirstPro : function(pkid,flag){
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
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "reservationFirst",
                            "reqMethod": "updateAuditState",
                            "marketFirstPkid": pkid,
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
	 * 市级审核
	 * @param pkid
	 * @param flag
	 */
	verifyMarketFirstCity : function(pkid,flag){
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
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "reservationFirst",
                            "reqMethod": "updateAuditState",
                            "marketFirstPkid": pkid,
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
	 * 审核框
	 */
	openAuditPanel:function(level){
		var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
	    var size={"height":"60","width":"400"};
	    top.UOMPDialog.showSubPage("一级预约营销案-审核", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_first/verify/auditContent.jsp?level="+level+"&marketPkId="+pkid,null,size);
	},
	areaInfo : function(){
		$.singleReq({
	         data: {
	             "reqUrl": "area",
	             "reqMethod": "queryAreaDAList"
	         },
	         success: function (ret) {
	             if (ret.retCode == 0) {
	                var result = ret.retObj.records; 
	                for (var index = 0; index < result.length; index++) {
	                	var option = $("<option>");
	               		option.val(result[index].bossCode);
	               		option.text(result[index].areaName);
	                	$("#city").append(option);
	                }
	             }
	         }
     });
	},
	previewMarketSecond : function(pkid,tchannal){
		window.open ('../marketSecond_preview.jsp?marketSecondPkid='+pkid+"&tchannal="+tchannal,'previewMarketSecondWin','fullscreen=yes,top=100,left=100,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	},
	/**
	 * 关联二级营销案
	 */
	viewSecond:function(marketFirstPkid,marketFirstCode,marketFirstName,cityId,tchannal){
		
		

    	var size={"height":"300","width":"1000"};
    	top.UOMPDialog.showSubPage("关联的二级预约营销案", GLOBAL_INFO.CONTEXTPATH +
    			"/page/reservation_market/reservation_second/verify/query.jsp?marketFirstPkid="+
    			marketFirstPkid+"&marketFirstCode="+marketFirstCode,null,size);
    
	},
}