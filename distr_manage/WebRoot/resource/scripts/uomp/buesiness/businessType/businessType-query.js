var divid="";
$().ready(function() {
	var channelNum = $("#channelNumP").val();
	if(channelNum!=null&&channelNum!=""){
		$("#confirmBt").css({display:""});
		$("#channelNum").prop({disabled:"true"});
		$("#channelNum").val(channelNum);
		$("#reset").attr("onclick","#")
	}
	component.queryBusiType(0,10000);
});
function reloadData() {
	window.location.reload();
}

jQuery.extend({
    singleSync: (function () {
        var default_options = {
            "type": "post",
            "timeout": "320000",
            "contentType": "application/x-www-form-urlencoded; charset=UTF-8",
            "url": GLOBAL_INFO.COMMON_REQ_URI,
            "async":false,
            "dataType": "json",
            "success": function (data) {
                alert("Ajax Success!");
            },
            "error": function (request, textStatus, errorThrown) {
            },
            "complete": function () {
            }
        };
        return function (user_options) {
            var options = {};
            $.extend(options, default_options, user_options);
            $.ajax(options);
        };
    })()
});
var component = {

	/**
	 * 查询
	 */
	queryBusiType : function(start, end) {
		var busiTypeName = $("#busiTypeName").val();
		var jb = $('#jb option:selected').val();
		var channelNum = $('#channelNum option:selected').val();
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
		if (jb=="" && busiTypeName=="") {
			jb = 1;
		}
		$.singleSync({
				data : {
					"busiTypeName" : busiTypeName,
					"jb" : jb,
					"channelNum":channelNum,
					"reqUrl" : "businessHandler",
					"reqMethod" : "queryBusiType",
					"start" : start,
					"end" : end
				},
				success : function(ret) {
					$("#busiTypeTb").empty();
					if (ret.retCode == 0) {
						var result = ret.retObj;
						for (var index = 0; index < result.length; index++) {
							var busiTypeName = result[index].busiTypeName;
							var busiTypeNum = result[index].busiTypeNum;
							var jb = result[index].jb;
							var jbNum = result[index].jbNum;
							var mj = result[index].mj;
							var channelNum = result[index].channelNum;
							
							var checkbox = "<input name='checkedjbNum' type='checkbox' busiTypeNum='"+busiTypeNum+"' busiTypeName='"+busiTypeName+"' value='"+jbNum+"'>";
							if(mj=='1'){
								$("#busiTypeTb").append("<dl id='"+jbNum+"' class=\"classification-item-con top-classification\"></dl>");
								$("#"+jbNum+"").append("<dt class=\"classification-item-title\">"
												            +"<p><span id='"+busiTypeNum+"'><label>"+checkbox+"- "+busiTypeName+"</label>" +
												            "<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNum+"','"+jbNum+"','"+jb+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a></span></p>"
												          +"</dt>");
							}else{
								$("#busiTypeTb").append("<dl id='"+jbNum+"' class=\"classification-item-con top-classification\"></dl>");
								$("#"+jbNum+"").append("<dt class=\"classification-item-title\">"
												            +"<p><span id='"+busiTypeNum+"'><label>"+checkbox+"- "+busiTypeName+"</label>" +
												            "<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNum+"','"+jbNum+"','"+jb+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a><s class=\"broken-line\"></s></span></p>"
												          +"</dt>");
							}
							if(mj=='0'){
								var id = jbNum +""+ jb;
								$.singleSync({
									data : {
										"jbNum" : jbNum,
										"jb" : jb+1,
										"channelNum":channelNum,
										"reqUrl" : "businessHandler",
										"reqMethod" : "queryBusiType",
										"start" : start,
										"end" : end
									},
									success : function(ret) {
										if (ret.retCode == 0) {
											var result = ret.retObj;
											for (var index = 0; index < result.length; index++) {
												var busiTypeName = result[index].busiTypeName;
												var busiTypeNum = result[index].busiTypeNum;
												var jb = result[index].jb;
												var jbNumnew = result[index].jbNum;
												var mj = result[index].mj;
												var channelNum = result[index].channelNum;
												
												var checkbox = "<input name='checkedjbNum' type='checkbox' busiTypeNum='"+busiTypeNum+"' busiTypeName='"+busiTypeName+"' value='"+jbNumnew+"'>";
												if(index==0){
													//$("#"+jbNum).append("<span id='"+id+"'><span>");
													$("#"+jbNum+"").append("<dd class=\"classification-item-detail\"><p id='"+id+"'></p></dd>");
												}
												if(mj=='1'){
													$("#"+id).append("<span id='"+busiTypeNum+"'><label>"+checkbox+busiTypeName+"</label><a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNumnew+"','"+jbNum+"','"+jb+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a></span>");
													$("#"+id).append("<em>|</em>");
												}else{
//													<span><label><input type="checkbox">网上充值</label><a onclick="Effect('toggle_box1',this.id);" id="toggle_btn1" class="arrow-down"></a><a title="添加" class="add-icon"></a><a title="修改" class="edit-icon"></a></span>
//              									<em>|</em>
													$("#"+id).append("<span id='"+busiTypeNum+"'><label>"+checkbox+busiTypeName+"</label><a onclick=\"component.querySubType('"+busiTypeName+"','"+jbNum+"','"+jbNumnew+"','"+jb+"','"+channelNum+"');Effect('"+jbNumnew+"',this.id);\" id=\"toggle_"+jbNumnew+"\" class=\"arrow-down\"></a>" +
															"<a href=\"javascript:component.addBusiType('"+busiTypeNum+"','"+jbNumnew+jb+"','"+jbNum+"','"+channelNum+"');void(0);\" title=\"添加\" class=\"add-icon\"></a>" +
															"<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNumnew+"','"+jbNum+"','"+jb+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a></span>");
													$("#"+id).append("<em>|</em>");
													
//													$("#"+id).append("<span id='"+busiTypeNum+"'>"+checkbox+busiTypeName+"<a href=\"javascript:component.querySubType('"+busiTypeName+"','"+jbNum+"','"+jbNumnew+"','"+jb+"');void(0);\"><b>︾</b></a>&nbsp;" +
//														"<a href=\"javascript:component.addBusiType('"+busiTypeNum+"','"+jbNumnew+jb+"','"+jbNum+"');void(0);\"><b>＋</b></a>" +
//																"<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNumnew+"','"+jbNum+"','"+jb+"');\"><img src=\"../../../resource/img/edit.png\"/></a></span>&nbsp;&nbsp;&nbsp;");
												}
											}
										} 
									}
								});
							}
						}
					} else {
						//
					}
				}
			});
	},
	Scroll :function(obj, h, s,flag){
//	    h = document.getElementById(obj).scrollHeight;
		var jb= 1;
		jb = $("#jb").val();
		if(jb==''){
			jb='1';
		}
		obj = obj.substring(0,parseInt(jb)*2);//TODO 此处临时解决,超过100条会有bug
		h = $("[id|="+obj+"]").prop("scrollHeight");
	    var h = h || 200;
	    var s = s || 1.2;
	    var obj = typeof(obj)=="string"?$("[id|="+obj+"]"):obj;
	    if(obj == undefined){return false;}
	    var status = obj.attr("status")==null;
	    var oh = parseInt(obj.prop("offsetHeight"));
	    obj.css("height",oh);
	    obj.css("display","block");
	    obj.css("overflow","hidden");
	    if(obj.attr("oldHeight") == undefined){
	        obj.attr("oldHeight", oh);
	    }else{
	        var oldH = Math.ceil(obj.attr("oldHeight"));
	    }
	    var reSet = function(){
	        if(status){
	            if(oh < h){
	                oh = Math.ceil(h-(h-oh)/s)+20;
	                obj.css("height",oh+"px");
	            }else{
	            	if (flag==true) {
		                obj.setAttribute("status",false);
	            	}
	                window.clearInterval(IntervalId);
	            }
	        }else{
	            obj.css("height",oldH+"px");
	            obj.removeAttr("status");
	            window.clearInterval(IntervalId);
	        }
	    }
	    var IntervalId = window.setInterval(reSet,10);
	 return status;
	},
	querySubType:function(parName,parentjbNum,jbNum,jb,channelNum){
		var classJb = jbNum.length/2;
		var parid = parentjbNum+ "" + (jb-1);
		var id = jbNum+ "" + jb;
		
		if(parentjbNum==""){
			component.Scroll(jbNum,0,1.2,false);
			return;
		}
		
		if($("#"+id).val()!=undefined){
			return;
		}
		$.singleSync({
			data : {
				"jbNum" : jbNum,
				"jb" : parseInt(jb)+1,
				"channelNum":channelNum,
				"reqUrl" : "businessHandler",
				"reqMethod" : "queryBusiType",
				"start" : 0,
				"end" : 10000
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					for (var index = 0; index < result.length; index++) {
						var busiTypeName = result[index].busiTypeName;
						var busiTypeNum = result[index].busiTypeNum;
						var jbnew = result[index].jb;
						var jbNumnew = result[index].jbNum;
						var mj = result[index].mj;
						var channelNum = result[index].channelNum;
						
						var checkbox = "<input name='checkedjbNum' type='checkbox' busiTypeNum='"+busiTypeNum+"' busiTypeName='"+busiTypeName+"' value='"+jbNumnew+"'>";
						if (index==0 && $("#"+jbNum).length==0) {
//							$("#"+parid).after("<span id='"+id+"'><span>");
//							$("#"+id).append("<hr style='border:dashed 1px;'>");
//							$("#"+id).append("<br>&nbsp;&nbsp;&nbsp;--"+parName+"&nbsp;<a href='#' style='text-decoration:none;'>+&nbsp;&nbsp;</a><a href='#' style='text-decoration:none;'>X</a>");
//							$("#"+id).append("<br>");
							
							$("[id|="+parentjbNum.substring(0,parseInt(jb)*2)+"]").after("<dl id='"+jbNum+"' style=\"display: none;\" id=\"toggle_box1\" class=\"classification-item-con classification"+classJb+"\"></dl>");
						}
						if ($("#"+id).length==0) {
							$("#"+jbNum).append("<dt class=\"classification-item-title\">"
            					+"<p><label>-- "+parName+"</label><a onclick=\"Closed('"+jbNum+"','toggle_"+jbNum+"');\" title=\"关闭\" class=\"del-icon\"></a><s class=\"broken-line\"></s></p>"
          						+"</dt>");
							$("#"+jbNum).append("<dd class=\"classification-item-detail\"><p id='"+id+"'></p></dd>");
						}
						if(mj=='1'){
							//$("#"+id).append("<span id='"+busiTypeNum+"'>"+checkbox+busiTypeName+"<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNumnew+"','"+jbNum+"','"+jb+"');\"><img src=\"../../../resource/img/edit.png\"/></a></span>&nbsp;&nbsp;&nbsp;");
							$("#"+id).append("<span id='"+busiTypeNum+"'><label>"+checkbox+busiTypeName+"</label>" +
									"<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNumnew+"','"+jbNum+"','"+jbnew+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a></span>");
							$("#"+id).append("<em>|</em>");
						}else{
							$("#"+id).append("<span id='"+busiTypeNum+"'><label>"+checkbox+busiTypeName+"</label><a class=\"arrow-down\" id=\"toggle_"+jbNumnew+"\" onclick=\"component.querySubType('"+busiTypeName+"','"+jbNum+"','"+jbNumnew+"','"+jbnew+"','"+channelNum+"');Effect('"+jbNumnew+"',this.id);\"\">" +
									"</a><a href=\"javascript:component.addBusiType('"+busiTypeNum+"','"+jbNumnew+jbnew+"','"+jbNum+"','"+channelNum+"');void(0);\" title=\"添加\" class=\"add-icon\"></a>" +
									"<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNumnew+"','"+jbNum+"','"+jbnew+"','"+channelNum+"');\" title=\"修改\" class=\"edit-icon\"></a></span>");
							$("#"+id).append("<em>|</em>");
              
//							$("#"+id).append("<span id='"+busiTypeNum+"'>"+checkbox+busiTypeName+"<a  href=\"javascript:component.querySubType('"+busiTypeName+"','"+jbNum+"','"+jbNumnew+"','"+jb+"');void(0);\"><b>︾</b></a>&nbsp;" +
//									"<a href=\"javascript:component.addBusiType('"+busiTypeNum+"','"+jbNumnew+jb+"','"+jbNum+"');void(0);\"><b>＋</b></a>" +
//											"<a href=\"javascript:component.modBusiType('"+busiTypeNum+"','"+jbNumnew+"','"+jbNum+"','"+jb+"');\"><img src=\"../../../resource/img/edit.png\"/></a></span>&nbsp;&nbsp;&nbsp;");
						}
					}
				}
				//component.Scroll(parentjbNum,0,1.2,false);
			}
		});
	},
	addBusiType: function(busiTypeNum,parid,parjbNum,channelNum){
//		var size = {"height":"400","width":"1000"};
		var url = GLOBAL_INFO.CONTEXTPATH + "/page/business/businessType/businessType_add.jsp?busiTypeNum="+busiTypeNum+"&parid="+parid+"&parjbNum="+parjbNum+"&channelNum="+channelNum;
//		top.UOMPDialog.showSubPage("业务分类添加",url,null,size);
		
		window.open(url,'addBusiType',
						'height=550, width=700, top=60,left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
	},
	modBusiType: function(busiTypeNum,jbNum,parjbNum,jb,channelNum){
//		var size = {"height":"400","width":"1000"};
		var url = GLOBAL_INFO.CONTEXTPATH + "/page/business/businessType/businessType_modify.jsp?busiTypeNum="+busiTypeNum+"&jbNum="+jbNum+"&parjbNum="+parjbNum+"&jb="+jb+"&channelNum="+channelNum;
//		top.UOMPDialog.showSubPage("业务分类修改",url,null,size);
		window.open(url,'modBusiType',
						'height=500, width=700, top=60,left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
	},
	deleteBusiType: function(){
		var  checkedjbNum = $("input[name=checkedjbNum]:checked");
		var channelNum = $('#channelNum option:selected').val();
		if (checkedjbNum.length==0) {
			UOMPComp.showTipDialog("请选择业务分类！", "");
			return;
		}
		var jbNums = this.getArrayValue(checkedjbNum).toString();
		UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "businessHandler",
                            "reqMethod": "deleteBusiType",
                            "channelNum":channelNum,
                            "jbNums": jbNums
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog("删除成功!", "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        var result = ret.retObj;
                                        var busiNums =  new Array();
                                        for (var index = 0; index < result.length; index++) {
                                        	var busiNum = result[index].busiNum;
                                        	busiNums[index]=busiNum;
                                        }
                                        UOMPComp.showFailedDialog("有业务("+busiNums.toString()+")关联到将要删除的属性,需要先取消关联!", "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
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
	getCheckedValue: function(){
		var channelNum = $("#channelNum option:selected").val();
		if (channelNum=="") {
			UOMPComp.showTipDialog("请选择渠道！", "");
			return;
		}
		try {
			var busiTypeList = new Array();
//			if (channelNum=="01") {
//             	$("#businessTypeList_01",opener.document).empty();
//             }
//             if (channelNum=="02") {
//             	$("#businessTypeList_02",opener.document).empty();
//             }
             if ($.trim($("#businessTypeJson_"+channelNum,opener.document).val())!="") {
	             busiTypeList = $.parseJSON($("#businessTypeJson_"+channelNum,opener.document).val());
             }
			$("input[name=checkedjbNum]:checked").each(function () {
				var flag = true;
				 var element = {};
	             var jbNum = $(this).val(); //获取单个value
	             var busiTypeName = $(this).attr("busiTypeName");
	             // busiTypeNum
	             var busiTypeNum = $(this).attr("busiTypeNum");
	             
				for (var index = 0; index < busiTypeList.length; index++) {
					var num = busiTypeList[index].busiTypeNum;
					if (num==busiTypeNum) {
						flag = false;
					}
				}
				
				if (flag) {
					var id = busiTypeNum +"_" + channelNum;
		             var comment  = "<span id='"+id+"'>"+busiTypeName+"<a href=\"javascript:removeDom('"+busiTypeNum+"','"+channelNum+"');\">" +
		             		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
		             if (channelNum=="01") {
		             	$("#businessTypeList_01",opener.document).append(comment);
		             }
		             if (channelNum=="02") {
		             	$("#businessTypeList_02",opener.document).append(comment);
		             }
		             
		             element.jbNum = jbNum;
		             element.busiTypeName = busiTypeName;
		             element.channelNum=channelNum;
		             element.busiTypeNum=busiTypeNum;
		             busiTypeList.push(element);
				}
	             
	         });
	        var json = $.toJSON(busiTypeList);
	        if (channelNum=="01") {
             	$("#businessTypeJson_01",opener.document).empty();
				$("#businessTypeJson_01",opener.document).val(json);
             }
             if (channelNum=="02") {
             	$("#businessTypeJson_02",opener.document).empty();
				$("#businessTypeJson_02",opener.document).val(json);
             }
		
			window.close();
		} catch (e) {
			return "";
		}
	}
}