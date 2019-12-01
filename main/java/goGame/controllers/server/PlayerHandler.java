package goGame.controllers.server;

import goGame.controllers.server.*;
import goGame.controllers.interfaces.*;
import goGame.controllers.abstractclasses.*;
import java.net.*; 
import java.io.*; 


public class PlayerHandler extends Thread implements GameUtil{ 

    final DataInputStream in; 
    final DataOutputStream out; 
    final Socket s; 
    static Server server;
    private String playerID;
      
    // Constructor 
    public PlayerHandler(Socket s, DataInputStream dis, DataOutputStream dos, Server motherserver) { 
        this.s = s; 
        this.in = dis; 
        this.out = dos; 
        this.server=motherserver;
        
        //TODO: add playerID setting !!
    } 
    
    public void messageToClient(String mess) {
    	try {
    		out.writeUTF(mess); 
    	} catch(IOException i) { 
            System.out.println(i); 
        }
    }
    
    public String getID() {
		return this.playerID;
	}
    
    public void messageToServer(String mess) {
		server.messageToGame(mess, this.playerID);
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
            	Thread.yield();
                Thread.sleep(1);
            } catch (IOException e) { 
                e.printStackTrace(); 
            } catch(InterruptedException i) { 
            	System.out.println(i); 
            }
        } 
          
    } 
} 