package goGame.controllers.server;

import java.util.ArrayList;
import goGame.controllers.interfaces.*;
import goGame.controllers.abstractclasses.Server;
import goGame.controllers.player.*;
import goGame.controllers.exception.*;


public class GameServer extends Server{

	public GameServer(int port) {
		super(port);
	}
	
	private synchronized void messageToClient(String mess, String id) {
		
		try {
			GameUtil client= findInArray(id, playerList);
			client.messageToClient(mess);
		}catch(CantFindEx ex){
			System.out.println(ex);
		}

	}
	    
	public synchronized void messageToGame(String mess, String id) {
		try {
			GameUtil game= findInArray(id, gameList);
			game.messageToClient(mess);
		}catch(CantFindEx ex){
			System.out.println(ex);
		}
	}

	public synchronized GameUtil findInArray(String id, ArrayList<GameUtil> c) throws CantFindEx {
		
		for(int i = 0; i < c.size(); i++)
			if(c.get(i).getID().equals(id))
		    	return c.get(i);
		
		throw new CantFindEx();		
	}
	
}
