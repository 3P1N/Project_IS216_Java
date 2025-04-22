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

    // ✅ Overload 1: Sử dụng trong transaction
    public int insert(CustomerDTO dto, int userId, Connection conn) throws SQLException {
        String sql = "INSERT INTO CUSTOMER (ID_CUSTOMER, NAME, ID_CCCD, PHONENUMBER, gender, DATE_OF_BIRTH, ADDRESS, EMAIL) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID_CUSTOMER"})) {
            ps.setInt(1, userId);
            ps.setString(2, dto.getName());
            ps.setString(3, dto.getCccd());
            ps.setString(4, dto.getPhone());
            ps.setString(5, String.valueOf(dto.getGender()));
            ps.setDate(6, new java.sql.Date(dto.getDateOfBirth().getTime()));
            ps.setString(7, dto.getAddress());
            ps.setString(8, dto.getEmail());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    // ✅ Overload 2: Dùng độc lập
    public int insert(CustomerDTO dto, int userId) {
        try (Connection conn = DBConnection.getConnection()) {
            return insert(dto, userId, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean isCCCDExists(String cccd) {
        String sql = "SELECT 1 FROM CUSTOMER WHERE ID_CCCD = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cccd);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isPhoneExists(String phone) {
        String sql = "SELECT 1 FROM CUSTOMER WHERE PHONENUMBER = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //overload
    public boolean isCCCDExists(String cccd, Connection conn) {
        String sql = "SELECT 1 FROM CUSTOMER WHERE ID_CCCD = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cccd);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isPhoneExists(String phone, Connection conn) {
        String sql = "SELECT 1 FROM CUSTOMER WHERE PHONENUMBER = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
