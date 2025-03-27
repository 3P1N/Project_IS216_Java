package appgiaovan;
import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Register extends RegisterFrame {

    public Register() {
        super();
    }

    @Override
    protected void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {
           new RegisterWorker(this).execute(); // Khởi chạy worker thread

//        String name = NameField.getText().trim();
//        String username = UsernameField.getText().trim();
//        String password = PasswordField.getText().trim();
//        String email = EmailField.getText().trim();
//        String phone = PhoneField.getText().trim();
//        String cccd = CCCDField.getText().trim();
//        String address = AddressField.getText().trim();
//        String birth = BirthField.getText().trim();
//        String role = (String) RoleBox.getSelectedItem();
//        String strgender = (String) GenderBox.getSelectedItem();
//        char gender = strgender.equals("Nam") ? 'M' : 'F';
//
//        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()
//                || cccd.isEmpty() || address.isEmpty() || birth.isEmpty() || role.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        Connection conn = null;
//        PreparedStatement pstmtUser = null;
//        PreparedStatement pstmtAccount = null;
//        PreparedStatement pstmtRole = null;
//        ResultSet rs = null;
//
//        try {
//            conn = DatabaseUtil.getConnection();
//            conn.setAutoCommit(false); // Tắt auto-commit để kiểm soát giao dịch
//
//            // Thêm vào bảng USER
//            String sqlUser = "INSERT INTO \"USER\" (FULL_NAME, EMAIL) VALUES (?, ?)";
//            pstmtUser = conn.prepareStatement(sqlUser, new String[]{"USER_ID"});
//            pstmtUser.setString(1, name);
//            pstmtUser.setString(2, email);
//            pstmtUser.executeUpdate();
//
//            // Lấy USER_ID vừa tạo
//            rs = pstmtUser.getGeneratedKeys();
//            int userId = -1;
//            if (rs.next()) {
//                userId = rs.getInt(1);
//            } else {
//                throw new SQLException("Lỗi khi lấy USER_ID!");
//            }
//
//            // Thêm vào bảng ACCOUNT
//            String sqlAccount = "INSERT INTO ACCOUNT (USER_ID, USERNAME, PASSWORD_HASH) VALUES (?, ?, ?)";
//            pstmtAccount = conn.prepareStatement(sqlAccount);
//            pstmtAccount.setInt(1, userId);
//            pstmtAccount.setString(2, username);
//            pstmtAccount.setString(3, password);
//            pstmtAccount.executeUpdate();
//
//            // Thêm vào bảng vai trò
//            String sqlRole = "";
//            switch (role) {
//                case "CUSTOMER":
//                    sqlRole = "INSERT INTO CUSTOMER (ID_CUSTOMER, NAME, ID_CCCD, PHONENUMBER, GENDER, DATE_OF_BIRTH, ADDRESS, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                    break;
//                case "MANAGER":
//                    sqlRole = "INSERT INTO MANAGER (ID_MANAGER, NAME, ID_CCCD, PHONENUMBER, GENDER, DATE_OF_BIRTH, EMAIL, MANAGEMENT_AREA, NUMBERS_DAYOFFS, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, 'Khu A', 0, 5000000)";
//                    break;
//                case "EMPLOYEE_WAREHOUSE":
//                    sqlRole = "INSERT INTO EMPLOYEE_WAREHOUSE (ID_EMPLOYEE, NAME, ID_CCCD, GENDER, DATE_OF_BIRTH, PHONENUMBER, EMAIL, ID_WAREHOUSE, ID_MANAGER, NUMBERS_DAYOFFS, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, 'WH01', 1, 0, 3000000)";
//                    break;
//                case "SHIPPER":
//                    sqlRole = "INSERT INTO SHIPPER (ID_SHIPPER, NAME, ID_CCCD, PHONE_NUMBER, GENDER, DATE_OF_BIRTH, EMAIL, ADDRESS_SHIP, RATING, ID_MANAGER, ID_WAREHOUSE, SALARY_FINAL, SALARY_BONUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 5, 1, 'WH01', 4000000, 500000)";
//                    break;
//                default:
//                    JOptionPane.showMessageDialog(this, "Vai trò không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    conn.rollback();
//                    return;
//            }
//
//            pstmtRole = conn.prepareStatement(sqlRole);
//            pstmtRole.setInt(1, userId);
//            pstmtRole.setString(2, name);
//            pstmtRole.setString(3, cccd);
//            pstmtRole.setString(4, phone);
//            pstmtRole.setString(5, String.valueOf(gender)); // Chuyển char thành String
//
//            // Chuyển đổi ngày sinh
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            java.sql.Date sqlDate = new java.sql.Date(sdf.parse(birth).getTime());
//            pstmtRole.setDate(6, sqlDate);
//
//            pstmtRole.setString(7, email);
//            if (role.equals("CUSTOMER")) {
//                pstmtRole.setString(8, address);
//            }
//
//            pstmtRole.executeUpdate();
//            conn.commit(); // Xác nhận transaction
//
//            JOptionPane.showMessageDialog(this, "Đăng ký thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
//
//        } catch (SQLException e) {
//            if (conn != null) {
//                try {
//                    conn.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi database: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } catch (ParseException ex) {
//            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pstmtUser != null) {
//                    pstmtUser.close();
//                }
//                if (pstmtAccount != null) {
//                    pstmtAccount.close();
//                }
//                if (pstmtRole != null) {
//                    pstmtRole.close();
//                }
//                if (conn != null) {
//                    conn.setAutoCommit(true);
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
    }

}
