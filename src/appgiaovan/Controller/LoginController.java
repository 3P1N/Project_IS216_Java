/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Controller;

import appgiaovan.DAO.TaiKhoanDAO;
import static appgiaovan.PasswordHashing.hashPassword;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class LoginController {
    //public TaiKhoanDAO u = new TaiKhoanDAO();
    public String yeuCauXacThuc(String a, String b) throws SQLException, ClassNotFoundException{
        //b = hashPassword(b);
        
        return new TaiKhoanDAO().xacThucThongTin(a,b);
    }
}
