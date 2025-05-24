/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Controller;
import appgiaovan.DAO.NhanVienGiaoHangDAO;
import appgiaovan.Entity.NhanVienGiaoHang;
import appgiaovan.Entity.TaiKhoan;
import java.util.List;
/**
 *
 * @author pc
 */
public class QLShipperController {
    private NhanVienGiaoHangDAO dao;
    public QLShipperController() throws ClassNotFoundException {
        // Khởi tạo DAO, các kết nối sẽ được thực hiện trong DAO khi cần
        dao = new NhanVienGiaoHangDAO();
    }

    public List<NhanVienGiaoHang> layTatCaNhanVienKho() throws ClassNotFoundException {
        try {
            return dao.LayDSNhanVienGiaoHang();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<NhanVienGiaoHang> timKiemNhanVienKho(String kw) throws ClassNotFoundException {
        try {
            return dao.timKiemNhanVienGiaoHang(kw);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public int layMaNhanVienGiaoHangMoi() throws ClassNotFoundException {
        try {
            return dao.layMaNhanVienGiaoHangMoi();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public NhanVienGiaoHang layThongTinNhanVienGiaoHang(int id) {
        try {
            return dao.layThongTinNhanVienGiaoHang(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean taoNhanVienGiaoHang(NhanVienGiaoHang nv, TaiKhoan tk) {
        try {
            return dao.themNhanVienGiaoHang(nv, tk);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaNhanVienGiaoHang(int id) {
        try {
            return dao.xoaNhanVienGiaoHang(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaNhanVienGiaoHang(NhanVienGiaoHang nv) {
        try {
            return dao.suaNhanVienGiaoHang(nv);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
