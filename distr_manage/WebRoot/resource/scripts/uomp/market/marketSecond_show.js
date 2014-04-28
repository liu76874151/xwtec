var marketFirstPkid = '';
var cityId = '';
var tchannal = '';
var marketFirstName = '';
var marketFirstCode = '';
var brand = '';
var cityName='';

$().ready(function() {
	marketFirstPkid = $("#marketFirstPkid").val();
	cityId = $("#cityId").val();
	tchannal = $("#tchannal").val();
	marketFirstName = $("#marketFirstName").val();
	cityName = $("#cityName").val();
	marketFirstCode = $("#marketFirstCode").val();
	brand = $("#brand").val();
	
	component.initDataGrid();// 初始化dataGrid
	component.initToolBar();
});


var component = {
		   /**
	     * 更新列表展示状态
	     */
	    updateListView:function(pkid,isListView){
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变列表展示状态吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "marketSecond",
	                            "reqMethod": "updateIsViewListState",
	                            "marketSecondPkid": pkid,
	                            "isListView":isListView
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
	    },
	    dtSync:function(){
	    	$.singleReq({
	    		data:
		        {
		        		"reqUrl": "marketFirst",
		                "reqMethod": "syncDT"
		        },
		        success: function (ret) {
		        	if (ret) {
	                    if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
	                        var resMsg = ret.resMsg;
	                        var retCode = ret.retCode;
	                        UOMPComp.showSuccessDialog(resMsg, "");
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
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,pkid,city,渠道,marketSecondCode,所属地市,二级方案名称,BOSS二级方案名称,活动对象,开始时间,结束时间,配置时间,配置工号,列表展示状态,审核状态",
			"initWidth" : "80,80,80,120,80,80,120,120,80,80,80,80,80,80,*",
			"colAlign" : "center,left,left,left,left,left,left,left,left,left,left,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,ro",
			"colSorting" : "str,str,str,str,str,str,str,str,str,str,str,str,str,str,str",
			"colKeys" : [ "marketSecondPkid","city","channalData","marketSecondCode","cityName","viewName","marketSecondName","toObject","beginTime","endTime","createTime","cfgUserId","isListViewLink",
			            {
			                "key": "verifyState",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "待审核";
			                    }
			                    else if (v == "1") {
			                        return "审核通过";
			                    } else if (v == "2") {
			                        return "审核不通过";
			                    } else if (v == "3") {
			                        return "待测试";
			                    }else if (v == "3") {
			                        return "测试不通过";
			                    }
			                }
			            }
			              ]
		});
		datagrid.setColumnHidden(1,true);
		datagrid.setColumnHidden(2,true);
		datagrid.setColumnHidden(0,true);
		datagrid.setColumnHidden(3,true);
		datagrid.setColumnHidden(4,true);
		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
	},
	/**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "二级营销案-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/market/marketSecond/marketSecond_add.jsp?marketFirstPkid="+marketFirstPkid
                +"&cityId="+cityId+"&tchannal="+tchannal+"&marketFirstName="+encodeURI(encodeURI(marketFirstName))+"&marketFirstCode="+marketFirstCode+"&brand="+brand+"&cityName="+encodeURI(encodeURI(cityName)),
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "二级营销案-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/market/marketSecond/marketSecond_edit.jsp?marketFirstchannal="+tchannal+"&marketFirstCode="+marketFirstCode+"&marketFirstPkid="+marketFirstPkid+"&brand="+brand+"&cityName="+encodeURI(encodeURI(cityName)),
                "param": [
                    {"name": "marketSecondPkid", "colIndex": "1"},
                    {"name": "tchannal", "colIndex": "3"}
                ],
                "callback": function () {
                  window.location.reload();
                }
            },

            "delete": {
                "url": "",
                "click": this.delete
            },
            "sync":{
            	"title":"同步短厅",
            	"click":this.dtSync
            }
        });
    },
	/**
	 * 查询
	 */
	query : function(start, end) {
		//var marketFirstPkid = $("#marketFirstPkid").val();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"marketFirstPkid" : marketFirstPkid,
			"reqUrl" : "marketSecond",
			"reqMethod" : "queryMarketSecondList",
			"state":"1",
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
     * 删除
     */
    delete: function () {
        var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
        var city = datagrid.cells(selectRowId, 2).getValue();
        var channel = datagrid.cells(selectRowId, 3).getValue();
        var marketSecondCode = datagrid.cells(selectRowId, 4).getValue();
        
        if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketSecond",
                            "reqMethod": "deleteMarketSecondByPkid",
                            "marketSecondPkid": pkid,
                            "marketSecondCode": marketSecondCode,
                            "marketFirstPkid": marketFirstPkid,
                            "marketFirstCode": marketFirstCode,
                            "city": city,
                            "channalData": channel,
                            "state":'0'
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
	showMarketSecondWin : function(pkid,tchannal){
		window.open ('marketSecond_edit.jsp?marketSecondPkid='+pkid+"&tchannal="+tchannal+"&marketFirstName="+encodeURI(encodeURI(marketFirstName)),'showMarketSecondWin','fullscreen=yes,top=100,left=100,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
	}
}