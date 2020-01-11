package controllers.client.interfaces;

public interface GoGui {
	void refresh (int x, int y, int value);
	void setMessage(String message);
	void nextStage(int value);
	void serverStatus(boolean state);
}
