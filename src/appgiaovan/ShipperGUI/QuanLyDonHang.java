package appgiaovan.ShipperGUI;

import appgiaovan.Controller.CapNhatController;
import appgiaovan.Controller.QLDonHangController;
import appgiaovan.EmployeeGUI.QuanLyDonHangPanel;
import appgiaovan.EmployeeGUI.SuaDonHangFrame;
import appgiaovan.EmployeeGUI.TableDonHang;
import appgiaovan.Entity.DonHang;
import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.RoundedButton;
import appgiaovan.GUI.Components.TimeWeather;
import com.formdev.flatlaf.FlatLightLaf;
import static com.sun.tools.attach.VirtualMachine.list;
import javax.swing.*;
import java.awt.*;
import static java.nio.file.Files.list;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyDonHang extends JPanel {

    private NVGHLoc filter = new NVGHLoc();
    private TableDonHang listOrder ;
    private final QLDonHangController controller = new QLDonHangController();
    public QuanLyDonHang(int idtk) throws SQLException, ClassNotFoundException {
        /*setTitle("Quản Lý Đơn Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);*/

        // Panel Menu
        //NVGHMenu menu = new NVGHMenu();
        //add(menu, BorderLayout.WEST);
        setLayout(new BorderLayout());

        // MAIN PANEL: dùng BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 1) Header (thời gian + thời tiết)
        mainPanel.add(new TimeWeather("Hồ Chí Minh 30°C"), BorderLayout.NORTH);

        // 2) Trung tâm gồm filter + bảng, xếp dọc
        JPanel centerPanel = new JPanel(new BorderLayout());

        // 2.1) Thanh lọc lên trên
        
        filter.setPreferredSize(new Dimension(0, 60));  // để layout manager tự co giãn chiều ngang
        centerPanel.add(filter, BorderLayout.NORTH);

        // 2.2) Bảng danh sách xuống dưới
        // Chuẩn bị header
        String[] columns = {
            "", "Mã đơn hàng", "Tên người nhận", "Địa chỉ", "SĐT nhận",
            "Trạng thái", "Tiền COD", "Thời gian tạo", "SĐT gửi", "Tên người gửi", "Kho tiếp nhận"
        };

        // Chuyển List<DonHang> thành Object[][]
        QLDonHangController dsdh = new QLDonHangController();
        List<DonHang> ds = dsdh.HienThiDSDHChoNVGH(idtk);
        Object[][] data = new Object[ds.size()][columns.length];
        for (int i = 0; i < ds.size(); i++) {
            DonHang dh = ds.get(i);
            data[i][0] = false;                                    // checkbox
            data[i][1] = dh.getIdDonHang();
            data[i][2] = dh.getTenNguoiNhan();
            data[i][3] = dh.getDiaChiNhan();
            data[i][4] = dh.getSdtNguoiNhan();
            data[i][5] = dh.getTrangThai();
            data[i][6] = dh.getTienCOD();
            data[i][7] = dh.getThoiGianTao();
            data[i][8] = dh.getSdtNguoiGui();
            data[i][9] = dh.getTenNguoiGui();
            data[i][10] = dh.getIdKhoTiepNhan();
        }
        listOrder = new TableDonHang(columns, data);
        centerPanel.add(listOrder, BorderLayout.CENTER);
        
        // 3) Gắn centerPanel vào mainPanel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Thêm mainPanel vào JFrame
        add(mainPanel, BorderLayout.CENTER);
        
        //Gán sự kiện tìm kiếm đơn hàng
        filter.getfilterButton().addActionListener(e -> {
            try {
                DonHang dh = filter.getDonHang();

                HienThiDanhSach(dh);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //Gan su kien cap nhat "da giao"
        filter.getDGButton().addActionListener(e ->{
            try {
                System.out.print(123);
                XuLyCapNhatDonHang("Đã giao");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        filter.getTBButton().addActionListener(e ->{
            try {
                System.out.print(234);
                XuLyCapNhatDonHang("Giao thất bại");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

//        topPanel.getupdateButton().addActionListener(e -> {
//            try {
//                XuLySuaDonHang();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (Exception ex) {
//                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });

        // Hiển thị danh sách ngay khi mở panel
        HienThiDanhSach();
    }
    public final void HienThiDanhSach() throws SQLException, ClassNotFoundException {
        List<DonHang> dsDonHang = controller.LayDSDonHang();
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[dsDonHang.size()][columns.length];

        for (int i = 0; i < dsDonHang.size(); i++) {
            data[i] = dsDonHang.get(i).toTableRow();
        }

        listOrder.setTableData(data);
    }

    public final void HienThiDanhSach(DonHang dh) throws SQLException, ClassNotFoundException {
        List<DonHang> dsDonHang = controller.LayDSDonHang(dh);
        String[] columns = DonHang.getTableHeaders();

        // Kiểm tra xem danh sách có trống không
        if (dsDonHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy đơn hàng phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Đặt dữ liệu trống cho bảng
            Object[][] data = new Object[0][columns.length];
            listOrder.setTableData(data);  // Cập nhật bảng với dữ liệu rỗng
            return;
        }

        // Chuyển List<DonHang> thành Object[][] cho bảng
        Object[][] data = new Object[dsDonHang.size()][columns.length];
        for (int i = 0; i < dsDonHang.size(); i++) {
            data[i] = dsDonHang.get(i).toTableRow();
        }

        // Cập nhật bảng với dữ liệu mới
        listOrder.setTableData(data);
    }
    public void XuLyCapNhatDonHang(String trangThai) throws SQLException, ClassNotFoundException, Exception  {
        for (int i = 0; i < listOrder.getRowCount(); i++) {
            Boolean isChecked = (Boolean) listOrder.getValueAt(i, 0); // Cột 0 là checkbox
            if (Boolean.TRUE.equals(isChecked)) {
                // Lấy thông tin dòng được chọn
                Integer maDonHang = (Integer) listOrder.getValueAt(i, 1); // cột 1: mã ĐH
                System.out.println(maDonHang);

                // Gọi hàm xử lý
                new CapNhatController().CapNhatDHDaGiao(maDonHang, trangThai);
                HienThiDanhSach();

            }
        }
    }



//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                UIManager.setLookAndFeel(new FlatLightLaf());
//            } catch (Exception ex) {
//                System.err.println("Không thể cài đặt FlatLaf");
//            }
//            try {
//                new QuanLyDonHang().setVisible(true);
//            } catch (SQLException ex) {
//                Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//    }
}
