$().ready(function() {
	component.initDataGrid();// 初始化dataGrid
	component.initToolBar();
	});
function reloadData(){
   	 window.location.reload();
}
var component = {
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,扣费方式编码,扣费方式名称,操作",
			"initWidth" : "40,80,550,*",
			"colAlign" : "center,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro",
			"colSorting" : "str,str,str,str",
			"colKeys" : [ "deductNum","deductWay","modify" ]
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
                "title": "业务扣费方式-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/businessDeduct/businessDeduct_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },
            "modify": {
                "title": "业务扣费方式-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/businessDeduct/businessDeduct_modify.jsp",
                "param": [
                    {"name": "deductNum", "colIndex": "1"}
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
	query : function(start, end) {
		var deductWay = $("#deductWay").val();
		var deductNum = $("#deductNum").val().toUpperCase();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"deductWay" : deductWay,
			"deductNum" : deductNum,
			"reqUrl" : "businessHandler",
			"reqMethod" : "queryBusinessDeduct",
			"start" : start,
			"end" : end
		};
		var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
		component.paging.setTotalPage(page.totalRecord);
		component.paging.refresh(start, end);
	},
    
    /**
     * 删除品牌
     */
    delete: function () {
        var selectRowId = datagrid.getSelectedRowId();
        var deductNum = datagrid.cells(selectRowId, 1).getValue();
        if (deductNum) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "businessHandler",
                            "reqMethod": "deleteBusiDeduct",
                            "deductNum": deductNum
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog("删除成功!", "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                    	alert(12);
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
    },
    updateBusiDeduct : function(deductNum){
		var size = {"height":"300","width":"1000"};
		var url = GLOBAL_INFO.CONTEXTPATH + "/page/business/businessDeduct/businessDeduct_modify.jsp?deductNum="+deductNum;
		top.UOMPDialog.showSubPage("业务扣费方式修改",url,null,size);
    }
}