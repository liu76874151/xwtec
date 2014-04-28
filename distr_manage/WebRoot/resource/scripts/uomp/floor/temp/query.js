$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
//    component.init();
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
	            "header": "序号,tempNum,模版ID,所属渠道,模版示意图,操作",
	            "initWidth": "60,0,60,80,*,80",
	            "colAlign": "center,center,center,center,center,center",
	            "colTypes": "ro,ro,ro,ro,ro,link",
	            "colSorting": "int,str,str,str,str,str",
	            "colKeys": ["tempNum","tempNum","channelName","tempImg","userableOper"]
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
	     * 创建toolBar对象
	     */
	    initToolBar: function () {
	        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
	            "add": {
	                "title": "模版-新增",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/floor/temp/add.jsp",
	                "size":{"height":200,"width":600},
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "view": {
	                "title": "模版-查看",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/floor/temp/view.jsp",
	                "param": [
	  	                    {"name": "tempNum", "colIndex": "1"}
	  	                ],
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "modify": {
	                "title": "模版-修改",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/floor/temp/edit.jsp",
	                "param": [
	                    {"name": "tempNum", "colIndex": "1"}
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
	    	var state = $('input[name="state"]:checked').val();
	    	var channelNum = $("#channelNum").find("option:selected").val();
	    	if (start == undefined) {
	            start = 0;
	        }
	        if (end == undefined) {
	            end = 10;
	        }
	        var data =
	        {
	        		"reqUrl": "floorTemplate",
	                "reqMethod": "queryFloorTemplateList",
	                "state": state,
	                "channelNum":channelNum,
	                "start": start,
	                "end": end
	        };

	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    },
	    /**
	     * 更新启停状态
	     */
	    updateUserableState:function(pkid,state){
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变启用状态吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "floorTemplate",
	                            "reqMethod": "updateUseableState",
	                            "tempNum": pkid,
	                            "state":state
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
	     * 删除
	     */
	    delete:function(){
	    	var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 1).getValue();
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "floorTemplate",
	                            "reqMethod": "deleteFloorTemplate",
	                            "tempNum": pkid
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