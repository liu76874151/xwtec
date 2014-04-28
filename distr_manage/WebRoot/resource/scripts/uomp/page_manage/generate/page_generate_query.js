$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
    component.query();
});

/**
 * 用户查询
 */
var component = {
    busi:{},

    /**
     * 创建datagrid对象
     */
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,编码,名称,版本,模板路径,是否关联业务,备注",
            "initWidth": "40,160,220,100,200,120,*",
            "colAlign": "center,left,left,left,left,center,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,str,ro",
            "colKeys": ["pageTmpNum", "pageTmpName",
                {
                    "key": "version",
                    "formatter": function (v) {
                        if (v == "2") {
                            return "标准版";
                        }
                        else if (v == "3") {
                            return "触屏版";
                        }
                    }
                },
                "tmplatePath",
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
                }, "desc"]
        });
        datagrid.attachEvent("onRowDblClicked", function (rId, cInd) {
            var selectRowId = datagrid.getSelectedRowId();
            var pageTmpNum = datagrid.cells(selectRowId, 1).getValue();
            var version = datagrid.cells(selectRowId, 3).getValue();

            if (version == "标准版") {
                version = "2";
            } else if (version == "触屏版") {
                version = "3";
            }

            var title = "关联业务";
            var url = GLOBAL_INFO.CONTEXTPATH + "/page/page_manage/generate/page_generate_select.jsp?pageTmpNum=" + pageTmpNum + "&version=" + version;
            top.UOMPDialog.showSubPage(title, url);
        })
    },

    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
//            "generateAll": {
//                "title": "页面模板管理-全部生成",
//                "click": this.generateAllPages
//            },
            "generateStaticAll": {
                "title": "页面模板管理-静态页面生成",
                "click": this.generateAllStaticPages
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
            end = 100;
        }
        var data =
        {
            "pageTmpNum": pageTmpNum,
            "pageTmpName": pageTmpName,
            "version": version,
            "reqUrl": "pageGenerate",
            "reqMethod": "queryPageTmpInfoListForGen"
        };
        DhtmlxUtis.loadGirdData(datagrid, data);
    },

    /**
     * 生成动态页面
     */
    generatePages: function () {
        var data = component.constructData("pageGenerate", "generatePages");
        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定生成这些页面吗？点击“取消”则返回！", {
            "yes": function () {
                $.singleReq({
                    data: data,
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
    },

    /**
     * 生成全部动态页面
     */
    generateAllPages: function () {
        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定生成所有页面吗？点击“取消”则返回！", {
            "yes": function () {
                $.singleReq({
                    data: {
                        "reqUrl": "pageGenerate",
                        "reqMethod": "generateAllPages"
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
    },

    /**
     * 生成全部静态页面
     */
    generateAllStaticPages: function () {
        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定生成所有静态页面吗？点击“取消”则返回！", {
            "yes": function () {
                $.singleReq({
                    data: {
                        "reqUrl": "pageGenerate",
                        "reqMethod": "generateAllStaticPages"
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
    },

    constructData: function (reqUrl, reqMethod) {
        var rows = datagrid.getCheckedRows(1);
        var pageTmpInfoList = [];
        if (rows) {
            rows = rows.split(',');
            for (var i = 0; i < rows.length; i++) {
                var pageTmpNum = datagrid.cells(rows[i], 2).getValue();
                var version = datagrid.cells(rows[i], 4).getValue();
                if (version == "普版") {
                    version = "1";
                } else if (version == "标准版") {
                    version = "2";
                } else if (version == "触屏版") {
                    version = "3";
                }
                pageTmpInfoList[i] = {
                    "pageTmpNum": pageTmpNum,
                    "version": version
                };
            }
        }

        var data = {
            "reqUrl": reqUrl,
            "reqMethod": reqMethod,
            "pageTmpInfo": pageTmpInfoList
        }
        return data;
    }
}