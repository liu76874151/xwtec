$().ready(function() {
	component.initDataGrid();// 初始化dataGrid
	component.initToolBar();
	});

var component = {
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,业务编码,业务名称,业务图标路径,业务宣传语,业务状态",
			"initWidth" : "80,80,80,300,80,*",
			"colAlign" : "center,center,center,center,center,center",
			"colTypes" : "ro,ro,ro,ro,ro,ro",
			"colSorting" : "str,str,str,str,str,str",
			"colKeys" : [ "busiNum","busiName","busiIcon","busiExpl","state" ]
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
                "title": "业务信息-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/businessinfo/businessInfo_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },

            "view": {
                "title": "业务信息-查看",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/businessinfo/businessInfo_view.jsp",
                "param": [
                    {"name": "pkid", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "业务信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/businessinfo/businessInfo_modify.jsp",
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
		var busiName = $("#busi_name").val();
		var busisNum = $("#busi_num").val();
		var busiNameJianpin = $("#busi_name_jp").val();
		var state = $("#busi_state").val();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"busiName" : busiName,
			"busisNum" : busisNum,
			"busiNameJianpin" : busiNameJianpin,
			"state" : state,
			"reqUrl" : "businessInfoHandler",
			"reqMethod" : "queryBusiInfoList",
			"start" : start,
			"end" : end
		};
		
		try {
			var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
			component.paging.setTotalPage(page.totalRecord);
			component.paging.refresh(start, end);
		} catch (e) {
			alert(4);
		}
		
	},
    
    /**
     * 删除品牌
     */
    delete: function () {
        var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
        if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“否”则返回！", {
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