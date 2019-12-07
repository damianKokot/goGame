package controllers.client.interfaces;

public interface GameMember {
	public void doMove(int x, int y);
	public void planeUpdate(int[][] plane);	
	public void skipRound();	
	public void endGame();
}
