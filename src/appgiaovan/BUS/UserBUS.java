package appgiaovan.BUS;

import appgiaovan.DAO.*;
import appgiaovan.DTO.*;

import java.sql.Connection;
import java.sql.SQLException;

public class UserBUS {

    private static UserBUS instance;

    public static UserBUS getInstance() {
        if (instance == null) {
            instance = new UserBUS();
        }
        return instance;
    }

    /**
     * Đăng ký người dùng với thông tin user, account, customer và vai trò.
     * @param userDTO
     * @param accountDTO
     * @param customerDTO
     * @param roleDTO
     * @return "SUCCESS" nếu thành công, hoặc một chuỗi báo lỗi cụ thể nếu thất bại.
     */
    public String registerCustomer(UserDTO userDTO, AccountDTO accountDTO, CustomerDTO customerDTO, AccountRoleDTO roleDTO) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Kiểm tra trùng username
            if (AccountDAO.getInstance().isUsernameExists(accountDTO.getUsername(), conn)) {
                return "USERNAME_EXISTS";
            }

            // Kiểm tra trùng email
            if (UserDAO.getInstance().isEmailExists(userDTO.getEmail(),conn)) {
                return "EMAIL_EXISTS";
            }

            // Kiểm tra trùng CCCD
            if (CustomerDAO.getInstance().isCCCDExists(customerDTO.getCccd(),conn)) {
                return "CCCD_EXISTS";
            }

            // Kiểm tra trùng số điện thoại
            if (CustomerDAO.getInstance().isPhoneExists(customerDTO.getPhone(),conn)) {
                return "PHONE_EXISTS";
            }

            // Insert lần lượt các đối tượng
            int userId = UserDAO.getInstance().insert(userDTO, conn);
            int accountId = AccountDAO.getInstance().insert(accountDTO, userId, conn);
            int customerId = CustomerDAO.getInstance().insert(customerDTO, userId, conn);

            roleDTO.setAccountId(accountId);
            AccountRoleDAO.getInstance().insert(roleDTO, conn);

            conn.commit();
            return "SUCCESS";

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
           
            return "ERROR";

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}
