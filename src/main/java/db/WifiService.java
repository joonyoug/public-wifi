package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiService {

    final static String url="jdbc:mariadb://localhost/Wifi";

    final static String dbId="testuser2";
    final static String dbPassword="1234";

    void showDb(){

        try {
            Class.forName("org.mariadb.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            con= DriverManager.getConnection(url,dbId,dbPassword);
            String sql="select * from wifi";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("X_SWIFI_MGR_NO"));
                System.out.println(rs.getString("X_SWIFI_WRDOFC"));
                System.out.println(rs.getString("X_SWIFI_MAIN_NM"));
                System.out.println(rs.getString("X_SWIFI_ADRES1"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(con!=null && !con.isClosed()){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (rs!=null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(ps!=null && !ps.isClosed()){
                   ps.close();

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


    }
    public List<Wifi> getetNearestWifiList(String lat,String lnt){

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Wifi> wifiList=new ArrayList<>();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            con= DriverManager.getConnection(url,dbId,dbPassword);
            String sql="SELECT * ,(6371 *\n" +
                    "\tacos(\n" +
                    "\t\tcos(RADIANS(?))*\n" +  //lat
                    "\t\tcos(RADIANS(LAT))*\n" +
                    "\t\tCOS(RADIANS(LNT)-\n" +
                    "\t\t\tRADIANS(?))+\n" +  //lnt
                    "\t\tsin(RADIANS(?))*\n" +  //lat
                    "\t\tsin(RADIANS(LAT))) \n" +
                    "\t)as distance\n" +
                    "\tFROM wifi \n" +
                    "\tORDER BY distance \n" +
                    "\tLIMIT 20;\n" +
                    "\n";
            ps=con.prepareStatement(sql);
            ps.setFloat(1,Float.parseFloat(lat));
            ps.setFloat(2,Float.parseFloat(lnt));
            ps.setFloat(3,Float.parseFloat(lat));
            rs=ps.executeQuery();
            while(rs.next()){
                Wifi wifi=new Wifi();
                wifi.setDistance(rs.getString("distance"));
                wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifi.setX_SWIFI_CNSTC_YEAR(rs.getDate("X_SWIFI_CNSTC_YEAR"));
                wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifi.setLAT(rs.getFloat("LAT"));
                wifi.setLNT(rs.getFloat("LNT"));
                wifi.setWORK_DTTM();

                wifiList.add(wifi);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(con!=null && !con.isClosed()){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (rs!=null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(ps!=null && !ps.isClosed()){
                    ps.close();

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return wifiList;

    }

    public void InsertBatch(List<Wifi> wifiList) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbId, dbPassword);
            String sql = "INSERT INTO wifi (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, " +
                    "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, " +
                    "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            for (Wifi wifi : wifiList) {
                preparedStatement.setString(1, wifi.getX_SWIFI_MGR_NO());
                preparedStatement.setString(2, wifi.getX_SWIFI_WRDOFC());
                preparedStatement.setString(3, wifi.getX_SWIFI_MAIN_NM());
                preparedStatement.setString(4, wifi.getX_SWIFI_ADRES1());
                preparedStatement.setString(5, wifi.getX_SWIFI_ADRES2());
                preparedStatement.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
                preparedStatement.setString(7, wifi.getX_SWIFI_INSTL_TY());
                preparedStatement.setString(8, wifi.getX_SWIFI_INSTL_MBY());
                preparedStatement.setString(9, wifi.getX_SWIFI_SVC_SE());
                preparedStatement.setString(10, wifi.getX_SWIFI_CMCWR());
                preparedStatement.setDate(11, wifi.getX_SWIFI_CNSTC_YEAR());
                preparedStatement.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
                preparedStatement.setString(13, wifi.getX_SWIFI_REMARS3());
                preparedStatement.setFloat(14, wifi.getLAT());
                preparedStatement.setFloat(15, wifi.getLNT());
                preparedStatement.setTimestamp(16, wifi.getWORK_DTTM());
                preparedStatement.addBatch();
            }

            int[] affected = preparedStatement.executeBatch();



        } catch (BatchUpdateException e) {
            throw new RuntimeException("Batch update failed", e.getNextException());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Wifi searchWifi(String X_SWIFI_MGR_NO,String distance){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Wifi wifi=new Wifi();

        try {
            con= DriverManager.getConnection(url,dbId,dbPassword);
            String sql="\n" +
                    "SELECT * from wifi w \n" +
                    "WHERE w.X_SWIFI_MGR_NO =?;";
            ps=con.prepareStatement(sql);
            ps.setString(1,X_SWIFI_MGR_NO);
            rs=ps.executeQuery();
            while(rs.next()){
                wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifi.setX_SWIFI_CNSTC_YEAR(rs.getDate("X_SWIFI_CNSTC_YEAR"));
                wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifi.setLAT(rs.getFloat("LAT"));
                wifi.setLNT(rs.getFloat("LNT"));
                wifi.setWORK_DTTM(rs.getTimestamp("WORK_DTTM"));
                wifi.setDistance(distance);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(con!=null && !con.isClosed()){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (rs!=null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(ps!=null && !ps.isClosed()){
                    ps.close();

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return wifi;

    }


    }




