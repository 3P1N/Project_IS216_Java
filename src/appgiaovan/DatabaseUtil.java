
package appgiaovan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:oracle:thin:@LAPTOP-LTSU53RG:1521/orcl";
    private static final String USER = "qldonvigiaovan";
    private static final String PASSWORD = "Admin123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

