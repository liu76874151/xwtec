$(document).ready(function() {
	    ValidateUtil.validate({
    	targetForm : "modifyForm",
    	rules : {
			newPasswd    : {required : true , maxlength : 50},
			oldPasswd    : {required : true , maxlength : 50},
			comfiPasswd  : {required : true}
		},
		messages : {
			newPasswd    : {required    : "请输入新密码" ,maxlength : "长度必须小于等于{0}"},
			oldPasswd    : {required    : "请输入旧密码" ,maxlength : "长度必须小于等于{0}"},
			comfiPasswd  : {required    : "请输入确认密码"}
		}
    });
		    });


var userPasswordVComponent = {

	//调用后台方法，获取数据
	modifyPasswd : function()
	{
	    var flag = true ;
		var oldPasswd = $("#oldPasswd").val();
		var newPasswd = $("#newPasswd").val();
		var comfiPasswd = $("#comfiPasswd").val();
		 if($('#modifyForm').valid()){
		  if(newPasswd != comfiPasswd){
       	    flag = false;
	        UOMPComp.showFailedDialog("\n\n两次密码输入不一致！","");
		  }
			if(flag){
		         
		         $.singleReq({
				              data : 
				              {
					            "reqUrl" : "userPassword",
					            "newPasswd" : newPasswd,
					            "oldPasswd" : oldPasswd,
					            "reqMethod" : "modifyUserPasswd"
				               },
				
				               success : function(ret)
				               {
					               var resMsg = ret.resMsg;
					               if(ret.retCode == "1"){			                
					                 UOMPComp.showFailedDialog(resMsg,"");
					               }else{
					                 UOMPComp. showSuccessDialog(resMsg,"");
					                 top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
					                 
					               }
				               }
			               });
			}
		 }
	
	    }
}