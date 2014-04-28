$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
});

/**
 * 用户查询
 */
var component = {

    /**
     * 创建datagrid对象
     *    
     */
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,渠道编码,站点状态,应急公告标题,应急公告",
            "initWidth": "120,120,120,200,*",
            "colAlign": "center,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str",
            "colKeys": ["channelNum", {
                "key": "state",
                "formatter": function (v) {
                    if (v == "1") {
                        return "应急";
                    }
                    else if (v == "0") {
                        return "开启";
                    }else if(v=='2'){
                    	 return "测试";
                    }
                }
            },
              "title", "notice"]
        });

        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
    },

    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            
            "modify": {
                "title": "网站基本信息-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/webinfo/edit.jsp",
                "param": [
                    {"name": "channelNum", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            }
        });
    },

    /**
     * 查询
     */
    query: function (start, end) {
    	var channelNum = $("#channelNum").val();
        var state = $("input[name=state]:checked").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
          
            "reqUrl": "webinfo",
            "reqMethod": "queryWebInfoList",
            "channelNum":channelNum,
            "state":state,
            "start": start,
            "end": end
        };

        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
        component.paging.setTotalPage(page.totalRecord);
        component.paging.refresh(start, end);
    }
}