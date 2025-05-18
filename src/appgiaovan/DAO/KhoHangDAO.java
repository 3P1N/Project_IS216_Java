package appgiaovan.DAO;


import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Entity.KhoHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhoHangDAO {

    public List<KhoHang> LayThongTinKho() throws SQLException, ClassNotFoundException {
        List<KhoHang> khoList = new ArrayList<>();
        
        String sql = "SELECT * FROM KhoHang";
        try (Connection conn = ConnectionUtils.getMyConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                KhoHang kho = new KhoHang(
                    rs.getInt("ID_Kho"),
                    rs.getString("TenKho"),
                    rs.getInt("ID_QuanLy"),
                    rs.getInt("SLHangToiDa"),
                    rs.getInt("SLHangTon"),
                    rs.getString("DiaChi")
                );
                khoList.add(kho);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return khoList;
    }
    public List<String> LayDSTenKho()throws SQLException, ClassNotFoundException {
        List<String> ListName = new ArrayList<>();

        String sql = "SELECT TenKho FROM KhoHang";
        try (Connection conn = ConnectionUtils.getMyConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                ListName.add(rs.getString("TenKho"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ListName;
    }
    public static void main(String[] args) {
    try {
        KhoHangDAO khoHangDAO = new KhoHangDAO();

        // Test lấy toàn bộ KhoHang
        List<KhoHang> khoList = khoHangDAO.LayThongTinKho();
        System.out.println("DANH SÁCH KHO:");
        for (KhoHang kho : khoList) {
            System.out.println("ID: " + kho.getIdKho() +
                               " | Tên: " + kho.getTenKho() +
                               " | Quản lý: " + kho.getIdQuanLy() +
                               " | Sức chứa: " + kho.getSlHangToiDa() +
                               " | Tồn kho: " + kho.getSlHangTon() +
                               " | Địa chỉ: " + kho.getDiaChi());
        }

        // Test lấy danh sách tên kho
        List<String> tenKhoList = khoHangDAO.LayDSTenKho();
        System.out.println("\nDANH SÁCH TÊN KHO:");
        for (String tenKho : tenKhoList) {
            System.out.println("- " + tenKho);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
}
