$(document).ready(function () {
    memInfoVComponent.init();
    memInfoVComponent.getTypeName();

});

var memInfoVComponent = {
    //初始化
    init: function () {
        //调用后台方法，获取数据
        this.drawGrid();
        this.drawToolBar();
        this.getData();

    },

    //初始化表格
    drawGrid: function () {
        cacheServiceGrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,缓存编码,缓存服务器类型,缓存类型编码,Memcached服务器列表,Redis服务器列表",
            "initWidth": "50,200,120,200,200,*",
            "colAlign": "center,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str"
        });
    },
    /**
     * 创建toolBar对象
     */
    drawToolBar: function () {
        var cacheServiceToolBar = DhtmlxUtis.createTableToolbar('toolbar', cacheServiceGrid, {
            "add": {
                "title": "缓存服务-新增",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_service_info_add.jsp",
                "callback": function () {
                    window.location.reload();
                }
            },

            "view": {
                "title": "缓存服务-查看",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_service_info_view.jsp",
                "param": [
                    {"name": "selectRowId", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },

            "modify": {
                "title": "缓存服务-修改",
                "url": GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_service_info_edit.jsp",
                "param": [
                    {"name": "selectRowId", "colIndex": "1"}
                ],
                "callback": function () {
                    window.location.reload();
                }
            },


            "delete": {"url": "", "click": function () {

                UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该缓存服务吗？点击“取消”则返回！", {
                    yes: function () {

                        var selectRowId = cacheServiceGrid.getSelectedRowId();
                        var id = cacheServiceGrid.cells(selectRowId, 1).getValue();
                        $.singleReq({
                            data: {
                                "id": id,
                                "reqUrl": "cacheService",
                                "reqMethod": "deleteCacheService"
                            },
                            success: function (ret) {
                                if (ret.retCode == 0) {
                                    UOMPComp.showSuccessDialog(ret.resMsg, "");
                                    memInfoVComponent.getData();
                                    ;
                                }
                                else {
                                    UOMPComp.showFailedDialog(ret.resMsg, "");
                                }

                            }
                        });

                    }
                });
            }},

            "clearMem": { "title": "", "url": "", "click": function () {
                var selectRowId = cacheServiceGrid.getSelectedRowId();
                var id = cacheServiceGrid.cells(selectRowId, 1).getValue();
                memInfoVComponent.cleanCache(id);
            }}


        });
    },

    //调用后台方法，获取数据
    getData: function () {
        $.singleReq({
            data: {
                "reqUrl": "cacheService",
                "reqMethod": "queryCacheServiceList",
                "num": $("input[name=num]").val(),
                "typeNum": $("#typeNum").find("option:selected").val()
            },
            success: function (ret) {
                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                    var resMsg = ret.resMsg;
                    var retCode = ret.retCode;
                    if (GLOBAL_INFO.SYS_FAILED == retCode) {
                        UOMPComp.showFailedDialog(ret.resMsg, "");
                    } else {
                        var jsonArr = eval("(" + ret.retObj + ")");
                        var datas = [];
                        for (var i = 0; i < jsonArr.length; i++) {
                            var serverType = jsonArr[i].serverType;
                            var num = jsonArr[i].num;
                            var typeNum = jsonArr[i].typeNum;
                            var memcached_servers = jsonArr[i].servers;
                            var redHost = jsonArr[i].redHost;
                            var redPort = jsonArr[i].redPort;
                            var redis_server = redHost;
                            if (redPort != undefined) {
                                redis_server += ":" + redPort;
                            }
                            var data = [ i + 1, num, serverType, typeNum, memcached_servers, redis_server ];
                            datas[i] = data;
                        }
                        cacheServiceGrid.clearAll();
                        cacheServiceGrid.parse(datas, "jsarray");
                    }
                }
            }
        });
    },
    //删除缓存服务
    deleteCacheService: function (selectRowId) {
        if (confirm("确定要删除数据吗？")) {
            $.singleReq({
                data: {
                    "reqUrl": "cacheService",
                    "reqMethod": "deleteCacheService",
                    "selectRowId": selectRowId
                },
                success: function (ret) {
                    memInfoVComponent.getData();
                }
            });
        }
    },
    //清除单个缓存缓存
    cleanCache: function (selectRowId) {
        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定清除该缓存吗？点击“取消”则返回！111111", {
            yes: function () {
                $.singleReq({
                    data: {
                        "reqUrl": "cacheService",
                        "reqMethod": "cleanCache",
                        "selectRowId": selectRowId,
                        "isCleanAllFlag": "0"
                    },
                    success: function (ret) {
                        if (ret) {
                            if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                var resMsg = ret.resMsg;
                                var retCode = ret.retCode;
                                if (GLOBAL_INFO.SYS_FAILED == retCode) {
                                    UOMPComp.showFailedDialog(ret.resMsg, "");
                                } else {
                                    UOMPComp.showSuccessDialog(ret.resMsg, "");
                                }
                            }
                        }
                    }
                });
            }
        });
    },

    cleanCacheAll: function () {
        UOMPComp.showConfirmDialog("【系统提示】\n\n您确定清除所有缓存吗？点击“取消”则返回！", {
            yes: function () {

                $.singleReq({
                    data: {
                        "reqUrl": "cacheService",
                        "reqMethod": "cleanCache",
                        "isCleanAllFlag": "1"
                    },
                    success: function (ret) {
                        if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                            var resMsg = ret.resMsg;
                            var retCode = ret.retCode;
                            if (GLOBAL_INFO.SYS_FAILED == retCode) {
                                UOMPComp.showFailedDialog(ret.resMsg, "");
                            } else {
                                UOMPComp.showSuccessDialog(ret.resMsg, "");
                            }
                        }
                    }
                });
            }
        });
    },


    getTypeName: function () {
        $.singleReq({
            data: {
                "reqUrl": "cacheTypeManage",
                "reqMethod": "queryCacheTypeManageInfoList"

            },
            success: function (ret) {
                var retObjTemp = eval("(" + ret.retObj + ")");

                for (var index = 0; index < retObjTemp.length; index++) {

                    var typeName = retObjTemp[index].typeName;
                    var typeNum = retObjTemp[index].typeNum;

                    $("#typeNum").append("<option value='" + typeNum + "' >" + typeName + "</option>");
                }

            }
        });
    }

}