package appgiaovan.GUI.Components;


import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class RoundedComboBox extends JComboBox<String> {

    static {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Thiết lập mặc định cho bo tròn và hover
            UIManager.put("ComboBox.arc", 20); // bo góc riêng cho ComboBox
            UIManager.put("Component.arc", 20); // áp dụng chung cho nhiều thành phần khác

            UIManager.put("ComboBox.hoverBackground", new Color(220, 220, 255));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public RoundedComboBox(String... items) {
        super(items);
        setPreferredSize(new Dimension(200, 40));
        setOpaque(false);
        setFocusable(false);
    }
}
