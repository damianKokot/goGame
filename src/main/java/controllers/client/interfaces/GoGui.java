package controllers.client.interfaces;

import controllers.client.view.PlayerGUI;

public interface GoGui {
	
	public void refresh (int x, int y, int value);
	public void setMessage(String message);
	public void nextStage(int value);
	public void serverStatus(boolean state);
	
	
}
