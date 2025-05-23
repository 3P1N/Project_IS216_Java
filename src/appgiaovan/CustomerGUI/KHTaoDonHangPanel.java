package appgiaovan.CustomerGUI;

import appgiaovan.Controller.QLDonHangController;
import appgiaovan.EmployeeGUI.*;
import appgiaovan.Controller.TaoDonHangController;
import appgiaovan.CustomerGUI.CustomerSidebar;
import appgiaovan.DAO.KhachHangDAO;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.KhachHang;
import appgiaovan.Entity.KhoHang;
import appgiaovan.GUI.Components.RoundedButton;
import appgiaovan.GUI.Components.RoundedComboBox;

import appgiaovan.GUI.Components.RoundedTextField;
import appgiaovan.GUI.Components.TimeWeather;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class KHTaoDonHangPanel extends JPanel {
    
        private QLDonHangController controller = new QLDonHangController();
    public KHTaoDonHangPanel() throws SQLException, ClassNotFoundException {

        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // Bên gửi
        JLabel lblBenGui = new JLabel("Bên gửi");
        lblBenGui.setFont(new Font("Arial", Font.BOLD, 14));
        lblBenGui.setBounds(20, 20, 100, 25);
        mainPanel.add(lblBenGui);
        
        //SDT
        RoundedTextField txtSDTNguoiGui = new RoundedTextField("Nhập số điện thoại người gửi");
        txtSDTNguoiGui.setBorder(BorderFactory.createTitledBorder("SĐT Người Gửi *"));
        txtSDTNguoiGui.setBounds(20, 50, 200, 50);
        mainPanel.add(txtSDTNguoiGui);
        //Ho ten
        RoundedTextField txtTenNguoiGui = new RoundedTextField("Nhập tên người gửi");
        txtTenNguoiGui.setBorder(BorderFactory.createTitledBorder("Tên Người Gửi *"));
        txtTenNguoiGui.setBounds(240, 50, 200, 50);
        mainPanel.add(txtTenNguoiGui);

        List<KhoHang> listKho  = controller.LayThongTinKho(); 
        System.out.println(listKho);
        String[] arrayTenKho = new String[listKho.size()];
        for(int i =0;i<listKho.size();i++){
            arrayTenKho[i] = listKho.get(i).getTenKho();
        }
        RoundedComboBox cbKhoTiepNhan = new RoundedComboBox(arrayTenKho);
        
        cbKhoTiepNhan.setBorder(BorderFactory.createTitledBorder("Kho tiếp nhận"));
        cbKhoTiepNhan.setBounds(460, 50, 200, 50);
        mainPanel.add(cbKhoTiepNhan);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 120, 1800, 10);
        mainPanel.add(separator);

        // Bên nhận
        JLabel lblBenNhan = new JLabel("Bên nhận");
        lblBenNhan.setFont(new Font("Arial", Font.BOLD, 14));
        lblBenNhan.setBounds(20, 130, 100, 25);
        mainPanel.add(lblBenNhan);

        RoundedTextField txtSDTNguoiNhan = new RoundedTextField("Nhập số điện thoại người nhận");
        txtSDTNguoiNhan.setBorder(BorderFactory.createTitledBorder("SĐT Người Nhận *"));
        txtSDTNguoiNhan.setBounds(20, 160, 200, 50);
        mainPanel.add(txtSDTNguoiNhan);

        RoundedTextField txtTenNguoiNhan = new RoundedTextField("Nhập tên người nhận");
        txtTenNguoiNhan.setBorder(BorderFactory.createTitledBorder("Tên Người Nhận *"));
        txtTenNguoiNhan.setBounds(240, 160, 200, 50);
        mainPanel.add(txtTenNguoiNhan);

        RoundedTextField txtDiaChiNhan = new RoundedTextField("Nhập địa chỉ người nhận");
        txtDiaChiNhan.setBorder(BorderFactory.createTitledBorder("Địa Chỉ Nhận *"));
        txtDiaChiNhan.setBounds(460, 160, 300, 50);
        mainPanel.add(txtDiaChiNhan);

        JComboBox<String> cbQuanHuyen = new JComboBox<>(new String[]{
            "Quận 1", "Quận 2", "Quận 3"
        });
        cbQuanHuyen.setBorder(BorderFactory.createTitledBorder("Quận/Huyện"));
        cbQuanHuyen.setBounds(20, 230, 200, 50);
        mainPanel.add(cbQuanHuyen);

        RoundedComboBox cbPhuongXa = new RoundedComboBox(new String[]{
            "Phường 1", "Phường 2", "Phường 3"
        });
        cbPhuongXa.setBorder(BorderFactory.createTitledBorder("Phường/Xã"));
        cbPhuongXa.setBounds(20, 300, 200, 50);
        mainPanel.add(cbPhuongXa);

//        RoundedTextField txtThoiGianNhan = new RoundedTextField("VD: 12/05/2025 14:30");
//        txtThoiGianNhan.setBorder(BorderFactory.createTitledBorder("Thời Gian Nhận *"));
//        txtThoiGianNhan.setBounds(460, 230, 200, 50);
//        mainPanel.add(txtThoiGianNhan);
        RoundedComboBox cbLoaiDichVu = new RoundedComboBox(new String[]{
            "Chọn loại dịch vụ", "Nhanh", "Tiết kiệm", "Hỏa tốc"
        });
        cbLoaiDichVu.setBorder(BorderFactory.createTitledBorder("Loại Dịch Vụ *"));
        cbLoaiDichVu.setBounds(240, 230, 150, 50);
        mainPanel.add(cbLoaiDichVu);

        //Loai Hang
        RoundedComboBox cbLoaiHang = new RoundedComboBox(new String[]{
            "Hàng bình thường", "Hàng dễ vỡ", "Hàng cồng kềnh"
        });
        cbLoaiHang.setBorder(BorderFactory.createTitledBorder("Loại Dịch Vụ *"));
        cbLoaiHang.setBounds(240, 300, 300, 50);
        mainPanel.add(cbLoaiHang);
        
        // Nút Tạo đơn hàng
        RoundedButton btnTaoDon = new RoundedButton("Tạo đơn hàng");
        btnTaoDon.setBounds((880 - 200 - 150) / 2, 440, 150, 45); // Trừ chiều rộng của menubar
        btnTaoDon.setBackground(new Color(0x007BFF)); // Flat Blue
        mainPanel.add(btnTaoDon);
        //Thêm hình thức thanh toán
        RoundedComboBox cbHinhThucThanhToan = new RoundedComboBox(new String[]{
            "Chọn hình thức thanh toán", "Tiền mặt", "Thanh toán online", "Thanh toán COD"
        });
        cbHinhThucThanhToan.setBorder(BorderFactory.createTitledBorder("Hình Thức Thanh Toán *"));
        cbHinhThucThanhToan.setBounds(20, 370, 300, 50);
        mainPanel.add(cbHinhThucThanhToan);

        CustomerSidebar sidebar = new CustomerSidebar();
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        //Thanh Weather
        TimeWeather CustomerTimeWeather = new TimeWeather("Ho Chi Minh 30 độ");
        mainPanel.add(CustomerTimeWeather, BorderLayout.NORTH);
        btnTaoDon.addActionListener(e->{
            String sdtNguoiGui = txtSDTNguoiGui.getText().trim();
                String tenNguoiGui = txtTenNguoiGui.getText().trim();

                KhoHang selectedKho = (KhoHang) cbKhoTiepNhan.getSelectedItem();
                int idKho = selectedKho.getIdKho();


                String sdtNguoiNhan = txtSDTNguoiNhan.getText().trim();
                String tenNguoiNhan = txtTenNguoiNhan.getText().trim();
                String diaChiNhan = txtDiaChiNhan.getText().trim();
                String quanHuyen = (String) cbQuanHuyen.getSelectedItem();
                String phuongXa = (String) cbPhuongXa.getSelectedItem();

                String loaiDichVu = (String) cbLoaiDichVu.getSelectedItem();
                String loaiHang = (String) cbLoaiHang.getSelectedItem();
                String hinhThucThanhToan = (String) cbHinhThucThanhToan.getSelectedItem();

                // Gộp địa chỉ chi tiết
                String diaChiDayDu = diaChiNhan + ", " + phuongXa + ", " + quanHuyen;

                // Tạo đối tượng DonHang
                DonHang dh = new DonHang();
//                dh.setIdDonHang(idDonHang);
                dh.setSdtNguoiGui(sdtNguoiGui);
                dh.setSdtNguoiNhan(sdtNguoiNhan);
                dh.setTenNguoiGui(tenNguoiGui);
                dh.setTenNguoiNhan(tenNguoiNhan);
                dh.setDiaChiNhan(diaChiDayDu);
                dh.setDichVu(loaiDichVu);
                dh.setLoaiHangHoa(loaiHang);
                dh.setIdKhoTiepNhan(idKho);
                if (!controller.KiemTraDinhDang(dh)) {
                    JOptionPane.showMessageDialog(this, "Định dạng đơn hàng không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng lại, không thực hiện thêm
                }
            try {
                // Gọi controller để thêm đơn hàng
                controller.ThemDonHang(dh);
            } catch (SQLException ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
                // Gọi callback
                JOptionPane.showMessageDialog(this, "Tạo đơn hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                
        });
    }
    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Employee Main Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            try {
                frame.add(new KHTaoDonHangPanel(), BorderLayout.CENTER);
            } catch (SQLException ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
}
