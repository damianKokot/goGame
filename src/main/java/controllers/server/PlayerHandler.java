package controllers.server;

import controllers.server.*;
import controllers.server.interfaces.*;
import controllers.client.abstractclasses.*;
import controllers.exception.ConnectionErr;

import java.net.*; 
import java.io.*; 


public class PlayerHandler extends Thread implements GameUtil{ 

    final DataInputStream in; 
    final DataOutputStream out; 
    final Socket s; 
    private int playerID;
    private String message=null;
      
    // Constructor 
    public PlayerHandler(Socket s, DataInputStream dis, DataOutputStream dos) { 
        this.s = s; 
        this.in = dis; 
        this.out = dos;          
    } 
    
    public void messageToClient(String mess) {
    	try {
    		out.writeUTF(mess); 
    	} catch(IOException i) { 
            System.out.println(i); 
        }
    }
    
    public String waitForMove() throws ConnectionErr {
    	
    	try {
    	    String line;
    		 
    		while(true) {
    		  line= in.readUTF();
    		  
    		  if(!line.equals(null))
    			  return line;
    			  
    		  Thread.yield();
              Thread.sleep(1);
    		}

    	} catch (IOException e) { 
            e.printStackTrace(); 
        } catch (InterruptedException i) { 
            i.printStackTrace(); 
        }
    	
    	throw new ConnectionErr();
    }
    
    public int getID() {
		return this.playerID;
	}
    
    public void setID(int id) {
		this.playerID=id;
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
  
} 