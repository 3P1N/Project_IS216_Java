package appgiaovan.Controller;

import appgiaovan.DAO.DonHangDAO;
import appgiaovan.DAO.KhoHangDAO;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.KhoHang;
import java.sql.SQLException;
import java.util.List;

public class QLDonHangController {

    private DonHangDAO donHangDAO = new DonHangDAO();
    
    public List<KhoHang> LayThongTinKho() throws SQLException, ClassNotFoundException {
        KhoHangDAO khoHangDAO = new KhoHangDAO();
        return khoHangDAO.LayThongTinKho();

    }

    public void ThemDonHang(DonHang donHang) throws SQLException, ClassNotFoundException {
        DonHangDAO donHangDAO = new DonHangDAO();
        donHangDAO.ThemDonHang(donHang);

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
        if (donHang.getLoaiHangHoa() == null || donHang.getLoaiHangHoa().trim().isEmpty()) {
            return false;
        }

        // 6. Thời gian nhận nếu có thì phải sau thời gian tạo
        if (donHang.getThoiGianNhan() != null && donHang.getThoiGianTao() != null) {
            if (donHang.getThoiGianNhan().before(donHang.getThoiGianTao())) {
                return false;
            }
        }

        // Có thể thêm nhiều kiểm tra hơn nếu cần
        return true; // Nếu qua tất cả kiểm tra
    }
    public List<DonHang> LayDSDonHang() throws SQLException, ClassNotFoundException{
        return donHangDAO.LayDSDonHang();
    }

}
