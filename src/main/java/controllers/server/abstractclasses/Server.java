package controllers.server.abstractclasses;

import java.net.*;
import java.io.*;
import java.net.ServerSocket;

import controllers.client.player.*;
import controllers.exception.*;
import controllers.server.PlayerHandler;
import controllers.server.gameroom.*;
import controllers.server.interfaces.*;

import java.util.*;


public abstract class Server extends Thread{ 
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
        DataOutputStream dos;
        DataInputStream dis;
    	
        while (true){ 
            try{ 
                userSocket=connectUser();
                dis = new DataInputStream(userSocket.getInputStream()); 
                dos = new DataOutputStream(userSocket.getOutputStream()); 
                PlayerHandler p1 = new PlayerHandler(userSocket, dis, dos); 
                
                userSocket=connectUser();
                dis = new DataInputStream(userSocket.getInputStream()); 
                dos = new DataOutputStream(userSocket.getOutputStream()); 
                PlayerHandler p2 = new PlayerHandler(userSocket, dis, dos); 
  
                GameRoom game = new GameRoom(p1,p2);
                game.start();
                
                Thread.yield();
                Thread.sleep(1);

            } catch(IOException i) { 
                System.out.println(i); 
            } catch(InterruptedException i) { 
            	System.out.println(i); 
            } catch(UserConnectionErr i) { 
            	System.out.println(i); 
            }
        } 

    } 
    
} 