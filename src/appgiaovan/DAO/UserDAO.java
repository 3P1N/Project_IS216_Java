package appgiaovan.DAO;

import appgiaovan.DTO.UserDTO;
import java.sql.*;

public class UserDAO {

    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    // ✅ Overload 1: Dùng trong transaction (có Connection truyền vào)
    public int insert(UserDTO dto, Connection conn) throws SQLException {
        String sql = "INSERT INTO \"USER\" (full_name, email, created_at) VALUES (?, ?, SYSDATE)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"USER_ID"})) {
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getEmail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Trả về USER_ID sinh ra
                }
            }
        }

        return -1;
    }

    // ✅ Overload 2: Dùng độc lập (không có Connection truyền vào)
    public int insert(UserDTO dto) {
        try (Connection conn = DBConnection.getConnection()) {
            return insert(dto, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM \"USER\" WHERE email = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //overload
    // ✅ Overload kiểm tra email có Connection
    public boolean isEmailExists(String email, Connection conn) {
        String sql = "SELECT 1 FROM \"USER\" WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
