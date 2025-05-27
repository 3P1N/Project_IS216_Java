package appgiaovan.CustomerGUI;

import appgiaovan.Controller.QLDonHangController;
import appgiaovan.EmployeeGUI.*;
import appgiaovan.Controller.TaoDonHangController;
import appgiaovan.CustomerGUI.CustomerSidebar;
import appgiaovan.DAO.DonHangDAO;
import appgiaovan.DAO.KhachHangDAO;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.KhachHang;
import appgiaovan.Entity.KhoHang;
import appgiaovan.GUI.Components.RoundedButton;
import appgiaovan.GUI.Components.RoundedComboBox;

import appgiaovan.GUI.Components.RoundedTextField;
import appgiaovan.GUI.Components.TimeWeather;
import appgiaovan.report.HoaDonKH;
import appgiaovan.report.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class KHTaoDonHangPanel extends JPanel {
        private DonHangDAO donHangDAO= new DonHangDAO();
        private KhachHangDAO khachHangDAO= new KhachHangDAO();
        private QLDonHangController qLDonHangController = new QLDonHangController();
    public KHTaoDonHangPanel(int ID_KhachHang) throws SQLException, ClassNotFoundException, Exception {

        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        KhachHang kh=khachHangDAO.layThongTinKhachHang(ID_KhachHang);
        // Bên gửi
        JLabel lblBenGui = new JLabel("Bên gửi");
        lblBenGui.setFont(new Font("Arial", Font.BOLD, 14));
        lblBenGui.setBounds(20, 20, 100, 25);
        mainPanel.add(lblBenGui);
        
        //SDT
        RoundedTextField txtSDTNguoiGui = new RoundedTextField(kh.getSDT());
        txtSDTNguoiGui.setBorder(BorderFactory.createTitledBorder("SĐT Người Gửi *"));
        txtSDTNguoiGui.setFocusable(false);
        txtSDTNguoiGui.setBounds(20, 50, 200, 50);
        mainPanel.add(txtSDTNguoiGui);
        //Ho ten
        RoundedTextField txtTenNguoiGui = new RoundedTextField(kh.getHoTen());
        txtTenNguoiGui.setBorder(BorderFactory.createTitledBorder("Tên Người Gửi *"));
        txtTenNguoiGui.setFocusable(false);
        txtTenNguoiGui.setBounds(240, 50, 200, 50);
        mainPanel.add(txtTenNguoiGui);

        List<KhoHang> listKho  = qLDonHangController.LayThongTinKho(); 
        JComboBox cbKhoTiepNhan = new RoundedComboBox();

        for (KhoHang kho : listKho) {
            cbKhoTiepNhan.addItem(kho);
        }

        cbKhoTiepNhan.setBorder(BorderFactory.createTitledBorder("Kho tiếp nhận"));
        cbKhoTiepNhan.setBounds(680, 50, 200, 50);
        mainPanel.add(cbKhoTiepNhan);
        
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
        String[] dsDichVu = donHangDAO.DSDichVu();
        RoundedComboBox cbLoaiDichVu = new RoundedComboBox(dsDichVu);
        cbLoaiDichVu.setBorder(BorderFactory.createTitledBorder("Loại Dịch Vụ *"));
        cbLoaiDichVu.setBounds(240, 230, 150, 50);
        mainPanel.add(cbLoaiDichVu);

        //Loai Hang
        String[] dsLoaiHang = donHangDAO.DSLoaiHang();
        RoundedComboBox cbLoaiHang = new RoundedComboBox(dsLoaiHang);
        cbLoaiHang.setBorder(BorderFactory.createTitledBorder("Loại Hàng Hóa *"));
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

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        
        btnTaoDon.addActionListener(e->{
            String sdtNguoiGui = txtSDTNguoiGui.getText().trim();
                String tenNguoiGui = txtTenNguoiGui.getText().trim();

                KhoHang selectedKho = (KhoHang) cbKhoTiepNhan.getSelectedItem();
                int idKho = selectedKho.getIdKho();
                System.out.print(idKho);

                String sdtNguoiNhan = txtSDTNguoiNhan.getText().trim();
                String tenNguoiNhan = txtTenNguoiNhan.getText().trim();
                String diaChiNhan = txtDiaChiNhan.getText().trim();
                String quanHuyen = (String) cbQuanHuyen.getSelectedItem();
                String phuongXa = (String) cbPhuongXa.getSelectedItem();

                String loaiDichVu = (String) cbLoaiDichVu.getSelectedItem();
                String loaiHang = (String) cbLoaiHang.getSelectedItem();
                String hinhThucThanhToan = (String) cbHinhThucThanhToan.getSelectedItem();
                double tienCOD=0;
                
                // Gộp địa chỉ chi tiết
                String diaChiDayDu = diaChiNhan + ", " + phuongXa + ", " + quanHuyen;
                if(hinhThucThanhToan.equals("Thanh toán COD")){
                    String input = JOptionPane.showInputDialog(null, "Nhập số tiền COD:", "Thông tin COD", JOptionPane.PLAIN_MESSAGE);

                    if (input != null && !input.trim().isEmpty()) {
                        try {
                            tienCOD = Double.parseDouble(input.trim());
                            // Gán vào đơn hàng hoặc xử lý tiếp
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập số tiền COD!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }    
                }
                else if(hinhThucThanhToan.equals("Thanh toán online")){
                    double phiDichVu = 0;
                    double phiLoaiHang = 0;
                    double phi=0;
                    // Tính phí dịch vụ
                    if (loaiDichVu == null) {
                        loaiDichVu = "";
                    }
                    switch (loaiDichVu.toLowerCase()) {
                        case "tiết kiệm":
                            phiDichVu = 10000;
                            break;
                        case "nhanh":
                            phiDichVu = 15000;
                            break;
                        case "hỏa tốc":
                            phiDichVu = 30000;
                            break;
                        default:
                            phiDichVu = 0;
                            break;
                    }

                    // Tính phí loại hàng hóa
                    if (loaiHang == null) {
                        loaiHang = "";
                    }
                    switch (loaiHang.toLowerCase()) {
                        case "bình thường":
                            phiLoaiHang = 25000;
                            break;
                        case "dễ vỡ":
                            phiLoaiHang = 30000;
                            break;
                        case "cồng kềnh":
                            phiLoaiHang = 50000;
                            break;
                        default:
                            phiLoaiHang = 0;
                            break;
                    }
                    phi=phiDichVu+phiLoaiHang;
                    // Tạo dialog
                    JDialog qrDialog = new JDialog((Frame) null, "Quét mã QR để thanh toán", true);
                    qrDialog.setLayout(new BorderLayout());
                    //Thêm label phí
                    JLabel phiLabel = new JLabel("Số tiền cần thanh toán: " + String.format("%,.0f", phi) + " VND");
                    phiLabel.setHorizontalAlignment(JLabel.CENTER);
                    phiLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    phiLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
                    qrDialog.add(phiLabel, BorderLayout.NORTH);
                    // Thêm ảnh mã QR
                    ImageIcon qrIcon = new ImageIcon("D:\\github\\Project_IS216_Java\\src\\images\\LOGO3P1N.png"); // Đường dẫn ảnh QR
                    JLabel qrLabel = new JLabel(qrIcon);
                    qrLabel.setHorizontalAlignment(JLabel.CENTER);
                    qrDialog.add(qrLabel, BorderLayout.CENTER);

                    // Thêm nút OK
                    JButton okButton = new JButton("Đã chuyển khoản");
                    okButton.addActionListener(ex -> {
                        // Đóng dialog
                        qrDialog.dispose();

                        // Thực hiện tiếp hành động sau khi thanh toán
                        JOptionPane.showMessageDialog(null, "Xác nhận đã chuyển khoản thành công!");
                        // Tiếp tục logic xử lý...
                    });

                    JPanel bottomPanel = new JPanel();
                    bottomPanel.add(okButton);
                    qrDialog.add(bottomPanel, BorderLayout.SOUTH);

                    qrDialog.setSize(300, 350);
                    qrDialog.setLocationRelativeTo(null); // Hiển thị giữa màn hình
                    qrDialog.setVisible(true);
                }
                // Tạo đối tượng DonHang
                DonHang dh = new DonHang();
//                dh.setIdDonHang(idDonHang);
                dh.setIdKhachHang(ID_KhachHang);
                dh.setSdtNguoiGui(kh.getSDT());
                dh.setSdtNguoiNhan(sdtNguoiNhan);
                dh.setTenNguoiGui(kh.getHoTen());
                dh.setTenNguoiNhan(tenNguoiNhan);
                dh.setDiaChiNhan(diaChiDayDu);
                dh.setDichVu(loaiDichVu);
                dh.setLoaiHangHoa(loaiHang);
                dh.setIdKhoTiepNhan(idKho);
                dh.setTienCOD(tienCOD);
                
                if (!qLDonHangController.KiemTraDinhDang(dh)) {
                    JOptionPane.showMessageDialog(this, "Định dạng đơn hàng không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng lại, không thực hiện thêm
                }
            try {
                // Gọi controller để thêm đơn hàng
                 int id_dh = qLDonHangController.ThemDonHang(dh);
                // Gọi callback
                JOptionPane.showMessageDialog(this, "Tạo đơn hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
//                = new DonHangDAO().layIDDHMoiNhat(ID_KhachHang);
//                System.out.print(id_dh);
                new HoaDonKH().XuatHD(id_dh);
                
            } catch (SQLException ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Tạo đơn hàng thất bại. Vui lòng thử lại.\nChi tiết: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Tạo đơn hàng thất bại. Vui lòng thử lại.\nChi tiết: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
                
                
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
                frame.add(new KHTaoDonHangPanel(1), BorderLayout.CENTER);
            } catch (SQLException ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(KHTaoDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
}
