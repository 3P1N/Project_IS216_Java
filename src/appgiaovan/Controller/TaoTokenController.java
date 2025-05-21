/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Controller;

import appgiaovan.DAO.TokenDAO;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class TaoTokenController {
    public void TaoToken(String username) throws SQLException, ClassNotFoundException{
        new TokenDAO().taoToken(username);
    }
}
