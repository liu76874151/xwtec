
var component = {
saveForm: function (channelNum) {
	try {
			var busiInfoList = new Array();
             if ($.trim($("#relaBusiInfoJson"+channelNum,opener.document).val())!="") {
	             busiInfoList = $.parseJSON($("#relaBusiInfoJson"+channelNum,opener.document).val());
             }
			$('#selectBusiInfo option').each(function () {
				var flag = true;
				 var element = {};
	             var relativeBusi = $(this).val(); //获取单个value
	             var busiName = $(this).attr("busiName");
	             
				for (var index = 0; index < busiInfoList.length; index++) {
					var num = busiInfoList[index].relativeBusi;
					if (num==relativeBusi) {
						flag = false;
					}
				}
				
				if (flag) {
					var id = relativeBusi +"_" + channelNum;
		             var comment  = "<span id='"+id+"'>"+busiName+"<a href=\"javascript:removeRela('"+relativeBusi+"','"+channelNum+"');\">" +
		             		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
		             $("#businessReleList_"+channelNum,opener.document).append(comment);
		             element.relativeBusi = relativeBusi;
					 element.channelNum = channelNum;
					 element.busiName = busiName;
		             busiInfoList.push(element);
				}
	             
	         });
	        var json = $.toJSON(busiInfoList);
			$("#relaBusiInfoJson"+channelNum,opener.document).val(json);
			if (busiInfoList.length>0) {
				$("#releCityList_"+channelNum,opener.document).parent().css({display:""});
				$("#releBrandList_"+channelNum,opener.document).parent().css({display:""});
			}
			window.close();
		} catch (e) {
			return "";
		}
},
getBusiInfo : function(channelNum){
	try {
		var busiInfoList = new Array();
		$('#selectBusiInfo option').each(function () {
			 var element = {};
             var code = $(this).val(); //获取单个value
			 element.relativeBusi = code;
			 element.channelNum = channelNum;
			 element.busiName = $(this).attr("busiName");
			 busiInfoList.push(element);
         });
		return  busiInfoList;
	} catch (e) {
		return "";
	}
	
},
queryBusiInfo: function(start, end,channelNum){
	var busiName = $("#busiInfoName_"+channelNum).val();
	  if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 100;
		}
		$.singleReq( {
			data : {
				"reqUrl" : "businessInfoHandler",
				"reqMethod" : "queryBusiInfoList",
				"channelNum":channelNum,
				"busiName":busiName,
				"state": '0',
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj.records;
					$("#busiInfoList").empty();
					for ( var int = 0; int < result.length; int++) {
						var busiName = result[int].busiName;
						var busiNum = result[int].busiNum;
						var option = "<option value='"+busiNum+"' busiName='"+busiName+"'>"+busiName;
						$("#busiInfoList").append(option);
					}
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
},
	addBusiInfo : function(channelNum) {
		var option = $("#busiInfoList option:selected");
		$("#selectBusiInfo").append(option);

	},
	removeBusiInfo : function(channelNum) {
		// selectBusiness
		var option = $("#selectBusiInfo option:selected");
		$("#busiInfoList").append(option);
	}
};