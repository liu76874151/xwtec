	var channalSel="";//--渠道编码
	var isSJ="";//--是否是统一营销案
	var submitBack=0;//提交图片返回标识
	var combo,combo1,combo2,combo3,combo4,combo5,combo6,combo7,combo8,combo9,combo10,combo11,combo12,combo13;
	var combos=[combo,combo1,combo2,combo3,combo4,combo5,combo6,combo7,combo8,combo9,combo10,combo11,,combo12,combo13];
    var cityLength=1;
    var vdefault;
	$(document).ready(function () {
	window.dhx_globalImgPath = GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/imgs/";
	combos[0] = new dhtmlXCombo("marketFirstNameSel", "alfa", 200);
	
	component.initTemplate();
	 isSJ=$("#isSJ").val();
	 if(isSJ==1){
		 component.initArea();
		 $("#tab").height(1450);
	 }else{
		 $("#tab").height(280); 
	 }
	 var myCalendarBeginTime=new dhtmlXCalendarObject(["beginTime"]);
	 var myCalendar=new dhtmlXCalendarObject(["endTime"]);
	 var date=new Date();
	 var m=parseInt(date.getMonth())+1;
	 var d=date.getDate();
	if(m<10){m="0"+m};
	if(d<10){d="0"+d.toString();}
	 $("#beginTime").val(date.getFullYear()+"-"+m+"-"+d);
	 $("#channalData_4").click(function() {
		 if(this.checked){
			 $("#tab").show();
			 tab.showTab('tab_1',true);
			 tab.setTabActive('tab_1');
				
			 }else{
				// tab.setTabActive('tab_3');
				 tab.hideTab('tab_1',true);
				 var channalDataTemp=",";
					$("input[name='channalData']:checked").each(function () {
						channalDataTemp=channalDataTemp+this.value+",";
				    });
					if(channalDataTemp.indexOf(",6,")>-1){
						tab.setTabActive('tab_3');
						$("#tab").show();
					}else if(channalDataTemp.indexOf(",5,")>-1){
						tab.setTabActive('tab_2');
						$("#tab").show();
						
					}else{
						$("#tab").hide();
					}
				 }
		 }); 
	 $("#channalData_5").click(function() {
		 if(this.checked){
			 $("#tab").show();
			 tab.showTab('tab_2',true);
			 tab.setTabActive('tab_2');
			 }else{
  				// tab.setTabActive('tab_1');
				 tab.hideTab('tab_2',true);
				 tab.setTabActive(tab.getActiveTab());
				 var channalDataTemp=",";
					$("input[name='channalData']:checked").each(function () {
						channalDataTemp=channalDataTemp+this.value+",";
				    });
					if(channalDataTemp.indexOf(",4,")>-1){
						tab.setTabActive('tab_1');
						$("#tab").show();
					}else if(channalDataTemp.indexOf(",6,")>-1){
						tab.setTabActive('tab_3');
						$("#tab").show();
					}else{
						$("#tab").hide();
					}
				 }
		 }); 
	 $("#channalData_6").click(function() {
		 if(this.checked){
			 $("#tab").show();
			 tab.showTab('tab_3',true);
			 tab.setTabActive('tab_3');
			 }else{
				 //tab.setTabActive('tab_1');
				 tab.hideTab('tab_3',true);
				 var channalDataTemp=",";
					$("input[name='channalData']:checked").each(function () {
						channalDataTemp=channalDataTemp+this.value+",";
				    });
					if(channalDataTemp.indexOf(",4,")>-1){
						tab.setTabActive('tab_1');
						$("#tab").show();
					} else if(channalDataTemp.indexOf(",5,")>-1){
						tab.setTabActive('tab_2');
						$("#tab").show();
						
					}else{
						$("#tab").hide();
					}
				 }
		 }); 
	 
	 $("#templateName").change(function() {
		 $("#marketContent").html("");
		 var content = $("#templateName option:selected").attr(
		 				"content");
		 if("undefined"!=content){
		 $("#marketContent").html(content);}
		 }); 
		component.initValidate();
		
		CKEDITOR.replace( 'marketContent',
				{
				toolbar :
				[
				//加粗 斜体， 下划线 穿过线 下标字 上标字
				['Bold','Italic','Underline','Strike','Subscript','Superscript'],
				// 数字列表 实体列表 减小缩进 增大缩进
				['NumberedList','BulletedList','-','Outdent','Indent'],
				//左对齐 居中对齐 右对齐 两端对齐
				['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
				//超链接 取消超链接 锚点
				['Link','Unlink','Anchor'],
				//图片 flash 表格 水平线 表情 特殊字符 分页符
				//['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
				['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
				'/',
				// 样式 格式 字体 字体大小
				['Styles','Format','Font','FontSize'],
				//文本颜色 背景颜色
				['TextColor','BGColor'],
				//全屏 显示区块
				['Maximize', 'ShowBlocks','-']
				]
				}
				);
		
		
		
		
		 //$(".input").each(function() {
			       //保存当前文本框的值
			 	         vdefault = $("#instructionContent").val();
			         $("#instructionContent").focus(function() {
			 	            //获得焦点时，如果值为默认值，则设置为空
			            if ($("#instructionContent").val() == vdefault) {
			                this.value = "";
			           }
			 	        });
			        $("#instructionContent").blur(function() {
			            //失去焦点时，如果值为空，则设置为默认值
				            if ($("#instructionContent").val() == "") {
				            	$("#instructionContent").val(vdefault) ;
			            }
				        });
				  //  });
});

function tabOnSelect(id,last_id){//--处理tabbar切换事件
	//alert(id+"=====id====22=isSJ==="+isSJ);
	
	if(id=="tab_3"){
		 $("#tab").height(120); 
	}else if(id=="tab_1"){
		if(isSJ==0){
		 $("#tab").height(560); 
		 }else{
			 $("#tab").height(1450);
		 }
	}else if(id=="tab_2"){
		if(isSJ==0){
			 $("#tab").height(280); 
			 }else{
				$("#tab").height(1130);
			 }
	}

}

function doOnInit(){//--初始化tabbar
        	
        	tab.hideTab('tab_3',true);
        	tab.hideTab('tab_1',true);
        	tab.setTabActive('tab_2');
        	channalSel=1;
        	if(isSJ!=1){component.initBossMarket("");}//--地市营销案加载boss
        	tab.attachEvent("onSelect", function(id,last_id) {//--处理tabbar切换事件id : id of selected tab ,last_id :id of previously selected tab
        		tabOnSelect( id,last_id);
        	    return true;
        	});
	}

function addOption(input){
	if(isSJ==1){
		var obj;
		for ( var i = 1; i < cityLength+1; i++) {
			marketFirstCodeTemp=$.trim(combos[i].getSelectedValue()); 
			if(marketFirstCodeTemp!=""){//boss一级方案名称未选不加入
			 obj=$("#marketOrder_"+i+" option[value='add']");
			 objZT=$("#ztMarketOrder_"+i+" option[value='add']");
			if(input.value!=""){ 
				if(obj[0]){
					$("#marketOrder_"+i+" option[value='add']").text(input.value);
					}else{
						$("#marketOrder_"+i).prepend("<option value='add'>"+input.value+"</option>");
					}
				
				if(objZT[0]){
					$("#ztMarketOrder_"+i+" option[value='add']").text(input.value);
					}else{
						$("#ztMarketOrder_"+i).prepend("<option value='add'>"+input.value+"</option>");
					}
				}else{
						if(obj[0]){$("#marketOrder_"+i+"  option[value='add']").remove();	
						}
						if(objZT[0]){$("#ztMarketOrder_"+i+"  option[value='add']").remove();	
						}
		 	}
			
			}
		}
	}else{
			var obj=$("#marketOrder option[value='add']");
			var objZT=$("#ztMarketOrder option[value='add']");
			if(input.value!=""){ 
				if(obj[0]){
					$("#marketOrder option[value='add']").text(input.value);
					}else{
						$("#marketOrder").prepend("<option value='add'>"+input.value+"</option>");
					}
				
				if(objZT[0]){
					$("#ztMarketOrder option[value='add']").text(input.value);
					}else{
						$("#ztMarketOrder").prepend("<option value='add'>"+input.value+"</option>");
					}
				}else{
						if(obj[0]){$("#marketOrder option[value='add']").remove();	}
						if(obj[0]){$("#ztMarketOrder option[value='add']").remove();	}
		 	}
	}

	
	}
	
var component = {
		
	
	    // 初始化校验规则
			
	    initValidate: function () {
	        ValidateUtil.validate({
	            targetForm: "addForm",
	            rules: {
	        	viewName: {required: true, minlength: 1,stringMaxLength: "100",isString:"#viewName"},
	        	toObject: {required: false, stringMaxLength: "100"},
	            },
	            messages: {
	            	viewName: {required: "请输入网营一级方案名称", minlength: "长度必须大于等于{0}"},
	            	toObject: {required: ""},
	            }
	        });
	    }, 
	    
		initNewCombo:function(id,areaBossCode){
		combos[id] = new dhtmlXCombo("marketFirstNameSel_"+id, "alfa", 200);
		//
},
	initCombo : function(data,id){
	if(id==""){id=0}else{
		id=parseInt(id.substr(1));
	}
	combos[id].enableOptionAutoWidth(1);
	combos[id].enableFilteringMode(true);
	combos[id].setComboValue(null);
	combos[id].clearAll();
	combos[id].addOption(data);
	
	
	combos[id].attachEvent("onChange", function(){
		var tempText=$.trim(combos[id].getSelectedText());
		tempText=tempText.substring(tempText.indexOf('[')+1,tempText.indexOf(']'));
		$("#viewName").val(tempText)});
    },
		initTemplate:function(){
	$.singleReq({
        data: {"start": 0,
        		"end": 9999,
        		"state": 1,
        		"category":1,
        		//"channalData":4,
		    	"reqUrl": "marketTemplate",
		        "reqMethod": "queryMarketTemplateList"
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	   var result = ret.retObj.records; 
                   $.each(result, function(i, item) {
                    	 $("#templateName").append("<option value="+item.templateId+" content='"+item.content+"'>"+item.templateName+"</option>");
                         
                   });
            }
        }
    });
},
initBtnMove:function(ID){
	 $("#btnMoveUp"+ID+",#btnMoveDown"+ID).click(function() {   
		  var $opt = $("#marketOrder"+ID+" option:selected:first");   
		  if (!$opt.length) return;   
		  if (this.id == "btnMoveUp"+ID) {
		  		$opt.prev().before($opt);   
		  		}else {
		  		$opt.next().after($opt); 
		  		      } 
			}); 
	 

},
initZTBtnMove:function(ID){
	
	 $("#ztBtnMoveUp"+ID+",#ztBtnMoveDown"+ID).click(function() {   
		  var $opt = $("#ztMarketOrder"+ID+" option:selected:first");   
		  if (!$opt.length) return;   
		  if (this.id == "ztBtnMoveUp"+ID) {
		  		$opt.prev().before($opt);   
		  		}else {
		  		$opt.next().after($opt); 
		  		      } 
			}); 
	 

},
//--统一营销案的时候初始化城市
initArea:function(){
	cityLength=0;
 	$.singleReq({
         data: {
 		    	"reqUrl": "area",
 		        "reqMethod": "queryCityList"
         },
         success: function (ret) {
             if (ret.retCode == 0) {
             	var result = ret.retObj.records; 
             	var totalRecord=ret.retObj.totalRecord;
             	 var bossCodeDiv=$("#bossCodeDiv");
             	 var marketOrderDiv=$("#marketOrderDiv");
             	 var ztMarketOrderDiv=$("#ztMarketOrderDiv");
             	 var length=result.length;
             	     bossCodeDiv.empty();
             	     marketOrderDiv.empty();
             	    ztMarketOrderDiv.empty();
             	  marketOrderDiv.append('<table ><tr >');
             	 ztMarketOrderDiv.append('<table ><tr >');
             	var tt='<table><tr>';
                 $.each(result, function(i, item) {
                	if(i>0){
                		cityLength++;
                		marketOrderDiv.append('<td>'+
						                		 '<table >'+
						                		 '<tr>'+
						                		 '<td colspan="2"><b>'+
						                		 item.areaName+
						                		 ':</b></td>'+
						                		 '</tr>'+
												'<tr style="">'+
													'<td>'+
														'<select size="6"  name="marketOrder" id="marketOrder_'+i+'" style="width: 160px ;height:120px"  >'+
														'</select>'+
													'</td>'+
													'<td style="">'+
														'<input type="button" value="上移" id="btnMoveUp_'+i+'" ><br>'+
														'<input type="button" value="下移" id="btnMoveDown_'+i+'">'+
													'</td>'+
												'</tr>'+
											'</table></td>');
                		ztMarketOrderDiv.append('<td>'+
		                		 '<table >'+
		                		 '<tr>'+
		                		 '<td colspan="2"><b>'+
		                		 item.areaName+
		                		 ':</b></td>'+
		                		 '</tr>'+
								'<tr style="">'+
									'<td>'+
										'<select size="6"  name="ztMarketOrder" id="ztMarketOrder_'+i+'" style="width: 160px ;height:120px"  >'+
										'</select>'+
									'</td>'+
									'<td style="">'+
										'<input type="button" value="上移" id="ztBtnMoveUp_'+i+'" ><br>'+
										'<input type="button" value="下移" id="ztBtnMoveDown_'+i+'">'+
									'</td>'+
								'</tr>'+
							'</table></td>');
                		 tt+='<td style="width: 250px">'+item.areaName+'&nbsp;<div name="marketFirstNameSel" id="marketFirstNameSel_'+i+'" cityId="'+item.areaBossCode+
				           '" style="width: 180px;" > </div></td>';
                		                 
                		if(i%2==0){
                    	tt+='</tr><tr>';
                    		if(i<totalRecord-1){
                    			marketOrderDiv.append('</tr ><tr>');
                    			ztMarketOrderDiv.append('</tr ><tr>');
                    		}
                    		
                    	}
                		if(i+1==totalRecord){
                			marketOrderDiv.append('</tr ></table >');
                			ztMarketOrderDiv.append('</tr ></table >');
                			tt+='</tr></table >';
                		}
                	
                	}
                	
                 });
                 bossCodeDiv.append(tt);
                 $.each(result, function(i, item) {  
                	 if(i>0){
                		 component.initNewCombo(i,item.areaBossCode);
                		 component.initBossMarket("_"+i,item.areaBossCode);
                	 }
                 });
              
 			}
         }
     });
},
saveForm: function () {
    if (!$('#addForm').valid()) {
        return;
    }
    
    if(submitBack==1){
    	return;
    }else{
    	submitBack=1;
    }
    var isInBrand="";
    $("input[name='isInBrand']:checked").each(function () {
    	    isInBrand=isInBrand+this.value+".";
    });
    if(isInBrand.length>0){
    	isInBrand=isInBrand.substring(0,isInBrand.length-1);}
    
    var tChannal="";
    $("input[name='tChannal']:checked").each(function () {
    	tChannal=tChannal+this.value+".";
    });
    if(tChannal.length>0){
    	tChannal=tChannal.substring(0,tChannal.length-1);} 
    var  wtFlag= $("#channalData_4:checked").val();
    if(wtFlag!=4){
    	tChannal=undefined;
    }
    var marketOrder = "";
    $("select[name=marketOrder] option").each(
    		  function() {
    			  marketOrder=marketOrder+$(this).val()+".";
    		  }
    		 );
    if(marketOrder.length>0){
    	marketOrder=marketOrder.substring(0,marketOrder.length-1);}
    
    var ztMarketOrder = "";
    $("select[name=ztMarketOrder] option").each(
    		  function() {
    			  ztMarketOrder=ztMarketOrder+$(this).val()+".";
    		  }
    		 );
    if(ztMarketOrder.length>0){
    	ztMarketOrder=ztMarketOrder.substring(0,ztMarketOrder.length-1);}
    
    var channalData="";//--4网厅数据 ，5掌厅数据，6短厅数据
    var ztMarketShowChannel="";
    $("input[name='channalData']:checked").each(function () {
    	channalData=channalData+this.value+",";
    });
    if(channalData.length>0){
    	channalData=channalData.substring(0,channalData.length-1);} 
    $("input[name='ztMarketShowChannel']:checked").each(function () {
    	ztMarketShowChannel=ztMarketShowChannel+this.value+".";
    });
    if(ztMarketShowChannel.length>0){
    	ztMarketShowChannel=ztMarketShowChannel.substring(0,ztMarketShowChannel.length-1);} 
    alert(ztMarketShowChannel)
    var instructionContent,spreadCode;
    var isDT="0";
    if(channalData.indexOf("6")!=-1){
    	instructionContent=$("#instructionContent").val();
    	//instructionContent=instructionContent==vdefault?"":instructionContent;
    	spreadCode=$("#spreadCode").val();
    	isDT="1";
    }
   
    var viewName = $("#viewName").val();
    var yxImgDir = $("#yxImgDir").val();
    var toObject = $("#toObject").val();
    var sweetPrompt = $("#sweetPrompt").val();
    //var prize = $("#prize").val();
    var activityComment = $("#activityComment").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var marketTemplateContent=CKEDITOR.instances.marketContent.getData();
   // alert(marketTemplateContent);
    var viewTemplateId=$("#templateName option:selected").val();
    var bandType=0;
    var allProSign=$("input[name='allProSignFlag']:checked").val();
    if(allProSign=="1"){
    	allProSign=$("#allProSignFlag").val();
    }
    var type = $("input[name='type']:checked").val();
    	type==0?bandType=0:(bandType=type,type=1);
    	var specialFlag= $("input[name='specialFlag']:checked").val();
   var marketFirstCode=$.trim(combos[0].getSelectedValue());
   var marketFirstName=$.trim(combos[0].getSelectedText());
   if(marketFirstName.indexOf("[") > 0){
	   marketFirstName = marketFirstName.substring(0,marketFirstName.indexOf("["));
	}
      
    var unityFlag=isSJ==1?1:0;
    var city;
    cityCount=1;
//    alert(isSJ+"====isSJ==1======")
    if(isSJ==1){
    	var marketFirstNameTemp="";
    	var marketFirstCodeTemp="";
    	var marketOrderTemp="";
    	var ztMarketOrderTemp="";
    	marketFirstName="";
    	marketFirstCode="";
    	marketOrder = "";
    	ztMarketOrder = "";
    	city="";
    	cityCount=0;
	for ( var i = 1; i < cityLength+1; i++) {
		marketFirstCodeTemp=$.trim(combos[i].getSelectedValue());
		if(marketFirstCodeTemp!=""){//boss一级方案名称未选不加入
			cityCount++;
		marketFirstCode+=marketFirstCodeTemp+",";
		city+=$("#marketFirstNameSel_"+i).attr("cityId")+",";
		marketFirstNameTemp=$.trim(combos[i].getSelectedText());
		if(marketFirstNameTemp.indexOf("[") > 0){
			marketFirstNameTemp = marketFirstNameTemp.substring(0,marketFirstNameTemp.indexOf("["));
		}
		marketFirstName+=marketFirstNameTemp+",";
		
		$("#marketOrder_"+i+" option").each(
	    		  function() {
	    			  marketOrderTemp=marketOrderTemp+$(this).val()+".";
	    		  }
	    		 );
	    if(marketOrderTemp.length>0){
	    	marketOrderTemp=marketOrderTemp.substring(0,marketOrderTemp.length-1);
	    	}
	    marketOrder+=marketOrderTemp+",";
	    marketOrderTemp="";
	    
	    $("#ztMarketOrder_"+i+" option").each(
	    		  function() {
	    			  ztMarketOrderTemp=ztMarketOrderTemp+$(this).val()+".";
	    		  }
	    		 );
	    if(ztMarketOrderTemp.length>0){
	    	ztMarketOrderTemp=ztMarketOrderTemp.substring(0,ztMarketOrderTemp.length-1);
	    	}
	    ztMarketOrder+=ztMarketOrderTemp+",";
	    ztMarketOrderTemp="";
		}
		
		
	}
	if(marketFirstCode.length>0){
		marketFirstCode=marketFirstCode.substring(0,marketFirstCode.length-1);
    	}
	if(marketFirstName.length>0){
		marketFirstName=marketFirstName.substring(0,marketFirstName.length-1);
    	}
	if(marketOrder.length>0){
		marketOrder=marketOrder.substring(0,marketOrder.length-1);
    	}
	if(ztMarketOrder.length>0){
		ztMarketOrder=ztMarketOrder.substring(0,ztMarketOrder.length-1);
    	}
	if(city.length>0){
		city=city.substring(0,city.length-1);
    	}
    }
    $.singleReq({
        data: {
            "reqUrl": "marketFirst",
            "reqMethod": "saveMarketFirst",
            "marketFirstName": marketFirstName,
            "marketFirstCode": marketFirstCode,
            "viewName": viewName,
            "yxImgDir": yxImgDir,
            "toObject": toObject,
            "sweetPrompt": sweetPrompt,
            "isDT": isDT,
            "specialFlag":specialFlag,
            "activityComment": activityComment,
            "tChannal":tChannal,
            "beginTime": beginTime,
            "endTime": endTime,
            "marketTemplateContent": marketTemplateContent,
            "viewTemplateId": viewTemplateId,
            "channalData":channalData,
            "type": type,
            "bandType":bandType,
            "isInBrand": isInBrand,
            "marketOrder": marketOrder,
            "ztMarketOrder": ztMarketOrder,
            "unityFlag": unityFlag,
            "city": city,
            "cityCount": cityCount,
            "isViewList":1,
            "spreadCode":spreadCode,
            "instructionContent":instructionContent,
            "allProSign":allProSign,
            "ztMarketShowChannel":ztMarketShowChannel
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	var result = ret.retObj;
            	var times=0;
            	if(yxImgDir){
            		var fileType = yxImgDir.substring(yxImgDir.indexOf("."),yxImgDir.length);
            		$("#yxImgDirName").val(result.fileName +""+fileType);
            		$("#marketFirstPkid").val(result.marketFirstPkid);
            		$("#addForm").submit();
            		times=3000;	
            	}
            	var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
            }
            else {
                UOMPComp.showFailedDialog(ret.resMsg, "");
            }
        }
    });
},


//---初始化一级boss编码
initBossMarket:function(id,city){
	//---boss表中t_channal字段:1,网厅（村组，校园，网点属于网厅），2，掌厅，3短厅
	
    var tchannal=channalSel==""?1:channalSel;
	 $.singleReq({
        data: {
            "reqUrl": "bossMarket",
            "reqMethod": "queryBossMarketFirstList",
            "tchannal":tchannal,
            "city":city,
            "start":"0",
            "end":"1000"
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	  var result = ret.retObj; 
            	  var busitype = new Array();
                   for (var index = 0; index < result.length; index++) {
                   		var element = new Array();
                   		element.push(result[index].marketFirstCode);
                   		//element.push(result[index].marketFirstName+"["+result[index].marketFirstCode+"]");
                   		element.push(result[index].marketFirstCode+"["+result[index].marketFirstName+"]");
						busitype.push(element);
                   }
                   component.initCombo(busitype,id);
            }
            
        }
    });
	
	 component.initMarketOrderSel(id,city);
	 component.initZTMarketOrderSel(id,city);
	 
},
/*
 *初始化展示顺序 
 */
