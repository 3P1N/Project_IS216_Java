/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.KhachHang;
import appgiaovan.Entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ASUS
 */
import java.util.*;

public class TaiKhoanDAO {
    //xac thuc thong tin
    public boolean xacThucThongTin(String user, String pass) throws SQLException, ClassNotFoundException{
    String sql = "SELECT TenTaiKhoan, MatKhauMaHoa FROM TAIKHOAN WHERE TENTAIKHOAN = ?";
        
     return false;
    }
}   
