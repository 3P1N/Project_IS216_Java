package appgiaovan.DAO;

import appgiaovan.DTO.CustomerDTO;

import java.sql.*;

public class CustomerDAO {

    private static CustomerDAO instance;

    public static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }
    
    public int insert(CustomerDTO dto, int userId) {
        String sql = "INSERT INTO CUSTOMER (user_id, full_name, cccd, phone, gender, birth_date, address, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
            ps.setString(2, dto.getFullName());
            ps.setString(3, dto.getCccd());
            ps.setString(4, dto.getPhone());
            ps.setString(5, dto.getGender());
            ps.setDate(6, dto.getBirthDate());
            ps.setString(7, dto.getAddress());
            ps.setString(8, dto.getEmail());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Trả về customer_id
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // Lỗi hoặc không sinh được ID
    }

    public boolean isCCCDExists(String cccd) {
        String sql = "SELECT 1 FROM Customers WHERE cccd = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cccd);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu CCCD đã tồn tại

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isPhoneExists(String phone) {
        String sql = "SELECT 1 FROM Customers WHERE phone = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu SĐT đã tồn tại

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
