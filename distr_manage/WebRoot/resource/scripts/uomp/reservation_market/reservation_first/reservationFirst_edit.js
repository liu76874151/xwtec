var preHtml = "";
var channelDataStr = "";
var submitBack=0;//提交图片返回标识
$(document).ready(function () {
       // component.initValidate();
        component.initForm();
        
        $("#btnMoveUp,#btnMoveDown").click(function() {   
  		  var $opt = $("#marketOrder option:selected:first");   
  		  if (!$opt.length) return;   
  		  if (this.id == "btnMoveUp") {
  		  		$opt.prev().before($opt);   
  		  		}else {
  		  		$opt.next().after($opt); 
  		  		      } 
  			}); 
        $("#channalData_4").click(function() {
        	if(this.checked){
        		$("#tab").show();
        		if(channelDataStr != ""){
        			$("#imgControl").show();
           		 	$("#unimgControl").hide();
        		}else{
        			$("#imgControl").hide();
           		 $("#unimgControl").show();
        		}
        	}else{
        		$("#tab").hide();
        	}
        	}); 
    }
);

function editOption(input){

	var pkid=$("#pkid").val();
	if(input.value!=""){ 
	$("#marketOrder option[value='"+pkid+"']").text(input.value);
 	}
}

var component =
{
    //初始化校验规则
		/**
    initValidate: function () {
        ValidateUtil.validate({
            targetForm: "editForm",
            rules: {
                stuName: {required: true, minlength: 1, maxlength: 20},
                stuAge: {required: true, minlength: 1, maxlength: 3},
                stuSex: {required: true},
                stuNative: {required: true}
            },
            messages: {
                stuName: {required: "请输入姓名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                stuAge: {required: "请输入年龄", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
                stuSex: {required: "请选择性别"},
                stuNative: {required: "请选择籍贯"}
            }
        });
    },*/

    initForm: function () {
	//展示顺序
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
            		//UOMPComp.showFailedDialog("失败1！", "");
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
					UOMPComp.showFailedDialog("失败2！", "");
				}
			}
		}); 
		
        $.singleReq({
            data: {
                "reqUrl": "reservationFirst",
                "reqMethod": "queryByPrimaryKey",
                "pkid": $("#pkid").val()
            },
            success: function (ret) {
                var result = ret.retObj;
               $("#marketFirstName").val(result.marketFirstName);
               $("#viewName").val(result.viewName);
               if(result.yxImgDir!=null&&result.yxImgDir!=undefined&&result.yxImgDir!=""){
            	   $("#yxImg").attr('src',result.yxImgDir) ;
            	   $("#yxImg").css('display','block');
               }
               $("#toObject").val(result.toObject);
               $("#tips").val(result.tips);
               $("#prize").val(result.prize);
               var isInBrandTemp=result.isInBrand;
             isInBrandTemp=isInBrandTemp==undefined?"":isInBrandTemp;
            		 
 	     	var brandHtml="";
  	        var brandName="";
  	        var brandVaue="";
  	        var brandArray= isInBrandTemp.split(",");
  	      if(brandArray[0]!=""){
  	        for(var j=0;j<brandArray.length;j++){
  	            if(brandArray[j]=="QQT"){brandName="全球通";brandVaue="QQT";}
  	            if(brandArray[j]=="DGDD"){brandName="动感地带";brandVaue="DGDD";}
  	            if(brandArray[j]=="SZX"){brandName="神州行";brandVaue="SZX";}
  	            brandHtml =brandHtml+"<input name=\"isInBrand\"  id='"+brandArray[j]+"' type=\"checkbox\" CHECKED value="+brandVaue+">"+brandName+"";
  	        }

  	        $("#brandId").html(brandHtml);}
              $("#activityComment").val(result.activityComment);
              $("#sweetPrompt").val(result.sweetPrompt);
              $("#beginTime").val(result.beginTime);
              $("#endTime").val(result.endTime);
              $("#endTime").val(result.endTime);
              $("#marketFirstCode").val(result.marketFirstCode);
              $("#marketFirstPkid").val(result.marketFirstPkid); 
              if(result.marketFirstCode!=null&&result.marketFirstCode!=""){
            	  component.queryReSaleOfficeInfo(result.marketFirstCode)}
              //result.type==1?($("#type1").attr("checked",true)):($("#type2").attr("checked",true));
              $("#paymode"+result.paymode).attr("checked",true);
              $("#type"+result.type).attr("checked",true)
            	 var tChannal= (parseInt(result.tChannal)+8).toString(2);
            	 var tChannalTemp="";
            	 for(var i=1;i<tChannal.length;i++){
            		tChannalTemp= tChannal.substring(i,i+1);
            		 if(tChannalTemp==1){$("#tChannal_"+i).attr("checked",true);}
    			}
            	 
            	 $("[name = channalData]:checkbox").each(function(){
            		 if((result.channalData).indexOf(this.value) > -1){
            			 $(this).attr("checked",true);
            		 }
            	 });
            	 
            	 if(result.imgDir != undefined && result.imgDir != ""){
            		 channelDataStr = result.imgDir;
            		 $("#imgControl").show();
            		 $("#unimgControl").hide();
            	 }else{
            		 $("#imgControl").hide();
            		 $("#imgControl").show();
            	 }
            	 var imgDirSource = $("#imgDir_src").attr("src") +result.imgDir ;
        		 $("#imgDir_src").attr("src",imgDirSource);
            	 if((result.channalData).indexOf("4") > -1){
            		 $("#tab").show();
            	 }else{
            		 $("#tab").hide();
            	 }
            }
        });


    },
    reupload:function(b){
    	if(b){
    		$("#imgControl").hide();
    		$("#unimgControl").show();
    	}else{
    		$("#imgControl").show();
    		$("#unimgControl").hide();
    	}
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
        
       // alert("1111");
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
        var marketOrder = "";
        $("select[name=marketOrder] option").each(
        		  function() {
        			  marketOrder=marketOrder+$(this).val()+".";
        		  }
        		 );
        if(marketOrder.length>0){
        	marketOrder=marketOrder.substring(0,marketOrder.length-1);
        	}
        var marketFirstName = $("#marketFirstName").val();
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
            marketFirstName=marketFirstName.substr(0,marketFirstName.indexOf("("+marketFirstCode+")"));
        var channalData="";
        var i = 0;
        $("[name = channalData]:checkbox").each(function(){
        	if($(this).attr("checked",true)){
        		if(i > 0){
          			 channalData += ",";
          		 }
          		 channalData += $(this).val();
        	}
       	 });
        var pkid=$("#pkid").val();
        var tips=$("#tips").val();
            //alert(marketFirstName+"----"+marketFirstCode);

        $.singleReq({
            data: {
                "reqUrl": "reservationFirst",
                "reqMethod": "updateReservationFirst",
                "marketFirstPkid":pkid,
                "viewName": viewName,
//                "yxImgDir": yxImgDir,
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
                		$("#marketFirstPkid").val(pkid);
                		
                		$("#editForm").submit();
                		times=3000;	
                	}
                	
            	var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
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
	               // alert(ret.retMsg+"queryBossMarketFirstList");
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
    	// selectBusiness
    	var option = $("#selecTargetGroupInfo option:selected");
    	$("#targetGroupInfoList").append(option);
    },
    
    editOption:  function (input){

    	var pkid=$("#pkid").val();
    	if(input.value!=""){ 
    	$("#marketOrder option[value='"+pkid+"']").text(input.value);
     	}
    }
};




