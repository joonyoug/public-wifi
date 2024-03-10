<%@ page import="db.WifiHistory" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Wifi" %>
<%@ page import="db.WifiHistoryService" %>
<%@ page import="db.WifiService" %><%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2024/03/04
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
    <h2>위치 히스토리 목록</h2>
</head>
<body>

    <div>
        <a href="index.jsp">홈 | </a>
        <a href="history.jsp">위치 히스토리 목록 | </a>
        <a href="openapi.jsp">Open Api 와이파이 정보 가져오기 | </a>
        <a href="bookMarkList.jsp">북마크 보기 | </a>
        <a href="bookMarkGroup.jsp">북마크 그룹 관리</a>
    </div>

    <%
        String historyId=request.getParameter("wifiHistoryId") ==null?"-1":request.getParameter("wifiHistoryId");
    %>

<br>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>x좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <%
                WifiHistoryService wifiHistoryService=new WifiHistoryService();
                if(!historyId.equals("-1")){
                    wifiHistoryService.deleteWifiHistory(historyId);
                }
                List<WifiHistory> wifiHistoryList= wifiHistoryService.getWifiHistory();
                for(WifiHistory wifiHistory:wifiHistoryList){

            %>
            <tr>
                <td><%=wifiHistory.getId()%></td>
                <td><%=wifiHistory.getLAT()%></td>
                <td><%=wifiHistory.getLNT()%></td>
                <td><%=wifiHistory.getWORK_DTTM()%></td>
                <td><button class="btn_deleteHistoryId" data-wifiHistoryId="<%= wifiHistory.getId()%>">삭제</button></td>
            </tr>
        <%  }
                %>
        <tbody>
    </table>
</body>
<script>
    let deleteButtons=document.querySelectorAll(".btn_deleteHistoryId");

        deleteButtons.forEach(button => {
            button.addEventListener("click", function () {
                let wifiHistoryId = button.getAttribute("data-wifiHistoryId");
                let confirmation = confirm("삭제하시겠습니까?");
                if (confirmation) {
                    window.location.assign("http://localhost:8080/wifi_war_exploded/history.jsp?wifiHistoryId="
                        + wifiHistoryId);
                }
            });
        });

</script>
</html>
