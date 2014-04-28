var tabbar;
$(document).ready(function () {
        component.initCkEditor();
        iniTab();
        $("#exattrBt").on("click", showBusiExtra);
        $("#tagsBt").on("click", showBusiTag);
        $("#areasBt").on("click", showAreas);//add by unique 2014-01-10
        
		$("#busiTypeBt_01").on("click", showBusiType1);
		$("#busiTypeBt_02").on("click", showBusiType2);
		$("#busiReleBt_01").on("click", function(){
			var channelNum = "01";
			window.open('relaBusiInfo.jsp?channelNum='+channelNum,
					'relaBusiInfo_query',
					'height=500, width=700, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
		});
		$("#busiReleBt_02").on("click", function(){
			var channelNum = "02";
			window.open('relaBusiInfo.jsp?channelNum='+channelNum,
					'relaBusiInfo_query',
					'height=500, width=700, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
		});
		$("#areasBt_02").on("click", showAreas2);
		$("#areasBt_01").on("click", showAreas1);
//		component.queryAreaList(0,100);
		component.queryBrandList(0,500);
		component.queryBusinessDeduct(0,500);
        
        component.initForm();
        component.addValidate();
        component.initValidate();
    }
);

function initCalendar(id){
	var myCalendar = new dhtmlXCalendarObject(id);
}

function iniTab(){
	tabbar = new dhtmlXTabBar("tab", "top");
	tabbar.setSkin('dhx_skyblue');
	tabbar.setImagePath(GLOBAL_INFO.CONTEXTPATH +"/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
	tabbar.addTab("tab_01", "网厅", "100px");
	tabbar.addTab("tab_02", "掌厅", "100px");
	tabbar.addTab("tab_other", "", "100px");
	tabbar.setContent("tab_01", "tab_01");
	tabbar.setContent("tab_02", "tab_02");
	tabbar.setContentHTML("tab_other", "");
	doOnInit();
}
		
		
		
function doOnInit(){
    /*tab.setTabActive('tab_01');
	tab.hideTab('tab_01');
	tab.hideTab('tab_02');
	tab.hideTab('tab_other');*/
	
	tabbar.setTabActive("tab_01");
	tabbar.hideTab('tab_01');
	tabbar.hideTab('tab_02');
	tabbar.hideTab('tab_other');
	
}
function showTabs(){
	doOnInit();
	var index = 0;
	$('input[name=channel]:checked').each(function () {
				$("#tab").css("display","");
		         v = $(this).val(); //获取单个value
				 tabbar.showTab('tab_'+v);
				if(index==0){
					tabbar.setTabActive('tab_'+v);
				}
		         index++;
		     });
	if (index==0) {
		$("#tab").css("display","none");
	}
}
function showBusiExtra(){
	//businessInfo_extra.jsp
	window.open('businessInfo_extra.jsp',
				'businessInfo_extra',
				'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
	
}

function showBusiTag(){
	window.open('businessInfo_tag.jsp',
				'businessInfo_tag',
				'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
}
function showBusiType1(){
	var channelNum = "01";
	window.open('../businessType/businessType_query.jsp?channelNum='+channelNum,
				'businessType_query',
				'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
}
function showBusiType2(){
	var channelNum = "02";
	window.open('../businessType/businessType_query.jsp?channelNum='+channelNum,
				'businessType_query',
				'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
}
function showAreas(){
	window.open('businessInfo_area.jsp',
				'businessInfo_area',
				'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
}
function showAreas1(){
	var channelNum = "01";
	window.open('businessInfo_area.jsp?channelNum='+channelNum,
				'businessInfo_area',
				'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
}
function showAreas2(){
	var channelNum = "02";
	window.open('businessInfo_area.jsp?channelNum='+channelNum,
				'businessInfo_area',
				'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
}

function removeDom(busiTypeNum,channelNum){//逐个删除业务分类
	try{
		if (busiTypeNum && channelNum) {
			var businessTypeJson = $.parseJSON($("#businessTypeJson_"+channelNum).val());
			for (var index = 0; index < businessTypeJson.length; index++) {
				var num = businessTypeJson[index].busiTypeNum;
				if (num==busiTypeNum) {
					businessTypeJson.splice(index,1);
				}
			}
			$("#businessTypeJson_"+channelNum).val($.toJSON(businessTypeJson));
			$("#"+busiTypeNum+"_"+channelNum).remove();
		}
	}catch(e){}
}
function removeRela(busiNum,channelNum){//逐个删除关联业务
	try{
		if (busiNum && channelNum) {
			var relaBusiInfoJson = $.parseJSON($("#relaBusiInfoJson"+channelNum).val());
			for (var index = 0; index < relaBusiInfoJson.length; index++) {
				var num = relaBusiInfoJson[index].relativeBusi;
				if (num==busiNum) {
					relaBusiInfoJson.splice(index,1);
				}
			}
			$("#relaBusiInfoJson"+channelNum).val($.toJSON(relaBusiInfoJson));
			$("#"+busiNum+"_"+channelNum).remove();
			if (relaBusiInfoJson.length==0) {
				$("input[name=relaBrand"+channelNum+"]:checked").removeAttr("checked");//取消选中
				$("input[name=relaCity"+channelNum+"]:checked").removeAttr("checked");
				$("#releCityList_"+channelNum).parent().css({display:"none"});
				$("#releBrandList_"+channelNum).parent().css({display:"none"});
			}
		}
	}catch(e){}
}
function removeAttr(attrKey){//逐个删除业务扩展属性
	try{
		if (attrKey) {
			var busiExtraJson = $.parseJSON($("#busiExtraJson").val());
			for (var index = 0; index < busiExtraJson.length; index++) {
				var num = busiExtraJson[index].attrKey;
				if (num==attrKey) {
					busiExtraJson.splice(index,1);
				}
			}
			$("#busiExtraJson").val($.toJSON(busiExtraJson));
			$("#"+attrKey).remove();
		}
	}catch(e){}
}
function removeTag(tagId){//逐个删除业务标签
	try{
		if (tagId) {
			var busiTagJson = $.parseJSON($("#busiTagJson").val());
			for (var index = 0; index < busiTagJson.length; index++) {
				var num = busiTagJson[index].tagId;
				if (num==tagId) {
					busiTagJson.splice(index,1);
				}
			}
			$("#busiTagJson").val($.toJSON(busiTagJson));
			$("#"+tagId).remove();
		}
	}catch(e){}
}
function removeArea(areaNum,channelNum){//逐一删除业务关联的地市信息
	if (channelNum==undefined || $.trim(channelNum)=="") {
		channelNum = "";
	}
	try{
		if (areaNum) {
			var busiAreaJson = $.parseJSON($("#busiAreaJson"+channelNum).val());
			for (var index = 0; index < busiAreaJson.length; index++) {
				var num = busiAreaJson[index].areaNum;
				if (num==areaNum) {
					busiAreaJson.splice(index,1);
				}
			}
			$("#busiAreaJson"+channelNum).val($.toJSON(busiAreaJson));
			$("#"+areaNum+channelNum).remove();
		}
	}catch(e){}
}
var component = {
		// 初始化校验规则
		initValidate: function () {
		jQuery.validator.addMethod("isCode", function (value, element) { 
			
			var tel = /^[A-Za-z0-9_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format(""));
	    ValidateUtil.validate({
	        targetForm: "editForm",
	        rules: {
	            busiName: {required: true, minlength: 1, maxlength: 200},
	            busiNum: {required: true, minlength: 1, maxlength: 50,isCode:"busiNum"},
	            files_01: {required: false,isImage: "files_01"},
                files_02: {required: false,isImage: "files_02"},
	            startTime_01:{dateISO: "yyyy-MM-dd"},
	            startTime_02:{dateISO: "yyyy-MM-dd"},
	            endTime_01:{dateISO: "yyyy-MM-dd"},
	            endTime_02:{dateISO: "yyyy-MM-dd"},
	            fee:{number: "请输入合法数字"},
	            feeScoreQ:{number: "请输入合法数字"},
	            feeScoreM:{number: "请输入合法数字"},
	            busiNamePym2:{required: false, minlength: 0, maxlength: 200,isCode:"busiNamePym2"},
	            busiNestFt_01:{url: "请输入合法的业务体验嵌套地址"},
	            busiNestFt_02:{url: "请输入合法的业务体验嵌套地址"},
	            busiPicUrl_01:{url: "请输入合法的页面地址"},
	            busiPicUrl_02:{url: "请输入合法的页面地址"},
	            busiIcon_01:{url: "请输入合法的图标路径"},
	            busiIcon_02:{url: "请输入合法的图标路径"}
	        },
	        messages: {
	        	busiName: {required: "请输入业务名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
	        	busiNum: {required: "请输入业务编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"业务编码格式不正确"},
	        	files_01: {required: "请选择图片",isImage:"只支持jpg，gif，bmp，jpeg，png格式的图片!"},
            	files_02: {required: "请选择图片",isImage:"只支持jpg，gif，bmp，jpeg，png格式的图片!"},
	        	startTime_01:{dateISO: "请输入正确的日期格式(yyyy-MM-dd)"},
	        	startTime_02:{dateISO: "请输入正确的日期格式(yyyy-MM-dd)"},
	        	endTime_01:{dateISO: "请输入正确的日期格式(yyyy-MM-dd)"},
	        	endTime_02:{dateISO: "请输入正确的日期格式(yyyy-MM-dd)"},
	        	fee:{number: "请输入合法数字"},
	        	feeScoreQ:{number: "请输入合法数字"},
	            feeScoreM:{number: "请输入合法数字"},
	            busiNamePym2: {minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"名称拼音输入格式不正确"},
	            busiNestFt_01:{url: "请输入合法的业务体验嵌套地址"},
	            busiNestFt_02:{url: "请输入合法的业务体验嵌套地址"},
	            busiPicUrl_01:{url: "请输入合法的页面地址"},
	            busiPicUrl_02:{url: "请输入合法的页面地址"},
	            busiIcon_01:{url: "请输入合法的图标路径"},
	            busiIcon_02:{url: "请输入合法的图标路径"}
	        }
	    });
	},

    initForm: function () {
        $.singleSync( {
			data : {
				"reqUrl" : "businessInfoHandler",
				"reqMethod" : "queryBusiBaseInfoBynum",
				"pkid" : $("#pkid").val()
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					
					var busiName = result.busiName;
					var busiNum = result.busiNum;
					var busiNamePym2 = result.busiNamePym2;
					var state = result.state;
					var suppPayWay = result.suppPayWay;
					var feeType = result.feeType;
					var deductWay = result.deductWay;
					var feeScoreQ = result.feeScoreQ;
					var feeScoreM = result.feeScoreM;
					var processType = result.processType;
					var busiClientUrl = result.busiClientUrl;
					var busiAdvl = result.busiAdvl;
					var busiFeature = result.busiFeature;
					var busiPrivilege = result.busiPrivilege;
					var mj = result.mj;
					var jbNum = result.jbNum;
					var jb = result.jb;
					var searchWords = result.searchWords;
					var fee = result.fee;
					
					var prompt = result.prompt;
					CKEDITOR.instances.prompt.setData(prompt);
		
					$("#busiName").val(busiName);
					$("#busiNum").val(busiNum);
					$("#busiNamePym2").val(busiNamePym2);
					$("#state").val(state);
					$("#suppPayWay").val(suppPayWay);
					$("#feeType").val(feeType);
					$("#deductWay").val(deductWay);
					$("#feeScoreQ").val(feeScoreQ);
					$("#feeScoreM").val(feeScoreM);
					$("#processType").val(processType);
					$("#busiClientUrl").val(busiClientUrl);
					$("#busiAdvl").val(busiAdvl);
					$("#busiFeature").val(busiFeature);
					$("#busiPrivilege").val(busiPrivilege);
					$("#mj").val(mj);
					$("#jbNum").val(jbNum);
					$("#jb").val(jb);
					$("#searchWords").val(searchWords);
					$("#fee").val(fee);
					
					var businessEffectWayBeans = result.businessEffectWayBeans;
					try {
						for(var i=0;i<businessEffectWayBeans.length;i++){
							var busiOprtType = businessEffectWayBeans[i].busiOprtType;
							var effectWay = businessEffectWayBeans[i].effectWay;
							if(busiOprtType=="0"){
								var name = "#effectWay"+effectWay;
								$(name).attr("checked","checked");
							}
							if(busiOprtType=="1"){
								$("#neffectWay"+effectWay).attr("checked","checked");
							}
						}
					} catch (e) {
						//TODO
					}
					var businessExattrDzBeans = result.businessExattrDzBeans;
					$("#busiExtraJson").val($.toJSON(businessExattrDzBeans));
					try {
						for (var index = 0; index < businessExattrDzBeans.length; index++) {
							var attrKey = businessExattrDzBeans[index].attrKey;
							var attrName = businessExattrDzBeans[index].attrName;
							var attrVale = businessExattrDzBeans[index].attrVale;
							
							var id = attrKey;
						    var comment  = "<span id='"+id+"'>"+attrName+"<a href=\"javascript:removeAttr('"+attrKey+"');\">" +
						     		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
							$("#businessExtraList").append(comment);
							if ((index+1)%3==0) {
								$("#businessExtraList").append("<br/>");
							}
							
						}
					} catch (e) {
						//TODO
					}
					var businessAreaDzBeans = result.businessAreaDzBeans;
					$("#busiAreaJson").val($.toJSON(businessAreaDzBeans));
					try {
						for (var index = 0; index < businessAreaDzBeans.length; index++) {
							var areaNum  = businessAreaDzBeans[index].areaNum;
							var areaName = businessAreaDzBeans[index].areaName;
//							$("input[name=city][value="+areaNum+"]").attr("checked","checked");
							
							var id = areaNum;
						    var comment  = "<span id='"+id+"'>"+areaName+"<a href=\"javascript:removeArea('"+areaNum+"','');\">" +
						     		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
							$("#cityList").append(comment);
//							if ((index+1)%3==0) {
//								$("#cityList").append("<br/>");
//							}
						}
						
					} catch (e) {
						//TODO
					}
					var businessBrandDzBeans = result.businessBrandDzBeans;
					try {
						for (var index = 0; index < businessBrandDzBeans.length; index++) {
							var brandNum  = businessBrandDzBeans[index].brandNum;
							$("input[name=brand][value="+brandNum+"]").attr("checked","checked");
						}
					} catch (e) {//TODO
					}
					var busiTagDzBeans = result.busiTagDzBeans;
					$("#busiTagJson").val($.toJSON(busiTagDzBeans));
					try {
						for (var index = 0; index < busiTagDzBeans.length; index++) {
							var tagName  = busiTagDzBeans[index].tagName;
							var tagId  = busiTagDzBeans[index].tagId;
							var id = tagId;
						    var comment  = "<span id='"+id+"'>"+tagName+"<a href=\"javascript:removeTag('"+tagId+"');\">" +
						     		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
							$("#businessTagList").append(comment);
							if ((index+1)%3==0) {
								$("#businessTagList").append("<br/>");
							}
						}
					} catch (e) {//TODO
					}
					
					var businessInfoBeans = result.businessInfoBeans;
					try {
						for (var i = 0; i < businessInfoBeans.length; i++) {
							var chState = businessInfoBeans[i].chState;
							var busiIcon = businessInfoBeans[i].busiIcon;
							var busiNestFt = businessInfoBeans[i].busiNestFt;
							var busiPicUrl = businessInfoBeans[i].busiPicUrl;
							var bz = businessInfoBeans[i].bz;
							var endTime = businessInfoBeans[i].endTime;
							var startTime = businessInfoBeans[i].startTime;
							var channelNum = businessInfoBeans[i].channelNum;
							
							$('input[name=channel][value='+channelNum+']').prop("checked","checked");
							$("#state_"+channelNum).val(chState);
							$("#busiIcon_"+channelNum).val(busiIcon);
							$("#busiNestFt_"+channelNum).val(busiNestFt);
							$("#busiPicUrl_"+channelNum).val(busiPicUrl);
							$("#bz_"+channelNum).val(bz);
							if (startTime==undefined&& endTime==undefined) {
								$("input[name=dateTime_"+channelNum+"][value=0]").attr("checked","checked");
								$("#date_time_"+channelNum).css({
									display : "none"
								});
							}else{
								$("input[name=dateTime_"+channelNum+"][value=1]").attr("checked","checked");
								$("#date_time_"+channelNum).css({
									display : "block"
								});
							}
							$("#startTime_"+channelNum).val(startTime);
							$("#endTime_"+channelNum).val(endTime);
							
						}
						showTabs();
					} catch (e) {//TODO
					}
					
				} else {
					alert(ret.retMsg);
				}
			}
		});
    },

    saveForm : function() {
		if (!$('#editForm').valid()) {
	        return;
	    }
	    if (this.timeViladidate()) {
	    	UOMPComp.showTipDialog("请检查日期范围！", "");
	    	return;
	    }
//	    if(!(/^[A-Za-z0-9_]+$/).test($("#busiNamePym2").val())){
//		    UOMPComp.showTipDialog("名称拼音输入不正确！", "");
//	    	return;
//		}
	    
	    var jbNum = $("#jbNum").val();
	    var jb = $("#jb").val();
		var busiName = $("#busiName").val();
		var busiNum = $("#busiNum").val().toUpperCase();
		var busiNamePym2 = $("#busiNamePym2").val();
		var effectList = this.getEffectList();
		var neffectList = this.getNeffectList();
		var state = $("#state option:selected").val();
		var suppPayWay = $("#suppPayWay option:selected").val();
		var feeType = $("#feeType option:selected").val();
		var deductWay = $("#deductWay option:selected").val();
		
		var feeScoreQ = $("#feeScoreQ").val();
		var feeScoreM = $("#feeScoreM").val();
		
		var processType = $("#processType option:selected").val();
		
		var busiClientUrl = $("#busiClientUrl").val();
		var busiAdvl = $("#busiAdvl").val();
		var busiFeature = $("#busiFeature").val();
		var busiPrivilege = $("#busiPrivilege").val();
		var prompt = CKEDITOR.instances.prompt.getData();
//		var areaList = this.getAreaList(); modify by unique 2014-01-10
		var areaList = $.trim($("#busiAreaJson").val());
		var brandList = this.getBrandList();
		var busiExtraList = $("#busiExtraJson").val();
		var busiTagJson = $("#busiTagJson").val();
		var busiInfoList = this.getSpecBusiInfo();
		var mj = $("#mj").val();
		var searchWords = $("#searchWords").val();
		searchWords = searchWords.replace(/(\s+$)|(^\s+)/g,"");
		searchWords = searchWords.replace(/\s+/g," ");
		var fee = $("#fee").val();
		$.singleSync({
            data: {
                "reqUrl": "businessInfoHandler",
                "reqMethod": "updateBusinessInfo",
                "jbNum":jbNum,
                "jb":jb,
                "busiName": busiName,
                "busiNum": busiNum,
                "busiNamePym2": busiNamePym2,
                "effectList": effectList,
                "neffectList": neffectList,
                "state": state,
                "suppPayWay": suppPayWay,
                "feeType":feeType,
				"deductWay":deductWay,
                "feeScoreQ":feeScoreQ,
                "feeScoreM":feeScoreM,
                "processType":processType,
                "busiClientUrl":busiClientUrl,
                "busiAdvl":busiAdvl,
                "busiFeature":busiFeature,
                "busiPrivilege":busiPrivilege,
                "prompt":prompt,
                "areaList":areaList,
                "brandList":brandList,
                "busiExtraList":busiExtraList,
                "busiTagJson":busiTagJson,
                "busiInfoList":busiInfoList,
                "searchWords":searchWords,
                "fee":fee,
                "mj":mj
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	$("input[name^='files_']").attr("name","files");
                	$("#editForm").submit();
                    UOMPComp.showSuccessDialog("修改成功！", "");
                    setTimeout("window.parent.location.reload()",2000)
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
                }
            }
        });
	},
    queryBusinessDeduct : function(start, end) {
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
			
		$.singleSync( {
			data : {
				"reqUrl" : "businessHandler",
				"reqMethod" : "queryBusinessDeduct",
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj.records;
					for ( var int = 0; int < result.length; int++) {
						var option = "<option value='"+ result[int].deductNum+"' >" + result[int].deductWay+ "</option>";
						$("#deductWay").append(option);
					}

				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},	
	getEffectList : function(){//开通生效方式
			try {
				var effectList = new Array();
				$('input[name=effectWay]:checked').each(function () {
					 var element = {};
		             var code = $(this).val(); //获取单个value
					 element.effectWay = code;
					 element.busiOprtType = "0";
					 effectList.push(element);
		         });
				return  $.toJSON(effectList);
			} catch (e) {
				return "";
			}
			
		},
	getNeffectList : function(){//关闭生效方式
			try {
				var neffectList = new Array();
				$('input[name=neffectWay]:checked').each(function () {
					 var element = {};
		             var code = $(this).val(); //获取单个value
					 element.effectWay = code;
					 element.busiOprtType = "1";
					 neffectList.push(element);
		         });
				return  $.toJSON(neffectList);
			} catch (e) {
				return "";
			}
			
		},
	initCkEditor: function(){
		CKEDITOR.replace('prompt');
	},
	selectBusinessExtra:function(){
		
	},
	queryAreaList : function(start, end) {//地市查询
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
			
		$.singleSync( {
			data : {
				"reqUrl" : "area",
				"reqMethod" : "queryCityList",
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj.records;
					for ( var int = 0; int < result.length; int++) {
						var ck = "<input name='city' type='checkbox' value='"+ result[int].areaNum+"' />" + result[int].areaName+"&nbsp;&nbsp;";
						$("#cityList").append(ck);
						if(int==0){
							$("#cityList").append("<br>");
						}
					}

				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	getAreaList : function(){//地市
			try {
				var areaList = new Array();
				$('input[name=city]:checked').each(function () {
					 var element = {};
		             var code = $(this).val(); //获取单个value
					 element.areaNum = code;
					 areaList.push(element);
		         });
				return  $.toJSON(areaList);
			} catch (e) {
				return "";
			}
			
		},
	queryBrandList : function(start, end) {//品牌查询
		var jb = "1,2";
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
			
		$.singleSync( {
			data : {
				"reqUrl" : "brand",
				"reqMethod" : "queryBrandListEx",
				"jb":jb,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var ck = "<input name='brand' type='checkbox' value='"+ result[int].brandNum+"' />" + result[int].brandName+"&nbsp;&nbsp;";
						$("#brandList").append(ck);
						if(int==0){
							$("#brandList").append("<br>");
						}
					}

				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	getBrandList : function(){//品牌
			try {
				var brandList = new Array();
				$('input[name=brand]:checked').each(function () {
					 var element = {};
		             var code = $(this).val(); //获取单个value
					 element.brandNum = code;
					 brandList.push(element);
		         });
				return  $.toJSON(brandList);
			} catch (e) {
				return "";
			}
			
		},
	getSpecBusiInfo: function(){
		try {
			var busiInfoList = new Array();
			$('input[name=channel]:checked').each(function () {
				var element = {};
		         v = $(this).val(); //获取单个value
		         var businessTypeJson = $("#businessTypeJson_"+v).val();
		         var businessTypeDzBeans = $.parseJSON(businessTypeJson);
		         
		         var relaBusiInfoJson = $("#relaBusiInfoJson"+v).val();
		         if (relaBusiInfoJson!="") {
		         	 var relaBusiInfoBeans = $.parseJSON(relaBusiInfoJson);
//			         var relationBusinessInfoAreaDzBeans = $.parseJSON(component.getRelaAreaList(v));
		         	 var relationBusinessInfoAreaDzBeans = $.parseJSON($("#busiAreaJson"+v).val());
		         	 
			         var relationBusinessInfoBrandDzBeans = $.parseJSON(component.getRelaBrandList(v));
			         var relaBusiInfoArray = new Array();
			         for (var index = 0; index < relaBusiInfoBeans.length; index++) {
			         	var elem = {};
			         	elem.busiName = relaBusiInfoBeans[index].busiName;
			         	elem.relativeBusi = relaBusiInfoBeans[index].relativeBusi;
			         	elem.channelNum = v;
			         	elem.relationBusinessInfoAreaDzBeans = relationBusinessInfoAreaDzBeans;
			         	elem.relationBusinessInfoBrandDzBeans = relationBusinessInfoBrandDzBeans;
			         	
			         	relaBusiInfoArray.push(elem);
			         }
			         relaBusiInfoBeans = $.parseJSON($.toJSON(relaBusiInfoArray))
			         element.relationBusinessInfoBeans = relaBusiInfoBeans;
		         }
		         
		         element.channelNum = v;
		         element.busiIcon = $("#busiIcon_"+v).val();
		         element.busiNestFt = $("#busiNestFt_"+v).val();
		         element.bz = $("#bz_"+v).val();
		         element.busiPicUrl = $("#busiPicUrl_"+v).val();
		         if ($("input[name=dateTime_"+v+"]:checked").val()=="1") {//需要有效期限
		         	element.startTime = $("#startTime_"+v).val();
		         	element.endTime = $("#endTime_"+v).val();
		         }else{
		         	element.startTime = "";
		         	element.endTime = "";
		         }
		         
		         element.state = $("#state_"+v).val();
		         element.businessTypeDzBeans = businessTypeDzBeans;
		         
		         busiInfoList.push(element);
		         
		     });
	     	return  $.toJSON(busiInfoList);
	     } catch (e) {
				return "";
			}
	},
	getRelaAreaList : function(channelNum){//地市
		var busiNum = $("#busiNum").val().toUpperCase();
			try {
				var areaList = new Array();
				$('input[name=relaCity'+channelNum+']:checked').each(function () {
					 var element = {};
		             var code = $(this).val(); //获取单个value
					 element.areaNum = code;
					 element.busiNum = busiNum;
					 element.channelNum = $(this).attr("channelNum");
					 areaList.push(element);
		         });
				return  $.toJSON(areaList);
			} catch (e) {
				return "";
			}
			
		},
	getRelaBrandList : function(channelNum){//品牌
		var busiNum = $("#busiNum").val().toUpperCase();
			try {
				var brandList = new Array();
				$('input[name=relaBrand'+channelNum+']:checked').each(function () {
					 var element = {};
		             var code = $(this).val(); //获取单个value
					 element.brandNum = code;
					 element.busiNum = busiNum;
					 element.channelNum = $(this).attr("channelNum");
					 brandList.push(element);
		         });
				return  $.toJSON(brandList);
			} catch (e) {
				return "";
			}
			
		},
	addValidate : function(){
		jQuery.validator.addMethod("isImage", function (value, element) {
	    	var RegExp = /^.+\.(jpg|bmp|gif|jpeg|png)$/; 
	   	 	return (value==null||value==""||value==undefined)? true:RegExp.test(value);
		}, $.validator.format("只支持jpg，gif，bmp，jpeg格式的图片!"));
	},
	timeViladidate: function(){
		var flag = false;
	    $('input[name=channel]:checked').each(function () {
	    	v = $(this).val();
	    	var startTime = $("#startTime_"+v).val();
	    	var endTime = $("#endTime_"+v).val();
	    	if ($("input[name=dateTime_"+v+"]:checked").val()=="1") {
	    		if (startTime=="" || endTime=="") {
	    			flag = true;
	    		}else{
	    			if (!checkTime(startTime,endTime)) {
		    			flag = true;
		    		}
	    		}
	    	}
	    });
	    return flag;
	}
};




