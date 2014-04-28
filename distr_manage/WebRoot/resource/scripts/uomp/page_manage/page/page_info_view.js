$(document).ready(function () {
        component.viewForm();
    }
);

var component = {
    viewForm: function () {
        $.singleReq({
            data: {
                "reqUrl": "pageInfo",
                "reqMethod": "findOnePageInfo",
                "pageNum": $("#pkid_1").val(),
                "version": $("#pkid_2").val()
            },
            success: function (ret) {
                if (ret.retCode == 0) {
                    var result = ret.retObj;
                    $("#pageNum").text(result.pageNum);
                    $("#pageName").text(result.pageName);

                    var version = result.version;
                    if(version == "2"){
                        version = "标准版";
                    }else if(version == "3"){
                        version = "触屏版";
                    }
                    $("#version").text(version);

                    $("#pageContent").text(result.pageContent);

                    var confirmComp = result.confirmComp;
                    if(confirmComp == ""){
                        confirmComp = "否";
                    }else if(confirmComp == "confirmComp"){
                        confirmComp = "是";
                    }
                    $("#confirmComp").text(confirmComp);

                    var resultComp = result.resultComp;
                    if(resultComp == ""){
                        resultComp = "否";
                    }else if(resultComp == "resultComp"){
                        resultComp = "是";
                    }
                    $("#resultComp").text(resultComp);

                    var type = result.type;
                    if(type == "0"){
                        type = "普通";
                    }else if(type == "1"){
                        type = "模板";
                    }
                    $("#type").text(type);

                    $("#desc").text(result.desc);
                }
                else {
                    alert(ret.retMsg);
                }
            }
        });
    }
}
