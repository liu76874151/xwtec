$().ready(function() {
	component.initDataGrid();// 初始化dataGrid
	component.initToolBar();
	});

var component = {
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		/*datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,#master_checkbox,品牌编码,品牌名称,备注",
			"initWidth" : "40,40,80,80,*",
			"colAlign" : "center,left,left,left,left",
			"colTypes" : "ro,ch,ro,ro,ro",
			"colSorting" : "str,na,str,str,str",
			"colKeys" : [ 'checkbox',"brandNum","brandName","memo" ]
		});*/
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,品牌编码,品牌名称,备注",
			"initWidth" : "40,80,80,*",
			"colAlign" : "center,left,left,left",
			"colTypes" : "ro,ro,ro,ro",
			"colSorting" : "str,str,str,str",
			"colKeys" : [ "brandNum","brandName","memo" ]
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
                "title": "品牌信息-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/brand/brand_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },

            "view": {
                "title": "品牌信息-查看",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/brand/brand_view.jsp",
                "param": [
                    {"name": "pkid", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "品牌信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/brand/brand_modify.jsp",
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
	query : function(start, end) {
		var brandName = $("#brand_Name").val();
		var brandNum = $("#brand_Num").val().toUpperCase();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"brandName" : brandName,
			"brandNum" : brandNum,
			"reqUrl" : "brand",
			"reqMethod" : "queryBrandList",
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
        var pkid = datagrid.cells(selectRowId, 1).getValue();
        if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "brand",
                            "reqMethod": "deleteBrandbyBrandNum",
                            "pkid": pkid
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog("品牌删除成功", "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog("品牌删除失败", "");
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