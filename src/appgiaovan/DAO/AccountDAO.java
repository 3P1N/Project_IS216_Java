package appgiaovan.DAO;

import appgiaovan.DTO.AccountDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    public AccountDTO getByUsernameAndPassword(String username, String password) {
        AccountDTO account = null;
        String sql = "SELECT * FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD_HASH = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // Nếu dùng hash, cần mã hóa trước

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                account = new AccountDTO();
                account.setAccountId(rs.getInt("ACCOUNT_ID"));
                account.setUserId(rs.getInt("USER_ID"));
                account.setUsername(rs.getString("USERNAME"));
                account.setPasswordHash(rs.getString("PASSWORD_HASH"));
                account.setStatus(rs.getString("STATUS"));
                account.setCreatedAt(rs.getDate("CREATED_AT"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }
}
