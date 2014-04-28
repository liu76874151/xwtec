$().ready(function() {
	component.initDataGrid();// 初始化dataGrid
	component.initToolBar();
	component.initAdvPostion();
	component.initCityArea();
	component.initBrand();
	});

var component = {
		/**
		 *初始化品牌 
		 */
initBrand:function(){
	$.singleReq({
        data: {
		    	"reqUrl": "brand",
		        "reqMethod": "queryBrandList",
		        "start": 0,
		        "end": 9999
		     
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	var result = ret.retObj.records; 
                $.each(result, function(i, item) {
               	 $("#advBrandNum").append("<option value="+item.brandNum+">"+item.brandName+"</option>");
                    
                });
					}
        }
    });
	
},
/**
 * 初始化地市
 */
initCityArea:function(){
	$.singleReq({
        data: {
		    	"reqUrl": "area",
		        "reqMethod": "queryCityList"
		     
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	var result = ret.retObj.records; 
                $.each(result, function(i, item) {
               	 $("#advAreaNum").append("<option value="+item.areaBossCode+">"+item.areaName+"</option>");
                    
                });
					}
        }
    });
},
	/**
	* 初始化广告位
	 */
		initAdvPostion:function(){

    $.singleReq({
        data: {
		    	"reqUrl": "advPosition",
		        "reqMethod": "queryAdvPositionList",
		        "start": 0,
		        "end": 9999
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	var result = ret.retObj.records; 
                $.each(result, function(i, item) {
               	 $("#positionNum").append("<option value="+item.positionNum+">"+item.positionName+"</option>");
                    
                });
					}
        }
    });
	
},

updateUserState:function(flag,advNum){
	
	var text;
	if(flag==0){
		text="【系统提示】\n\n您确定停用该广告吗？点击“取消”则返回！"
	}else{
		text="【系统提示】\n\n您确定启用该广告吗？点击“取消”则返回！"
	}
    UOMPComp.showConfirmDialog(text, {
        "yes": function () {
            $.singleReq({
                data: {
                    "reqUrl": "advInfo",
                    "reqMethod": "updateAdvInfoUserState",
                    "advNum": advNum,
                    "useState":flag
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

},
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {

			"header" : "序号,#master_checkbox,位置,广告名称,广告编码,地区,显示顺序,运行状态,品牌,审核状态,渠道,positionNum,useState",
			"initWidth" : "40,40,120,*,80,120,80,80,140,80,80,0,0",
			"colAlign" : "center,center,left,left,left,left,center,center,left,center,center,center,center",
			"colTypes" : "ro,ch,ro,ro,ro,ro,ro,link,ro,ro,ro,ro,ro",
			"colSorting" : "str,na,str,str,str,str,str,str,str,str,str,str,str",

			"colKeys" : [ 'checkbox',"positionName","advName","advNum","advAreaName","showXh" ,"useStateLink","advBrandName",{
				"key":"auditState",
				 "formatter": function (v) {
					if(v==0){
						return "未审核";
						}else if(v==1){
						return "审核通过";
						}else{
							return "审核不通过";	
						}
	            	}
			},{
                "key": "channelNum",
                "formatter": function (v) {
            	var chanNum=["01","02","0201","0202","0203","0204","03"];
            	var chanName=["网厅","掌厅","掌厅普版","掌厅标准版","掌厅触屏版","掌厅APP","短厅"];
            	var index=$.inArray(v, chanNum);
                 return chanName[index];
                }
            },"positionNum","useState"]
		});
		datagrid.setColumnHidden(1,true);
         datagrid.setColumnHidden(11,true);
         datagrid.setColumnHidden(12,true);
		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
	},
	/**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "广告信息-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/adv/adv_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },
            
            "modify": {
                "title": "广告信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/adv/adv_edit.jsp",
                "param": [
                    {"name": "advNum", "colIndex": "4"},
                    {"name": "useState", "colIndex": "7"},
                    {"name": "channelNum", "colIndex": "10"},
                    {"name": "auditState", "colIndex": "9"},
                    {"name": "positionNum", "colIndex": "11"},
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
		var positionNum = $("#positionNum").val();
		var advAreaNum = $("#advAreaNum").val();
		var advBrandNum = $("#advBrandNum").val();
		var advNum = $("#advNum").val();
		var advName = $("#advName").val();
		var useState = $("#useState").val();
		var auditState = $("#auditState").val();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {

			"reqUrl" : "advInfo",
			"reqMethod" : "queryAdvInfoList",
			"positionNum" : positionNum,
			"advBrandNum" : advBrandNum,
			"advAreaNum" : advAreaNum,
			"advNum" : advNum,
			"advName" : advName,
			"useState" : useState,
			"auditState" : auditState,
			"start" : start,
			"end" : end
		};
		var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
		component.paging.setTotalPage(page.totalRecord);
		component.paging.refresh(start, end);
	},
    
    /**
     * 删除
     */
    delete: function () {
        var selectRowId = datagrid.getSelectedRowId();
        var advNum = datagrid.cells(selectRowId, 4).getValue();
        var useState = datagrid.cells(selectRowId, 12).getValue();
        if (advNum&&useState==0) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "advInfo",
                            "reqMethod": "deleteAdvInfo",
                            "advNum": advNum
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
        	 if(useState==1){UOMPComp.showFailedDialog("请先停用广告！", "");
        	 }else{
        		 UOMPComp.showTipDialog("请选择行", "");
        	 }
        }
    }
}