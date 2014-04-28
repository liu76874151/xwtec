<%--
  Created by IntelliJ IDEA.
  User: alabo
  Date: 13-8-21
  Time: 上午11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/page/taglibs.jsp" %>
<html>
<head>
    <title></title>
    <script src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript" type="text/javascript"></script>
</head>
<%
    String user_agent = request.getHeader("User-Agent");
    System.out.println("user_agent = " + user_agent);
    out.println("user_agent = " + user_agent);
%>
<body>
<div>
    <table>
        <tr>
            <td id="a"></td>
        </tr>
    </table>

</div>
</body>
<script>

    $("#a").html("hellggggggggggggggggggg");
</script>
</html>