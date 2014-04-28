$(document).ready(function () {
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
            "header": "序号,地市编码,地市名称,地市级别编码,备注",
            "initWidth": "80,80,120,120,*",
            "colAlign": "center,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str",
            "colKeys": ["areaNum", "areaName",
              "areaJbNum", "areaBz"]
        });

        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
    },

    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "地市信息-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/area/add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },




            "modify": {
                "title": "地市信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/area/edit.jsp",
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
        var areaName = $("#areaName").val();
        var areaNum = $("#areaNum").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
            "areaName": areaName,
            "areaNum": areaNum,
            "reqUrl": "area",
            "reqMethod": "queryAreaList",
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
                            "reqUrl": "area",
                            "reqMethod": "deleteArea",
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