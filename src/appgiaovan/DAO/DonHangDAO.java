package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.KhoHang;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonHangDAO {

    public void ThemDonHang(DonHang donHang) throws SQLException, ClassNotFoundException {
        String sql = "{call ThemDonHang(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = ConnectionUtils.getMyConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            if (donHang.getIdKhachHang() != null) {
                cs.setInt(1, donHang.getIdKhachHang());
            } else {
                cs.setNull(1, java.sql.Types.INTEGER);
            }

            cs.setString(2, donHang.getSdtNguoiGui());
            cs.setString(3, donHang.getSdtNguoiNhan());
            cs.setInt(4, donHang.getIdKhoTiepNhan());
            cs.setString(5, donHang.getTenNguoiGui());
            cs.setString(6, donHang.getTenNguoiNhan());
            cs.setString(7, donHang.getDiaChiNhan());
            if (donHang.getTienCOD() != null) {
                cs.setDouble(8, donHang.getTienCOD());
            } else {
                cs.setNull(8, java.sql.Types.INTEGER);
            }

            cs.setString(9, donHang.getDichVu());
            cs.setString(10, donHang.getLoaiHangHoa());

            cs.execute();

        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi procedure ThemDonHang: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<DonHang> LayDSDonHang(DonHang donHang) throws SQLException, ClassNotFoundException {
        List<DonHang> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM DonHang WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (null == donHang.getIdDonHang()) {
        } else {
            sql.append(" AND ID_DonHang = ?");
            params.add(donHang.getIdDonHang());
        }

//        if (donHang.getDichVu() != null && !donHang.getDichVu().isEmpty()) {
//            sql.append(" AND DichVu LIKE ?");
//            params.add("%" + donHang.getDichVu() + "%");
//        }

        if (donHang.getTenNguoiGui() != null && !donHang.getTenNguoiGui().isEmpty()) {
            sql.append(" AND TenNguoiGui LIKE ?");
            params.add("%" + donHang.getTenNguoiGui() + "%");
        }

        sql.append(" ORDER BY ID_DonHang");

        try (
                Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DonHang dh = new DonHang();
                dh.setIdDonHang(rs.getInt("ID_DonHang"));
                dh.setIdKhachHang(rs.getInt("ID_KhachHang"));
                dh.setIdNVGiaoHang(rs.getInt("ID_NVGiaoHang"));
                dh.setSdtNguoiGui(rs.getString("SDTNguoiGui"));
                dh.setSdtNguoiNhan(rs.getString("SDTNguoiNhan"));
                dh.setIdKhoTiepNhan(rs.getInt("ID_KhoTiepNhan"));
                dh.setTenNguoiGui(rs.getString("TenNguoiGui"));
                dh.setTenNguoiNhan(rs.getString("TenNguoiNhan"));
                dh.setDiaChiNhan(rs.getString("DiaChiNhan"));
                dh.setTienCOD(rs.getDouble("TienCOD"));
                dh.setPhi(rs.getDouble("Phi"));
                dh.setThoiGianNhan(rs.getTimestamp("ThoiGianNhan"));
                dh.setThoiGianTao(rs.getTimestamp("ThoiGianTao"));
                dh.setThoiGianDuKien(rs.getTimestamp("ThoiGianDuKien"));
                dh.setTrangThai(rs.getString("TrangThai"));
                dh.setDichVu(rs.getString("DichVu"));
                dh.setLoaiHangHoa(rs.getString("LoaiHangHoa"));

                list.add(dh);
            }
        }

        return list;
    }

    public List<DonHang> LayDSDonHang() throws SQLException, ClassNotFoundException {
        List<DonHang> list = new ArrayList<>();

        String sql = "SELECT * FROM DonHang ORDER BY ID_DONHANG";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DonHang dh = new DonHang();
                dh.setIdDonHang(rs.getInt("ID_DonHang"));
                dh.setIdKhachHang(rs.getInt("ID_KhachHang"));
                dh.setIdNVGiaoHang(rs.getInt("ID_NVGiaoHang"));
                dh.setSdtNguoiGui(rs.getString("SDTNguoiGui"));
                dh.setSdtNguoiNhan(rs.getString("SDTNguoiNhan"));
                dh.setIdKhoTiepNhan(rs.getInt("ID_KhoTiepNhan"));
                dh.setTenNguoiGui(rs.getString("TenNguoiGui"));
                dh.setTenNguoiNhan(rs.getString("TenNguoiNhan"));
                dh.setDiaChiNhan(rs.getString("DiaChiNhan"));
                dh.setTienCOD(rs.getDouble("TienCOD"));
                dh.setPhi(rs.getDouble("Phi"));
                dh.setThoiGianNhan(rs.getTimestamp("ThoiGianNhan"));
                dh.setThoiGianTao(rs.getTimestamp("ThoiGianTao"));
                dh.setThoiGianDuKien(rs.getTimestamp("ThoiGianDuKien"));
                dh.setTrangThai(rs.getString("TrangThai"));
                dh.setDichVu(rs.getString("DichVu"));
                dh.setLoaiHangHoa(rs.getString("LoaiHangHoa"));

                list.add(dh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        DonHangDAO dao = new DonHangDAO();
        try {
            List<DonHang> ds = dao.LayDSDonHang();
            for (DonHang dh : ds) {
                System.out.println("ID: " + dh.getIdDonHang()
                        + ", Khách hàng: " + dh.getIdKhachHang()
                        + ", Người gửi: " + dh.getTenNguoiGui()
                        + ", Người nhận: " + dh.getTenNguoiNhan()
                        + ", Địa chỉ nhận: " + dh.getDiaChiNhan()
                        + ", Tiền COD: " + dh.getTienCOD()
                        + ", Trạng thái: " + dh.getTrangThai());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
