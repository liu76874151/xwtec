$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
    var t = new dhtmlXCalendarObject(["beginTime"]);
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
	            "header": "序号,type,日期,编号,类型,内容",
	            "initWidth": "40,0,100,60,80,*",
	            "colAlign": "center,left,left,left,left,left",
	            "colTypes": "ro,ro,ro,ro,ro,ro",
	            "colSorting": "str,str,str,str,str,str",
	            "colKeys": ["type","id","num",   {"key":"type",
      				 "formatter": function (v) {
	            	return v.replace(/1/, "标题").replace(/2/, "内容");
	       	            	}
	       			},{"key":"value",
	      				 "formatter": function (v) {
		            	v=v.replace(/<br\/>/g, "").replace(/<br>/g, "").replace(/\n/g,"").replace(/(^\s*)|(\s*$)/g, "");
		            	if(v.length>80){
		            		v=v.substring(0,80)+"..."
		            	}
		            	return v;
		       	            	}
		       			}
	                        ]
	        });
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
	                "title": "彩信日报-新增",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/webinfo/daily/add.jsp",
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            

	            "modify": {
	                "title": "彩信日报-修改",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/webinfo/daily/edit.jsp",
	                "param": [
	                    {"name": "type", "colIndex": "1"},
	                    {"name": "id", "colIndex": "2"},
	                    {"name": "num", "colIndex": "3"}
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
	    	if (start == undefined) {
	            start = 0;
	        }
	        if (end == undefined) {
	            end = 10;
	        }
	        var type = $("input[name='type']:checked").val();
	        var id = $("#beginTime").val();
	        var data =
	        {
	        		"reqUrl": "dailyPaper",
	                "reqMethod": "queryDailyPaperList",
	                "type":type,
	                "id":id,
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
        var id = datagrid.cells(selectRowId, 2).getValue();
        var type = datagrid.cells(selectRowId, 1).getValue();
        var num = datagrid.cells(selectRowId, 3).getValue();
        if(id){
	        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
	            "yes": function () {
			    	$.singleReq({
			    		data:
				        {
				        		"reqUrl": "dailyPaper",
				                "reqMethod": "deleteDailyPaper",
				                "id":id,
				                "type":type,
				                "num":num
				        },
				        success: function (ret) {
				        	if (ret) {
			                    if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
			                        var resMsg = ret.resMsg;
			                        var retCode = ret.retCode;
			                        UOMPComp.showSuccessDialog("删除成功！", "");
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