package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class BookMarkGroupService {
    final static String urlH="jdbc:mariadb://localhost/wifi";
    final static String dbId="testuser2";
    final static String dbPassword="1234";

    public int addBookMark(String name,String order_number){
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
            String sql="INSERT into bookmark(name,order_number,register_time)\n" +
                    "values(?,?,now());";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,Integer.parseInt(order_number));
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
    public int updateBookMark(String oldname, String newName,String orederNumber){
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
            String sql="UPDATE bookmark  set name=? , order_number =?\n" +
                    "where name =?;";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,newName);
            preparedStatement.setInt(2,Integer.parseInt(orederNumber));
            preparedStatement.setString(3,oldname);

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
    public int deleteBookMark(String id){
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
            String sql="DELETE FROM bookmark \n" +
                    "WHERE id=?;\n";

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
    public List<BookMarkGroup> getBookMarkGroup(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<BookMarkGroup> bookMarkGroupList=new ArrayList<>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=DriverManager.getConnection(urlH,dbId,dbPassword);
            String sql="SELECT * from  bookmark \n" +
                    "order by id;";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
               BookMarkGroup bookMarkGroup=new BookMarkGroup();
               bookMarkGroup.setId(resultSet.getInt("id"));
               bookMarkGroup.setName(resultSet.getString("name"));
               bookMarkGroup.setOrder(resultSet.getInt("order_number"));
               bookMarkGroup.setRegisterTime(resultSet.getTimestamp("register_time"));
               bookMarkGroup.setUpdateTime(resultSet.getTimestamp("update_time"));
               bookMarkGroupList.add(bookMarkGroup);
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

        return bookMarkGroupList;

    }


}

