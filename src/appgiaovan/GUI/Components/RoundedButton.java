
package appgiaovan.GUI.Components;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private final int radius;

    public RoundedButton(String text) {
        this(text, 30); // default radius
    }

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        // Optional: set style consistent with FlatLaf
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(Color.WHITE);
        setBackground(new Color(45, 140, 240)); // FlatLaf primary blue
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);

        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fill(roundedRect);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border to keep the look clean
    }

    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius).contains(x, y);
    }
}