initMarketOrderSel:function(id,city){
	
	$.singleReq({
        data: {
			"city":city,
            "reqUrl": "marketFirst",
            "reqMethod": "queryViewNameOrderByMarketOrder"
        },
        success: function (ret) {
            if (ret.retCode == 0) {
           	var obj=$("#marketOrder"+id+" option[value='add']");
           	if(obj[0]){
           		var text=obj.text();
           		$("#marketOrder"+id).empty().prepend("<option value='add'>"+text+"</option>");	
           	}else{
           		$("#marketOrder"+id).empty();
           	}
                var result = ret.retObj.records; 
                $.each(result, function(i, item) {
               	 $("#marketOrder"+id).append("<option value="+item.marketFirstPkid+">"+item.viewName+"</option>");
                    
                });
              
            }
            
        }
    });
	 component.initBtnMove(id);//---数据加载后在注册click事件否则会出错
},
initZTMarketOrderSel:function(id,city){
	$.singleReq({
        data: {
			"city":city,
            "reqUrl": "marketFirst",
            "reqMethod": "queryViewNameOrderByZTMarketOrder"
        },
        success: function (ret) {
            if (ret.retCode == 0) {
           	var obj=$("#ztMarketOrder"+id+" option[value='add']");
           	if(obj[0]){
           		var text=obj.text();
           		$("#ztMarketOrder"+id).empty().prepend("<option value='add'>"+text+"</option>");	
           	}else{
           		$("#ztMarketOrder"+id).empty();
           	}
                var result = ret.retObj.records; 
                $.each(result, function(i, item) {
               	 $("#ztMarketOrder"+id).append("<option value="+item.marketFirstPkid+">"+item.viewName+"</option>");
                    
                });
              
            }
            
        }
    });
	 component.initZTBtnMove(id);//---数据加载后在注册click事件否则会出错
},
addSignStr:function(flag){
	if(flag){
		$("#allProSignDiv").show();
		$("#allProSignFlag").val("");
	}else{
		$("#allProSignDiv").hide();
		$("#allProSignFlag").val("0");
	}
}
}
