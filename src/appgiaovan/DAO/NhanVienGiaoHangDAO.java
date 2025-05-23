/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.NhanVienGiaoHang;
import appgiaovan.Entity.TaiKhoan;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.text.ParseException;



/**
 *
 * @author pc
 */
public class NhanVienGiaoHangDAO {
    public NhanVienGiaoHangDAO(){
        
    }
    public List<NhanVienGiaoHang> LayDSNhanVienGiaoHang() throws SQLException, ClassNotFoundException {
        List<NhanVienGiaoHang> list = new ArrayList<>();

        String sql = "SELECT * FROM NhanVienGiaoHang";
        try (Connection conn = ConnectionUtils.getMyConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql); 
                ResultSet rs = stmt.executeQuery()) 
        {
            while (rs.next()) {
                NhanVienGiaoHang sh = new NhanVienGiaoHang();
                sh.setID_NguoiDung(rs.getInt("ID_NVGiaoHang"));
                sh.setID_TaiKhoan(rs.getInt("ID_TaiKhoan"));
                sh.setHoTen(rs.getString("HoTen"));
                sh.setSDT(rs.getString("SDT"));
                sh.setEmail(rs.getString("Email"));
                sh.setCCCD(rs.getString("CCCD"));
                sh.setNgaySinh(rs.getDate("NgaySinh"));
                sh.setGioiTinh(rs.getString("GioiTinh"));
                
                sh.setDiaChi(rs.getString("DiaChi"));
                sh.setID_Kho(rs.getInt("ID_Kho"));
                sh.setID_QuanLy(rs.getInt("ID_QuanLy"));
                sh.setDiemDanhGia(rs.getInt("DiemDanhGia"));

                list.add(sh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;    
    }
    public NhanVienGiaoHang layThongTinNhanVienGiaoHang(int id) throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM NhanVienGiaoHang WHERE ID_NVGiaoHang = ?";
        try (Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    NhanVienGiaoHang sh = new NhanVienGiaoHang();
                    sh.setID_NguoiDung(rs.getInt("ID_NhanVien"));
                    sh.setID_TaiKhoan(rs.getInt("ID_TaiKhoan"));
                    sh.setHoTen(rs.getString("HoTen"));
                    sh.setSDT(rs.getString("SDT"));
                    sh.setEmail(rs.getString("Email"));
                    sh.setCCCD(rs.getString("CCCD"));
                    sh.setNgaySinh(rs.getDate("NgaySinh"));
                    sh.setGioiTinh(rs.getString("GioiTinh"));
                    sh.setDiaChi(rs.getString("DiaChi"));
                    sh.setID_Kho(rs.getInt("ID_Kho"));
                    sh.setID_QuanLy(rs.getInt("ID_QuanLy"));
                    sh.setDiemDanhGia(rs.getInt("DiemDanhGia"));
                    return sh;
                }
            }
        }
        return null;
    }
    
    public boolean kiemTraTonTaiThongTin(NhanVienGiaoHang sh) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM NhanVienGiaoHang WHERE CCCD = ? OR Email = ? OR SDT = ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sh.getCCCD());
            ps.setString(2, sh.getEmail());
            ps.setString(3, sh.getSDT());
            try (ResultSet rs = ps.executeQuery()){
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public boolean themNhanVienGiaoHang(NhanVienGiaoHang sh, TaiKhoan tk) throws SQLException, ClassNotFoundException {
        String sql = "{ call ThemNhanVienGiaoHang(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
        System.out.println(tk.getTenTaiKhoan());
        try (Connection conn = ConnectionUtils.getMyConnection(); 
            CallableStatement cs = conn.prepareCall(sql))
        {
            cs.setString(1, tk.getTenTaiKhoan());
            cs.setString(2, tk.getMatKhauMaHoa());
            cs.setString(3, sh.getHoTen());
            cs.setString(4, sh.getSDT());
            cs.setString(5, sh.getEmail());
            cs.setString(6, sh.getCCCD());
            java.util.Date utilDate = sh.getNgaySinh();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(utilDate);
            System.out.println(dateStr);
            cs.setDate(7, java.sql.Date.valueOf(dateStr));
            cs.setString(8, sh.getGioiTinh());
            cs.setString(9, sh.getDiaChi());
            cs.setInt(10, sh.getID_Kho());
            cs.setInt(11, sh.getID_QuanLy());
            cs.setInt(12, sh.getDiemDanhGia());
            cs.execute();
        } catch (SQLException e) {

            System.err.println("Lỗi khi gọi function ThemTaiKhoan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public int layMaNhanVienGiaoHangMoi() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COALESCE(MAX(ID_NVGiaoHang),0) AS maxId FROM NhanVienGiaoHang";
        try (Connection conn = ConnectionUtils.getMyConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            return rs.getInt("maxId") + 1;
        }
    }
    public boolean xoaNhanVienGiaoHang(int idNguoiDung) throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM NhanVienGiaoHang WHERE ID_NVGiaoHang = ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNguoiDung);
            return ps.executeUpdate() > 0;
        }
    }
        
    public boolean suaNhanVienGiaoHang(NhanVienGiaoHang nv) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE NhanVienGiaoHang SET HoTen=?, SDT=?, Email=?, CCCD=?, NgaySinh=?, GioiTinh=?, DiaChi=?, ID_Kho =?, ID_QuanLy=?, DiemDanhGia = ? WHERE ID_NVGiaoHang = ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getSDT());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getCCCD());
            ps.setDate(5, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(6, nv.getGioiTinh());
            ps.setString(7, nv.getDiaChi());
            ps.setInt(8, nv.getID_Kho());
            ps.setInt(9, nv.getID_QuanLy());
            ps.setDouble(10, nv.getDiemDanhGia());
            ps.setInt(11, nv.getID_NguoiDung());


            return ps.executeUpdate() > 0;
        }
    }
    

    public List<NhanVienGiaoHang> timKiemNhanVienGiaoHang(String keyword) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM NhanVienGiaoHang WHERE HoTen LIKE ? OR SDT LIKE ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                List<NhanVienGiaoHang> results = new ArrayList<>();
                while (rs.next()) {
                    NhanVienGiaoHang nv = new NhanVienGiaoHang();
                    nv.setID_NguoiDung(rs.getInt("ID_KhachHang"));
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
                    nv.setDiemDanhGia(rs.getInt("DiemDanhGia"));  
                    results.add(nv);
                }
                return results;
            }
        }
    }
    
    public void ThemTaiKhoan(TaiKhoan tk) throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionUtils.getMyConnection()) {

            String sql = "{ ? = call DOANGIAOVAN.TaoTaiKhoan_Func(?, ?) }";
            try (CallableStatement stmt = conn.prepareCall(sql)) {

              
                stmt.registerOutParameter(1, Types.INTEGER); 

                // Set IN parameters
                stmt.setString(2, tk.getTenTaiKhoan());           // p_TenTaiKhoan
                stmt.setString(3, tk.getMatKhauMaHoa());        // p_MatKhauMaHoa

                // Gọi hàm
                stmt.execute();

                int idTaiKhoan = stmt.getInt(1); // Lấy kết quả trả về
                System.out.println("Tạo tài khoản thành công với ID: " + idTaiKhoan);
            }

        } catch (SQLException e) {
            // Xử lý lỗi chi tiết
            if (e.getErrorCode() == 20011) {
                System.err.println("Tên tài khoản đã tồn tại.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
        }
    }
    
    public int layID_NhanVienGiaoHang(int ID_TaiKhoan) throws SQLException, ClassNotFoundException {
        String sql = "SELECT ID_NVGiaoHang FROM NhanVienGiaoHang WHERE ID_NVGiaoHang=?";
        try (Connection conn = ConnectionUtils.getMyConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ID_TaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int ID_NhanVien=rs.getInt("ID_NVGiaoHang");
                    return ID_NhanVien;
                }
            }
        }
        return -1;
    }
}

