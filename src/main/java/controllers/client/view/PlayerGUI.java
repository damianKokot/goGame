package controllers.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class PlayerGUI extends JFrame {
	
	public JLabel serverStatus;
	private TextField y;
	private TextField x;
	private String dispMessage;
	private GoPanel display;
	public int lastX, lastY, planeSize;
	private GoPanelListener listener;
	public JLabel serverComm;

	public PlayerGUI() {
		
		setTitle("GoGame-Player");
		setResizable(false);
		JButton connectServer = new JButton("Connect");
		JButton skip = new JButton("Skip");
		JButton pass = new JButton("Pass");
		JButton move = new JButton("Make move");
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		display = GoPanel.getInstance();
		serverStatus= new JLabel("Server status: NOT CONNECTED");
		x =new TextField("x");
		y =new TextField("y");
		dispMessage="";
		listener= new GoPanelListener(display);
		listener.start();
		serverComm= new JLabel();
		serverComm.setForeground (Color.red);
		serverComm.setFont(new Font("Serif", Font.BOLD, 20));
		Box box = Box.createVerticalBox();
		lowerPanel.add(box);

		add(display, BorderLayout.CENTER);
		add(upperPanel, BorderLayout.WEST);
		add(lowerPanel, BorderLayout.SOUTH);
		upperPanel.add(connectServer);
		upperPanel.add(move);
		upperPanel.add(x);
		upperPanel.add(y);
		upperPanel.add(skip);
		upperPanel.add(pass);
		box.add(serverComm);
		box.add(serverStatus);
		upperPanel.setBackground(Color.GRAY);
		lowerPanel.setBackground(Color.GRAY);
		upperPanel.setPreferredSize(new Dimension(100, 100));
		lowerPanel.setPreferredSize(new Dimension(100, 60));
        setBounds(100, 100, 700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);	
        
        connectServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispMessage="connect";
			}
		});
        
        move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tab[]= listener.waitForMove();
				lastX=tab[0];//Integer.parseInt(x.getText());
				lastY=tab[1];//Integer.parseInt(y.getText());
				
				dispMessage="move";				
			}
		});
        
        skip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispMessage="skip";	
			}
		});
        
        pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				dispMessage="pass";			
			}
		});
      
	}
	
   public void setMessage(String mess) {	
		serverComm.setText(mess);
   }
	
	public void drawPlane(int plane[][]) {	
		listener.generateBoard(plane);
		getContentPane().add(display);
	}
	
	public void makeMove() {	
		int tab[]= listener.waitForMove();
		lastX=tab[0];//Integer.parseInt(x.getText());
		lastY=tab[1];//Integer.parseInt(y.getText());
		dispMessage="move";	
	}
    
	public void planeUpdate(int plane[][]) {
		
    }
	
	public void settingPlane() {
		display.settingPlane();
		planeSize=listener.waitForSize();
        dispMessage="setplane";
    }
	
    public String dispMessage() {
    	String line=dispMessage;
    	dispMessage="";
    	return line;
    }
    
    public void serverStatus(String mess) {
    	serverStatus.setText(mess);
    }  
     
}
