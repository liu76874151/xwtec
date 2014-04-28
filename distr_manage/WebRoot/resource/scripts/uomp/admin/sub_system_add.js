$(document).ready(function(){
	ValidateUtil.validate({
    	targetForm : "addForm",
    	rules : {
    		sysNum1 : {required : true , maxlength : 2},
    		sysName1 : {required : true ,minlength : 2, maxlength : 20},
    		sysTitle1 : {required : true ,minlength : 2, maxlength : 20},
    		sysUri1 : {required : true, maxlength : 1000 },
    		xh1 : {required : true, maxlength : 5,digits : true},
    		bz1 : {required : false}
		},
		messages : {
			sysNum1 : {required : "请输入编码",maxlength :"长度必须大于等于{0}"},
			sysName1 : {required : "请输入名称",minlength : "长度必须大于等于{0}", maxlength : "长度必须大于等于{0}"},
			sysTitle1 : {required : "请输入标题" ,minlength : "长度必须大于等于{0}",maxlength : "长度必须大于等于{0}"},
			sysUri1 : {required : "请输入URL",maxlength : "长度必须大于等于{0}"},
			xh1 : {required : "请输入序号",maxlength : "长度必须大于等于{0}",digits : "请输入整数"}
		}
    });
});


/**
 * 子系统add
 */
   function addSubmit(){
	   if($('#addForm').valid()){
      	  //此前需要做验证  
      	    var sysNum  =$("#sysNum1").val();
        	var sysName = $("#sysName1").val();
    		var sysTitle = $("#sysTitle1").val();
    		var sysUri =$("#sysUri1").val();
    		var xh = $("#xh1").val();
    		var bz = $("#bz1").val();
    		if(sysNum=="00" || sysNum==00 || sysNum==0){
      	         UOMPComp.showFailedDialog("00编码不允许使用,请重新填写","");
      	    }else{
    		$.singleReq({
    			data : 
    			{
    				"reqUrl":"subSystem",
    				"sysNum":sysNum,
    				"sysName":sysName,
    				"sysTitle":sysTitle,
    				"sysUri":sysUri,
    				"xh":xh,
    				"bz":bz,
    				"reqMethod" : "saveSubSystem"
    			},
    			success : function(ret)
    			{
    				if(ret){
						if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
							var resMsg = ret.resMsg;
							var retCode = ret.retCode;
							UOMPComp.showSuccessDialog(resMsg,"");
							if(GLOBAL_INFO.SYS_FAILED == retCode){
								
							}else if(GLOBAL_INFO.SYS_SUCCESS){
								top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
							}
						}else{
							if(ret.resMsg){
								UOMPComp.showFailedDialog(ret.resMsg,"");
							}else{
								UOMPComp.showFailedDialog("系统异常","");
							}
						}
					}
    				
    			}
    		}); 
    	   }
	   }	
    };
