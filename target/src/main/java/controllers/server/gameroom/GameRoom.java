package controllers.server.gameroom;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.exception.*;
import controllers.server.*;
import models.exceptions.*;
import models.factories.*;
import models.interfaces.*;
import models.*;

public class GameRoom extends Thread {
	
	private PlayerHandler player1;
	private PlayerHandler player2;
	private GoGame game;
	private boolean isGameOn=false;
	private boolean turn = true;
	private int skips=0;
	
	
	public GameRoom(PlayerHandler player1, PlayerHandler player2) {
		this.player1=player1;
		this.player2=player2;
		player1.setID(1);
		player2.setID(2);
	}
	
	private void createGame(int plane) {
		
		IPanel panel = null;
		
		switch(plane) {
		  case 9:
			  panel = new PanelSmall();
			 break;
		  case 13:
			  panel = new PanelNormal();
		    break;
		  case 19:
			  panel = new PanelLarge();
			break;
		}
		
		try {
			game= new GoGame(player1.getID(), panel);    
			game.setOpponent(player2.getID());
			
		}catch(UserExistsException i){	
			System.out.println(i);
		}
	}
	
	private int getPlaneSize() {
		return 13;
	}
	
	private void runGame(){
		createGame(getPlaneSize());
		isGameOn=true;
		updateGame();
	}
	
	private void updateGame() {
		int[][] plane = game.getGameStatus();
		JsonObject command = new JsonObject();
		Gson gson = new Gson();
		command.addProperty("command", "gameupdate");
		command.addProperty("plane", gson.toJson(plane));
		
		player1.messageToClient(command.toString());
		player2.messageToClient(command.toString());
	}
	
	private void makeMove(int id) {
		
		String move;
		JsonObject command = new JsonObject();
		
		try {
			if(id==1)
				move=player1.waitForMove();
			else
				move=player2.waitForMove();
			
			JsonParser jsonParser = new JsonParser();
			command= jsonParser.parse(move).getAsJsonObject();
			
			commandInterpreter(id, command);
			
		}catch(ConnectionErr e) {
			System.out.println(e);
		} catch(NoMoveEx e) {
			repeatMove(id); 
		}
	}
	
	private void commandInterpreter(int id,JsonObject command) throws NoMoveEx {
		
		if(command.get("command").getAsString().equals("move")) {
			try {
				game.push(id,  command.get("x").getAsInt(), command.get("y").getAsInt());
				skips=0;
			}
			catch(PushException ex) { repeatMove(id); }
			
		}else if(command.get("command").getAsString().equals("skip")){
			
			//skips++;
			
			//if(skips==2)
				//TODO implement !
			//else;
			
		}else if(command.get("command").getAsString().equals("pass")){
			if(id==1)
				gameSumUp(2);
			else
				gameSumUp(1);
		}else {
			throw new NoMoveEx();
		}
	}
	
	private void repeatMove(int id) {
		
	}
	
    private void gameSumUp(int id) {
	
	}
	
	@Override
	public void run(){
		
		runGame();
		
		while(isGameOn) {
			
			try{
				
				if(turn)
					makeMove(player1.getID());
				else
					makeMove(player2.getID());
				
				updateGame();
				
				turn=!turn;
				
				Thread.yield(); 
				Thread.sleep(1);
			}catch(InterruptedException i){
				System.out.println(i);
			}
			
		}

	}

}
