package controllers.client.interfaces;

import com.google.gson.JsonObject;


public interface CommandCreator {
	JsonObject getPlane();
	JsonObject waitForTurn();
	JsonObject yourTurn();
	JsonObject gameUpdate(int[][] plane);
	JsonObject win(int winnerPoints, int looserPoints);
	JsonObject loose(int winnerPoints, int looserPoints);
	JsonObject repeatMove();
	JsonObject makeMove(int x, int y);
	JsonObject pass();
	JsonObject skipRound();
	JsonObject setPlane(int x);
	JsonObject tie();
	JsonObject playerDisconection();
	JsonObject serverDisconection();
}
