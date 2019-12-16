package controllers.client.interfaces;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public interface CommandCreator {
	public JsonObject getPlane();
	public JsonObject waitForTurn();
	public JsonObject yourTurn();
	public JsonObject gameUpdate(int[][] plane);
	public JsonObject win(int winnerPoints, int looserPoints);
	public JsonObject loose(int winnerPoints, int looserPoints);
	public JsonObject repeatMove();
	public JsonObject makeMove(int x, int y);
	public JsonObject pass();
	public JsonObject skipRound();
	public JsonObject setPlane(int x);
	public JsonObject tie();
	public JsonObject playerDisconection();
}
