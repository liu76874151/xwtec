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
            "header": "序号,姓名,年龄,性别,籍贯,联系电话,家庭住址",
            "initWidth": "80,80,120,120,120,80,*",
            "colAlign": "center,center,center,center,center,center,center",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,str,ro",
            "colKeys": ["stuName", "stuAge",
                {
                    "key": "stuSex",
                    "formatter": function (v) {
                        if (v == "0") {
                            return "男";
                        }
                        else if (v == "1") {
                            return "女";
                        }
                    }
                },
                {
                    "key": "stuNative",
                    "formatter": function (v) {
                        if (v == "0") {
                            return "北京";
                        }
                        else if (v == "1") {
                            return "上海";
                        }
                        else if (v == "2") {
                            return "江苏";
                        }
                        else if (v == "3") {
                            return "浙江";
                        }
                    }
                }, "stuTel", "stuAddr"]
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
                "title": "学生信息-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/demo/student/add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },

            "view": {
                "title": "学生信息-查看",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/demo/student/view.jsp",
                "param": [
                    {"name": "pkid", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "学生信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/demo/student/edit.jsp",
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
        var stuName = $("#stuName").val();
        var stuAge = $("#stuAge").val();
        var stuSex = $("#stuSex").val();
        var stuNative = $("#stuNative").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
            "stuName": stuName,
            "stuAge": stuAge,
            "stuSex": stuSex,
            "stuNative": stuNative,
            "reqUrl": "student",
            "reqMethod": "queryStudentList",
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
                            "reqUrl": "student",
                            "reqMethod": "deleteStudent",
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