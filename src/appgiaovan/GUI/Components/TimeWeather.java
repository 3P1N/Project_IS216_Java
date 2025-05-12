package appgiaovan.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeWeather extends JPanel {
    private final JLabel timeLabel;
    private final JLabel weatherLabel;

    public TimeWeather(String weatherText) {
        // Dùng BorderLayout để đưa timeLabel bên trái, weatherLabel bên phải
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Time label
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(timeLabel, BorderLayout.WEST);

        // Weather label
        weatherLabel = new JLabel(weatherText);
        weatherLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        weatherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(weatherLabel, BorderLayout.EAST);

        // Timer để cập nhật mỗi giây
        new Timer(1000, e -> updateTime()).start();
        updateTime();
    }

    private void updateTime() {
        timeLabel.setText(
          LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"))
        );
    }

    /** Nếu muốn thay đổi text thời tiết sau khi tạo */
    public void setWeatherText(String text) {
        weatherLabel.setText(text);
    }
}
