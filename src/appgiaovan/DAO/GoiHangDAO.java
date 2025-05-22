/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.GoiHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

/**
 *
 * @author HP
 */
public class GoiHangDAO {

    public List<GoiHang> LayDSGoiHang() throws SQLException, ClassNotFoundException {
        List<GoiHang> list = new ArrayList<>();

        String sql = "SELECT * FROM GoiHang ORDER BY ID_GOIHANG desc";
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GoiHang gh = new GoiHang();
                gh.setIdGoiHang(rs.getInt("ID_GoiHang"));
                gh.setIdKhoHangDen(rs.getInt("ID_KhoHangDen"));
                gh.setIdKhoHangGui(rs.getInt("ID_KhoHangGui"));
                gh.setIdNhanVien(rs.getInt("ID_NhanVien"));
//                gh.setNgayGui(rs.getDate(sql));

                list.add(gh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void ThemGoiHang(GoiHang goiHang, List<Integer> listDonHang) throws SQLException, ClassNotFoundException {
        String sql = "{call ThemGoiHang(?, ?, ?, ?)}";
        Integer[] donHangArray = listDonHang.toArray(Integer[]::new);
        try (Connection conn = ConnectionUtils.getMyConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor("DONHANGLIST", conn);
            ARRAY oracleArray = new ARRAY(descriptor, conn, donHangArray);

            stmt.setInt(1, 1); // ID_KhoHangGui
            stmt.setInt(2, 2); // ID_KhoHangDen
            stmt.setInt(3, 10); // ID_NhanVien
            stmt.setArray(4, oracleArray);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] agrs){
        
    }
}
