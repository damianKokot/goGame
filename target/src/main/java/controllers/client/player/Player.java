package controllers.client.player;

import controllers.client.abstractclasses.*;
import controllers.client.interfaces.GameMember;
import controllers.server.interfaces.*;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Player extends Client implements GameMember {
	
	private int[][] plane;
	
	public Player(String adress, int port) {
		super(adress,port);
	}
	
	@Override
	public void doMove(int x, int y) {
		JsonObject command = new JsonObject();
		command.addProperty("command", "move");
		command.addProperty("x", x);
		command.addProperty("y", y);
		
		messageToServer(command.toString());
	}
	
	@Override
	public void gameUpdate(String mess) {
		JsonParser jsonParser = new JsonParser();
		JsonObject command = jsonParser.parse(mess).getAsJsonObject();
		
		Gson gson = new Gson();
		int[][] arr=gson.fromJson(command.get("plane").getAsString(), int[][].class);
		
		planeUpdate(arr);
       
	}
	
    public void planeUpdate(int[][] plane) {
    	this.plane=plane;
	}
    
    public int[][] getPlane(){
    	return this.plane;
    }

	@Override
	public void skipRound() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "skip");
		messageToServer(command.toString());
	} 
	
	@Override
	public void endGame() {
		JsonObject command = new JsonObject();
		command.addProperty("command", "pass");
		messageToServer(command.toString());
	}



}
