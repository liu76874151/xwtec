var attachCount=0;
$(document).ready(function () {
   // component.initValidate();
    component.initForm();
    component.onClickType();
    component.querySubDoc();
});

var component = {

//    //初始化校验规则
//    initValidate: function () {
//        ValidateUtil.validate({
//            targetForm: "addForm",
//            rules: {
//                stuName: {required: true, minlength: 1, maxlength: 20},
//                stuAge: {required: true, minlength: 1, maxlength: 3},
//                stuSex: {required: true},
//                stuNative: {required: true}
//            },
//            messages: {
//                stuName: {required: "请输入姓名", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
//                stuAge: {required: "请输入年龄", minlength: "长度必须大于等于{0}", maxlength: "长度不能超过{0}"},
//                stuSex: {required: "请选择性别"},
//                stuNative: {required: "请选择籍贯"}
//            }
//        });
//    },
    onClickType:function(){
    	 $("input[name='type']").click(function() {
    		var type= $("input[name='type']:checked").val();
    		 component.onchangeType(type);
    				}); 
    },
    onchangeType:function(type){
    	if(type==1){
    		$('#content').parent().parent().show();	
    		$('#url').parent().parent().show();
    		$("input[name=level]").parent().parent().show();
    		$('#logo').parent().parent().show();
    		//$('#feeStandard','#busiName','#busiIntro','#busiFee','#bandName','#busiOldfee','#areaName','#busiUrl').parent().parent().hide();
    		$('#feeStandard').parent().parent().show();
    		$('#busiName').parent().parent().show();
    		$('#busiIntro').parent().parent().show();
    		$('#busiFee').parent().parent().show();
    		$('#bandName').parent().parent().show();
    		$('#busiOldfee').parent().parent().show();
    		$('#areaName').parent().parent().show();
    		$('#busiUrl').parent().parent().show();
    	}else if(type==2){
    		$('#content').parent().parent().show();	
    		$('#url').parent().parent().show();
    		$("input[name=level]").parent().parent().show();
    		$('#logo').parent().parent().show();
    		$('#feeStandard').parent().parent().show();
    		$('#busiName').parent().parent().show();
    		$('#busiIntro').parent().parent().show();
    		$('#busiFee').parent().parent().show();
    		$('#bandName').parent().parent().show();
    		$('#busiOldfee').parent().parent().show();
    		$('#areaName').parent().parent().show();
    		$('#busiUrl').parent().parent().show();
    	}else if(type==4){
    		$('#url').parent().parent().show();
    		$('#content').parent().parent().show();
    		$("input[name=level]").parent().parent().show();
    		$('#logo').parent().parent().show();
    		$('#feeStandard').parent().parent().show();
    		$('#busiName').parent().parent().show();
    		$('#busiIntro').parent().parent().show();
    		$('#busiFee').parent().parent().show();
    		$('#bandName').parent().parent().show();
    		$('#busiOldfee').parent().parent().show();
    		$('#areaName').parent().parent().show();
    		$('#busiUrl').parent().parent().show();
    	}else if(type==3){
    		$('#content').parent().parent().show();	
    		$('#url').parent().parent().show();
    		$("input[name=level]").parent().parent().show();
    		$('#logo').parent().parent().show();
    		$('#feeStandard').parent().parent().show();
    		$('#busiName').parent().parent().show();
    		$('#busiIntro').parent().parent().show();
    		$('#busiFee').parent().parent().show();
    		$('#bandName').parent().parent().show();
    		$('#busiOldfee').parent().parent().show();
    		$('#areaName').parent().parent().show();
    		$('#busiUrl').parent().parent().show();
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
	querySubDoc:function(){
		var chanId=$("#chanId").val(); 	
		$.singleReq({
		    data: {
		    "state":"1",
		    "reqUrl": "contentDoc",
		    "reqMethod": "queryContentDocs",
		    "chanId":chanId,
		    "start": "0",
		    "end": "10000"

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
		           	$("#sortNum").append("<option value="+item.docId+">"+item.title+"("+item.docId+")"+"</option>");
		            });
		        }
		    }
		});
			},
	initAttach:function(){
	
		var  content   ='   <tr class="attach">'+
		 			' <th align="right" id="addAttach">附件名称：</th>'+
		 			'<td class="form_table_content2" >'+
		 				'<input type="text" name="attachName'+attachCount+'"  id="attachName'+attachCount+'" class="form_input"  />'+
		 			'</td>'+
		 			'</tr>'+
					'<tr class="attach">'+
		 	  			'<th align="right">附件类型：</th>'+
						'<td class="form_table_content2">'+
						'<input type="radio"   name="attachType'+attachCount+'" id="attachType1'+attachCount+'" value="1" checked="checked" />&nbsp;附件'+
						'<input type="radio"   name="attachType'+attachCount+'" id="attachType2'+attachCount+'" value="2" />&nbsp;URL'+
						'</td>'+
		 			'</tr>'+
		 			'<tr class="attach" id="fileAdd'+attachCount+'">'+
	  	   				'<th align="right" >附件：</th>'+
		   				'<td class="form_table_content2" > &nbsp;'+
							'<input type="file" name="files" id="attachDir'+attachCount+'" class="qinggoudan_input021" >'+
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
					'</td>'+
				   ' </tr> '+
				   ' <tr class="attach">'+
					'<td colspan="2" height="1px">&nbsp;<hr>'+
					'</td>'+
				   ' </tr> ';
		$("#addTab").append(content);
			

				 
				 attachCount=attachCount+1;	
			},
