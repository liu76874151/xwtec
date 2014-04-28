<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="taglibs.jsp" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="ext-all.js"></script>
</head>
<body>
<input type="button" name="test" value="test" onclick="send()">
</body>
<script>

    function send() {
        var data = {
            reqUrl:"pageCompInfo",
            reqMethod:"savePageCompInfo",
            name:"lee",
            age:32,
            sex:"female",
            aaa:[
                {
                    school:"no5",
                    period:"1998-2001"
                },
                {
                    school:"no5",
                    period:"1995-1998"
                }
            ]
        };
        console.log(data);
        Ext.Ajax.request({
            method:"post",
            url:"http://127.0.0.1:8080/ecu_center_manage/actionDispatcher.do",
            jsonData:data,
            success:function (resp, opts) {
                console.log(resp);
                //res.innerHTML = resp.responseText;
            },
            failure:function (resp, opts) {
                //res.innerHTML = "failure";
            }
        });
    }
</script>
</html>