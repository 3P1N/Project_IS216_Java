/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.DAO;
import appgiaovan.ConnectDB.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import appgiaovan.Entity.KhachHang;
public class KhachHangDAO {

    // Lấy thông tin cá nhân theo ID
    public KhachHang layThongTinCaNhan(int idKhachHang) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM KhachHang WHERE ID_KhachHang = ?";
        try (Connection conn = ConnectionUtils.getMyConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idKhachHang);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new KhachHang(
                        rs.getInt("ID_KhachHang"),
                        rs.getInt("ID_TaiKhoan"),
                        rs.getString("HoTen"),
                        rs.getString("SDT"),
                        rs.getString("Email"),
                        rs.getString("CCCD"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("DiaChi"),
                        rs.getDouble("MucLuong")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // không tìm thấy
    }

    // Cập nhật thông tin khách hàng
    public boolean capNhatThongTin(KhachHang khach) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE KhachHang SET HoTen=?, SDT=?, Email=?, CCCD=?, NgaySinh=?, " +
                     "GioiTinh=?, DiaChi=?, MucLuong=? WHERE ID_KhachHang=?";

        try (Connection conn = ConnectionUtils.getMyConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, khach.getHoTen());
            stmt.setString(2, khach.getSdt());
            stmt.setString(3, khach.getEmail());
            stmt.setString(4, khach.getCccd());
            stmt.setDate(5, khach.getNgaySinh());
            stmt.setString(6, khach.getGioiTinh());
            stmt.setString(7, khach.getDiaChi());
            stmt.setDouble(8, khach.getMucLuong());
            stmt.setInt(9, khach.getIdKhachHang());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
