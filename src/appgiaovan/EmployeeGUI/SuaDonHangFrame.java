package appgiaovan.EmployeeGUI;

import appgiaovan.Controller.QLDonHangController;
import appgiaovan.CustomerGUI.CustomerSidebar;
import appgiaovan.DAO.DonHangDAO;
import appgiaovan.Entity.DonHang;
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

public class SuaDonHangFrame extends JFrame {

    private QLDonHangController controller = new QLDonHangController();
    private DonHangDAO donHangDAO = new DonHangDAO();
    private JTextField txtMaDon = new JTextField("");
    private JTextField txtSDTNguoiGui = new JTextField("");
    private JTextField txtTenNguoiGui = new JTextField("");
    private JComboBox cbKhoTiepNhan = new JComboBox();
    private JTextField txtSDTNguoiNhan = new JTextField("");
    private JTextField txtTenNguoiNhan = new JTextField("");
    private JComboBox cbLoaiDichVu;
    private JComboBox cbLoaiHang;
    private JComboBox cbHinhThucThanhToan;
    
    private JTextField txtDiaChiNhan = new JTextField("");

    private RoundedButton btnSuaDonHang = new RoundedButton("Sửa đơn hàng");

    public SuaDonHangFrame(int idDonHang, Runnable onSuccess) throws SQLException, ClassNotFoundException {
        setTitle("Tạo Đơn Hàng");
        setSize(920, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // Bên gửi
        JLabel lblBenGui = new JLabel("Bên gửi");
        lblBenGui.setFont(new Font("Arial", Font.BOLD, 14));
        lblBenGui.setBounds(20, 20, 100, 25);
        mainPanel.add(lblBenGui);
        //MaDon
        txtMaDon.setFocusable(false);

        txtMaDon.setBorder(BorderFactory.createTitledBorder("Mã đơn hàng"));
        txtMaDon.setBounds(20, 50, 200, 50);
        txtMaDon.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(txtMaDon);
        //SDT
        txtSDTNguoiGui.setBorder(BorderFactory.createTitledBorder("SĐT Người Gửi *"));
        txtSDTNguoiGui.setBounds(240, 50, 200, 50);
        mainPanel.add(txtSDTNguoiGui);
        //Ho ten
        txtTenNguoiGui.setBorder(BorderFactory.createTitledBorder("Tên Người Gửi *"));
        txtTenNguoiGui.setBounds(460, 50, 200, 50);
        mainPanel.add(txtTenNguoiGui);

        List<KhoHang> listKho = controller.LayThongTinKho();

        for (KhoHang kho : listKho) {
            cbKhoTiepNhan.addItem(kho);
        }

        cbKhoTiepNhan.setBorder(BorderFactory.createTitledBorder("Kho tiếp nhận"));
        cbKhoTiepNhan.setBounds(680, 50, 200, 50);
        mainPanel.add(cbKhoTiepNhan);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 120, 820, 10);
        mainPanel.add(separator);

        // Bên nhận
        JLabel lblBenNhan = new JLabel("Bên nhận");
        lblBenNhan.setFont(new Font("Arial", Font.BOLD, 14));
        lblBenNhan.setBounds(20, 130, 100, 25);
        mainPanel.add(lblBenNhan);

        txtSDTNguoiNhan.setBorder(BorderFactory.createTitledBorder("SĐT Người Nhận *"));
        txtSDTNguoiNhan.setBounds(20, 160, 200, 50);
        mainPanel.add(txtSDTNguoiNhan);

        txtTenNguoiNhan.setBorder(BorderFactory.createTitledBorder("Tên Người Nhận *"));
        txtTenNguoiNhan.setBounds(240, 160, 200, 50);
        mainPanel.add(txtTenNguoiNhan);

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

//        
        cbLoaiDichVu = new JComboBox(new String[]{
            "Chọn loại dịch vụ", "Nhanh", "Tiết kiệm", "Hỏa tốc"
        });
        cbLoaiDichVu.setBorder(BorderFactory.createTitledBorder("Loại Dịch Vụ *"));
        cbLoaiDichVu.setBounds(240, 230, 150, 50);
        mainPanel.add(cbLoaiDichVu);

        //Loai Hang
        cbLoaiHang = new JComboBox(new String[]{
            "Hàng bình thường", "Hàng dễ vỡ", "Hàng cồng kềnh"
        });
        cbLoaiHang.setBorder(BorderFactory.createTitledBorder("Loại Dịch Vụ *"));
        cbLoaiHang.setBounds(240, 300, 300, 50);
        mainPanel.add(cbLoaiHang);

        // Nút Xác nhận
        btnSuaDonHang.setBounds((880 - 200 - 150) / 2, 440, 150, 45); // Trừ chiều rộng của menubar
        btnSuaDonHang.setBackground(new Color(0x007BFF)); // Flat Blue
        mainPanel.add(btnSuaDonHang);
        //Thêm hình thức thanh toán
        
        cbHinhThucThanhToan = new JComboBox(new String[]{
            "Chọn hình thức thanh toán", "Tiền mặt", "Thanh toán online", "Thanh toán COD"
        });
        cbHinhThucThanhToan.setBorder(BorderFactory.createTitledBorder("Hình Thức Thanh Toán *"));
        cbHinhThucThanhToan.setBounds(20, 370, 300, 50);
        mainPanel.add(cbHinhThucThanhToan);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        //Thanh Weather
        TimeWeather CustomerTimeWeather = new TimeWeather("Ho Chi Minh 30 độ");
        mainPanel.add(CustomerTimeWeather, BorderLayout.NORTH);

        HienThiDonHang(idDonHang);
        btnSuaDonHang.addActionListener(e -> {

            try {
                SuaDonHang(idDonHang, onSuccess);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        });

    }

    public void HienThiDonHang(int idDonHang) throws SQLException, ClassNotFoundException {
        DonHang dh = donHangDAO.LayThongTinDonHang(idDonHang);
        txtMaDon.setText(String.valueOf(idDonHang));
        txtSDTNguoiGui.setText(dh.getSdtNguoiGui());
        txtTenNguoiGui.setText(dh.getTenNguoiGui());
        txtSDTNguoiNhan.setText(dh.getSdtNguoiNhan());
        txtTenNguoiNhan.setText(dh.getTenNguoiNhan());
        txtDiaChiNhan.setText(dh.getDiaChiNhan());
        cbKhoTiepNhan.setSelectedItem(dh);
    }

    public void HienThiMaDonHang() throws SQLException, ClassNotFoundException {
        DonHangDAO donHangDAO = new DonHangDAO();
        int maDon = donHangDAO.LayMaDon();
        this.txtMaDon.setText(String.valueOf(maDon)); // chuyển int sang String
        System.out.println(maDon);
    }

    public void SuaDonHang(int idDonHang, Runnable onSuccess) throws SQLException, ClassNotFoundException {
        // Lấy dữ liệu từ các trường
        System.out.println(txtMaDon.getText().trim());
        String sdtNguoiGui = txtSDTNguoiGui.getText().trim();
        String tenNguoiGui = txtTenNguoiGui.getText().trim();

        KhoHang selectedKho = (KhoHang) cbKhoTiepNhan.getSelectedItem();
        int idKho = selectedKho.getIdKho();

        String sdtNguoiNhan = txtSDTNguoiNhan.getText().trim();
        String tenNguoiNhan = txtTenNguoiNhan.getText().trim();
        String diaChiNhan = txtDiaChiNhan.getText().trim();
//        String quanHuyen = (String) cbQuanHuyen.getSelectedItem();
//        String phuongXa = (String) cbPhuongXa.getSelectedItem();

        String loaiDichVu = (String) cbLoaiDichVu.getSelectedItem();
        String loaiHang = (String) cbLoaiHang.getSelectedItem();
        String hinhThucThanhToan = (String) cbHinhThucThanhToan.getSelectedItem();

        // Gộp địa chỉ chi tiết
//        String diaChiDayDu = diaChiNhan + ", " + phuongXa + ", " + quanHuyen;
        // Tạo đối tượng DonHang
        DonHang dh = new DonHang();
        dh.setIdDonHang(idDonHang);
        dh.setSdtNguoiGui(sdtNguoiGui);
        dh.setSdtNguoiNhan(sdtNguoiNhan);
        dh.setTenNguoiGui(tenNguoiGui);
        dh.setTenNguoiNhan(tenNguoiNhan);
        dh.setDiaChiNhan(diaChiNhan);
        dh.setDichVu(loaiDichVu);
        dh.setLoaiHangHoa(loaiHang);
        dh.setIdKhoTiepNhan(idKho);
        if (!controller.KiemTraDinhDang(dh)) {
            JOptionPane.showMessageDialog(this, "Định dạng đơn hàng không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng lại, không thực hiện thêm
        }
        // Gọi controller để thêm đơn hàng
        controller.SuaDonHang(dh);
        // Gọi callback
        JOptionPane.showMessageDialog(this, "Sửa đơn hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        onSuccess.run();

        dispose();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            SuaDonHangFrame frame = null;
            try {
                frame = new SuaDonHangFrame(2, () -> System.out.println("Cập nhật danh sách!"));
            } catch (SQLException ex) {
                Logger.getLogger(SuaDonHangFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SuaDonHangFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
//            frame.setVisible(true);
//            frame.setVisible(true);
        });
    }
}
