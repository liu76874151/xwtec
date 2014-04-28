$(document).ready(function () {
        component.viewForm();
        component.initCompGrid();
        component.initBusiGrid();
        component.queryGridData();
    }
);

var component = {
    viewForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "pageTmpInfo",
                "reqMethod": "findOnePageTmpInfo",
                "pageTmpNum": $("#pkid_1").val(),
                "version": $("#pkid_2").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#pageTmpNum").text(result.pageTmpNum);
                    $("#pageTmpName").text(result.pageTmpName);
                    $("#tmplatePath").text(result.tmplatePath);

                    var version = result.version;
                    if (version == "0") {
                        version = "支持所有";
                    } else if (version == "1") {
                        version = "支持普版";
                    } else if (version == "2") {
                        version = "支持标准版";
                    } else if (version == "3") {
                        version = "支持触屏版";
                    }
                    $("#version").text(version);

                    var isRelaBusi = result.isRelaBusi;
                    if (isRelaBusi == "0") {
                        isRelaBusi = "否";
                    } else if (isRelaBusi == "1") {
                        isRelaBusi = "是";
                    }
                    $("#isRelaBusi").text(isRelaBusi);
                    var type = result.type;
                    if (type == "0") {
                        type = "普通";
                    } else if (type == "1") {
                        type = "模板";
                    }
                    $("#type").text(type);
                    $("#desc").text(result.desc);
                }
                else {
                    alert(ret.retMsg);
                }
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

        DhtmlxUtis.loadGirdData(datagrid_comp, data_comp, false);
        DhtmlxUtis.loadGirdData(datagrid_busi, data_busi, false);
    }
}
