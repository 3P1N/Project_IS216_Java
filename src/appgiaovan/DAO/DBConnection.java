package appgiaovan.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;

    // Lấy kết nối nếu chưa tồn tại hoặc đã đóng
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseUtil.getConnection();
                System.out.println("✅ Ket noi co so du lieu thanh cong.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Khong the ket noi den co so du lieu: " + e.getMessage());
        }
        return connection;
    }

    // Đóng kết nối nếu cần
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Da dong ket noi co so du lieu.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Loi khi dong ket noi: " + e.getMessage());
        }
    }
}
