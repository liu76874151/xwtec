$(document).ready(function () {
       component.initCity();
       component.initValidate();
      
    }
);

var component =
{

	    // 初始化校验规则
	    initValidate: function () {
	        ValidateUtil.validate({
	            targetForm: "editForm",
	            rules: {
	        	templateName: {required: true, minlength: 1, stringMaxLength:"100"}
	            },
	            messages: {
	            	templateName: {required: "请输入名称", minlength: "长度必须大于等于{0}"}
	            }
	        });
	    },
    initCity: function () {
	$.singleReq({
        data: {
		    	"reqUrl": "area",
		        "reqMethod": "queryCityList"
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	var result = ret.retObj.records; 
            	var checked="";
            	var disabled="";
            	var userCityCode=$("#userCityCode").val();
                $.each(result, function(i, item) {
                	if(item.areaBossCode==userCityCode)
                	{
                		checked="checked";
                		disabled="";
                		}else{
                			checked="";
                			if(userCityCode!=0)
                			disabled="disabled";
                		}
                		 $("#city").append('<input '+disabled+'  type="radio" name="city" value="'+
                       			 item.areaBossCode+'">'+item.areaName+'&nbsp;');
                       	if(i%7==0){
                       		$("#city").append('<br>');
                       	}	
			
                });
                component.initForm(); 
			}
            
           
        }
    });
   
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
    	
        $.singleReq({
            data: {
                "reqUrl": "marketTemplate",
                "reqMethod": "queryOneMarketTemplate",
                "templateId": $("#pkid").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#templateName").val(result.templateName);
                    $("#content").val(result.content);
                    $("input[name='city'][value="+result.city+"]").attr("checked",true);
                    $("input[name='state'][value="+result.state+"]").attr("checked",true);
                    $("input[name='channalData'][value="+result.channalData+"]").attr("checked",true);
                   
                   
                }
                else {
                    alert(ret.retMsg);
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

    saveForm: function () {
        if (!$('#editForm').valid()) {
            return;
        }
        var templateName = $("#templateName").val();
        var city = $("input[name='city']:checked").val();
        var state = $("input[name='state']:checked").val();
     //   var channalData= $("input[name='channalData']:checked").val();
	    var content= CKEDITOR.instances.content.getData();
	    //var category=1;///营销模板类别：1-展示模板，2-协议模板
	    var templateId=$("#pkid").val();
	    

        $.singleReq({
            data: {
        	"reqUrl": "marketTemplate",
            "reqMethod": "updateMarketTemplate",
            "templateName": templateName,
            "city": city,
            "state": state,
          //  "channalData": channalData,
            "content": content,
           "templateId": templateId
           // "category": category
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    UOMPComp.showSuccessDialog("修改成功！", "");
                    top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
                }
            }
        });
    }
};




