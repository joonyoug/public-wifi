<%@ page import="db.BookMarkListService" %>
<%@ page import="db.BookMarkList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2024/03/07
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 목록</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div>
    <%
        BookMarkListService bookMarkListService=new BookMarkListService();
        List<BookMarkList> list=bookMarkListService.selectBookmarkList();
    %>

    <a href="index.jsp">홈 | </a>
    <a href="history.jsp">위치 히스토리 목록 | </a>
    <a href="openapi.jsp">Open Api 와이파이 정보 가져오기 | </a>
    <a href="bookMarkList.jsp">북마크 보기 | </a>
    <a href="bookMarkGroup.jsp">북마크 그룹 관리</a>
</div>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>북마크 이름</th>
            <th>와이파이명</th>
            <th>등록일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tr>

            <%  for(BookMarkList bookMarkList: list){


            %>
            <td><%=bookMarkList.getId()%></td>
            <td><%=bookMarkList.getBookmarkName()%></td>
            <td><%=bookMarkList.getWifiName()%></td>
            <td><%=bookMarkList.getRegister_time()%></td>
            <td><a href="bookMarkListDelete.jsp?bookmarkListId=<%=bookMarkList.getId()%>">삭제</a></td>
        </tr>
            <%
        }
        %>
    </table>
</body>
<script>
    var param=new URLSearchParams(window.location.search);
    var success=param.get('success');
    if(success==='true'){
        alert('삭제성공');
    }
    else if(success==='false'){
        alert('삭제 실패')
    }
    var addsuccess=param.get('addsuccess');
    if(addsuccess==='true'){
        alert('추가성공');
    }
    else if (addsuccess==='false'){
        alert('삭제성공')
    }


</script>
</html>
