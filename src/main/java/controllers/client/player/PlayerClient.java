package controllers.client.player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.client.abstractclasses.Client;
import controllers.client.interfaces.GameMember;
import controllers.client.interfaces.GoGui;
import controllers.commandfacade.CommandMaker;

public abstract class PlayerClient extends Client implements GoGui {
	
	private int[][] plane;
	private int myId=2;
	private int firstMove=0;
	
	public PlayerClient() {
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
		serverStatus(true);
		
		switch(command.get("command").getAsString()) {
		
		  case "gameupdate":
			  gameUpdate(command);
			  if(firstMove==1)
			      nextStage(plane.length);
			 break;
			 
		  case "repeatmove":
			   setMessage("You can't move like this! Repeat your move!");  
		    break;
		    
		  case "chooseplane":
			   myId=1;
			   setMessage("Choose board size!"); 
			   nextStage(1);
			break;
			
		  case "wait":
			   setMessage("Waiting for opponents move..."); 
			   if(firstMove==0)
				  nextStage(0);
			break;
			
		  case "winner":
			   setMessage("You have won!"); 
			break;
			
		  case "looser":
			    setMessage("You have lost!"); 
			break;
			    
		  case "yourmove":
			    setMessage("It is your move!"); 	
		    break;
		    
		  case "tie":
			    setMessage("It is a tie!"); 		  
		    break;
		    
		  case "playerdisconnected":
			 	setMessage("Opponent has disconnected!");  
		    break;
		    
		  case "serverdied":
			    setMessage("Server has disconnected!"); 
			    serverStatus(false);
		    break;
		    
		}
		firstMove++;
		

	}
	
	private void gameUpdate(JsonObject command) {
		Gson gson = new Gson();
		int[][] arr=gson.fromJson(command.get("plane").getAsString(), int[][].class);
		
		
		for(int i=0; i< arr.length; i++)
			for(int j=0; j< arr.length; j++)
				if(this.plane==null || this.plane[i][j]!=arr[i][j])
					refresh(i,j, arr[i][j]);
		
		
		this.plane=arr;
	}
    
    public int[][] getPlane(){
    	return this.plane;
    }
    
    public int getMyId() {
    	return myId;
    }
 
	public void setPlane(int size) {
		messageToServer(commander.setPlane(size).toString());
	}
	
}
