package appgiaovan.DAO;

import appgiaovan.DTO.AccountDTO;
import appgiaovan.PasswordHashing;
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

    public int insert(AccountDTO dto, int userId) {
        String sql = "INSERT INTO ACCOUNT (username, password_hash, USER_ID, created_at) VALUES (?, ?, ?, SYSDATE)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ACCOUNT_ID"})) { // Giả sử khóa chính là ACCOUNT_ID

            ps.setString(1, dto.getUsername());
            
            ps.setString(2, dto.getPasswordHash()); // Password đã được hash trước khi lưu

            ps.setInt(3, userId);

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

    public boolean isUsernameExists(String username) {
        String sql = "SELECT * FROM ACCOUNT WHERE username = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
