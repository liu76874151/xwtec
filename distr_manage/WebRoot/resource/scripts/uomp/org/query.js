$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
    component.initCity();
});

/**
 * 用户查询
 */
var component = {
		/**
		 * 初始化地市下拉表
		 */
	initCity :function(){
		 $.singleReq({
	         data: {
	             "reqUrl": "area",
	             "reqMethod": "queryAreaCityList"
	         },
	         success: function (ret) {
	             if (ret.retCode == 0) {
	                 var result = ret.retObj; 
	               $.each(result, function(i, item) {
	                	 $("#cityId").append("<option value="+item.bossCode+">"+item.areaName+"</option>");
	                     
	               });
	               
	             }
	             var obj=$("#area [value='0']");// ---是不是省级用户 bossCode=0
//	             if(obj[0]){ $("#isSJ").val("1");}
	             component.initZoneInfo();
	         }
	        
	     });
		
		},
		initZoneInfo:function(){
			 var cityId = $("#cityId").find("option:selected").val();
			$.singleReq({
		         data: {
		             "reqUrl": "zoneInfo",
		             "reqMethod": "queryZoneInfoList",
		             "cityId":cityId
		         },
		         success: function (ret) {
		             if (ret.retCode == 0) {
		                 var result = ret.retObj; 
		               $.each(result, function(i, item) {
		                	 $("#zoneId").append("<option value="+item.zoneId+">"+item.zoneName+"</option>");
		               });
		               
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
	            "header": "序号,#master_checkbox,orgCode,营业厅名称,营业厅地址,所在地市,所在区县,是否有效",
	            "initWidth": "40,35,80,120,*,80,80,60",
	            "colAlign": "center,center,left,left,left,left,left,center",
	            "colTypes": "ro,ch,ro,ro,ro,ro,ro,ro",
	            "colSorting": "str,na,str,str,str,str,str,str",
	            "colKeys": ["checkbox","orgCode","orgName","orgAddr","areaName","zoneName",
	                        {
			                    "key": "state",
			                    "formatter": function (v) {
			                        if (v == "1") {
			                            return "有效";
			                        }
			                        else if (v == "0") {
			                            return "无效";
			                        }
			                    }
			                },
	                        ]
	        });
	        datagrid.setColumnHidden(1,true);
	        datagrid.setColumnHidden(2,true);
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
	                "title": "营业厅-新增",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/org/add.jsp",
	                "callback": function () {
	                    window.location.reload();
	                }
	            },
	            /**
	            "view": {
	                "title": "营业厅-查看",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/org/view.jsp",
	                "param": [
	                          {"name": "channelNum", "colIndex": "3"}
	                ],
	                "callback": function () {
	                    window.location.reload();
	                }
	            },
				**/
	            "modify": {
	                "title": "营业厅-修改",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/org/edit.jsp",
	                "param": [
	                    {"name": "orgCode", "colIndex": "2"}
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
	        var cityId = $("#cityId").find("option:selected").val();
	        var zoneId = $("#zoneId").find("option:selected").val();
	        var orgName = $("#orgName").val();
	        var data =
	        {
	        		"reqUrl": "orgHandle",
	                "reqMethod": "queryOrgInfoByPage",
	                "orgName":orgName,
	                "cityId":cityId,
	                "zoneId":zoneId,
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
        var pkid = datagrid.cells(selectRowId, 2).getValue();
    	$.singleReq({
    		data:
	        {
	        		"reqUrl": "orgHandle",
	                "reqMethod": "updateOrgInfoState",
	                "state": "0",
	                "orgCode":pkid
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