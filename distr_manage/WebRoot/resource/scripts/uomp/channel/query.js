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
	            "header": "序号,#master_checkbox,渠道名称,渠道编码",
	            "initWidth": "40,40,*,80",
	            "colAlign": "center,center,left,left",
	            "colTypes": "ro,ch,ro,ro",
	            "colSorting": "str,na,str,str",
	            "colKeys": ["checkbox","channelName","channelNum"]
	        });
//	        datagrid.setColumnHidden(2,true);
//	        
	        datagrid.setColumnHidden(1,true);
	        this.paging = new dhtmlxGridPaging('paging');
	        this.paging.initPaging(component.query);
	    },
	    
		/**
	     * 创建toolBar对象
	     */
	    initToolBar: function () {
	        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
	            "add": {
	                "title": "渠道-新增",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/channel/add.jsp",
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "view": {
	                "title": "渠道-查看",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/channel/view.jsp",
	                "param": [
	                          {"name": "channelNum", "colIndex": "3"}
	                ],
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "modify": {
	                "title": "渠道-修改",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/channel/edit.jsp",
	                "param": [
	                    {"name": "channelNum", "colIndex": "3"}
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
	    				alert(pkid);
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
	        var data =
	        {
	        		"reqUrl": "channel",
	                "reqMethod": "queryAllChannels",
	                "start": start,
	                "end": end
	        };
//	        DhtmlxUtis.loadGirdData(datagrid_comp, data_comp, false);
	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data,false);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    },
	   
	    /**
	     * 删除
	     */
	    delete: function () {
		    var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 3).getValue();
	        var idArray = new Array();
	        if (pkid) {
	        	idArray.push(pkid);
	        	component.deleteInfo(idArray);
	        } else {
	        	
	            	
	        }
    },
    deleteInfo:function(idArray){
    	$.singleReq({
    		data:
	        {
	        		"reqUrl": "channel",
	                "reqMethod": "deleteChannelInfo",
	                "channeNums":idArray
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
    }
}