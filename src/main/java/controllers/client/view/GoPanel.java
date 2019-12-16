package controllers.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GoPanel extends JPanel{

	private static GoPanel instance;
	public int planeSize, boardSize;
	private GoButton[][] board;
	public int x, y;
	public boolean wasMoveMade=false;
	
	private GoPanel() {
		planeSize=0;
	}
	
    public static GoPanel getInstance() {
    	if(instance == null){
            instance = new GoPanel();
        }
        return instance;
	}
    
    public int[] getCoordinates() {
		int tab[]= new int[2];
		tab[0]=this.x;
		tab[1]=this.y;
		wasMoveMade=false;
		return tab;
	}
	
    public void drawPlane(int plane[][]) {
    	
    	removeAll();
    	revalidate();
    	
    	if(boardSize==0)
    		boardSize=plane.length;
    	
    	board= new GoButton[boardSize][boardSize];
        GridLayout layout = new GridLayout(2*boardSize, 2*boardSize);
        setLayout(layout);
        
        for (int i = 0; i <2*boardSize-1; i++)   {

        	if(i%2==0 || i==0)
        		for (int j = 0; j < 2* boardSize-1; j++)  {
            	   if(j%2==0 || j==0 ) {
            		 board[i/2][j/2]=new GoButton(i/2, j/2); 
                     add(board[i/2][j/2]);
                    }else {
                     JLabel panel1 = new JLabel();
                     panel1.setBorder(new LineBorder(Color.BLACK, 40));
                     panel1.setBackground(Color.BLACK);
                     add(panel1);
            	   }
                }
        	else
        		for (int j = 0; j < 2 *boardSize-1; j++)
        			if(j==0 || j%2==0) {
        				JLabel panel1 = new JLabel();
                        panel1.setBorder(new LineBorder(Color.BLACK, 40));
                        panel1.setBackground(Color.BLACK);
                        add(panel1);	
        			}else
                        add(new JLabel(""));       
        }
        
        for(int i=0; i<board.length; i++)
        	for(int j=0; j<board.length; j++)
        		board[i][j].addActionListener(listener);
        
        revalidate();
        
	}
    
    public void settingPlane() {
    	JButton small = new JButton("9x9");
		JButton medium = new JButton("13x13");
		JButton big = new JButton("19x19");
		JLabel mess= new JLabel("Choose plane size!");
		
		add(mess);
		add(small);
		add(medium);
		add(big);
		
		revalidate();
		
		small.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				planeSize= 9;
			}
		});
        
        medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				planeSize=  13;
			}
		});
        
        big.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				planeSize=  19;
				
			}
		});
    }
    
    ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            GoButton sender = (GoButton)e.getSource();
            x=sender.x;
      		y=sender.y;
     		wasMoveMade=true;
        }
    };

}
