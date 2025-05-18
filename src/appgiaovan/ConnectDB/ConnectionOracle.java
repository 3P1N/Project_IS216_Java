
package appgiaovan.ConnectDB;
import java.sql.*;

public class ConnectionOracle {
    public static Connection getConnection() throws ClassNotFoundException,
            SQLException {

        //Host name
        String hostName = "localhost";
        //SID Oralce
        String sid = "orcl";
        //Username
        String userName = "DoAnGiaoVan";
        //Password
        String password = "Admin123";
        
  
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Cấu trúc URL Connection dành cho Oracle
        // Ví dụ: jdbc:oracle:thin:@localhost:1521:db11g
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;

        //Tạo đối tượng connection
        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        
        return conn;
    }
}

