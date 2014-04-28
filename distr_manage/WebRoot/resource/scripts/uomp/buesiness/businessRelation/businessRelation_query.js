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
			"header" : "序号,渠道,开始时间,结束时间,业务名称,业务编码,业务状态,资费类别,办理类别",
			"initWidth" : "40,40,80,80,80,80,80,80,80,*",
			"colAlign" : "center,left,left,left,left,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro",
			"colSorting" : "str,str,str,str,str,str,str,str,str,str",
			"colKeys" : [ {
				"key": "channelNum",
			                "formatter": function (v) {
			                    if (v == "01") {
			                        return "网厅";
			                    }
			                    else if (v == "02") {
			                        return "掌厅";
			                    }else if (v == "03") {
			                        return "短厅";
			                    }
			                }
			},"startTime","endTime","busiName","busiNum",{
				"key": "state",
			                "formatter": function (v) {//业务状态（0-正常；1-已删除；2-未审核; 3-审核未通过;4,待测试,5,测试不通过）
			                    if (v == "0") {
			                        return "正常";
			                    }
			                    else if (v == "1") {
			                        return "已删除";
			                    }else if (v == "2") {
			                        return "未审核";
			                    }else if (v == "3") {
			                        return "审核未通过";
			                    }else if (v == "4") {
			                        return "待测试";
			                    }else if (v == "5") {
			                        return "测试不通过";
			                    }
			                }
			},{
				"key": "feeType",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "免费";
			                    }
			                    else if (v == "1") {
			                        return "包月";
			                    }else if (v == "2") {
			                        return "包年";
			                    }else if (v == "3") {
			                        return "单次";
			                    }else if (v == "4") {
			                        return "按数量计费";
			                    }else if (v == "5") {
			                        return "其他";
			                    }
			                }
			},{
				"key": "processType",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "查询";
			                    }
			                    else if (v == "1") {
			                        return "开关";
			                    }else if (v == "2") {
			                        return "其他";
			                    }
			                }
			} ]
		});

		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
	},
	/**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {});
    },
	/**
	 * 查询
	 */
	query : function(start, end) {
		var busiNum = $("#busiNum").val();
		var channelNum = $("#channelNum").val();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"busiNum" : busiNum,
			"channelNum" : channelNum,
			"reqUrl" : "businessHandler",
			"reqMethod" : "queryRelaBusiDetail",
			"start" : start,
			"end" : end
		};
		var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
		component.paging.setTotalPage(page.totalRecord);
		component.paging.refresh(start, end);
	}
}