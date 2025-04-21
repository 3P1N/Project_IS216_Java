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
         PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ACCOUNT_ROLE_ID"})) { // Giả sử khóa chính là ACCOUNT_ROLE_ID

        ps.setInt(1, dto.getAccountId());
        ps.setString(2, dto.getRoleName());

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

}
