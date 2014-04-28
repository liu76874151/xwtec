$().ready(function() {
	component.queryBusinessTag();
});

var component = {
    queryBusinessTag : function() {
		$.singleReq({
            data: {
                "reqUrl": "businessHandler",
                "reqMethod": "queryBusinessTag",
                "tagState":1,
                "end": 1000
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                    for (var index = 0; index < result.length; index++) {
                    	var tagDesc = result[index].tagDesc;
                    	var tagEname = result[index].tagEname;
                    	var tagId = result[index].tagId;
                    	var tagName = result[index].tagName;
                    	
                    	var item = "<span><label><input name=\"tagCheck\" type=\"checkbox\" value=\""+tagId+"\" />"+tagName+
                    	"</label><a onclick='component.updateBusiTag("+tagId+");' class=\"edit-icon\" title=\"修改\"></a></span>";
                    	
                    	$("#busiTag dl dd p").append(item).append("<em>|</em>");
                    }
                }
            }
        });
	},
	getArrayValue : function(obj){//获取checkBox的值
		var chk_value =[];    
		  obj.each(function(){    
			  chk_value.push($(this).val());    
		  });    
		  chk_value = chk_value.length==0 ?'':chk_value;   
		  return chk_value;	
	},
	deleteBusiTag:function(){
		var tagId = this.getArrayValue($("input[name=tagCheck]:checked")).toString();
		if(tagId==""){
			UOMPComp.showTipDialog("请选择要删除的标签！", "");
			return;
		}
		UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
			            data: {
			                "reqUrl": "businessHandler",
			                "reqMethod": "deleteBusinessTag",
			                "tagId":tagId
			            },
			            success: function (ret) {
			                if (ret.retCode == 0) {
			                	UOMPComp.showSuccessDialog("删除成功！", "");
			                	window.location.reload();
			                }
			            }
			        });
                },
                "no": function () {
                }
            });
		
	},
    addBusiTag : function(){//标签添加
//		var size = {"height":"300","width":"1000"};
//		var url = GLOBAL_INFO.CONTEXTPATH + "/page/business/businessTag/businessTag_add.jsp";
//		top.UOMPDialog.showSubPage("业务标签添加",url,null,size);
    	window.open(GLOBAL_INFO.CONTEXTPATH + "/page/business/businessTag/businessTag_add.jsp",
						'businessTag_add',
						'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
    },
    updateBusiTag : function(tagId){//标签修改
//		var size = {"height":"300","width":"1000"};
//		var url = GLOBAL_INFO.CONTEXTPATH + "/page/business/businessTag/businessTag_modify.jsp?tagId="+tagId;
//		top.UOMPDialog.showSubPage("业务标签修改",url,null,size);
    	window.open(GLOBAL_INFO.CONTEXTPATH + "/page/business/businessTag/businessTag_modify.jsp?tagId="+tagId,
						'businessTag_modify',
						'height=500, width=800, top=60,left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
    }
    
}