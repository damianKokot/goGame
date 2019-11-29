package goGame.controllers.server;

import goGame.controllers.abstractclasses.Server;

public class GameServer extends Server{

	public GameServer(int port) {
		super(port);
	}
	
	public void messageInterpreter(String mess) {
	    messageToGame(mess);
	}
	
	private synchronized void messageToClient(String mess) {
	    System.out.println(mess); 
	}
	    
	private synchronized void messageToGame(String mess) {
	    System.out.println(mess); 
	}

	private Thread findPlayer(String id) {
		//TODO: implement
	}
}
