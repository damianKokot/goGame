package goGame.controllers.server;

import goGame.controllers.server.*;
import goGame.controllers.abstractclasses.*;
import java.net.*; 
import java.io.*; 


public class PlayerHandler extends Thread { 

    final DataInputStream in; 
    final DataOutputStream out; 
    final Socket s; 
    static Server server;
    public String playerID;
      
    // Constructor 
    public PlayerHandler(Socket s, DataInputStream dis, DataOutputStream dos, Server motherserver) { 
        this.s = s; 
        this.in = dis; 
        this.out = dos; 
        this.server=motherserver;
        
        //TODO: add playerID setting !!
    } 
    
    private void messageToPlayer(String mess) {
    	try {
    		out.writeUTF(mess); 
    	} catch(IOException i) { 
            System.out.println(i); 
        }
    }
    
    public String getPlayerID() {
		return this.playerID;
	}
    
    private void messageToServer(String mess) {
		server.messageInterpreter(mess);
	}
    
    
    public void shutDown() {
    	
        try{ 
    	 
        	this.s.close(); 
        	this.out.close(); 
            this.in.close(); 
         
        } catch(IOException i) { 
            System.out.println(i); 
        } 
        System.exit(0);
    }
  
    @Override
    public void run() { 
    	
        String line; 

        while (true) { 
            try {
            	
            	line = in.readUTF();
            	
            	if(!line.equals(null))
            		messageToServer(line);
                
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
          
    } 
} 