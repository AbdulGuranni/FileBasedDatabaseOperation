package utils;

import constants.DbConstants;

import java.lang.reflect.Field;
import java.sql.*;

public class DbUtils {
    Connection con =null;
    Statement stmt = null;
    public Connection getSqlConnection(){

        try {
            Class.forName(DbConstants.DB_DRIVER);
            System.out.println("Driver is loaded successfully");
//DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            con = DriverManager.getConnection(DbConstants.CONNECTION_URL,DbConstants.USERNAME,DbConstants.PASSWORD);
            System.out.println("Connection is established succesfully ");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public ResultSet executeNonCrudSqlQuery(Connection con, String query) throws SQLException {
        ResultSet resultSet = null;
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(query);
            System.out.println("NonCrud query has been executed succesfully");
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public void executeSqlQuery(Connection con, String query) throws SQLException {
        try {
            stmt = con.createStatement();
            stmt.execute(query);
            System.out.println("Query has been executed succesfully");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void executeQuery(Connection con, String query, Object classObject) {
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(query);
            Class<?> clazz = classObject.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(classObject);
                pstmt.setObject(i + 1, value);
            }
            pstmt.executeUpdate();
            System.out.println("Query has been executed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
