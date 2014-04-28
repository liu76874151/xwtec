var combo;
var parbusiTypeNum='';
$().ready(function() {
	parbusiTypeNum = $("#parbusiTypeNum").val();
	var channelNum = $("#channelNum").val();
	if ($.trim(channelNum)=="") {
		
	}else{
		$("#channel").val(channelNum);
		$("#channel").prop({disabled:"true"});
	}
	window.dhx_globalImgPath = GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCombo/codebase/imgs/";
	combo = new dhtmlXCombo("combo_zone", "alfa", 300);
	component.initValidate();
	component.queryParentBusiType(0,50000);
});
jQuery.extend({
    singleSync: (function () {
        var default_options = {
            "type": "post",
            "timeout": "320000",
            "contentType": "application/x-www-form-urlencoded; charset=UTF-8",
            "url": GLOBAL_INFO.COMMON_REQ_URI,
            "async":false,
            "dataType": "json",
            "success": function (data) {
                alert("Ajax Success!");
            },
            "error": function (request, textStatus, errorThrown) {
            },
            "complete": function () {
            }
        };
        return function (user_options) {
            var options = {};
            $.extend(options, default_options, user_options);
            $.ajax(options);
        };
    })()
});
var component = {
	saveForm : function() {
		if (!$('#addForm').valid()) {
	        return;
	    }
	    if(component.queryBusiType()){
	    	UOMPComp.showTipDialog("业务类型编码已经存在！", "");
	    	return;
	    }
	    if($.trim(combo.getSelectedValue())==""){
	    	UOMPComp.showConfirmDialog("【系统提示】\n\n您确定添加顶级类型吗？点击“否”则返回！", {
                "yes": function () {
                	component.addBusiType();
                	component.addSingleOption();
                },
                "no": function () {
                	return;
                }
            });
	    }else{
	    	component.addBusiType();
	    	component.addSingleOption();
	    }
	    
	},
	addBusiType : function(){
	    var parentNum = $.trim(combo.getSelectedValue());
		var busiTypeNum = $("#busiTypeNum").val().toUpperCase();
		var busiTypeName = $("#busiTypeName").val();
		var busiTypeDesc = $("#busiTypeDesc").val();
		var busiTypeYx = $("#busiTypeYx").val();
		var loadType = $("#loadType option:selected").val();
		var mj = $("#mj option:selected").val();
		var channelNum = $("#channel").val();
		
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "addBusiType",
                "busiTypeNum": busiTypeNum,
                "busiTypeName": busiTypeName,
                "busiTypeDesc": busiTypeDesc,
                "busiTypeYx": busiTypeYx,
                "loadType": loadType,
                "mj": mj,
                "channelNum":channelNum,
                "parentNum": parentNum
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                	var jbNum = result.jbNum;
                	var busiTypeNum = result.busiTypeNum;
                	var busiTypeName = result.busiTypeName;
                	var jb = result.jb;
                	var mj = result.mj;
                	
                	var parid = $("#parid").val();
                	var parjbNum = $("#parjbNum").val();
                	var checkbox = "<input name='checkedjbNum' type='checkbox' busiTypeNum='"+busiTypeNum+"'  busiTypeName='"+busiTypeName+"' value='"+jbNum+"'>";
                	if (parjbNum!="") {
                		$("#"+parid,opener.document).append("<span id='"+busiTypeNum+"'></span>");
                		$("#"+busiTypeNum,opener.document).append("<label>"+checkbox+busiTypeName+"</label>");
	                	if (mj=='0') {
	                		$("#"+busiTypeNum,opener.document).append("<a class=\"arrow-down\" id=\"toggle_"+jbNum+"\" onclick=\"component.querySubType('"+busiTypeName+"','"+parjbNum+"','"+jbNum+"','"+jb+"','"+channelNum+"');Effect('"+jbNum+"',this.id);\"\">" +
									"</a>");
	                		$("#"+busiTypeNum,opener.document).append("<a href=\"javascript:component.addBusiType('"+busiTypeNum+"','"+jbNum+jb+"','"+parjbNum+"','"+channelNum+"');void(0);\" title=\"添加\" class=\"add-icon\"></a>");
	                	}
	                	$("#"+busiTypeNum,opener.document).append("<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNum+"','"+parjbNum+"','"+jb+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a>");
						$("#"+busiTypeNum,opener.document).append("<em>|</em>");
	                	window.close();
                	}else{
                    	UOMPComp.showSuccessDialog("添加成功！", "");
                    	document.addForm.reset();
//                    	top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                	}
                }
                else {
//                    UOMPComp.showFailedDialog("添加失败！", "");
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
    initCombo : function(data){
		combo.enableOptionAutoWidth(1);
		combo.enableFilteringMode(true);
		combo.clearAll();
		combo.addOption(data);
		combo.setComboValue(parbusiTypeNum);
		if (parbusiTypeNum!="") {
			$("#reset").attr("onClick","#");//
			combo.readonly(true);
		}
    },
    queryBusiType : function() {
		var busiTypeNum = $("#busiTypeNum").val().toUpperCase();
		var channelNum = $("#channel").val();
		var flag = true;
		$.singleSync({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryBusiTypeBean",
                "channelNum":channelNum,
                "busiTypeNum": busiTypeNum
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                	if(result==undefined){
                		flag = false;
                	}else{
                		flag = true;
                	}
                }
                else {
                    flag = true;
                }
            }
        });
        return flag;
	},
	queryParentBusiType: function(start,end) {
		var channelNum = $("#channel").val();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryParentBusiType",
                "mj":"0",
                "channelNum":channelNum,
                "start": start,
                "end": end
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                   var result = ret.retObj;
                   var busitype = new Array();
                   for (var index = 0; index < result.length; index++) {
                   		var  busiTypeNum = result[index].busiTypeNum;
                   		var  busiTypeName = result[index].busiTypeName;
                   		busiTypeName = busiTypeName+"("+busiTypeNum+")";
                   		var element = new Array();
                   		element.push(busiTypeNum);
                   		element.push(busiTypeName);
						busitype.push(element);
                   }
                   component.initCombo(busitype);
                }
                else {
                }
            }
        });
	},
	addSingleOption:function(){
		var mj = $("#mj option:selected").val();
		if (mj=="0") {
			var busiTypeNum = $("#busiTypeNum").val().toUpperCase();
			var busiTypeName = $("#busiTypeName").val();
			busiTypeName = busiTypeName+"("+busiTypeNum+")";
			var optionArray = new Array();
			var option = new Array();
			option.push(busiTypeNum);
			option.push(busiTypeName);
			optionArray.push(option);
	    	combo.addOption(optionArray);
		}
	}
}