//			attachTypeOnclick:function(flag,attachCount){
//				if(flag==1){
//					$("#fileAdd"+attachCount).show();
//	    			$("#urlAdd"+attachCount).hide();
//				}else{
//					("#fileAdd"+attachCount).hide();
//	    			$("#urlAdd"+attachCount).show();	
//				}
//			},
    //初始化表单
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
	
	
	// component.initAttach();	
   	 $("#btnMoveUp,#btnMoveDown").click(function() {   
		  var $opt = $("#sortNum option:selected:first");   
		  if (!$opt.length) return;   
		  if (this.id == "btnMoveUp") {
		  		$opt.prev().before($opt);   
		  		}else {
		  		$opt.next().after($opt); 
		  		      } 
			}); 
    	
    	var type= $("#type").val();
    	//var typeNames=new Array("普通","下载","链接","专区");
    	//var typeName=typeNames[(type-1)];
    	//$("#breadcrumb").html(typeName+"内容--新增");
    	$("#type"+type).attr("checked",true);
    	this.onchangeType(type);
    },

    saveForm: function () {
        if (!$('#addForm').valid()) {
            return;
        }
        var state=1;//状态位(0：新稿 1：发布 2：不可用)
        var type= $("input[name='type']:checked").val();
        var level= $("input[name='level']:checked").val();
        var chanId = $("#chanId").val();
        var title = $("#title").val();
        var subhead = $("#subhead").val();
        var showtitle = $("#showtitle").val();
        var summary = $("#summary").val();
        var author = $("#author").val();
        var url = $("#url").val();
        var size = $("#size").val();
        var  content= CKEDITOR.instances.content.getData();
        var logo = $("#logo").val();
        if(logo!=""){
        logo=logo.substr(logo.lastIndexOf("\\")+1);}
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
        var isAddAttach= $(".attach").is(":visible");
        var sortNum="";
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
                "reqMethod": "saveContentDoc",
                "chanId": chanId,
                "type": type,
                "state": state,
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
                "isAddAttach":isAddAttach,
                "sortNum":sortNum,
                "size":size,
                "attach":attach
            },
            success: function (ret) {
            	
                if (ret.retCode == 0) {
                	$("#hiddenLogo").val(ret.retObj.logo);
                	$("#hiddenDocId").val(ret.retObj.docId);
                	//alert($.toJSON(ret.retObj.contentAttachmentBeanList));
                	$("#hiddenAttach").val($.toJSON(ret.retObj.contentAttachmentBeanList));
                	$("#addForm").submit();
                		var times=3000;
                	
                	setTimeout('UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times)
                	  // UOMPComp.showSuccessDialog("添加成功22！", "");
                       //top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                
                }
                else {
                    UOMPComp.showFailedDialog("添加失败！", "");
                }
            }
        });
    },
    getAttach:function(){
    	var attach = new Array();
    	for ( var j = 0; j < attachCount; j++) {
    	var attachName=$("#attachName"+j).val();
    	var type=$("input[name='attachType"+j+"']:checked").val();
    	var attachDir=$("#attachDir"+j).val();
    	var attachUrl=$("#attachUrl"+j).val();
    	var interfaceDir=$("#interfaceDir"+j).val();
    	if(attachDir!="")
    	attachDir=attachDir.substr(attachDir.lastIndexOf("\\")+1);
    	if(interfaceDir!="")
    	interfaceDir=interfaceDir.substr(interfaceDir.lastIndexOf("\\")+1);
    	
    	 var element = {};
    	 element.attachName = attachName;
    	 element.type = type;
    	 element.attachDir = attachDir;
    	 element.attachUrl = attachUrl;
    	 element.interfaceDir = interfaceDir;
    	 attach.push(element);
    	}
    	 return  $.toJSON(attach);
    	
    }
}
