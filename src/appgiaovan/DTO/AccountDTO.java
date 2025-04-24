package appgiaovan.DTO;

import java.util.Date;

public class AccountDTO {
    private int accountId;
    private int userId;
    private String username;
    private String passwordHash;
    private String status;
    private Date createdAt;

    // Constructor không tham số
    public AccountDTO() {
    }

    // Constructor đầy đủ tham số
    public AccountDTO(int accountId, int userId, String username, String passwordHash, String status, Date createdAt) {
        this.accountId = accountId;
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getter và Setter
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // ToString để dễ debug
    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }


}
