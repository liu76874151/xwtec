$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
//    component.init();
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
	            "header": "序号,planId,所属渠道,方案编号,状态",
	            "initWidth": "60,0,180,180,*,180",
	            "colAlign": "center,left,left,left",
	            "colTypes": "ro,ro,ro,ro,ro",
	            "colSorting": "int,str,str,str,str",
	            "colKeys": ["planId","channelName","planNum", 
	            {
                    "key": "state",
                    "formatter": function (v) {
                        if (v == "0") {
                            return "可用";
                        }
                        else if (v == "1") {
                            return "不可用";
                        }
                    }
                }]
	        });
	        datagrid.setColumnHidden(1,true);
	        this.paging = new dhtmlxGridPaging('paging');
	        this.paging.initPaging(component.query);
	    },
	    
	    /**
		 * 查询
		 */
	    query: function (start, end) {
	    	var floorName = $("#floorName").val();
	    	var state = $("#state").find("option:selected").val();
	    	var channelNum = $("#channelNum").find("option:selected").val();
	    	if (start == undefined) {
	            start = 0;
	        }
	        if (end == undefined) {
	            end = 10;
	        }
	        var data =
	        {
	        		"reqUrl": "floorPlan",
	                "reqMethod": "queryFloorPlan",
	                "state": state,
	                "channelNum":channelNum,
	                "start": start,
	                "end": end
	        };

	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    },
	    /**
	     * 更新启停状态
	     */
	    updateUserableState:function(pkid,state){
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变启用状态吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "floorTemplate",
	                            "reqMethod": "updateUseableState",
	                            "tempNum": pkid,
	                            "state":state
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
	     * 创建toolBar对象
	     */
	    initToolBar: function () {
	        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
	            "view": {
	                "title": "方案-查看",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/floor/plan/view.jsp",
	                "param": [
	  	                    {"name": "planId", "colIndex": "1"}
	  	                ],
	  	            "size": [{"height":"300","width":"2000"}],
	                "callback": function () {
	                    window.location.reload();
	                }
	            }
	        });
	    }
}