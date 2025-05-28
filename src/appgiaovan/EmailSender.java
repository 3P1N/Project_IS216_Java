package appgiaovan;

import java.io.File;
import java.util.Properties;
import jakarta.activation.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {
    public static void sendMail(String toEmail, File pdfFile, String tieuDe) throws Exception {
        final String fromEmail = "3p1nPMIT@gmail.com";
        final String password = "fboftfflmqhazakj"; // App password nếu dùng Gmail

        // Thiết lập thông số SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tạo session xác thực
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(tieuDe);

            // Nội dung text
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Vui lòng xem thông tin chi tiết ở tệp đính kèm.");

            // Đính kèm file PDF
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(pdfFile);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(pdfFile.getName());

            // Gom tất cả lại
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Gửi mail
            Transport.send(message);

            System.out.println("Gửi Email thành công.");
        } catch (MessagingException e) {
            throw new RuntimeException("Lỗi gửi email: " + e.getMessage(), e);
        }
    }
}
