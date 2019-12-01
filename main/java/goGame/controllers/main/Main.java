package goGame.controllers.main;

import goGame.controllers.server.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame{
	
	private GameServer server;
	private boolean isRunning=false;
	private JLabel clientCount;
	private JLabel serverStatus;
	
	public Main() {
		
		setTitle("GoServer-Controller");
		JButton runServer = new JButton("Run Server");
		JButton killServer = new JButton("Kill Server");
		JButton refresh = new JButton("Refresh");
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		clientCount= new JLabel("Clients connected: ...");
		serverStatus= new JLabel("Server status: OFFLINE");
        
		add(middlePanel);
		add(upperPanel, BorderLayout.NORTH);
		add(lowerPanel, BorderLayout.SOUTH);
		upperPanel.add(runServer);
		upperPanel.add(killServer);
		upperPanel.add(refresh);
		middlePanel.add(serverStatus);
		lowerPanel.add(clientCount);
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
        
        refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
	}

	public static void main(String[] args) {
		Main GoServerController = new Main();
	}
	
	private void refresh() {
		if(isRunning) 
		  serverStatus.setText("Server status: ONLINE");
		else
		  serverStatus.setText("Server status: OFFLINE");
	}
	
	private void startServer() {
		if(!isRunning) {
		 server = new GameServer(5000);
		 isRunning=true;
		 server.start();
		 refresh();
		}
	}
	
	private void killServer() {
		if(isRunning) {
		 server.interrupt();
		 isRunning=false;
		 refresh();
		 System.exit(0);
		}
	}

}
 