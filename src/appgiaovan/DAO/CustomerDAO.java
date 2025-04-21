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
    String sql = "INSERT INTO CUSTOMER (ID_CUSTOMER, NAME, ID_CCCD, PHONENUMBER, gender, DATE_OF_BIRTH, ADDRESS, EMAIL) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID_CUSTOMER"})) { // Giả sử khóa chính tên là CUSTOMER_ID

        ps.setInt(1, userId);
        ps.setString(2, dto.getName());
        ps.setString(3, dto.getCccd());
        ps.setString(4, dto.getPhone());
        ps.setString(5, String.valueOf(dto.getGender()));

        // Nếu dto.getDateOfBirth() là java.util.Date thì cần chuyển sang java.sql.Date
        ps.setDate(6, new java.sql.Date(dto.getDateOfBirth().getTime()));
        
        ps.setString(7, dto.getAddress());
        ps.setString(8, dto.getEmail());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            Object generatedKey = rs.getObject(1);
            System.out.println("Generated key: " + generatedKey + " (" + generatedKey.getClass().getSimpleName() + ")");
            return Integer.parseInt(generatedKey.toString());
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return -1; // Trả về -1 nếu lỗi
}


    public boolean isCCCDExists(String cccd) {
        String sql = "SELECT 1 FROM CUSTOMER WHERE ID_CCCD = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cccd);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu CCCD đã tồn tại

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isPhoneExists(String phone) {
        String sql = "SELECT 1 FROM CUSTOMER WHERE PHONENUMBER = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu SĐT đã tồn tại

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
