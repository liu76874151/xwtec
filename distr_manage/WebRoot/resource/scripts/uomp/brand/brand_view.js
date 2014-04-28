$().ready(function() {
	component.viewForm();
});

var component = {
	viewForm : function() {
		$.singleReq( {
			data : {
				"reqUrl" : "brand",
				"reqMethod" : "queryOneBrand",
				"pkid" : $("#pkid").val()
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					$("#brandNum").text(result.brandNum);
					$("#brandName").text(result.brandName);
					$("#jbNum").text(result.jbNum);
					$("#jb").text(result.jb);
					$("#mj").text(result.mj=="0"?"否":(result.mj=="1"?"是":""));
					$("#memo").text(result.memo);
					$("#bossCode").text(result.bossCode);
				} else {
					alert(ret.retMsg);
				}
			}
		});
	}
}