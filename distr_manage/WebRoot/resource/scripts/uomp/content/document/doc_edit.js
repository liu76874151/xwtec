var attachLength=0;
var attachCount=0;
$(document).ready(function () {
      // component.initValidate();
        component.initForm();
        component.querySubDoc();
    }
);

var component =
{
		initAttach:function(result){
	//value='"+(result==undefined||result[i].contentTitle==undefined?'':result[i].contentTitle)+"'
	
	var checked1="",checked2="",dispaly1="display:none",dispaly2="display:none";
	if(result!=undefined){
	if(result.type!=undefined){
		if(result.type==1){
			checked1="checked";
		}else if(result.type==2){
			checked2="checked";
		}}
		if(result.type==1&&(result.attachDir!=undefined)){
			dispaly1="display:block";
		}else{
			dispaly1="display:none";
		}
		if(result.interfaceDir!=undefined){
			dispaly2="display:block";
		}else{
			dispaly2="display:none";
		}
		
	}
	var  content   ='   <tr class="attach">'+
	 			' <th align="right" id="addAttach">附件名称：</th>'+
	 			'<td class="form_table_content2" >'+
	 				'<input type="text" name="attachName'+attachCount+'"  id="attachName'+attachCount+'" class="form_input"  value="'+((result==undefined||result.attachName==undefined)?"":result.attachName)+' "/>'+
	 				'<input type="hidden"  name="attachId_'+attachCount+'"  id="attachId_'+attachCount+'" value="'+((result==undefined||result.attachId==undefined)?"":result.attachId)+' " /> '+
	 				'</td>'+
	 			'</tr>'+
				'<tr class="attach">'+
	 	  			'<th align="right">附件类型：</th>'+
					'<td class="form_table_content2">'+
					'<input type="radio"   name="attachType'+attachCount+'" id="attachType1_'+attachCount+'" value="1" '+checked1+'/>&nbsp;附件'+
					'<input type="radio"   name="attachType'+attachCount+'" id="attachType2_'+attachCount+'" value="2" '+checked2+'/>&nbsp;URL'+
					'</td>'+
	 			'</tr>'+
	 			'<tr class="attach" id="fileAdd'+attachCount+'">'+
  	   				'<th align="right" >附件：</th>'+
	   				'<td class="form_table_content2" > &nbsp;'+
						'<input type="file" name="files" id="attachDir'+attachCount+'" class="qinggoudan_input021" >'+
						'<input type="text" readonly="readonly" name="attachDir1_'+attachCount+'"  id="attachDir1_'+attachCount+'" class="form_input"  style="'+dispaly1+'" '
						+'value="'+((result==undefined||result.attachDir==undefined)?"":result.attachDir)+' "/>'+		
			       '<span class="errorMsg"></span></td>'+
			     '  </tr>'+
			    '<tr class="attach" id="urlAdd'+attachCount+'">'+
			   ' <th align="right" >URL：</th>'+
				'<td class="form_table_content2" ><input type="text" name="attachUrl'+attachCount+'"  id="attachUrl'+attachCount+'" class="form_input"  /></td>'+
			   ' </tr> '+
			   ' <tr class="attach">'+
			   ' <th align="right" >附件界面：</th>'+
				'<td class="form_table_content2" >&nbsp;'+
				'<input type="file" name="files" id="interfaceDir'+attachCount+'" class="qinggoudan_input021" ><br>'+
				'<input type="text" readonly="readonly" name="interfaceDir1_'+attachCount+'"  id="interfaceDir1_'+attachCount+'" class="form_input"  style="'+dispaly2+'" '
				+'value="'+((result==undefined||result.interfaceDir==undefined)?"":result.interfaceDir)+' "/>'+
				'</td>'+
			   ' </tr> '+
			   ' <tr class="attach">'+
				'<td colspan="2" height="1px">&nbsp;<hr>'+
				'</td>'+
			   ' </tr> ';
	//alert(content);
	$("#editTab").append(content);
	attachCount=attachCount+1;	
		},
    initTable:function(type,result){
	//if(type==1){
		$('#content').parent().parent().show();	
	 	$("#content").val(result.content);
	 	
//		$('#url').parent().parent().hide();
//		$("input[name=level]").parent().parent().hide();
//		$('#logo').parent().parent().hide();
//		$('#feeStandard').parent().parent().hide();
//		$('#busiName').parent().parent().hide();
//		$('#busiIntro').parent().parent().hide();
//		$('#busiFee').parent().parent().hide();
//		$('#bandName').parent().parent().hide();
//		$('#busiOldfee').parent().parent().hide();
//		$('#areaName').parent().parent().hide();
//		$('#busiUrl').parent().parent().hide();
//		

	//}else if(type==2){
		//$('#content').parent().parent().hide();	
		//$('#url').parent().parent().hide();
		
		$("input[name=level]").parent().parent().show();
		  $("#level"+result.level).attr("checked",true);
		$('#logo').parent().parent().show();
        if(result.logo!=null&&result.logo!=undefined&&result.logo!=""){
        	$("#hiddenLogo1").val(result.logo);
      	   $("#logoImg").attr('src',GLOBAL_INFO.CONTEXTPATH+"/"+result.logo) ;
      	   $("#logoImg").css('display','block');
         }
        
//		$('#feeStandard').parent().parent().hide();
//		$('#busiName').parent().parent().hide();
//		$('#busiIntro').parent().parent().hide();
//		$('#busiFee').parent().parent().hide();
//		$('#bandName').parent().parent().hide();
//		$('#busiOldfee').parent().parent().hide();
//		$('#areaName').parent().parent().hide();
//		$('#busiUrl').parent().parent().hide();
	//}else if(type==4){
//		$('#url').parent().parent().hide();
//		$('#content').parent().parent().hide();
//		$("input[name=level]").parent().parent().hide();
//		$('#logo').parent().parent().hide();
		$('#feeStandard').parent().parent().show();
		$('#busiName').parent().parent().show();
		$('#busiIntro').parent().parent().show();
		$('#busiFee').parent().parent().show();
		$('#bandName').parent().parent().show();
		$('#busiOldfee').parent().parent().show();
		$('#areaName').parent().parent().show();
		$('#busiUrl').parent().parent().show();
		
		$("#feeStandard").val(result.feeStandard);
       	$("#busiName").val(result.busiName);
       	$("#busiIntro").val(result.busiIntro);
       	$("#busiFee").val(result.busiFee);
       	$("#busiOldfee").val(result.busiOldfee);
       	$("#bandName").val(result.bandName);
       	$("#areaName").val(result.areaName);
       	$("#busiUrl").val(result.busiUrl);
	////}else if(type==3){
		//$('#content').parent().parent().hide();	
		$('#url').parent().parent().show();
//		$("input[name=level]").parent().parent().hide();
//		$('#logo').parent().parent().hide();
//		$('#feeStandard').parent().parent().hide();
//		$('#busiName').parent().parent().hide();
//		$('#busiIntro').parent().parent().hide();
//		$('#busiFee').parent().parent().hide();
//		$('#bandName').parent().parent().hide();
//		$('#busiOldfee').parent().parent().hide();
//		$('#areaName').parent().parent().hide();
//		$('#busiUrl').parent().parent().hide();
		
	   	$("#url").val(result.url);
	//}
},

    initForm: function () {
	
	CKEDITOR.replace( 'content',
			{
			toolbar :
			[
			//加粗 斜体， 下划线 穿过线 下标字 上标字
			['Bold','Italic','Underline','Strike','Subscript','Superscript'],
			// 数字列表 实体列表 减小缩进 增大缩进
			['NumberedList','BulletedList','-','Outdent','Indent'],
			//左对齐 居中对齐 右对齐 两端对齐
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			//超链接 取消超链接 锚点
			['Link','Unlink','Anchor'],
			//图片 flash 表格 水平线 表情 特殊字符 分页符
			//['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
			['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
			'/',
			// 样式 格式 字体 字体大小
			['Styles','Format','Font','FontSize'],
			//文本颜色 背景颜色
			['TextColor','BGColor'],
			//全屏 显示区块
			['Maximize', 'ShowBlocks','-']
			]
			}
			);
	
	
	
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
                "reqUrl": "contentDoc",
                "reqMethod": "queryOneContentDoc",
                "docId": $("#docId").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj;
                	attachLength=result.contentAttachmentBeanList.length
                	var typeNames=new Array("普通","下载","链接","专区");
                	var type=result.type;
                   	var typeName=typeNames[(type-1)];
                   	$("#type").val(typeName);
                   	$("#title").val(result.title);
                   	$("#subhead").val(result.subhead);
                   	$("#showtitle").val(result.showtitle);
                   	$("#summary").val(result.summary);
                   	$("#author").val(result.author);
                   	component.initTable(type,result);
                   	$("#versionDesc").val(result.versionDesc);
                   	$("#busiUrl").val(result.useDesc);
                   	
                   	for ( var j = 0; j < attachLength; j++) {
						
                   		component.initAttach(result.contentAttachmentBeanList[j]);
					}
//                   	
//                   	$("#url").val(result.url);
//                   	$("#content").val(result.content);
//                   	$("#feeStandard").val(result.feeStandard);
//                   	$("#busiName").val(result.busiName);
//                   	$("#busiIntro").val(result.busiIntro);
//                   	$("#busiFee").val(result.busiFee);
//                   	$("#busiOldfee").val(result.busiOldfee);
//                   	$("#bandName").val(result.bandName);
//                   	$("#areaName").val(result.areaName);
//                   	$("#busiUrl").val(result.busiUrl);
//
//                    if(result.logo!=null&&result.logo!=undefined&&result.logo!=""){
//                 	   $("#logoImg").attr('src',result.yxImgDir) ;
//                 	   $("#logomg").css('display','block');
//                    }
//                    $("#level"+result.level).attr("checked",true);
         
                }
                else {
                   // alert(ret.retMsg);
                }
            }
        });

