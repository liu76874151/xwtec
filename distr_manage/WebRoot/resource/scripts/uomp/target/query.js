$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
    component.init();
    component.initCalendar();
});

/**
 * 用户查询
 */
var component = {
		initCalendar:function () {
		var t = new dhtmlXCalendarObject(["beginTime","endTime"]);
		},
    /**
	 * 创建datagrid对象
	 * 
	 */
		initDataGrid: function () {
	        datagrid = DhtmlxUtis.createGrid('gridbox', {
	            "header": "序号,id,地市,组织名称,开始时间,结束时间,号码量,状态,操作,city",
	            "initWidth": "40,0,60,*,80,80,80,60,80,0",
	            "colAlign": "center,center,left,left,left,left,left,left,left,left",
	            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,link,ro",
	            "colSorting": "str,str,str,str,str,str,str,str,str,str",
	            "colKeys": ["groupId","areaName","groupName","beginTime","endTime","importCount",
	                        {
			                    "key": "state",
			                    "formatter": function (v) {
			                        if (v == "1") {
			                            return "可用";
			                        }
			                        else if (v == "0") {
			                            return "不可用";
			                        }
			                    }
			                }
	                        ,"importOper","city"]
	        });
	        datagrid.setColumnHidden(1,true);
	        datagrid.setColumnHidden(8,true);
	        datagrid.setColumnHidden(9,true);
	        
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
		             "reqMethod": "queryCityList"
		         },
		         success: function (ret) {
		             if (ret.retCode == 0) {
		                 var result = ret.retObj.records; 
		               $.each(result, function(i, item) {
		                	 $("#area").append("<option value="+item.areaBossCode+">"+item.areaName+"</option>");
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
	                "title": "目标组织-新增",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/target/add.jsp",
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "view": {
	                "title": "目标组织-查看",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/target/view.jsp",
	                "param": [
	                    {"name": "groupId", "colIndex": "1"}
	                ],
	                "callback": function () {
	                    window.location.reload();
	                }
	            },

	            "modify": {
	                "title": "目标组织-修改",
	                "url": GLOBAL_INFO.CONTEXTPATH + "/page/target/edit.jsp",
	                "param": [
	                    {"name": "groupId", "colIndex": "1"}
	                ],
	                "callback": function () {
	                    window.location.reload();
	                }
	            },
	            /**
	            "recover": {
	                "url": "",
	          "click": this.recoverInfo
	            },
				**/
	            "delete": {
	                "url": "",
	          "click": this.deleteInfo
	            },
	            "import":{
	            	"title": "目标组织-号码管理",
	            	"url":GLOBAL_INFO.CONTEXTPATH + "/page/target/importData.jsp",
	            	"param":[{"name":"groupId","colIndex":"1"}],
	            	"callback": function () {
	                    window.location.reload();
	                }
	            }
	        });
	    },
	    recoverInfo:function(){
	    	component.delete(1);
	    },
	    deleteInfo:function(){
	    	component.delete(0);
	    },
	    /**
		 * 查询
		 */
	    query: function (start, end) {
	    	var area = $("#area").find("option:selected").val();
	    	var state = $("#state").find("option:selected").val();
	    	var beginTime = $("#beginTime").val();
	    	var endTime = $("#endTime").val();
	    	var groupName = $("#groupName").val();
	    	if (start == undefined) {
	            start = 0;
	        }
	        if (end == undefined) {
	            end = 10;
	        }
	        var data =
	        {
	        		"reqUrl": "target",
	                "reqMethod": "queryTargetGroupInfo",
	                "city": area,
	                "state":state,
	                "groupName":groupName,
	                "beginTime":beginTime,
	                "endTime":endTime,
	                "start": start,
	                "end": end
	        };
//	        DhtmlxUtis.loadGirdData(datagrid_comp, data_comp, false);
	        var page = DhtmlxUtis.loadGirdPageData(datagrid, data,false);
	        component.paging.setTotalPage(page.totalRecord);
	        component.paging.refresh(start, end);
	    },
	    importData:function(){
	    	var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 1).getValue();
	        component.openImportOper(pkid);
	    },
	    /**
	     * 删除
	     */
	    delete: function (state) {
		    var selectRowId = datagrid.getSelectedRowId();
	        var pkid = datagrid.cells(selectRowId, 1).getValue();
	        var tipStr = "";
	        if(state == '0'){
	        	tipStr = "删除";
	        }else{
	        	tipStr = "恢复";
	        }
	        if (pkid) {
	            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定"+tipStr+"该组织信息吗？点击“取消”则返回！", {
	                "yes": function () {
	                    $.singleReq({
	                        data: {
	                            "reqUrl": "target",
	                            "reqMethod": "updateState",
	                            "groupId": pkid,
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
     * 导入
     */
    openImportOper:function(groupId){
    	var size={"height":"300","width":"600"};
    	top.UOMPDialog.showSubPage("目标组织-导入", GLOBAL_INFO.CONTEXTPATH + "/page/target/importData.jsp?groupId="+groupId,null,size);
    }
}