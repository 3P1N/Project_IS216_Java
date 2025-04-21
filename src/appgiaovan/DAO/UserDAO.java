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
        String sql = "INSERT INTO \"User\" (full_name, email, created_at) VALUES (?, ?, NOW())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dto.getFullName());
            ps.setString(2, dto.getEmail());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Trả về user_id được sinh ra
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // Trả về -1 nếu thất bại
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM Users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true nếu tồn tại

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // false nếu có lỗi hoặc không tìm thấy
    }
}
