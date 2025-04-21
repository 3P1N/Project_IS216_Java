
package appgiaovan.BUS;

import appgiaovan.DAO.AccountDAO;
import appgiaovan.DAO.AccountRoleDAO;
import appgiaovan.DAO.CustomerDAO;
import appgiaovan.DAO.UserDAO;
import appgiaovan.DTO.AccountDTO;
import appgiaovan.DTO.AccountRoleDTO;
import appgiaovan.DTO.CustomerDTO;
import appgiaovan.DTO.UserDTO;

public class UserBUS {

    private static UserBUS instance;
    public static UserBUS getInstance() {
        if (instance == null) {
            instance = new UserBUS();
        }
        return instance;
    }

    public boolean registerCustomer(UserDTO userDTO, AccountDTO accountDTO, CustomerDTO customerDTO, AccountRoleDTO roleDTO) {
        // Kiểm tra trùng tài khoản, email, CCCD, SĐT
        if (AccountDAO.getInstance().isUsernameExists(accountDTO.getUsername())
            || UserDAO.getInstance().isEmailExists(userDTO.getEmail())
            || CustomerDAO.getInstance().isCCCDExists(customerDTO.getCccd())
            || CustomerDAO.getInstance().isPhoneExists(customerDTO.getPhone())) {
            return false;
        }

        // Insert dữ liệu (giữ ID nếu cần truyền giữa các bảng)
        int userId = UserDAO.getInstance().insert(userDTO);
        int accountId = AccountDAO.getInstance().insert(accountDTO, userId);
        int customerId = CustomerDAO.getInstance().insert(customerDTO, userId);
        roleDTO.setAccountId(accountId);
        AccountRoleDAO.getInstance().insert(roleDTO);

        return true;
    }
}

