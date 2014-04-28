var z;
var fileName;
var submitBack=0;//提交图片返回标识
window.dhx_globalImgPath = GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/imgs/";
$(document).ready(function () {
	 component.initForm();   
    //component.initValidate();
	 $("#btnMoveUp,#btnMoveDown").click(function() {   
		  var $opt = $("#marketOrder option:selected:first");   
		  if (!$opt.length) return;   
		  if (this.id == "btnMoveUp") {
		  		$opt.prev().before($opt);   
		  		}else {
		  		$opt.next().after($opt); 
		  		      } 
			}); 
	 $("#channalData_4").click(function() {if(this.checked){$("#tab").show()}else{$("#tab").hide()}}); 
});

	//--view_name命名的时候添加到展示顺序里
	function addOption(input){
			if(input.value!=""){ 
			$("#marketOrder option[value='add']").remove();	
			$("#marketOrder").append("<option value='add'>"+input.value+"</option>");
		 	}
	}
	
var component = {
		// 初始化校验规则

	    initValidate: function () {
	        ValidateUtil.validate({
	            targetForm: "addForm",
	            rules: {
	            	imgDir: {required: true,isImage: "#imgDir"},
	            },
	            messages: {
	            	imgDir: {required: "请选择图片",isImage:"只支持jpg，gif，bmp，jpeg，png格式的图片!"},
	            }
	        });
	    },
		  //--保存网厅数据(channalData=4;)
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
    	    isInBrand=isInBrand+this.value+",";
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
//    alert(tChannal);
    var marketOrder = "";
    $("select[name=marketOrder] option").each(
    		  function() {
    			  marketOrder=marketOrder+$(this).val()+".";
    		  }
    		 );
    if(marketOrder.length>0){
    	marketOrder=marketOrder.substring(0,marketOrder.length-1);}
//    var marketFirstName = $("#marketFirstName").val();
    var viewName = $("#viewName").val();
    var imgDir = $("#imgDir").val();
    var toObject = $("#toObject").val();		
    var targetGroupInfo =component.getTargetGroupInfo();
    var prize = $("#prize").val();
    var activityComment = $("#activityComment").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var bandType=0;
    var type = $("input[name='type']:checked").val();
    var paymode = $("input[name='paymode']:checked").val();
    var marketFirstCode=z.getSelectedValue();  
    var marketFirstName=z.getSelectedText(); 
        marketFirstName=marketFirstName.substr(0,marketFirstName.indexOf("("+marketFirstCode+")"));
    var str="";
    var i = 0;
    $("input[name='channalData']:checkbox").each(function(){ 
    	if($(this).attr("checked")){
    		if(i > 0){
    			str += ",";
    		}
        	str += $(this).val();
        	i++;
    	}
    })    
    var channalData=str;//--TODO
    var tips=$("#tips").val();
      
    
    $.singleReq({
        data: {
            "reqUrl": "reservationFirst",
            "reqMethod": "saveReservationFirst",
            "marketFirstName": marketFirstName,
            "marketFirstCode": marketFirstCode,
            "viewName": viewName,
            "imgDir": imgDir,
            "toObject": toObject,
            "targetGroupInfo":targetGroupInfo,
            "prize": prize,
            "paymode": paymode,
            "activityComment": activityComment,
            "tChannal":tChannal,
            "beginTime": beginTime,
            "endTime": endTime,
            "channalData":channalData,
            "type": type,
            "isInBrand": isInBrand,
            "marketOrder": marketOrder,
            "tips":tips
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	var result = ret.retObj;
            	var times=0;
            	if(imgDir){
            		var fileType = imgDir.substring(imgDir.indexOf("."),imgDir.length);
            		$("#fileName").val(result.fileName +""+fileType);
            		$("#marketFirstPkid").val(result.marketFirstPkid);
            		$("#addForm").submit();
            		times=3000;	
            	}
            	
        	var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
            }
            else {
                UOMPComp.showFailedDialog("添加失败！", "");
            }
        }
    });
},
/**
    // 初始化校验规则
		
    initValidate: function () {
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
        	    areaNum: {required: true, minlength: 1, maxlength: 3},
        	    areaName: {required: true, minlength: 1, maxlength: 20},
        	    areaJbNum: {required: true},
            },
            messages: {
            	areaNum: {required: "请输入地市编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	areaName: {required: "请输入地市名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	areaJbNum: {required: "请输入地市级别编码"},
            }
        });
    }, 
    */

