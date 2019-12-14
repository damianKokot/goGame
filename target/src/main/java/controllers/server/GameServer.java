package controllers.server;

import java.util.ArrayList;

import controllers.client.player.*;
import controllers.exception.*;
import controllers.server.abstractclasses.Server;
import controllers.server.interfaces.*;


public class GameServer extends Server{

	public GameServer(int port) {
		super(port);
	}

	/*
	public synchronized GameUtil findInArray(int id, ArrayList<GameUtil> c) throws CantFindEx {
		
		for(int i = 0; i < c.size(); i++)
			if(c.get(i).getID()==id)
		    	return c.get(i);
		
		throw new CantFindEx();		
	}
	*/
	
}
