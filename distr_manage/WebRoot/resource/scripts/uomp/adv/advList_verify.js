$().ready(function() {
	component.initDataGrid();// 初始化dataGrid
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

updateAuditState:function(flag,advNum){
	var selectRowId = datagrid.getSelectedRowId();
    var advAreaNums = datagrid.cells(selectRowId, 13).getValue();
    var text;
	if(flag==1){
		text="【系统提示】\n\n您确定审核通过该广告吗？点击“否”则返回！"
	}else{
		text="【系统提示】\n\n您确定审核不通过该广告吗？点击“否”则返回！"
	}
    UOMPComp.showConfirmDialog(text, {
        "yes": function () {
        $.singleReq({
            data: {
                "reqUrl": "advInfo",
                "reqMethod": "verifyAdvInfo",
                "advNum": advNum,
                "areaNums": advAreaNums,
                "auditState":flag
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
                            UOMPComp.showFailedDialog("不是本地区广告或者"+ret.resMsg, "");
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
			"header" : "序号,#master_checkbox,positionNum,useState,advAreaNum,位置,广告名称,广告编码,地区,显示顺序,运行状态,品牌,审核状态,渠道",
			"initWidth" : "80,80,80,80,80,120,120,80,120,40,60,150,80,*",
			"colAlign" : "center,left,left,left,left,left,left,left,left,left,left,left,left,left",
			"colTypes" : "ro,ch,ro,ro,ro,ro,ro,ro,ro,link,ro,ro,ro,ro",
			"colSorting" : "str,na,str,str,str,str,str,str,str,str,str,str,str,str",
			"colKeys" : [ 'checkbox',"positionNum","useState","advAreaNum","positionName","advName","advNum","advAreaName","showXh" ,{
				"key":"useState",
				 "formatter": function (v) {
				if(v==1){
					return "启用";
					}else{
					return "停用";
						}
	            	}
			},"advBrandName","auditStateLink",{
                "key": "channelNum",
                "formatter": function (v) {
            	var chanNum=["01","02","0201","0202","0203","0204","03"];
            	var chanName=["网厅","掌厅","掌厅普版","掌厅标准版","掌厅触屏版","掌厅APP","短厅"];
            	var index=$.inArray(v, chanNum);
                 return chanName[index];
                }
            }]
		});
		datagrid.setColumnHidden(1,true);
         datagrid.setColumnHidden(2,true);
         datagrid.setColumnHidden(3,true);
         datagrid.setColumnHidden(4,true);
		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
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
    
   
}