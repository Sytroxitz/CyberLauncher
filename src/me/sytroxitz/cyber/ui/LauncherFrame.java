package me.sytroxitz.cyber.ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import me.sytroxitz.cyber.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LauncherFrame {

    URL fontUrl;
    {
        try {
            fontUrl = new URL("https://github.com/Sytroxitz/cyber-launcher/raw/main/Poppins-Bold.ttf");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    Font font;
    {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.BOLD, 35);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Font smallFont;
    {
        try {
            smallFont = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.BOLD, 18);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LauncherFrame() {
        initialize();
    }

    public JFrame frame;
    public JPanel panel;

    private void initialize() {

        /*Scene scene = new Scene(new Group(), 1400, 830);
        scene.getStylesheets().add("ui/style/ui.css");*/

        frame = new JFrame("CYBER Launcher v1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(620, 125, 1400, 830);
        frame.setVisible(true);

        panel = new JPanel();
        panel.setBounds(0, 0, 1400, 830);
        panel.setBackground(new Color(44, 47, 51));
        panel.repaint();
        panel.setVisible(true);

        JLabel label = new JLabel();
        label.setText("CYBER Launcher");
        label.setForeground(Color.white);
        label.setBounds(5, 5, 0, 0);
        label.setVisible(true);
        label.setFont(font);
        panel.add(label);

        JButton button = new JButton();
        button.setFont(smallFont);
        button.setText("Launch CYBER 1.8.8");
        button.setBounds(0, 450, 350, 290);
        button.setBackground(new Color(88, 101, 242));
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.setVisible(true);
        button.addActionListener(e -> new Thread(() -> Main.launch()).start());
        panel.add(button);

        frame.getContentPane().add(panel);
    }
}
