package controllers.client.player;

import controllers.client.abstractclasses.*;
import controllers.client.interfaces.GameMember;
import controllers.commandfacade.CommandMaker;
import models.factories.PanelLarge;
import models.factories.PanelNormal;
import models.factories.PanelSmall;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Player extends Client implements GameMember {
	
	private int[][] plane;
	private String dispMessage;
	private CommandMaker commander;
	
	public Player(String adress, int port) {
		super(adress,port);
		dispMessage="";
		commander= new CommandMaker();
	}
	
	@Override
	public void doMove(int x, int y) {
		messageToServer(commander.makeMove(x, y).toString());
	}
	
	@Override
	public void skipRound() {	
		messageToServer(commander.skipRound().toString());
	} 
	
	@Override
	public void endGame() {
		messageToServer(commander.pass().toString());
	}
	
	@Override
	public void commandInterpreter(String mess) {
		
		JsonParser jsonParser = new JsonParser();
		JsonObject command = jsonParser.parse(mess).getAsJsonObject();
		
		switch(command.get("command").getAsString()) {
		
		  case "gameupdate":
			  gameUpdate(command);
			  dispMessage="0";
			 break;
			 
		  case "repeatmove":
			  dispMessage="1";			  
		    break;
		    
		  case "chooseplane":
			  dispMessage="2";
			break;
			
		  case "wait":
			  dispMessage="3";
			break;
			
		  case "winner":
			  dispMessage="4";
			break;
			
		  case "looser":
			  dispMessage="5";
			break;
			
		  case "yourmove":
			  dispMessage="6";			  
		    break;
		    
		  case "tie":
			  dispMessage="7";			  
		    break;
		    
		  case "playerdisconnected":
			  dispMessage="8";			  
		    break;
		    
		  case "serverdied":
			  dispMessage="9";			  
		    break;
		    
		}	
	}
	
	private void gameUpdate(JsonObject command) {
		Gson gson = new Gson();
		int[][] arr=gson.fromJson(command.get("plane").getAsString(), int[][].class);
		
		planeUpdate(arr);
		dispMessage="update";
       
	}
	
    public void planeUpdate(int[][] plane) {
    	this.plane=plane;
	}
    
    public int[][] getPlane(){
    	return this.plane;
    }
    
    public String dispMessage() {
    	String line=dispMessage;
    	dispMessage="";
    	
    	return line;
    }
 
	public void setPlane(int x) {
		messageToServer(commander.setPlane(x).toString());
	}
	


}
