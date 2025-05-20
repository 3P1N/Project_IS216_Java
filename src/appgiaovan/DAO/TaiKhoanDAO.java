/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.KhachHang;
import appgiaovan.Entity.TaiKhoan;
import static appgiaovan.PasswordHashing.hashPassword;
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
    public String xacThucThongTin(String user, String pass) throws SQLException, ClassNotFoundException{
    String sql = "SELECT TenTaiKhoan, MatKhauMaHoa, VaiTro FROM TAIKHOAN WHERE TENTAIKHOAN = ?";
    Connection conn = ConnectionUtils.getMyConnection();
    PreparedStatement st = conn.prepareStatement(sql);
    st.setString(1, user);
    ResultSet rs = st.executeQuery();
    
    rs.next();
    String tenDN = rs.getString("TenTaiKhoan");
    String passH = rs.getString("MatKhauMaHoa");
    String vaiTro = rs.getString("VaiTro");
    
    if(passH.equals(hashPassword(pass)) )
        return vaiTro;
    return null;
    }
}   
