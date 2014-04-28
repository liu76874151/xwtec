$().ready(function() {
	component.initValidate();
	component.queryBusiType();
});
var component = {
	saveForm : function() {
		if (!$('#addForm').valid()) {
	        return;
	    }
		var busiTypeNum = $("#busiTypeNum").val().toUpperCase();
		var busiTypeName = $("#busiTypeName").val();
		var busiTypeDesc = $("#busiTypeDesc").val();
		var busiTypeYx = $("#busiTypeYx").val();
		var loadType = $("#loadType option:selected").val();
		var mj = $("#mj option:selected").val();
		var channelNum = $("#channelNum").val();
		
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "modifyBusiType",
                "busiTypeNum": busiTypeNum,
                "busiTypeName": busiTypeName,
                "busiTypeDesc": busiTypeDesc,
                "busiTypeYx": busiTypeYx,
                "loadType": loadType,
                "channelNum":channelNum,
                "mj": mj
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var jbNum = $("#jbNum").val();
                	var parjbNum = $("#parjbNum").val();
                	var jb = $("#jb").val();
//                	alert("parjbNum:"+parjbNum+" jbNum:"+jbNum+" jb:"+jb);
                	var checkbox = "<input name='checkedjbNum'  busiTypeNum='"+busiTypeNum+"' busiTypeName='"+busiTypeName+"' type='checkbox' value='"+jbNum+"'>";
                	$("#"+busiTypeNum,opener.document).empty();
        			
            		if (jb=='1') {
            			$("#"+busiTypeNum,opener.document).append("<label>"+checkbox+"- "+busiTypeName+"</label>" +
											            "<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNum+"','"+jbNum+"','"+jb+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a><s class=\"broken-line\"></s>");
            		}else{
	                	if (mj=='0') {
                			$("#"+busiTypeNum,opener.document).append("<label>"+checkbox+busiTypeName+"</label><a onclick=\"component.querySubType('"+busiTypeName+"','"+parjbNum+"','"+jbNum+"','"+jb+"');Effect('"+jbNum+"',this.id);\" id=\"toggle_"+jbNum+"\" class=\"arrow-down\"></a>" +
															"<a href=\"javascript:component.addBusiType('"+busiTypeNum+"','"+jbNum+jb+"','"+parjbNum+"','"+channelNum+"');void(0);\" title=\"添加\" class=\"add-icon\"></a>" +
															"<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNum+"','"+parjbNum+"','"+jb+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a>");
                		}else{
                			$("#"+busiTypeNum,opener.document).append("<label>"+checkbox+busiTypeName+"</label><a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNum+"','"+parjbNum+"','"+jb+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a>");
                		}
                	}
                    window.close();
                }
                else {
//                    UOMPComp.showFailedDialog("修改失败！", "");
                }
            }
        });
	},
	//初始化校验规则
    initValidate: function () {
    	jQuery.validator.addMethod("isCode", function (value, element) { 
			
			var tel = /^[A-Za-z0-9_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format("请输入规范的业务类型编码"));
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
                busiTypeNum: {required: true, minlength: 1, maxlength: 20,isCode:"busiTypeNum"},
                busiTypeName: {required: true, minlength: 1, maxlength: 100},
                busiTypeDesc:{required: false, minlength: 1, maxlength: 500},
                busiTypeYx:{required: false, minlength: 1, maxlength: 500}
            },
            messages: {
            	busiTypeNum: {required: "请输入类型编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"请输入规范的业务类型编码"},
            	busiTypeName: {required: "请输入类型名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	busiTypeDesc:{ minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	busiTypeYx:{ minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
            }
        });
        
    },
    queryBusiType : function() {
		var busiTypeNum = $("#busiTypeNum").val().toUpperCase();
		var channelNum =  $("#channelNum").val();
		var flag = true;
		$.singleSync({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryBusiTypeBean",
                "channelNum": channelNum,
                "busiTypeNum": busiTypeNum
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                	var busiTypeName = result.busiTypeName;
                	var jb = result.jb;
                	var loadType = result.loadType;
                	var mj = result.mj;
                	var busiTypeDesc = result.busiTypeDesc;
                	var busiTypeYx = result.busiTypeYx;
                	var jbNum = result.jbNum;
                	
                	$("#busiTypeName").val(busiTypeName);
                	$("#jb").val(jb);
                	$("#loadType").val(loadType);
                	$("#mj").val(mj);
                	$("#busiTypeDesc").val(busiTypeDesc);
                	$("#busiTypeYx").val(busiTypeYx);
                	$("#jbNum").val(jbNum);
                }
            }
        });
	}
}