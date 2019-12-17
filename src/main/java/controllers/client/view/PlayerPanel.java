package controllers.client.view;

import javax.swing.JPanel;

import controllers.client.interfaces.GoGui;
import controllers.client.player.PlayerClient;
import controllers.client.view.panels.ChoseSizePanel;
import controllers.client.view.panels.WaitingPanel;
import controllers.client.view.panels.panels_parts.PanelState;

public class PlayerPanel extends PlayerClient{
	
	public PlayerGUI GUI;
	public PanelState currentPanel;
	
	public PlayerPanel() {
		this.GUI = new PlayerGUI();	
		connectToServer();
		nextStage(0);
		
	}

	@Override
	public void refresh(int x, int y, int value) {
		currentPanel.refresh(x, y, value);
	}

	@Override
	public void setMessage(String message) {
		GUI.setMessage(message);
	}
	
	@Override
	public void serverStatus(boolean state) {
		GUI.serverStatus(state);
	}

	@Override
	public void nextStage(int value) {
		
		if(currentPanel==null) {
			currentPanel = new WaitingPanel(this);
			GUI.changePanel(currentPanel.getPanel());
		} else {
			currentPanel = currentPanel.next(value);
			GUI.changePanel(currentPanel.getPanel());
		}
		
		//if(this.getMyId()==1)
	//	  GUI.changePanel(new ChoseSizePanel(this));

	}
	

}
