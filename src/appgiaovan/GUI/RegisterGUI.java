package appgiaovan.GUI;

import appgiaovan.BUS.UserBUS;
import appgiaovan.CheckFormat;
import appgiaovan.DTO.AccountDTO;
import appgiaovan.DTO.AccountRoleDTO;
import appgiaovan.DTO.CustomerDTO;
import appgiaovan.DTO.UserDTO;
import appgiaovan.PasswordHashing;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RegisterGUI extends javax.swing.JFrame {

    public RegisterGUI() {
        initComponents();
        setTitle("Đăng ký");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jUsername = new javax.swing.JLabel();
        jPassword = new javax.swing.JLabel();
        PasswordField = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        jName = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        jEmail = new javax.swing.JLabel();
        jPhone = new javax.swing.JLabel();
        jCCCD = new javax.swing.JLabel();
        jBirth = new javax.swing.JLabel();
        jAddress = new javax.swing.JLabel();
        EmailField = new javax.swing.JTextField();
        PhoneField = new javax.swing.JTextField();
        CCCDField = new javax.swing.JTextField();
        BirthField = new javax.swing.JTextField();
        AddressField = new javax.swing.JTextField();
        UsernameField = new javax.swing.JTextField();
        jGender = new javax.swing.JLabel();
        GenderBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jUsername.setText("Username:");

        jPassword.setText("Password:");

        btnRegister.setText("Đăng ký");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        jName.setText("Họ và tên:");

        jEmail.setText("Email:");

        jPhone.setText("Số điện thoại:");

        jCCCD.setText("CCCD:");

        jBirth.setText("Ngày sinh:");

        jAddress.setText("Địa chỉ:");

        jGender.setText("Giới tính:");

        GenderBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        GenderBox.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                GenderBoxComponentAdded(evt);
            }
        });
        GenderBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenderBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Đăng ký tài khoản khách hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jGender, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBirth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCCCD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(EmailField)
                            .addComponent(PhoneField)
                            .addComponent(CCCDField)
                            .addComponent(BirthField)
                            .addComponent(AddressField)
                            .addComponent(UsernameField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnRegister))
                            .addComponent(GenderBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jName, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jName)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUsername)
                    .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPassword)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jEmail)
                    .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPhone)
                    .addComponent(PhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCCCD)
                    .addComponent(CCCDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jGender)
                    .addComponent(GenderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBirth)
                    .addComponent(BirthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAddress))
                .addGap(33, 33, 33)
                .addComponent(btnRegister))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed

        // Lấy dữ liệu từ các field
        String name = NameField.getText().trim();
        String username = UsernameField.getText().trim();
        String password = PasswordField.getText().trim();
        String email = EmailField.getText().trim();
        String phone = PhoneField.getText().trim();
        String cccd = CCCDField.getText().trim();
        char gender = GenderBox.getSelectedItem().toString().equals("Nam") ? 'M' : 'F';
        String birthStr = BirthField.getText().trim();
        String address = AddressField.getText().trim();

        // Kiểm tra đầu vào cơ bản
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()
                || phone.isEmpty() || cccd.isEmpty() || birthStr.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tất cả thông tin.");
            return;
        }

        // Kiểm tra định dạng ngày sinh
        java.sql.Date birthDate;
        try {
            java.util.Date parsed = new SimpleDateFormat("dd-MM-yyyy").parse(birthStr);
            birthDate = new java.sql.Date(parsed.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng dd-MM-yyyy");
            return;
        }

        // Kiểm tra định dạng số điện thoại
        if (!CheckFormat.isValidPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng.");
            return;
        }

        // Kiểm tra định dạng CCCD
        if (!CheckFormat.isValidCCCD(cccd)) {
            JOptionPane.showMessageDialog(this, "CCCD không đúng định dạng.");
            return;
        }

        // Kiểm tra định dạng email
        if (!CheckFormat.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng.");
            return;
        }

        // Hash mật khẩu
        String hashedPassword = PasswordHashing.hashPassword(password);

        // Tạo các DTO
        UserDTO userDTO = new UserDTO(0, name, email, null);
        AccountDTO accountDTO = new AccountDTO(0, 0, username, hashedPassword, null, null);
        CustomerDTO customerDTO = new CustomerDTO(0, name, cccd, phone, gender, birthDate, address, email);
        AccountRoleDTO roleDTO = new AccountRoleDTO(0, 0, "CUSTOMER", null);

        // Gửi đến BUS xử lý đăng ký
        String result = UserBUS.getInstance().registerCustomer(userDTO, accountDTO, customerDTO, roleDTO);

        switch (result) {
            case "SUCCESS" -> {
                JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                dispose(); // Đóng cửa sổ sau khi đăng ký thành công
            }
            case "USERNAME_EXISTS" ->
                JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại!");
            case "EMAIL_EXISTS" ->
                JOptionPane.showMessageDialog(null, "Email đã tồn tại!");
            case "CCCD_EXISTS" ->
                JOptionPane.showMessageDialog(null, "CCCD đã tồn tại!");
            case "PHONE_EXISTS" ->
                JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!");
            default ->
                JOptionPane.showMessageDialog(null, "Đăng ký thất bại. Vui lòng thử lại!");
        }

    }//GEN-LAST:event_btnRegisterActionPerformed

    private void GenderBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenderBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderBoxActionPerformed

    private void GenderBoxComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_GenderBoxComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderBoxComponentAdded

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RegisterGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddressField;
    private javax.swing.JTextField BirthField;
    private javax.swing.JTextField CCCDField;
    private javax.swing.JTextField EmailField;
    private javax.swing.JComboBox<String> GenderBox;
    private javax.swing.JTextField NameField;
    private javax.swing.JTextField PasswordField;
    private javax.swing.JTextField PhoneField;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jAddress;
    private javax.swing.JLabel jBirth;
    private javax.swing.JLabel jCCCD;
    private javax.swing.JLabel jEmail;
    private javax.swing.JLabel jGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jPassword;
    private javax.swing.JLabel jPhone;
    private javax.swing.JLabel jUsername;
    // End of variables declaration//GEN-END:variables
}
