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
            "header": "序号,编码,名称,版本,步骤,备注",
            "initWidth": "60,200,200,120,100,*",
            "colAlign": "center,left,left,left,center,left",
            "colTypes": "ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,ro",
            "colKeys": ["pageletNum", "pageletName","version","step", "desc"]
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
                "title": "组件模板管理-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/component/pagelet_info_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },

            "view": {
                "title": "组件模板管理-查看",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/component/pagelet_info_view.jsp",
                "param": [
                    {"name": "pkid_1", "colIndex": "1"},
                    {"name": "pkid_2", "colIndex": "3"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "组件模板管理-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/component/pagelet_info_edit.jsp",
                "param": [
                    {"name": "pkid_1", "colIndex": "1"},
                    {"name": "pkid_2", "colIndex": "3"}
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
        var pageletNum = $("#pageletNum").val();
        var pageletName = $("#pageletName").val();
        var version = $("#version").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
            "pageletNum": pageletNum,
            "pageletName": pageletName,
            "version": version,
            "reqUrl": "pageletInfo",
            "reqMethod": "pagingQueryPageletInfoList",
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
        var pageletNum = datagrid.cells(selectRowId, 1).getValue();
        var version = datagrid.cells(selectRowId, 3).getValue();
        if (pageletNum && version) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "pageletInfo",
                            "reqMethod": "deletePageletInfo",
                            "pageletNum": pageletNum,
                            "version": version
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