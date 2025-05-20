/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Controller;

import appgiaovan.DAO.KhachHangDAO;
import appgiaovan.Entity.KhachHang;
import appgiaovan.Entity.TaiKhoan;

/**
 *
 * @author nhant
 */
public class DangKyController {
        private KhachHangDAO khachHangDAO=new KhachHangDAO();
        public boolean KiemTraDinhDang(KhachHang khachHang, TaiKhoan taiKhoan) {
            
        //1. họ tên không được để trống
        if (khachHang.getHoTen() == null || khachHang.getHoTen().trim().isEmpty()) {
            return false;
        }
        //2. tên đăng nhập không được để trống
        if (taiKhoan.getTenTaiKhoan() == null || taiKhoan.getTenTaiKhoan().trim().isEmpty()) {
            return false;
        }
        //3. Mật khẩu không được để trống
        if (taiKhoan.getMatKhauMaHoa() == null || taiKhoan.getMatKhauMaHoa().trim().isEmpty()) {
            return false;
        }
        //4. Nhập lại mật khẩu phải trùng với mật khẩu
        
        //5. CCCD không được để trống
        if (khachHang.getCCCD() == null || khachHang.getCCCD().trim().isEmpty()) {
            return false;
        }
        //6. Gmail không được để trống
        if (khachHang.getEmail() == null || khachHang.getEmail().trim().isEmpty()) {
            return false;
        }
        //7. Số điện thoại khách hàng phải bắt đầu bằng '0' và đủ 10 chữ số
        if (khachHang.getSDT() == null || !khachHang.getSDT().matches("0\\d{9}")) {
            return false;
        }
        //8.


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

        

        // Có thể thêm nhiều kiểm tra hơn nếu cần
        return true; // Nếu qua tất cả kiểm tra
    }
}
