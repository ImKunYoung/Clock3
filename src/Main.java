import java.awt.Font;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.JFrame;

public class Main extends JFrame {
    private JLabel timeLabel;

    public Main() {
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 60));
        add(timeLabel);

        setTitle("Digital Clock");
        setSize(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            LocalTime time = LocalTime.now();
            timeLabel.setText(time.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}