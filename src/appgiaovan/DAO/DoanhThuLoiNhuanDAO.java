package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.DoanhThuLoiNhuan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoanhThuLoiNhuanDAO {

    // Thông tin kết nối DB - thay đổi theo cấu hình bạn
    

    public List<DoanhThuLoiNhuan> getListDoanhThuLoiNhuan() throws SQLException, ClassNotFoundException {
        List<DoanhThuLoiNhuan> list = new ArrayList<>();

        String sql = "SELECT NGAY, DOANH_THU, LOI_NHUAN FROM DOANH_THU_LOI_NHUAN ORDER BY NGAY";

        try (
            Connection conn = ConnectionUtils.getMyConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Date ngay = rs.getDate("NGAY");
                Double doanhThu = rs.getDouble("DOANH_THU");
                Double loiNhuan = rs.getDouble("LOI_NHUAN");

                DoanhThuLoiNhuan dtln = new DoanhThuLoiNhuan(ngay, doanhThu, loiNhuan);
                list.add(dtln);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
