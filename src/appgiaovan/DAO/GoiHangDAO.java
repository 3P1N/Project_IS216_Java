/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.DAO;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.GoiHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GoiHangDAO {
    
    public List<GoiHang> LayDSGoiHang() throws SQLException, ClassNotFoundException{
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
}
