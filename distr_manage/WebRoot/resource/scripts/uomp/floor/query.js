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
	            "header": "序号,#master_checkbox,floorId,渠道,货架名称,楼层,货架描述,操作",
	            "initWidth": "40,60,0,80,120,60,*,80",
	            "colAlign": "center,center,left,left,left,left,left,left,left",
	            "colTypes": "ro,ch,ro,ro,ro,ro,ro,link",
	            "colSorting": "str,na,str,str,str,str,str,str",
	            "colKeys": ["checkbox","floorId","channelName","floorName","floorNum",
	              "floorBz","showOper"]
	        });
	        datagrid.setColumnHidden(2,true);
//	        
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
	                "title": "楼层-新增",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/floor/add.jsp",
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "modify": {
	                "title": "楼层-修改",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/floor/edit.jsp",
	                "param": [
	                    {"name": "pkid", "colIndex": "2"}
	                ],
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "delete": {
	                "url": "",
	          "click": this.delete
	            },
	            "generateProgram":{
	            	"title":"生成方案",
	            	"click":this.generateProgram
	            }
	        });
	    },
	    /**
	     * 生成方案
	     */
	    generateProgram:function(){
	    	var channelNum = $("#channelNum").find("option:selected").val();
	    	var checkedBoxArray = component.getCheckedBox();
	    	if(checkedBoxArray.length == 0){
	    		UOMPComp.showTipDialog("请选择楼层", "");
	    		return false;
	    	}
	    	$.singleReq({
	    		data:
		        {
		        		"reqUrl": "floorPlan",
		                "reqMethod": "generateProgram",
		                "floorIdArray":checkedBoxArray,
		                "channelNum":channelNum
		        },
		        success: function (ret) {
		        	if (ret) {
                        if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                            var resMsg = ret.resMsg;
                            var retCode = ret.retCode;
                            UOMPComp.showSuccessDialog("保存楼层方案成功！", "");
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
	    /**
	     * 获取checkbox的值
	     */
	    getCheckedBox:function(){
	    	var checkedCount = 0;
	    	   // 获取所有行id数组对象
	    		var idArray = new Array();
	    		// 根据索引遍历所有的行
	    		for ( var index = 0; index < datagrid.getRowsNum(); index++) {
	    			var ch = datagrid.cellByIndex(index,1);// index行，第二列
	    			if (ch.isChecked()) {// checkbox是否选中
	    				checkedCount ++;
	    				var pkid = datagrid.cellByIndex(index,2).getValue();// index行，第三列为楼层ID
	    				idArray.push(pkid);// 获取index行的Id属性值
	    			}
	    		}
	    		return idArray;
	    },
	    
	    /**
		 * 查询
		 */
	    query: function (start, end) {
	    	var state = $("#state").find("option:selected").val();
	    	var floorName = $("#floorName").val();
	    	var channelNum = $("#channelNum").find("option:selected").val();
	    	if (start == undefined) {
	            start = 0;
	        }
	        if (end == undefined) {
	            end = 10;
	        }
	        var data =
	        {
	        		"reqUrl": "floor",
	                "reqMethod": "queryFloorList",
	                "state": state,
	                "floorName":floorName,
	                "channelNum":channelNum,
	                "start": start,
	                "end": end
	        };
//	        DhtmlxUtis.loadGirdData(datagrid_comp, data_comp, false);
	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data,false);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    },
	    /**
	     * 更新列表展示状态
	     */
	    updateShowState:function(pkid,state){
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定改变展示状态吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "floor",
	                            "reqMethod": "updateState",
	                            "floorId": pkid,
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
	    delete: function () {
		    var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 2).getValue();
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "floor",
	                            "reqMethod": "deleteFloorInfo",
	                            "floorId": pkid
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