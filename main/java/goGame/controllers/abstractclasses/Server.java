package goGame.controllers.abstractclasses;

import java.net.*;
import java.io.*;
import java.net.ServerSocket;

import goGame.controllers.abstractclasses.*;
import goGame.controllers.interfaces.*;
import goGame.controllers.server.PlayerHandler;
import java.util.*;
import goGame.controllers.player.*;


public abstract class Server extends Thread{ 
    //initialize socket and input stream 
    private ServerSocket server   = null; 
    protected ArrayList<GameUtil> playerList = new ArrayList<GameUtil>();
    protected ArrayList<GameUtil> gameList = new ArrayList<GameUtil>();
  
    // constructor with port 
    public Server(int port) { 
    	
        try{ 
            server = new ServerSocket(port); 
            System.out.println("Server started");        
        } catch(IOException i) { 
            System.out.println(i); 
        } 
    } 
    
    public abstract void messageToGame(String mess, String ID);
    
    @Override
    public void run () {
    	
        Socket userSocket = null; 
    	
        while (true){ 
            try{ 
            	Thread.yield();
            	userSocket = server.accept(); 
                
                System.out.println("A new client is connected " ); 
                  
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(userSocket.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(userSocket.getOutputStream()); 
                // create a new thread object 
                PlayerHandler t = new PlayerHandler(userSocket, dis, dos, this); 
  
                // Invoking the start() method 
                playerList.add(t);
                t.start(); 
                
                Thread.yield();
                Thread.sleep(1);

            } catch(IOException i) { 
                System.out.println(i); 
            } catch(InterruptedException i) { 
            	System.out.println(i); 
            }
        } 

    } 
    
} 