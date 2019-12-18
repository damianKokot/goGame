package controllers.client.interfaces;

public interface GameMember {
	void doMove(int x, int y);
	void skipRound();
	void endGame();
	void commandInterpreter(String message);
}
