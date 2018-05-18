/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class MyConnection {

    public static Connection getConnection(String databaseName, String user, String password) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName="
                + databaseName + ";user=" + user + ";password=" + password;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        Connection con = getConnection("QuanLyThuVienV2", "sa", "hot10000%");
        if(con!=null){
            System.out.println("Kết nối thành công");
        }
        else {
            System.out.println("Kết nối thất bại");
        }
    }
}
