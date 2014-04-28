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
			"header" : "序号,属性标识,属性名称,属性类别,属性描述,操作",
			"initWidth" : "40,80,80,80,550,*",
			"colAlign" : "center,left,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro,ro,ro",
			"colSorting" : "str,str,str,str,str,str",
			"colKeys" : [ "attrKey","attrName",
						{
			                "key": "attrType",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "字符串";
			                    }
			                    else if (v == "1") {
			                        return "数值";
			                    }else if (v == "1") {
			                        return "日期";
			                    }
			                }
			            },"attrDesc","modify" ]
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
                "title": "业务扩展属性-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/businessExtra/businessExtra_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },
            "modify": {
                "title": "业务扩展属性-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/business/businessExtra/businessExtra_modify.jsp",
                "param": [
                    {"name": "attrKey", "colIndex": "1"}
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
		var attrName = $("#attrName").val();
		var attrKey = $("#attrKey").val().toUpperCase();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"attrName" : attrName,
			"attrKey" : attrKey,
			"reqUrl" : "businessHandler",
			"reqMethod" : "queryBusinessExtra",
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
        var attrKey = datagrid.cells(selectRowId, 1).getValue();
        if (attrKey) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "businessHandler",
                            "reqMethod": "deleteBusiExtra",
                            "attrKey": attrKey
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
                                        var result = ret.retObj;
                                        var busiNums =  new Array();
                                        for (var index = 0; index < result.length; index++) {
                                        	var busiNum = result[index].busiNum;
                                        	busiNums[index]=busiNum;
                                        }
                                        UOMPComp.showFailedDialog("有业务("+busiNums.toString()+")关联到将要删除的属性,需要先取消关联!", "");
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
    updateBusiExtra : function(attrKey){
		var size = {"height":"300","width":"1000"};
		var url = GLOBAL_INFO.CONTEXTPATH + "/page/business/businessExtra/businessExtra_modify.jsp?attrKey="+attrKey;
		top.UOMPDialog.showSubPage("业务扩展属性修改",url,null,size);
    }
}