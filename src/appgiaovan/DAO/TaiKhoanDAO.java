package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.TaiKhoan;
import static appgiaovan.PasswordHashing.hashPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDAO {

    public TaiKhoan xacThucThongTin(String user, String pass) throws SQLException, ClassNotFoundException {
        String sql = "SELECT ID_TAIKHOAN, TenTaiKhoan, MatKhauMaHoa, VaiTro FROM TAIKHOAN WHERE TENTAIKHOAN = ?";

        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, user);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String passH = rs.getString("MatKhauMaHoa");

                    // So sánh mật khẩu sau khi mã hóa
                    if (passH.equals(hashPassword(pass))) {
                        int id = rs.getInt("ID_TAIKHOAN");
                        String vaiTro = rs.getString("VaiTro");
                        return new TaiKhoan(id, vaiTro);
                    }
                }
            }
        }

        // Trả về null nếu sai tên đăng nhập hoặc mật khẩu
        return null;
    }

    public TaiKhoan LayThongTinTaiKhoan(int idtk) throws SQLException, ClassNotFoundException {
        String sql = "SELECT ID_TAIKHOAN, TenTaiKhoan, MatKhauMaHoa, VaiTro FROM TAIKHOAN WHERE ID_TaiKhoan = ?";
        TaiKhoan tk = null;

        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, idtk);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    tk = new TaiKhoan();
                    tk.setIdTaiKhoan(rs.getInt("ID_TAIKHOAN"));
                    tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                   
                    tk.setVaiTro(rs.getString("VaiTro"));
                } else {
                    return null; // Không tìm thấy tài khoản
                }
            }

            // Nếu tìm thấy, tiếp tục lấy thông tin từ bảng tương ứng
            String vaiTro = tk.getVaiTro();
            String hoTen = null;

            String tableName = null;
            if ("NVK".equals(vaiTro)) {
                tableName = "NhanVienKho";
                tk.setVaiTro("Nhân Viên Kho");
            } else if ("KH".equals(vaiTro)) {
                tableName = "KHACHHANG";
                tk.setVaiTro("Khách hàng");
            }else if ("QL".equals(vaiTro)) {
                tableName = "QUANLY";
                tk.setVaiTro("Quản lý");
            }else if ("NVGH".equals(vaiTro)) {
                tableName = "NHANVIENGIAOHANG";
                tk.setVaiTro("Nhân Viên giao hàng");
            }

            if (tableName != null) {
                String sql2 = "SELECT HoTen FROM " + tableName + " WHERE ID_TaiKhoan = ?";
                try (PreparedStatement st2 = conn.prepareStatement(sql2)) {
                    st2.setInt(1, idtk);
                    try (ResultSet rs2 = st2.executeQuery()) {
                        if (rs2.next()) {
                            hoTen = rs2.getString("HoTen");
                            tk.setTenNguoiDung(hoTen); // Giả sử class TaiKhoan có setHoTen
                        }
                    }
                }
            }
        }

        return tk;
    }

    static void main(String[] args) throws SQLException, ClassNotFoundException {
        TaiKhoan tk = new TaiKhoanDAO().LayThongTinTaiKhoan(29);
        System.out.println(tk.getTenNguoiDung());
    }

    public void CapNhatMK(String hashPassword, int ID_TaiKhoan) throws SQLException, ClassNotFoundException {
        
        String sql = "UPDATE TAIKHOAN SET MATKHAUMAHOA = ? WHERE ID_TaiKhoan = ?";

        try(Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            
            st.setString(1, hashPassword);
            st.setInt(2, ID_TaiKhoan);

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật mật khẩu thành công cho: " );
            } else {
                System.out.println("Không tìm thấy tài khoản với email: " );
            }

        } catch (SQLException e) {
            e.printStackTrace(); // hoặc log lỗi nếu cần
        } 
    }

}
