<%@ page import="db.BookMarkGroupService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹 수정</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%
            String oldBookMarkGroupName=request.getParameter("BookMarkGroupName");
    %>
<div>
    <a href="index.jsp">홈 | </a>
    <a href="history.jsp">위치 히스토리 목록 | </a>
    <a href="openapi.jsp">Open Api 와이파이 정보 가져오기 | </a>
    <a href="bookMarkList.jsp">북마크 보기 | </a>
    <a href="bookMarkGroup.jsp">북마크 그룹 관리</a>
</div>

<form action="BookmarkGroupServlet" method="post" >
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="oldBookMarkGroupName" value="<%=oldBookMarkGroupName%>">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td style="text-align: left">
                <label style="margin: 20px">
                    <input id="bookMarkGroupName" type="text" name="bookMarkGroupName">
                </label>
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td style="text-align: left">
                <label style="margin: 20px">
                    <input id="bookMarkGroupOrder" type="text" name="bookMarkGroupOrder">
                </label>
            </td>
        </tr>
    </table>
    <div style="text-align: center">
        <button type="submit">수정하기</button>
        <a href="bookMarkGroup.jsp">돌아가기</a>
    </div>
</form>

</body>
</html>
