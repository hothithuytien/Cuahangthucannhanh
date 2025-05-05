package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconn {
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;"
            + "databaseName=QLFASTFOODSTORE;"
            + "encrypt=true;"
            + "trustServerCertificate=true";
    
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
         System.out.println("ket noi thanh cong");
        } catch (SQLException e) {
            System.err.println("ket noi that bai " + e.getMessage());
        }
        return conn;
    }

 /* public static void main(String[] args) {
      getConnection();
   }*/
}
