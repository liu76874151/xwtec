var tableID=0;//--广告信息位ID
var tableIDCount=0;//--广告信息位总个数
var loadTabId=0;//--动态加载起始ID
var tabSimpleContent="";//--包含广告名称和编码的广告信息

$(document).ready(function () {
	    component.addAdvfun("","",0);
        component.initForm();
        component.btnMoveOnClick();
       // component.initValidate();
    }
);

var component = {
		// 初始化校验规则
//		initValidate: function () {
//	},
		
    initForm: function () {
	var hidChannelNum=$("#hidChannelNum").val();
	var hidPositionNum=$("#hidPositionNum").val();
	var hidAdvNum=$("#hidAdvNum").val();
	component.findOneAdvinfo(0);
       
    },
    //--查询单个广告详细信息
    findOneAdvinfo:function(tableID){
    	var advNum="";
    	if(tableID==0){
    		advNum =$("#hidAdvNum").val();
    	}else{
    		advNum =$("#advNum_"+tableID).val();
    	}
    	 $.singleReq({
             data: {
                 "reqUrl": "advInfo",
                 "reqMethod": "queryAdvInfoList",
                 "start": 0,
 		         "end": 1,
                 "advNum":advNum
             },
             success: function (ret) {
                 var result = ret.retObj.records[0];
                 if(tableID==0){
	                $("#advName").val(result.advName);
	                $("#advName_"+tableID).val(result.advName);
	                $("#advNum_"+tableID).val(result.advNum);
	                $("#channelNum option[value='"+result.channelNum+"']").attr("selected",true);
	                component.initAdvPosition(result.positionNum);
	                //$("#useState"+result.useState).attr("checked",true);
                   }
                 $("#advDesc_"+tableID).val(result.advDesc);
                 $("#startTime_"+tableID).val(result.startTime);
                 $("#endTime_"+tableID).val(result.endTime);
                 $("#openUriType"+result.openUriType+"_"+tableID).attr("checked",true);
                 $("#advImgType"+result.advImgType+"_"+tableID).attr("checked",true);
                 $("#useState"+result.useState+"_"+tableID).attr("checked",true);
                 if(result.advMenuIcon){
                	// alert(2222222)
                     $("#advMenuIconShow_"+tableID).attr('src',GLOBAL_INFO.CONTEXTPATH +"/upload/"+result.advMenuIcon) ;
                     $("#advMenuIconOld_"+tableID).val(result.advMenuIcon) ;
                     $("#advMenuIconShow_"+tableID).css('display','block');
                     }
               //  alert(result.advImg+"==result.advImg===")
                 if(result.advImg){
                	// alert(1111111111)
                     $("#advImgShow_"+tableID).attr('src',GLOBAL_INFO.CONTEXTPATH +"/upload/"+result.advImg) ;
                     $("#advImgOld_"+tableID).val(result.advImg) ;
                     $("#advImgShow_"+tableID).css('display','block');
                     }
                 if(result.advUri){
                	// alert(3333)
                     $("#advUriShow_"+tableID).attr('src',GLOBAL_INFO.CONTEXTPATH +"/upload/"+result.advUri) ;
                     $("#advUriOld_"+tableID).val(result.advUri) ;
                     $("#advUriShow_"+tableID).css('display','block');
                     }
                 if(result.advImgS){
                	// alert(4444)
                     $("#advImgSShow_"+tableID).attr('src',GLOBAL_INFO.CONTEXTPATH +"/upload/"+result.advImgS) ;
                     $("#advImgSOld_"+tableID).val(result.advImgS) ;
                     $("#advImgSShow_"+tableID).css('display','block');
                     }
                 $("#bz_"+tableID).val(result.bz);
                 if(result.advAreaNum)
                 var advAreaNum=(result.advAreaNum).split(",");
                 if(result.advBrandNum)
                 var advBrandNum=(result.advBrandNum).split(",");
                 $("input[name='advAreaNums_"+tableID+"']").each(function () {
                	 var index=$.inArray(this.value,advAreaNum);
                	 if(index>-1){
                	 $("input[name='advAreaNums_"+tableID+"'][value='"+this.value+"']").attr("checked",true);
                	 }
                 });
                 $("input[name='advBrandNums_"+tableID+"']").each(function () {
                	 var index=$.inArray(this.value,advBrandNum);
                	 if(index>-1){
                	 $("input[name='advBrandNums_"+tableID+"'][value='"+this.value+"']").attr("checked",true);
                	 }
                 });
                 
             }
         });
    },
    //--动态加载广告信息
    loadAdvInfo:function(){
    	var advNum;
    	var advName;
    	var hidAdvNum=$("#hidAdvNum").val();
    	for ( var j = loadTabId; j < (loadTabId+3)&&(j<tableIDCount); j++) {
    		advNum=tabSimpleContent[j].advNum
    		advName=tabSimpleContent[j].advName
    		if(advNum!=hidAdvNum){
    			tableID++;
    		component.addAdvfun(advNum,advName,tableID);
    		}
		}
    	loadTabId=loadTabId+3;
    	if(loadTabId>=tableIDCount){
    		$("#loadLink").parent().parent().hide();
    		}
    },
    //--展开/收起广告添加位
    isShowAdv:function(tableID){
    	 var isShow= $(".addAdv_"+tableID).is(":visible");
    	 var text= isShow?"展开":"收起";
    	 $("#showAdvButton_"+tableID).val(text);
    	 var useState= $("input[name='useState_"+tableID+"']:checked").val();//--useState是非空字段,如果为undefined表示数据未加载
    	 if(!isShow&&(useState==undefined)){
    		 component.findOneAdvinfo(tableID);
    	 	}
    	 $(".addAdv_"+tableID).fadeToggle(); 
    },
    //-- 初始化表单
    initTable: function (j) {
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
    //--初始化广告位
    initAdvPosition:function(positionNum){
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
                    	if(positionNum==item.positionNum) {
                    	$("#positionNum").append("<option value="+item.positionNum+" positionType="+item.positionType+" selected='true' >"+item.positionName+"("+positionTypename+")</option>");
                    	component.addAdvOption();
                    	}else{
                    		$("#positionNum").append("<option value="+item.positionNum+" positionType="+item.positionType+" >"+item.positionName+"("+positionTypename+")</option>");
                    	}
                    	});
                
    					}
               
            }
        });
 
    },
    /*
     * 根据广告位加载广告顺序的
     */
    addAdvOption:function(){
    	var positionNum = $("#positionNum").val();
    	var positionType = $("#positionNum option:selected").attr("positionType");
    	if(positionType==1){
    		$(".showImgs").show();
    	}else{
    		$(".showImgs").hide();
    	}
    	$.singleReq({
            data: {
		    		"reqUrl" : "advInfo",
					"reqMethod" : "queryAdvInfoListOrderShowXh",
					"positionNum" : positionNum
    		        
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	$("#showXh").empty();//--清空
                	var result = ret.retObj.records;
                	tableIDCount=ret.retObj.totalRecord;
                	tabSimpleContent=result;
                	 component.loadAdvInfo();//--加载table_simple_content
                	var hidAdvNum=$("#hidAdvNum").val();
                    $.each(result, function(i, item) {
                    	if(hidAdvNum==item.advNum){
                    		$("#showXh").append("<option value="+item.advNum+" selected='true'>"+item.advName+"("+item.advNum+")</option>");
                    	}else{
                   	 $("#showXh").append("<option value="+item.advNum+" >"+item.advName+"("+item.advNum+")</option>");
                    	}
                    });
    					}
            }
        });


    	
    },
    /*
     *广告编码和广告名称不为空时加入到顺序select中 
     */
    addOption:function(tableID){
    	var advNum = $("#advNum_"+tableID).val();
    	var advName = $("#advName_"+tableID).val();
        var obj=$("#showXh option[value='"+advNum+"']");
        	if(advNum&&advName){ 
        		if(obj[0]){
        			$("#showXh  option[value='"+advNum+"']").text(advName+"("+advNum+")");
        			}
        		}
    	
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
    /*
     * 修改广告信息
     */
    saveForm: function () {
    	if (!$('#editForm').valid()) {
            return;
        }
    	var advInfo = new Array();
        for ( var j = 0; j <(tableID+1)&&($("input[name='useState_"+j+"']:checked").val()!=undefined); j++) {
              var advName = $("#advName_"+j).val();
              var advNum = $("#advNum_"+j).val();
              var advDesc = $("#advDesc_"+j).val();
              var startTime = $("#startTime_"+j).val();
              var endTime = $("#endTime_"+j).val();
              var openUriType= $("input[name='openUriType_"+j+"']:checked").val();
              var advImgType= $("input[name='advImgType_"+j+"']:checked").val();
              var useState= $("input[name='useState_"+j+"']:checked").val();
              //TODO
              var advMenuIcon=$("#advMenuIcon_"+j).val();
              if(!advMenuIcon){
            	  advMenuIcon=$("#advMenuIconOld_"+j).val();
              }
              var advImg=$("#advImg_"+j).val();
              if(advImg){advImg=$("#advImgOld_"+j).val();}
              var advUri=$("#advUri_"+j).val();
              if(advUri){advUri=$("#advUriOld_"+j).val();}
              var advImgS=$("#advImgS_"+j).val();
              if(advImgS){advImgS=$("#advImgSOld_"+j).val();}
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
                "reqMethod": "updateAdvInfo",
                "sortNum": sortNum,
                "advInfo": $.toJSON(advInfo)
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                	$("#retobj").val($.toJSON(ret.retObj));
                    $("#editForm").submit();
                    var times=5000;	
                	var t=setTimeout('UOMPComp.showSuccessDialog("修改成功！", "");top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();',times)
                    
               
                }
                else {
                    UOMPComp.showFailedDialog("修改失败！", "");
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
    	
    },
  //--加载广告添加位
	addAdvfun:function(advNum,advName,tableID){
		var tableContent ='<tr class="addAdvName_'+tableID+'">'+
		'<th align="right" >广告名称：</th>'+
		'<td class="form_table_content2">'+	
		'<input type="text" name="advName" id="advName_'+tableID+'"   class="form_input"  onchange="component.addOption('+tableID+')" />'+	
		'&nbsp;&nbsp;<input type="button" id="showAdvButton_'+tableID+'" onclick="component.isShowAdv('+tableID+')" value="收起">'+
		'</td>'+
		'</tr>'+
		'<tr class="addAdv_'+tableID+'">'+
		'<th align="right" >广告编码：</th>'+
		'<td class="form_table_content2" ><input type="text" class="form_input"  name="advNum"  id="advNum_'+tableID+'"  disabled/></td>'+
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
		'<input type="hidden"   name="advMenuIconOld_'+tableID+'" id="advMenuIconOld_'+tableID+'" />'+
		'<span class="errorMsg"></span>'+
		'<br><img alt="" style="display:none; width: 240;height: 55" name="advMenuIconShow" id="advMenuIconShow_'+tableID+'"  src=""></td>'+
		'</tr>'+
		'<tr class="addAdv_'+tableID+'">'+
			'<th align="right" >广告图片：</th>'+
			
		'<td class="form_table_content2" > &nbsp;'+
		'<input type="file" name="files" id="advImg_'+tableID+'" class="qinggoudan_input021" >'+
		'<input type="hidden"   name="advImgOld_'+tableID+'" id="advImgOld_'+tableID+'" />'+
		'<span class="errorMsg"></span>'+
		'<br><img alt="" style="display:none; width: 240;height: 55" name="advImgShow" id="advImgShow_'+tableID+'"  src=""></td>'+
		'</tr>'+
		'<tr class="addAdv_'+tableID+'">'+
			'<th align="right" >广告链接：</th>'+
		'<td class="form_table_content2" > &nbsp;'+
		'<input type="file" name="files" id="advUri_'+tableID+'"class="qinggoudan_input021" >'+
		'<input type="hidden"   name="advUriOld_'+tableID+'" id="advUriOld_'+tableID+'" />'+
		'<span class="errorMsg"></span>'+
		'<br><img alt="" style="display:none; width: 240;height: 55" name="advUriShow" id="advUriShow_'+tableID+'"  src=""></td>'+
		'</tr>'+
		'<tr class="addAdv_'+tableID+'">'+
			'<th align="right" >广告小图片：</th>'+
		'<td class="form_table_content2" > &nbsp;'+
		'<input type="file" name="files" id="advImgS_'+tableID+'" class="qinggoudan_input021" >'+
		'<input type="hidden"   name="advImgSOld_'+tableID+'" id="advImgSOld_'+tableID+'" />'+
		'<span class="errorMsg"></span>'+
		'<br><img alt="" style="display:none; width: 240;height: 55" name="advImgSShow" id="advImgSShow_'+tableID+'"  src=""></td>'+
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
		$("#editTab").append(tableContent);
		component.initTable(tableID);
		if(tableID>0){
			$(".addAdv_"+tableID).hide();
			$("#advNum_"+tableID).val(advNum);
			$("#advName_"+tableID).val(advName);
	    	$("#showAdvButton_"+tableID).val("展开");
		}
		
	}
};




