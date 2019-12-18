package controllers.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class PlayerGUI extends JFrame {

    public JLabel serverComm;
    public JLabel serverStatus;
    public JPanel display;

    public PlayerGUI() {

        setTitle("GoGame-Player");
        setResizable(false);
        JPanel lowerPanel = new JPanel();
        serverStatus = new JLabel("SERVER: Not connected...");
        serverComm = new JLabel();
        serverComm.setForeground(Color.red);
        serverComm.setFont(new Font("Serif", Font.BOLD, 20));
        Box box = Box.createVerticalBox();
        lowerPanel.add(box);

        add(lowerPanel, BorderLayout.SOUTH);
        box.add(serverComm);
        box.add(serverStatus);
        lowerPanel.setBackground(Color.GRAY);
        lowerPanel.setPreferredSize(new Dimension(100, 90));
        setBounds(100, 100, 730, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void setMessage(String mess) {
        serverComm.setText(mess);
    }

    public void serverStatus(boolean state) {
        if (state)
            serverStatus.setText("SERVER: Connected");
        else
            serverStatus.setText("SERVER: Not connected...");
    }

    public void changePanel(JPanel newPanel) {

        if (display != null)
            this.remove(display);

        this.display = newPanel;
        this.add(display, BorderLayout.CENTER);
        this.display.revalidate();

    }

}