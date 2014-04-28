$(document).ready(function () {
        component.initValidate();
        component.initForm();
        component.initCompGrid();
        component.initBusiGrid();
        component.initNonbusiGrid();
        component.queryGridData();
    }
);

var component =
{
    //初始化校验规则
    initValidate: function () {
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
                pageTmpNum: {required: true, minlength: 1, maxlength: 20},
                pageTmpName: {required: true, minlength: 1, maxlength: 20},
                tmplatePath: {required: true, minlength: 1, maxlength: 200}
            },
            messages: {
                pageTmpNum: {required: "请输入页面模板编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                pageTmpName: {required: "请输入页面模板名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                tmplatePath: {required: "请输入模板路径", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
            }
        });
    },

    initForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "pageTmpInfo",
                "reqMethod": "findOnePageTmpInfo",
                "pageTmpNum": $("#pkid_1").val(),
                "version": $("#pkid_2").val()
            },
            success: function (ret) {
                var result = ret.retObj;
                $("#pageTmpNum").val(result.pageTmpNum);
                $("#pageTmpName").val(result.pageTmpName);
                $("#version").val(result.version);
                $("#tmplatePath").val(result.tmplatePath);
                $("#isRelaBusi").val(result.isRelaBusi);
                $("#desc").val(result.desc);
            }
        });
    },
    /**
     * 创建datagrid对象
     */
    initCompGrid: function () {
        datagrid_comp = DhtmlxUtis.createGrid('gridbox_comp', {
            "header": "选择,组件编码,组件名称,组件类型,组件容器ID",
            "initWidth": "40,160,200,100,*",
            "colAlign": "center,left,left,left,left",
            "colTypes": "ch,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str",
            "colKeys": ["pageTmpNum", "compNum", "compName",
                {
                    "key": "compType",
                    "formatter": function (v) {
                        if (v == "1") {
                            return "静态组件";
                        } else if (v == "2") {
                            return "动态组件";
                        } else if (v == "3") {
                            return "货架组件";
                        } else if (v == "4") {
                            return "二次确认组件";
                        } else if (v == "5") {
                            return "处理结果组件";
                        }
                    }
                }, "containerId"
            ]
        });
    },
    /**
     * 创建datagrid对象
     */
    initBusiGrid: function () {
        datagrid_busi = DhtmlxUtis.createGrid('gridbox_busi', {
            "header": "选择,业务编码,业务名称",
            "initWidth": "40,200,*",
            "colAlign": "center,left,left",
            "colTypes": "ch,ro,ro",
            "colSorting": "str,str,str",
            "colKeys": ["pageTmpNum", "busiNum", "busiName"]
        });
    },
    /**
     * 创建datagrid对象
     */
    initNonbusiGrid: function () {
        datagrid_nonbusi = DhtmlxUtis.createGrid('gridbox_nonbusi', {
            "header": "选择,非业务编码,非业务名称",
            "initWidth": "40,200,*",
            "colAlign": "center,left,left",
            "colTypes": "ch,ro,ro",
            "colSorting": "str,str,str",
            "colKeys": ["pageTmpNum", "nonbusiNum", "nonbusiName"]
        });
    },

    queryGridData: function () {
        var pageTmpNum = $("#pkid_1").val();
        var version = $("#pkid_2").val();
        var data_comp =
        {
            "pageTmpNum": pageTmpNum,
            "version": version,
            "reqUrl": "pageInCompList",
            "reqMethod": "queryPageInCompList"
        };
        var data_busi =
        {
            "pageTmpNum": pageTmpNum,
            "version": version,
            "reqUrl": "pageRelaBusiList",
            "reqMethod": "queryPageRelaBusiList"
        };
        var data_nonbusi =
        {
            "pageTmpNum": pageTmpNum,
            "version": version,
            "reqUrl": "pageRelaNonbusiList",
            "reqMethod": "queryPageRelaNonbusiList"
        };

        DhtmlxUtis.loadGirdData(datagrid_comp, data_comp, false);
        DhtmlxUtis.loadGirdData(datagrid_busi, data_busi, false);
        DhtmlxUtis.loadGirdData(datagrid_nonbusi, data_nonbusi, false);
    },

    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }

        var data = this.constructData("pageTmpInfo", "updatePageTmpInfo");
        $.singleReq({
            data: data,
            success: function (ret) {
                if (ret.retCode == 0) {
                    UOMPComp.showSuccessDialog("修改成功！", "");
                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
                }
            }
        });
    },

    constructData: function (reqUrl, reqMethod) {
        var pageTmpNum = $("#pageTmpNum").val();
        var pageTmpName = $("#pageTmpName").val();
        var version = $("#version").val();
        var tmplatePath = $("#tmplatePath").val();
        var isRelaBusi = $("#isRelaBusi").val();
        var type = $("#type").val();
        var desc = $("#desc").val();

        var rows_comp = datagrid_comp.getCheckedRows(0);
        var pageInCompList = [];
        if (rows_comp) {
            rows_comp = rows_comp.split(',');
            for (var i = 0; i < rows_comp.length; i++) {
                var compNum = datagrid_comp.cells(rows_comp [i], 1).getValue();
                pageInCompList[i] = {
                    "pageTmpNum": pageTmpNum,
                    "version": version,
                    "compNum": compNum,
                    "isopen": "0"
                };
            }
        }

        var rows_busi = datagrid_busi.getCheckedRows(0);
        var pageRelaBusiList = [];
        if (rows_busi) {
            rows_busi = rows_busi.split(',');
            for (var i = 0; i < rows_busi.length; i++) {
                var busiNum = datagrid_busi.cells(rows_busi[i], 1).getValue();
                pageRelaBusiList[i] = {
                    "pageTmpNum": pageTmpNum,
                    "version": version,
                    "busiNum": busiNum,
                    "isopen": "0"
                };
            }
        }

        var rows_nonbusi = datagrid_nonbusi.getCheckedRows(0);
        var pageRelaNonbusiList = [];
        if (rows_nonbusi) {
            rows_nonbusi = rows_nonbusi.split(',');
            for (var i = 0; i < rows_nonbusi.length; i++) {
                var nonbusiNum = datagrid_nonbusi.cells(rows_nonbusi[i], 1).getValue();
                pageRelaNonbusiList[i] = {
                    "pageTmpNum": pageTmpNum,
                    "version": version,
                    "nonbusiNum": nonbusiNum,
                    "isopen": "0"
                };
            }
        }

        var data = {
            "reqUrl": reqUrl,
            "reqMethod": reqMethod,
            "pageTmpNum": pageTmpNum,
            "pageTmpName": pageTmpName,
            "version": version,
            "tmplatePath": tmplatePath,
            "isRelaBusi": isRelaBusi,
            "type": type,
            "desc": desc,
            "pageInCompList": pageInCompList,
            "pageRelaBusiList": pageRelaBusiList,
            "pageRelaNonbusiList": pageRelaNonbusiList
        }
        return data;
    }
};




