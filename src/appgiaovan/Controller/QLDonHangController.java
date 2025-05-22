package appgiaovan.Controller;

import appgiaovan.DAO.DanhGiaDAO;
import appgiaovan.DAO.DonHangDAO;
import appgiaovan.DAO.KhoHangDAO;
import appgiaovan.Entity.DanhGia;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.KhoHang;
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
}
