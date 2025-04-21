package appgiaovan.BUS;

import appgiaovan.DAO.AccountDAO;
import appgiaovan.DAO.AccountRoleDAO;
import appgiaovan.DTO.AccountDTO;
import appgiaovan.PasswordHashing;

public class AccountBUS {

    private final AccountDAO accountDAO;
    private final AccountRoleDAO roleDAO;
    
    
    public AccountBUS() {
        accountDAO = new AccountDAO();
        roleDAO = new AccountRoleDAO();
    }

    public AccountDTO login(String username, String password) {
       
        String hashedPassword = PasswordHashing.hashPassword(password);
        
        return accountDAO.getByUsernameAndPassword(username, hashedPassword);
    }
    public String getRoleByAccountId(int accountId) {
        return roleDAO.getRoleByAccountId(accountId);
    }
}

