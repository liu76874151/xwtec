var tchannal = '';
var cityId = '';
var marketSecondPkid = '';
var marketSecondCode = '';
var pactTemplateId = '';
var giftGetTypes = '';

/**boss业务树**/
var zsetting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeCheck: beforeCheck,
			onCheck: onCheck
		}
	};
var wsetting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeCheck: beforeCheck,
			onCheck: onCheck
		}
	};
var dsetting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeCheck: beforeCheck,
			onCheck: onCheck
		}
	};

var zGiftsetting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeCheck: beforeCheck,
			onCheck: onCheck
		}
	};

var wGiftsetting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeCheck: beforeCheck,
			onCheck: onCheck
		}
	};

var dGiftsetting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeCheck: beforeCheck,
			onCheck: onCheck
		}
	};

var zNodes =new Array;
var wNodes =new Array;
var dNodes =new Array;
var zGiftNodes =new Array;
var wGiftNodes =new Array;
var dGiftNodes =new Array;
var zbusiTreeArray = new Array;
var wbusiTreeArray = new Array;
var dbusiTreeArray = new Array;
var zGiftTreeArray = new Array;
var wGiftTreeArray = new Array;
var dGiftTreeArray = new Array;

function beforeCheck(treeId, treeNode) {
	return (treeNode.doCheck !== false);
}

function onCheck(e, treeId, treeNode) {
	if(treeNode.checked){
		zbusiTreeArray.push(treeNode);
	}else{
		zbusiTreeArray.remove(treeNode);
	}
}

jQuery.extend({
    singleSync: (function () {
        var default_options = {
            "type": "post",
            "timeout": "320000",
            "contentType": "application/x-www-form-urlencoded; charset=UTF-8",
            "url": GLOBAL_INFO.COMMON_REQ_URI,
            "async":false,
            "dataType": "json",
            "success": function (data) {
               // alert("Ajax Success!");
            },
            "error": function (request, textStatus, errorThrown) {
            },
            "complete": function () {
            }
        };
        return function (user_options) {
            var options = {};
            $.extend(options, default_options, user_options);
            $.ajax(options);
        };
    })()
});
$().ready(function() {
	var marketFirstchannal=$("#marketFirstchannal").val();
	var isViewPage=$("#isViewPage").val();
	if(isViewPage=='1'){
		document.getElementById("pageTitle").innerHTML='二级营销案--查看';
		$("#saveBtn").hide();
	}
	var marketFirstCode=$("#marketFirstCode").val();
	var channalHtml="";
	var brand = $("#brand").val();
	var brandHtml="";
	if(marketFirstchannal.indexOf("4")!=-1){
		 component.querywtmarketorder($("#marketFirstPkid").val());
	  	  channalHtml+='<input type="checkbox"  value="4" name="channalData" disabled onclick="component.channalOnclick(4)" id="channalData_4"><label for="channalData_4">&nbsp;网厅&nbsp;&nbsp;&nbsp;</label>'};
	  	  if(marketFirstchannal.indexOf("5")!=-1){
	  		component.queryztmarketorder($("#marketFirstPkid").val());
	    	  channalHtml+='<input type="checkbox"  value="5" name="channalData" disabled onclick="component.channalOnclick(5)" id="channalData_5"><label for="channalData_5">&nbsp;掌厅&nbsp;&nbsp;&nbsp;</label>'};
	  	  if(marketFirstchannal.indexOf("6")!=-1){
	    	  channalHtml+='<input type="checkbox"  value="6" name="channalData" disabled  onclick="component.channalOnclick(6)" id="channalData_6"><label for="channalData_6">&nbsp;短厅&nbsp;&nbsp;&nbsp;</label>'
	    			//if(brand.indexOf("QQT")!=-1){
	    		  		brandHtml+='<input type="checkbox"   value="QQT" name="dtBrand"  id="dtBrand_QQT"><label for="dtBrand_QQT">&nbsp;全球通&nbsp;&nbsp;&nbsp;</label>'
	    		  	//}
	    	  		//if(brand.indexOf("DGDD")!=-1){
	    	  			brandHtml+='<input type="checkbox"   value="DGDD" name="dtBrand"  id="dtBrand_DGDD"><label for="dtBrand_DGDD">&nbsp;动感地带&nbsp;&nbsp;&nbsp;</label>'
	    	  		//}
	    	  		//if(brand.indexOf("SZX")!=-1){
	    	  			brandHtml+='<input type="checkbox"   value="SZX" name="dtBrand"  id="dtBrand_SZX"><label for="dtBrand_SZX">&nbsp;神州行&nbsp;&nbsp;&nbsp;</label>'
	    	  		//}
	    	  		
	    	  		 $("#dtBrandId").html(brandHtml);	  
	  	  };
	    	  $("#channalDatas").html(channalHtml);
	
	
	marketSecondPkid = $("#marketSecondPkid").val();
	
	// 日期控件
		var myCalendar = new dhtmlXCalendarObject( [ "beginTime", "endTime" ]);
		myCalendar.setPosition($("#beginTime").offset().top - 600, $(
				"#beginTime").offset().left + 100);

		// component.initEditor("dhx_skyblue");
		component.queryMarketSecnd();
		component.queryGiftInfoBeans();
		component.queryNewGiftInfo();
		
		component.querySecondBindBizBeans(4);
		component.querySecondBindBizBeans(5);
		component.querySecondBindBizBeans(6);
		component.querySecondMarketGroups(4);
		component.querySecondMarketGroups(5);
		component.querySecondMarketGroups(6);
		
		component.initValidate();
	});

