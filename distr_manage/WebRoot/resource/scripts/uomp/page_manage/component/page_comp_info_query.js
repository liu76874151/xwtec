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
     */
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,编码,名称,类型,组件URL,关联区域,备注",
            "initWidth": "60,160,180,120,200,200,*",
            "colAlign": "center,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,str,ro",
            "colKeys": ["compNum", "compName",
                {
                    "key": "compType",
                    "formatter": function (v) {
                        if (v == "1") {
                            return "静态组件";
                        }
                        else if (v == "2") {
                            return "动态组件";
                        }
                        else if (v == "3") {
                            return "货架组件";
                        }
                        else if (v == "4") {
                            return "二次确认组件";
                        }
                        else if (v == "5") {
                            return "处理结果组件";
                        }
                    }
                },
                "compUrl", "containerId", "desc"]
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
                "title": "组件管理-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/component/page_comp_info_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },

            "view": {
                "title": "组件管理-查看",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/component/page_comp_info_view.jsp",
                "param": [
                    {"name": "pkid", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "组件管理-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/component/page_comp_info_edit.jsp",
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
        var compNum = $("#compNum").val();
        var compName = $("#compName").val();
        var compType = $("#compType").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
            "compNum": compNum,
            "compName": compName,
            "compType": compType,
            "reqUrl": "pageCompInfo",
            "reqMethod": "pagingQueryPageCompInfoList",
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
                            "reqUrl": "pageCompInfo",
                            "reqMethod": "deletePageCompInfo",
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