package appgiaovan.DAO;

import appgiaovan.DTO.AccountDTO;

import java.sql.*;

public class AccountDAO {

    private static AccountDAO instance;

    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    public AccountDTO getByUsernameAndPassword(String username, String passwordHash) {
        AccountDTO account = null;
        String sql = "SELECT * FROM ACCOUNT WHERE username = ? AND password_hash = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                account = new AccountDTO();
                account.setAccountId(rs.getInt("account_id"));
                account.setUserId(rs.getInt("user_id"));
                account.setUsername(rs.getString("username"));
                account.setPasswordHash(rs.getString("password_hash"));
                account.setStatus(rs.getString("status"));
                account.setCreatedAt(rs.getDate("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

    // Overload 1: Dùng trong transaction
    public int insert(AccountDTO dto, int userId, Connection conn) throws SQLException {
        String sql = "INSERT INTO ACCOUNT (username, password_hash, USER_ID, created_at) VALUES (?, ?, ?, SYSDATE)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ACCOUNT_ID"})) {
            ps.setString(1, dto.getUsername());
            ps.setString(2, dto.getPasswordHash());
            ps.setInt(3, userId);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    // Overload 2: Dùng khi không cần transaction
    public int insert(AccountDTO dto, int userId) {
        try (Connection conn = DBConnection.getConnection()) {
            return insert(dto, userId, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Overload 1: Kiểm tra tồn tại username trong transaction
    public boolean isUsernameExists(String username, Connection conn) throws SQLException {
        String sql = "SELECT 1 FROM ACCOUNT WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Overload 2: Dùng bình thường
    public boolean isUsernameExists(String username) {
        try (Connection conn = DBConnection.getConnection()) {
            return isUsernameExists(username, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
