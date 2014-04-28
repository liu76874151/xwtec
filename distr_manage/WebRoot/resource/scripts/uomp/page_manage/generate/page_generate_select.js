$(document).ready(function () {
    component.initBusiGrid();
    component.initNonbusiGrid();
    component.queryGridData();
});

var component = {

    /**
     * 创建datagrid对象
     */
    initBusiGrid: function () {
        busi_datagrid = DhtmlxUtis.createGrid('gridbox_busi', {
            "header": "选择,业务编码,业务名称",
            "initWidth": "40,200,*",
            "colAlign": "center,left,left",
            "colTypes": "ch,ro,ro",
            "colSorting": "str,str,str",
            "colKeys": [" ", "busiNum", "busiName"]
        });
    },

    /**
     * 创建datagrid对象
     */
    initNonbusiGrid: function () {
        nonbusi_datagrid = DhtmlxUtis.createGrid('gridbox_nonbusi', {
            "header": "选择,非业务编码,非业务名称",
            "initWidth": "40,200,*",
            "colAlign": "center,left,left",
            "colTypes": "ch,ro,ro",
            "colSorting": "str,str,str",
            "colKeys": [" ", "nonbusiNum", "nonbusiName"]
        });
    },

    queryGridData: function () {
        var pageTmpNum = $("#pageTmpNum").val() == "" ? "null" : $("#pageTmpNum").val();
        var version = $("#version").val();
        var busi_data =
        {
            "pageTmpNum": pageTmpNum,
            "version": version,
            "reqUrl": "pageRelaBusiList",
            "reqMethod": "queryPageRelaBusiUsedList"
        };
        var nonbusi_data =
        {
            "pageTmpNum": pageTmpNum,
            "version": version,
            "reqUrl": "pageRelaNonbusiList",
            "reqMethod": "queryPageRelaNonbusiUsedList"
        };
        DhtmlxUtis.loadGirdData(busi_datagrid, busi_data, false);
        DhtmlxUtis.loadGirdData(nonbusi_datagrid, nonbusi_data, false);
    },

    generatePages: function () {
        if (!$('#generateForm').valid()) {
            return;
        }

        var data = this.constructData("pageGenerate", "generatePages");
        $.singleReq({
            data: data,
            success: function (ret) {
                if (ret.retCode == 0) {
                    UOMPComp.showSuccessDialog("生成成功！", "");
                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("生成失败！", "");
                }
            }
        });
    },

    constructData: function (reqUrl, reqMethod) {
        var pageTmpNum = $("#pageTmpNum").val();
        var version = $("#version").val();

        var busi_rows = busi_datagrid.getCheckedRows(0);
        var busiNums = "";
        if (busi_rows) {
            busi_rows = busi_rows.split(',');
            for (var i = 0; i < busi_rows.length; i++) {
                var busiNum = busi_datagrid.cells(busi_rows[i], 1).getValue();
                busiNums += busiNum + ",";
            }
            busiNums = busiNums.substring(0, busiNums.length - 1);
        }

        var nonbusi_rows = nonbusi_datagrid.getCheckedRows(0);
        var nonbusiNums = "";
        if (nonbusi_rows) {
            nonbusi_rows = nonbusi_rows.split(',');
            for (var i = 0; i < nonbusi_rows.length; i++) {
                var nonbusiNum = nonbusi_datagrid.cells(nonbusi_rows[i], 1).getValue();
                nonbusiNums += nonbusiNum + ",";
            }
            nonbusiNums = nonbusiNums.substring(0, nonbusiNums.length - 1);
        }

        if((busiNums == "" && busi_datagrid.getRowsNum() != 0) && nonbusiNums == "" && nonbusi_datagrid.getRowsNum() != 0){
            UOMPComp.showFailedDialog("请至少选择一个需要生成的关联业务或非业务！", "");
        }

        var data = {
            "reqUrl": reqUrl,
            "reqMethod": reqMethod,
            "pageTmpNum": pageTmpNum,
            "version": version,
            "busiNums": busiNums,
            "nonbusiNums":nonbusiNums
        }
        return data;
    }
}
