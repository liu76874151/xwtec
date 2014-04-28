var tableID=0;//--广告添加标志ID
$(document).ready(function () {
    //component.initValidate();
    component.addAdvfun();
    component.initAdvPosition();
    component.btnMoveOnClick();
  
});

var component = {
//--加载广告添加位
addAdvfun:function(){
	var tableContent ='<tr class="addAdvName_'+tableID+'">'+
	'<th align="right" >广告名称：</th>'+
	'<td class="form_table_content2">'+	
	'<input type="text" name="advName" id="advName_'+tableID+'"   class="form_input"  onchange="component.addOption('+tableID+')" />'+	
	'&nbsp;&nbsp;<input type="button" id="showAdvButton_'+tableID+'" onclick="component.isShowAdv('+tableID+')" value="收起">'+
	'&nbsp;&nbsp;<input type="button" id="delAdvButton_'+tableID+'" onclick="component.delShowAdv('+tableID+')" value="删除">'+
	'</td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right" >广告编码：</th>'+
	'<td class="form_table_content2" ><input type="text" class="form_input"  name="advNum"  id="advNum_'+tableID+'" onchange="component.addOption('+tableID+')"/></td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right" >广告简介：</th>'+
	'<td class="form_table_content2" ><input type="text" class="form_input"  name="advDesc"  id="advDesc_'+tableID+'" /></td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right">广告有效期：</th>'+
	'<td class="form_table_content2">'+
	'&nbsp;开始时间:'+
	'<input type="text" name="startTime" id="startTime_'+tableID+'" class=""  >&nbsp;----&nbsp;结束时间:'+
	'<input type="text" name="endTime" id="endTime_'+tableID+'" class=""  >'+
	'</td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right">打开方式：</th>'+
	'<td class="form_table_content2">'+
	'<input type="radio"   name="openUriType_'+tableID+'" id="openUriType1_'+tableID+'" value="1" />&nbsp;新页面'+
	'<input type="radio"   name="openUriType_'+tableID+'" id="openUriType0_'+tableID+'" value="0" />&nbsp;当前页'+
	'</td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right">图片类型：</th>'+
	'<td class="form_table_content2">'+
	'<input type="radio"   name="advImgType_'+tableID+'" id="advImgType0_'+tableID+'" value="0" />&nbsp;图片'+
	'<input type="radio"   name="advImgType_'+tableID+'" id="advImgType1_'+tableID+'" value="1" />&nbsp;flash'+
	'</td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right">运行状态：</th>'+
	'<td class="form_table_content2">'+
	'<input type="radio"   name="useState_'+tableID+'" id="useState1_'+tableID+'" value="1" />&nbsp;启用'+
	'<input type="radio"   name="useState_'+tableID+'" id="useState0_'+tableID+'" value="0" />&nbsp;停用'+
	'</td>'+
	'</tr>'+

	'<tr class="addAdv_'+tableID+'">'+
		'<th align="right" >广告菜单图标：</th>'+
		
	'<td class="form_table_content2" > &nbsp;'+
	'<input type="file" name="files" id="advMenuIcon_'+tableID+'" class="qinggoudan_input021" >'+
	'<span class="errorMsg"></span></td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
		'<th align="right" >广告图片：</th>'+
		
	'<td class="form_table_content2" > &nbsp;'+
	'<input type="file" name="files" id="advImg_'+tableID+'" class="qinggoudan_input021" >'+
	'<span class="errorMsg"></span></td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
		'<th align="right" >广告链接：</th>'+
	'<td class="form_table_content2" > &nbsp;'+
	'<input type="file" name="files" id="advUri_'+tableID+'"class="qinggoudan_input021" >'+
	'<span class="errorMsg"></span>'+
	'</td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
		'<th align="right" >广告小图片：</th>'+
		
	'<td class="form_table_content2" > &nbsp;'+
	'<input type="file" name="files" id="advImgS_'+tableID+'" class="qinggoudan_input021" >'+
	'<span class="errorMsg"></span></td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right">备注：</th>'+
	'<td class="form_table_content2">'+
	'<textarea rows="3" cols="50" class=""  name="bz"  id="bz_'+tableID+'"></textarea>'+
	'</td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right">城市：</th>'+
	'<td class="form_table_content2">'+

	'<div id="advAreaNumDiv_'+tableID+'">'+

	'</div>'+
	'</td>'+
	'</tr>'+
	'<tr class="addAdv_'+tableID+'">'+
	'<th align="right">品牌：</th>'+
	'<td class="form_table_content2">'+
	'<div id="advBrandNumDiv_'+tableID+'">'+
	'</div>'+
	'</td>'+
	'</tr>';
	//var positionType = $("#positionNum option:selected").attr("positionType");
	$("#addTab").append(tableContent);
	component.initForm(tableID);
//	if(tableID==0||positionType==1){
//		//alert(tableContent);	
//		}
	tableID++;
},
    //-- 初始化校验规则
    initValidate: function () {

    },
    //--展开/收起广告添加位
    isShowAdv:function(tableID){
    	 var isShow= $(".addAdv_"+tableID).is(":visible");
    	 $(".addAdv_"+tableID).fadeToggle(); 
    	 var text= isShow?"展开":"收起";
    	 $("#showAdvButton_"+tableID).val(text);
    	 
    },
    delShowAdv:function(tableID){
    	//alert(tableID+"====tableID==delShowAdv==");
    	},
    /*
     *广告编码和广告名称不为空时加入到顺序select中 
     */
    addOption:function(tableID){
    	var advNum = $("#advNum_"+tableID).val();
    	var advName = $("#advName_"+tableID).val();
        var obj=$("#showXh option[isExsit='"+tableID+"']");
        	if(advNum&&advName){ 
        		if(obj[0]){
        			$("#showXh  option[isExsit='"+tableID+"']").text(advName+"("+advNum+")");
        			$("#showXh  option[isExsit='"+tableID+"']").val(advNum);
        			 
        			}else{
        				$("#showXh").prepend("<option value='"+advNum+"' isExsit='"+tableID+"'>"+advName+"("+advNum+")</option>");
        				
        			}
        		}else{
        				if(obj[0]){
        					$("#showXh option[isExsit='"+tableID+"']").remove();	
        					}
         	}
    	
    },
    /*
     * 根据广告位加载广告顺序的
     */
    addAdvOption:function(input){
    	var positionNum = $("#positionNum").val();
    	var positionType = $("#positionNum option:selected").attr("positionType");
    	if(positionType==1){
    		$(".showImgs").show();
    	}else{
    		$(".showImgs").hide();
    		for ( var j = 1; j <  tableID; j++) {//--多图到单图,文字只留一个广告添加位
    		 $("#addTab tr").filter(" .addAdv_"+j).remove();
    		 $("#addTab tr").filter(" .addAdvName_"+j).remove();
    		 $("#showXh option[isExsit='"+j+"']").remove();//--删除展示位置的选项
    		
    		 }
    		 tableID=1;//--广告添加标志值为1
    	}
    	$.singleReq({
            data: {
		    		"reqUrl" : "advInfo",
					"reqMethod" : "queryAdvInfoList",
					"positionNum" : positionNum,
    		        "start": 0,
    		        "end": 99999999999
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	$("#showXh").empty();//--清空
                	for ( var j = 0; j <  tableID; j++) {//--添加已有的广告位到位置顺序select中
                		component.addOption(j);
                	}
                	var result = ret.retObj.records; 
                    $.each(result, function(i, item) {
                   	 $("#showXh").append("<option value="+item.advNum+" >"+item.advName+"("+item.advNum+")</option>");
                   	  
                    });
    					}
            }
        });


    	
    },
    
  //--初始化广告位
    initAdvPosition:function(){
    	var channelNum=$("#channelNum").val();
        $.singleReq({
            data: {
    		    	"reqUrl": "advPosition",
    		        "reqMethod": "queryAdvPositionList",
    		        "channelNum": channelNum,
    		        "start": 0,
    		        "end": 99999999999
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	$("#showXh").empty();//--清空
                	$("#positionNum").empty().prepend("<option value=''>--请选择--</option>");	
                	var result = ret.retObj.records; 
                	var positionType=["","多图","单图","文字"];
                	var positionTypename="";
                    $.each(result, function(i, item) {
                    	positionTypename=(item.positionType>0&&item.positionType<4)?positionType[item.positionType]:"";
                   	 $("#positionNum").append("<option value="+item.positionNum+" positionType="+item.positionType+">"+item.positionName+"("+positionTypename+")</option>");
                   	  
                    });
    					}
               
            }
        });
    	
   



    },
  //--顺序select option上下移动
    btnMoveOnClick:function(){
      	 $("#btnMoveUp,#btnMoveDown").click(function() {   
     		  var $opt = $("#showXh option:selected:first");   
     		  if (!$opt.length) return;   
     		  if (this.id == "btnMoveUp") {
     		  		$opt.prev().before($opt);   
     		  		}else {
     		  		$opt.next().after($opt); 
     		  		      } 
     			}); 
    },
    //-- 初始化表单
    initForm: function (j) {
    	//--日期输入框
    	var myCalendar=new dhtmlXCalendarObject(["startTime_"+j,"endTime_"+j]);
    		//--初始化城市
    	$.singleReq({
            data: {
    		    	"reqUrl": "area",
    		        "reqMethod": "queryCityList"
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	var result = ret.retObj.records; 
                    $.each(result, function(i, item) {
                    		 $("#advAreaNumDiv_"+j).append('<input type="checkbox" name="advAreaNums_'+j+'" value="'+
                           			 item.areaBossCode+'">'+item.areaName+'&nbsp;');
                           	if(i%7==0){
                           		$("#advAreaNumDiv_"+j).append('<br>');
                           	}	
				
                    });
    			}
            }
        });
    	
    	
    	//--初始化品牌
    	$.singleReq( {
			data : {
				"reqUrl" : "brand",
				"reqMethod" : "queryBrandList",
				"start" : 0,
				"end" : 99999999
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj.records;
					$.each(result, function(i, item) {
						$("#advBrandNumDiv_" + j).append(
								'<input type="checkbox" name="advBrandNums_'+ j + '" value="' + item.brandNum+ '">' + item.brandName + '&nbsp;');
						if (i % 7 == 0) {
							$("#advBrandNumDiv_" + j).append('<br>');
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
        var advInfo = new Array();
        for ( var j = 0; j <tableID; j++) {
              var advName = $("#advName_"+j).val();
              var advNum = $("#advNum_"+j).val();
              var advDesc = $("#advDesc_"+j).val();
              var startTime = $("#startTime_"+j).val();
              var endTime = $("#endTime_"+j).val();
              var openUriType= $("input[name='openUriType_"+j+"']:checked").val();
              var advImgType= $("input[name='advImgType_"+j+"']:checked").val();
              var useState= $("input[name='useState_"+j+"']:checked").val();
              var advMenuIcon = $("#advMenuIcon_"+j).val();
              var advImg = $("#advImg_"+j).val();
              var advUri = $("#advUri_"+j).val();
              var advImgS = $("#advImgS_"+j).val();
              var bz = $("#bz_"+j).val();
              var area=component.getAdvAreaNums(j);
              var brand=component.getAdvBrandNums(j);
              
              var element = {};
        	  element.advName =advName;
        	  element.advNum =advNum;
        	  element.advDesc =advDesc;
        	  element.startTime =startTime;
        	  element.endTime =endTime;
        	  element.openUriType =openUriType;
        	  element.advImgType =advImgType;
        	  element.useState =useState;
        	  element.advMenuIcon =advMenuIcon;
        	  element.advImg =advImg;
        	  element.advUri =advUri;
        	  element.advImgS =advImgS;
        	  element.bz =bz;
        	  element.advAreaNum =area;
        	  element.advBrandNum =brand;
        	  advInfo.push(element);
        }
        var info=$.toJSON(advInfo);
        var channelNum = $("#channelNum").val();
        var positionNum = $("#positionNum").val();
       
        var sortNum="";
        $("select[name=showXh] option").each(
      		  function() {
      			  sortNum=sortNum+$(this).val()+".";
      		  }
      		 );
      if(sortNum.length>0){
      	sortNum=sortNum.substring(0,sortNum.length-1);}
        $.singleReq({
            data: {
                "reqUrl": "advInfo",
                "reqMethod": "saveAdvInfo",
                "channelNum": channelNum,
                "positionNum": positionNum,
                "sortNum": sortNum,
                "advInfo": $.toJSON(advInfo)
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	//alert($.toJSON(ret.retObj))
                	
                	$("#retobj").val($.toJSON(ret.retObj));
                    $("#addForm").submit();
                    var times=5000;	
                    	
                	var t=setTimeout('UOMPComp.showSuccessDialog("添加成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times)
                    
                }
                else {
                    UOMPComp.showFailedDialog("添加失败！", "");
                }
            }
        });
    },
    /*
     *获取地市 
     */
    getAdvAreaNums:function(tableID){
    	var areaNum = new Array();
    	var advNum = $("#advNum_"+tableID).val();
    	  $("input[name='advAreaNums_"+tableID+"']:checked").each(function () {
    		  var element = {};
        	  element.advAreaNum =this.value;;
        	  element.advNum = advNum;
        	  areaNum.push(element);
        });
    	 return  $.toJSON(areaNum);
    	
    },
    /*
     * 获取品牌
     */
    getAdvBrandNums:function(tableID){
    	var brandNum = new Array();
    	var advNum = $("#advNum_"+tableID).val();
    	$("input[name='advBrandNums_"+tableID+"']:checked").each(function () {
    		var element = {};
    		 element.advBrandNum = this.value;
    		 element.advNum = advNum;
    		 brandNum.push(element);
      });
    	 
    	 return  $.toJSON(brandNum);
    	
    }
    
    
  
}
