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

    public int insert(AccountDTO dto, int userId) {
        String sql = "INSERT INTO Accounts (username, password_hash, user_id, created_at) VALUES (?, ?, ?, NOW())";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dto.getUsername());
            ps.setString(2, dto.getPassword()); // phải là password đã hash
            ps.setInt(3, userId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // trả về account_id mới sinh
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT * FROM Accounts WHERE username = ?";

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
