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
            "header": "序号,chanId,专区名称,父级专区,渠道,专区描述,操作",
            "initWidth": "80,0,120,120,120,320,*",
            "colAlign": "center,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "int,str,str,str,str,str,str",
            "colKeys": ["chanId","showName","parentName",{
                "key": "channelNum",
                "formatter": function (v) {
            	var chanNum=["01","02","0201","0202","0203","0204","03"];
            	var chanName=["网厅","掌厅","掌厅普版","掌厅标准版","掌厅触屏版","掌厅APP","短厅"];
            	var index=$.inArray(v, chanNum);
                 return chanName[index];
                }
            },"desc","v"]
        });
        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
    },

    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
    	var chanId=$("#hiddenChanId").val();
    	var parentId=$("#hiddenParentId").val();
    	var channelNum=$("#hiddenChannelNum").val();
    	var type=$("#hiddenType").val();
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "一级营销案-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/content/channel/channel_add.jsp?channelNum="+
                								 channelNum+"&chanId="+chanId+"&parentId="+parentId+"&type="+type,
                "callback": function () {
                    window.location.reload();
                }
            },


            "modify": {
                "title": "一级营销案-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/content/channel/channel_edit.jsp",
                "param": [
                    {"name": "pkid", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },

            "delete": {
                "url": "",
          "click": this.delete
            }
        });
    },

    /**
     * 查询
     */
    query: function (start, end) {
    	var chanId=$("#hiddenChanId").val();
    	var channelNum=$("#hiddenChannelNum").val();
    	var state=1;      
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
            "state":state,
            "parentId":chanId,
            "channelNum":channelNum,
            "reqUrl": "content",
            "reqMethod": "queryAllChannels",
            "start": start,
            "end": end
        };

        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
        component.paging.setTotalPage(page.totalRecord);
        component.paging.refresh(start, end);
    } ,

    /**
     * 删除
     */
    delete: function () {
        var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
        if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "content",
                            "reqMethod": "updateByPrimaryKey",
                            "chanId": pkid,
                            "state": "1"
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
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
        } else {
            UOMPComp.showTipDialog("请选择行", "");
        }
    }
}