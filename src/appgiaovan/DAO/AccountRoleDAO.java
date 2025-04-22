package appgiaovan.DAO;

import appgiaovan.DTO.AccountRoleDTO;

import java.sql.*;

public class AccountRoleDAO {

    private static AccountRoleDAO instance;

    public static AccountRoleDAO getInstance() {
        if (instance == null) {
            instance = new AccountRoleDAO();
        }
        return instance;
    }

    // Overload 1: Dùng trong transaction
    public int insert(AccountRoleDTO dto, Connection conn) throws SQLException {
        String sql = "INSERT INTO ACCOUNT_ROLE (ACCOUNT_ID, ROLE_NAME, CREATED_AT) VALUES (?, ?, SYSDATE)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ACCOUNT_ROLE_ID"})) {
            ps.setInt(1, dto.getAccountId());
            ps.setString(2, dto.getRoleName());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    // Overload 2: Dùng độc lập (không trong transaction)
    public int insert(AccountRoleDTO dto) {
        try (Connection conn = DBConnection.getConnection()) {
            return insert(dto, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Overload 1: Có hỗ trợ connection nếu cần sau này
    public String getRoleByAccountId(int accountId, Connection conn) throws SQLException {
        String sql = "SELECT ROLE_NAME FROM ACCOUNT_ROLE WHERE ACCOUNT_ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ROLE_NAME");
                }
            }
        }
        return null;
    }

    // Overload 2: Dùng bình thường
    public String getRoleByAccountId(int accountId) {
        try (Connection conn = DBConnection.getConnection()) {
            return getRoleByAccountId(accountId, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
