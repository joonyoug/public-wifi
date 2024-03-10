<%@ page import="db.BookMarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="db.BookMarkGroupService" %><%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2024/03/06
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div>
    <a href="index.jsp">홈 | </a>
    <a href="history.jsp">위치 히스토리 목록 | </a>
    <a href="openapi.jsp">Open Api 와이파이 정보 가져오기 | </a>
    <a href="bookMarkList.jsp">북마크 보기 | </a>
    <a href="bookMarkGroup.jsp">북마크 그룹 관리</a>
</div>
    <div>
        <button id="btn_addGroup" onclick="location.href='bookMarkGroupAdd.jsp'">북마크 그룹 이름 추가</button>
    </div>
    <table>
        <thead>
            <tr>
                <th>id</th>
                <th>북마크 이름</th>
                <th>순서</th>
                <th>등록 일자</th>
                <th>수정 일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
                <% BookMarkGroupService bookMarkGroupService=new BookMarkGroupService();
                    List<BookMarkGroup> bookMarkGroupList= bookMarkGroupService.getBookMarkGroup();
                    for(BookMarkGroup bookMarkGroup:bookMarkGroupList){
                %>
                <tr>
                    <td><%=bookMarkGroup.getId()%></td>
                    <td><%=bookMarkGroup.getName()%></td>
                    <td><%=bookMarkGroup.getOrder()%></td>
                    <td><%=bookMarkGroup.getRegisterTime()%></td>
                    <td><%= bookMarkGroup.getUpdateTime()==null?"":bookMarkGroup.getUpdateTime()%></td>
                    <td><a href="BookMarkGroupUpdate.jsp?BookMarkGroupName=<%=bookMarkGroup.getName()%>">수정</a>
                        <a href="#" onclick="confirmDelete('<%=bookMarkGroup.getId()%>')">삭제</a></td>
                </tr>
                <%
                    }
                %>

        </tbody>


    </table>

</body>
<script>
        function confirmDelete(id){
            var confirmDelete=confirm("삭제하시겠습니까?");
            if(confirmDelete){
                window.location.href="BookmarkGroupServlet?action=delete&id="+id;
            }
            else {
                return false;
            }
        }
        var params=new URLSearchParams(window.location.search);
        var success=params.get('success');
        if(success==="true"){
            alert("삭제 완료!")
        }
        else if(success==="false"){
            alert("오류");
        }
        var updatesuccess=params.get('updatesuccess');
        if(updatesuccess==="true"){
            alert("수정완료!")
        }
        else if(updatesuccess==="false"){
            alert("오류");
        }


</script>

</html>
