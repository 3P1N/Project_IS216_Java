package appgiaovan.DTO;

import java.util.Date;

public class UserDTO {

    private int userId;
    private String Name;
    private String email;
    private Date createdAt;

    public UserDTO() {
    }

    public UserDTO(int userId, String Name, String email, Date createdAt) {
        this.userId = userId;
        this.Name = Name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public UserDTO(String Name, String email) {
        this.Name = Name;
        this.email = email;
    }

    // Getter & Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
