$().ready(function() {
	component.initValidate();
	component.query(0,500);
});

var component = {
	saveForm : function() {
		var jbNum = $("#topBrandName option[value!=-请选择-]:selected").val();
		if (!$('#addForm').valid()) {
	        return;
	    }
		if (jbNum=="0") {
			UOMPComp.showTipDialog("请选择一级品牌名称！", "");
	    	return;
		}
		var brandNum = $("#brandNum").val().toUpperCase();
		var brandName = $("#brandName").val();
		var mj = $("#mj").val();
		var memo = $("#memo").val();
		var bossCode = $("#bossCode").val();
		if (this.queryBrandValidate()) {
			UOMPComp.showTipDialog("该品牌已存在,请检查品牌编码与名称！", "");
	    	return;
		}
		$.singleReq({
            data: {
                "reqUrl": "brand",
                "reqMethod": "saveBrand",
                "brandNum": brandNum,
                "brandName": brandName,
                "jbNum":jbNum,
                "mj": mj,
                "memo": memo,
                "bossCode":bossCode
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    UOMPComp.showSuccessDialog("添加成功！", "");
                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("添加失败！", "");
                }
            }
        });
	},
	//初始化校验规则
    initValidate: function () {
    	jQuery.validator.addMethod("isCode", function (value, element) { 
			
			var tel = /^[A-Za-z_]+$/;  
	    	return this.optional(element) ||tel.test(value);
		}, $.validator.format("请输入规范的品牌编码"));  
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
                brandName: {required: true, minlength: 1, maxlength: 200},
                brandNum:{required: true, minlength: 1, maxlength: 10,isCode:"brandNum"},
                memo:{required: false, minlength: 0, maxlength: 1000}
            },
            messages: {
            	brandName: {required: "请输入品牌名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
            	brandNum: {required: "请输入品牌编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}",isCode:"请输入规范的品牌编码"},
            	memo:{ minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"}
            }
        });
        
    },
    query : function(start, end) {
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		
		$.singleSync( {
			data : {
				"jb" : "2",
				"reqUrl" : "brand",
				"reqMethod" : "queryBrandList",
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj.records;
					for (var index = 0; index < result.length; index++) {
						var brandName = result[index].brandName;
						var brandNum = result[index].brandNum;
						var jbNum = result[index].jbNum;
						var option ="<option value=\""+jbNum+"\">"+brandName+"</option>";
						$("#topBrandName").append(option);
					}
					
				}
			}
		});
	},
	queryBrandValidate : function(){
		var brandNum = $("#brandNum").val().toUpperCase();
		var brandName = $("#brandName").val();
		var flag = true;
			$.singleSync( {
				data : {
					"reqUrl" : "brand",
					"reqMethod" : "queryBrandValidate",
					"brandNum":brandNum,
					"brandName":brandName
				},
				success : function(ret) {
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
	}
}