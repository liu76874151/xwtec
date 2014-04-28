var tchannal = '';
var cityId = '';
var marketFirstCode = '';
var z;

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
			onCheck: zonCheck
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
			onCheck: wonCheck
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
			onCheck: donCheck
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
			onCheck: zGiftonCheck
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
			onCheck: wGiftonCheck
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
			onCheck: dGiftonCheck
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

window.dhx_globalImgPath = GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/imgs/";
$().ready(function() {
	
	tchannal = $("#tchannal").val();
	cityId = $("#cityId").val();
	var brand = $("#brand").val();
	marketFirstCode = $("#marketFirstName").attr("code");
	var channalHtml="";
	var brandHtml="";
	var channalData=tchannal;
	
	
	if(channalData.indexOf("4")!=-1){
	  component.querywtmarketorder($("#marketFirstPkid").val());
  	  channalHtml+='<input type="checkbox" checked disabled value="4" name="channalData" onclick="component.channalOnclick(4)" id="channalData_4"><label for="channalData_4">&nbsp;网厅&nbsp;&nbsp;&nbsp;</label>'};
  	  if(channalData.indexOf("5")!=-1){
  		  component.queryztmarketorder($("#marketFirstPkid").val());
    	  channalHtml+='<input type="checkbox" checked disabled value="5" name="channalData" onclick="component.channalOnclick(5)" id="channalData_5"><label for="channalData_5">&nbsp;掌厅&nbsp;&nbsp;&nbsp;</label>'};
  	  if(channalData.indexOf("6")!=-1){
    	  channalHtml+='<input type="checkbox" checked disabled value="6" name="channalData" onclick="component.channalOnclick(6)" id="channalData_6"><label for="channalData_6">&nbsp;短厅&nbsp;&nbsp;&nbsp;</label>'
    		 
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
	
  	  
  	//cmdCotent(指令)保存当前文本框的值
       vdefault = $("#cmdCotent").val();
  $("#cmdCotent").focus(function() {
          //获得焦点时，如果值为默认值，则设置为空
     if ($("#cmdCotent").val() == vdefault) {
         this.value = "";
    }
      });
   $("#cmdCotent").blur(function() {
  //失去焦点时，如果值为空，则设置为默认值
      if ($("#cmdCotent").val() == "") {
      	$("#cmdCotent").val(vdefault) ;
  }
  });
  
	      
	// 日期控件
		var myCalendar = new dhtmlXCalendarObject( [ "beginTime", "endTime" ]);
		myCalendar.setPosition($("#beginTime").offset().top - 600, $(
				"#beginTime").offset().left + 100);
		// component.initEditor("dhx_skyblue");
		 var date=new Date();
		 var m=parseInt(date.getMonth())+1;
		 var d=date.getDate();
		if(m<10){m="0"+m};
		if(d<10){d="0"+d.toString();}
		 $("#beginTime").val(date.getFullYear()+"-"+m+"-"+d);
		component.initValidate();
		component.queryBossMarketSecond(0, 999);// 二级boss
		//component.queryBossMarketFirst();// 一级boss
		component.queryOrgInfoList(0, 1000);// 营业厅查询
		component.queryTargetGroupList(0, 1000);//目标组织
		component.queryMarketTemplate(0, 1000);//协议查询
		
	});

function beforeCheck(treeId, treeNode) {
	return (treeNode.doCheck !== false);
}

function zonCheck(e, treeId, treeNode) {
	if(treeNode.checked){
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					zbusiTreeArray.push(treeNode.children[i]);
				}
			}else{
				zbusiTreeArray.push(treeNode);
			}
		}else{
			zbusiTreeArray.push(treeNode);
		}
	}else{
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					zbusiTreeArray.remove(treeNode.children[i]);
				}
			}else{
				zbusiTreeArray.remove(treeNode);
			}
		}else{
			zbusiTreeArray.remove(treeNode);
		}
	}
}

