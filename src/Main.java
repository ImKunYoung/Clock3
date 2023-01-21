import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

public class Main extends JFrame {
    private JPanel panel;
    private JLabel timeLabel;
    private Point initialClick;
    private boolean isBlack = true;

    public Main() {
        panel = new JPanel();
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Gothic", Font.PLAIN, 16));
        timeLabel.setSize(100, 30);
        panel.add(timeLabel);

        panel.setBackground(Color.WHITE);
        setSize(100, 30);
        setUndecorated(true);

        this.add(panel);

        setAlwaysOnTop(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(mousePressed);
        addMouseMotionListener(mouseDragged);

        while (true) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm:ss");
            timeLabel.setText(sdf.format(cal.getTime()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    MouseAdapter mousePressed = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            initialClick = e.getPoint();
            getComponentAt(initialClick);
        }

        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                System.exit(-1);
            }

            if (e.getButton() == MouseEvent.BUTTON1) {
                if (isBlack) {
                    panel.setBackground(Color.WHITE);
                    timeLabel.setForeground(Color.BLACK);
                    isBlack = false;
                } else {
                    panel.setBackground(Color.BLACK);
                    timeLabel.setForeground(Color.WHITE);
                    isBlack = true;
                }
            }
        }
    };

    MouseAdapter mouseDragged = new MouseAdapter() {
        public void mouseDragged(MouseEvent e) {
            // get location of Window
            int thisX = getLocation().x;
            int thisY = getLocation().y;

            // Determine how much the mouse moved since the initial click
            int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
            int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

            // Move window to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;
            setLocation(X, Y);
        }
    };

    public static void main(String[] args) {
        new Main();
    }
}