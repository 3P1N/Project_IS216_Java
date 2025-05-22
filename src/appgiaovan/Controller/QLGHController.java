/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Controller;

import appgiaovan.DAO.DonHangDAO;
import appgiaovan.DAO.GoiHangDAO;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.GoiHang;
import java.sql.SQLException;
import java.util.List;


public class QLGHController {
    private GoiHangDAO goiHangDAO = new GoiHangDAO();
    private DonHangDAO donHangDAO = new DonHangDAO();
    public List<GoiHang> LayDSGoiHang() throws SQLException, ClassNotFoundException{
        return goiHangDAO.LayDSGoiHang();
    }
    
    public List<DonHang> LayDSDonHang(DonHang dh) throws SQLException, ClassNotFoundException{
        return donHangDAO.LayDSDonHang(dh);
    }
    
    public void ThemGoiHang(GoiHang goiHang, List<Integer> listDonHang){
        goiHangDAO.ThemGoiHang(goiHang, listDonHang);
    }
}
