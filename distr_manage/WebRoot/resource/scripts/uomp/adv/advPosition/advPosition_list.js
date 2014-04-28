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
        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变状态吗？点击“取消”则返回！", {
            "yes": function () {
                $.singleReq({
                    data: {
                        "reqUrl": "advPosition",
                        "reqMethod": "updateByPrimaryKeySelective",
                        "positionNum": pkid,
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
      //  UOMPComp.showTipDialog("请选择行", "");
    }
	
	    	
	    },

    /**
     * 创建datagrid对象
     */
		
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {

            "header": "序号,广告信息编码,渠道,广告位名称,广告位类型,状态",
            "initWidth": "80,140,140,*,140,140",
            "colAlign": "center,center,center,left,center,center",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,link",

            "colKeys": ["positionNum", {
                "key": "channelNum",
                "formatter": function (v) {
            	var chanNum=["01","02","0201","0202","0203","0204","03"];
            	var chanName=["网厅","掌厅","掌厅普版","掌厅标准版","掌厅触屏版","掌厅APP","短厅"];
            	var index=$.inArray(v, chanNum);
                 return chanName[index];
                }
            },"positionName",
            
                {
                    "key": "positionType",
                    "formatter": function (v) {
                        if (v == "1") {
                            return "多图";
                        }
                        else if (v == "2") {
                            return "单图";
                        }
                        else if (v == "3") {
                            return "文字";
                        }else{
                        	return "";
                        }
                       
                    }
                }, "stateLink" 
               ]
        });
        //datagrid.setColumnHidden(1,true);
        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
    },

    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "广告位信息-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/adv/advPosition/advPostion_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },
            "modify": {
                "title": "广告位信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/adv/advPosition/advPosition_edit.jsp",
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
        var positionNum = $("#positionNum").val();
        var positionName = $("#positionName").val();
        var state = $("#state").find("option:selected").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
        	"positionName":positionName,
        	"positionNum":positionNum,
            "reqUrl": "advPosition",
            "reqMethod": "queryAdvPositionList",
            "state":state,
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
                            "reqUrl": "advPosition",
                            "reqMethod": "deleteAdvPosition",
                            "pkid": pkid
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