$(document).ready(function(){
	subSystemEditComponent.historySubSystem();
   	ValidateUtil.validate({
    	targetForm : "editForm",
    	rules : {
    		sysName : {required : true ,minlength : 2, maxlength : 20},
    		sysTitle : {required : true ,minlength : 2, maxlength : 20},
    		sysUri : {required : true, maxlength : 1000 },
    		xh : {required : true, maxlength : 5,digits : true},
    		bz : {required : false}
		},
		messages : {
			sysName : {required : "请输入名称",minlength : "长度必须大于等于{0}", maxlength : "长度必须大于等于{0}"},
			sysTitle : {required : "请输入标题" ,minlength : "长度必须大于等于{0}",maxlength : "长度必须大于等于{0}"},
			sysUri : {required : "请输入URL",maxlength : "长度必须大于等于{0}"},
			xh : {required : "请输入序号",maxlength : "长度必须大于等于{0}",digits : "请输入整数"}
		}
    }); 
    
    
    
});
/**
 * 子系统查询
 */
var subSystemEditComponent = {
			
		/**
		 * 用于回显示数据，便于修改
		 */
		historySubSystem: function() {
			var sysNum = $("#sysNum")[0].value;	  
	           $.singleReq({
	        	   data : 
		   			{
		   				"reqUrl" : "subSystem",
		   				"sysNum" : sysNum,
		   				"reqMethod" : "querySubSystemByNum"
		   			},
					success : function(ret)
					{
						if(ret){
							
							if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
								if(ret.retObj){
									var subSystemList = ret.retObj.subSystemList;
									subSystemInfo = subSystemList[0];
									if(subSystemInfo){
										$("#sysName").val(subSystemInfo.sysName);
										$("#sysTitle").val(subSystemInfo.sysTitle);
										$("#sysUri").val(subSystemInfo.sysUri);
										$("#xh").val(subSystemInfo.xh);
										$("#bz").val(subSystemInfo.bz);
									}
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
		},
	/**
	 * 处理返回数据，创建datagrid
	 */
    editSubmit: function(){
     if($('#editForm').valid()){
	    var sysNum  = $("#sysNum").val();
     	var sysName = $("#sysName").val();
		var sysTitle = $("#sysTitle").val();
		var sysUri =$("#sysUri").val();;
		var xh = $("#xh").val();;
		var bz = $("#bz").val();
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
				"reqMethod" : "updateSubSystem"
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