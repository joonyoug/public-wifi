package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookMarkListService {

    final static String urlH="jdbc:mariadb://localhost/wifi";
    final static String dbId="testuser2";
    final static String dbPassword="1234";
    public int addBookMarkList(String bookmarkId,String X_SWIFI_MGR_NO){

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
            String sql="INSERT into bookmark_list(bookmark_id,X_SWIFI_MGR_NO,register_time)\n" +
                    "values(?,?,now());";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(bookmarkId));
            preparedStatement.setString(2,X_SWIFI_MGR_NO);
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
        return affected;
    }
    public BookMarkList ShowBookmarkList(String id){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        BookMarkList bookMarkList=new BookMarkList();
        try {
            connection= DriverManager.getConnection(urlH,dbId,dbPassword);
            String sql="SELECT bl.*, w.X_SWIFI_MAIN_NM AS wifi_name, b.name AS bookmark_name\n" +
                    "FROM bookmark_list bl\n" +
                    "JOIN wifi w ON bl.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO\n" +
                    "JOIN bookmark b ON bl.bookmark_id = b.id\n" +
                    "WHERE bl.id = ?;";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(id));
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                bookMarkList.setId(resultSet.getInt("id"));
                bookMarkList.setBookmarkId(resultSet.getInt("bookmark_id"));
                bookMarkList.setBookmarkName(resultSet.getString("bookmark_name"));
                bookMarkList.setWifiName(resultSet.getString("wifi_name"));
                bookMarkList.setRegister_time(resultSet.getTimestamp("register_time"));
                bookMarkList.setX_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"));
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
    return bookMarkList;
    }
    public List<BookMarkList> selectBookmarkList(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<BookMarkList> List=new ArrayList<>();
        try {
            connection= DriverManager.getConnection(urlH,dbId,dbPassword);
            String sql="SELECT bl.*, w.X_SWIFI_MAIN_NM AS wifi_name, b.name AS bookmark_name\n" +
                    "FROM bookmark_list bl\n" +
                    "JOIN wifi w ON bl.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO\n" +
                    "JOIN bookmark b ON bl.bookmark_id = b.id\n";


            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                BookMarkList bookMarkList=new BookMarkList();
                bookMarkList.setId(resultSet.getInt("id"));
                bookMarkList.setBookmarkId(resultSet.getInt("bookmark_id"));
                bookMarkList.setBookmarkName(resultSet.getString("bookmark_name"));
                bookMarkList.setWifiName(resultSet.getString("wifi_name"));
                bookMarkList.setRegister_time(resultSet.getTimestamp("register_time"));
                bookMarkList.setX_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"));
                List.add(bookMarkList);
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
        return List;


    }

    public int deleteBookmarkList(String id){

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
            String sql="DELETE FROM bookmark_list \n" +
                    "WHERE id=?;";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(id));
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

        return affected;


    }
}
