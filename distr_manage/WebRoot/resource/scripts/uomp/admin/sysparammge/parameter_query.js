$(document)
		.ready(
				function() {
					mygrid = new dhtmlXGridObject('mygrid_container');
					mygrid
							.setImagePath(GLOBAL_INFO.CONTEXTPATH
									+ "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/imgs/");
					//设置第一行
					mygrid
							.setHeader("<a href='${contextPath}/page/admin/sub_system_add.jsp' target='_self'>添加</a>&nbsp;&nbsp;<a href='javascript:window.location.reload();' class='btn refresh'>刷新</a>,#cspan,#cspan,#cspan,#cspan,#cspan,#cspan");
					mygrid.attachHeader("序号,子系统编码,子系统名称,参数名称,参数值,备注,操作");
					mygrid.setInitWidths("80,80,120,120,120,*,80");
					mygrid
							.setColAlign("center,center,center,center,center,center,center");
					mygrid.setColTypes("ro,ro,ro,ro,ro,ro,link");
					mygrid.setColSorting("str,str,str,str,str,str,str");
					mygrid.setSkin("dhx_skyblue");
					mygrid.init();
					loadDate("", "");
				});
function loadDate(subsysname, csname) {
	$.singleReq( {
		data : {
			"subsysname" : subsysname,
			"csname" : csname,
			"reqUrl" : "sysparmmage",
			"reqMethod" : "query"
		},
		success : function(ret) {
			var jsonArr = eval("(" + ret.retObj + ")");
			var datas = [];
			for ( var i = 0; i < jsonArr.length; i++) {
				var subSysNum = jsonArr[i].subSysNum;
				var subSysName = jsonArr[i].subSysName;
				var csmc = jsonArr[i].csmc;
				var csz = jsonArr[i].csz;
				var bz = jsonArr[i].bz;
				var data = [ i + 1, subSysNum, subSysName, csmc, csz, bz,
						"修改^page/admin/sysparammge/parameter_edit.jsp^_self" ];
				datas[i] = data;
			}
			mygrid.clearAll();
			mygrid.parse(datas, "jsarray");
		}
	});
}
