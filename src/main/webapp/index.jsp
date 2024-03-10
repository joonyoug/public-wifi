<%@ page import="java.util.List" %>
<%@ page import="db.WifiService" %>
<%@ page import="db.WifiHistoryService" %>
<%@ page import="db.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <title>내 위치 정보 가져오기</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
<h2>와이파이 정보 구하기</h2>
<div>
    <a href="index.jsp">홈 | </a>
    <a href="history.jsp">위치 히스토리 목록 | </a>
    <a href="openapi.jsp">Open Api 와이파이 정보 가져오기 | </a>
    <a href="bookMarkList.jsp">북마크 보기 | </a>
    <a href="bookMarkGroup.jsp">북마크 그룹 관리</a>
</div>
<br>
<%
    String lat= request.getParameter("lat") ==null?"0.0":request.getParameter("lat");
    String lnt=request.getParameter("lnt") ==null ? "0.0" : request.getParameter("lnt");
%>

<div>
    <label>LAT:</label>
    <input type="text" value=<%=lat%> id="lat" name="lat">
    <label>LNT:</label>
    <input type="text" value= <%=lnt%> id="lnt" name="lnt">
    <button id="btn_cur_position" onclick="getLocation()">내 위치 가져오기</button>
    <button id="btn_nearest_wifi">근처 WIFI 정보 보기</button>
</div>

<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
        <%
            if(lat.equals("0.0")&&lnt.equals("0.0")){
        %>
        <td colspan="17">상세위치정보를 입력하시오 </td>
        <%
            }
            else {
                WifiService wifiService =new WifiService();

                List<Wifi> wifiList=  wifiService.getetNearestWifiList(lat,lnt);

                WifiHistory wifiHistory=new WifiHistory(lat,lnt);

                WifiHistoryService wifiHistoryService= new WifiHistoryService();

                wifiHistoryService.InsertWifiHistroy(wifiHistory);

                for(Wifi wifi:wifiList){
        %>
        <tr>
            <td><%=wifi.getDistance()%></td>
            <td><%=wifi.getX_SWIFI_MGR_NO()%></td>
            <td><%=wifi.getX_SWIFI_WRDOFC()%></td>
            <td><a href="wifiDetail.jsp?distance=<%=wifi.getDistance()%>&X_SWIFI_MGR_NO=<%=wifi.getX_SWIFI_MGR_NO()%>">
                <%=wifi.getX_SWIFI_MAIN_NM()%></a></td>
            <td><%=wifi.getX_SWIFI_ADRES1()%></td>
            <td><%=wifi.getX_SWIFI_ADRES2()%></td>
            <td><%=wifi.getX_SWIFI_INSTL_FLOOR()%></td>
            <td><%=wifi.getX_SWIFI_INSTL_TY()%></td>
            <td><%=wifi.getX_SWIFI_INSTL_MBY()%></td>
            <td><%=wifi.getX_SWIFI_SVC_SE()%></td>
            <td><%=wifi.getX_SWIFI_CMCWR()%></td>
            <td><%=wifi.getX_SWIFI_CNSTC_YEAR()%></td>
            <td><%=wifi.getX_SWIFI_INOUT_DOOR()%></td>
            <td><%=wifi.getX_SWIFI_REMARS3()%></td>
            <td><%=wifi.getLAT()%></td>
            <td><%=wifi.getLNT()%></td>
            <td><%=wifi.getWORK_DTTM()%></td>
        <%
                }
            }
        %>
        </tr>

    </tbody>
</table>
<SCRIPT>
    let getNearesWifi=document.getElementById("btn_nearest_wifi");

    function getLocation(){
        if (navigator.geolocation){
            navigator.geolocation.getCurrentPosition(showPosition);
        }
    }
    function showPosition(position) {
        var latitude = position.coords.latitude;
        var longitude = position.coords.longitude;
        document.getElementById("lat").value = latitude;
        document.getElementById("lnt").value = longitude;
    }
    getNearesWifi.addEventListener("click",function (){
        let latitude = document.getElementById("lat").value;
        let longitude = document.getElementById("lnt").value;

        if (latitude !== "0.0" || longitude !== "0.0") {
            window.location.assign("http://localhost:8080/wifi_war_exploded/index.jsp" +
                "?lat=" + latitude + "&lnt=" + longitude);
        } else {
            alert("위치 정보를 입력하신 후에 조회해주세요.")
        }

    });
</SCRIPT>
</body>
</html>
