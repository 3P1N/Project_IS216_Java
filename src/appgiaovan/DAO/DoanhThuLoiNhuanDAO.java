package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.DoanhThuLoiNhuan;
import appgiaovan.Entity.TK_DoanhThu;
import appgiaovan.Entity.TK_DonHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoanhThuLoiNhuanDAO {

    // Thông tin kết nối DB - thay đổi theo cấu hình bạn
    
     public static List<TK_DoanhThu> getListDoanhThuLoiNhuan2() throws SQLException, ClassNotFoundException {
        List<TK_DoanhThu> list = new ArrayList<>();
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
    public List<DoanhThuLoiNhuan> getListDoanhThuLoiNhuan() throws SQLException, ClassNotFoundException {
        List<DoanhThuLoiNhuan> list = new ArrayList<>();

        String sql = """
            SELECT 
              LEVEL AS Thang,
              TongDoanhThu(LEVEL, EXTRACT(YEAR FROM SYSDATE)) AS DoanhThu
            FROM dual
            CONNECT BY LEVEL <= (SELECT EXTRACT(MONTH FROM SYSDATE) FROM dual)
            """;

        try (
            Connection conn = ConnectionUtils.getMyConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                int thang = rs.getInt("THANG");
                Double doanhThu = rs.getDouble("DOANHTHU");

                DoanhThuLoiNhuan dtln = new DoanhThuLoiNhuan(thang, doanhThu);
                list.add(dtln);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
