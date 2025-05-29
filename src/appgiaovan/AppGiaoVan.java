
package appgiaovan;
import appgiaovan.GUI.LOGIN;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

//import appgiaovan.GUI.LoginGUI;
//import javax.mail.MessagingException;


public class AppGiaoVan {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
         LOGIN login = new LOGIN();
         login.setVisible(true);    
    }
}
