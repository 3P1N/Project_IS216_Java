
package appgiaovan.Controller;

import appgiaovan.DAO.KhoHangDAO;
import appgiaovan.Entity.KhoHang;
import java.sql.SQLException;
import java.util.List;

public class QLDonHangController {
    public List<KhoHang> LayThongTinKho() throws SQLException, ClassNotFoundException {
        KhoHangDAO khoHangDAO = new KhoHangDAO();
        return khoHangDAO.LayThongTinKho();

    }
    public void ThemDonHang(){
        
    }
}
