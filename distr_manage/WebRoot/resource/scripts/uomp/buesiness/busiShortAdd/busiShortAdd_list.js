$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
});

/**
 * 广告位查询
 */
var component = {
		
		
  updateState:function(state){

    var selectRowId = datagrid.getSelectedRowId();
    var pkid = datagrid.cells(selectRowId, 1).getValue();
    if (pkid) {
        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变状态吗？点击“否”则返回！", {
            "yes": function () {
                $.singleReq({
                    data: {
                        "reqUrl": "businessHandler",
                        "reqMethod": "updateBusiShortAddState",
                        "shortId": pkid,
                        "state":state
                    },
                    success: function (ret) {
                        if (ret) {
                            if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                var resMsg = ret.resMsg;
                                var retCode = ret.retCode;
                                UOMPComp.showSuccessDialog("修改成功!", "");
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
      //  UOMPComp.showTipDialog("请选择行", "");
    }
	
	    	
	    },

    /**
     * 创建datagrid对象
     */
		
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,shortId,state,业务编码,渠道,业务短地址标识,短地址渠道标识,状态,开始时间,结束时间",
            "initWidth": "40,0,0,80,100,100,100,80,160,*",
            "colAlign": "center,left,left,left,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro,link,ro,ro",
            "colSorting": "str,str,str,str,str,str,str,str,str,str",
            "colKeys": ["shortId", "state","busiNum",{
                "key": "channelNum",
                "formatter": function (v) {
            	var chanNum=["01","02","0201","0202","0203","0204","03"];
            	var chanName=["网厅","掌厅","掌厅普版","掌厅标准版","掌厅触屏版","掌厅APP","短厅"];
            	var index=$.inArray(v, chanNum);
                 return chanName[index];
                }
            },"shortBusi","shortChannel","stateLink", "startTime", "endTime"
               ]
        });
        datagrid.setColumnHidden(1,true);
        datagrid.setColumnHidden(2,true);
        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
    },

    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "短地址信息-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/busiShortAdd/busiShortAdd_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "短地址信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/busiShortAdd/busiShortAdd_edit.jsp",
                "param": [
                    {"name": "pkid", "colIndex": "1"}
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
        var shortBusi = $("#shortBusi").val();
        var busiNum = $("#busiNum").val();
        var state = $("#state").val();
        var channelNum = $("#channelNum").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
        	"shortBusi":shortBusi,
        	"busiNum":busiNum,
        	"state":state,
        	"channelNum":channelNum,
            "reqUrl": "businessHandler",
            "reqMethod": "queryBusiShortAdd",
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
        var state = datagrid.cells(selectRowId, 2).getValue();
        if (pkid&&state==1) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "businessHandler",
                            "reqMethod": "deleteBusiShortAdd",
                            "shortId": pkid
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
        	 if(state==0){  UOMPComp.showFailedDialog("请先停用短地址!", "");
        	 }else{
        		 UOMPComp.showTipDialog("请选择行", "");
        	 }
           
        }
    }
}