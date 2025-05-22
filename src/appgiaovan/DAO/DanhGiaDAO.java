/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.DanhGia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhant
 */
public class DanhGiaDAO {

    public void ThemDanhGia(DanhGia dg) throws SQLException, ClassNotFoundException {
        String sql = "{INSERT INTO DanhGia (ID_DanhGia, ID_DonHang, ID_KhachHang, NoiDungDanhGia, DiemDanhGia, NgayTao)\n" +
"VALUES (SEQ_DANHGIA_ID.NEXTVAL, ?, ?, ?, ?, ?)}";

        try (Connection conn = ConnectionUtils.getMyConnection();CallableStatement cs = conn.prepareCall(sql)) {
//            if (donHang.getIdDonHang() != null) {
//                cs.setInt(1, donHang.getIdDonHang());
//            } else {
//                cs.setNull(1, Types.INTEGER);
//            }

            if (donHang.getIdKhachHang() != null) {
                cs.setInt(1, donHang.getIdKhachHang());
            } else {
                cs.setNull(1, Types.INTEGER);
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
                cs.setNull(8, Types.INTEGER);
            }

            cs.setString(9, donHang.getDichVu());
            cs.setString(10, donHang.getLoaiHangHoa());

            cs.execute();

        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi procedure ThemDonHang: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
