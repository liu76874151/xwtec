var combo;
$().ready(function() {
	window.dhx_globalImgPath = GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/imgs/";
	combo = new dhtmlXCombo("combo_zone", "alfa", 200);
	component.initDataGrid();// 初始化dataGrid
	component.initToolBar();
	component.queryBusiType(0,50000);
	component.areaInfo();
	});

var component = {
	/**
	 * 创建dataGrid
	 */
	initDataGrid : function() {
		datagrid = DhtmlxUtis.createGrid('gridbox', {
			"header" : "序号,渠道,地区,业务类型,业务名称,业务编码,业务状态,资费类别,办理类别,品牌,线上测试状态,地市审核状态,省级审核状态,关联业务,操作",
			"initWidth" : "40,40,80,80,80,80,80,80,80,80,100,100,100,100,*",
			"colAlign" : "center,left,left,left,left,left,left,left,left,left,left,left,left,left,left",
			"colTypes" : "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,link,link,link,ro",
			"colSorting" : "str,str,str,str,str,str,str,str,str,str,str,str,str,str,str",
			"colKeys" : [ {
				"key": "channelNum",
			                "formatter": function (v) {
			                    if (v == "01") {
			                        return "网厅";
			                    }
			                    else if (v == "02") {
			                        return "掌厅";
			                    }else if (v == "03") {
			                        return "短厅";
			                    }
			                }
			},"areaName","busiTypeName","busiName","busiNum",{
				"key": "state",
			                "formatter": function (v) {//业务状态（0-正常；1-已删除；2-未审核; 3-审核未通过;4,待测试,5,测试不通过）
			                    if (v == "0") {
			                        return "正常";
			                    }
			                    else if (v == "1") {
			                        return "已删除";
			                    }else if (v == "2") {
			                        return "未审核";
			                    }else if (v == "3") {
			                        return "审核未通过";
			                    }else if (v == "4") {
			                        return "待测试";
			                    }else if (v == "5") {
			                        return "测试不通过";
			                    }
			                }
			},{
				"key": "feeType",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "免费";
			                    }
			                    else if (v == "1") {
			                        return "包月";
			                    }else if (v == "2") {
			                        return "包年";
			                    }else if (v == "3") {
			                        return "单次";
			                    }else if (v == "4") {
			                        return "按数量计费";
			                    }else if (v == "5") {
			                        return "其他";
			                    }
			                }
			},{
				"key": "processType",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "查询";
			                    }
			                    else if (v == "1") {
			                        return "开关";
			                    }else if (v == "2") {
			                        return "其他";
			                    }
			                }
			},"brandName","busiTestState","localVerifyState","proVerifyState","relaBusi","操作" ]
		});
		this.paging = new dhtmlxGridPaging('paging');
		this.paging.initPaging(component.query);
	},
	/**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            
        });
    },
    getArrayValue : function(obj){//获取checkBox的值
		var chk_value =[];    
		  obj.each(function(){    
			  chk_value.push($(this).val());    
		  });    
		  chk_value = chk_value.length==0 ?'':chk_value;   
		  return chk_value;	
	},
	/**
	 * 查询
	 */
	query : function(start, end) {
		var busiNum = $("#busiNum").val().toUpperCase();
		var state = $("#state option:selected[value!='-请选择-']").val();
		var processType = $("#processType option:selected[value!='-请选择-']").val();
		var feeType = $("#feeType option:selected[value!='-请选择-']").val();
//		var busiTestState = $("#busiTestState option:selected[value!='-请选择-']").val();
		
		var brandNum = component.getArrayValue($('input[name=brandNum]:checked')).toString();
		var channelNum = component.getArrayValue($('input[name=channelNum]:checked')).toString();
		var busiName = $("#busiName").val();
		var busiTypeNum = $.trim(combo.getSelectedValue());
		var city = $("#city option:selected[value!='-请选择-']").val();
		
		if (state=="") {
			state = "0,4,5";
		}
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		var data = {
			"busiNum" : busiNum,
			"reqUrl" : "businessInfoHandler",
			"reqMethod" : "selectBusiInfoListForTest",
			"state" :state,
			"processType" :processType,
			"feeType" :feeType,
			"brandNum" : brandNum,
			"channelNum" :channelNum,
			"busiName":busiName,
			"city":city,
			"busiTypeNum":busiTypeNum,
			"start" : start,
			"end" : end
		};
		var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
		component.paging.setTotalPage(page.totalRecord);
		component.paging.refresh(start, end);
	},
    testBusiInfo: function(busiNum,flag,channelNum){
    	var verify='5';
		if(flag){
			verify='0';
		}
		if (busiNum) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
                "yes": function () {
                	if (!flag) {
						var size={"height":"300","width":"1000"};
						var url=GLOBAL_INFO.CONTEXTPATH + 
						"/page/business/businessTest/businessTest_description.jsp?verify="+verify+"&busiNum="+busiNum+"&channelNum="+channelNum;
						top.UOMPDialog.showSubPage("业务测试理由",url,function(){
							window.location.reload();
						},size);
						return;
			    	}
                    $.singleReq({
                        data: {
                            "reqUrl": "businessInfoHandler",
                            "reqMethod": "testBusinessInfo",
                            "busiNum": busiNum,
                            "channelNum" : channelNum,
                            "state":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog("操作完成", "");
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
    initCombo : function(data){
		combo.enableOptionAutoWidth(1);
		combo.enableFilteringMode(true);
		combo.setComboValue(null);
		combo.clearAll();
		combo.addOption(data);
		combo.setComboValue("");
    },
    queryBusiType: function(start,end) {
		var channelNum = component.getArrayValue($('input[name=channelNum]:checked')).toString();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryParentBusiType",
                "channelNum" :channelNum,
                "start": start,
                "end": end
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                   var result = ret.retObj;
                   var busitype = new Array();
                   for (var index = 0; index < result.length; index++) {
                   		var element = new Array();
                   		element.push(result[index].busiTypeNum);
                   		element.push(result[index].busiTypeName);
						busitype.push(element);
                   }
                   component.initCombo(busitype);
                }
                else {
                }
            }
        });
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
	                for (var index = 0; index < result.length; index++) {
	                	var option = $("<option>");
	               		option.val(result[index].bossCode);
	               		option.text(result[index].areaName);
	                	$("#city").append(option);
	                }
	             }
	         }
     });
	},
	showRelaBusiness : function(busiNum,channelNum){
		var size={"height":"300","width":"1000"};
		var url=GLOBAL_INFO.CONTEXTPATH + 
		"/page/business/businessRelation/relationBusiness_query.jsp?busiNum="+busiNum+"&channelNum="+channelNum;
		top.UOMPDialog.showSubPage("业务审核理由",url,null,size);
		
	},
	showDesc: function(msg){
		if (msg=="null" || msg==undefined||msg==null) {
			msg="";
		}
//		UOMPComp.showTipDialog(msg, "");
		var size={"height":"50","width":"800"};
		var url=GLOBAL_INFO.CONTEXTPATH + 
		"/page/business/description.jsp?msg="+encodeURI(encodeURI(msg));
		top.UOMPDialog.showSubPage("业务测试不通过理由",url,null,size);	
	}
}