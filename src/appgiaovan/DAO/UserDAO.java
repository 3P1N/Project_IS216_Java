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

    public int insert(UserDTO dto) {
        String sql = "INSERT INTO \"USER\" (full_name, email, created_at) VALUES (?, ?, SYSDATE)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, new String[]{"USER_ID"})) {

            ps.setString(1, dto.getName());
            ps.setString(2, dto.getEmail());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1); // Lấy USER_ID được sinh ra
                return userId;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // Trả về -1 nếu thất bại
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM \"USER\" WHERE email = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true nếu tồn tại

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // false nếu có lỗi hoặc không tìm thấy
    }
}
