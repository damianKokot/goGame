package controllers.server;

import java.net.*;
import java.io.*;
import java.net.ServerSocket;

import controllers.client.player.*;
import controllers.exception.*;

import java.util.*;


public class Server extends Thread{ 
    //initialize socket and input stream 
    private ServerSocket server   = null; 
  
    // constructor with port 
    public Server(int port) { 
    	
        try{ 
            server = new ServerSocket(port); 
            System.out.println("Server started");        
        } catch(IOException i) { 
            System.out.println(i); 
        } 
    } 
    
    private Socket connectUser() throws UserConnectionErr{
    	try{ 
    		Socket Socket= server.accept(); 
    		System.out.println("A new client is connected " ); 
    		return Socket; 
    	} catch(IOException i) { 
            System.out.println(i); 
        }
    	
    	throw new UserConnectionErr();
    }
    
    @Override
    public void run () {
    	
        Socket userSocket = null; 
        DataOutputStream outputStream;
        DataInputStream inputStream;
    	
        while (true){ 
            try{ 
                userSocket=connectUser();
                inputStream = new DataInputStream(userSocket.getInputStream()); 
                outputStream = new DataOutputStream(userSocket.getOutputStream()); 
                PlayerHandler p1 = new PlayerHandler(userSocket, inputStream, outputStream); 
                
                userSocket=connectUser();
                inputStream = new DataInputStream(userSocket.getInputStream()); 
                outputStream = new DataOutputStream(userSocket.getOutputStream()); 
                PlayerHandler p2 = new PlayerHandler(userSocket, inputStream, outputStream); 
  
                GameRoom game = new GameRoom(p1,p2);
                game.start();
                
                Thread.yield();
                Thread.sleep(1);

            } catch(UserConnectionErr | InterruptedException | IOException i) { 
                System.out.println(i); 
            }
        } 

    } 
    
} 