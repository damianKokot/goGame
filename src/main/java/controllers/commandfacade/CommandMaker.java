package controllers.commandfacade;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.client.interfaces.CommandCreator;

public class CommandMaker implements CommandCreator{
	
	public CommandMaker(){};

	@Override
	public JsonObject getPlane() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "chooseplane");
		return command;
	}

	@Override
	public JsonObject waitForTurn() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "wait");
		return command;
	}

	@Override
	public JsonObject yourTurn() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "yourmove");
		return command;
	}
	
	@Override
	public JsonObject playerDisconection() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "playerdisconnected");
		return command;
	}

	@Override
	public JsonObject gameUpdate(int[][] plane) {
		JsonObject command = new JsonObject();
		Gson gson = new Gson();
		command.addProperty("command", "gameupdate");
		command.addProperty("plane", gson.toJson(plane));
		return command;
	}

	@Override
	public JsonObject win(int winnerPoints, int looserPoints) {
		JsonObject command = new JsonObject();
		command.addProperty("command", "winner");
		command.addProperty("yours", winnerPoints);
		command.addProperty("oponents", looserPoints);
		return command;
	}

	@Override
	public JsonObject loose(int winnerPoints, int looserPoints) {
		JsonObject command = new JsonObject();
		command.addProperty("command", "looser");
		command.addProperty("yours", looserPoints);
		command.addProperty("oponents", winnerPoints);
		return command  ;
	}
	
	@Override
	public JsonObject tie() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "tie");
		return command  ;
	}

	@Override
	public JsonObject repeatMove() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "repeatmove");
		return command;
	}

	@Override
	public JsonObject makeMove(int x, int y) {
		JsonObject command = new JsonObject();
		command.addProperty("command", "move");
		command.addProperty("x", x);
		command.addProperty("y", y);
		return command;
	}

	@Override
	public JsonObject pass() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "pass");
		return command;
	}

	@Override
	public JsonObject skipRound() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "skip");
		return command;
	}

	@Override
	public JsonObject setPlane(int x) {
		JsonObject command = new JsonObject();
		command.addProperty("command", "setplane");
		command.addProperty("x", x);
		return command;
	}

}
