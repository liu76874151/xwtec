var marketFirstPkid = '';
var cityId = '';
var tchannal = '';
var marketFirstName = '';
var marketFirstCode = '';
var brand = '';
var cityName = '';

$().ready(function() {
	marketFirstPkid = $("#marketFirstPkid").val();
	cityId = $("#cityId").val();
	tchannal = $("#tchannal").val();
	marketFirstName = $("#marketFirstName").val();
	marketFirstCode = $("#marketFirstCode").val();
	brand = $("#brand").val();
	
	component.initDataGrid();// 初始化dataGrid
	component.initToolBar();
});


var component = {
		
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,pkid,渠道,地市,二级方案名称,BOSS二级方案名称,活动对象,开始时间,结束时间,配置时间,配置工号,列表展示状态,线上测试状态,地市审核状态,省级审核状态",
			"initWidth" : "0,0,40,80,80,0,80,80,80,80,80,80,80,80,*",
			"colAlign" : "center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,link",
			"colSorting" : "str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str",
			"colKeys" : [ "marketSecondPkid",{
				"key": "channalData",
			                "formatter": function (v) {
			                	return v.replace(/4/, "网厅").replace(/5/, "掌厅").replace(/6/, "短厅");
			                }
			},"city","viewName","marketSecondName","toObject","beginTime","endTime","createTime","cfgUserId",
			              {
			                "key": "isListView",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "不展示";
			                    }
			                    else if (v == "1") {
			                        return "展示";
			                    }
			                }
			            },
			            'testOper','localOper','proOper'
			              ]
		});
		datagrid.setColumnHidden(1,true);
		datagrid.setColumnHidden(5,true);
		datagrid.setColumnHidden(0,true);
		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
	},
	/**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
         

            "view": {
                "title": "二级营销案-查看",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/market/marketSecond/marketSecond_edit.jsp?marketFirstchannal="+tchannal+"&marketFirstCode="+marketFirstCode+"&marketFirstPkid="+marketFirstPkid+"&brand="+brand+"&cityName="+encodeURI(encodeURI(cityName))+"&isViewPage=1",
                "param": [
                    {"name": "marketSecondPkid", "colIndex": "1"},
                    {"name": "tchannal", "colIndex": "3"}
                ],
                "callback": function () {
                  window.location.reload();
                }
            }
        });
    },
	verifyMarketSecondCity : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanel("local");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketSecond",
                            "reqMethod": "updateMarketSecondForVerify",
                            "marketSecondPkid": pkid,
                            "localVerifyState":verify
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
        }
	},
	/**
	 * 省级审核
	 */
	verifyMarketSecondPro : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanel("pro");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketSecond",
                            "reqMethod": "updateMarketSecondForVerify",
                            "marketSecondPkid": pkid,
                            "proVerifyState":verify
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
        }
	},
	/**
	 * 审核框
	 */
	openAuditPanel:function(level){
		var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
	    var size={"height":"60","width":"400"};
	    top.UOMPDialog.showSubPage("二级营销案-审核", GLOBAL_INFO.CONTEXTPATH + "/page/market/marketSecond/verify/auditContent.jsp?level="+level+"&marketPkId="+pkid,null,size);
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
			"reqMethod" : "queryMarketSecondForVerify",
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
    
 
	showMarketSecondWin : function(pkid,tchannal){
		window.open ('marketSecond_edit.jsp?marketSecondPkid='+pkid+"&tchannal="+tchannal+"&marketFirstName="+encodeURI(encodeURI(marketFirstName)),'showMarketSecondWin','fullscreen=yes,top=100,left=100,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
	}
}