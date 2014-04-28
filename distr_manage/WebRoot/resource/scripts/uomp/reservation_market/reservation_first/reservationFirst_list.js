$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
    component.init();
  // component.initCalendar();
});

/**
 * 查询
 */
var component = {
		
		init :function(){
	var t = new dhtmlXCalendarObject(["beginTime","endTime"]);
	 $.singleReq({
         data: {
             "reqUrl": "area",
             "reqMethod": "queryAreaDAList"
         },
         success: function (ret) {
             if (ret.retCode == 0) {
                 var result = ret.retObj.records; 
               $.each(result, function(i, item) {
            	   if(item.jb==2){
                	 $("#area").append("<option value="+item.bossCode+">"+item.areaName+"</option>");
            	   } 
               });
               
             }
             else {
                // alert(ret.retMsg+"queryViewNameOrderByMarketOrder");
             }
         }
     });
	},
		
		

    /**
     * 创建datagrid对象
     *    
     */
  
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,hiddenPKID,渠道,一级方案名称,活动对象,开始时间,结束时间,所属地市,配置时间,配置工号,关联地市二级,列表展示状态",
            "initWidth": "80,0,80,120,120,80,80,80,120,80,120,*",
            "colAlign": "center,left,left,left,left,left,left,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,link",
            "colSorting": "int,int,str,str,str,str,str,str",
            "colKeys": ["marketFirstPkid","channalData","viewName", "toObject",
              "beginTime","endTime","city","createTime","cfgUserId","relateSecondList","linkOper"]
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
                "title": "一级营预约销案-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_first/reservationFirst_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },
            "modify": {
                "title": "一级营预约销案-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_first/reservationFirst_edit.jsp",
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
    query: function (start, end) {
    	var state=1;
        var endTime = $("#endTime").val();
        var beginTime = $("#beginTime").val();
        var isListView = $("#isListView").val();
        var viewName = $("#viewName").val();
        var channalData = $("#channalData").find("option:selected").val();
        var city = $("#area").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 7;
        }
        var data =
        {
            "endTime": endTime,
            "beginTime": beginTime,
            "isListView": isListView,
            "viewName": viewName,
            "city": city,
            "state":state,
            "channalData":channalData,
            "reqUrl": "reservationFirst",
            "reqMethod": "queryReservationFirstList",
            "start": start,
            "end": end
        };

        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
        component.paging.setTotalPage(page.totalRecord);
        component.paging.refresh(start, end);
    } ,

    /**
     * 删除
     */
    delete: function () {
        var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
        if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "reservationFirst",
                            "reqMethod": "deleteReservationFirst",
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
    },
    /**
     * 查看关联二级预约营销案
     */
    viewSecond:function(marketFirstPkid,marketFirstCode){
    	var size={"height":"300","width":"1000"};
    	top.UOMPDialog.showSubPage("关联的二级预约营销案", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_second/query.jsp?marketFirstPkid="+marketFirstPkid+"&marketFirstCode="+marketFirstCode,null,size);
    }
}