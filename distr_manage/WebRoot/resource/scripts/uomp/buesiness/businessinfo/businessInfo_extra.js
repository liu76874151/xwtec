var myPop;
$(document).ready(function () {
    component.queryBusiExtra(0,500);
//	component.addValidate();
//	component.initValidate();
}
);
 
function showPopup(inp,id,value) {
	var myPop;
	myPop = new dhtmlXPopup({
        mode: "right"
    });
    myPop.attachHTML($(inp).attr("desc"));
    if (myPop.isVisible()) {
        myPop.hide();
    } else {
        var x = getAbsoluteLeft(inp);
        var y = getAbsoluteTop(inp);
        var w = inp.offsetWidth;
        var h = inp.offsetHeight;
        myPop.show(x, y, w, h);
    }
    
}
function hidePopup() {
    if (myPop)
        myPop.hide();
}

var component = {
addValidate : function(){
		jQuery.validator.addMethod("isChecked", function (value, element) {
			var flag = false;
			var id = element.id;
			alert(id+":"+$('input[name=busiExtra][value='+id+']').attr("checked"));
			if ($('input[name=busiExtra][value='+id+']').attr("checked")=="checked" && value!="") {
				flag = true;
			}
	   	 	return flag==true?true:false;
		}, $.validator.format("请输入数值!"));
	},
initValidate: function () {
        ValidateUtil.validate({
            targetForm: "addForm",
            rules: {
//                attrValue: {required: false,isChecked: ""}
            },
            messages: {
//            	attrValue: {required: "请输入数值",isChecked:"请输入数值!"}
            }
        });
        $("input[name^=attrValue]").each(function(){
	     $(this).rules("add", {
	     	required: false,
	       isChecked: "attrValue",
	       messages: {
	         required: "请输入数值",isChecked:"请输入数值!"
	       }
	     });   
	   });
        
    },
saveForm: function () {
//	alert($('#addForm').valid());
	if (this.viladiate()) {
		UOMPComp.showTipDialog("请输入数值!", ""); return;
	}
	
 	var obj = this.getBusiExtra();
 	var busiExyraList = new Array();
 	var busiExtraJson = $.trim($("#busiExtraJson",opener.document).val());
 	if (busiExtraJson!="") {
 		busiExyraList = $.parseJSON(busiExtraJson);
 	}

         
	$('input[name=busiExtra]:checked').each(function () {
	 	var flag = true;
		var element = {};
	     var attrKey = $(this).val(); //获取单个value
	     var attrName = $("#"+attrKey).attr("attrName");
	     var attrVale = $("#"+attrKey).val();
		for (var index = 0; index < busiExyraList.length; index++) {
			var num = busiExyraList[index].attrKey;
			if (num==attrKey) {
				flag = false;
			}
		}
		
		if (flag) {
			var id = attrKey;
		    var comment  = "<span id='"+id+"'>"+attrName+"<a href=\"javascript:removeAttr('"+attrKey+"');\">" +
		     		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
			$("#businessExtraList",opener.document).append(comment);
			
			element.attrKey = attrKey;
			 element.attrName = attrName;
			 element.attrVale = attrVale;
			 busiExyraList.push(element);
			flag= false;
		}
		
	});
	var json = $.toJSON(busiExyraList);
	//businessExtraList
	$("#busiExtraJson",opener.document).val(json);
	window.close();
},
viladiate : function(){
	try {
		var flag = false;
		$('input[name=busiExtra]:checked').each(function () {
			 var element = {};
             var code = $(this).val(); //获取单个value
			 element.attrKey = code;
			 element.attrName = $("#"+code).attr("attrName");
			 if ($.trim($("#"+code).val())=="") {
				flag=true;
			 }
         });
		return  flag;
	} catch (e) {
		return true;
	}
	
},
getBusiExtra : function(){
	try {
		var busiExtraList = new Array();
		$('input[name=busiExtra]:checked').each(function () {
			 var element = {};
             var code = $(this).val(); //获取单个value
			 element.attrKey = code;
			 element.attrName = $("#"+code).attr("attrName");
			 element.attrVale = $("#"+code).val();
			 busiExtraList.push(element);
         });
		return  busiExtraList;
	} catch (e) {
		return "";
	}
	
},
queryBusiExtra: function(start, end){
	  if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 100;
		}
		$.singleSync( {
			data : {
				"reqUrl" : "businessHandler",
				"reqMethod" : "queryBusinessExtra",
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj.records;
					for ( var int = 0; int < result.length; int++) {
						var attrDesc = result[int].attrDesc;
						var attrKey = result[int].attrKey;
						var attrName = result[int].attrName;
						var attrType = result[int].attrType;
						var name = "";
						switch (attrType) {
							case 0:
								name = "字符串";
								break;
							case 1:
								name = "数值";
								break;
							case 2:
								name="日期类型";
								break;
							default:
								break;
						}
						var i = 30;
						var length = attrDesc.length;
						var desc = "";
						do {
							desc+=attrDesc.substring(0,i);
							desc+="<br>";
							attrDesc = attrDesc.substring(i,attrDesc.length);
							if(attrDesc.length<=i){
								desc+=attrDesc;
								break;
							}
						}while (attrDesc.length>i) 

						
						for ( var index = 0; index < Math.floor(length/i); index++) {
							desc += attrDesc.substring(index+index*i-1,i+index*i)+"<br>";
						}
						var tr = $("<tr><th width='20%' align=\"right\"><input name='busiExtra' type=\"checkbox\" value='"+attrKey+"'>&nbsp;&nbsp;"+attrKey+"：<br/>"
								+name+"：</th><td class=\"form_table_content\">"+attrName+"<br/><input id=\""+attrKey+"\" name='attrValue"+int+"' type=\"text\" attrName='"+attrName+"' onclick='showPopup(this,this.id,this.value);' onblur='hidePopup();'  desc='"+desc+"'><span class=\"errorMsg\"></span></td></tr>");
						$("#tb1").append(tr);
					}
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
}
};