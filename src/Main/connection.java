package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class connection {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
	

    public connection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "C##Gym12345";
            String password = "991224";
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
