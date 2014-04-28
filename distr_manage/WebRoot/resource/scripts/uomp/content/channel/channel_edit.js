var type="";
 var urltext="";
 var parentId="";
$(document).ready(function () {
       //component.initValidate();
        component.initForm();
       
    }
);

var component =
{
    //初始化校验规则
  //  initValidate: function () {
//        ValidateUtil.validate({
//            targetForm: "editForm",
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
//    },
		querryTMPList:function(pageNum,version,idName){
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
	            if(pageNum==item.pageNum){
	             $("#"+idName).append("<option value="+item.pageNum+"  selected>"+item.pageName+"("+item.pageNum+")"+"</option>");
	            }else{
	            $("#"+idName).append("<option value="+item.pageNum+">"+item.pageName+"("+item.pageNum+")"+"</option>");
	            }	
	            	
	           	
	          });
	        }
	    }
	});
	
},
		
	    addOption: function (input){
	var chanId=$("#pkid").val();
	var obj=$("#sortNum option[value='"+chanId+"']");
	if(input.value!=""){ 
		if(obj[0]){
				$("#sortNum option[value='"+chanId+"']").text(input.value);
			}else{
				$("#sortNum").prepend("<option value='"+chanId+"'>"+input.value+"</option>");////prepend	append
			}
		}else{
				if(obj[0]){$("#sortNum option[value='"+chanId+"']").remove();	}
 	}
},
	querySubChannel:function(iparentId,ichanId){
	if(iparentId==""){
		iparentId=$("#parentId").val(); 
	}
	if(ichanId==""){
		ichanId=$("#pkid").val()
	}
	
	$.singleReq({
	    data: {
	    "state":"1",
	    "reqUrl": "content",
	    "reqMethod": "querySubchannel",
	    "parentId":iparentId,
	    "start": "0",
	    "end": "1000"

	    },  
	    success: function (ret) {
	        if (ret.retCode == 0) {
	        	var obj=$("#sortNum option[value='"+ichanId+"']");
	        	if(obj[0]){
	        		var text=obj.text();
	        		$("#sortNum").empty();
	        		if(iparentId!=parentId)
	        		$("#sortNum").prepend("<option value='"+ichanId+"'>"+text+"</option>");	
	        	}else{
	        		$("#sortNum").empty();
	        	}

	      	  var result = ret.retObj.records; 
	            $.each(result, function(i, item) {
	            	if(item.chanId==ichanId){
	            	
	    	           	$("#sortNum").append("<option value="+item.chanId+" selected='selected'  >"+item.chanName+"("+item.chanId+")"+"</option>");

	            	}else{
	    	           	$("#sortNum").append("<option value="+item.chanId+">"+item.chanName+"("+item.chanId+")"+"</option>");

	            	}
	            
	            	});
	        }
	    }
	});
		},
		
		queryParentChannel:function(iparentId){
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
	                    	  if(item.chanId==iparentId){
	  	                     	$("#parentId").append("<option value="+item.chanId+" selected='selected'  >"+item.chanName+"("+item.chanId+")"+"</option>");

	                    	  }else{
	                    		  
	  	                     	$("#parentId").append("<option value="+item.chanId+" >"+item.chanName+"("+item.chanId+")"+"</option>");

	                    	  }
	                      });
	                  }
	              }
	          });
	    },
	OnClickaddUrl:function(urltext){
	    	//专区类型为链接的时候增加链接地址行
	     	 $("input[name='type']")
				.click(
						function() {
							var type = $("input[name='type']:checked").val();
							var urlobj = $("#url");
							if (type == 3) {
								if (!urlobj[0]) {
									component.addUrl(type,urltext);
								}
							} else {
								if (urlobj[0]) {
									$('#url').parent().parent().remove();
								}
							}
						}); 

	    },   
	    
	    addUrl:function(type,urltext){
	    	if(type==3){
	    		if(urltext==undefined){urltext="";}
				var tr = "<tr><th align='right'>链接地址：</th>"
						+ "<td class='form_table_content2'>	"
						+ "<input type='text' name='url' id='url' class='form_input' value='"+urltext+"'/>"
						+ "</td></tr>";
				$("#editTab tr:eq(4)").after(tr);	
				}
	    },
	    
    initForm: function () {
	    	
	    	
	    	 $("#btnMoveUp,#btnMoveDown").click(function() {   
	   		  var $opt = $("#sortNum option:selected:first");   
	   		  if (!$opt.length) return;   
	   		  if (this.id == "btnMoveUp") {
	   		  		$opt.prev().before($opt);   
	   		  		}else {
	   		  		$opt.next().after($opt); 
	   		  		      } 
	   			}); 
	    	 
        $.singleReq({
            data: {
                "reqUrl": "content",
                "reqMethod": "queryChannelByPkid",
                "chanId": $("#pkid").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    var chanId=result.chanId;
                     parentId=result.parentId;
                    var channelNum=result.channelNum;
                    component.querySubChannel(parentId,chanId);
                    component.queryParentChannel(parentId);
                    $("#channelNum option[value='"+channelNum+"']").attr("selected",true);
                    type=result.type;
                    urltext=result.url;
                    component.addUrl(type,urltext);
                    component.OnClickaddUrl(urltext);
                    $("#type"+type).attr("checked",true);
                    $("#chanName").val(result.chanName);
                    $("#showName").val(result.showName);
                    $("#desc").val(result.desc);
                    $("#depository").val(result.depository);
                    component.querryTMPList(result.overviewTmp,2,"overviewTmp");
                    component.querryTMPList(result.overviewTmp2,3,"overviewTmp2");
                    component.querryTMPList(result.detailTmp,2,"detailTmp");
                    component.querryTMPList(result.detailTmp2,3,"detailTmp2");
                    //
                    /**
                     *  var overviewTmp=$("#overviewTmp").val();
        var overviewTmp2=$("#overviewTmp2").val();
		var detailTmp=$("#detailTmp").val();
		var detailTmp2=$("#detailTmp2").val();
		
                     */
                   
                    	try
                    	{
                    		 if(result.logo){
                    		 $("#deImg").attr('src',GLOBAL_INFO.CONTEXTPATH +"/"+result.logo) ;
                    		 $("#fileName").val(result.logo) ;
                             $("#deImg").css('display','block');}
                    	}
                    	catch(err)
                    	{
                    	   //在此处理错误
                    	}
                   
                }
                else {
                  //  alert(ret.retMsg);
                }
            }
        });


   },

    saveForm: function () {//updateByPrimaryKey

       if (!$('#editForm').valid()) {
           return;
       }
       var chanName = $("#chanName").val();
       var showName = $("#showName").val();
       var parentId = $("#parentId").val();
       var channelNum = $("#channelNum").val();
       var type = $("input[name='type']:checked").val();
       var sortNum = "";
       $("select[name=sortNum] option").each(
       		  function() {
       			  sortNum=sortNum+$(this).val()+".";
       		  }
       		 );
       if(sortNum.length>0){
       	sortNum=sortNum.substring(0,sortNum.length-1);}
       var desc = $("#desc").val();
       var depository = $("#depository").val();
       var logo = $("#logo").val();
       var fileName =$("#fileName").val();
       $.singleReq({
           data: {
               "reqUrl": "content",
               "reqMethod": "updateByPrimaryKey",
               "chanName": chanName,
               "showName": showName,
               "parentId": parentId,
               "channelNum": channelNum,
               "type": type,
               "sortNum": sortNum,
               "desc": desc,
               "state":"0",
               "chanId": $("#pkid").val(),
               "depository": depository,
               "logo": logo,
               "fileName": fileName
           },
           success: function (ret) {
               if (ret.retCode == 0) {

	            	var result = ret.retObj;
	            
	            	var times=0;
	            	if(logo){
	            		if(result.logo){
	            		$("#fileName").val(result.logo);//--此时获取重命名后的文件名  
	            		}
	            		$("#chanId").val(result.chanId);
	            		$("#editForm").submit();
	            		times=3000;	
	            	}
	            	var t=setTimeout('submitBack=0;UOMPComp.showSuccessDialog("修改成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times);
	            }
               else {
                   UOMPComp.showFailedDialog("修改失败！", "");
               }
           }
       });
   }
};
