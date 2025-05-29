package appgiaovan.Controller;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.DAO.ThongKeDAO;
import appgiaovan.Entity.DoanhThuLoiNhuan;
import appgiaovan.Entity.TK_DanhGia;
import appgiaovan.Entity.TK_DoanhThu;
import appgiaovan.Entity.TK_DonHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class ThongKeController {

    private ThongKeDAO dao;

    public ThongKeController() throws SQLException, ClassNotFoundException {
        dao = new ThongKeDAO();
    }
        
    public List<TK_DanhGia> getListTKDanhGia() throws SQLException, ClassNotFoundException {
        
        return ThongKeDAO.getListTKDanhGia();
    }

    public List<TK_DonHang> getListTKDonHang() throws SQLException, ClassNotFoundException {
        return ThongKeDAO.getListTKDonHang();

    }
    

}
