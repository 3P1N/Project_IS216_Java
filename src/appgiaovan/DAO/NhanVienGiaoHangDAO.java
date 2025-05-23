/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.NhanVienGiaoHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class NhanVienGiaoHangDAO {
    public NhanVienGiaoHangDAO(){
        
    }
    public List<NhanVienGiaoHang> LayDSNhanVienGiaoHang() throws SQLException, ClassNotFoundException{
         List<NhanVienGiaoHang> list = new ArrayList<>();

        String sql = "SELECT * FROM NhanVienGiaoHang";
        try (Connection conn = ConnectionUtils.getMyConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                NhanVienGiaoHang nv = new NhanVienGiaoHang();
                 nv.setID_NguoiDung(rs.getInt("ID_NVGiaoHang"));
                nv.setID_TaiKhoan(rs.getInt("ID_TaiKhoan"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setSDT(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                
                if("Nam".equals(rs.getString("GioiTinh"))){
                nv.setGioiTinh('M');
            }
                else{
                    nv.setGioiTinh('F');
                }
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setID_Kho(rs.getInt("ID_Kho"));
                nv.setID_QuanLy(rs.getInt("ID_QuanLy"));
                nv.setDanhGia(rs.getInt("DiemDanhGia"));

                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;    }
}
