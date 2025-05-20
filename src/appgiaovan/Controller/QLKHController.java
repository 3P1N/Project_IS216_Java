package appgiaovan.Controller;

import appgiaovan.DAO.KhachHangDAO;
import appgiaovan.Entity.KhachHang;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class QLKHController {
    private KhachHangDAO dao;
    private Connection conn;

    public QLKHController() {
        try {
            // TODO: Điền URL, username, password của database
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "your_username";
            String password = "your_password";
            conn = DriverManager.getConnection(url, username, password);
            dao = new KhachHangDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi kết nối DB", e);
        }
    }

    public List<KhachHang> layTatCaKhachHang() {
        try {
            return dao.timKiemThongTinKhachHang("");
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<KhachHang> timKiemKhachHang(String kw) {
        try {
            return dao.timKiemThongTinKhachHang(kw);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public int layMaKhachHangMoi() {
        try {
            return dao.layMaKhachHangMoi();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public KhachHang layThongTinKhachHang(int id) {
        try {
            return dao.layThongTinKhachHang(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean taoKhachHang(KhachHang kh) {
        try {
            return dao.taoKhachHang(kh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaKhachHang(int id) {
        try {
            return dao.xoaKhachHang(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaKhachHang(KhachHang kh) {
        try {
            return dao.suaKhachHang(kh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

