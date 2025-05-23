package appgiaovan.Controller;

import appgiaovan.DAO.DanhGiaDAO;
import appgiaovan.DAO.DonHangDAO;
import appgiaovan.DAO.KhoHangDAO;

import appgiaovan.Entity.DanhGia;

import appgiaovan.DAO.TaiKhoanDAO;

import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.KhoHang;
import appgiaovan.Entity.NhanVienGiaoHang;
import appgiaovan.GUI.LOGIN;
import java.sql.SQLException;
import java.util.List;

public class QLDonHangController {

    private final DonHangDAO donHangDAO = new DonHangDAO();
    private final DanhGiaDAO danhGiaDAO = new DanhGiaDAO();
    
    public List<KhoHang> LayThongTinKho() throws SQLException, ClassNotFoundException {
        KhoHangDAO khoHangDAO = new KhoHangDAO();
        return khoHangDAO.LayThongTinKho();

    }

    public void ThemDonHang(DonHang donHang) throws SQLException, ClassNotFoundException {
        donHangDAO.ThemDonHang(donHang);

    }
    
    public void SuaDonHang(DonHang donHang) throws SQLException, ClassNotFoundException{
        donHangDAO.SuaDonHang(donHang);
    }

    public boolean KiemTraDinhDang(DonHang donHang) {
        // 1. Số điện thoại phải bắt đầu bằng '0' và đủ 10 chữ số
        if (donHang.getSdtNguoiGui() == null || !donHang.getSdtNguoiGui().matches("0\\d{9}")) {
            return false;
        }

        if (donHang.getSdtNguoiNhan() == null || !donHang.getSdtNguoiNhan().matches("0\\d{9}")) {
            return false;
        }

        // 2. Tên người gửi/nhận không được null hoặc rỗng
        if (donHang.getTenNguoiGui() == null || donHang.getTenNguoiGui().trim().isEmpty()) {
            return false;
        }

        if (donHang.getTenNguoiNhan() == null || donHang.getTenNguoiNhan().trim().isEmpty()) {
            return false;
        }

        // 3. Địa chỉ nhận không được null hoặc rỗng
        if (donHang.getDiaChiNhan() == null || donHang.getDiaChiNhan().trim().isEmpty()) {
            return false;
        }

        // 4. Dịch vụ phải là 'Tiết kiệm', 'Nhanh' hoặc 'Hỏa tốc'
        String dichVu = donHang.getDichVu();
        if (!(dichVu.equals("Tiết kiệm") || dichVu.equals("Nhanh") || dichVu.equals("Hỏa tốc"))) {
            return false;
        }
        // 5. Loại hàng hóa không được null hoặc rỗng
        // Có thể thêm nhiều kiểm tra hơn nếu cần
        // Nếu qua tất cả kiểm tra

        return !(donHang.getLoaiHangHoa() == null || donHang.getLoaiHangHoa().trim().isEmpty()); 
    }
    public List<DonHang> LayDSDonHang() throws SQLException, ClassNotFoundException{
        return donHangDAO.LayDSDonHang();
    }

    public List<DonHang> LayDSDonHang(DonHang dh) throws SQLException, ClassNotFoundException{
        return donHangDAO.LayDSDonHang(dh);
    }

    public void ThemDanhGia(DanhGia danhGia) throws SQLException, ClassNotFoundException {
        danhGiaDAO.ThemDanhGia(danhGia);
    }
   // String ab = new LOGIN().getuser();
    //int idTaiKhoan;
    public List<DonHang> HienThiDSDHChoNVGH(int idtk) throws SQLException, ClassNotFoundException{
        //int idTaiKhoan = new LoginController().layIDTaiKhoan() ;
        //String us = new LOGIN().getuser();
        return new DonHangDAO().layDSDonHangCuaNVGH(idtk);
    }

    
    public void PhanCongGiaoHang(NhanVienGiaoHang nv, List<Integer> listIdDonHang) throws SQLException, ClassNotFoundException{
        donHangDAO.PhanCongGiaoHang(nv,listIdDonHang);
    }


    public DonHang layThongTinDH(int ID_DonHang) throws SQLException, ClassNotFoundException{
        
        return donHangDAO.LayThongTinDonHang(ID_DonHang);
    }

    static public void main(String[] args ){
        System.out.print("Test");

    }

    public void HuyDonHang(int ID_DonHang) throws SQLException, ClassNotFoundException {
        donHangDAO.HuyDonHang(ID_DonHang);
    }
    
}
