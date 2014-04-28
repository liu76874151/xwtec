$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
    //component.initCity();
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
	            "header": "序号,giftType,礼品类型,备注",
	            "initWidth": "40,0,160,*",
	            "colAlign": "center,center,left,left",
	            "colTypes": "ro,ro,ro,ro",
	            "colSorting": "str,str,str,str",
	            "colKeys": ["giftType","productName","giftTypeDesc"
	                        ]
	        });
	        datagrid.setColumnHidden(1,true);
//	        
	        this.paging = new dhtmlxGridPaging('paging');
	        this.paging.initPaging(component.query);
	    },
	    
		/**
	     * 创建toolBar对象
	     */
	    initToolBar: function () {
	        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
	            "add": {
	                "title": "礼品类型-新增",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/gift/add.jsp",
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "view": {
	                "title": "礼品类型-查看",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/gift/view.jsp",
	                "param": [
	                          {"name": "giftType", "colIndex": "1"}
	                ],
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "modify": {
	                "title": "礼品类型-修改",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/gift/edit.jsp",
	                "param": [
	                    {"name": "giftType", "colIndex": "1"}
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
	    				var pkid = datagrid.cellByIndex(index,3).getValue();// index行，第三列为渠道编码
	    				idArray.push(pkid);// 获取index行的Id属性值
	    			}
	    		}
	    		return idArray;
	    },
	    
	    /**
		 * 查询
		 */
	    query: function (start, end) {
	    	if (start == undefined) {
	            start = 0;
	        }
	        if (end == undefined) {
	            end = 10;
	        }
	        var productName = $("#productName").val();
	        var data =
	        {
	        		"reqUrl": "giftInfoHandler",
	                "reqMethod": "queryGiftInfoTypeListForManage",
	                "productName":productName,
	                "state":"0",
	                "start": start,
	                "end": end
	        };
//	        DhtmlxUtis.loadGirdData(datagrid_comp, data_comp, false);
	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data,false);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    },
	 
    delete:function(idArray){
    	var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
        if(pkid){
	        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
	            "yes": function () {
			    	$.singleReq({
			    		data:
				        {
				        		"reqUrl": "giftInfoHandler",
				                "reqMethod": "updateGiftTypeInfo",
				                "giftType":pkid,
				                "state":"1"
				        },
				        success: function (ret) {
				        	if (ret) {
			                    if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
			                        var resMsg = ret.resMsg;
			                        var retCode = ret.retCode;
			                        UOMPComp.showSuccessDialog("礼品类型删除成功！", "");
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