package appgiaovan.Controller;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.DAO.ThongKeDAO;
import appgiaovan.Entity.DoanhThuLoiNhuan;
import appgiaovan.Entity.TK_DanhGia;
import appgiaovan.Entity.TK_DoanhThu;
import appgiaovan.Entity.TK_DonHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class ThongKeController {

    private ThongKeDAO dao;

    public ThongKeController() throws SQLException, ClassNotFoundException {
        dao = new ThongKeDAO();
    }
        
    public List<TK_DanhGia> getListTKDanhGia() throws SQLException, ClassNotFoundException {
        List<TK_DanhGia> list = new ArrayList<>();
        String sql = "SELECT * FROM TK_DanhGia ORDER BY NGAY";

        try (
                Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Date ngay = rs.getDate("NGAY");
                int soLuong1Sao = rs.getInt("SoLuong1Sao");
                int soLuong2Sao = rs.getInt("SoLuong2Sao");
                int soLuong3Sao = rs.getInt("SoLuong3Sao");
                int soLuong4Sao = rs.getInt("SoLuong4Sao");
                int soLuong5Sao = rs.getInt("SoLuong5Sao");

                int tongLuotDanhGia = soLuong1Sao + soLuong2Sao + soLuong3Sao + soLuong4Sao + soLuong5Sao;

                TK_DanhGia danhgia = new TK_DanhGia(
                        ngay,
                        tongLuotDanhGia,
                        soLuong1Sao,
                        soLuong2Sao,
                        soLuong3Sao,
                        soLuong4Sao,
                        soLuong5Sao
                );

                list.add(danhgia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<TK_DonHang> getListTKDonHang() throws SQLException, ClassNotFoundException {
        return ThongKeDAO.getListTKDonHang();
//        List<TK_DonHang> list = new ArrayList<>();
//        String sql = "SELECT * FROM TK_DonHang ORDER BY NGAY";
//
//        try (
//                Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
//            while (rs.next()) {
//                Date ngay = rs.getDate("NGAY");
//                int soLuongDaGiao = rs.getInt("SoLuongDaGiao");
//                int soLuongThatBai = rs.getInt("SoLuongThatBai");
//                int soLuongDaHuy = rs.getInt("SoLuongDaHuy");
//
//                int tongSoDonHang = soLuongDaGiao + soLuongThatBai + soLuongDaHuy;
//
//                TK_DonHang donhang = new TK_DonHang(
//                        ngay,
//                        tongSoDonHang,
//                        soLuongDaGiao,
//                        soLuongThatBai,
//                        soLuongDaHuy
//                );
//
//                list.add(donhang);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
    }
    public List<TK_DoanhThu> getListTKDoanhThu() throws SQLException, ClassNotFoundException {
        List<TK_DanhGia> list = new ArrayList<>();
        String sql = "SELECT * FROM TK_DanhGia ORDER BY NGAY";


        try (
                Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Date ngay = rs.getDate("NGAY");
                int soLuong1Sao = rs.getInt("SoLuong1Sao");
                int soLuong2Sao = rs.getInt("SoLuong2Sao");
                int soLuong3Sao = rs.getInt("SoLuong3Sao");
                int soLuong4Sao = rs.getInt("SoLuong4Sao");
                int soLuong5Sao = rs.getInt("SoLuong5Sao");

                int tongLuotDanhGia = soLuong1Sao + soLuong2Sao + soLuong3Sao + soLuong4Sao + soLuong5Sao;

                TK_DanhGia danhgia = new TK_DanhGia(
                        ngay,
                        tongLuotDanhGia,
                        soLuong1Sao,
                        soLuong2Sao,
                        soLuong3Sao,
                        soLuong4Sao,
                        soLuong5Sao
                );

                list.add(danhgia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<Map<String, Object>> LayDuLieuThongKe() {
        return dao.layTatCaThongKe();
    }

}
