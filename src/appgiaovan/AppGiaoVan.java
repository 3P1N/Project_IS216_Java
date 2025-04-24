
package appgiaovan;


import appgiaovan.GUI.LoginGUI;
import javax.mail.MessagingException;


public class AppGiaoVan {

    public static void main(String[] args) throws MessagingException {
//         LoginGUI login = new LoginGUI();
//         login.setVisible(true);
            EmailSender.sendEmail("tranthephong1908@gmail.com");
        
    }
}