crmOnchange:function(val){
	var crmFirstCode=val; 
	//初始化品牌
	 $.singleReq({
	        data: {
		 		"crmFirstCode": crmFirstCode,
	            "reqUrl": "rebossMarketFirst",
	            "reqMethod": "queryCrmBrandInfo",
	        },
	        success: function (ret) {
	            if (ret.retCode == 0) {
	            	var result = ret.retObj; 
	                var brandArray= result.split(",");
	     	        var brandHtml="";
	     	        var brandName="";
	     	        var brandVaue="";
	     	        for(var j=0;j<brandArray.length;j++){
	     	            if(brandArray[j]==1){brandName="全球通";brandVaue="QQT";}
	     	            if(brandArray[j]==2){brandName="动感地带";brandVaue="DGDD";}
	     	            if(brandArray[j]==3){brandName="神州行";brandVaue="SZX";}
	     	            brandHtml =brandHtml+"<input name=\"isInBrand\"  id="+brandArray[j]+" type=\"checkbox\" readonly='true' CHECKED value="+brandVaue+">"+brandName+"";
	     	        }
	     	        $("#brandId").html(brandHtml);
	            }
	            else {
	            	UOMPComp.showFailedDialog("失败！", "");
	            }
	        }
	    });
	 component.queryReSaleOfficeInfo(crmFirstCode);
},
initForm:function(){
	var myCalendar=new dhtmlXCalendarObject(["beginTime","endTime"]);
    var tchannal=1;
    //---查询boss
	 $.singleReq({
        data: {
            "reqUrl": "rebossMarketFirst",
            "reqMethod": "queryCrmInfo",
            "start":"0",
            "end":"1000"
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	  var result = ret.retObj.records; 
                  $.each(result, function(i, item) {
                 	 $("#marketFirstNameSel").append("<option value="+item.marketLevel1Id+">"+item.marketLevel1Name+"("+item.marketLevel1Id+")"+"</option>");
                      
                  });
                  z = dhtmlXComboFromSelect("marketFirstNameSel");
                  z.enableFilteringMode(true);
                  z.attachEvent("onChange", function(){component.crmOnchange( z.getSelectedValue() )});
            }
            else {
            	UOMPComp.showFailedDialog("失败！", "");
            }
        }
    });
	 
	
	 
	 //---用户目标组初始化
		$.singleReq( {
			data : {
				"reqUrl" : "orgHandle",
				"reqMethod" : "queryTargetGroupInfo",
				"state" : "1",
				"groupType" : "2",
				"start" : "0",
				"end" : "100"
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
	                  $.each(result, function(i, item) {
							var groupId = item.groupId;
							var groupName = item.groupName;
							var groupType = item.groupType;
							var option = "<option value='" + groupId + "' groupType='"+groupType+"'>"
									+ groupName + "</option>";
							$("#targetGroupInfoList").append(option);  
	                   });
				} else {
				//	UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
		
		//--查询展示顺序
	 $.singleReq({
         data: {
             "reqUrl": "reservationFirst",
             "reqMethod": "queryAllMarketOrder"
         },
         success: function (ret) {
             if (ret.retCode == 0) {
                 var result = ret.retObj.records; 
                 $.each(result, function(i, item) {
                	 $("#marketOrder").append("<option value="+item.marketFirstPkid+">"+item.viewName+"</option>");
                     
                 });
               
             }
             else {
            	//	UOMPComp.showFailedDialog("失败！", "");
             }
         }
     });
},
//--查询营业厅
queryReSaleOfficeInfo:function(crmFirstCode) {
	 var marketFirstPkid=crmFirstCode;
	 $.singleReq({
	        data: {
	            "reqUrl": "reservationFirst",
	            "reqMethod": "queryReSaleOfficeInfo",
	            "marketFirstPkid":marketFirstPkid,
	            "start":"0",
	            "end":"1000"
	        },
	        success: function (ret) {
	            if (ret.retCode == 0) {
	            	  var result = ret.retObj.records; 
	            	  var startHtml="<select id=\"OfficeId\" class=\"width:150\" size=\"8\" multiple=\"\" name=\"OfficeName\">"; 
	      			var forHtml="";
	                  $.each(result, function(i, item) {
	                	  forHtml=forHtml +"<option value="+item.officeNum+
	                	  " id="+item.officeNum+" >"+item.officeName+"</option>"; 
	                  });
	                  var endHtml="</select>";
	      	        $("#officeId").html(startHtml+forHtml+endHtml);
	            }
	            else {
	            	//UOMPComp.showFailedDialog("失败！", "");
	            }
	        }
	    });
	 
},
//添加业务
addTargetGroupInfo : function() {
	var option = $("#targetGroupInfoList option:selected");
	$("#selecTargetGroupInfo").append(option);

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
removeTargetGroupInfo : function() {
	var option = $("#selecTargetGroupInfo option:selected");
	$("#targetGroupInfoList").append(option);
}
}
