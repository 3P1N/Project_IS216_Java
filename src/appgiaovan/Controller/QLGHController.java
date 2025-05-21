/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Controller;

import appgiaovan.DAO.GoiHangDAO;
import appgiaovan.Entity.GoiHang;
import java.sql.SQLException;
import java.util.List;


public class QLGHController {
    private GoiHangDAO goiHangDAO = new GoiHangDAO();
    
    public List<GoiHang> LayDSGoiHang() throws SQLException, ClassNotFoundException{
        return goiHangDAO.LayDSGoiHang();
    }
}
