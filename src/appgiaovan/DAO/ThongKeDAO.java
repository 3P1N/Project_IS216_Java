package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.TK_DonHang;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKeDAO {

    public static List<TK_DonHang> getListTKDonHang() throws SQLException, ClassNotFoundException {
        List<TK_DonHang> list = new ArrayList<>();
        String sql = "SELECT "
                + "d.THOIGIANTAO AS NGAY, "
                + "SUM(CASE WHEN D.TRANGTHAI = N'Đã giao' THEN 1 ELSE 0 END) AS SoLuongDaGiao, "
                + "SUM(CASE WHEN D.TRANGTHAI = N'Giao thất bại' THEN 1 ELSE 0 END) AS SoLuongThatBai, "
                + "SUM(CASE WHEN D.TRANGTHAI = N'Hủy' THEN 1 ELSE 0 END) AS SoLuongDaHuy "
                + "FROM DONHANG d "
                + "GROUP BY d.THOIGIANTAO "
                + "ORDER BY d.THOIGIANTAO";

        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            java.sql.Date ngay = rs.getDate("NGAY");
            int soLuongDaGiao = rs.getInt("SOLUONGDAGIAO");
            int soLuongThatBai = rs.getInt("SOLUONGTHATBAI");
            int soLuongDaHuy = rs.getInt("SOLUONGDAHUY");

            int tongSoDonHang = soLuongDaGiao + soLuongThatBai + soLuongDaHuy;

            TK_DonHang donhang = new TK_DonHang(
                    ngay,
                    tongSoDonHang,
                    soLuongDaGiao,
                    soLuongThatBai,
                    soLuongDaHuy
            );

            list.add(donhang);
        }

        return list;
    }

    public static void main(String[] args) {
        try {
            List<TK_DonHang> list = getListTKDonHang();
            for (TK_DonHang item : list) {
                System.out.println("Ngày: " + item.getNgay());
                System.out.println("Tổng số đơn hàng: " + item.getTongSoDonHang());
                System.out.println("Số lượng đã giao: " + item.getSoLuongDaGiao());
                System.out.println("Số lượng giao thất bại: " + item.getSoLuongThatBai());
                System.out.println("Số lượng đã hủy: " + item.getSoLuongDaHuy());
                System.out.println("----------------------------------------");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
