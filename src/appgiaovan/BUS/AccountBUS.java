package appgiaovan.BUS;

import appgiaovan.DAO.AccountDAO;
import appgiaovan.DTO.AccountDTO;
import appgiaovan.PasswordHashing;

public class AccountBUS {

    private final AccountDAO accountDAO;

    public AccountBUS() {
        accountDAO = new AccountDAO();
    }

    public AccountDTO login(String username, String password) {
       
        String hashedPassword = PasswordHashing.hashPassword(password);
        
        return accountDAO.getByUsernameAndPassword(username, hashedPassword);
    }
}

