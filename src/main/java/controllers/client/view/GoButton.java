package controllers.client.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class GoButton extends JButton {
	
	public int x;
	public int y;
	public int player;
	
	public GoButton(int x, int y) {
		this.x=x;
		this.y=y;
		player=0;
		setColor();
	}
	
	public void changePlayer(int occupied) {
		this.player=occupied;
		setColor();
	}
	
	private void setColor() {
		if(player==0)
			setForeground(Color.GRAY);
		else if(player==1)
		    setForeground(Color.BLACK);
		else if( player==2)
		    setForeground(Color.RED);
	}

}
