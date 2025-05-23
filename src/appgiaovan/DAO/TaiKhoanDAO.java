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

        try (Connection conn = ConnectionUtils.getMyConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

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
}
