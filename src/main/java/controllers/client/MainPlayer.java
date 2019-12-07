package controllers.client;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controllers.client.interfaces.GameMember;
import controllers.client.player.Player;

public class MainPlayer extends JFrame{
	
	Player player;
	JLabel serverStatus;
	TextField y;
	TextField x;
	JPanel display; 

	public MainPlayer() {
		
		setTitle("GoGame-Player");
		JButton connectServer = new JButton("Connect");
		JButton createGame = new JButton("Create Game");
		JButton joinGame = new JButton("Join Game");
		JButton move = new JButton("Make move");
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		display = new JPanel();
		serverStatus= new JLabel("Server status: NOT CONNECTED");
		x =new TextField("x");
		y =new TextField("y");

		add(display, BorderLayout.CENTER);
		add(upperPanel, BorderLayout.WEST);
		add(lowerPanel, BorderLayout.SOUTH);
		upperPanel.add(connectServer);
		//upperPanel.add(createGame);
		//upperPanel.add(joinGame);
		upperPanel.add(move);
		upperPanel.add(x);
		upperPanel.add(y);
		lowerPanel.add(serverStatus);
		upperPanel.setBackground(Color.GRAY);
		lowerPanel.setBackground(Color.GRAY);
		upperPanel.setPreferredSize(new Dimension(100, 100));
		lowerPanel.setPreferredSize(new Dimension(100, 60));
        setBounds(100, 100, 700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);	
        
        connectServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectToServer();
			}
		});
        
        move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.doMove(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
				drawPlane();
			}
		});
        
        createGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        
        joinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	
	public static void main(String args[]) {       
		 MainPlayer GoGamePlayer = new MainPlayer();
	}
	
	public void connectToServer() {
		player = new Player("127.0.0.1", 5000);
		player.start();
		serverStatus.setText("Server status: CONNECTED");
	}
	
	public void drawPlane() {
	  	
		int plane[][]=player.getPlane();
		
		Box box = Box.createVerticalBox();
		
		display.removeAll();
		display.add(box);
		
    	for(int i = 0; i < plane.length; i++) {
    		
    		JLabel tab = new JLabel();
    		
    		for(int j = 0; j < plane.length; j++) {
    			int el=plane[i][j];
    			tab.setText(tab.getText()+ Integer.toString(el) +" ");
    			box.add(tab);
        	}	

    	}
    	display.revalidate();
	}
     
}
