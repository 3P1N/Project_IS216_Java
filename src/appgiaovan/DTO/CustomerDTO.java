
package appgiaovan.DTO;

import java.util.Date;

public class CustomerDTO {
    private int idCustomer;
    private String name;
    private String cccd;
    private String phoneNumber;
    private char gender;
    private Date dateOfBirth;
    private String address;
    private String email;

    public CustomerDTO() {}

    public CustomerDTO(int idCustomer, String name, String cccd, String phoneNumber, char gender,
                       Date dateOfBirth, String address, String email) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.cccd = cccd;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
    }

    public CustomerDTO(String name, String cccd, String phoneNumber, char gender,
                       Date dateOfBirth, String address, String email) {
        this.name = name;
        this.cccd = cccd;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
    }

    // Getter & Setter
    public int getIdCustomer() { return idCustomer; }
    public void setIdCustomer(int idCustomer) { this.idCustomer = idCustomer; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCccd() { return cccd; }
    public void setCccd(String cccd) { this.cccd = cccd; }

    public String getPhone() { return phoneNumber; }
    public void setPhone(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

 

}

