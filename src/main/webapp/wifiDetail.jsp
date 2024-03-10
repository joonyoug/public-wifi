<%@ page import="db.WifiService" %>
<%@ page import="db.Wifi" %>
<%@ page import="db.BookMarkGroupService" %>
<%@ page import="java.util.List" %>
<%@ page import="db.BookMarkGroup" %><%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2024/03/05
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 상세 정보</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        td{

        }
    </style>
</head>
<body>
        <%
            String X_SWIFI_MGR_NO=request.getParameter("X_SWIFI_MGR_NO");
            String distance=request.getParameter("distance");
            WifiService wifiService=new WifiService();
            Wifi detailWifi= wifiService.searchWifi(X_SWIFI_MGR_NO,distance);
        %>
        <div>
            <a href="index.jsp">홈 | </a>
            <a href="history.jsp">위치 히스토리 목록 | </a>
            <a href="openapi.jsp">Open Api 와이파이 정보 가져오기 | </a>
            <a href="bookMarkList.jsp">북마크 보기 | </a>
            <a href="bookMarkGroup.jsp">북마크 그룹 관리</a>
        </div>
        <%
            BookMarkGroupService bookMarkGroupService=new BookMarkGroupService();
            List<BookMarkGroup> bookMarkGroupList=bookMarkGroupService.getBookMarkGroup();
        %>
        <form action="BookmarkListServlet" method="post">
            <input type="hidden" name="action" value="add">
        <div>
            <select name="bookmark_id">
                <option>북마크 그룹 이름 선택</option>
            <%  for(BookMarkGroup bookMarkGroup:bookMarkGroupList){
            %>
                <option value="<%=bookMarkGroup.getId()%>"><%=bookMarkGroup.getName()%></option>
            <%
                    }
            %>
            </select>
            <input type="hidden" name="X_SWIFI_MGR_NO" value="<%=detailWifi.getX_SWIFI_MGR_NO()%>">
            <input type="hidden" name="distance" value="<%=detailWifi.getDistance()%>">
            <button type="submit">북마크 추가하기</button>
        </div>
        </form>
        <table>
            <tr>
                <th>거리</th>
                <td><%=detailWifi.getDistance()%></td>
            </tr>
            <tr>
                <th>관리번호</th>
                <td><%=detailWifi.getX_SWIFI_MGR_NO()%></td>
            </tr>
            <tr>
                <th>자치구</th>
                <td><%=detailWifi.getX_SWIFI_WRDOFC()%></td>
            </tr>
            <tr>
                <th>와이파이명</th>
                <td><%=detailWifi.getX_SWIFI_MAIN_NM()%></td>
            </tr>
            <tr>
                <th>도로명주소</th>
                <td><%=detailWifi.getX_SWIFI_ADRES1()%></td>
            </tr>
            <tr>
                <th>상세주소</th>
                <td><%=detailWifi.getX_SWIFI_ADRES2()%></td>
            </tr>
            <tr>
                <th>설치위치(층)</th>
                <td><%=detailWifi.getX_SWIFI_INSTL_FLOOR()%></td>
            </tr>
            <tr>
                <th>설치유형</th>
                <td><%=detailWifi.getX_SWIFI_INSTL_TY()%></td>
            </tr>
            <tr>
                <th>설치기관</th>
                <td><%=detailWifi.getX_SWIFI_INSTL_MBY()%></td>
            </tr>
            <tr>
                <th>서비스구분</th>
                <td><%=detailWifi.getX_SWIFI_SVC_SE()%></td>
            </tr>
            <tr>
                <th>망종류</th>
                <td><%=detailWifi.getX_SWIFI_CMCWR()%></td>
            </tr>
            <tr>
                <th>설치년도</th>
                <td><%=detailWifi.getX_SWIFI_CNSTC_YEAR()%></td>
            </tr>
            <tr>
                <th>실내외구분</th>
                <td><%=detailWifi.getX_SWIFI_INOUT_DOOR()%></td>
            </tr>
            <tr>
                <th>WIFI접속환경</th>
                <td><%=detailWifi.getX_SWIFI_REMARS3()%></td>
            </tr>
            <tr>
                <th>X좌표</th>
                <td><%=detailWifi.getLAT()%></td>
            </tr>
            <tr>
                <th>Y좌표</th>
                <td><%=detailWifi.getLNT()%></td>
            </tr>
            <tr>
                <th>작업일자</th>
                <td><%=detailWifi.getWORK_DTTM()%></td>
            </tr>

        </table>
</body>
<script>
    var param=new URLSearchParams(window.location.search);
    var success=param.get('success');
    if(success==='true'){
        alert('추가완료');
    }
    else if(success ==='false'){
        alert('추가실패')
    }


</script>
</html>
