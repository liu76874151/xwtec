$(document).ready(function () {
 //   component.initValidate();
	 
	
    component.initForm();
    component.queryParentChannel();
    component.querryTMPList(2);//标准版
    component.querryTMPList(3);//触屏版
    
    
});

var component = {

    // 初始化校验规则
   // initValidate: function () {
//        ValidateUtil.validate({
//            targetForm: "addForm",
//            rules: {
//        	    areaNum: {required: true, minlength: 1, maxlength: 3},
//        	    areaName: {required: true, minlength: 1, maxlength: 20},
//        	    areaJbNum: {required: true},
//            },
//            messages: {
//            	areaNum: {required: "请输入地市编码", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
//            	areaName: {required: "请输入地市名称", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
//            	areaJbNum: {required: "请输入地市级别编码"},
//            }
//        });
   // },
		querryTMPList:function(version){
	$.singleReq({
	    data: {
	    "type":"1",
	    "reqUrl": "content",
	    "reqMethod": "querryTMPList",
	    "version":version,
	    "start": "0",
	    "end": "1000"

	    },
	    success: function (ret) {
	        if (ret.retCode == 0) {
	      	  var result = ret.retObj
	            $.each(result, function(i, item) {
	            	if(version==2){
	            		$("#overviewTmp").append("<option value="+item.pageNum+">"+item.pageName+"("+item.pageNum+")"+"</option>");
	            		$("#detailTmp").append("<option value="+item.pageNum+">"+item.pageName+"("+item.pageNum+")"+"</option>");
	            	}else{
	            		$("#overviewTmp2").append("<option value="+item.pageNum+">"+item.pageName+"("+item.pageNum+")"+"</option>");
	            		$("#detailTmp2").append("<option value="+item.pageNum+">"+item.pageName+"("+item.pageNum+")"+"</option>");
	            	}
	           	
	          });
	        }
	    }
	});
	
},
    // 初始化表单
    initForm: function () {
	
	 
	var channelNum=$("#hiddenChannelNum").val();
	var type=$("#hiddenType").val();
	$("#type"+type).attr("checked",true);
	component.addUrlTr(type);
	$("#channelNum option[value='"+channelNum+"']").attr("selected",true);
	//专区类型为链接的时候增加链接地址行
	 $("input[name='type']")
				.click(
						function() {
							component.addUrlTr();
						}); 
	  
// 
	 $("#btnMoveUp,#btnMoveDown").click(function() {   
		  var $opt = $("#sortNum option:selected:first");   
		  if (!$opt.length) return;   
		  if (this.id == "btnMoveUp") {
		  		$opt.prev().before($opt);   
		  		}else {
		  		$opt.next().after($opt); 
		  		      } 
			}); 

    },
    addUrlTr:function(type){
    	if(!type){type = $("input[name='type']:checked").val();}
		var urlobj = $("#url");
		if (type == 3) {
			if (!urlobj[0]) {
				var tr = "<tr><th align='right'>链接地址：</th>"
						+ "<td class='form_table_content2'>	"
						+ "<input type='text' name='url' id='url' class='form_input'/>"
						+ "</td></tr>";
				$("#addTab tr:eq(4)").after(tr);
			}
		} else {
			if (urlobj[0]) {
				$('#url').parent().parent().remove();
			}
		}
    },
    addOption: function (input){
    	var obj=$("#sortNum option[value='add']");
		if(input.value!=""){ 
			if(obj[0]){
					$("#sortNum option[value='add']").text(input.value);
				}else{
					$("#sortNum").prepend("<option value='add'>"+input.value+"</option>");////prepend	append
				}
			}else{
					if(obj[0]){$("#sortNum option[value='add']").remove();	}
	 	}
	},
	
querySubChannel:function(parentId){
if(!parentId){ parentId=$("#parentId").val(); }
$.singleReq({
    data: {
    "state":"1",
    "reqUrl": "content",
    "reqMethod": "querySubchannel",
    "parentId":parentId,
    "start": "0",
    "end": "1000"

    },
    success: function (ret) {
        if (ret.retCode == 0) {
        	var obj=$("#sortNum option[value='add']");
        	if(obj[0]){
        		var text=obj.text();
        		$("#sortNum").empty().prepend("<option value='add'>"+text+"</option>");	
        	}else{
        		$("#sortNum").empty();
        	}

      	  var result = ret.retObj.records; 
            $.each(result, function(i, item) {
           	$("#sortNum").append("<option value="+item.chanId+">"+item.chanName+"("+item.chanId+")"+"</option>");
            });
        }
    }
});
	},
queryParentChannel:function(){
		var chanId=$("#hiddenChanId").val(); 	
    	  $.singleReq({
              data: {
              "state":"1",
              "reqUrl": "content",
              "reqMethod": "queryAllChannels",
              "start": "0",
              "end": "1000"

              },
              success: function (ret) {
                  if (ret.retCode == 0) {
                	  var result = ret.retObj.records; 
                	 
                      $.each(result, function(i, item) {
                    	 if(chanId==item.chanId){
                    		component.querySubChannel(chanId);
                    	$("#parentId").append("<option value="+item.chanId+"  selected='selected'  >"+item.chanName+"("+item.chanId+")"+" </option>");
                         		 
                    	 }else{
                     	$("#parentId").append("<option value="+item.chanId+">"+item.chanName+"("+item.chanId+")"+"</option>");
                    	 }
                    	 });
                  }
              }
          });
    },
    saveForm: function () {
        if (!$('#addForm').valid()) {
            return;
        }
        var chanName = $("#chanName").val();
        var showName = $("#showName").val();
        var parentId = $("#parentId").val();
        var channelNum = $("#channelNum").val();
        var type = $("input[name='type']:checked").val();
        var overviewTmp=$("#overviewTmp").val();
        var overviewTmp2=$("#overviewTmp2").val();
		var detailTmp=$("#detailTmp").val();
		var detailTmp2=$("#detailTmp2").val();
		var depository=$("#depository").val();
        var sortNum = "";
        $("select[name=sortNum] option").each(
        		  function() {
        			  sortNum=sortNum+$(this).val()+".";
        		  }
        		 );
        if(sortNum.length>0){
        	sortNum=sortNum.substring(0,sortNum.length-1);}
        var desc = $("#desc").val();
        var logo = $("#logo").val();

        $.singleReq({
            data: {
                "reqUrl": "content",
                "reqMethod": "saveChannel",
                "chanName": chanName,
                "showName": showName,
                "parentId": parentId,
                "channelNum": channelNum,
                "overviewTmp": overviewTmp,
                "overviewTmp2": overviewTmp2,
                "detailTmp": detailTmp,
                "detailTmp2": detailTmp2,
                "type": type,
                "sortNum": sortNum,
                "logo": logo,
                "desc": desc,
                "state": "0",
                "depository": depository
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                	var times=0;
                	if(logo){
                		var fileType = logo.substring(logo.indexOf("."),logo.length);
                		$("#fileName").val(result.fileName +""+fileType);
                		$("#chanId").val(result.chanId);
                		$("#addForm").submit();
                		times=3000;	
                	}
                	var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
                }
                else {
                    UOMPComp.showFailedDialog("添加失败！", "");
                }
            }
        });
    }
}
