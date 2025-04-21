
package appgiaovan.DTO;

import java.util.Date;

public class AccountRoleDTO {
    private int accountRoleId;
    private int accountId;
    private String roleName;
    private Date createdAt;

    public AccountRoleDTO() {}

    public AccountRoleDTO(int accountRoleId, int accountId, String roleName, Date createdAt) {
        this.accountRoleId = accountRoleId;
        this.accountId = accountId;
        this.roleName = roleName;
        this.createdAt = createdAt;
    }

    public AccountRoleDTO(int accountId, String roleName) {
        this.accountId = accountId;
        this.roleName = roleName;
    }

    // Getter & Setter
    public int getAccountRoleId() { return accountRoleId; }
    public void setAccountRoleId(int accountRoleId) { this.accountRoleId = accountRoleId; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}

