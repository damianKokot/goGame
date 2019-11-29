package goGame.controllers.abstractclasses;

import java.net.*;
import java.io.*;
import java.net.ServerSocket;

import goGame.controllers.abstractclasses.*;
import goGame.controllers.server.PlayerHandler;
import java.util.*;


  
public abstract class Server { 
    //initialize socket and input stream 
    private ServerSocket server   = null; 
    protected Vector<Thread> playerList = new Vector<Thread>();
  
    // constructor with port 
    public Server(int port) { 
    	
        try{ 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 

            run();
            
        } catch(IOException i) { 
            System.out.println(i); 
        } 
    } 
    
    public void shutDown() {
	    System.exit(0);
	}
    
    public abstract void messageInterpreter(String mess);
    
    private void run () {
    	
        Socket userSocket = null; 
    	
        while (true){ 
            try{ 
            	userSocket = server.accept(); 
                
                System.out.println("A new client is connected " ); 
                  
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(userSocket.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(userSocket.getOutputStream()); 
                // create a new thread object 
                Thread t = new PlayerHandler(userSocket, dis, dos, this); 
  
                // Invoking the start() method 
                playerList.add(t);
                t.start(); 

            } catch(IOException i) { 
                System.out.println(i); 
            } 
        } 

    } 
    
} 