function wonCheck(e, treeId, treeNode) {
	if(treeNode.checked){
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					wbusiTreeArray.push(treeNode.children[i]);
				}
			}else{
				wbusiTreeArray.push(treeNode);
			}
		}else{
			wbusiTreeArray.push(treeNode);
		}
	}else{
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					wbusiTreeArray.remove(treeNode.children[i]);
				}
			}else{
				wbusiTreeArray.remove(treeNode);
			}
		}else{
			wbusiTreeArray.remove(treeNode);
		}
	}
}	

function donCheck(e, treeId, treeNode) {
	if(treeNode.checked){
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					dbusiTreeArray.push(treeNode.children[i]);
				}
			}else{
				dbusiTreeArray.push(treeNode);
			}
		}else{
			dbusiTreeArray.push(treeNode);
		}
	}else{
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					dbusiTreeArray.remove(treeNode.children[i]);
				}
			}else{
				dbusiTreeArray.remove(treeNode);
			}
		}else{
			dbusiTreeArray.remove(treeNode);
		}
	}
}	

function zGiftonCheck(e, treeId, treeNode) {
	if(treeNode.checked){
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					zGiftTreeArray.push(treeNode.children[i]);
				}
			}else{
				zGiftTreeArray.push(treeNode);
			}
		}else{
			zGiftTreeArray.push(treeNode);
		}
	}else{
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					zGiftTreeArray.remove(treeNode.children[i]);
				}
			}else{
				zGiftTreeArray.remove(treeNode);
			}
		}else{
			zGiftTreeArray.remove(treeNode);
		}
	}
}

function wGiftonCheck(e, treeId, treeNode) {
	if(treeNode.checked){
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					wGiftTreeArray.push(treeNode.children[i]);
				}
			}
		}else{
			wGiftTreeArray.push(treeNode);
		}
	}else{
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					wGiftTreeArray.remove(treeNode.children[i]);
				}
			}else{
				wGiftTreeArray.remove(treeNode);
			}
		}else{
			wGiftTreeArray.remove(treeNode);
		}
	}
}

function dGiftonCheck(e, treeId, treeNode) {
	if(treeNode.checked){
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					dGiftTreeArray.push(treeNode.children[i]);
				}
			}
		}else{
			dGiftTreeArray.push(treeNode);
		}
	}else{
		if(treeNode.pId == null){
			if(treeNode.children){
				for(var i=0;i<treeNode.children.length;i++) {
					dGiftTreeArray.remove(treeNode.children[i]);
				}
			}else{
				dGiftTreeArray.remove(treeNode);
			}
		}else{
			dGiftTreeArray.remove(treeNode);
		}
	}
}

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
	var channalHtml="";
	$("#tab").hide();
	tab.hideTab('tab_1',true);
	tab.hideTab('tab_2',true);
	tab.hideTab('tab_3',true);
	  var channalData=$("#tchannal").val();
	  if(channalData.indexOf("6")!=-1){
    	  $("#tab").show();
    	  tab.showTab('tab_3',true);
    	  if(tab.getActiveTab()=="tab_3"){
				 tab.setTabActive('tab_1');
			 }
    	  tab.setTabActive('tab_3');
    	  $("#tab").height(1050);	
    		  };
    	  if(channalData.indexOf("5")!=-1){
        	  $("#tab").show();
        	  tab.showTab('tab_2',true);
        	  tab.setTabActive('tab_2');
        	  $("#tab").height(750);	}
      if(channalData.indexOf("4")!=-1){
    	  $("#tab").show();
    	  tab.showTab('tab_1',true);
    	  tab.setTabActive('tab_1');
    	  $("#tab").height(750);	}
       
	tab.attachEvent("onSelect", function(id,last_id) {
		tabOnSelect( id,last_id);
	    return true;
	});
}
var editor;

