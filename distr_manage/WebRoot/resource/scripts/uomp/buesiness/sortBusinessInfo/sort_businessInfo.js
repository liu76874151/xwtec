$().ready(function(){
	component.initValidate();
})

var component = {
	//初始化校验规则
    initValidate: function () {
    	jQuery.validator.addMethod("isCode", function (value, element) { 
			var tel = /^[A-Za-z0-9_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format("请输入规范的业务编码"));  
        ValidateUtil.validate({
            targetForm: "queryForm",
            rules: {
                busiNum: {required: true, minlength: 1, maxlength: 50,isCode:"busiNum"},
                busiName:{required: false, minlength: 0, maxlength: 100}
            },
            messages: {
            	busiNum: {required: "请输入业务编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"请输入规范的业务编码"},
            	busiName:{minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
            }
        });
        
    },
	query : function(end) {
		if (!$('#queryForm').valid()) {
	        return;
	    }
	    
		var busiName = $("#busiName").val();
		var busiNum = $("#busiNum").val().toUpperCase();
		var channelNum = $("#channelNum option:selected[value!='-请选择-']").val();
		var state = "";
		if (state=="") {
			state = "0";
		}
		if (end == undefined) {
			end = 1000;
		}
		$.singleReq({
            data:{
				"busiNum" : busiNum,
				"reqUrl" : "businessInfoHandler",
				"reqMethod" : "queryForSort",
				"state" :state,
				"channelNum" :channelNum,
				"busiName":busiName,
				"end" : end
			},
            success: function (ret) {
                if (ret.retCode == 0) {
                   var result = ret.retObj;
                   $("#sortable").empty();
                   for (var index = 0; index < result.length; index++) {
                   		var busiNum = result[index].busiNum;
                   		var busiName = result[index].busiName;
                   		$("#sortable").append("<li class=\"ui-state-default\" busiNum=\""+busiNum+"\">"+busiName+"</li>");
                   }
                }
                else {
                	$("#sortable").empty();
                	UOMPComp.showTipDialog(ret.resMsg, "");
                }
            }
        });
	},
 	getDetail:function(){
		var array = new Array();
		var busi = $("li");
		var channelNum = $("#channelNum option:selected[value!='-请选择-']").val();
		for ( var i = 0; i < busi.length; i++) {
			var element = {};
			element.busiNum = $(busi[i]).attr("busiNum");
			element.channelNum = channelNum;
			array.push(element);
		}
		return $.toJSON(array);
	},
	save:function(){
		var sortJson = this.getDetail();
		$.singleReq({
            data:{
				"sortJson" : sortJson,
				"reqUrl" : "businessInfoHandler",
				"reqMethod" : "sortBusinessInfo"
			},
            success: function (ret) {
            	if (ret.retCode == 0) {
                    UOMPComp.showSuccessDialog("保存成功！", "");
                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("保存失败！", "");
                }
            }
        });
	}
}