// File: XemBaoCaoDAO.java
// Package: appgiaovan.DAO

package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.BaoCaoGiaoHang;
import appgiaovan.Entity.BaoCaoKho;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XemBaoCaoDAO {

    /** Lấy danh sách báo cáo kho, nếu keyword rỗng thì lấy tất cả */
    public List<BaoCaoKho> getBaoCaoKho(String keyword) throws SQLException, ClassNotFoundException {
        List<BaoCaoKho> list = new ArrayList<>();
        String sql = "SELECT idBaoCao, idNhanVien, ngayKhoiTao, kyBaoCao, soGoiHangNhap, soGoiHangXuat "
                   + "FROM BaoCaoKho "
                   + (keyword != null && !keyword.isEmpty() ? "WHERE to_char(kyBaoCao, 'YYYY-MM-DD') LIKE ? " : "")
                   + "ORDER BY kyBaoCao DESC";
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        if (keyword != null && !keyword.isEmpty()) {
            ps.setString(1, "%" + keyword + "%");
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BaoCaoKho bc = new BaoCaoKho();
            bc.setIdBaoCao(rs.getInt("idBaoCao"));
            bc.setIdNhanVien(rs.getInt("idNhanVien"));
            bc.setNgayKhoiTao(rs.getDate("ngayKhoiTao"));
            bc.setKyBaoCao(rs.getDate("kyBaoCao"));
            bc.setSoGoiHangNhap(rs.getInt("soGoiHangNhap"));
            bc.setSoGoiHangXuat(rs.getInt("soGoiHangXuat"));
            list.add(bc);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
    }

    /** Lấy danh sách báo cáo giao hàng, có thể filter theo ngày hoặc idNV tương tự */
    public List<BaoCaoGiaoHang> getBaoCaoGiaoHang(String keyword) throws SQLException, ClassNotFoundException {
        List<BaoCaoGiaoHang> list = new ArrayList<>();
        String sql = "SELECT idBaoCao, idNhanVien, ngayKhoiTao, tongDonHangDaGiao, tongDHGiaoThatBai, tongTienCOD "
                   + "FROM BaoCaoGiaoHang "
                   + (keyword != null && !keyword.isEmpty() ? "WHERE to_char(ngayKhoiTao, 'YYYY-MM-DD') LIKE ? " : "")
                   + "ORDER BY ngayKhoiTao DESC";
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        if (keyword != null && !keyword.isEmpty()) {
            ps.setString(1, "%" + keyword + "%");
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BaoCaoGiaoHang bc = new BaoCaoGiaoHang();
            bc.setIdBaoCao(rs.getInt("idBaoCao"));
            bc.setIdNhanVien(rs.getInt("idNhanVien"));
            bc.setNgayKhoiTao(rs.getDate("ngayKhoiTao"));
            bc.setTongDonHangDaGiao(rs.getInt("tongDonHangDaGiao"));
            bc.setTongDHGiaoThatBai(rs.getInt("tongDHGiaoThatBai"));
            bc.setTongTienCOD(rs.getInt("tongTienCOD"));
            list.add(bc);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
    }
}
