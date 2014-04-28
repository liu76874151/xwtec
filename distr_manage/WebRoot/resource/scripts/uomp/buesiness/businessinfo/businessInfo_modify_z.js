$().ready(function() {
//			comp_z.queryRelaAreaList_z(0, 500, '02');
			comp_z.queryRelaBrandList_z(0, 500, '02');
			comp_z.queryBusiInfoBynum_z('02');
		});
function showTime_z() {
	var val = $("input[name=dateTime_02]:checked").val();
	if (val == "0") {
		$("#date_time_02").css({
					display : "none"
				});
		$("#startTime_02").val("");
		$("#endTime_02").val("");
	} else {
		$("#date_time_02").css({
					display : "block"
				});
	}
}
var comp_z = {
	queryRelaAreaList_z : function(start, end, channelNum) {// 地市查询
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}

		$.singleSync({
					data : {
						"reqUrl" : "area",
						"reqMethod" : "queryCityList",
						"start" : start,
						"end" : end
					},
					success : function(ret) {
						if (ret.retCode == 0) {
							var result = ret.retObj.records;
							$("#releCityList_" + channelNum).empty();
							for (var int = 0; int < result.length; int++) {
								var ck = "<input name='relaCity" + channelNum
										+ "' type='checkbox' channelNum='"
										+ channelNum + "' value='"
										+ result[int].areaNum + "' />"
										+ result[int].areaName + "&nbsp;&nbsp;";
								$("#releCityList_" + channelNum).append(ck);
								if (int == 0) {
									$("#releCityList_" + channelNum)
											.append("<br>");
								}
							}

						} else {
							UOMPComp.showFailedDialog("失败！", "");
						}
					}
				});
	},
	queryRelaBrandList_z : function(start, end, channelNum) {// 品牌查询
		var jb = "1,2";
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}

		$.singleSync({
			data : {
				"reqUrl" : "brand",
				"reqMethod" : "queryBrandListEx",
				"jb":jb,
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					$("#releBrandList_" + channelNum).empty();
					for (var int = 0; int < result.length; int++) {
						var ck = "<input name='relaBrand" + channelNum
								+ "' type='checkbox' channelNum='"
								+ channelNum + "' value='"
								+ result[int].brandNum + "' />"
								+ result[int].brandName
								+ "&nbsp;&nbsp;";
						$("#releBrandList_" + channelNum).append(ck);
						if (int == 0) {
							$("#releBrandList_" + channelNum)
									.append("<br>");
						}
					}

				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	},
	queryBusiInfoBynum_z : function(channelNum) {
		$.singleSync({
			data : {
				"reqUrl" : "businessInfoHandler",
				"reqMethod" : "queryBusiInfoBynum",
				"busiNum" : $("#pkid").val(),
				"channelNum" : channelNum
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					var businessTypeDzBeans = new Array();
					var relationBusinessInfoBeans = new Array();
					try{
						businessTypeDzBeans = result.businessTypeDzBeans;
						relationBusinessInfoBeans = result.relationBusinessInfoBeans;
						var busiTypeList = new Array();
						for(var index = 0; index < businessTypeDzBeans.length; index++) {
							var element = {};
							var busiTypeName = businessTypeDzBeans[index].busiTypeName;
							var busiTypeNum = businessTypeDzBeans[index].busiTypeNum;
							var jbNum = businessTypeDzBeans[index].jbNum;
							var id = busiTypeNum +"_" + channelNum;
		             		var comment  = "<span id='"+id+"'>"+busiTypeName+"<a href=\"javascript:removeDom('"+busiTypeNum+"','"+channelNum+"');\">" +
		             						"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
							$("#businessTypeList_"+channelNum).append(comment);
							element.jbNum = jbNum;
				            element.busiTypeName = busiTypeName;
				            element.channelNum=channelNum;
				            element.busiTypeNum=busiTypeNum;
				            busiTypeList.push(element);
							
						}
						$("#businessTypeJson_"+channelNum).val($.toJSON(busiTypeList));
					}catch(e){
						//TODO
					};
					try{
						var busiInfoList = new Array();
						for (var index = 0; index < relationBusinessInfoBeans.length; index++) {
							var element = {};
							var busiName = relationBusinessInfoBeans[index].busiName;
							var relativeBusi = relationBusinessInfoBeans[index].relativeBusi;
							
							var id = relativeBusi +"_" + channelNum;
		             		var comment  = "<span id='"+id+"'>"+busiName+"<a href=\"javascript:removeRela('"+relativeBusi+"','"+channelNum+"');\">" +
		             						"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
							$("#businessReleList_"+channelNum).append(comment);
//							if ((index+1)%3==0) {
//								$("#businessReleList_"+channelNum).append("<br/>");
//							}
							 element.relativeBusi = relativeBusi;
							 element.channelNum = channelNum;
							 element.busiName = busiName;
							 busiInfoList.push(element);
						}
						$("#relaBusiInfoJson"+channelNum).val($.toJSON(busiInfoList));
					}catch(e){
						//TODO
					};
					if (relationBusinessInfoBeans.length>0) {
						$("#releCityList_"+channelNum).parent().css({display:""});
						$("#releBrandList_"+channelNum).parent().css({display:""});
						$.singleSync({
							data : {
								"reqUrl" : "businessHandler",
								"reqMethod" : "queryRelationInfos",
								"busiNum" : $("#pkid").val(),
								"channelNum" : channelNum
							},
							success : function(ret) {
								if (ret.retCode == 0) {
									var result = ret.retObj;
									if (result!=undefined) {
										var relationBusinessInfoAreaDzBeans = result.relationBusinessInfoAreaDzBeans;
										$("#busiAreaJson"+channelNum).val($.toJSON(relationBusinessInfoAreaDzBeans));
										try {
											for (var index = 0; index < relationBusinessInfoAreaDzBeans.length; index++) {
												var areaNum  = relationBusinessInfoAreaDzBeans[index].areaNum;
												var areaName = relationBusinessInfoAreaDzBeans[index].areaName;
												
												var id = areaNum+channelNum;
											    var comment  = "<span id='"+id+"'>"+areaName+"<a href=\"javascript:removeArea('"+areaNum+"','"+channelNum+"');\">" +
											     		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
												$("#cityList"+channelNum).append(comment);
												
//												$("input[name=relaCity"+channelNum+"][value="+areaNum+"]").attr("checked","checked");
											}
										} catch (e) {
											//TODO
										}
										var relationBusinessInfoBrandDzBeans = result.relationBusinessInfoBrandDzBeans;
										try {
											for (var index = 0; index < relationBusinessInfoBrandDzBeans.length; index++) {
												var brandNum  = relationBusinessInfoBrandDzBeans[index].brandNum;
												$("input[name=relaBrand"+channelNum+"][value="+brandNum+"]").attr("checked","checked");
											}
										} catch (e) {//TODO
										}
									}
								}else{
									UOMPComp.showFailedDialog("失败！", "");
								}
							}
						});
					}

				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
	}
};