//        $.singleReq({
//            data: {
//                "reqUrl": "?",
//                "reqMethod": "??"
//            },
//            success: function (ret) {
//                if (ret) {
//                    if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
//                        if (ret.retObj) {
//                            var result = ret.retObj;
//                            //todo 加载界面上的一些字典项
//                        }
//                    } else {
//                        if (ret.resMsg) {
//                            UOMPComp.showFailedDialog(ret.resMsg, "");
//                        } else {
//                            UOMPComp.showFailedDialog("系统异常", "");
//                        }
//                    }
//                }
//            }
//        });
    },
    addOption: function (input){
    	var docId=$("#docId").val();
    	var obj=$("#sortNum option[value='"+docId+"']");
    	if(input.value!=""){ 
    		if(obj[0]){
    				$("#sortNum option[value='"+docId+"']").text(input.value);
    			}else{
    				$("#sortNum").prepend("<option value='"+docId+"'>"+input.value+"</option>");////prepend	append
    			}
    		}else{
    				if(obj[0]){$("#sortNum option[value='"+docId+"']").remove();	}
     	}
    },
    querySubDoc:function(){

    	var chanId=$("#chanId").val(); 
		var docId=$("#docId").val();
    	$.singleReq({
    	    data: {
    	    //"state":"1",
    	    "reqUrl": "contentDoc",
    	    "reqMethod": "queryContentDocs",
    	    "chanId":chanId,
    	    "start": "0",
    	    "end": "1000"

    	    },  
    	    success: function (ret) {
    	        if (ret.retCode == 0) {
    	      	  var result = ret.retObj.records; 
    	            $.each(result, function(i, item) {
    	            	
    	            	if(item.docId==docId){
    	    	           	$("#sortNum").append("<option value="+item.docId+" selected='selected'  >"+item.title+"("+item.docId+")"+"</option>");

    	            	}else{
    	    	           	$("#sortNum").append("<option value="+item.docId+">"+item.title+"("+item.docId+")"+"</option>");

    	            	}
    	            
    	            	});
    	        }
    	    }
    	});
    		},
    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }
        var level= $("input[name='level']:checked").val();
        var title = $("#title").val();
        var subhead = $("#subhead").val();
        var showtitle = $("#showtitle").val();
        var summary = $("#summary").val();
        var author = $("#author").val();
        var url = $("#url").val();
       var content= CKEDITOR.instances.content.getData();
        var logo = $("#logo").val();
       // alert();
        //if(logo){
        	//logo=$("#hiddenLogo1").val();
       // }
        var feeStandard = $("#feeStandard").val();
        var busiName = $("#busiName").val();
        var busiIntro = $("#busiIntro").val();
        var busiFee = $("#busiFee").val();
        var busiOldfee = $("#busiOldfee").val();
        var bandName = $("#bandName").val();
        var areaName = $("#areaName").val();
        var busiUrl = $("#busiUrl").val();
        var versionDesc = $("#versionDesc").val();
        var useDesc = $("#useDesc").val();
        var attach = component.getAttach();
        var sortNum = "";
        $("select[name=sortNum] option").each(
      		  function() {
      			  sortNum=sortNum+$(this).val()+".";
      		  }
      		 );
      if(sortNum.length>0){
      	sortNum=sortNum.substring(0,sortNum.length-1);}
        $.singleReq({
            data: {
                "reqUrl": "contentDoc",
                "reqMethod": "updateBydocId",
                "docId": $("#docId").val(),
                "level": level,
                "title": title,
                "subhead": subhead,
                "level": level,
                "showtitle": showtitle,
                "author": author,
                "summary": summary,
                "url": url,
                "content": content,
                "logo": logo,
                "feeStandard": feeStandard,
                "busiName": busiName,
                "busiIntro": busiIntro,
                "busiFee": busiFee,
                "busiOldfee": busiOldfee,
                "bandName": bandName,
                "areaName": areaName,
                "busiUrl": busiUrl,
                "bandName": bandName,
                "versionDesc": versionDesc,
                "useDesc": useDesc,
                "sortNum": sortNum,
                "attach":attach
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	$("#hiddenLogo").val(ret.retObj.logo);
                	$("#hiddenDocId").val(ret.retObj.docId);
                	$("#hiddenIsUpdate").val(ret.retObj.isUpdate);
                	$("#hiddenAttach").val($.toJSON(ret.retObj.attach));
                	$("#editForm").submit();
                		var times=3000;
                	
                setTimeout('UOMPComp.showSuccessDialog("修改成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times)
                	
                  
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
                }
            }
        });
    },
    getAttach:function(){
    	var attach = new Array();
    	for ( var j = 0; j < attachCount; j++) {
    	var attachName=$("#attachName"+j).val();
    	var attachId=$("#attachId_"+j).val();
    	var type=$("input[name='attachType"+j+"']:checked").val();
    	var attachUrl=$("#attachUrl"+j).val();
    	
    	var attachDir=$("#attachDir"+j).val();
    	var interfaceDir=$("#interfaceDir"+j).val();
   
//    	if(!attachDir){
//    	//attachDir=$("#attachDir1_"+j).val()==""?undefined:$("#attachDir1_"+j).val();
//    	}
//    	if(!interfaceDir){
//        //	interfaceDir=$("#interfaceDir1_"+j).val()==""?undefined:$("#interfaceDir1_"+j).val();
//        	}
//    	//var dir="upload/zq/"
    	 var element = {};
    	 element.attachName = attachName;
    	 element.attachId = attachId;
    	 element.type = type;
    	 element.attachDir = attachDir;//(attachDir==""||attachDir==undefined)?attachDir1:attachDir; 
    	 element.attachUrl = attachUrl;
    	 element.interfaceDir=interfaceDir;
    	 attach.push(element);
    	}
    	 return  $.toJSON(attach);
    	
    }
};




