package controllers.server;

import controllers.server.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainServer extends JFrame {

    private Server server;
    private boolean isRunning = false;
    private JLabel serverStatus;

    public MainServer() {

        setTitle("GoServer-Controller");
        JButton runServer = new JButton("Run Server");
        JButton killServer = new JButton("Kill Server");
        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        serverStatus = new JLabel("Server status: OFFLINE");

        add(middlePanel);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.SOUTH);

        upperPanel.add(runServer);
        upperPanel.add(killServer);
        middlePanel.add(serverStatus);
        setBounds(100, 100, 400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        runServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        killServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                killServer();
            }
        });

    }

    private void refresh() {
        if (isRunning)
            serverStatus.setText("Server status: ONLINE");
        else
            serverStatus.setText("Server status: OFFLINE");
    }

    private void startServer() {
        if (!isRunning) {
            server = new Server(5000);
            isRunning = true;
            server.start();
            refresh();
        }
    }

    private void killServer() {
        if (isRunning) {
            server.interrupt();
            isRunning = false;
            refresh();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        MainServer GoServerController = new MainServer();
    }
}