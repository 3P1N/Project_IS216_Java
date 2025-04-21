package appgiaovan.DAO;

import appgiaovan.DTO.AccountRoleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountRoleDAO {

    private static AccountRoleDAO instance;

    public static AccountRoleDAO getInstance() {
        if (instance == null) {
            instance = new AccountRoleDAO();
        }
        return instance;
    }

    public int insert(AccountRoleDTO dto) {
        String sql = "INSERT INTO ACCOUNT_ROLE (ACCOUNT_ID, ROLE_NAME, CREATED_AT) VALUES (?, ?, SYSDATE)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, dto.getAccountId());
            ps.setString(2, dto.getRoleName());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Trả về ACCOUNT_ROLE_ID
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
