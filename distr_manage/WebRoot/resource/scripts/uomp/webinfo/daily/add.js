$(document).ready(function () {
	
	   var t = new dhtmlXCalendarObject(["beginTime"]);
});
var component = {
		
		   saveForm: function () {
    if (!$('#addForm').valid()) {
        return;
    }

    var id=$("#beginTime").val();
    var value=$("#value").val();
    var num=$("#num").val();
    var type = $("input[name='type']:checked").val();
    $.singleReq({
    	
        data: {
            "reqUrl": "dailyPaper",
            "reqMethod": "addDailyPaper",
            "id": id,
            "type": type,
            "num": num,
            "value": value
        },
        success: function (ret) {
            if (ret.retCode == 0) {
                UOMPComp.showSuccessDialog("添加成功！", "");
                top.UOMPDialog.subPageCallback && top.UOMPDialog.subPageCallback();
            }
            else {
                UOMPComp.showFailedDialog("添加失败！", "");
            }
        }
    });
}
}