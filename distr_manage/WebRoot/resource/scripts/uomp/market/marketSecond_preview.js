var tchannal = '';
var cityId = '';
var marketSecondPkid = '';
var marketSecondCode = '';
var pactTemplateId = '';
var giftGetTypes = '';
$().ready(function() {
	marketSecondPkid = $("#marketSecondPkid").val();
	tchannal = $("#tchannal").val();
	// 日期控件
		var myCalendar = new dhtmlXCalendarObject( [ "beginTime", "endTime" ]);
		myCalendar.setPosition($("#beginTime").offset().top - 600, $(
				"#beginTime").offset().left + 100);

		// component.initEditor("dhx_skyblue");
		component.queryMarketSecnd();
		component.queryGiftInfoBeans();
		
		component.querySecondBindBizBeans();
		component.querySecondMarketGroups();
		
		component.initValidate();
	});
function doOnInit(){
	tchannal = $("#tchannal").val();
	switch (tchannal) {
        case '4':
            tab.setTabActive('tab_1');
            break;
  		case '5':
            tab.setTabActive('tab_2');
            break
        case '6':
            tab.setTabActive('tab_3');
            break;
        default:
        	tab.setTabActive('tab_1');
	}
}
var editor;

var component = {
	
		getPayShow : function(){//获取支付方式
			try {
				var payId = ''
				var state = '0';
				
				var payShow = new Array();
				$('input[name=payname]:checked').each(function () {
					 var element = {};
		             payId = $(this).val(); //获取单个value
					 
					 element.payId = payId;
					 element.state = state;
					 payShow.push(element);
		         });
				return  $.toJSON(payShow);
			} catch (e) {
				return "";
			}
			
		},
		getOrgInfoList : function(){//获取营业厅
			try {
				var state = '1';
				
				var orgInfo = new Array();
				$('input[name=orgInfoName]:checked').each(function () {
					 var element = {};
		             var orgCode = $(this).val(); //获取单个value
					 
					 element.orgCode = orgCode;
					 orgInfo.push(element);
		         });
				return  $.toJSON(orgInfo);
			} catch (e) {
				return "";
			}
			
		},
		getTargetGroupInfo : function(){//获取目标组织
			try {
				var groupId = ''
				var state = '1';
				
				var targetGroupInfo = new Array();
				$("#selecTargetGroupInfo option").each(function () {
					 var element = {};
		             groupId = $(this).val(); //获取单个value
					 
					 element.groupId = groupId;
					 element.state = state;
					 targetGroupInfo.push(element);
		         });
				return  $.toJSON(targetGroupInfo);
			} catch (e) {
				return "";
			}
			
		},
		getmarketSecondGiftInfo : function(){//获取礼品信息
			try {
				var giftName = ''
				var giftPrice = '';
				var imgDir = '';
				var giftComment = '';
				var bossGiftId ='';
				var state = '';
				var giftGetTypes='';
				var giftNum = '';
				var giftSendType = '';
				var giftCountState = '';
				
				var giftInfo = new Array();
				$("#bossGiftInfo option").each(function () {
					 var element = {};
		             giftName = $(this).text(); //获取单个text
		             bossGiftId = $(this).val(); //获取单个value
		             giftComment = $(this).attr("giftComment");
		             giftPrice = $(this).attr("giftPrice");
		             imgDir = $(this).attr("imgDir");
		             state = $(this).attr("state");
		             giftGetTypes = $(this).attr("giftGetTypes");
		             giftNum = $(this).attr("giftNum");
		             giftSendType = $(this).attr("giftSendType");
		             giftCountState = $(this).attr("giftCountState");
					 
					 element.giftName = giftName;
					 element.giftPrice = giftPrice;
					 element.imgDir = imgDir;
					 element.giftComment = giftComment;
					 element.bossGiftId = bossGiftId;
					 element.state = state;
					 element.giftGetTypes = giftGetTypes;
					 element.giftNum = giftNum;
					 element.giftSendType = giftSendType;
					 element.giftCountState=giftCountState;
					 giftInfo.push(element);
		         });
				//新增礼品
				$("#newAddGiftInfo option").each(function () {
					 var element = {};
		             giftName = $(this).text(); //获取单个text
		             bossGiftId = $(this).val(); //获取单个value
		             giftComment = $(this).attr("giftComment");
		             giftPrice = $(this).attr("giftPrice");
		             imgDir = $(this).attr("imgDir");
		             state = $(this).attr("state");
		             giftGetTypes = $(this).attr("giftGetTypes");
		             giftNum = $(this).attr("giftNum");
		             giftSendType = $(this).attr("giftSendType");
		             giftCountState = $(this).attr("giftCountState");
					 
					 element.giftName = giftName;
					 element.giftPrice = giftPrice;
					 element.imgDir = imgDir;
					 element.giftComment = giftComment;
					 element.bossGiftId = bossGiftId;
					 element.state = state;
					 element.giftGetTypes = giftGetTypes;
					 element.giftNum = giftNum;
					 element.giftSendType = giftSendType;
					 element.giftCountState=giftCountState;
					 giftInfo.push(element);
		         });
				
				return  $.toJSON(giftInfo);
			} catch (e) {
				return "";
			}
			
		},
	getmarketSecondBizInfo : function(){//获取已选取的绑定业务
		try {
			//var options = $("#selectBusiness option").attr("bizId").toString();
			
			var bizCode = '';
			var bizName = ''
			var bizId = '';
			var city = '';
			var bizComment = '';
			var bizType = '0';
			var state = '1';
			var gprsCode='';
			var bindbiz = new Array();
			$("#selectBusiness option").each(function () {
				 var element = {};
	             bizName = $(this).text(); //获取单个text
	             bizCode = $(this).val(); //获取单个value
	             bizId = $(this).attr("bizId");
				 city = $(this).attr("city");
				 
				 element.bizCode = bizCode;
				 element.bizName = bizName;
				 element.bizId = bizId;
				 element.city = city;
				 element.bizComment = bizComment;
				 element.bizType = bizType;
				 element.state = state;
				 element.gprsCode = gprsCode;
				 bindbiz.push(element);
	         });			
			return  $.toJSON(bindbiz);
		} catch (e) {
			alert(e);
			return "";
		}
		
	},
	changPayType : function(){
		var check = $("input[name=isNeedMoney]").attr("checked");
		if(check=="checked"){
			$("input[name=payname]").removeAttr("disabled");
		}else{
			//payname 
			$("input[name=payname]").removeAttr("checked");
			$("input[name=payname]").attr("disabled","true");
		}
	},
	getArrayValue : function(obj){//获取checkBox的值
		var chk_value =[];    
		  obj.each(function(){    
			  chk_value.push($(this).val());    
		  });    
		  chk_value = chk_value.length==0 ?'':chk_value;   
		  return chk_value;	
	},
	saveForm : function() {//
		
		if (!$('#addForm').valid()) {
			return;
		}
		var marketFirstPkid = $("#marketFirstPkid").val();
		var marketSecondCode = $("#marketSecondName").attr("code");
		var marketSecondName = $("#marketSecondName").val();// boss名称
		var city = $("#city").val();
		var viewName = $("#viewName").val();
		var imgDir = "";
		var flashDir = "";
		var toObject = $("#toObject").val();
		var activityComment = $("#activityComment").val();
		var isNeedMoney = $("input[name=isNeedMoney]:checked").val();
		isNeedMoney = isNeedMoney == undefined ? "0" : isNeedMoney;
		
		var moneyVal = $("#bossMoneyVal").val();

		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		var isFlashView = "";
		var isListView = "";
		var viewTemplateId = "";
		var pactTemplateId = $("#pactTemplateName").attr("templateId");
		var state = '1';
		var verifyState = '0';
		var giftGetTypes = component.getArrayValue($('input[name=giftGetTypes]:checked')).toString();
		var notice = '';
		var proVerifyState = '0';//审核状态
		var localVerifyState = '0';//地市审核状态
		var marketSecondType= '';
		var isInGroup='';//是否目标营销案
			  
		var selecTargetGroupInfo = $("#selecTargetGroupInfo option");
		if(component.getArrayValue(selecTargetGroupInfo).toString()==""){
			isInGroup='0';
		}else{
			isInGroup='1';
		}
		var fnestificationType =$("input[name=NESTIFICATION_TYPE]:checked").val();
		var businessNum="";//业务编码
		var lowestConsumption="";//最低消费
		var firstAccount="";
		var monthlyRelease="";
		var agreementTime=""//协议期
		var firstTop=$("input[name=firstTop]:checked").val();//网厅充值页面 优先展示
		var ticketFlag="";
		var pactTemplateContent = $("#pactTemplateContent").val();
		var channalData=tchannal;
		var marketSecondBizInfo=component.getmarketSecondBizInfo();//绑定业务
		var marketSecondGiftInfo = component.getmarketSecondGiftInfo();//绑定礼品信息,新增礼品信息
		var targetGroupInfo = component.getTargetGroupInfo();//目标组织
		var OrgInfoList = component.getOrgInfoList();//营业厅
		var payShow = component.getPayShow();//支付方式
		$.singleReq( {
			data : {
				"reqUrl" : "marketSecond",
				"reqMethod" : "updateMarketSecondBean",
				"marketSecondPkid":marketSecondPkid,
				"marketSecondCode" : marketSecondCode,
				"marketSecondName" : marketSecondName,
				"city" : cityId,
				"viewName" : viewName,
				"toObject" : toObject,
				"activityComment":activityComment,
				"isNeedMoney":isNeedMoney,
				"moneyVal":moneyVal,
				"beginTime":beginTime,
				"endTime":endTime,
				"state":state,
				"verifyState":verifyState,
				"giftGetTypes":giftGetTypes,
				"proVerifyState":proVerifyState,
				"localVerifyState":localVerifyState,
				"isInGroup":isInGroup,
				"fnestificationType":fnestificationType,
				"firstTop":firstTop,
				"pactTemplateContent":pactTemplateContent,
				"pactTemplateId":pactTemplateId,
				"channalData":channalData,
				
				//二级营销案绑定业务
				"marketSecondBizInfo":marketSecondBizInfo,
				//礼品信息
				"marketSecondGiftInfo":marketSecondGiftInfo,
				"targetGroupInfo":targetGroupInfo,
				"OrgInfoList":OrgInfoList,
				"payShow":payShow
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					UOMPComp.showSuccessDialog("添加成功！", "");
					top.UOMPDialog.subPageCallback
							&& top.UOMPDialog.subPageCallback();
				} else {
					UOMPComp.showFailedDialog("添加失败！", "");
				}
			}
		});
	},
	// 初始化校验规则
	initValidate : function() {
		ValidateUtil.validate( {
			targetForm : "addForm",
			rules : {
			marketSecondName : {
					required : true,
					minlength : 1,
					maxlength : 200
				}
			},
			messages : {
				marketSecondName : {
					required : "请输入BOSS二级方案名称 ",
					minlength : "长度必须大于等于{0}",
					maxlength : "长度不能超过{0}"
				}
			}
		});

	},
	// 营业厅获取,根据一级营销案的cityid
	queryOrgInfoList : function(start, end) {

		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "orgHandle",
				"reqMethod" : "queryOrgInfoList",
				"tchannal" : tchannal,
				"cityId" : cityId,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var orgName = result[int].orgName;
						var orgCode = result[int].orgCode;
						var input = "<input type='checkbox' value='" + orgCode
								+ "' name='orgInfoName' id='orgInfo"+orgCode+"'>" + orgName + "&nbsp;";
						$("#yingyeting").append(input);
					}
					component.setGiftTypes(giftGetTypes);
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	// 目标组织查询,根据一级营销案的cityid
	queryTargetGroupList : function(start, end) {

		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "orgHandle",
				"reqMethod" : "queryTargetGroupFilterBySecondPkid",
				"tchannal" : tchannal,
				"city" : cityId,
				"state" : "1",
				"groupType" : "1",
				"marketSecondPkid":marketSecondPkid,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var groupId = result[int].groupId;
						var groupName = result[int].groupName;
						var groupType = result[int].groupType;
						
						var option = "<option value='" + groupId + "' groupType='"+groupType+"'>"
								+ groupName + "</option>";
						$("#targetGroupInfoList").append(option);
					}
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	queryMarketTemplate : function(start, end) {//查询协议
		
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "marketTemplate",
				"reqMethod" : "queryMarketTemplate",
				"channalData" : tchannal,
				"city" : cityId,
				"state" : "1",
				"category" : "2",
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var templateName = result[int].templateName;
						var templateId = result[int].templateId;
						var content =  result[int].content;
						var option = $("<option>").val(templateId);
						option.text(templateName);
						option.attr("content",content);
						$("#marketTemplate").append(option);
						if(pactTemplateId==templateId){
							$("#pactTemplateName").val(templateName);
							$("#pactTemplateName").attr("templateId",templateId);
							$("#marketTemplate").val(templateId);
						}
					}
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	
	showOrgInfoDisplay : function(){
		if($("input[name=giftGetTypes]:checked").val()=="1"){
			$("#orgInfoDisplay").css("display","");
		}else{
			$("#orgInfoDisplay").css("display","none");
			$("input[name=orgInfoName]").removeAttr("checked");
		}
	},
	changeGiftTypes : function(){
		if($("#giftGetTypes4").attr("checked")=="checked"){
			$("input[name=giftGetTypes][gtype=simple]").removeAttr("checked");
			$("input[name=orgInfoName]").removeAttr("checked");
			$("input[name=giftGetTypes][gtype=simple]").attr("disabled","true");
			$("#orgInfoDisplay").css("display","none");
		}else{
			$("input[name=giftGetTypes][gtype=simple]").removeAttr("disabled");
		}
	},
	setGiftTypes : function(giftGetTypes){
		var array =  giftGetTypes.split(",");
		for ( var int = 0; int < array.length; int++) {
			var giftGetType = array[int];
			if(giftGetType=="4"){
				$("input[name=giftGetTypes][gtype=simple]").removeAttr("checked");
				$("input[name=orgInfoName]").removeAttr("checked");
				$("input[name=giftGetTypes][gtype=simple]").attr("disabled","true");
				$("#orgInfoDisplay").css("display","none");
			}else
			if(giftGetType=="1"){
				//查询设置营业厅数据
				$("#orgInfoDisplay").css("display","");
				component.querySecondOrgRelationBeans();
			}
			$("#giftGetTypes"+giftGetType).attr("checked",true);
		}
	},
	setPactTemplateContent : function(){
		var option = $("#marketTemplate option:selected");
		var templateId = option.val();
		var content =  option.attr("content");
		var name =  option.text();
		
		$("#pactTemplateContent").val(content);
		$("#pactTemplateName").val(name);
		$("#pactTemplateName").attr("templateId",templateId);
	},
	queryMarketSecnd : function(){//查询二级营销案
		$.singleReq({
            data: {
                "reqUrl": "marketSecond",
                "reqMethod": "queryMarketSecondBean",
                "pkid": marketSecondPkid
            },
            success: function (ret) {
                var result = ret.retObj;
                marketSecondCode = result.marketSecondCode;
				tchannal = result.tchannal;
				cityId = result.city;                
                
                $("#marketSecondName").val(result.marketSecondName);
                $("#marketSecondName").attr("code", result.marketSecondCode);
                $("#viewName").val(result.viewName);
                $("#toObject").val(result.toObject);
                $("#activityComment").val(result.activityComment);
                
                //领取方式
                giftGetTypes = result.giftGetTypes;
                
                
                var firstTop = result.firstTop;
                $("input[name=firstTop][value="+firstTop+"]").attr("checked",true);
                
                var isNeedMoney = result.isNeedMoney;
                if(isNeedMoney=='1'){
                	$("input[name=isNeedMoney]").attr("checked",true);
                	component.querySecondPayShowBeans();
                }else{
        			$("input[name=payname]").attr("disabled","true");
                	$("input[name=payname]").removeAttr("checked");
                }
                
                var NESTIFICATION_TYPE = result.fnestificationType;//叠加优惠
                $("input[name=NESTIFICATION_TYPE][value="+NESTIFICATION_TYPE+"]").attr("checked",true);
                
                $("#bossMoneyVal").val(result.moneyVal);
                
                
                $("#beginTime").val(result.beginTime);
                $("#endTime").val(result.endTime);
                $("#pactTemplateContent").val(result.pactTemplateContent);
                pactTemplateId = result.pactTemplateId;
        		//$("#pactTemplateName").val(name);
        		$("#pactTemplateName").attr("templateId",pactTemplateId);
                
                
                
                component.queryTargetGroupList(0, 50);
				component.queryOrgInfoList(0, 100);
                component.queryBossbindBiz(0,100);//boss绑定业务
                component.queryMarketTemplate(0, 50);//协议查询
            }
        });
	},
	querySecondBindBizBeans : function(){//绑定业务
		$.singleReq({
            data: {
                "reqUrl": "marketSecond",
                "reqMethod": "querySecondBindBizBeans",
                "marketSecondPkid": marketSecondPkid
            },
            success: function (ret) {
                var result = ret.retObj;
                for ( var int = 0; int < result.length; int++) {
                	var bizCode = result[int].bizCode;
    				var bizName = result[int].bizName;
    				var bizId = result[int].bizId;
    				var city = result[int].city;
    					
    				var option = "<option value='" + bizCode +"' +  bizId='"+bizId +"' city='"+city+"' >" + result[int].bizName + "</option>";
    				// alert(option);
    				$("#selectBusiness").append(option);
				}
            }
        });
	},
	// 查询boss绑定业务信息
	queryBossbindBiz : function(start, end) {
		
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "bossMarket",
				"reqMethod" : "queryBossBindBizBySecondPkid",
				"tchannal" : tchannal,
				"marketSecondCode" : marketSecondCode,
				"marketSecondPkid":marketSecondPkid,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var bizCode = result[int].bizCode;
						var bizName = result[int].bizName;
						var bizId = result[int].bizId;
						var cityId = result[int].cityId;
							
						var option = "<option value='" + bizCode +"' +  bizId='"+bizId +"' city='"+cityId+"' >" + result[int].bizName + "</option>";
						// alert(option);
						$("#bossBindBizName").append(option);
					}
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	queryGiftInfoBeans : function(){//礼品信息
		$.singleReq({
            data: {
                "reqUrl": "giftInfoHandler",
                "reqMethod": "queryGiftInfoBeans",
                "marketSecondPkid": marketSecondPkid
            },
            success: function (ret) {
                var result = ret.retObj;
                for ( var int = 0; int < result.length; int++) {
                	var bossGiftId = result[int].bossGiftId;
					var giftName = result[int].giftName;
					var cityId ='';
					var giftComment = result[int].giftComment;
					
					var option = $("<option>").val(bossGiftId);
					option.text(giftName);
					option.attr("cityId",cityId);
					option.attr("giftComment",giftComment);
					option.attr("giftPrice",'');
					option.attr("imgDir",'');
					option.attr("giftComment",'');
					option.attr("state",'1');
					option.attr("giftGetTypes",'');
					option.attr("giftNum",'0');
					option.attr("giftSendType",'');
					option.attr("giftCountState",'');
					
					if(bossGiftId==undefined){
						$("#newAddGiftInfo").append(option);
					}else{
						$("#bossGiftInfo").append(option);
					}
				}
            }
        });
	},
	querySecondOrgRelationBeans: function(){//营业厅选中
		$.singleReq({
            data: {
                "reqUrl": "marketSecond",
                "reqMethod": "querySecondOrgRelationBeans",
                "marketSecondPkid": marketSecondPkid
            },
            success: function (ret) {
                var result = ret.retObj;
                for ( var int = 0; int < result.length; int++) {
                	var orgCode = result[int].orgCode;
                	$("#orgInfo"+orgCode).attr("checked",true);
				}
            }
        });
	},
	querySecondPayShowBeans : function(){//支付方式
		$.singleReq({
            data: {
                "reqUrl": "marketSecond",
                "reqMethod": "querySecondPayShowBeans",
                "marketSecondPkid": marketSecondPkid,
                "state":'0'
            },
            success: function (ret) {
                var result = ret.retObj;
                for ( var int = 0; int < result.length; int++) {
                	$("input[name=payname][value="+result[int].payId+"]").attr("checked",true);
				}
            }
        });
	},
	querySecondMarketGroups : function(){//二级营销案目标组织查询
		$.singleReq({
            data: {
                "reqUrl": "marketSecond",
                "reqMethod": "querySecondMarketGroups",
                "marketSecondPkid": marketSecondPkid,
                "state":'1'
            },
            success: function (ret) {
                var result = ret.retObj;
                for ( var int = 0; int < result.length; int++) {
                	var groupId = result[int].targetGroupInfoBean.groupId;
					var groupName = result[int].targetGroupInfoBean.groupName;
					var groupType = result[int].targetGroupInfoBean.groupType;
					
					var option = "<option value='" + groupId + "' groupType='"+groupType+"'>"
							+ groupName + "</option>";
					$("#selecTargetGroupInfo").append(option);
				}
            }
        });
	}
}