$(document).ready(function () {
   component.initDataGrid();
   component.init();
   component.initToolBar();
});


var component = {

	

		init :function(){
	 $.singleReq({
        data: {
            "reqUrl": "zoneInfo",
            "reqMethod": "queryBossAreaList"
        },
        success: function (ret) {
            if (ret.retCode == 0) {
                var result = ret.retObj; 
              $.each(result, function(i, item) {
               	 $("#area").append("<option value="+item.orgMapId+">"+item.zoneName+"</option>");
           	  
              });
              
            }
           
        }
       
    });
	
	},

    /**
     * 创建datagrid对象
     */
		
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,一级boss编码,一级boss名称,二级boss编码,二级boss名称,二级金额,开始时间,结束时间,品牌,礼品boss编码,礼品boss名称,业务boss编码,业务boss名称,地市",
            "initWidth": "50,80,130,80,130,80,0,0,0,80,130,80,130,*",
            "colAlign": "center,left,left,left,left,left,left,left,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,str,str,str,str,str,str,str,str,str",
            "colKeys": ["marketFirstCode", "marketFirstName","marketSecondCode","marketSecondName", 
                        "moneyVal","startTime" ,"endTime","brandId","giftId","giftName","bizCode","bizName","cityId"]
        });
        datagrid.setColumnHidden(6,true);
        datagrid.setColumnHidden(7,true);
        datagrid.setColumnHidden(8,true);
        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
   
},
    /**
     * 查询
     */
    query: function (start, end) {
		var marketFirstCode=$("#marketFirstCode").val();
		var marketSecondCode=$("#marketSecondCode").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
        	"marketSecondCode":marketSecondCode,
        	"marketFirstCode":marketFirstCode,
        	"cityId":$("#area").val(),
            "reqUrl": "bossMarket",
            "reqMethod": "queryBossCodeList",// queryBossCodeList queryBossMarketFirstPage
            "start": start,
            "end": end
        };

        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
        component.paging.setTotalPage(page.totalRecord);
        component.paging.refresh(start, end);
    },
    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
    	var isSJ= $("#isSJ").val();
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "一级营销案-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/boss/add.jsp?isSJ="+isSJ,
                "callback": function () {
                    window.location.reload();
                }
            },

//            "modify": {
//                "title": "一级营销案-修改",
//                "url": GLOBAL_INFO.CONTEXTPATH + "/page/market/marketFirst/edit.jsp?isSJ="+isSJ,
//                "param": [
//                    {"name": "pkid", "colIndex": "1"},
//                    {"name": "cityId", "colIndex": "2"},
//                    {"name": "unityFlag", "colIndex": "3"},
//                    {"name": "marketFirstCode", "colIndex": "4"}
//                ],
//                "callback": function () {
//                    window.location.reload();
//                }
//            },
            "delete": {
                "url": "",
          "click": this.delete1
            }
        });
    }, 
    
 /**
 * 删除
 */
delete1: function () {
    var selectRowId = datagrid.getSelectedRowId();
    var pkid = datagrid.cells(selectRowId, 1).getValue();
    if (pkid) {
        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
            "yes": function () {
                $.singleReq({
                    data: {
                        "reqUrl": "bossMarket",
                        "reqMethod": "deleteMarketFirstCascade",
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
