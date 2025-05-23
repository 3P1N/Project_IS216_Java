/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.DAO;

/**
 *
 * @author pc
 */
import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.NhanVienKho;
import appgiaovan.Entity.TaiKhoan;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhanVienKhoDAO {
    public NhanVienKhoDAO(){
        
    }
    
    public NhanVienKho layThongTinNVKho(int id) throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM NhanVienKho WHERE ID_NhanVien = ?";
        try (Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    NhanVienKho nv = new NhanVienKho();
                    nv.setID_NguoiDung(rs.getInt("ID_NhanVien"));
                    nv.setID_TaiKhoan(rs.getInt("ID_TaiKhoan"));
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setSDT(rs.getString("SDT"));
                    nv.setEmail(rs.getString("Email"));
                    nv.setCCCD(rs.getString("CCCD"));
                    nv.setNgaySinh(rs.getDate("NgaySinh"));
                    nv.setGioiTinh(rs.getString("GioiTinh"));
                    nv.setDiaChi(rs.getString("DiaChi"));
                    nv.setID_Kho(rs.getInt("ID_Kho"));
                    nv.setID_QuanLy(rs.getInt("ID_QuanLy"));
                    nv.setMucLuong(rs.getDouble("MucLuong"));
                    return nv;
                }
            }
        }
        return null;
    }
    
    public boolean kiemTraTonTaiThongTin(NhanVienKho nv) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM NhanVienKho WHERE CCCD = ? OR Email = ? OR SDT = ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getCCCD());
            ps.setString(2, nv.getEmail());
            ps.setString(3, nv.getSDT());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }
    
    public boolean themNVKho(NhanVienKho nv, TaiKhoan tk) throws SQLException, ClassNotFoundException {
        String sql = "{ call ThemNhanVienKho(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
        System.out.println(tk.getTenTaiKhoan());
        try (Connection conn = ConnectionUtils.getMyConnection(); 
            CallableStatement cs = conn.prepareCall(sql)) 
        {
            cs.setString(1, tk.getTenTaiKhoan());
            cs.setString(2, tk.getMatKhauMaHoa());
            cs.setString(3, nv.getHoTen());
            cs.setString(4, nv.getSDT());
            cs.setString(5, nv.getEmail());
            cs.setString(6, nv.getCCCD());
            java.util.Date utilDate = nv.getNgaySinh();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(utilDate);
            System.out.println(dateStr);
            cs.setDate(7, Date.valueOf(dateStr));
            cs.setString(8, nv.getGioiTinh());
            cs.setString(9, nv.getDiaChi());
            cs.setInt(10, nv.getID_Kho());
            cs.setInt(11, nv.getID_QuanLy());
            cs.setDouble(12, nv.getMucLuong());
            cs.execute();
        } catch (SQLException e) {

            System.err.println("Lỗi khi gọi function ThemTaiKhoan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
