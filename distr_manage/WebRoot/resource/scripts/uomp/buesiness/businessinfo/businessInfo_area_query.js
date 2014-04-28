$().ready(function(){
	component.queryAreaList(0,500);
});


var component = {
saveForm: function (channelNum) {
	if (channelNum==undefined || $.trim(channelNum)=="") {
		channelNum = "";
	}
	try {
			var areaInfoList = new Array();
             if ($.trim($("#busiAreaJson"+channelNum,opener.document).val())!="") {
	             areaInfoList = $.parseJSON($("#busiAreaJson"+channelNum,opener.document).val());
             }
			$('#selectAreaInfo option').each(function () {
				var flag = true;
				 var element = {};
	             var areaNum = $(this).val(); //获取单个value
	             var areaName = $(this).attr("areaName");
	             
				for (var index = 0; index < areaInfoList.length; index++) {
					var num = areaInfoList[index].areaNum;
					if (num==areaNum) {
						flag = false;
					}
				}
				
				if (flag) {
					var id = areaNum + channelNum;
		             var comment  = "<span id='"+id+"'>"+areaName+"<a href=\"javascript:removeArea('"+areaNum+"','"+channelNum+"');\">" +
		             		"<img title='删除' src='../../../resource/img/toggle_disabled.gif' height='15'></a>&nbsp;&nbsp;</span>";
		             $("#cityList"+channelNum,opener.document).append(comment);
		             element.areaNum = areaNum;
					 element.areaName = areaName;
					 if (channelNum!="") {
						 element.channelNum = channelNum;
					 }
		             areaInfoList.push(element);
				}
	             
	         });
	        var json = $.toJSON(areaInfoList);
			$("#busiAreaJson"+channelNum,opener.document).val(json);
			window.close();
		} catch (e) {
			return "";
		}
},
getBusiInfo : function(channelNum){
	try {
		var areaInfoList = new Array();
		$('#selectAreaInfo option').each(function () {
			 var element = {};
             var code = $(this).val(); //获取单个value
			 element.areaNum = code;
			 element.areaName = $(this).attr("areaName");
			 areaInfoList.push(element);
         });
		return  areaInfoList;
	} catch (e) {
		return "";
	}
	
},
queryAreaList : function(start, end) {//地市查询
		if (start == undefined) {
			start = 0;
		}
		if (end == undefined) {
			end = 10;
		}
			
		$.singleSync( {
			data : {
				"reqUrl" : "area",
				"reqMethod" : "findAreaList",
				"start" : start,
				"end" : end
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					$("#areaInfoList").empty();
					for ( var int = 0; int < result.length; int++) {
						var areaName = result[int].areaName;
						var areaNum = result[int].areaNum;
						var option = "<option value='"+areaNum+"' areaName='"+areaName+"'>"+areaName;
						$("#areaInfoList").append(option);
					}
				} else {
					UOMPComp.showFailedDialog("失败！", "");
				}
			}
		});
},
	addAreaInfo : function(channelNum) {
		var option = $("#areaInfoList option:selected");
		$("#selectAreaInfo").append(option);

	},
	removeAreaInfo : function(channelNum) {
		// selectBusiness
		var option = $("#selectAreaInfo option:selected");
		$("#areaInfoList").append(option);
	}
};