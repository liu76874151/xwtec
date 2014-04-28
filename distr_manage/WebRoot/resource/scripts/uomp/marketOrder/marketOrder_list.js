$(document).ready(function () {
	var t = new dhtmlXCalendarObject(["beginTime","endTime"]);
   component.initDataGrid();
   component.initCityArea();
});

/**
 * 营销案订单查询
 */
var component = {
		//--导出查询结果
marketOrderToExcel:function(){
	var sqlObj={};
   var sql=$("#sql").val();
	if(sql!=""){
		var sqlArr=sql.split("|");
		for ( var i = 0; i < sqlArr.length; i++) {
			eval(sqlArr[i]);
		}
	}
	   var url="/ecu_center_manage/actionDispatcher.do?" +
  		"reqUrl=marketOrder&reqMethod=marketOrderToExcel&start=0&end=99999";
	   for ( var p in sqlObj ){ 
		   url+="&"+p+"="+sqlObj[p];   
	    } 
	  window.location.href=url;

	
},
		/**
		 * 初始化地市
		 */
		initCityArea:function(){
			$.singleReq({
		        data: {
				    	"reqUrl": "area",
				        "reqMethod": "queryCityList"
				     
		        },
		        success: function (ret) {
		            if (ret.retCode == 0) {
		            	var result = ret.retObj.records; 
		                $.each(result, function(i, item) {
		               	 $("#city").append("<option value="+item.areaBossCode+">"+item.areaName+"</option>");
		                    
		                });
							}
		        }
		    });
		},
addhiddenSql:function(){
			var sql="";
			var name=["city","marketFirstName","marketSecondName","beginTime","endTime","type","telNum","PayStatus","payType","brandId","channelType"];
		for(index in arguments){
			if(arguments[index]!=undefined&&arguments[index]!=null&&arguments[index]!=""){
				sql+="sqlObj."+name[index]+"='"+arguments[index]+"'|";
			}
		}
		if(sql.length>0){
			sql=sql.substring(0,sql.length-1);}
		return sql;
		},
checkboxValFormat:function(name){
	var value="";
    $("input[name='"+name+"']:checked").each(function () {
    	value=value+this.value+",";
});
if(value.length>0){
	value=value.substring(0,value.length-1);}
	return value;
},

    /**
     * 创建datagrid对象
     */
		
    initDataGrid: function () {
        datagrid = DhtmlxUtis.createGrid('gridbox', {
            "header": "序号,城市,一级营销案,二级营销案,订单编号,手机号,品牌,充值金额,支付方式,订单状态,失败原因",
            "initWidth": "80,130,160,160,80,80,80,80,80,80,*",
            "colAlign": "center,left,left,left,left,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,str,str,str,str,str,str",
            "colKeys": ["cityName", "marketFirstName","marketSecondName","marketOrderId", 
                        "telNum","brandId" ,"payVal","payName",{
			                "key": "currState",
			                "formatter": function (v) {
			                    if (v == "0") {
			                        return "未完成";
			                    }
			                    else if (v == "1") {
			                        return "成功";
			                    }
			                    else if (v == "2") {
			                        return "无法支付";
			                    }
			                    else if (v == "9") {
			                        return "失败";
			                    }
			                }
			            },""
               ]
        });
        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
   
},
    /**
     * 查询
     */
    query: function (start, end) {
		 var city ;
	     if($("#city").val()!=0){
	     	city = $("#city").val();  
	     }   
        var marketFirstName = $("#marketFirstName").val();
        var marketSecondName = $("#marketSecondName").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();
        var type = $("#type").val();
        var telNum = $("#telNum").val();
        var PayStatus=component.checkboxValFormat('PayStatus');
        var payType=component.checkboxValFormat('payType');
        var brandId=component.checkboxValFormat('brandId');
        var channelType=component.checkboxValFormat('channelType');//--4网厅数据 ，5掌厅数据，6短厅数据
        var sql=component.addhiddenSql(city,marketFirstName,marketSecondName,beginTime,endTime,type,telNum,PayStatus,payType,brandId,channelType);
        $("#sql").val(sql);
        if (start == undefined) {
            start = 0;
        }
        if (end == undefined) {
            end = 10;
        }
        var data =
        {
        	"city":city,
        	"marketFirstName":marketFirstName,
        	"marketSecondName":marketSecondName,
        	"beginTime":beginTime,
        	"endTime":endTime,
        	"type":type,
        	"telNum":telNum,
        	"PayStatus":PayStatus,
        	"payType":payType,
        	"brandId":brandId,
        	"channelType":channelType,
            "reqUrl": "marketOrder",
            "reqMethod": "queryMarketOrderList",
            "start": start,
            "end": end
        };

        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
        component.paging.setTotalPage(page.totalRecord);
        component.paging.refresh(start, end);
    }

	    }
