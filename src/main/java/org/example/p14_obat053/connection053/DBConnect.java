package org.example.p14_obat053.connection053;
import java.sql.*;

public class DBConnect {
    public Connection conn;
    public Statement stat;
    public ResultSet result;
    public PreparedStatement pstat;

    public DBConnect(){
        try{
            String url = "jdbc:sqlserver://DESKTOP-ADMK3G0:1433;database=Apotek;user=sa;password=123";
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();
        }catch (Exception e) {
            System.out.println("Error saat connect database: " + e);
        }
    }

    public static void main(String[] args) {
        DBConnect connnection = new DBConnect();
        System.out.println("Connection berhasil");
    }

}
