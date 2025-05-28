package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import java.sql.*;
import java.util.*;

public class ThongKeDAO {
    private Connection conn;

    public ThongKeDAO() throws SQLException, ClassNotFoundException {
        // Giả sử bạn đã có class DBConnection.getConnection()
        this.conn = ConnectionUtils.getMyConnection();
    }

    public List<Map<String, Object>> layTatCaThongKe() {
        List<Map<String, Object>> result = new ArrayList<>();

        try {
            List<Map<String, Object>> doanhThuList = layThongKeDoanhThu();
            List<Map<String, Object>> donHangList = layThongKeDonHang();
            List<Map<String, Object>> danhGiaList = layThongKeDanhGia();

            int maxSize = Math.max(doanhThuList.size(), Math.max(donHangList.size(), danhGiaList.size()));

            for (int i = 0; i < maxSize; i++) {
                Map<String, Object> row = new HashMap<>();

                if (i < doanhThuList.size()) {
                    row.putAll(doanhThuList.get(i));
                }

                if (i < donHangList.size()) {
                    row.putAll(donHangList.get(i));
                }

                if (i < danhGiaList.size()) {
                    row.putAll(danhGiaList.get(i));
                }

                result.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Map<String, Object>> layThongKeDoanhThu() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();

        String sql = "SELECT * FROM TK_DoanhThu ORDER BY Ngay";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("ngay", rs.getDate("Ngay"));
            row.put("doanhthu", rs.getDouble("TongTien"));
            list.add(row);
        }

        rs.close();
        ps.close();

        return list;
    }

    public List<Map<String, Object>> layThongKeDonHang() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();

        String sql = "SELECT * FROM TK_DonHang ORDER BY Ngay";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("ngay", rs.getDate("Ngay"));
            row.put("donhang", rs.getInt("TongSoDonHang"));
            row.put("dagiao", rs.getInt("SoLuongDaGiao"));
            row.put("thathbai", rs.getInt("SoLuongThatBai"));
            row.put("dahuy", rs.getInt("SoLuongDaHuy"));
            list.add(row);
        }

        rs.close();
        ps.close();

        return list;
    }

    public List<Map<String, Object>> layThongKeDanhGia() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();

        String sql = "SELECT * FROM TK_DanhGia ORDER BY Ngay";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("ngay", rs.getDate("Ngay"));
            row.put("danhgia", rs.getInt("TongLuotDanhGia"));
            row.put("sao1", rs.getInt("SoLuong1Sao"));
            row.put("sao2", rs.getInt("SoLuong2Sao"));
            row.put("sao3", rs.getInt("SoLuong3Sao"));
            row.put("sao4", rs.getInt("SoLuong4Sao"));
            row.put("sao5", rs.getInt("SoLuong5Sao"));
            list.add(row);
        }

        rs.close();
        ps.close();

        return list;
    }
}
