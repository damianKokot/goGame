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
	public int lastX, lastY, planeSize;
	public JLabel serverComm;
	public GoPanel display;

	public PlayerGUI(GoPanel dispPanel) {
		
		setTitle("GoGame-Player");
		setResizable(false);
		JButton skip = new JButton("Skip");
		JButton pass = new JButton("Pass");
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		serverStatus= new JLabel("Server status: NOT CONNECTED");
		serverComm= new JLabel();
		serverComm.setForeground (Color.red);
		serverComm.setFont(new Font("Serif", Font.BOLD, 20));
		Box box = Box.createVerticalBox();
		lowerPanel.add(box);
		this.display=dispPanel;

		add(display, BorderLayout.CENTER);
		add(upperPanel, BorderLayout.WEST);
		add(lowerPanel, BorderLayout.SOUTH);
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
    
    public void serverStatus(String mess) {
    	serverStatus.setText(mess);
    }  
     
}
