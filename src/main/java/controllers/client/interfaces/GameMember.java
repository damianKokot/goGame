package controllers.client.interfaces;

public interface GameMember {
	public void doMove(int x, int y);
	public void skipRound();	
	public void endGame();
	public void commandInterpreter(String message);
}
