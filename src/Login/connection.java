package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class connection {
    public static Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
	

    public connection(String jdbcUrl, String username, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            con = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Database connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getDatabaseUsername() {
        try {
            // Assuming the connection is already established
            return con.getMetaData().getUserName();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Connection getConnection() {
        return con;
    }

    public void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
