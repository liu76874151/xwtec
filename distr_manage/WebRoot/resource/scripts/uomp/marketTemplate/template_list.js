var  category;
var addPage,editPage;
$(document).ready(function () {
	 category=$("#category").val();
	    if(category==1){
	    	addPage="/page/marketTemplate/showTemplate/template_add.jsp";
	    	editPage="/page/marketTemplate/showTemplate/template_edit.jsp";
	    }else{
	    	addPage="/page/marketTemplate/protocolTemplate/template_add.jsp";
	    	editPage="/page/marketTemplate/protocolTemplate/template_edit.jsp";
	    }
    component.init();
    component.initDataGrid();
    component.initToolBar();
   
});

/**
 * 模板查询
 */
var component = {
		updateState:function(pkid,state){
	 if (pkid) {
         UOMPComp.showConfirmDialog("【系统提示】\n\n您确定修改状态吗？点击“取消”则返回！", {
             "yes": function () {
                 $.singleReq({
                     data: {
                         "reqUrl": "marketTemplate",
                         "reqMethod": "updateMarketTemplate",
                         "templateId": pkid,
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
     } 
	
},
		/**
		 * 初始化地市下拉表
		 */
	init :function(){
		 $.singleReq({
	         data: {
	             "reqUrl": "area",
	             "reqMethod": "queryCityList"
	         },
	         success: function (ret) {
	        	 var userCityCode=$("#userCityCode").val();
	             if (ret.retCode == 0) {
	                 var result = ret.retObj.records; 
	               $.each(result, function(i, item) {
	            	   if(userCityCode!=0&&userCityCode==item.areaBossCode){
	                	 $("#city").append("<option value="+item.areaBossCode+" >"+item.areaName+"</option>");
	            	   }else if(userCityCode==0){
	            		   $("#city").append("<option  value="+item.areaBossCode+" >"+item.areaName+"</option>"); 
	            	   }
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
            "header": "序号,templateId,地市,名称,是否有效",
            "initWidth": "80,0,120,120,*",
            "colAlign": "center,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str",
            "colKeys": ["templateId", "areaName","templateName", "state"]
        });

        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
    },

    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "信息-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + addPage,
                "callback": function () {
                    window.location.reload();
                },
                "size":{"width":800,
                		"hieght":560
                }
            },

            "modify": {
                "title": "信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + editPage,
                "param": [
                    {"name": "pkid", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                },
                "size":{"width":800,
                		"hieght":560
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
    	var category=$("#category").val();//--营销模板类别：1-展示模板，2-协议模板
        var city = $("#city").find("option:selected").val();
        var state = $("#state").find("option:selected").val();
        var templateName = $("#templateName").val();
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
            "category": category,
            "channalData": channalData,
            "templateName": templateName,
            "state": state,
            "city": city,
            "reqUrl": "marketTemplate",
            "reqMethod": "queryMarketTemplateList",
            "start": start,
            "end": end
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
        var pkid = datagrid.cells(selectRowId, 1).getValue();
        if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketTemplate",
                            "reqMethod": "deleteMarketTemplate",
                            "templateId": pkid
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