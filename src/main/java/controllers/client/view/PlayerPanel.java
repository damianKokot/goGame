package controllers.client.view;

import controllers.client.interfaces.GoGui;
import controllers.client.player.PlayerClient;

public class PlayerPanel extends PlayerClient{
	
	public PlayerGUI GUI;
    public GoPanel display;

	public PlayerPanel(String adress, int port) {
		super(adress, port);
		this.display = GoPanel.getInstance();
		this.GUI = new PlayerGUI(this.display);	
	}

	@Override
	public void refresh(int x, int y, int value) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void setMessage(String message) {
		GUI.setMessage(message);
	}


}
