var marketFirstPkid;
var marketFirstCode;
var marketFirstName;
$(document).ready(function () {
	marketFirstPkid = $("#marketFirstPkid").val();
    marketFirstCode = $("#marketFirstCode").val();
    marketFirstName = $("#marketFirstName").val();
    component.initDataGrid();
    component.init();
    // 日期控件
	var myCalendar = new dhtmlXCalendarObject([ "beginTime", "endTime" ]);
});

/**
 * 用户查询
 */
var component = {

    /**
	 * 创建datagrid对象
	 * 
	 */
		initDataGrid: function () {
	        datagrid = DhtmlxUtis.createGrid('gridbox', {
	            "header": "序号,marketSecondPkidHidden,渠道,一级预约营销案名称,二级预约方案名称,活动对象,开始时间,结束时间,配置时间,配置工号,列表展示状态,审核状态,地市,操作",
	            "initWidth": "60,80,60,120,*,120,80,80,80,80,120,80,80,80",
	            "colAlign": "center,center,center,center,center,center,center,center,center,center,center,center,center,center",
	            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,ro,link",
	            "colSorting": "int,str,str,str,str,str,str,str,str,str,str,str,str,str",
	            "colKeys": ["marketSecondPkId","channelData","marketFirstName","viewName", "toObject",
	              "beginTime","endTime","createTime","cfgUserId","isListView","linkOper","city","viewOper"]
	        });
	        datagrid.setColumnHidden(1,true);
	        this.paging = new dhtmlxGridPaging('paging');
	        this.paging.initPaging(component.query);
	    },
	    /**
	     * 初始化地市条件
	     */
	    init:function(){
		    $.singleReq({
		         data: {
		             "reqUrl": "area",
		             "reqMethod": "queryAreaDAList"
		         },
		         success: function (ret) {
		             if (ret.retCode == 0) {
		                 var result = ret.retObj.records; 
		               $.each(result, function(i, item) {
		                	 $("#area").append("<option value="+item.bossCode+">"+item.areaName+"</option>");
		               });
		             }
		             else {
		             }
		         }
		     });
		},
	    /**
		 * 查询
		 */
	    query: function (start, end) {
	    	var state=1;
	    	if (start == undefined) {
	            start = 0;
	        }
	        if (end == undefined) {
	            end = 10;
	        }
	        var marketFirstPkid = $("#marketFirstPkid").val();
	        var data =
	        {
	        		"reqUrl": "reservationMarketSecond",
	                "reqMethod": "queryReservationMarketSecondAuditList",
	                "marketFirstPkid": marketFirstPkid,
	                "start": start,
	                "end": end
	        };

	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    },
	    /**
	     * 更新列表展示状态
	     */
	    updateListView:function(verifyStateCode){
	    	var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 1).getValue();
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变审核状态吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "reservationMarketSecond",
	                            "reqMethod": "updateAuditState",
	                            "marketSecondPkId": pkid,
	                            "verifyState":verifyStateCode
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
	     * 预览
	     */
	    viewOper:function(marketSecondPkid,marketFirstName){
	    	var size={"height":"300","width":"1000"};
	    	top.UOMPDialog.showSubPage("二级预约营销案-预览", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_second/view.jsp?marketSecondPkid="+marketSecondPkid+"&marketFirstName="+marketFirstName,null,size);
	    }
}