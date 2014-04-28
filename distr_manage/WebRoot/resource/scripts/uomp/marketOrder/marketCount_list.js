$(document).ready(function () {
	var t = new dhtmlXCalendarObject(["beginTime","endTime"]);
   component.initDataGrid();
   component.initCityArea();
   component.initmarketFirst();
});

/**
 * 营销案订单查询
 */
var component = {
		//--导出查询结果
		marketCountToExcel:function(){ 
			var sqlObj={};
		   var sql=$("#sql").val();
			if(sql!=""){
				var sqlArr=sql.split("|");
				for ( var i = 0; i < sqlArr.length; i++) {
					eval(sqlArr[i]);
				}
			}
			   var url="/ecu_center_manage/actionDispatcher.do?" +
		  		       "reqUrl=marketOrder&reqMethod=marketCountToExcel&start=0&end=99999";
			   for ( var p in sqlObj ){ 
				   url+="&"+p+"="+sqlObj[p];     
			    }
			  window.location.href=url;
		},
		
initmarketSecod:function(){
	var marketFirstPkid=$("#marketFirstPkid").val();
	$.singleReq({
        data: {
		"reqUrl" : "marketSecond",
		"reqMethod" : "queryMarketSecondList",
		"marketFirstPkid" : marketFirstPkid,
        "state":"1",
        "start": 0,
        "end": 9999
		     
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	$("#marketSecondPkid").empty().append("<option selected value=''   >--请选择--</option>");
            	var result = ret.retObj.records; 
                $.each(result, function(i, item) {
               	 $("#marketSecondPkid").append("<option value="+item.marketSecondPkid+">"+item.marketSecondName+"</option>");
                    
                });
					}
        }
    });
},
		initmarketFirst:function(){
	$.singleReq({
        data: {
		"reqUrl": "marketFirst",
        "reqMethod": "queryMarketFirstList",
        "state":"1",
        "start": 0,
        "end": 9999
		     
        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	var result = ret.retObj.records; 
                $.each(result, function(i, item) {
               	 $("#marketFirstPkid").append("<option value="+item.marketFirstPkid+">"+item.marketFirstName+"</option>");
                    
                });
					}
        }
    });
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
			var name=["city","marketFirstPkid","marketSecondPkid","beginTime","endTime","type","brandId","channalData"];
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
            "header": "序号,城市,一级营销案,二级营销案,开始时间,结束时间,总办理数,成功数,失败数,成功金额",
            "initWidth": "80,130,160,160,120,120,80,80,80,*",
            "colAlign": "center,left,left,left,left,left,left,left,left,left",
            "colTypes": "ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro",
            "colSorting": "str,str,str,str,str,str,str,str,str,str",
            "colKeys": ["city", "marketFirstName","marketSecondName","beginTime", 
                        "endTime" ,"count","success","fail","val"
               ]
        });
        this.paging = new dhtmlxGridPaging('paging');
        this.paging.initPaging(component.query);
   
},
    /**
     * 查询
     */
    query: function (start, end) {
        var city = $("#city").val();  
        var marketFirstPkid= $("#marketFirstPkid").val();
        var marketSecondPkid = $("#marketSecondPkid").val();
        var beginTime = $("#beginTime").val();
        var endTime = $("#endTime").val();
        var type = $("#type").val();
        var brandId=component.checkboxValFormat('brandId');
        var channalData=component.checkboxValFormat('channelType');//--4网厅数据 ，5掌厅数据，6短厅数据
        var sql=component.addhiddenSql(city,marketFirstPkid,marketSecondPkid,beginTime,endTime,type,brandId,channalData);
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
        	"marketFirstPkid":marketFirstPkid,
        	"marketSecondPkid":marketSecondPkid,
        	"beginTime":beginTime,
        	"endTime":endTime,
        	"type":type,
        	"brandId":brandId,
        	"channalData":channalData,
            "reqUrl": "marketOrder",
            "reqMethod": "queryMarketOrderCountList",
            "start": start,
            "end": end
        };

        var page = DhtmlxUtis.loadGirdPageData(datagrid, data);
        component.paging.setTotalPage(page.totalRecord);
        component.paging.refresh(start, end);
    }

	    }
