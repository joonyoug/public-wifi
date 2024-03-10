package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiHistoryService {
    final static String urlH="jdbc:mariadb://localhost/wifi";
    final static String dbId="testuser2";
    final static String dbPassword="1234";
    public void InsertWifiHistroy(WifiHistory wifiHistory){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        int affected;
        try {
            connection= DriverManager.getConnection(urlH,dbId,dbPassword);
            String sql="INSERT INTO wifi_history (LAT,LNT,WORK_DTTM)\n" +
                    "values(?,?,?);\n";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setFloat(1,wifiHistory.getLAT());
            preparedStatement.setFloat(2,wifiHistory.getLNT());
            preparedStatement.setTimestamp(3,wifiHistory.getWORK_DTTM());
            affected=preparedStatement.executeUpdate();
            if(affected>0){
                System.out.println("성공");
            }
            else{
                System.out.println("실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(connection!=null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement!=null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
    public List<WifiHistory> getWifiHistory(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<WifiHistory> wifiHistoryList=new ArrayList<>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=DriverManager.getConnection(urlH,dbId,dbPassword);
            String sql="SELECT * from wifi_history wh \n" +
                    "order by id;";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                WifiHistory wifiHistory=new WifiHistory();
                wifiHistory.setLAT(resultSet.getFloat("LAT"));
                wifiHistory.setLNT(resultSet.getFloat("LNT"));
                wifiHistory.setWORK_DTTM(resultSet.getTimestamp("work_DTTM"));
                wifiHistory.setId(resultSet.getInt("id"));
                wifiHistoryList.add(wifiHistory);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(connection!=null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement!=null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return wifiHistoryList;

    }
    public void deleteWifiHistory(String id){
        int historyID=Integer.parseInt(id);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        int affected;
        try {
            connection= DriverManager.getConnection(urlH,dbId,dbPassword);
            String sql="DELETE FROM wifi_history \n" +
                    "WHERE id=?;";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,historyID);

            affected=preparedStatement.executeUpdate();
            if(affected>0){
                System.out.println("성공");
            }
            else{
                System.out.println("실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(connection!=null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement!=null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }




    }

}
