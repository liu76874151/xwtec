$().ready(function() {
	var myCalendar = new dhtmlXCalendarObject( [ "beginTime", "endTime" ]);
	component.initDataGrid();// 初始化dataGrid
//	component.initToolBar();
	component.areaInfo();
});


var component = {
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,pkid,cityId,unityFlag,marketFirstCode,渠道,地市,一级方案名称,活动对象,开始时间,结束时间,配置时间,配置工号,关联二级,列表展示状态,线上测试,地市审核状态,省级审核状态",
			"initWidth" : "60,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,*",
			"colAlign" : "center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,link",
			"colSorting" : "str,str,str,str,str,str,na,str,str,str,str,str,str,str,str,str,str,str,str,str",
			"colKeys" : [ "marketFirstPkid","cityId","unityFlag","marketFirstCode",{
				"key": "channalData",
                "formatter": function (v) {
                	return v.replace(/4/, "网厅").replace(/5/, "掌厅").replace(/6/, "短厅");
                }
},"areaName","viewName","toObject","beginTime","endTime","createTime","cfgUserId","linkOper",
			              {
			                "key": "isListView",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "不展示";
			                    }
			                    else if (v == "1") {
			                        return "展示";
			                    }
			                }
			            },"testOper","localOper","proOper"
			              ]
		});
		datagrid.setColumnHidden(1,true);
		datagrid.setColumnHidden(2,true);
		datagrid.setColumnHidden(3,true);
		datagrid.setColumnHidden(4,true);
		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
	},
	
    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
		var isSJ= $("#isSJ").val();
		//alert(isSJ+"==========issj=========");
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid,{
        	"view": {
            "title": "一级营销案-查看",
            "url": GLOBAL_INFO.CONTEXTPATH + "/page/market/marketFirst/edit.jsp?isSJ="+isSJ+"&isView=1",
            "param": [
                {"name": "pkid", "colIndex": "1"},
                {"name": "cityId", "colIndex": "2"},
                {"name": "unityFlag", "colIndex": "3"},
                {"name": "marketFirstCode", "colIndex": "4"}
            ],
            "callback": function () {
                window.location.reload();
            }
        },
        });
    }, 
	/**
	 * 查询
	 */
	query : function(start, end) {
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		var city = $("#city option:selected[value!='-请选择-']").val();
		var verifyState = $("#verifyState option:selected[value!='-请选择-']").val();
		var isListView = $("#isListView option:selected[value!='-请选择-']").val();
		var channalData = $("#channalData option:selected[value!='-请选择-']").val();
		var viewName = $("#viewName").val();
		
		
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"reqUrl" : "marketFirst",
			"reqMethod" : "queryMarketFirstAuditList",
			"state":"1",
			"city":city,
			"isListView":isListView,
			"verifyState":verifyState,
			"viewName":viewName,
			"beginTime":beginTime,
			"endTime":endTime,
			"channalData":channalData,
			"start" : start,
			"end" : end
		};
		
		try {
			var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
			component.paging.setTotalPage(page.totalRecord);
			component.paging.refresh(start, end);
		} catch (e) {
			alert(4);
		}
		
	},
	/**
	 * 省级审核
	 * @param pkid
	 * @param flag
	 */
	verifyMarketFirstPro : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanel("pro");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketFirst",
                            "reqMethod": "updateAuditState",
                            "marketFirstPkid": pkid,
                            "proVerifyState":verify
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
        }
	},
	/**
	 * 市级审核
	 * @param pkid
	 * @param flag
	 */
	verifyMarketFirstCity : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanel("local");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketFirst",
                            "reqMethod": "updateAuditState",
                            "marketFirstPkid": pkid,
                            "localVerifyState":verify
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
        }
	},
	/**
	 * 审核框
	 */
	openAuditPanel:function(level){
		var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
	    var size={"height":"60","width":"400"};
	    top.UOMPDialog.showSubPage("一级营销案-审核", GLOBAL_INFO.CONTEXTPATH + "/page/market/marketFirst/verify/auditContent.jsp?level="+level+"&marketPkId="+pkid,null,size);
	},
	areaInfo : function(){
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
	                	 $("#city").append("<option value="+item.bossCode+">"+item.areaName+"</option>");
	            	   }
	            	   if(item.bossCode==0){
	            		   $("#isSJ").val("1");//---是不是省级用户 bossCode=0
	            		   $("#channalData").attr("disabled",false);
	            	   }else{
	            			 
	            	   }
	               });
	               
	             }
	            // var obj=$("#area [value='0']");//---是不是省级用户 bossCode=0
	             //if(obj[0]){ $("#isSJ").val("1");}
	             component.initToolBar();
	         }
     });
	},
	previewMarketSecond : function(pkid,tchannal){
		window.open ('../marketSecond_preview.jsp?marketSecondPkid='+pkid+"&tchannal="+tchannal,'previewMarketSecondWin','fullscreen=yes,top=100,left=100,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	},
	/**
	 * 关联二级营销案
	 */
	viewSecond:function(marketFirstPkid,marketFirstCode,marketFirstName,cityId,tchannal){
	var size={"height":"300","width":"1000"};
	var url=GLOBAL_INFO.CONTEXTPATH + 
	"//page/market/marketSecond/verify/marketSecond_show.jsp?marketFirstPkid="+
	marketFirstPkid+"&marketFirstCode="+marketFirstCode+
	"&marketFirstName="+encodeURI(encodeURI(marketFirstName))+"&cityId="+cityId+
	"&tchannal="+tchannal;
	
	top.UOMPDialog.showSubPage(marketFirstName+"关联的二级营销案",url,null,size);
	},
}