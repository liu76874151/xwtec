var isSJ="";
var cityId="";
var unityFlag="";//--是否是统一营销案
var unityCtiys = new Array();
var unityMarketFirstCode = new Array();
var unityMarketFirstPkid = new Array();
var submitBack=0;//提交图片返回标识
var WTHeight="";
var curAreaName="";
$(document).ready(function () {
		if($("#isViewPage").val()=="1"){
			document.getElementById("mypageTitle").innerHTML='一级营销案--查看';
			$("#saveBtn").hide();
			}
	cityId=$("#cityId").val();
	isSJ=$("#isSJ").val();
	 unityFlag=$("#unityFlag").val();
	if(unityFlag==1){
		//if(isSJ==1){
		 component.queryUnityMarketFirst(cityId); 
		 //}
	}else{
		component.initBossMarket("",cityId,$("#marketFirstCode").val());
	}
        
        var beginTimeCalendar=new dhtmlXCalendarObject(["beginTime"]);
        var endTimeCalendar=new dhtmlXCalendarObject(["endTime"]);
        endTimeCalendar.attachEvent("onblur",function(date, state){
           // alert("Date is changed to "+date) 
      })
      	 $("#channalData_4").click(function() {
		 if(this.checked){
			 $("#tab").show();
			 tab.showTab('tab_1',true);
			 if(tab.getActiveTab()=="tab_1"){
				 tab.setTabActive('tab_3');
			 }
			 tab.setTabActive('tab_1');
				
			 }else{
				 tab.setTabActive('tab_3');
				 tab.hideTab('tab_1',true);
				 var channalDataTemp=",";
					$("input[name='channalData']:checked").each(function () {
						channalDataTemp=channalDataTemp+this.value+",";
				    });
					if(channalDataTemp.indexOf(",6,")>-1){
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
			 if(tab.getActiveTab()=="tab_3"){
				 tab.setTabActive('tab_1');
			 }
			 tab.setTabActive('tab_3');
			 
			 
			 }else{
				 tab.setTabActive('tab_1');
				 tab.hideTab('tab_3',true);
				 var channalDataTemp=",";
					$("input[name='channalData']:checked").each(function () {
						channalDataTemp=channalDataTemp+this.value+",";
				    });
					if(channalDataTemp.indexOf(",4,")>-1){
						$("#tab").show();
					}else{
						$("#tab").hide();
					}
				 }
		 }); 
	 
	 component.initValidate();  

	
    }
);
function endTimeOnblur(input){
	//alert(input.value);
}
function editOption(input){

	if(input.value!=""){ 
		var length=$("select[name='marketOrder']").length;
		if(unityFlag==1){
		for ( var i = 0; i < length; i++) {
			marketFirstCodeTemp=$("#marketFirstNameSel_"+i).val(); 
			$("#marketOrder option[value='"+marketFirstCodeTemp+"']").text(input.value);	
		}
		}else{
			$("#marketOrder option[value='"+$("#pkid").val()+"']").text(input.value);	
		}
	
 	}
}

function tabOnSelect(id,last_id){//--处理tabbar切换事件

	if(id=="tab_3"){
		 $("#tab").height(120); 
	}else if(id=="tab_1"){
		if(unityFlag==0){
		 $("#tab").height(560); 
		 }else{
			 $("#tab").height(WTHeight);
		 }
	}else if(id=="tab_2"){
		if(unityFlag==0){
			 $("#tab").height(280); 
			 }else{
				$("#tab").height(1130);
			 }
	}


}
function doOnInit(){//--初始化tabbar
	//alert("1111");
	tab.setSize("100%",WTHeight,true);//--设置tabbar的宽高
	tab.attachEvent("onSelect", function(id,last_id) {//--处理tabbar切换事件id : id of selected tab ,last_id :id of previously selected tab
		tabOnSelect( id,last_id);
	    return true;
	});
}
var component =
{
		
		  // 初始化校验规则
		
	    initValidate: function () {
	        ValidateUtil.validate({
	            targetForm: "editForm",
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
		/*
		 * 选择营销案模板 改变模板内容
		 */
		templateNameOnchange:function(obj){
		 $("#marketContent").empty();
		 var content = $("#templateName option:selected").attr("content");
		 if("undefined"!=content)
		 $("#marketContent").val(content);
		},
		initTemplate:function(viewTemplateId){
	$.singleReq({
        data: {"start": 0,
        		"end": 9999,
        		"state": 1,
        		"category":1,
        		"channalData":4,
		    	"reqUrl": "marketTemplate",
		        "reqMethod": "queryMarketTemplateList"
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	   var result = ret.retObj.records; 
                   $.each(result, function(i, item) {
                	   if(viewTemplateId==item.templateId){
                		   $("#templateName").append("<option value="+item.templateId+" content='"+item.content+"' selected>"+item.templateName+"</option>");
                 	   }else{
                    	 $("#templateName").append("<option value="+item.templateId+" content='"+item.content+"'>"+item.templateName+"</option>");
                	   }
                   });
                  
            }
        }
    });
},
    initForm: function () {

        $.singleReq({
            data: {
                "reqUrl": "marketFirst",
                "reqMethod": "queryByPrimaryKey",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                var result = ret.retObj;
               $("#marketFirstName").val(result.marketFirstName);
               $("#marketContent").val(result.marketTemplateContent);
               component.initTemplate(result.viewTemplateId);
               $("#viewName").val(result.viewName);
               if(result.yxImgDir!=null&&result.yxImgDir!=undefined&&result.yxImgDir!=""){
            	   $("#yxImg").attr('src',GLOBAL_INFO.CONTEXTPATH +"/"+result.yxImgDir) ;
            	   $("#yxImg").css('display','block');
            	   $("#yxImgDir_old").val(result.yxImgDir);
               }
               $("#toObject").val(result.toObject);
               var isInBrandTemp=result.isInBrand;
             isInBrandTemp=(isInBrandTemp==undefined?"":isInBrandTemp);
                if(isInBrandTemp.indexOf("QQT")!=-1){$("#isInBrand0").attr("checked",true);}
                if(isInBrandTemp.indexOf("DGDD")!=-1){$("#isInBrand1").attr("checked",true);}
                if(isInBrandTemp.indexOf("SZX")!=-1){$("#isInBrand2").attr("checked",true);}
                var ztMarketShowChannel=result.ztMarketShowChannel;
                ztMarketShowChannel=(ztMarketShowChannel==undefined?"":ztMarketShowChannel);
                if(ztMarketShowChannel.indexOf("a")!=-1){$("#a").attr("checked",true);}
                if(ztMarketShowChannel.indexOf("b")!=-1){$("#b").attr("checked",true);}
              $("#activityComment").val(result.activityComment);
              $("#sweetPrompt").val(result.sweetPrompt);
              $("#beginTime").val(result.beginTime);
              $("#endTime").val(result.endTime);
              $("#endTime").val(result.endTime);
              $("#marketFirstCode").val(result.marketFirstCode);
              $("#marketFirstPkid").val(result.marketFirstPkid); 
              $("#marketFirstName").val(result.marketFirstName); 
              $("#marketFirstName2").val(result.marketFirstName); 
              $("#marketFirstNameSel option[value='"+result.marketFirstCode+"']").attr("selected",true);
              var channalData=result.channalData;
              $("#tab").show();
         	 tab.hideTab('tab_1',true);
         	 tab.hideTab('tab_3',true);
         	 tab.hideTab('tab_2',true);
         	 
         	 if(channalData.indexOf("6")!=-1){
	          	  $("#channalData_6").attr("checked",true);
	          tab.showTab('tab_3',true);
	          	  if(tab.getActiveTab()=="tab_3"){
	          		  tab.setTabActive('tab_1');
	          	  	}
	          	  tab.setTabActive('tab_3');
          	  }else{tab.hideTab('tab_3',true);};
              if(channalData.indexOf("5")!=-1){
            	  $("#channalData_5").attr("checked",true);
            	  tab.showTab('tab_2',true);
            	  if(tab.getActiveTab()=="tab_2"){
	          		  tab.setTabActive('tab_1');
	          	  	}
            	  tab.setTabActive('tab_2');
            	  }else{tab.hideTab('tab_2',true);};
              if(channalData.indexOf("4")!=-1){
            	  $("#channalData_4").attr("checked",true);
            	  tab.showTab('tab_1',true); 
	              if(tab.getActiveTab()=="tab_1"){
	        		  tab.setTabActive('tab_3');
	        	  	}
	              tab.setTabActive('tab_1');
              }else{tab.hideTab('tab_1',true);};
              $("#instructionContent").val(result.instructionContent); 
              if(result.allProSign=="0"){
            	  $("#allProSign_0").attr("checked",true);
              }else{
            	  $("#allProSign_1").attr("checked",true);
            	  $("#allProSignFlag").val(result.allProSign);
            	  $("#allProSignDiv").show();
            	  }
              $("#spreadCode").val(result.spreadCode); 
              $("#specialFlag_"+result.specialFlag).attr("checked",true);
              result.type==0?($("#type0").attr("checked",true)):(result.bandType==1?$("#type1").attr("checked",true):$("#type2").attr("checked",true));
            	 var tChannal= (parseInt(result.tChannal)+8).toString(2);//---转换为四位的二进制
            	 var tChannalTemp="";
            	 for(var i=1;i<tChannal.length;i++){
            		tChannalTemp= tChannal.substring(i,i+1);
            		 if(tChannalTemp==1){$("#tChannal"+i).attr("checked",true);}
    				 }
            }
        });


    },
    
  //---初始化一级boss编码
    initBossMarket:function(id,city,unityMarketFirstCode,unityMarketFirstPkid){
    	//---boss表中t_channal字段:1,网厅（村组，校园，网点属于网厅），2，掌厅，3短厅
        var tchannal=1;
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
                	$("#marketFirstNameSel"+id).empty().append("<option selected value='-1' cityId="+city+" marketFirstPkid="+unityMarketFirstPkid+" disabled='disabled' >---请选择---</option>");;
                	  var result = ret.retObj; 
                      $.each(result, function(i, item) {
                    	  if(unityMarketFirstCode==item.marketFirstCode){
                    		  $("#marketFirstNameSel"+id).append("<option value="+item.marketFirstCode+" selected >"+item.marketFirstName+"("+item.marketFirstCode+")"+"</option>");
                    	  }else{
                     	 $("#marketFirstNameSel"+id).append("<option value="+item.marketFirstCode+" >"+item.marketFirstName+"("+item.marketFirstCode+")"+"</option>");
                    	  }
                      });
                      component.initForm(); 
                      }
               
            }
            
        });
    	
    	component.initMarketOrderSel(id,city);
    	component.initZTMarketOrderSel(id,city);
    	 
    },
    queryUnityMarketFirst:function(cityId){
    	//alert(cityId);
    	$.singleReq({
            data: { 
    			"marketFirstPkid":$("#pkid").val(),
                "reqUrl": "marketFirst",
                "reqMethod": "queryUnityMarketFirstList"
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj.records; 
                 	var totalRecord=ret.retObj.totalRecord;
                 	$.each(result, function(i, item) {
                 		unityMarketFirstCode.push(item.marketFirstCode);
                 		unityMarketFirstPkid.push(item.marketFirstPkid);
                 		unityCtiys.push(item.city);
                 	});
           
                 	WTHeight=330+(Math.round(unityCtiys.length/2)*135);
                 	
                 	component.initArea(cityId);
                }
                
            }
        });
    },
  //--统一营销案的时候初始化城市  ,boss下拉框,相应地市营销案排序列表
    initArea:function(cityId){
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
                 	     bossCodeDiv.empty();
                 	    ztMarketOrderDiv.empty();
                 	   $("#marketOrderTab").remove();
                 	  marketOrderDiv.append('<table ><tr >');//<table><tr>
                 	 ztMarketOrderDiv.append('<table ><tr >');
                 	bossCodeDiv.append('<table ><tr >');
                 	 var index=-1;
                 	 var j=0;
                     $.each(result, function(i, item) {
                    	 index=$.inArray(item.areaBossCode, unityCtiys);
                    	if(i>0 &&index>-1){
                     		if(item.areaBossCode==cityId){
                     			curAreaName=item.areaName;
                    			$("#pageTitle").append("--"+item.areaName);
                    		}
                    		//bossCodeDiv.append('&nbsp;'+item.areaName+'&nbsp;<select name="marketFirstNameSel" disabled="disabled" id="marketFirstNameSel_'+j+'" cityId="'+item.areaBossCode+'" style="width: 180px;" > </select>&nbsp;&nbsp;&nbsp;');
                     		bossCodeDiv.append('<td>&nbsp;'+item.areaName+'</td><td>&nbsp;<select name="marketFirstNameSel" disabled="disabled" id="marketFirstNameSel_'+j+
                     				'" cityId="'+item.areaBossCode+'" style="width: 180px;" > </select>&nbsp;&nbsp;&nbsp;</td>');
                    		marketOrderDiv.append('<td>'+
    						                		 '<table >'+
    						                		 '<tr>'+
    						                		 '<td colspan="2"><b>'+
    						                		 item.areaName+
    						                		 ':</b></td>'+
    						                		 '</tr>'+
    												'<tr style="">'+
    													'<td>'+
    														'<select size="6"  name="marketOrder" id="marketOrder_'+j+'" cityId="'+item.areaBossCode+'"  style="width: 160px ;height:120px"  >'+
    														'</select>'+
    													'</td>'+
    													'<td style="">'+
    														'<input type="button" value="上移" id="btnMoveUp_'+j+'" ><br>'+
    														'<input type="button" value="下移" id="btnMoveDown_'+j+'">'+
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
											'<select size="6"  name="ztMarketOrder" id="ztMarketOrder_'+j+'" cityId="'+item.areaBossCode+'"  style="width: 160px ;height:120px"  >'+
											'</select>'+
										'</td>'+
										'<td style="">'+
											'<input type="button" value="上移" id="ztBtnMoveUp_'+j+'" ><br>'+
											'<input type="button" value="下移" id="ztBtnMoveDown_'+j+'">'+
										'</td>'+
									'</tr>'+
								'</table></td>');
                    		                 
                    		if((j+1)%2==0){
                        		//bossCodeDiv.append('<br>');
                        		
                        		if(i<totalRecord-1){
                        			marketOrderDiv.append('</tr ><tr>');
                        			ztMarketOrderDiv.append('</tr ><tr>');
                        			bossCodeDiv.append('</tr ><tr>');
                        		}
                        		
                        	}
                    		if(j+1==totalRecord){
                    			marketOrderDiv.append('</tr ></table >');
                    			ztMarketOrderDiv.append('</tr ></table >');
                    			bossCodeDiv.append('</tr ></table >');
                    		}
                    		
                    	component.initBossMarket("_"+j,item.areaBossCode,unityMarketFirstCode[index],unityMarketFirstPkid[index]);//--根据城市和渠道(全局变量channalSel)加载一级boss信息   ,initBossMarket(select的id,城市编码)
                    	j++;}
                    	
                    	
                    	});
     			}
             }
             
         });
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
                    var result = ret.retObj.records; 
                    $.each(result, function(i, item) {
                   	 $("#ztMarketOrder"+id).append("<option value="+item.marketFirstPkid+">"+item.viewName+"</option>");
                        
                    });
                  
                }
                
            }
        });
    	 component.initZTBtnMove(id);//---数据加载后在注册click事件否则会出错
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
   saveForm1: function (marketFirstPkid,city,isDT,marketFirstName,marketFirstCode,viewName,yxImgDir,toObject,instructionContent,spreadCode,activityComment,tChannal,beginTime,endTime,channalData
		   ,type,bandType,isInBrand,marketOrder,ztMarketOrder,sweetPrompt,marketTemplateContent,viewTemplateId,unityFlag) {
		   var allProSign;
		   var ztMarketShowChannel="";
		   var localVerifyState,proVerifyState,verifyState;
		   var dtLocalVerifyState,dtProVerifyState,dtVerifyState;
		   var wtLocalVerifyState,wtProVerifyState,wtVerifyState;
		   var wtTestOnlineState,testOnlineState,dtTestOnlineState;
		   if(channalData.indexOf("4")!=-1){
			   wtLocalVerifyState='0';
			   wtProVerifyState='0';
			   wtVerifyState='0';
			   wtTestOnlineState='0';
		   }
		   if(channalData.indexOf("5")!=-1){
			   allProSign=$("input[name='allProSignFlag']:checked").val();
			   if(allProSign=="1"){
			    	allProSign=$("#allProSignFlag").val();
			    }
			   $("input[name='ztMarketShowChannel']:checked").each(function () {
			    	ztMarketShowChannel=ztMarketShowChannel+this.value+".";
			    });
			    if(ztMarketShowChannel.length>0){
			    	ztMarketShowChannel=ztMarketShowChannel.substring(0,ztMarketShowChannel.length-1);} 
			    
			   localVerifyState='0';
			   proVerifyState='0';
			   verifyState='0';
			   testOnlineState='0';
		   }
		   if(channalData.indexOf("6")!=-1){
			   dtLocalVerifyState='0';
			   dtProVerifyState='0';
			   dtVerifyState='0';
			   dtTestOnlineState='0';
		   }
		   
	   $.singleReq({
	        data: {
	            "reqUrl": "marketFirst",
	            "reqMethod": "updateByPrimaryKey",
	            "marketFirstPkid":marketFirstPkid,
	            "isDT":isDT,
	            "cityLog":city,
	            "marketFirstName": marketFirstName,
	            "marketFirstCode": marketFirstCode,
	            "viewName": viewName,
	            "yxImgDir": yxImgDir,
	            "toObject": toObject,
	            "instructionContent": instructionContent,
	            "spreadCode": spreadCode,
	            "activityComment": activityComment,
	            "tChannal":tChannal,
	            "beginTime": beginTime,
	            "endTime": endTime,
	            "channalData":channalData,
	            "type": type,
	            "bandType":bandType,
	            "isInBrand": isInBrand,
	            "sweetPrompt": sweetPrompt,
	            "unityFlag": unityFlag,
	            "marketOrder": marketOrder,
	            "marketTemplateContent": marketTemplateContent,
	            "viewTemplateId": viewTemplateId,
	            "allProSign": allProSign,
	            "ztMarketOrder": ztMarketOrder,
	            "yxImgDir_old": $("#yxImgDir_old").val(),
	            "specialFlag": $("input[name=specialFlag]:checked").val(),
	            "localVerifyState":localVerifyState,
	            "proVerifyState":proVerifyState,
	            "verifyState":verifyState,
	            "dtLocalVerifyState":dtLocalVerifyState,
	            "dtProVerifyState":dtProVerifyState,
	            "dtVerifyState":dtVerifyState,
	            "wtLocalVerifyState":wtLocalVerifyState,
	            "wtProVerifyState":wtProVerifyState,
	            "wtVerifyState":wtVerifyState,
	            "ztMarketShowChannel":ztMarketShowChannel,
	            "testOnlineState":testOnlineState
	           
	        },
	        success: function (ret) {
	            if (ret.retCode == 0) {
	            	
	            	var result = ret.retObj;
	            
	            	var times=0;
	            	if(yxImgDir){
	            		if(result.yxImgDir){
	            		$("#yxImgDir_old").val(result.yxImgDir);//--此时获取重命名后的文件名  
	            		}
	            		$("#marketFirstPkid").val(result.marketFirstPkid);
	            		$("#editForm").submit();
	            		times=3000;	
	            	}
	            	var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("修改成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
	            }
	            else {
	                UOMPComp.showFailedDialog("修改失败！", "");
	            }
	        }
	        
	        
	    });
	   
   },
    saveForm: function () {
	
	   
    	 if (!$('#editForm').valid()) {
    	        return;
    	    }
    	 if(submitBack==1){
    		 return;
    	 }else{
    		 submitBack=1;
    	 }
    	    var isInBrand="";
    	    $("input[name=isInBrand]:checked").each(function () {
    	    	    isInBrand=isInBrand+this.value+".";
    	    });
    	    if(isInBrand.length>0){
    	    	isInBrand=isInBrand.substring(0,isInBrand.length-1);}
    	    
    	    var tChannal="";
    	    $("input[name=tChannal]:checked").each(function () {
    	    	tChannal=tChannal+this.value+".";
    	    });
    	    if(tChannal.length>0){
    	    	tChannal=tChannal.substring(0,tChannal.length-1);} 
    	    var  wtFlag= $("#channalData_4:checked").val();
            if(wtFlag!=4){
            	tChannal=undefined;
            }
            var isDT="0";
            if($("#channalData_6:checked").val()==6){
            	isDT="1";
            }
    	    var marketOrder = "";
    	    var ztMarketOrder = "";
    	    var viewName = $("#viewName").val();
    	    var yxImgDir  = $("#yxImgDir").val();
    	    var toObject = $("#toObject").val();
    	    var activityComment = $("#activityComment").val();
    	    var beginTime = $("#beginTime").val();
    	    var endTime = $("#endTime").val();
    	    var bandType=0;
    	    var type = $("input[name=type]:checked").val();
    	    	type==0?bandType=0:(bandType=type,type=1);
    	    var marketFirstCode="";//$("#marketFirstCode").val(); 
    	    var marketFirstName="";//$("#marketFirstName").val(); 
    	    var marketFirstPkid=$("#marketFirstPkid").val(); 
    	    var sweetPrompt=$("#sweetPrompt").val(); 
    	    var marketTemplateContent=$("#marketContent").val();
    	    var viewTemplateId=$("#templateName option:selected").val();
    	   
    	    var channalData="";//--4网厅数据 ，5掌厅数据，6短厅数据
    	    $("input[name='channalData']:checked").each(function () {
    	    	channalData=channalData+this.value+",";
    	    });
    	    if(channalData.length>0){
    	    	channalData=channalData.substring(0,channalData.length-1);} 
    	    var instructionContent,spreadCode;
    	    if(channalData.indexOf("6")!=-1){
    	    	instructionContent=$("#instructionContent").val();
    	    	spreadCode=$("#spreadCode").val();
    	    }
    	    var city;
    	    	if(unityFlag==1){
    	    		// if( isSJ==1){//---如果是省级用户修改统一营销案
    	    			 //alert(11111111111);
    	    	var unityMarketFirstCode="";
    	    	var unityMarketFirstName="";
    	    	var unityMarketFirstPkid="";
    	    	var marketOrderTemp="";
    	    	var ztMarketOrderTemp="";
    	    	var marketFirstCodeTemp="";
    	    	var marketFirstNameTemp="";
    	    	var length=$("select[name='marketFirstNameSel']").length;
    	    	city="";
    	    	for ( var i = 0; i < length; i++) {
    	    		city+=$("#marketFirstNameSel_"+i).attr("cityId")+",";
    	    		marketFirstCodeTemp=$("#marketFirstNameSel_"+i).val(); 
    	    		unityMarketFirstCode+=marketFirstCodeTemp+",";
    	    		marketFirstNameTemp=$("#marketFirstNameSel_"+i).find("option:selected").text(); 
    	    		unityMarketFirstName+=marketFirstNameTemp.substr(0,marketFirstNameTemp.indexOf("("+marketFirstCodeTemp+")"))+",";
    	    		unityMarketFirstPkid+=$("#marketFirstNameSel_"+i+" option[value='-1']").attr("marketFirstPkid")+",";
    	    		//alert(unityMarketFirstPkid+"===unityMarketFirstPkid===-1=");
    	    		$("#marketOrder_"+i+" option").each(
    	  	    		  function() {
    	  	    			  marketOrderTemp=marketOrderTemp+$(this).val()+".";
    	  	    		  }
    	  	    		 );
    	    		$("#ztMarketOrder_"+i+" option").each(
      	  	    		  function() {
      	  	    			ztMarketOrderTemp=ztMarketOrderTemp+$(this).val()+".";
      	  	    		  }
      	  	    		 );
    	  	    if(marketOrderTemp.length>0){
    	  	    	marketOrderTemp=marketOrderTemp.substring(0,marketOrderTemp.length-1);
    	  	    	}else{
    	  	    		marketOrderTemp="-1"
    	  	    			}
    	  	    marketOrder+=marketOrderTemp+",";
    	  	    marketOrderTemp="";
    	  	    
    	  	  if(ztMarketOrderTemp.length>0){
    	  		ztMarketOrderTemp=ztMarketOrderTemp.substring(0,ztMarketOrderTemp.length-1);
  	  	    	}else{
  	  	    	ztMarketOrderTemp="-1"
  	  	    			}
    	  	ztMarketOrder+=ztMarketOrderTemp+",";
  	  	ztMarketOrderTemp="";
    	    		
    	    	}
    	    	
    	    	if(city.length>0){
    	    		city=city.substring(0,city.length-1);}
    	    	 if(marketOrder.length>0){
 	      	    	marketOrder=marketOrder.substring(0,marketOrder.length-1);}
    	    	 if(ztMarketOrder.length>0){
    	    		 ztMarketOrder=ztMarketOrder.substring(0,ztMarketOrder.length-1);}
    	    	 if(length>1){
    	    UOMPComp.showConfirmDialog("【系统提示】\n\n您确定修改地区的信息吗？", {
    	        "yes": function () {
    	    	marketFirstPkid=unityMarketFirstPkid;
    	    	marketFirstName=unityMarketFirstName;
    	    	marketFirstCode=unityMarketFirstCode;
    	    	//unityFlag="1";
    	    	
    	    	component.saveForm1 (marketFirstPkid,city,isDT,marketFirstName,marketFirstCode,viewName,yxImgDir,toObject,instructionContent,spreadCode,activityComment,tChannal,beginTime,endTime,channalData
    	    			   ,type,bandType,isInBrand,marketOrder,ztMarketOrder,sweetPrompt,marketTemplateContent,viewTemplateId,1);
    	        },
    	        "no": function () {
    	        	
    	        	city=cityId;
    	        	marketOrder="";
    	        	ztMarketOrder="";
    	        	marketFirstCode="";
    	        	marketFirstName="";
    	        	marketFirstCode=$("[name='marketFirstNameSel'][cityId='"+cityId+"']").val();  
           	      	marketFirstName=$("#[name='marketFirstNameSel'][cityId='"+cityId+"']").find("option:selected").text(); 
           	         marketFirstName=marketFirstName.substr(0,marketFirstName.indexOf("("+marketFirstCode+")"));
    	        	$("[name='marketOrder'][cityId='"+cityId+"'] option").each(
      	  	    		  function() {
      	  	    		 marketOrder=marketOrder+$(this).val()+".";
      	  	    		  }
      	  	    		 );
    	        	$("[name='ztMarketOrder'][cityId='"+cityId+"'] option").each(
        	  	    		  function() {
        	  	    			ztMarketOrder=ztMarketOrder+$(this).val()+".";
        	  	    		  }
        	  	    		 );
    	        	 if(marketOrder.length>0){
    	       	    	marketOrder=marketOrder.substring(0,marketOrder.length-1);}
    	        	 if(ztMarketOrder.length>0){
    	        		 ztMarketOrder=ztMarketOrder.substring(0,ztMarketOrder.length-1);}
    	        	 component.saveForm1 (marketFirstPkid,city,isDT,marketFirstName,marketFirstCode,viewName,yxImgDir,toObject,instructionContent,spreadCode,activityComment,tChannal,beginTime,endTime,channalData
     	    			   ,type,bandType,isInBrand,marketOrder,ztMarketOrder,sweetPrompt,marketTemplateContent,viewTemplateId,0);
    	        }
    	    },"修改全部地区","仅修改"+curAreaName);
    	    
    	    	 }else{//--统一营销案只有一个城市不提示是否覆盖
    	    		 	marketFirstPkid=unityMarketFirstPkid;
    	    	    	marketFirstName=unityMarketFirstName;
    	    	    	marketFirstCode=unityMarketFirstCode;
    	    	    	component.saveForm1 (marketFirstPkid,city,isDT,marketFirstName,marketFirstCode,viewName,yxImgDir,toObject,instructionContent,spreadCode,activityComment,tChannal,beginTime,endTime,channalData
    	    	    			   ,type,bandType,isInBrand,marketOrder,ztMarketOrder,sweetPrompt,marketTemplateContent,viewTemplateId,1); 
    	    	 }
    	    		 //}
    	    	}else{
    	    	city=cityId;
    	    	marketOrder="";
    	    	ztMarketOrder="";
	        	marketFirstCode="";
	        	marketFirstName="";
        	     marketFirstCode=$("#marketFirstNameSel").val();  
        	     marketFirstName=$("#marketFirstNameSel").find("option:selected").text(); 
        	     marketFirstName=marketFirstName.substr(0,marketFirstName.indexOf("("+marketFirstCode+")"));
        	     $("select[name=marketOrder] option").each(
       	    		  function() {
       	    			  marketOrder=marketOrder+$(this).val()+".";
       	    		  }
       	    		 );
        	     $("select[name=ztMarketOrder] option").each(
          	    		  function() {
          	    			ztMarketOrder=ztMarketOrder+$(this).val()+".";
          	    		  }
          	    		 );
        	     if(marketOrder.length>0){
           	    	marketOrder=marketOrder.substring(0,marketOrder.length-1);}
        	     if(ztMarketOrder.length>0){
        	    	 ztMarketOrder=ztMarketOrder.substring(0,ztMarketOrder.length-1);}
        	     component.saveForm1 (marketFirstPkid,city,isDT,marketFirstName,marketFirstCode,viewName,yxImgDir,toObject,instructionContent,spreadCode,activityComment,tChannal,beginTime,endTime,channalData
  	    			   ,type,bandType,isInBrand,marketOrder,ztMarketOrder,sweetPrompt,marketTemplateContent,viewTemplateId,0);
    	    }
    	   
    	 
    },
    addSignStr:function(flag){
    	if(flag){
    		$("#allProSignDiv").show();
    	}else{
    		$("#allProSignDiv").hide();
    	}
    }
};




