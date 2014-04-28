$().ready(function() {
	component.viewForm();
});

var component = {
	viewForm : function() {
		$.singleReq( {
			data : {
				"reqUrl" : "businessInfoHandler",
				"reqMethod" : "queryBusiInfoBynum",
				"pkid" : $("#pkid").val()
			},
			success : function(ret) {
				if (ret.retCode == 0) {
					var result = ret.retObj;
					
					$("#busiName").val(result.busiName);
					$("#busisNum").val(result.busisNum);
					$("#busiNameJianpin").val(result.busiNameJianpin);
					
					try {
						//alert(result.busiEffectWayBeans[2].effectWay);
						for(var i=0;i<result.busiEffectWayBeans.length;i++){
							var busiOprtType = result.busiEffectWayBeans[i].busiOprtType;
							var effectWay = result.busiEffectWayBeans[i].effectWay;
							if(busiOprtType=="0"){
								var name = "#effectWay"+effectWay;
								$(name).attr("checked","checked");
							}
							if(busiOprtType=="1"){
								$("#neffectWay"+effectWay).attr("checked","checked");
							}
						}
					} catch (e) {
						alert("无开通、关闭 业务生效方式");
					}
					$("#state").val(result.state);
					
				} else {
					alert(ret.retMsg);
				}
			}
		});
	}
}