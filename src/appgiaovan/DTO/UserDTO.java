package appgiaovan.DTO;

import java.util.Date;

public class UserDTO {

    private int userId;
    private String fullName;
    private String email;
    private Date createdAt;

    public UserDTO() {
    }

    public UserDTO(int userId, String fullName, String email, Date createdAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
    }

    public UserDTO(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    // Getter & Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
