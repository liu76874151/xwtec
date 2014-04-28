$(document).ready(function () {
    component.queryBusiTag();
}
);
 

var component = {
saveForm: function () {
 	var busiTagList = new Array();
 	var busiTagJson = $.trim($("#busiTagJson",opener.document).val());
 	if (busiTagJson!="") {
 		busiTagList = $.parseJSON(busiTagJson);
 	}

	$('input[name=busiTag]:checked').each(function () {
		var flag = true;
		 var element = {};
         var tagId = $(this).val(); //获取单个value
         var tagName = $(this).attr("tagName");
         
         for (var index = 0; index < busiTagList.length; index++) {
			var num = busiTagList[index].tagId;
			if (num==tagId) {
				flag = false;
			}
		}
         if (flag) {
			var id = tagId;
		    var comment  = "<span id='"+id+"'>"+tagName+"<a href=\"javascript:removeTag('"+tagId+"');\">" +
		     		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
			$("#businessTagList",opener.document).append(comment);
			element.tagId = tagId;
			 element.tagName = tagName;
			 busiTagList.push(element);
		}
		 
     });
	var json = $.toJSON(busiTagList);
	$("#busiTagJson",opener.document).val(json);
 	window.close();
},
getBusiTag : function(){
	try {
		var busiTagList = new Array();
		$('input[name=busiTag]:checked').each(function () {
			 var element = {};
             var code = $(this).val(); //获取单个value
			 element.tagId = code;
			 element.tagName = $(this).attr("tagName");
			 busiTagList.push(element);
         });
		return  busiTagList;
	} catch (e) {
		return "";
	}
	
},
queryBusiTag: function(){
		$.singleReq( {
			data : {
				"reqUrl" : "businessHandler",
				"reqMethod" : "queryBusinessTag",
				"tagState":1,
				"end" : 1000
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for ( var int = 0; int < result.length; int++) {
						var tagDesc = result[int].tagDesc;
                    	var tagEname = result[int].tagEname;
                    	var tagId = result[int].tagId;
                    	var tagName = result[int].tagName;
						var i = 30;
						var desc = "";
						if (tagDesc!=undefined) {
							var length = tagDesc.length;
							do {
								desc+=tagDesc.substring(0,i);
								desc+="<br>";
								tagDesc = tagDesc.substring(i,tagDesc.length);
								if(tagDesc.length<=i){
									desc+=tagDesc;
									break;
								}
							}while (tagDesc.length>i) 
	
							
							for ( var index = 0; index < Math.floor(length/i); index++) {
								desc += tagDesc.substring(index+index*i-1,i+index*i)+"<br>";
							}
						}
						
						var tr = $("<tr><th width='20%' align=\"right\"><input name='busiTag' type=\"checkbox\" value='"+tagId+"' tagName='"+tagName+"'>&nbsp;&nbsp;标签名称：<br/>"
								+"说明：</th><td class=\"form_table_content\">"+tagName+"<br/>"+desc+"</td></tr>");
						$("#tb1").append(tr);
					}
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
}
};