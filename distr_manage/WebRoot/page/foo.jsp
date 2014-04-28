<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="taglibs.jsp" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
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
        var default_options = {
            "type": "post",
            "timeout": "320000",
            "contentType": "application/x-www-form-urlencoded; charset=UTF-8",
            "url": "/ecu_center_manage/actionDispatcher.do",
            "dataType": "json",
            "data": data,
            "success": function (data) {
                alert("Ajax Success!");
            },
            "error": function (request, textStatus, errorThrown) {
            },
            "complete": function () {
            }
        };
        console.log(default_options)
        $.ajax(default_options);
    }
</script>
</html>