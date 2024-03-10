<%@ page import="db.BookMarkListService" %>
<%@ page import="db.BookMarkList" %><%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2024/03/07
  Time: 5:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 삭제</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
        <% BookMarkListService bookMarkListService=new BookMarkListService();
          BookMarkList bookMarkList=
                  bookMarkListService.ShowBookmarkList(request.getParameter("bookmarkListId"));
        %>
        <div>
            <a href="index.jsp">홈 | </a>
            <a href="history.jsp">위치 히스토리 목록 | </a>
            <a href="openapi.jsp">Open Api 와이파이 정보 가져오기 | </a>
            <a href="bookMarkList.jsp">북마크 보기 | </a>
            <a href="bookMarkGroup.jsp">북마크 그룹 관리</a>
        </div>

        <table>
        <tr>
            <th>북마크 이름</th>
            <td><%=bookMarkList.getBookmarkName()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=bookMarkList.getWifiName()%></td>
        </tr>
        <tr>
            <th>등록일자</th>
            <td><%=bookMarkList.getRegister_time()%></td>
        </tr>
    </table>
        <form action="BookmarkListServlet" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="<%=bookMarkList.getId()%>">
                <div style="text-align: center">
                 <a href="bookMarkList.jsp">돌아가기 |</a><button type="submit">삭제</button>
                </div>
        </form>
</body>
<script>

</script>
</html>
