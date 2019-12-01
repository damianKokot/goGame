package goGame.controllers.interfaces;

public interface GameMember {
	public String getID();
	public void setID(String id);
	public void doMove(int x, int y);
	public void gameUpdate(int[][] plane);	
}
