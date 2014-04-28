var busiNum;
$(document).ready(function () {
        component.initForm();
        
        component.initValidate();
    }
);

var component = {
//初始化校验规则
    initValidate: function () {
    	jQuery.validator.addMethod("isCode", function (value, element) { 
			
			var tel = /^[A-Za-z0-9_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format("请输入规范的短地址标识"));  
        ValidateUtil.validate({
            targetForm: "editForm",
            rules: {
                shortBusi:{required: true, minlength: 1, maxlength: 2,isCode:"shortBusi"},
                busiNum:{required: true},
                channelNum:{required: true},
                startTime:{dateISO: "yyyy-MM-dd"},
                endTime:{dateISO: "yyyy-MM-dd"}
            },
            messages: {
            	shortBusi: {required: "请输入规范的短地址标识", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"请输入规范的短地址标识"},
            	busiNum:{required: "请选择业务编码"},
            	channelNum:{required: "请选择渠道编码"},
            	startTime:{dateISO: "请输入正确的日期格式"},
                endTime:{dateISO: "请输入正确的日期格式"}
            }
        });
        
    },
		
		 initBusiNum: function () {
	var channelNumtemp=$("#shortChannel").val();
	var channelNum;
	if(channelNumtemp!=""){channelNum=channelNumtemp;}else{channelNum="-1"}
	 $.singleReq({
         data: {
             "reqUrl": "businessInfoHandler",
             "reqMethod": "queryBusiInfoList",
             "channelNum": channelNum,
             "start": 0,
             "end": 9999
             
         },
         success: function (ret) {
             if (ret.retCode == 0) {
            		$("#busiNum").empty().append("<option selected value=''   >---请选择---</option>");
            
             	var result = ret.retObj.records; 
                 $.each(result, function(i, item) {
                	 if(item.busiNum==busiNum){
                		 $("#busiNum").append("<option value="+item.busiNum+" selected>"+item.busiName+"("+item.busiNum+")</option>"); 
                	 }else{
                	 $("#busiNum").append("<option value="+item.busiNum+" >"+item.busiName+"("+item.busiNum+")</option>");
                	 }
                	  
                 });
 					
             }
            
         }
     });
    },
// 初始化表单
		initForm: function () {
	    	var myCalendar=new dhtmlXCalendarObject(["startTime","endTime"]);
	    	//myCalendar.setDateFormat("%Y-%m-%d %H:%i:%s");
	    	
	    	   $.singleReq({
	               data: {
	                   "reqUrl": "businessHandler",
	                   "reqMethod": "queryBusiShortChannel",
	                   "start": 0,
	                   "end": 9999
	                   
	               },
	               success: function (ret) {
	                   if (ret.retCode == 0) {

	                  
	                   	var result = ret.retObj.records; 
	                       $.each(result, function(i, item) {
	                      	 $("#shortChannel").append("<option value="+item.shortChannel+" >"+item.channelName+"("+item.shortChannel+")</option>");
	                      	  
	                       });
	                       component.initDate();	
	                   }
	                  
	               }
	              
	           });
	    	  
	    },
    initDate: function () {
	
        $.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "findOnebusiShortAdd",
                "shortId": $("#pkid").val()
            },
            success: function (ret) {
                var result = ret.retObj;
                $("#shortBusi").val(result.shortBusi);
                $("#channelNum option[value='"+result.channelNum+"']").attr("selected",true);
                $("#shortChannel option[value='"+result.shortChannel+"']").attr("selected",true);
                busiNum=result.busiNum;
                component.initBusiNum();
                $("#busiNum option[value='"+result.busiNum+"']").attr("selected",true);
                $("#state"+result.state).attr("checked",true);
                $("#endTime").val(result.endTime);
                $("#startTime").val(result.startTime);
               
            }
        });
    },

    saveForm: function () {
    	if (!$('#editForm').valid()) {
            return;
        }
        if (this.timeViladidate()) {
	    	UOMPComp.showTipDialog("请检查日期范围！", "");
	    	return;
	    }
    	var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var state = $("input[name='state']:checked").val();
	    var channelNum= $("#channelNum").val();
	    var busiNum= $("#busiNum").val();
	    var shortChannel= $("#shortChannel").val();
	    var shortBusi= $("#shortBusi").val();
	    
        $.singleReq({
            data: {
        	 "reqUrl": "businessHandler",
             "reqMethod": "updateBusiShortAdd",
             "startTimeStr": startTime,
             "state": state,
             "endTimeStr": endTime,
             "busiNum": busiNum,
            "shortChannel": shortChannel,
            "shortBusi": shortBusi,
             "channelNum": channelNum,
             "shortId": $("#pkid").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    UOMPComp.showSuccessDialog("修改成功！", "");
                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
                }
            }
        });
    },
    checkTime:function(startTime,endTime){//date validiate
	      /*var startTime="2013-12-12 18:00:00";
	      var endTime="2013-12-12 18:00:01";*/
	      var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
	      var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
	      if(end<start){
	          return false;  
	      }  
	      return true;  
	  },
	  timeViladidate: function(){
		var flag = false;
		var startTime = $("#startTime").val();
    	var endTime = $("#endTime").val();
    	if (startTime=="" && endTime=="") {
    		flag = false;
    	}else{
	    	if (startTime!="" && endTime!="") {
	    		if (!this.checkTime(startTime,endTime)) {
					flag = true;
				}
	    	}else{
		    	flag = true;
	    	}
    	}
	    return flag;
	}
};




