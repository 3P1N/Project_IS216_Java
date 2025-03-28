package appgiaovan;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class RegisterWorker extends SwingWorker<Void, Void> {

    private final Register parentFrame;

    public RegisterWorker(Register parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    protected Void doInBackground() {
        Connection conn = null;
        boolean hasError = false; // Cờ kiểm tra lỗi

        try {
            conn = DatabaseUtil.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(parentFrame, "Không thể kết nối đến database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            conn.setAutoCommit(false); // Tắt auto-commit để kiểm soát giao dịch

            // Lấy dữ liệu từ form
            String name = parentFrame.NameField.getText().trim();
            String username = parentFrame.UsernameField.getText().trim();
            String password = parentFrame.PasswordField.getText().trim();
            String email = parentFrame.EmailField.getText().trim();
            String phone = parentFrame.PhoneField.getText().trim();
            String cccd = parentFrame.CCCDField.getText().trim();
            String address = parentFrame.AddressField.getText().trim();
            String birth = parentFrame.BirthField.getText().trim();
            String strgender = (String) parentFrame.GenderBox.getSelectedItem();

            String hashedpassword = PasswordHashing.hashPassword(password);

            // Kiểm tra rỗng
            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()
                    || phone.isEmpty() || cccd.isEmpty() || address.isEmpty() || birth.isEmpty() || strgender.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            System.out.println("Bắt đầu thực hiện giao dịch...");

            char gender = strgender.equals("Nam") ? 'M' : 'F';

            // Thêm vào bảng USER
            String sqlUser = "INSERT INTO \"USER\" (FULL_NAME, EMAIL) VALUES (?, ?)";
            try (PreparedStatement pstmtUser = conn.prepareStatement(sqlUser)) {
                pstmtUser.setString(1, name);
                pstmtUser.setString(2, email);
                pstmtUser.executeUpdate();
            } catch (SQLException e) {
                hasError = true;
                if (e.getSQLState().equals("23000") || e.getMessage().contains("unique constraint") || e.getErrorCode() == 1) {
                    JOptionPane.showMessageDialog(parentFrame, "Email đã tồn tại! Vui lòng sử dụng email khác.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Lỗi khi thêm người dùng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            // Lấy user ID vừa tạo
            int userId = -1;
            String sqlGetID = "SELECT USER_ID FROM \"USER\" WHERE EMAIL = ? FETCH FIRST 1 ROW ONLY";
            try (PreparedStatement pstmtGetID = conn.prepareStatement(sqlGetID)) {
                pstmtGetID.setString(1, email);
                try (ResultSet rs = pstmtGetID.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getInt(1);
                        System.out.println("User ID vừa tạo: " + userId);
                    } else {
                        hasError = true;
                        JOptionPane.showMessageDialog(null, "Không tìm thấy USER_ID cho email: " + email, "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                }
            } catch (SQLException e) {
                hasError = true;
                JOptionPane.showMessageDialog(null, "Lỗi truy vấn ID người dùng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Thêm vào bảng ACCOUNT
            String sqlAccount = "INSERT INTO ACCOUNT (USER_ID, USERNAME, PASSWORD_HASH) VALUES (?, ?, ?)";
            try (PreparedStatement pstmtAccount = conn.prepareStatement(sqlAccount)) {
                pstmtAccount.setInt(1, userId);
                pstmtAccount.setString(2, username);
                pstmtAccount.setString(3, hashedpassword);
                pstmtAccount.executeUpdate();
            } catch (SQLException e) {
                hasError = true;
                if (e.getSQLState().equals("23000") || e.getMessage().contains("unique constraint") || e.getErrorCode() == 1) {
                    JOptionPane.showMessageDialog(parentFrame, "Tên đăng nhập đã tồn tại! Vui lòng chọn tên khác.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Lỗi khi thêm tài khoản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            // Thêm vào bảng CUSTOMER
            String sqlRole = "INSERT INTO CUSTOMER (ID_CUSTOMER, NAME, ID_CCCD, PHONENUMBER, GENDER, DATE_OF_BIRTH, ADDRESS, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmtRole = conn.prepareStatement(sqlRole)) {
                pstmtRole.setInt(1, userId);
                pstmtRole.setString(2, name);
                pstmtRole.setString(3, cccd);
                pstmtRole.setString(4, phone);
                pstmtRole.setString(5, String.valueOf(gender));

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date utilDate = sdf.parse(birth);
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    pstmtRole.setDate(6, sqlDate);
                } catch (ParseException pe) {
                    hasError = true;
                    JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày sinh! Vui lòng nhập theo yyyy-MM-dd.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return null;
                }

                pstmtRole.setString(7, email);
                pstmtRole.setString(8, address);
                pstmtRole.executeUpdate();
            } catch (SQLException e) {
                hasError = true;
               
                String errorMessage = e.getMessage().toLowerCase();
                
                if (e.getSQLState().equals("23000") || errorMessage.contains("unique constraint") || e.getErrorCode() == 1) {
                    if (errorMessage.contains("cccd")) {
                        JOptionPane.showMessageDialog(parentFrame, "CCCD đã tồn tại! Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    } else if (errorMessage.contains("phonenumber")) {
                        JOptionPane.showMessageDialog(parentFrame, "Số điện thoại đã tồn tại! Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, "Lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Lỗi khi thêm khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            JOptionPane.showMessageDialog(null, "Đăng ký thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            hasError = true;
            JOptionPane.showMessageDialog(null, "Lỗi database: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try {
                    if (hasError) {
                        conn.rollback();
                        System.out.println("Rollback giao dịch do lỗi.");
                    } else {
                        conn.commit();
                        System.out.println("Commit giao dịch thành công.");
                    }
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
                }
            }
        }

        return null;
    }

    @Override
    protected void done() {
        // Thực hiện các hành động sau khi hoàn thành
    }
}
