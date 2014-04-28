//全局公共变量
GLOBAL_INFO =
{
    COMMON_REQ_URI: '/ecu_center_manage/actionDispatcher.do',
    CONTEXTPATH: '/ecu_center_manage',
    COMMON_SKIN: 'dhx_blue',
    SYS_FAILED: "1",
    SYS_SUCCESS: "0",
    PAGING_DEFAULT: 10
};

var UOMPComp =
{
    showTipDialog: function (msg, title) {
        top.UOMPDialog.alert(msg, "0", title);
    },
    showSuccessDialog: function (msg, title) {
        top.UOMPDialog.alert(msg, "1", title);
    },
    showFailedDialog: function (msg, title) {
        top.UOMPDialog.alert(msg, "2", title);
    },
    showConfirmDialog: function (msg, callback,yes,no) {
        top.UOMPDialog.confirm(msg, callback,yes,no);
    }
};

//页面请求对象
PageDynRequestInfo = function () {
};
PageDynRequestInfo.prototype =
{
    dynamicDataNodeName: '',
    dynamicURI: '',
    dynamicParameter: [],
    dynamicRequestCallback: null
};

//JSON格式化对象
var JSON =
{
    $defined: function (obj) {
        return (obj != undefined);
    },

    encode: function (obj) {
        switch (typeof obj) {
            case 'string':
                return '"' + obj.replace(/[\x00-\x1f\\"]/g, JSON.$replaceChars) + '"';
            case 'array':
                return '[' + String(obj.map(JSON.encode).filter(JSON.$defined)) + ']';
            case 'object':
                if (obj instanceof Array) {
                    //return '[' + String(obj.map(JSON.encode).filter(JSON.$defined)) + ']';
                    //return '{' + String(string) + '}';

                    var objStr = "";
                    for (var i = 0; i < obj.length; i++) {
                        var string = [];
                        for (var key in obj[i]) {
                            var json = JSON.encode(obj[i][key]);
                            if (json)
                                string.push(JSON.encode(key) + ':' + json);
                        }
                        objStr += '{' + String(string) + '},';
                    }
                    if (objStr.length > 0) {
                        objStr = objStr.substring(0, objStr.length - 1);
                        return '[' + objStr + ']';
                    }
                    else {
                        return '[]';
                    }
                }
                else {
                    var string = [];
                    for (var key in obj) {
                        var json = JSON.encode(obj[key]);
                        if (json)
                            string.push(JSON.encode(key) + ':' + json);
                    }
                    return '{' + String(string) + '}';
                }
            case 'number':
            case 'boolean':
                return String(obj);
            case false:
                return 'null';
        }
        return null;
    },

    $specialChars: {
        '\b': '\\b',
        '\t': '\\t',
        '\n': '\\n',
        '\f': '\\f',
        '\r': '\\r',
        '"': '\\"',
        '\\': '\\\\'
    },

    $replaceChars: function (chr) {
        return JSON.$specialChars[chr] || '\\u00' + Math.floor(chr.charCodeAt() / 16).toString(16) + (chr.charCodeAt() % 16).toString(16);
    },

    decode: function (string, secure) {
        if (typeof string != 'string' || !string.length)
            return null;
        if (secure && !(/^[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]*$/).test(string.replace(/\\./g, '@').replace(/"[^"\\\n\r]*"/g, '')))
            return null;
        return eval('(' + string + ')');
    },
    //将form表单转换为json对象
    form2json: function (form) {
        var json = {};
        if (form) {
            var data = $('#' + form).serializeArray();
            for (var i = 0; i < data.length; i++) {
                json[data[i].name] = data[i].value;
            }
        }
        return json;
    }
};

//封装AJAX请求
jQuery.extend({
    //多个请求
    multiReqs: (function () {
        var default_options = {
            "type": "post",
            "timeout": "40000",
            "contentType": "application/x-www-form-urlencoded; charset=UTF-8",
            "url": GLOBAL_INFO.COMMON_REQ_URI,
            "success": function (data) {
                alert("Ajax Success!");
            },
            "error": function (request, textStatus, errorThrown) {
            },
            "complete": function () {
            }
        };
        return function (dynamicReqs) {
            if (dynamicReqs && dynamicReqs.length) {
                var jsonRequestStr = JSON.encode(dynamicReqs);
                var user_options = {
                    data: {
                        "jsonParam": jsonRequestStr
                    }
                };
                var options = {};
                $.extend(options, default_options, user_options);
                options["success"] = function (data) {
                    var obj = JSON.decode(data);
                    for (var i = 0; i < dynamicReqs.length; i++) {
                        if (dynamicReqs[i].dynamicRequestCallback != null) {
                            dynamicReqs[i].dynamicRequestCallback(obj[dynamicReqs[i].dynamicDataNodeName]);
                        }
                    }
                };
                //发送请求
                $.ajax(options);
            }
        };
    })(),
    //单个请求
    singleReq: (function () {
        var default_options = {
            "type": "post",
            "timeout": "320000",
            "contentType": "application/x-www-form-urlencoded; charset=UTF-8",
            "url": GLOBAL_INFO.COMMON_REQ_URI,
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
            //发送请求
            $.ajax(options);
        };
    })(),
    /**************add by zhangel 2013-11-28 start****************/
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
    /**************add by zhangel 2013-11-28 end****************/
});

//DHTMLX工具方法
var DhtmlxUtis =
{
    /**
     ** 创建树对象
     ** treeObj 树对象ID或者实例
     ** type 0:主菜单树 1：内部页面中的树
     ** click 点击事件对应的方法
     **/
    createTree: function (treeObj, click, type) {
        var tree = new dhtmlXTreeObject(treeObj, "100%", "100%", 0);
        tree.setSkin(GLOBAL_INFO.COMMON_SKIN);
        var treeType = "csh_bluefolders/";
        if ("0" == type) {
            treeType = "csh_bluebooks/";
        }
        tree.setImagePath(GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/imgs/" + treeType);
        tree.enableDragAndDrop(0);
        tree.setOnClickHandler(click);
        //树的记忆请放开以下代码
//	    tree.attachEvent("onOpenEnd",function(){
//	    	 tree.saveOpenStates();
//	    });
        return tree;
    },

    createMenuTree: function (treeObj, frameTabbar, type) {
        //var tree = new dhtmlXTreeObject(treeObj, "100%", "100%", 0);
        treeObj.setSkin(GLOBAL_INFO.COMMON_SKIN);
        var treeType = "csh_bluefolders/";
        if ("0" == type) {
            treeType = "csh_bluebooks/";
        }
        treeObj.setImagePath(GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/imgs/" + treeType);
        treeObj.enableDragAndDrop(0);
        treeObj.attachEvent("onClick", function (id) {
            var title = treeObj.getItemText(id);
            var url = treeObj.getUserData(id, "url");
            var newid=treeObj.getUserData(id,"subNum")+"_"+id;
            if (url && url.length > 0) {
                if (!frameTabbar.cells(newid)) {
                    frameTabbar.addTab(newid, title, "*");
                    frameTabbar.setContentHref(newid, url);
                }
                frameTabbar.setTabActive(newid);
            }
            return true;
        });
        //tree.setOnClickHandler(click);
        return treeObj;
    },

    /**
     ** 创建Tabbar对象
     ** tabbarObj Tabbar对象ID
     **/
    createTabbar: function (tabbarObj, skin) {
        var tabbar = new dhtmlXTabBar(tabbarObj, "top");
        tabbar.setHrefMode("iframes-on-demand");
        tabbar.setSkin(skin != null ? skin : GLOBAL_INFO.COMMON_SKIN);
        tabbar.enableTabCloseButton(true);
        tabbar.setImagePath(GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
        tabbar.enableForceHiding(1);
        tabbar.enableAutoReSize(true, true);
        return tabbar;
    },

    /**
     ** 创建表格对象
     ** gridObj 表格对象ID
     **/
    createGrid: function (gridObj, options) {
        var mygrid = new dhtmlXGridObject(gridObj);
        mygrid.setImagePath(GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/imgs/");
        mygrid.setHeader(options["header"]);
        mygrid.setInitWidths(options["initWidth"]);
        mygrid.setColAlign(options["colAlign"]);
        mygrid.setColTypes(options["colTypes"]);
        mygrid.setColSorting(options["colSorting"]);
        mygrid.setSkin("dhx_skyblue");
        mygrid.colKeys = options["colKeys"];
        mygrid.init();
        return mygrid;
    },

    /**
     ** 获取grid数据
     ** gridObj 表格对象
     ** url 请求字符串
     ** params 请求参数
     *  isNeedSeqCol 第一列是否为序号列 true,false 默认为true，第一列为序号列
     **/
    loadGirdData: function (gridObj, params, isNeedSeqCol) {
        if (isNeedSeqCol == undefined || isNeedSeqCol == null) {
            isNeedSeqCol = true;
        }

        var colKeys = gridObj.colKeys;
        $.singleReq({
            data: params,
            success: function (ret) {
                if (ret.retCode == "0") {
                    if (ret.retObj) {
                        var jsonArr = eval("(" + ret.retObj + ")");
                        var datas = [];
                        for (var i = 0; i < jsonArr.length; i++) {
                            var data = [];
                            if (isNeedSeqCol) {
                                data.push(i + 1);
                            }
                            for (var j = 0; j < colKeys.length; j++) {
                                var key = colKeys[j];
                                var value = "";
                                if (key) {
                                    if (typeof key == "object") {
                                        var tempKey = key.key;
                                        value = key.formatter(jsonArr[i][tempKey] == undefined ? "" : jsonArr[i][tempKey]);
                                        data.push(value);
                                    }
                                    else {
                                        data.push(jsonArr[i][key] == null ? "" : jsonArr[i][key]);
                                    }
                                }
                            }
                            datas[i] = data;
                        }
                        gridObj.clearAll();
                        gridObj.parse(datas, "jsarray");
                    }
                }
            }
        });
    },

    /**
     ** 获取grid数据
     ** gridObj 表格对象
     ** url 请求字符串
     ** params 请求参数
     **  page  分页
     **/
    loadGirdPageData: function (gridObj, params) {
        var page = '';
        var colKeys = gridObj.colKeys;
        $.singleReq({
            data: params,
            async: false,
            success: function (ret) {
                if (ret.retCode == "0") {
                    if (ret.retObj) {

                        page = eval(ret.retObj);
                        var jsonArr = page.records;
                        var datas = [];
                        for (var i = 0; i < jsonArr.length; i++) {
                            var data = [i + 1];
                            for (var j = 0; j < colKeys.length; j++) {
                                var key = colKeys[j];
                                var value = "";
                                if (key) {
                                    if (typeof key == "object") {
                                        var tempKey = key.key;
                                        value = key.formatter(jsonArr[i][tempKey]);
                                        data.push(value);
                                    }
                                    else {
                                        data.push(jsonArr[i][key]);
                                    }
                                }
                            }
                            datas[i] = data;
                        }
                        gridObj.loadSizeFromCookie('column_width');
                        gridObj.clearAll();
                        gridObj.parse(datas, "jsarray");
                    }
                }
            }
        });

        return page;
    },

    createTableToolbar: function (toolbarObj, gridObj, options) {
        var btnName = {'add': '新增', 'modify': '修改', 'view': '查看', 'delete': '删除', 'clearMem': '清除缓存','generate':'生成','generateAll':'全部生成','generateStaticAll':'静态页面生成','generateProgram':'生成方案','recover':'恢复','sync':'短厅同步','import':'号码管理'};
        var toolBar = new dhtmlXToolbarObject(toolbarObj);
        var count = 0;
        for (var btn in options) {
            toolBar.addSeparator("sep" + count, count + 1);
            count++;
            toolBar.addButton(btn, count + 1, btnName[btn], (GLOBAL_INFO.CONTEXTPATH + "/resource/img/" + btn + ".gif"), "");
            count++;
        }
        toolBar.setIconSize(18);
        toolBar.attachEvent("onClick", function (id) {
            var url = options[id]["url"];
            var param = options[id]["param"];
            if (url) {
                if (param) {
                    var selectRowId = gridObj.getSelectedRowId();
                    if (!selectRowId) {
                        UOMPComp.showTipDialog("请选择一行！");
                        return;
                    }
                    var paramUrl = "";
                    for (var i = 0; i < param.length; i++) {
                        if (i > 0) {
                            paramUrl += "&";
                        }
                        paramUrl += param[i].name + "=" + encodeURI(gridObj.cells(selectRowId, parseInt(param[i].colIndex)).getValue());
                    }
                    if (url.indexOf("?") == -1) {
                        url = url + "?";
                        url = url + paramUrl;
                    }
                    else {
                        url = url + "&" + paramUrl;
                    }
                }
                top.UOMPDialog.showSubPage(options[id]["title"], url, options[id]["callback"], options[id]["size"]);
                return;
            }
            if (options[id]["click"]) {
                var selectRowId = gridObj.getSelectedRowId();
                if (!selectRowId  && id != 'generate' && id != 'generateAll' && id != 'generateStaticAll' && id != 'generateProgram'&& id != 'sync') {
                    UOMPComp.showTipDialog("请选择一行！");
                    return;
                }
                options[id]["click"]();
            }
        });
        return toolBar;
    },

    /**
     * 创建弹出层
     */
    createWindow: function (x, y, width, height) {
        var dhxWins;
        var w1;
        dhxWins = new dhtmlXWindows();
        dhxWins.attachViewportTo("winVP");
        dhxWins.vp.style.border = "#909090 1px solid";
        dhxWins.setImagePath(GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
        w1 = dhxWins.createWindow("w1", x, y, width, height);
        w1.button("park").hide();
        w1.center();
        return w1;
    }
};

var ValidateUtil = {
    validate: (function () {
        return function (user_options) {
            $("#" + user_options["targetForm"]).validate({
                errorPlacement: function (error, element) {
                    error.appendTo(element.next());
                },
                success: function (label) {
                },
                rules: user_options["rules"],
                messages: user_options["messages"]
            });
        };
    })()
};

//键盘事件
function getKeyCode(e) {
    var keyNum = 0;
    try {
        if (window.event) { // IE
            keyNum = e.keyCode;
        } else if (e.which) { // Netscape/Firefox/Opera
            keyNum = e.which;
        }

    } catch (ex) {
    }
    return keyNum;
}
//屏蔽Form提交事件
function returnEvent(e) {
    var flag = true;
    try {
        if (window.event) { // IE
            e.returnValue = false;
        } else { // Netscape/Firefox/Opera
            if (e.which != 8) {
                e.preventDefault();
            }
        }
    } catch (ex) {
    }
}
//去掉左右空格
function trimBlankFunc(str) {
    return str.replace(/^\s*|\s*$/g, "");
}

Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
