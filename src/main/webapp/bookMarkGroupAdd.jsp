<%@ page import="db.BookMarkGroupService" %><%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2024/03/06
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹 추가</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .td-con{
            text-align: left;
        }
    </style>
</head>
<body>
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
              <td class="td-con">
                  <label style="margin: 20px"><input type="text" id="name"></label>
              </td>
         </tr>
        <tr>
            <th>순서</th>
            <td class="td-con">
                <label style="margin: 20px"><input type=text id="order"></label>
            </td>
        </tr>
    </table>
    <form action="BookmarkGroupServlet" method="post" id="bookmarkForm">
        <input type="hidden" name="bookmarkName" value="" id="bookMarkGroupName">
        <input type="hidden" name="bookmarkOrder" value="" id="bookMarkGroupOrder">
        <input type="hidden" name="action" value="add">
        <div style="text-align: center">
            <button type="submit">추가</button>
        </div>
    </form>
</body>
    <script>
        document.getElementById('bookmarkForm').addEventListener('submit',function (event){
            var bookmarkName=document.getElementById('name').value;
            var bookmarkOrder=document.getElementById('order').value;
            document.getElementById('bookMarkGroupName').value=bookmarkName;
            document.getElementById('bookMarkGroupOrder').value=bookmarkOrder;


        })



        var param=new URLSearchParams(window.location.search);
        var success=param.get('success');

        if(success==='true'){
            alert('추가 성공');
        }
        else if(success==='false'){
            alert('추가 실패');
        }


    </script>



</html>