function tabOnSelect(id,last_id){//--处理tabbar切换事件
	if(id=="tab_3"){
		$("#tab").height(1050);
	}else if(id=="tab_1"){
		$("#tab").height(750);
	}else if(id=="tab_2"){
		$("#tab").height(750);	
	}

}

function doOnInit(){
	var channalDataTemp="";
	$("input[name='channalData']:checked").each(function () {
		channalDataTemp=channalDataTemp+this.value+",";
    });
	 if(channalDataTemp.indexOf("6")<0){
		  tab.hideTab('tab_3',true);
		  }else{ $("#tab").height(1050);}
	  if(channalDataTemp.indexOf("4")<0){
		  tab.hideTab('tab_1',true);
		  }else{
			  $("#tab").height(750);  
		  }
	  if(channalDataTemp.indexOf("5")<0){
			  tab.hideTab('tab_2',true);
			  }else{$("#tab").height(750);}
	 
	tab.attachEvent("onSelect", function(id,last_id) {
						tabOnSelect( id,last_id);
					    return true;
					});
}
var editor;

var component = {
		channalOnclick:function(id){
	if(id==4){
		if($("#channalData_4").attr('checked')==undefined){
			tab.hideTab('tab_1',true);
		} else{
			tab.showTab('tab_1',true);	
			tab.setTabActive('tab_1');
		}
	}else if(id==5){
		if($("#channalData_5").attr('checked')==undefined){
			tab.hideTab('tab_2',true);
		} else{
			tab.showTab('tab_2',true);
			tab.setTabActive('tab_2');
		}
		
	}else if(id==6){
		if($("#channalData_6").attr('checked')==undefined){
			tab.hideTab('tab_3',true);
		} else{
			tab.showTab('tab_3',true);
			tab.setTabActive('tab_3');
		}
		
	}
},
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
		getTargetGroupInfo : function(channel){//获取目标组织
			try {
				var groupId = ''
				var state = '1';
				
				var targetGroupInfo = new Array();
				$("#selecTargetGroupInfo"+channel+" option").each(function () {
					 var element = {};
		             groupId = $(this).val(); //获取单个value
					 
					 element.groupId = groupId;
					 element.state = state;
					 element.channel=channel;
					 targetGroupInfo.push(element);
		         });
				return  $.toJSON(targetGroupInfo);
			} catch (e) {
				return "";
			}
			
		},
		getmarketSecondGiftInfo : function(channel){//获取礼品信息
			try {
				var giftName = ''
				var giftPrice = '';
				var imgDir = '';
				var giftComment = '';
				var bossGiftId ='';
				var state = '1';
				var giftGetTypes='';
				var giftNum = '';
				var giftSendType = '';
				var giftCountState = '';
				var parentGiftCode = "";
				var parentGiftName = "";
				
				var giftInfo = new Array();
				
				var giftNodesArray = component.getSelectedBossNode(channel,"gift");
				for(var i = 0; i < giftNodesArray.length; i++){
					var element = {};
					giftName = giftNodesArray[i].name;
					bossGiftId = giftNodesArray[i].id;
					parentGiftCode = giftNodesArray[i].pId;
					if(parentGiftCode != "0"){
						parentGiftName = component.getNodeName(channel, parentGiftCode, "gift");
						element.parentGiftCode = parentGiftCode;
						element.parentGiftName = parentGiftName;
					}else{
						element.parentGiftCode = "";
						element.parentGiftName = "";
					}
					element.giftName = giftName;
					 element.giftPrice = giftPrice;
					 element.imgDir = imgDir;
					 element.giftComment = giftComment;
					 element.bossGiftId = bossGiftId;
					 element.state = state;
					 element.channel = channel;
					 element.giftGetTypes = giftGetTypes;
					 element.giftNum = giftNum;
					 element.giftSendType = giftSendType;
					 element.giftCountState=giftCountState;
					 giftInfo.push(element);
				}
				/**
				$("#bossGiftInfo"+channel+" option").each(function () {
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
					 element.channel = channel;
					 element.giftGetTypes = giftGetTypes;
					 element.giftNum = giftNum;
					 element.giftSendType = giftSendType;
					 element.giftCountState=giftCountState;
					 giftInfo.push(element);
		         });**/
				//新增礼品
				$("#newAddGiftInfo"+channel+" option").each(function () {
					 var element = {};
		             giftName = $(this).text(); //获取单个text
		             bossGiftId = $(this).val(); //获取单个value
		             giftComment = $(this).attr("giftComment");
		             giftPrice = $(this).attr("giftPrice");
		             imgDir = $(this).attr("imgDir");
		             state = $(this).attr("state");
		             giftGetTypes = $(this).attr("giftGetTypes");
		             giftNum = $(this).attr("giftNum");
		             giftSendType = $(this).attr("gifttype");
		             giftCountState = $(this).attr("giftCountState");
					 
					 element.giftName = giftName;
					 element.giftPrice = giftPrice;
					 element.imgDir = imgDir;
					 element.giftComment = giftComment;
					 element.bossGiftId = bossGiftId;
					 element.state = state;
					 element.channel = channel;
					 element.giftGetTypes = giftGetTypes;
					 element.giftNum = giftNum;
					 element.giftSendType = giftSendType;
					 element.giftType = giftSendType;
					 element.giftCountState=giftCountState;
					 giftInfo.push(element);
		         });
				//alert($.toJSON(giftInfo));
				return  $.toJSON(giftInfo);
			} catch (e) {
				return "";
			}
			
		},
		getNodeName:function(channel,code,type){
			if(type == "gift"){
				if(channel == "4"){
					treeObj = $.fn.zTree.getZTreeObj("treeGiftWT");
				}
				if(channel == "5"){
					treeObj = $.fn.zTree.getZTreeObj("treeGiftZT");
				}
				if(channel == "6"){
					treeObj = $.fn.zTree.getZTreeObj("treeGiftDT");
				}
			}
			if(type == "biz"){
				if(channel == "4"){
					treeObj = $.fn.zTree.getZTreeObj("treeWT");
				}
				if(channel == "5"){
					treeObj = $.fn.zTree.getZTreeObj("treeZT");
				}
				if(channel == "6"){
					treeObj = $.fn.zTree.getZTreeObj("treeDT");
				}
			}
			var nodeName = "";
			var nodes = treeObj.getNodes();
			for(var t = 0; t < nodes.length; t++){
				var id = nodes[t].id;
				if(code == id){
					nodeName = nodes[t].name;
				}
			}
			return nodeName;
		},
		//获取勾选boss礼品
		getSelectedBossNode:function(channel,type){
			var treeObj={};
			var selectedNodes=new Array();
			if(type == "gift"){
				if(channel == "4"){
					treeObj = $.fn.zTree.getZTreeObj("treeGiftWT");
				}
				if(channel == "5"){
					treeObj = $.fn.zTree.getZTreeObj("treeGiftZT");
				}
				if(channel == "6"){
					treeObj = $.fn.zTree.getZTreeObj("treeGiftDT");
				}
			}
			if(type == "biz"){
				if(channel == "4"){
					treeObj = $.fn.zTree.getZTreeObj("treeWT");
				}
				if(channel == "5"){
					treeObj = $.fn.zTree.getZTreeObj("treeZT");
				}
				if(channel == "6"){
					treeObj = $.fn.zTree.getZTreeObj("treeDT");
				}
			}
            var nodes=treeObj.getCheckedNodes(true);
            for(var i = 0; i < nodes.length; i++){
            	var pId = nodes[i].pId;
            	if(pId == null){
            		var childrenNodes = nodes[i].children;
            		if(!childrenNodes){
            			selectedNodes.push(nodes[i]);
            		}
            	}else{
            		selectedNodes.push(nodes[i]);
            	}
            }
            return selectedNodes;
		},
	getmarketSecondBizInfo : function(channel){//获取已选取的绑定业务
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
			var bizNodesArray = component.getSelectedBossNode(channel,"biz");
			for(var i = 0; i < bizNodesArray.length; i++){
				var element = {};
				bizName = bizNodesArray[i].name;
				bizCode = bizNodesArray[i].id;
				city = bizNodesArray[i].cityId;
				parentBizCode = bizNodesArray[i].pId==null?"0":bizNodesArray[i].pId;
				if(parentBizCode != "0"){
					var nodeName = component.getNodeName(channel, parentBizCode, "biz");
					parentBizName = nodeName;
					element.parentBizCode = parentBizCode;
					element.parentBizName = parentBizName;
				}else{
					element.parentBizCode = "";
					element.parentBizName = "";
				}
				element.bizCode = bizCode;
				 element.bizName = bizName;
				 element.bizId = bizId;
				 element.city = city;
				 element.bizComment = bizComment;
				 element.bizType = bizType;
				 element.state = state;
				 element.gprsCode = gprsCode;
				 element.channel = channel;
				 bindbiz.push(element);
			}
			/**
			$("#selectBusiness"+channel+" option").each(function () {
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
				 element.channel = channel;
				 bindbiz.push(element);
	         });			
	         **/
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
		var marketFirstCode=$("#marketFirstCode").val();
		 var marketSecondCode = $("#marketSecondName").attr("code");
		var marketSecondName = $("#marketSecondName").val();// boss名称
		var city = $("#city").val();
		var viewName = $("#viewName").val();
		var imgDir = "";
		var flashDir = "";
		var toObject = $("#toObject").val();
		var activityComment = $("#activityComment").val();
		var isNeedCashBooks = $("input[name=isNeedCashBooks]:checked").val();
		//alert(isNeedCashBooks);
		var isNeedMoney = $("input[name=isNeedMoney]:checked").val();
		isNeedMoney = isNeedMoney == undefined ? "0" : isNeedMoney;
		var channalData="";
		$("input[name='channalData']:checked").each(function () {
			channalData=channalData+this.value+",";
	    });
		 channalData=channalData.substring(0,channalData.length-1);
		var moneyVal = $("#bossMoneyVal").val();

		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		var isFlashView = "";
		var isListView = "0";
		var viewTemplateId = "";
		var pactTemplateId = $("#pactTemplateName").attr("templateId");
		var state = '1';
		
		var giftGetTypes = component.getArrayValue($('input[name=giftGetTypes]:checked')).toString();
		var notice = '';
		var verifyState ,proVerifyState,localVerifyState,testOnlineState;
		var dtVerifyState,dtProVerifyState,dtLocalVerifyState,dtTestOnlineState;
		var wtVerifyState,wtProVerifyState,wtLocalVerifyState,wtTestOnlineState;
		if(channalData.indexOf("4")!=-1){//--网厅审核,测试状态复位
			wtVerifyState = '0';
			wtProVerifyState = '0';//审核状态
			wtLocalVerifyState = '0';//地市审核状态
			wtTestOnlineState = '0';//地市审核状态
		}
		if(channalData.indexOf("5")!=-1){//--掌厅审核,测试状态复位
			 verifyState = '0';
			 proVerifyState = '0';//审核状态
			 localVerifyState = '0';//地市审核状态
			 testOnlineState = '0';//地市审核状态
		}
		if(channalData.indexOf("6")!=-1){//--掌厅审核,测试状态复位
			dtVerifyState = '0';
			dtProVerifyState = '0';//审核状态
			dtLocalVerifyState = '0';//地市审核状态
			dtTestOnlineState = '0';//地市审核状态
		}
		var marketSecondType= '';
		var isInGroup='';//是否目标营销案
		var dtIsInGroup='';//是否目标营销案-短厅
		var wtIsInGroup='';//是否目标营销案-网厅
		var targetGroupInfo ;//目标组织--掌厅
		var wtTargetGroupInfo; //目标组织--网厅
		var dtTargetGroupInfo;//目标组织--短厅
		
		var dtConfigureMoney;
		var cmdCotent;
		var dtSpCode;
		var dtExpireMsg;
		var dtSuccMsg;
		var dtSendSuccMsgEnable;
		var dtIsRecommend;
		var dtUnallowBusi;
		var dtMutexBusi;
		var dtBeforeEffectMsg;
		var dtExpireMsg;
		var busiMarketSms;
		var cmdEnableFlag ;
		var dtMutexEnable;
		var dtConfirmExtraMsg;
		var isDT="0";
		var marketSecondBizInfo,marketSecondGiftInfo;
		var wtMarketSecondBizInfo,wtMarketSecondGiftInfo;
		var dtMarketSecondBizInfo,dtMarketSecondGiftInfo;
		var wtMarketOrder,ztMarketOrder;
		if($("#channalData_4").attr('checked')=='checked'){
			wtTargetGroupInfo = component.getTargetGroupInfo(4);//目标组织--网厅
			var selecTargetGroupInfo4 = $("#selecTargetGroupInfo4 option");
			if(component.getArrayValue(selecTargetGroupInfo4).toString()==""){
				wtIsInGroup='0';
			}else{
				wtIsInGroup='1';
			}

			wtMarketSecondBizInfo=component.getmarketSecondBizInfo(4);//网  绑定业务
			wtMarketSecondGiftInfo = component.getmarketSecondGiftInfo(4);//网 绑定礼品信息,新增礼品信息
			wtMarketOrder="";
		    $("select[name=wtMarketOrder] option").each(
		    		  function() {
		    			  wtMarketOrder=wtMarketOrder+$(this).val()+".";
		    		  }
		    		 );
		    if(wtMarketOrder.length>0){
		    	wtMarketOrder=wtMarketOrder.substring(0,wtMarketOrder.length-1);}
		    
		    wtVerifyState = '0';
			wtProVerifyState = '0';//审核状态
			wtLocalVerifyState = '0';//地市审核状态
			wtTestOnlineState = '0';//地市审核状态
		}
		if($("#channalData_5").attr('checked')=='checked'){
			targetGroupInfo = component.getTargetGroupInfo(5);//目标组织--掌厅
			var selecTargetGroupInfo5 = $("#selecTargetGroupInfo5 option");
			if(component.getArrayValue(selecTargetGroupInfo5).toString()==""){
				isInGroup='0';
			}else{
				isInGroup='1';
			}
			 marketSecondBizInfo=component.getmarketSecondBizInfo(5);//掌  绑定业务
			marketSecondGiftInfo = component.getmarketSecondGiftInfo(5);//掌   绑定礼品信息,新增礼品信息
			 ztMarketOrder="";
			    $("select[name=ztMarketOrder] option").each(
			    		  function() {
			    			  ztMarketOrder=ztMarketOrder+$(this).val()+".";
			    		  }
			    		 );
			    if(ztMarketOrder.length>0){
			    	ztMarketOrder=ztMarketOrder.substring(0,ztMarketOrder.length-1);}
			    verifyState = '0';
				 proVerifyState = '0';//审核状态
				 localVerifyState = '0';//地市审核状态
				 testOnlineState = '0';//地市审核状态
		}
		if($("#channalData_6").attr('checked')=='checked'){
			isDT="1";
			dtTargetGroupInfo = component.getTargetGroupInfo(6);
			var selecTargetGroupInfo6 = $("#selecTargetGroupInfo6 option");
			if(component.getArrayValue(selecTargetGroupInfo6).toString()==""){
				dtIsInGroup='0';
			}else{
				dtIsInGroup='1';
			}
			 dtConfigureMoney=$("#dtConfigureMoney").val();
			 dtConfirmExtraMsg=$("#dtConfirmExtraMsg").val();
			 cmdCotent=$("#cmdCotent").val();
			 dtSpCode=$("#dtSpCode").val();
			 dtExpireMsg=$("#dtExpireMsg").val();
			 dtSuccMsg=$("#dtSuccMsg").val();
			 dtSendSuccMsgEnable=$("input[name=dtSendSuccMsgEnable]:checked").val();
			 dtIsRecommend=$("input[name=dtIsRecommend]:checked").val();
			 dtUnallowBusi=$("#dtUnallowBusi").val();
			 dtMutexBusi=$("#dtMutexBusi").val();
			 dtBeforeEffectMsg=$("#dtBeforeEffectMsg").val();
			 dtExpireMsg=$("#dtExpireMsg").val();
			 busiMarketSms=$("#busiMarketSms").val();
			 cmdEnableFlag =($("#cmdEnableFlag").attr('checked')=='checked')?1:0;
			 dtMutexEnable =($("#dtMutexEnable").attr('checked')=='checked')?1:0;
			 var dtBrand="";
			    $("input[name='dtBrand']:checked").each(function () {
			    	dtBrand=dtBrand+this.value+";";
			    });
			    if(dtBrand.length>0){
			    	if(dtBrand.indexOf("QQT;DGDD;SZX;")>-1){
			    		dtBrand="QPP;";
			    		}
			    	}
			     dtMarketSecondBizInfo=component.getmarketSecondBizInfo(6);//短   绑定业务
				 dtMarketSecondGiftInfo = component.getmarketSecondGiftInfo(6);//短  绑定礼品信息,新增礼品信息
				 
					dtVerifyState = '0';
					dtProVerifyState = '0';//审核状态
					dtLocalVerifyState = '0';//地市审核状态
					dtTestOnlineState = '0';//地市审核状态
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
		

		
		var OrgInfoList = component.getOrgInfoList();//营业厅
		var payShow = component.getPayShow();//支付方式
		
		var notice = $("#notice").val();
		var sweetPrompt = $("#sweetPrompt").val();
		
		$.singleReq( {
			data : {
				"reqUrl" : "marketSecond",
				"reqMethod" : "updateMarketSecondBean",
				"marketSecondPkid":marketSecondPkid,
				"marketFirstPkid":marketFirstPkid,
				"marketFirstCode":marketFirstCode,
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
				"isListView":isListView,
				"state":state,
				"verifyState":verifyState,
				"giftGetTypes":giftGetTypes,
				"proVerifyState":proVerifyState,
				"localVerifyState":localVerifyState,
				"testOnlineState":testOnlineState,
				"dtVerifyState":dtVerifyState,
				"dtProVerifyState":dtProVerifyState,
				"dtLocalVerifyState":dtLocalVerifyState,
				"dtTestOnlineState":dtTestOnlineState,
				"wtVerifyState":wtVerifyState,
				"wtProVerifyState":wtProVerifyState,
				"wtLocalVerifyState":wtLocalVerifyState,
				"wtLocalVerifyState":wtLocalVerifyState,
				"wtTestOnlineState":wtTestOnlineState,
				 
				"isInGroup":isInGroup,
				"fnestificationType":fnestificationType,
				"firstTop":firstTop,
				"pactTemplateContent":pactTemplateContent,
				"pactTemplateId":pactTemplateId,
				"channalData":channalData,
				
				//二级营销案绑定业务
				"marketSecondBizInfo":marketSecondBizInfo,
				"wtMarketSecondBizInfo":wtMarketSecondBizInfo,
				"dtMarketSecondBizInfo":dtMarketSecondBizInfo,
				//礼品信息
				"marketSecondGiftInfo":marketSecondGiftInfo,
				"dtMarketSecondGiftInfo":dtMarketSecondGiftInfo,
				"wtMarketSecondGiftInfo":wtMarketSecondGiftInfo,
				"targetGroupInfo":targetGroupInfo,
				"wtTargetGroupInfo":wtTargetGroupInfo,
				"dtTargetGroupInfo":dtTargetGroupInfo,
				"OrgInfoList":OrgInfoList,
				"payShow":payShow,
				//--短厅信息
				"isDT":isDT,
				"dtConfigureMoney":dtConfigureMoney,
				"cmdCotent": cmdCotent,
				 "dtSpCode":dtSpCode,
				 "dtExpireMsg":dtExpireMsg,
				 "dtSuccMsg":dtSuccMsg,
				 "dtSendSuccMsgEnable":dtSendSuccMsgEnable,
				 "dtIsRecommend":dtIsRecommend,
				 "dtUnallowBusi":dtUnallowBusi,
				 "dtMutexBusi":dtMutexBusi,
				 "dtBeforeEffectMsg":dtBeforeEffectMsg,
				 "dtExpireMsg":dtExpireMsg,
				 "busiMarketSms":busiMarketSms,
				 "cmdEnableFlag":cmdEnableFlag,
				 "dtMutexEnable":dtMutexEnable,
				 "dtConfirmExtraMsg":dtConfirmExtraMsg,
				 "wtIsInGroup":wtIsInGroup,
				 "dtIsInGroup":dtIsInGroup,
				 "dtBrandId":dtBrand,
				
				"sweetPrompt":sweetPrompt,
				"isNeedCashBooks":isNeedCashBooks,
				"notice":notice,
				"ztMarketOrder":ztMarketOrder,
				"wtMarketOrder":wtMarketOrder
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					UOMPComp.showSuccessDialog("修改成功！", "");
					top.UOMPDialog.subPageCallback
							&& top.UOMPDialog.subPageCallback();
				} else {
					UOMPComp.showFailedDialog("修改失败！", "");
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
	
	// 添加业务
	addBusiness : function(channel) {

		var option = $("#bossBindBizName"+channel+" option:selected");
		$("#selectBusiness"+channel).append(option);

	},
	removeBusiness : function(channel) {
		// selectBusiness
		var option = $("#selectBusiness"+channel+" option:selected");
		$("#bossBindBizName"+channel).append(option);
	},
	// 编辑礼品包
	editBossGift : function(channel) {
		var pkid = $("#bossGiftInfo"+channel+" option:selected").val();
		if (pkid == undefined) {
			UOMPComp.showFailedDialog("请选择礼品包！", "");
			return;
		}
		window
				.open(
						'marketSecondGiftEdit.jsp?pkid=' + pkid+"&channel="+channel,
						'marketSecondGiftEdit',
						'height=500, width=700, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
	},
	removeBossGift : function(channel) {
		$("#bossGiftInfo"+channel+" option:selected").remove();

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
				//"tchannal" : tchannal,
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
	queryTargetGroupList : function(start, end,tchannal) {

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
//				"groupType" : "1",
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
						$("#targetGroupInfoList"+tchannal).append(option);
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
				//"channalData" : tchannal,
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
	// 添加业务
	addTargetGroupInfo : function(channel) {

		var option = $("#targetGroupInfoList"+channel+" option:selected");
		$("#selecTargetGroupInfo"+channel).append(option);

	},
	removeTargetGroupInfo : function(channel) {
		// selectBusiness
		var option = $("#selecTargetGroupInfo"+channel+" option:selected");
		$("#targetGroupInfoList"+channel).append(option);
	},
	addGift : function(channel) {//新增礼品
		window.open(
						'marketSecondNewGiftAdd.jsp?channel='+channel,
						'marketSecondNewGiftAdd',
						'height=500, width=700, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
	},
	editGift : function(channel) {//编辑礼品
		var val = $("#newAddGiftInfo"+channel+" option:selected").val();
		if (val == undefined) {
			UOMPComp.showFailedDialog("请选择礼品包！", "");
			return;
		}
		window
				.open(
						'marketSecondNewGiftEdit.jsp?channel='+channel,
						'marketSecondNewGiftEdit',
						'height=500, width=700, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
	},
	removeGift : function(channel) {//删除礼品
		$("#newAddGiftInfo"+channel+" option:selected").remove();

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
		if(giftGetTypes==undefined||giftGetTypes==null){
			return;
		}
		
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
		if(content){
			if(content.indexOf("$一级名称$")>-1)
			content=content.replace(/\$一级名称\$/g,$("#marketFirstName").val());
			if(content.indexOf("$地市名称$")>-1)
			content=content.replace(/\$地市名称\$/g,$("#cityName").val());
			if(content.indexOf("$活动内容$")>-1)
			content=content.replace(/\$活动内容\$/g,$("#activityComment").val());
			}
		$("#pactTemplateContent").val(content);
		$("#pactTemplateName").val(name);
		$("#pactTemplateName").attr("templateId",templateId);
	},
	queryMarketSecnd : function(){//查询二级营销案
		$.singleSync({
            data: {
                "reqUrl": "marketSecond",
                "reqMethod": "queryMarketSecondBean",
                "pkid": marketSecondPkid
            },
            success: function (ret) {
                var result = ret.retObj;
                 marketSecondCode = result.marketSecondCode;
				tchannal = result.channalData;
			      if(tchannal.indexOf("4")!=-1){  $("#channalData_4").attr("checked",true);}else{$("#channalData_4").attr("disabled",true);}
			      if(tchannal.indexOf("5")!=-1){ $("#channalData_5").attr("checked",true);}else{$("#channalData_5").attr("disabled",true);}
			      if(tchannal.indexOf("6")!=-1){ $("#channalData_6").attr("checked",true);}else{$("#channalData_6").attr("disabled",true);}
			     
				cityId = result.city;      
				
                $("#marketSecondName").val(result.marketSecondName);
                $("#marketSecondName").attr("code", result.marketSecondCode);
                $("#viewName").val(result.viewName);
                $("#toObject").val(result.toObject);
                $("#activityComment").val(result.activityComment);
                
                //领取方式
                giftGetTypes = result.giftGetTypes;
                $("#marketFirstName").val(result.marketFirstName);
                
                var firstTop = result.firstTop;
                $("input[name=firstTop][value="+firstTop+"]").attr("checked",true);
                var isNeedCashBooks=result.isNeedCashBooks;
                isNeedCashBooks=(isNeedCashBooks=="1"?"1":"0");
                $("#isNeedCashBooks_"+isNeedCashBooks).attr("checked",true);
                var isNeedMoney = result.isNeedMoney;
                $("#bossMoneyVal").attr("disabled",true);
				$("input[name=isNeedMoney]").attr("disabled",true);
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
                
                $("#notice").val(result.notice);
                $("#sweetPrompt").val(result.sweetPrompt);
                //--短厅信息
                $("#dtConfigureMoney").val(result.dtConfigureMoney);
                
                var isInBrandTemp=result.dtBrandId;
                isInBrandTemp=(isInBrandTemp==undefined?"":isInBrandTemp);
                if(isInBrandTemp.indexOf("QPP")!=-1){
                	isInBrandTemp="QQT;DGDD;SZX;";
                }
                   if(isInBrandTemp.indexOf("QQT")!=-1){$("#dtBrand_QQT").attr("checked",true);}
                   if(isInBrandTemp.indexOf("DGDD")!=-1){$("#dtBrand_DGDD").attr("checked",true);}
                   if(isInBrandTemp.indexOf("SZX")!=-1){$("#dtBrand_SZX").attr("checked",true);}
               
                   
                if(result.cmdEnableFlag=='1'){$("#cmdEnableFlag").attr("checked",true);}
                if(result.dtMutexEnable=='1'){$("#dtMutexEnable").attr("checked",true);}
                $("#cmdCotent").val(result.cmdCotent);
                $("#dtSpCode").val(result.dtSpCode);
                $("#dtExpireMsg").val(result.dtExpireMsg);
                $("#dtSuccMsg").val(result.dtSuccMsg);
                $("#dtUnallowBusi").val(result.dtUnallowBusi);
                $("#dtMutexBusi").val(result.dtMutexBusi);
                $("#dtBeforeEffectMsg").val(result.dtBeforeEffectMsg);
                $("#dtExpireMsg").val(result.dtExpireMsg);
                $("#busiMarketSms").val(result.busiMarketSms);
                $("#dtConfirmExtraMsg").val(result.dtConfirmExtraMsg);
                $("#dtSendSuccMsgEnable_"+result.dtSendSuccMsgEnable).attr("checked",true);
                $("#dtIsRecommend_"+result.dtIsRecommend).attr("checked",true);
                component.queryTargetGroupList(0, 50,4);
                component.queryTargetGroupList(0, 50,5);
                component.queryTargetGroupList(0, 50,6);
				component.queryOrgInfoList(0, 100);
                component.queryBossbindBiz(0,100);//boss绑定业务
                component.queryMarketTemplate(0, 50);//协议查询
            }
        });
	},
	querySecondBindBizBeans : function(channel){//绑定业务
		$.singleReq({
            data: {
                "reqUrl": "marketSecond",
                "reqMethod": "querySecondBindBizBeans",
                "channel": channel,
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
    				$("#selectBusiness"+channel).append(option);
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
				//"tchannal" : tchannal,
				"marketSecondCode" : marketSecondCode,
				"marketSecondPkid":marketSecondPkid,
				"start" : start,
				"end" : end
			},
			success : function(ret) {alert();
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var bizCode = result[int].bizCode;
						var bizName = result[int].bizName;
						var bizId = result[int].bizId;
						var cityId = result[int].cityId;
						var parentBizCode = result[int].parentBizCode;
						/**
						var option = "<option value='" + bizCode +"' +  bizId='"+bizId +"' city='"+cityId+"' >" + result[int].bizName + "</option>";
						// alert(option);
						$("#bossBindBizName4").append(option);
						$("#bossBindBizName5").append(option);
						$("#bossBindBizName6").append(option);
						**/
						var obj = new Object;
						obj.id = bizCode;
						obj.pId = parentBizCode;
						obj.name = bizName;
						obj.cityId = cityId;
						obj.bizId = bizId;
						obj.open = true;
						var state = result[int].state;
						if(state == "selected"){
							obj.checked = true;
						}
						zNodes.push(obj);
						dNodes.push(obj);
						wNodes.push(obj);
					}
					$.fn.zTree.init($("#treeZT"), zsetting, zNodes);
					$.fn.zTree.init($("#treeDT"), dsetting, dNodes);
					$.fn.zTree.init($("#treeWT"), wsetting, wNodes);
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	queryNewGiftInfo:function(){
		$.singleReq({
            data: {
                "reqUrl": "giftInfoHandler",
                "reqMethod": "queryGiftInfoBeans",
                "marketSecondPkid": marketSecondPkid,
                "marketSecondCode" : marketSecondCode,
            },
            success:function(ret){
            	var result = ret.retObj;
            	for ( var int = 0; int < result.length; int++) {
                	var bossGiftId = result[int].bossGiftId;
					var giftName = result[int].giftName;
					var cityId ='';
					var giftComment = result[int].giftComment;
					var gifttype=result[int].giftType;
					var giftNum=result[int].giftNum;
					var state = result[int].state;
					
					var option = $("<option>").val(bossGiftId);
					option.text(giftName);
					option.attr("cityId",cityId);
					option.attr("giftComment",giftComment);
					option.attr("giftPrice",'');
					option.attr("imgDir",'');
					option.attr("state",'1');
					option.attr("giftGetTypes",'');
					option.attr("giftNum",giftNum);
					option.attr("giftSendType",'');
					option.attr("giftCountState",'');
					if(result[int].channel!=undefined){
						if(bossGiftId==undefined){
							option.attr("gifttype",gifttype);
							$("#newAddGiftInfo"+result[int].channel).append(option);
						}
					}
            	}
            }
            });
	},
	queryGiftInfoBeans : function(){//礼品信息
		$.singleReq({
            data: {
                "reqUrl": "bossMarket",
                "reqMethod": "queryGiftInfoByPkid",
                "marketSecondPkid": marketSecondPkid,
                "marketSecondCode" : marketSecondCode,
            },
            success: function (ret) {
                var result = ret.retObj;
                for ( var int = 0; int < result.length; int++) {
                	var bossGiftId = result[int].bossGiftId;
					var giftName = result[int].giftName;
					var cityId ='';
					var giftComment = result[int].giftComment;
					var gifttype=result[int].giftType;
					var giftNum=result[int].giftNum;
					var state = result[int].state;
					var bossParentGiftId = result[int].bossParentGiftId;
					
					var obj = new Object();
					obj.id = bossGiftId;
					obj.pId = bossParentGiftId;
					obj.name = giftName;
					obj.cityId = cityId;
					obj.open = true;
					var state = result[int].state;
					if(state == "selected"){
						obj.checked = true;
					}
					zGiftNodes.push(obj);
					wGiftNodes.push(obj);
					dGiftNodes.push(obj);
				}
                $.fn.zTree.init($("#treeGiftZT"), zGiftsetting, zGiftNodes);
				$.fn.zTree.init($("#treeGiftWT"), wGiftsetting, wGiftNodes);
				$.fn.zTree.init($("#treeGiftDT"), dGiftsetting, dGiftNodes);
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
	querySecondMarketGroups : function(channel){//二级营销案目标组织查询
		$.singleReq({
            data: {
                "reqUrl": "marketSecond",
                "reqMethod": "querySecondMarketGroups",
                "marketSecondPkid": marketSecondPkid,
                "state":'1',
                "channel":channel
            },
            success: function (ret) {
                var result = ret.retObj;
                for ( var int = 0; int < result.length; int++) {
                	var groupId = result[int].targetGroupInfoBean.groupId;
					var groupName = result[int].targetGroupInfoBean.groupName;
					//var groupType = result[int].targetGroupInfoBean.groupType;
					
					var option = "<option value='" + groupId + "' groupType=''>"
							+ groupName + "</option>";
					$("#selecTargetGroupInfo"+channel).append(option);
				}
            }
        });
	},
	initBtnMove:function(){
		 $("#ztBtnMoveUp,#ztBtnMoveDown").click(function() {   
			  var $opt = $("#ztMarketOrder option:selected:first");   
			  if (!$opt.length) return;   
			  if (this.id == "ztBtnMoveUp") {
			  		$opt.prev().before($opt);   
			  		}else {
			  		$opt.next().after($opt); 
			  		      } 
				}); 
		 

	},
	initWTBtnMove:function(){
		 $("#wtBtnMoveUp,#wtBtnMoveDown").click(function() {   
			  var $opt = $("#wtMarketOrder option:selected:first");   
			  if (!$opt.length) return;   
			  if (this.id == "wtBtnMoveUp") {
			  		$opt.prev().before($opt);   
			  		}else {
			  		$opt.next().after($opt); 
			  		      } 
				}); 
		 

	},
	queryztmarketorder:function(marketFirstPkid){
		
		$.singleReq( {
			data : {
				"reqUrl" : "marketSecond",
				"reqMethod" : "queryViewNameOrderByZTMarketOrder",
				"marketFirstPkid" : marketFirstPkid,
			},
			success : function(ret) {
				if (ret.retCode == 0) {

		           	var obj=$("#ztMarketOrder option[value='add']");
		           	if(obj[0]){
		           		var text=obj.text();
		           		$("#ztMarketOrder").empty().prepend("<option value='add'>"+text+"</option>");	
		           	}else{
		           		$("#ztMarketOrder").empty();
		           	}
		                var result = ret.retObj.records; 
		                $.each(result, function(i, item) {
		               	 $("#ztMarketOrder").append("<option value="+item.marketSecondPkid+">"+item.viewName+"</option>");
		                    
		                });
		              
					
				} else {
					UOMPComp.showFailedDialog("失败！--queryViewNameOrderByZTMarketOrder---", "");
				}
			}
		});
		component.initBtnMove();
	},
querywtmarketorder:function(marketFirstPkid){
		
		$.singleReq( {
			data : {
				"reqUrl" : "marketSecond",
				"reqMethod" : "queryViewNameOrderByWTMarketOrder",
				"marketFirstPkid" : marketFirstPkid,
			},
			success : function(ret) {
				if (ret.retCode == 0) {

		           	var obj=$("#wtMarketOrder option[value='add']");
		           	if(obj[0]){
		           		var text=obj.text();
		           		$("#wtMarketOrder").empty().prepend("<option value='add'>"+text+"</option>");	
		           	}else{
		           		$("#wtMarketOrder").empty();
		           	}
		                var result = ret.retObj.records; 
		                $.each(result, function(i, item) {
		               	 $("#wtMarketOrder").append("<option value="+item.marketSecondPkid+">"+item.viewName+"</option>");
		                    
		                });
		              
					
				} else {
					UOMPComp.showFailedDialog("失败！--queryViewNameOrderByZTMarketOrder---", "");
				}
			}
		});
		component.initWTBtnMove();
	},
	editOption:function(input){

		if(input.value!=""){ 
			$("#wtMarketOrder option[value='"+marketSecondPkid+"']").text(input.value);	
			$("#ztMarketOrder option[value='"+marketSecondPkid+"']").text(input.value);	
			}

		
	}
}