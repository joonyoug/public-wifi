<%@ page import="api.InsertAPI" %><%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2024/03/04
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <%
        InsertAPI insertAPI=new InsertAPI();
        int count= insertAPI.wifiLoadBatch();


    %>
    <h2 style="text-align: center"><%= count %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
    <a href="index.jsp" style=" display: block; text-align: center">홈으로 가기</a>


</body>
</html>