var component = {
	// 初始化html编辑器
	// initEditor : function (skin) {
	// dhtmlx.image_path =
	// "/ecu_center_manage/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxEditor/codebase/imgs/";
	// document.getElementById("editorObj").innerHTML = "";
	// editor = new dhtmlXEditor("editorObj", skin);
	//		     
	// },
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
		getTargetGroupInfo : function(channal){//获取目标组织
			try {
				var groupId = ''
				var state = '1';
				
				var targetGroupInfo = new Array();
				$("#selecTargetGroupInfo"+channal+" option").each(function () {
					 var element = {};
		             groupId = $(this).val(); //获取单个value
					 element.groupId = groupId;
					 element.channel=channal;
					 element.state = state;
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
				var giftNum = '0';
				var giftSendType = '';
				var giftCountState = '0';
				
				var giftInfo = new Array();
				
				var bossParentGiftId = "";
				var parentGiftName = "";
				var treeArray = new Array();
				var nodeArray = new Array();
				if(channel == "5"){
					treeArray = zGiftTreeArray;
					nodeArray = zGiftNodes;
				}
				if(channel == "4"){
					treeArray = wGiftTreeArray;
					nodeArray = wGiftNodes;
				}
				if(channel == "6"){
					treeArray = dGiftTreeArray;
					nodeArray = dGiftNodes;
				}
				
				for(var i = 0; i < treeArray.length; i++){
					var element = {};
					giftName = treeArray[i].name;
					bossGiftId = treeArray[i].id;
					bossParentGiftId = treeArray[i].pId==null?"0":treeArray[i].pId;
					if(bossParentGiftId != "0"){
						var nodeName = component.getNodeName(nodeArray,bossParentGiftId);
						parentGiftName = nodeName;
						element.parentGiftCode = bossParentGiftId;
						element.parentGiftName = parentGiftName;
					}else{
						element.parentGiftCode = "";
						element.parentGiftName = "";
					}
					element.giftName = giftName;
					 element.channel = channel;
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
					 element.channel = channel;
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
		         **/
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
		             giftSendType = $(this).attr("giftSendType");
		             giftCountState = $(this).attr("giftCountState");
					 
					 element.giftName = giftName;
					 element.channel = channel;
					 element.giftPrice = giftPrice;
					 element.imgDir = imgDir;
					 element.giftComment = giftComment;
					 element.bossGiftId = bossGiftId;
					 element.state = state;
					 element.giftGetTypes = giftGetTypes;
					 element.giftNum = giftNum;
					 element.giftSendType = giftSendType;
					 element.giftType = giftSendType;
					 element.giftCountState=giftCountState;
					 giftInfo.push(element);
		         });
				//alert($.toJSON(giftInfo)+"=====$.toJSON(giftInfo)======");
				return  $.toJSON(giftInfo);
			} catch (e) {
				return "";
			}
			
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
			var parentBizCode = "";
			var parentBizName = "";
			var bindbiz = new Array();
			var treeArray = new Array();
			var nodeArray = new Array();
			if(channel == "5"){
				treeArray = zbusiTreeArray;
				nodeArray = zNodes;
			}
			if(channel == "4"){
				treeArray = wbusiTreeArray;
				nodeArray = wNodes;
			}
			if(channel == "6"){
				treeArray = dbusiTreeArray;
				nodeArray = dNodes;
			}
			for(var i = 0; i < treeArray.length; i++){
				var element = {};
				bizName = treeArray[i].name;
				bizCode = treeArray[i].id;
				city = treeArray[i].cityId;
				parentBizCode = treeArray[i].pId==null?"0":treeArray[i].pId;
				if(parentBizCode != "0"){
					var nodeName = component.getNodeName(nodeArray,parentBizCode);
					parentBizName = nodeName;
					element.parentBizCode = parentBizCode;
					element.parentBizName = parentBizName;
				}else{
					element.parentBizCode = "";
					element.parentBizName = "";
				}
				
				element.bizCode = bizCode;
				 element.channel = channel;
				 element.bizName = bizName;
				 element.bizId = bizId;
				 element.city = city;
				 element.bizComment = bizComment;
				 element.bizType = bizType;
				 element.state = state;
				 element.gprsCode = gprsCode;
				 bindbiz.push(element);
			}
			/**
			$("#selectBusiness"+channel+" option").each(function () {
				 var element = {};
	             var bizName = $(this).text(); //获取单个text
	             var bizCode = $(this).val(); //获取单个value
	             bizId = $(this).attr("bizId");
				 city = $(this).attr("cityId");
				 
				 element.bizCode = bizCode;
				 element.channel = channel;
				 element.bizName = bizName;
				 element.bizId = bizId;
				 element.city = city;
				 element.bizComment = bizComment;
				 element.bizType = bizType;
				 element.state = state;
				 element.gprsCode = gprsCode;
				 bindbiz.push(element);
	         });	
			//alert($.toJSON(bindbiz)+"=======$.toJSON(bindbiz)======================");
			**/
			return  $.toJSON(bindbiz);
		} catch (e) {
			return "";
		}
		
	},
	getNodeName:function(treeArray,code){
		var nodeName = "";
		for(var t = 0; t < treeArray.length; t++){
			var id = treeArray[t].id;
			if(code == id){
				nodeName = treeArray[t].name;
			}
		}
		return nodeName;
	},
	changPayType : function(){
		var check = $("input[name=isNeedMoney]").attr("checked");
		if(check=="checked"){
			//payname 
			$("input[name=payname]").removeAttr("disabled");
		}else{
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
	saveForm : function() {//添加
		
		if (!$('#addForm').valid()) {
			return;
		}
		var marketFirstPkid = $("#marketFirstPkid").val();
//		var marketSecondCode = $("#marketSecondName").attr("code");
		var marketSecondCode = z.getSelectedValue();
		if(component.querySecondByCode(0,10,marketSecondCode)){
				UOMPComp.showFailedDialog("该营销案已添加！", "");
				return;
		}
//		var marketSecondName = $("#marketSecondName").val();// boss名称
		var marketSecondName = z.getSelectedText(); 
		var city = $("#city").val();
		var viewName = $("#viewName").val();
		var imgDir = "";
		var flashDir = "";
		var toObject = $("#toObject").val();
		var activityComment = $("#activityComment").val();
		var isNeedMoney = $("input[name=isNeedMoney]:checked").val();
		var isNeedCashBooks=$("input[name='isNeedCashBooks']:checked").val();
		isNeedMoney = isNeedMoney == undefined ? "0" : isNeedMoney;
		var moneyVal = $("#bossMoneyVal").val();

		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		var isFlashView = "";
		var isListView = "0";
		var viewTemplateId = "";
		var pactTemplateId = $("#pactTemplateName").attr("templateId");
		var state = '1';
		var verifyState = '0';
		var giftGetTypes = component.getArrayValue($('input[name=giftGetTypes]:checked')).toString();
		var notice = $("#notice").val();
		var sweetPrompt = $("#sweetPrompt").val();
		var proVerifyState = '0';//审核状态
		var localVerifyState = '0';//地市审核状态
		var marketSecondType= '';
		var isInGroup='';//是否目标营销案--掌厅
		var dtIsInGroup='';//是否目标营销案-短厅
		var wtIsInGroup='';//是否目标营销案-网厅
		var targetGroupInfo //目标组织--掌厅
		var wtTargetGroupInfo //目标组织--网厅
		var dtTargetGroupInfo//目标组织--短厅
		
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
		var wtMarketSecondBizInfo,wtMarketSecondGiftInfo;
		var marketSecondBizInfo,marketSecondGiftInfo;
		var dtMarketSecondBizInfo,dtMarketSecondGiftInfo;
		  var wtMarketOrder ,ztMarketOrder;
		if($("#channalData_4").attr('checked')=='checked'){
			wtTargetGroupInfo = component.getTargetGroupInfo(4);//目标组织--网厅
			var selecTargetGroupInfo4 = $("#selecTargetGroupInfo4 option");
			if(component.getArrayValue(selecTargetGroupInfo4).toString()==""){
				wtIsInGroup='0';
			}else{
				wtIsInGroup='1';
			}
			wtMarketSecondBizInfo=component.getmarketSecondBizInfo(4);//绑定业务
			wtMarketSecondGiftInfo = component.getmarketSecondGiftInfo(4);//绑定礼品信息,新增礼品信息
			wtMarketOrder="";
			    $("select[name=wtMarketOrder] option").each(
			    		  function() {
			    			  wtMarketOrder=wtMarketOrder+$(this).val()+".";
			    		  }
			    		 );
			    if(wtMarketOrder.length>0){
			    	wtMarketOrder=wtMarketOrder.substring(0,wtMarketOrder.length-1);}
		}
		if($("#channalData_5").attr('checked')=='checked'){
			targetGroupInfo = component.getTargetGroupInfo(5);//目标组织--掌厅
			var selecTargetGroupInfo5 = $("#selecTargetGroupInfo5 option");
			if(component.getArrayValue(selecTargetGroupInfo5).toString()==""){
				isInGroup='0';
			}else{
				isInGroup='1';
			}
			 marketSecondBizInfo=component.getmarketSecondBizInfo(5);//绑定业务
			 marketSecondGiftInfo = component.getmarketSecondGiftInfo(5);//绑定礼品信息,新增礼品信息
			 ztMarketOrder="";
			    $("select[name=ztMarketOrder] option").each(
			    		  function() {
			    			  ztMarketOrder=ztMarketOrder+$(this).val()+".";
			    		  }
			    		 );
			    if(ztMarketOrder.length>0){
			    	ztMarketOrder=ztMarketOrder.substring(0,ztMarketOrder.length-1);}
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
			// cmdCotent=cmdCotent==vdefault?"":cmdCotent;
			 dtSpCode=$("#dtSpCode").val();
			 dtSuccMsg=$("#dtSuccMsg").val();
			 dtSendSuccMsgEnable=$("input[name=dtSendSuccMsgEnable]:checked").val();
			 dtIsRecommend=$("input[name=dtIsRecommend]:checked").val();
			 dtUnallowBusi=$("#dtUnallowBusi").val();
			 dtMutexBusi=$("#dtMutexBusi").val();
			 dtBeforeEffectMsg=$("#dtBeforeEffectMsg").val();
			 dtExpireMsg=$("#dtExpireMsg").val();
			  var dtBrand="";
			    $("input[name='dtBrand']:checked").each(function () {
			    	dtBrand=dtBrand+this.value+";";
			    });
			    if(dtBrand.length>0){
			    	if(dtBrand.indexOf("QQT;DGDD;SZX;")>-1){
			    		dtBrand="QPP;";
			    	}
			    }
			 busiMarketSms=$("#busiMarketSms").val();
			 cmdEnableFlag =($("#cmdEnableFlag").attr('checked')=='checked')?1:0;
			 dtMutexEnable =($("#dtMutexEnable").attr('checked')=='checked')?1:0;
			 dtMarketSecondBizInfo=component.getmarketSecondBizInfo(6);//绑定业务
			 dtMarketSecondGiftInfo = component.getmarketSecondGiftInfo(6);//绑定礼品信息,新增礼品信息
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
		
		  var channalData="";
		    $("input[name='channalData']:checked").each(function () {
		    	channalData=channalData+this.value+".";
		    });
		    if(channalData.length>0){
		    	channalData=channalData.substring(0,channalData.length-1);} 
		
		
		
		var OrgInfoList = component.getOrgInfoList();//营业厅
		var payShow = component.getPayShow();//支付方式
		$.singleReq( {
			data : {
				"reqUrl" : "marketSecond",
				"reqMethod" : "addMarketSecondBean",
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
				"isInGroup":isInGroup,
				"wtIsInGroup":wtIsInGroup,
				"dtIsInGroup":dtIsInGroup,
				"fnestificationType":fnestificationType,
				"firstTop":firstTop,
				"pactTemplateContent":pactTemplateContent,
				"pactTemplateId":pactTemplateId,
				"channalData":channalData,
				
				//二级营销案绑定业务
				"marketSecondBizInfo":marketSecondBizInfo,
				"wtMarketSecondBizInfo":wtMarketSecondBizInfo,
				"dtMarketSecondBizInfo":dtMarketSecondBizInfo,
 				"marketSecondGiftInfo":marketSecondGiftInfo,
				"dtMarketSecondGiftInfo":dtMarketSecondGiftInfo,
				"wtMarketSecondGiftInfo":wtMarketSecondGiftInfo,
				"targetGroupInfo":targetGroupInfo,
				"dtTargetGroupInfo":dtTargetGroupInfo,
				"wtTargetGroupInfo":wtTargetGroupInfo,
				"OrgInfoList":OrgInfoList,
				"payShow":payShow,
				
				"sweetPrompt":sweetPrompt,
				"isNeedCashBooks":isNeedCashBooks,
				"notice":notice,
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
				 "dtBrandId":dtBrand,
				 "dtConfirmExtraMsg":dtConfirmExtraMsg,
				 "ztMarketOrder":ztMarketOrder,
				 "wtMarketOrder":wtMarketOrder
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
	// 查询boss二级营销案
	queryBossMarketSecond : function(start, end) {
		
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
//		if(tchannal==undefined || marketFirstCode==undefined){
//			return;
//		}
			
		$.singleReq( {
			data : {
				"reqUrl" : "bossMarket",
				"reqMethod" : "queryBossMarketSecondList",
//				"tchannal" : tchannal,
				"marketFirstCode" : marketFirstCode,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					var str="";
					for ( var int = 0; int < result.length; int++) {
						var option = "<option value='"
								+ result[int].marketSecondCode
								+ "' moneyValue='" + result[int].moneyVal
								+ "' >" + result[int].marketSecondCode+"["+result[int].marketSecondName+"]"
								+ "</option>";
						// alert(option);
						$("#bossMarketSecondName").append(option);
						 var key = result[int].marketSecondCode;
							var val = result[int].moneyVal;
							str += key+":"+val+",";
					}
					str=str.substr(0,str.length-1);
					
					
					z = dhtmlXComboFromSelect("bossMarketSecondName");
	                z.enableFilteringMode(true);
	                z.attachEvent("onChange", function(){component.setBossSecondName( str )});
				} else {
					UOMPComp.showFailedDialog("失败！--queryBossMarketSecondList---", "");
				}
			}
		});
	},
	// 查询boss一级营销案
	queryBossMarketFirst : function(start, end) {
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "bossMarket",
				"reqMethod" : "queryBossMarketFirstList",
//				"tchannal" : tchannal,
				"marketFirstCode" : marketFirstCode,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					// alert(result[0].marketFirstName);
		} else {
			UOMPComp.showFailedDialog("失败！--queryBossMarketFirstList---", "");
		}
	}
		});
	},
	querySecondByCode : function(start, end, code) {
		$.singleReq( {
			data : {
				"reqUrl" : "marketSecond",
				"reqMethod" : "queryMarketSecondCount",
				"marketSecondCode" : code
			},
			
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					if(result>0){
						return true;						
					}else{
						return false;
					}
					
				} else {
					UOMPComp.showFailedDialog("失败！--queryMarketSecondCount---", "");
					return true;
				}
			}
		});
		
	},
	setBossSecondName : function(str) {
		 var code=$.trim(z.getSelectedValue());
		 var name=$.trim(z.getSelectedText());
		 name= name.substring(code.length+1,name.length-1);
		 eval('var marketJson = {' + str+"}");
		 var moneyValue =marketJson[code];
		if ("请选择" != name) {
			if(component.querySecondByCode(0,10,code)){
				UOMPComp.showFailedDialog("该营销案已添加！", "");
				return;
			}
			$("#marketSecondName").val(name);
			$("#viewName").val(name);
			$("#marketSecondName").attr("code", code);
			$("#bossMoneyVal").val(moneyValue);
			$("#bossMoneyVal").attr("disabled",true);
			$("input[name=isNeedMoney]").attr("disabled",true);
			if(moneyValue=="" || moneyValue==0){
				$("input[name=payname]").attr("disabled",true);
				$("input[name=isNeedMoney]").removeAttr("checked");
			}else{
				$("input[name=isNeedMoney]").attr("checked","checked");
				component.changPayType();
			}

			$("#selectBusiness4").empty();
			$("#selectBusiness5").empty();
			$("#selectBusiness6").empty();
			$("#bossBindBizName4").empty();
			$("#bossBindBizName5").empty();
			$("#bossBindBizName6").empty();
			$("#bossGiftInfo").empty();
			
			component.queryBossbindBiz();
			component.queryBossGiftInfo();

		} else {
			//code
			
			$("#marketSecondName").val("");
			$("#marketSecondName").attr("code", "");
			$("input[name=payname]").attr("disabled",true);
			$("#bossMoneyVal").val("");
			$("#selectBusiness4").empty();
			$("#selectBusiness5").empty();
			$("#selectBusiness6").empty();
			$("#bossBindBizName4").empty();
			$("#bossBindBizName5").empty();
			$("#bossBindBizName6").empty();
			$("#bossGiftInfo").empty();
			$("input[name=isNeedMoney]").removeAttr("checked");
		}
	},
	// 查询boss绑定业务信息
	queryBossbindBiz : function(start, end) {
		var marketSecondCode = z.getSelectedValue();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "bossMarket",
				"reqMethod" : "queryBossBindBizList",
//				"tchannal" : tchannal,
				"marketSecondCode" : marketSecondCode,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				zNodes = new Array();
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var bizCode = result[int].bizCode;
						var bizName = result[int].bizName;
						var bizId = result[int].bizId;
						var cityId = result[int].cityId;
						var parentBizCode = result[int].parentBizCode;
						//var parentBizName = result[int].parentBizName;
						/**
						var option = "<option value='" + bizCode +"' +  bizId='"+bizId +"' cityId='"+cityId+"' >" + result[int].bizName + "</option>";
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
						//obj.parentBizName = parentBizName;
						zNodes.push(obj);
						dNodes.push(obj);
						wNodes.push(obj);
					}
					$.fn.zTree.init($("#treeZT"), zsetting, zNodes);
					$.fn.zTree.init($("#treeDT"), dsetting, dNodes);
					$.fn.zTree.init($("#treeWT"), wsetting, wNodes);
				} else {
					UOMPComp.showFailedDialog("失败！--queryBossBindBizList---", "");
				}
			}
		});
	},
	// 查询boss礼品信息
	queryBossGiftInfo : function(start, end) {
		var marketSecondCode = z.getSelectedValue();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "bossMarket",
				"reqMethod" : "queryBossGiftInfoList",
//				"tchannal" : tchannal,
				"marketSecondCode" : marketSecondCode,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var bossGiftId = result[int].bossGiftId;
						var bossParentGiftId = result[int].bossParentGiftId;
						var giftName = result[int].giftName;
						var cityId =result[int].cityId;
						var giftComment = result[int].giftComment;
//						var option = "<option value='"+bossGiftId+"' cityId='"+cityId+"' giftComment='"+giftComment+"'" +
//						"giftPrice='' imgDir='' state='1' giftGetTypes='' giftNum='0' giftSendType='' giftCountState=''"+
//								">" + giftName + "</option>";
						
//						var option = $("<option>").val(bossGiftId);
//						option.text(giftName);
//						option.attr("cityId",cityId);
//						option.attr("giftComment",giftComment);
//						option.attr("giftPrice",'');
//						option.attr("imgDir",'');
//						option.attr("giftComment",'');
//						option.attr("state",'1');
//						option.attr("giftGetTypes",'');
//						option.attr("giftNum",'0');
//						option.attr("giftSendType",'');
//						option.attr("giftCountState",'');
//						 alert(option);
//						$("#bossGiftInfo4").append(option);
//						$("#bossGiftInfo5").append(option);
//						$("#bossGiftInfo6").append(option);
						var obj = new Object();
						obj.id = bossGiftId;
						obj.pId = bossParentGiftId;
						obj.name = giftName;
						obj.cityId = cityId;
						zGiftNodes.push(obj);
						wGiftNodes.push(obj);
						dGiftNodes.push(obj);
					}
					$.fn.zTree.init($("#treeGiftZT"), zGiftsetting, zGiftNodes);
					$.fn.zTree.init($("#treeGiftWT"), wGiftsetting, wGiftNodes);
					$.fn.zTree.init($("#treeGiftDT"), dGiftsetting, dGiftNodes);
				} else {
					UOMPComp.showFailedDialog("失败！--queryBossGiftInfoList---", "");
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
//		if(tchannal==undefined || cityId==undefined){
//			return;
//		}
		
		$.singleReq( {
			data : {
				"reqUrl" : "orgHandle",
				"reqMethod" : "queryOrgInfoList",
//				"tchannal" : tchannal,
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
								+ "' name='orgInfoName'>" + orgName + "&nbsp;";
						$("#yingyeting").append(input);
					}
				} else {
					UOMPComp.showFailedDialog("失败！---queryOrgInfoList---", "");
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
				"reqMethod" : "queryTargetGroupInfo",
//				"tchannal" : tchannal,
				"city" : cityId,
				"state" : "1",
//				"groupType" : "1",
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
						$("#targetGroupInfoList4").append(option);
						$("#targetGroupInfoList5").append(option);
						$("#targetGroupInfoList6").append(option);
					}
				} else {
					UOMPComp.showFailedDialog("失败！--queryTargetGroupInfo---", "");
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
					}
				} else {
					UOMPComp.showFailedDialog("失败！---queryMarketTemplate---", "");
				}
			}
		});
	},
	// 添加业务
	addTargetGroupInfo : function(channal) {

		var option = $("#targetGroupInfoList"+channal+" option:selected");
		$("#selecTargetGroupInfo"+channal).append(option);

	},
	removeTargetGroupInfo : function(channal) {
		// selectBusiness
		var option = $("#selecTargetGroupInfo"+channal+" option:selected");
		$("#targetGroupInfoList"+channal).append(option);
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
		if($("#qtgiftGetTypes").attr("checked")=="checked"){
			$("input[name=giftGetTypes][gtype=simple]").removeAttr("checked");
			$("input[name=orgInfoName]").removeAttr("checked");
			$("input[name=giftGetTypes][gtype=simple]").attr("disabled","true");
			$("#orgInfoDisplay").css("display","none");
		}else{
			$("input[name=giftGetTypes][gtype=simple]").removeAttr("disabled");
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
	addOption:function(input){

		var obj=$("#wtMarketOrder option[value='add']");
		var objZT=$("#ztMarketOrder option[value='add']");
		if(input.value!=""){ 
			if(obj[0]){
				$("#wtMarketOrder option[value='add']").text(input.value);
				}else{
					$("#wtMarketOrder").prepend("<option value='add'>"+input.value+"</option>");
				}
			
			if(objZT[0]){
				$("#ztMarketOrder option[value='add']").text(input.value);
				}else{
					$("#ztMarketOrder").prepend("<option value='add'>"+input.value+"</option>");
				}
			}else{
					if(obj[0]){$("#wtMarketOrder option[value='add']").remove();	}
					if(obj[0]){$("#ztMarketOrder option[value='add']").remove();	}
	 	}

		
	}
}