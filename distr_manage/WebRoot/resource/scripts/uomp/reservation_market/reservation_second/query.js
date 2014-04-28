var marketFirstPkid;
var marketFirstCode;
var marketFirstName;
$(document).ready(function () {
	marketFirstPkid = $("#marketFirstPkid").val();
    marketFirstCode = $("#marketFirstCode").val();
    marketFirstName = $("#marketFirstName").val();
    component.initDataGrid();
    component.initToolBar();
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
	            "header": "序号,marketSecondPkidHidden,二级预约方案名称,活动对象,开始时间,结束时间,配置时间,配置工号,列表展示状态,审核状态,地市,列表展示状态码",
	            "initWidth": "80,80,*,120,80,80,80,80,120,80,80,0",
	            "colAlign": "center,left,left,left,left,left,left,left,left,left,left,left",
	            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,link,ro,ro,ro",
	            "colSorting": "int,str,str,str,str,str,str,str,str,str,str,str",
	            "colKeys": ["marketSecondPkId","viewName", "toObject",
	              "beginTime","endTime","createTime","cfgUserId","linkOper","verifyState","city","isListView"]
	        });
	        datagrid.setColumnHidden(1,true);
	        datagrid.setColumnHidden(11,true);
	        this.paging = new dhtmlxGridPaging('paging');
	        this.paging.initPaging(component.query);
	    },
	    /**
		 * 创建toolBar对象
		 */
	    initToolBar: function () {
	        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
	            "add": {
	                "title": "二级预约营销案-新增",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_second/add.jsp?marketFirstPkid="+marketFirstPkid+"&remarketFirstCode="+marketFirstCode+"&remarketFirstName="+marketFirstName,
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "modify": {
	            	"title": "二级预约营销案-修改",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_second/edit.jsp?marketFirstName="+marketFirstName,
	                "param": [
	                    {"name": "marketSecondPkid", "colIndex": "1"}
	                ],
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "delete": {
	                "url": "",
	          "click": this.delete
	            }
	        });
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
	        		"reqUrl": "reservationMarketSecond",
	                "reqMethod": "queryReservationMarketSecondList",
	                "marketFirstPkid": marketFirstPkid,
	                "start": start,
	                "end": end
	        };

	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    },
	    /**
	     * 删除
	     */
	    delete: function () {
	        var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 1).getValue();
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "reservationMarketSecond",
	                            "reqMethod": "deleteReservationMarketSecond",
	                            "marketSecondPkid": pkid
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
	        } else {
	            UOMPComp.showTipDialog("请选择行", "");
	        }
	    },
	    /**
	     * 更新列表展示状态
	     */
	    updateListView:function(){
	    	var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 1).getValue();
	        var listViewStateCode = datagrid.cells(selectRowId, 11).getValue();
	        //改变展示状态
	        if(listViewStateCode == "1"){
	        	listViewStateCode = "0";
	        }
	        else{
	        	listViewStateCode = "1";
	        }
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变列表展示状态吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "reservationMarketSecond",
	                            "reqMethod": "updateIsViewListState",
	                            "marketSecondPkId": pkid,
	                            "isListView":listViewStateCode
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
	        } else {
	            UOMPComp.showTipDialog("请选择行", "");
	        }
	    }
}