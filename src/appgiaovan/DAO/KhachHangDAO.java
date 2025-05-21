package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.KhachHang;
import appgiaovan.Entity.TaiKhoan;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    public KhachHangDAO() {
    }

    public KhachHang layThongTinKhachHang(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM KhachHang WHERE ID_KhachHang = ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setID_NguoiDung(rs.getInt("ID_KhachHang"));
                    kh.setID_TaiKhoan(rs.getInt("ID_TaiKhoan"));
                    kh.setHoTen(rs.getString("HoTen"));
                    kh.setSDT(rs.getString("SDT"));
                    kh.setEmail(rs.getString("Email"));
                    kh.setCCCD(rs.getString("CCCD"));
                    kh.setNgaySinh(rs.getDate("NgaySinh"));
                    kh.setGioiTinh(rs.getString("GioiTinh").charAt(0));
                    return kh;

                }
            }
        }
        return null;
    }

    public boolean kiemTraTonTaiThongTin(KhachHang kh) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE CCCD = ? OR Email = ? OR SDT = ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getCCCD());
            ps.setString(2, kh.getEmail());
            ps.setString(3, kh.getSDT());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    /**
     * Tạo mới khách hàng
     */
    public boolean taoKhachHang(KhachHang kh) throws SQLException, ClassNotFoundException {
        int newId = layMaKhachHangMoi();
        kh.setID_NguoiDung(newId);
        String sql = "INSERT INTO KhachHang(ID_KhachHang, ID_TaiKhoan, HoTen, SDT, Email, CCCD, NgaySinh, GioiTinh) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, kh.getID_NguoiDung());
            ps.setInt(2, kh.getID_TaiKhoan());
            ps.setString(3, kh.getHoTen());
            ps.setString(4, kh.getSDT());
            ps.setString(5, kh.getEmail());
            ps.setString(6, kh.getCCCD());
            ps.setDate(7, new java.sql.Date(kh.getNgaySinh().getTime()));
            ps.setString(8, String.valueOf(kh.getGioiTinh()));
            return ps.executeUpdate() > 0;
        }
    }


    public boolean themKhachHang(KhachHang kh, TaiKhoan tk) throws SQLException, ClassNotFoundException {
        String sql = "{ call ThemKhachHang(?, ?, ?, ?, ?,  ?, ?, ?) }";
        System.out.println(tk.getTenTaiKhoan());
        try (Connection conn = ConnectionUtils.getMyConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, tk.getTenTaiKhoan());
            cs.setString(2, tk.getMatKhauMaHoa());
            cs.setString(3, kh.getHoTen());
            cs.setString(4, kh.getSDT());
            cs.setString(5, kh.getEmail());
            cs.setString(6, kh.getCCCD());
            cs.setDate(7, Date.valueOf(LocalDate.of(1995, 5, 21)));
            cs.setString(8, "Nam");
            cs.execute();
        } catch (SQLException e) {

            System.err.println("Lỗi khi gọi function ThemTaiKhoan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Lấy ID khách hàng mới = max(ID_KhachHang) + 1
     */
    public int layMaKhachHangMoi() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COALESCE(MAX(ID_KhachHang),0) AS maxId FROM KhachHang";
        try (Connection conn = ConnectionUtils.getMyConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            return rs.getInt("maxId") + 1;
        }
    }

    /**
     * Xóa khách hàng theo ID
     */
    public boolean xoaKhachHang(int idNguoiDung) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM KhachHang WHERE ID_KhachHang = ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNguoiDung);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean suaKhachHang(KhachHang kh) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE KhachHang SET HoTen=?, SDT=?, Email=?, CCCD=?, NgaySinh=?, GioiTinh=? WHERE ID_KhachHang = ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSDT());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getCCCD());
            ps.setDate(5, new java.sql.Date(kh.getNgaySinh().getTime()));
            ps.setString(6, String.valueOf(kh.getGioiTinh()));
            ps.setInt(7, kh.getID_NguoiDung());
            return ps.executeUpdate() > 0;
        }
    }

    public List<KhachHang> layTatCaKhachHang() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM KhachHang";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            List<KhachHang> results = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setID_NguoiDung(rs.getInt("ID_KhachHang"));
                kh.setID_TaiKhoan(rs.getInt("ID_TaiKhoan"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setCCCD(rs.getString("CCCD"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setGioiTinh(rs.getString("GioiTinh").charAt(0));
                results.add(kh);
            }
            return results;
        }
    }

    public List<KhachHang> timKiemKhachHang(String keyword) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM KhachHang WHERE HoTen LIKE ? OR SDT LIKE ?";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                List<KhachHang> results = new ArrayList<>();
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setID_NguoiDung(rs.getInt("ID_KhachHang"));
                    kh.setID_TaiKhoan(rs.getInt("ID_TaiKhoan"));
                    kh.setHoTen(rs.getString("HoTen"));
                    kh.setSDT(rs.getString("SDT"));
                    kh.setEmail(rs.getString("Email"));
                    kh.setCCCD(rs.getString("CCCD"));
                    kh.setNgaySinh(rs.getDate("NgaySinh"));
                    kh.setGioiTinh(rs.getString("GioiTinh").charAt(0));
                    results.add(kh);
                }
                return results;
            }
        }
    }

    public void ThemTaiKhoan(TaiKhoan tk) throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionUtils.getMyConnection()) {

            String sql = "{ ? = call DOANGIAOVAN.TaoTaiKhoan_Func(?, ?) }";
            try (CallableStatement stmt = conn.prepareCall(sql)) {

                // Đăng ký kiểu trả về (RETURN)
                stmt.registerOutParameter(1, Types.INTEGER); // vị trí 1: RETURN NUMBER

                // Set IN parameters
                stmt.setString(2, tk.getTenTaiKhoan());           // p_TenTaiKhoan
                stmt.setString(3, tk.getMatKhauMaHoa());        // p_MatKhauMaHoa

                // Gọi hàm
                stmt.execute();

                int idTaiKhoan = stmt.getInt(1); // Lấy kết quả trả về
                System.out.println("Tạo tài khoản thành công với ID: " + idTaiKhoan);
            }

        } catch (SQLException e) {
            // Xử lý lỗi chi tiết
            if (e.getErrorCode() == 20011) {
                System.err.println("Tên tài khoản đã tồn tại.");
            } else {
                System.err.println("Lỗi SQL: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        KhachHangDAO khDAO = new KhachHangDAO();
        KhachHang kh = new KhachHang();
        kh.setHoTen("Nguyen Van A");
        kh.setSDT("0901204467");
        kh.setEmail("ab@example.com");
        kh.setCCCD("126856789");

//        kh.setNgaySinh();  // Nếu kh.getNgaySinh() trả về java.sql.Date thì dòng dưới không lỗi

        // Nếu phương thức set chấp nhận java.util.Date hoặc java.sql.Date
        kh.setGioiTinh('M');
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan("o12ok");
        tk.setMatKhauMaHoa("ok123");
        boolean a = khDAO.themKhachHang(kh, tk);
//        khDAO.ThemTaiKhoan(tk);
//
    }

    /**
     * Hàm main để test DAO
     */
//    public static void main(String[] args) {
//        String url = "jdbc:oracle:thin:@localhost:1521:XE";
//        String username = "your_username";
//        String password = "your_password";
//        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//            KhachHangDAO dao = new KhachHangDAO(conn);
//
//            // Tạo đối tượng KhachHang mới
//            KhachHang kh = new KhachHang();
//            kh.setID_TaiKhoan(1);
//            kh.setHoTen("Nguyen Van A");
//            kh.setSDT("0901234567");
//            kh.setEmail("a@example.com");
//            kh.setCCCD("123456789");
//            kh.setNgaySinh(new java.sql.Date(new java.util.Date().getTime()));
//            kh.setGioiTinh('M');
//
//            boolean created = dao.taoKhachHang(kh);
//            System.out.println("Tạo khách hàng: " + (created ? "Thành công" : "Thất bại"));
//
//            // Tìm kiếm
//            List<KhachHang> list = dao.timKiemThongTinKhachHang("Nguyen");
//            for (KhachHang k : list) {
//                System.out.println(k.getID_NguoiDung() + " - " + k.getHoTen());
//            }
//
//            // Cập nhật
//            if (!list.isEmpty()) {
//                KhachHang first = list.get(0);
//                first.setHoTen(first.getHoTen() + " Updated");
//                dao.suaKhachHang(first);
//                System.out.println("Đã cập nhật tên khách hàng ID " + first.getID_NguoiDung());
//            }
//
//            // Xóa mẫu
//            if (!list.isEmpty()) {
//                int delId = list.get(0).getID_NguoiDung();
//                dao.xoaKhachHang(delId);
//                System.out.println("Đã xóa khách hàng ID " + delId);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
