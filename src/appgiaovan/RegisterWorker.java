package appgiaovan;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

class RegisterWorker extends SwingWorker<Void, Void> {

    private final Register parentFrame;

    public RegisterWorker(Register parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    protected Void doInBackground() {
        try (Connection conn = DatabaseUtil.getConnection()) {

            conn.setAutoCommit(false); // Tắt tự động commit để kiểm soát giao dịch

            // Truy cập các trường từ frame cha
            String name = parentFrame.NameField.getText().trim();
            String username = parentFrame.UsernameField.getText().trim();
            String password = parentFrame.PasswordField.getText().trim();
            String email = parentFrame.EmailField.getText().trim();
            String phone = parentFrame.PhoneField.getText().trim();
            String cccd = parentFrame.CCCDField.getText().trim();
            String address = parentFrame.AddressField.getText().trim();
            String birth = parentFrame.BirthField.getText().trim(); // Định dạng dd/MM/yyyy
            String role = (String) parentFrame.RoleBox.getSelectedItem();
            String strgender = (String) parentFrame.GenderBox.getSelectedItem();
            char gender = strgender.equals("Nam") ? 'M' : 'F';
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()
                    || phone.isEmpty() || cccd.isEmpty() || address.isEmpty() || birth.isEmpty() || role.isEmpty()) {
                throw new Exception("Vui lòng điền đầy đủ thông tin!");
            }

            // Thêm vào bảng USER
            String sqlUser = "INSERT INTO \"USER\" (FULL_NAME, EMAIL) VALUES (?, ?)";

            try (PreparedStatement pstmtUser = conn.prepareStatement(sqlUser)) {

                pstmtUser.setString(1, name);
                pstmtUser.setString(2, email);
                pstmtUser.executeUpdate();

            } catch (SQLException e) {

                if (e.getErrorCode() == 1) { // ORA-00001: unique constraint violated
                    JOptionPane.showMessageDialog(null, "Email đã tồn tại! Vui lòng sử dụng email khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi khi thêm người dùng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                conn.rollback(); // Hủy giao dịch nếu lỗi
                return null;
            }

            System.out.println("here1");
            int userId = 1;
            String sqlGetID = "SELECT USER_ID FROM \"USER\" WHERE EMAIL = ? FETCH FIRST 1 ROW ONLY";

            try (PreparedStatement pstmtGetID = conn.prepareStatement(sqlGetID)) {
                pstmtGetID.setString(1, email); // Gán giá trị email vào câu lệnh SQL
                try (ResultSet rs = pstmtGetID.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getInt(1);
                        System.out.println("User ID vừa tạo: " + userId);
                    } else {
                        System.out.println("Không tìm thấy USER_ID cho email: " + email);
                    }
                }
            } catch (SQLException e) {

            }

            // Thêm vào bảng ACCOUNT
            String sqlAccount = "INSERT INTO ACCOUNT (USER_ID, USERNAME, PASSWORD_HASH) VALUES (?, ?, ?)";
            try (PreparedStatement pstmtAccount = conn.prepareStatement(sqlAccount)) {
                pstmtAccount.setInt(1, userId);
                pstmtAccount.setString(2, username);
                pstmtAccount.setString(3, password);
                pstmtAccount.executeUpdate();
            } catch (SQLException e) {
                conn.rollback(); // Hủy toàn bộ giao dịch nếu có lỗi
                if (e.getErrorCode() == 1) { // ORA-00001: unique constraint violated
                    JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại! Vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi khi thêm tài khoản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                return null; // Dừng lại khi có lỗi
            }

            // Thêm vào bảng theo vai trò
            String sqlRole = switch (role) {
                case "Customer" ->
                    "INSERT INTO CUSTOMER (ID_CUSTOMER, NAME, ID_CCCD, PHONENUMBER, GENDER, DATE_OF_BIRTH, ADDRESS, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                case "Manager" ->
                    "INSERT INTO MANAGER (ID_MANAGER, NAME, ID_CCCD, PHONENUMBER, GENDER, DATE_OF_BIRTH, EMAIL, MANAGEMENT_AREA, NUMBERS_DAYOFFS, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, 'Khu A', 0, 5000000)";
                case "Employee_WareHouse" ->
                    "INSERT INTO EMPLOYEE_WAREHOUSE (ID_EMPLOYEE, NAME, ID_CCCD, GENDER, DATE_OF_BIRTH, PHONENUMBER, EMAIL, ID_WAREHOUSE, ID_MANAGER, NUMBERS_DAYOFFS, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, 'WH01', 1, 0, 3000000)";
                case "Shipper" ->
                    "INSERT INTO SHIPPER (ID_SHIPPER, NAME, ID_CCCD, PHONE_NUMBER, GENDER, DATE_OF_BIRTH, EMAIL, ADDRESS_SHIP, RATING, ID_MANAGER, ID_WAREHOUSE, SALARY_FINAL, SALARY_BONUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 5, 1, 'WH01', 4000000, 500000)";
                default ->
                    throw new Exception("Vai trò không hợp lệ!");
            };

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
                    conn.rollback();
                    JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày sinh! Vui lòng nhập theo định dạng yyyy-MM-dd.");
                    return null; // Dừng quá trình nếu lỗi định dạng ngày
                }
                pstmtRole.setString(7, email);

                if (role.equals("Customer")) {
                    pstmtRole.setString(8, address);
                }

                pstmtRole.executeUpdate();

            } catch (SQLException e) {
                conn.rollback(); // Hủy toàn bộ giao dịch nếu có lỗi

                JOptionPane.showMessageDialog(null, "Lỗi khi thêm vai trò: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null; // Dừng lại khi có lỗi
            }

            JOptionPane.showMessageDialog(null, "Đăng ký thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            conn.commit(); // Xác nhận transaction
        } catch (SQLException e) {
            conn.rollback();
            JOptionPane.showMessageDialog(null, "Lỗi database: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            conn.rollback();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    @Override
    protected void done() {

    }

}
