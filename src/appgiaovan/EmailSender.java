
package appgiaovan;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;

public class EmailSender {

    public static void sendEmail(String toEmail) {
        // Cấu hình thông tin SMTP (Gmail)
        final String fromEmail = "3p1nPMIT@gmail.com";
        final String password = "fboftfflmqhazakj"; // Không dùng mật khẩu Gmail thường, dùng app password
        String generatedCode = String.valueOf(new Random().nextInt(900000) + 100000);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable", "true"); 

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Xác nhận đăng ký tài khoản");
             message.setText("Mã xác nhận của bạn là: " + generatedCode);

            Transport.send(message);
            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
