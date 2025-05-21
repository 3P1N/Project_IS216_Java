/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Controller;

import appgiaovan.DAO.KhachHangDAO;
import appgiaovan.DAO.TaiKhoanDAO;
import appgiaovan.Entity.KhachHang;
import appgiaovan.Entity.TaiKhoan;
import javax.swing.JPasswordField;

/**
 *
 * @author nhant
 */
public class DangKyController {
        private KhachHangDAO khachHangDAO=new KhachHangDAO();
        private TaiKhoanDAO taiKhoanDAO=new TaiKhoanDAO();
        public boolean KiemTraDinhDang(KhachHang khachHang, String matKhau,String matKhauNL,String tenDangNhap ) {
            
        //1. họ tên không được để trống
        if (khachHang.getHoTen() == null || khachHang.getHoTen().trim().isEmpty()) {
            return false;
        }
        //2. tên đăng nhập không được để trống
        if (tenDangNhap == null) {
            return false;
        }
        //3. Mật khẩu không được để trống
        if (matKhau == null || matKhauNL == null) {
            return false;
        }
        //4. Nhập lại mật khẩu phải trùng với mật khẩu
        if(matKhau!=matKhauNL){
            return false;
        }
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
        // Có thể thêm nhiều kiểm tra hơn nếu cần
        return true; // Nếu qua tất cả kiểm tra
    }
    public boolean themKhachHang(KhachHang kh) {
        try {
            return khachHangDAO.taoKhachHang(kh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean themTaiKhoan(TaiKhoan tk) {
        try {
            return taiKhoanDAO.themTaiKhoan(tk);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
