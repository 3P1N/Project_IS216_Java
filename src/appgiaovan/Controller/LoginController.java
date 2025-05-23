/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Controller;

import appgiaovan.DAO.TaiKhoanDAO;
import appgiaovan.Entity.TaiKhoan;
import static appgiaovan.PasswordHashing.hashPassword;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class LoginController {
    private TaiKhoanDAO u = new TaiKhoanDAO();
    //private TaiKhoan tk = new TaiKhoa
    public TaiKhoan yeuCauXacThuc(String a, String b) throws SQLException, ClassNotFoundException{
        //b = hashPassword(b);
        
        return u.xacThucThongTin(a,b);
    }
//    public int layIDTaiKhoan() throws SQLException, ClassNotFoundException{
//        return u.layIDTaiKhoan();
//    }
//    public void main(String[] args){
//        try {
//            System.out.print(layIDTaiKhoan());
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
