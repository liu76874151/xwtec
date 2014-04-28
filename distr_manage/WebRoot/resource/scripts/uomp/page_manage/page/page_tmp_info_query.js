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
            "header": "序号,编码,名称,版本,模板路径,是否关联业务,类型,备注",
            "initWidth": "60,160,220,60,160,100,80,*",
            "colAlign": "center,left,left,center,left,center,center,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,str,str,ro",
            "colKeys": ["pageTmpNum", "pageTmpName","version","tmplatePath",
                {
                    "key": "isRelaBusi",
                    "formatter": function (v) {
                        if (v == "0") {
                            return "否";
                        }
                        else if (v == "1") {
                            return "是";
                        }
                    }
                },
                {
                    "key": "type",
                    "formatter": function (v) {
                        if (v == "0") {
                            return "普通";
                        }
                        else if (v == "1") {
                            return "模板";
                        }
                    }
                },"desc"]
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
                "title": "页面模板管理-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/page/page_tmp_info_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },

            "view": {
                "title": "页面模板管理-查看",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/page/page_tmp_info_view.jsp",
                "param": [
                    {"name": "pkid_1", "colIndex": "1"},
                    {"name": "pkid_2", "colIndex": "3"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "页面模板管理-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/page/page_tmp_info_edit.jsp",
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
        var pageTmpNum = $("#pageTmpNum").val();
        var pageTmpName = $("#pageTmpName").val();
        var version = $("#version").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
            "pageTmpNum": pageTmpNum,
            "pageTmpName": pageTmpName,
            "version": version,
            "reqUrl": "pageTmpInfo",
            "reqMethod": "pagingQueryPageTmpInfoList",
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
        var pageTmpNum = datagrid.cells(selectRowId, 1).getValue();
        var version = datagrid.cells(selectRowId, 3).getValue();
        if (pageTmpNum && version) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "pageTmpInfo",
                            "reqMethod": "deletePageTmpInfo",
                            "pageTmpNum": pageTmpNum,
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