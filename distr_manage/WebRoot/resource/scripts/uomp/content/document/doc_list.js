$(document).ready(function () {
    component.initDataGrid();
    component.initToolBar();
});

/**
 * 用户查询
 */
var component = {
		
		updateState:function(id,state){
	
	UOMPComp.showConfirmDialog("【系统提示】\n\n您确定修改状态吗？点击“否”则返回！", {
        "yes": function () {
            $.singleReq({
                data: {
                    "reqUrl": "contentDoc",
                    "reqMethod": "updateStateBydocId",
                    "state": state,
                    "docId": id
                },
                success: function (ret) {
                    if (ret) {
                        if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                            var resMsg = ret.resMsg;
                            var retCode = ret.retCode;
                            UOMPComp.showSuccessDialog("操作成功!", "");
                            if (GLOBAL_INFO.SYS_FAILED == retCode) {

                            } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                window.location.reload();
                            }
                        } else {
                            if (ret.resMsg) {
                                UOMPComp.showFailedDialog("系统异常", "");
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

    /**
     * 创建datagrid对象
     *    
     */
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,hiddenDocId,hiddenChanId,内容类型,摘要,标题,作者,撰写日期,状态,操作",
            "initWidth": "80,0,0,60,100,260,80,120,50,*",
            "colAlign": "center,left,left,left,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,str,str,str,str,str",
            "colKeys": [ "docId","chanId",{
                "key": "type",
                "formatter": function (v) {
                   if (v == "1") {
                        return "文章";
                    }
                    else if (v == "2") {
                        return "下载";
                    }
                    else if (v == "3") {
                        return "链接";
                    }                  
                    else if (v == "4") {
                        return "专区";
                    }
                    
                }
            },  "summary","title","author","writeTime",
                {
                    "key": "state",
                    "formatter": function (v) {
                       if (v == "0") {
                            return "新稿";
                        }
                        else if (v == "1") {
                            return "发布";
                        }
                        else if (v == "2") {
                            return "不可用";
                        }
                        
                    }
                },"stateLink"]
        });

        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
    },

    /**
     * 创建toolBar对象
     */
    initToolBar: function () {
    	var type= $("#type").val();
    	var chanId =$("#chanId").val();
        var toolbar = DhtmlxUtis.createTableToolbar('toolbar', datagrid, {
            "add": {
                "title": "内容-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/content/document/doc_add.jsp?type="+type+"&chanId="+chanId,
                "callback": function () {
                    window.location.reload();
                }
            },

           

            "modify": {
                "title": "内容--修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/content/document/doc_edit.jsp",
                "param": [
                    {"name": "pkid", "colIndex": "1"},{"name": "chanId", "colIndex": "2"}
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
        var title = $("#title").val();
        var author = $("#author").val();
        var chanId =$("#chanId").val();
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
            "title": title,
            "author": author,
            "chanId": chanId,
            "reqUrl": "contentDoc",
            "reqMethod": "queryContentDocs",
            "start": start,
            "end": end
        };

        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
        component.paging.setTotalPage(page.totalRecord);
        component.paging.refresh(start, end);
    },

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
                            "reqUrl": "contentDoc",
                            "reqMethod": "deleteBydocId",
                            "state": "2",
                            "pkid": pkid
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog("操作成功!", "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog("系统异常", "");
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