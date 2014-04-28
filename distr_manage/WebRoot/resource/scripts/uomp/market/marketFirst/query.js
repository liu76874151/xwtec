
$(document).ready(function () {
    component.initDataGrid();
   
    component.init();
  component.initCalendar();
});

/**
 * 用户查询
 */
var component = {
		/**
		 * 更改展示状态
		 */
	updateListView:function(flag,marketFirstPkid){
    UOMPComp.showConfirmDialog("【系统提示】\n\n您确定修改展示状态吗？点击“取消”则返回！", {
        "yes": function () {
            $.singleReq({
                data: {
            	 "reqUrl": "marketFirst",
                 "reqMethod": "updateByPrimaryKey",
                 "isListView":flag,
                 "updateListView":1,
                 "marketFirstPkid":marketFirstPkid
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
	 * 关联二级营销案
	 */
	viewSecond:function(marketFirstPkid,marketFirstCode,marketFirstName,cityId,tchannal,brand){
	var size={"height":"300","width":"1000"};
	 var selectRowId = datagrid.getSelectedRowId();
	 var cityName = encodeURI(encodeURI(datagrid.cells(selectRowId, 11).getValue()));
	 
	var url=GLOBAL_INFO.CONTEXTPATH + 
	"//page/market/marketSecond/marketSecond_show.jsp?marketFirstPkid="+
	marketFirstPkid+"&marketFirstCode="+marketFirstCode+
	"&marketFirstName="+encodeURI(encodeURI(marketFirstName))+"&cityId="+cityId+
	"&tchannal="+tchannal+"&brand="+brand+"&cityName="+cityName;
	top.UOMPDialog.showSubPage(marketFirstName+"关联的二级营销案",url,null,size);

	},
	
	/**
	 * 初始化地市下拉表
	 */
init :function(){
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
                	 $("#area").append("<option value="+item.bossCode+">"+item.areaName+"</option>");
            	   }
            	   if(item.bossCode==0){
            		   $("#isSJ").val("1");//---是不是省级用户 bossCode=0
            	   }
               });
               
             }
            // var obj=$("#area [value='0']");//---是不是省级用户 bossCode=0
             //if(obj[0]){ $("#isSJ").val("1");}
             component.initToolBar();
         }
        
     });
	
	},
		
		

    /**
     * 创建datagrid对象
     *    
     */
    initCalendar:function () {
	var t = new dhtmlXCalendarObject(["beginTime","endTime"]);
},
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,PKID,cityId,unityFlag,marketFirstCode,渠道,类别,网厅一级方案名称,活动对象,开始时间,结束时间,所属地市,配置时间,配置工号,关联地市二级,列表展示状态,审核状态",
            "initWidth": "0,60,0,0,0,40,40,120,120,80,80,80,80,80,120,100,*",
            "colAlign": "center,left,left,left,left,left,left,left,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,link,ro",
            "colSorting": "int,int,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str",
            "colKeys": ["marketFirstPkid","cityId","unityFlag","marketFirstCode",
                        {"key":"channalData",
       				 "formatter": function (v) {
            	return v.replace(/4/, "网厅").replace(/5/, "掌厅").replace(/6/, "短厅");
       	            	}
       			},{"key":"unityFlag",
       				 "formatter": function (v) {
       				if(v==1){
       					return "统一";
       					}else{
       					return "地市";
       						}
       	            	}
       			},"viewName", "toObject",
              "beginTime","endTime","city","createTime","cfgUserId","relateSecondList","linkOper",{"key":"verifyState",
      				 "formatter": function (v) {
                	return v.replace(/0/, "待审核").replace(/1/, "审核通过").replace(/2/, "审核不通过").replace(/3/, "待测试").replace(/4/, "测试不通过");
           	            	}
           			},]
        });
        datagrid.setColumnHidden(2,true);
        datagrid.setColumnHidden(3,true);
        datagrid.setColumnHidden(4,true);
        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
    },
    dtSync:function(){
    	$.singleReq({
    		data:
	        {
	        		"reqUrl": "marketFirst",
	                "reqMethod": "syncDT"
	        },
	        success: function (ret) {
	        	if (ret) {
                    if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                        var resMsg = ret.resMsg;
                        var retCode = ret.retCode;
                        UOMPComp.showSuccessDialog(resMsg, "");
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
     * 创建toolBar对象
     */
    initToolBar: function () {
    	var isSJ= $("#isSJ").val();
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "一级营销案-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/market/marketFirst/add.jsp?isSJ="+isSJ,
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "一级营销案-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/market/marketFirst/edit.jsp?isSJ="+isSJ,
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
            "delete": {
                "url": "",
          "click": this.delete
            },
            "sync":{
            	"title":"同步短厅",
            	"click":this.dtSync
            }
        });
    },
   
    /**
     * 查询
     */
    query: function (start, end) {
    	var state=1;
        var endTime = $("#endTime").val();
        var beginTime = $("#beginTime").val();
        var isListView = $("#isListView").val();
        var viewName = $("#viewName").val();
        var city = $("#area").val();
        var activityType = $("#activityType").val();//TYPE:0,1,BAND_TYPE:1,2
        var type,bandType;
        activityType>1?(type=1,bandType=activityType-1):(type=activityType,bandType="");
        var unityFlag = $("input[name='unityFlag']:checked").val();
       var channalData="";//--4网厅数据 ，5掌厅数据，6短厅数据
       $("input[name='channalData']:checked").each(function () {
    	   channalData=channalData+this.value+",";
   });
   if(channalData.length>0){
	   channalData=channalData.substring(0,channalData.length-1);}
        
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
            "endTime": endTime,
            "beginTime": beginTime,
            "isListView": isListView,
            "viewName": viewName,
            "city": city,
            "type": activityType,
          "channalData": channalData,
            "bandType": bandType,
            "type":type,
            "state":state,
            "unityFlag":unityFlag,
            "reqUrl": "marketFirst",
            "reqMethod": "queryMarketFirstList",
            "start": start,
            "end": end
        };

        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
        component.paging.setTotalPage(page.totalRecord);
        component.paging.refresh(start, end);
    } ,

    /**
     * 删除
     */
    delete: function () {
        var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
        var cityID = datagrid.cells(selectRowId, 2).getValue();
        var marketFirstCode = datagrid.cells(selectRowId, 4).getValue();
        var channel = datagrid.cells(selectRowId, 5).getValue();
        if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketFirst",
                            "reqMethod": "deleteMarketFirst",
                            "pkid": pkid,
                            "cityID": cityID,
                            "channel": channel,
                            "marketFirstCode": marketFirstCode,
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
                               if(ret.retCode == GLOBAL_INFO.SYS_FAILED){
                            	   var resMsg = ret.resMsg;
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