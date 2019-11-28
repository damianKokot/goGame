package goGame.controllers.server;

import goGame.controllers.client.*;

import java.net.*;
import java.io.*;
import java.net.ServerSocket;

  
public class Server 
{ 
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in= null, ConsoleInput =  null; 
    private DataOutputStream out	 = null; 
  
    // constructor with port 
    public Server(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
            out = new DataOutputStream(socket.getOutputStream()); 
            
            ConsoleInput = new DataInputStream(System.in); 

  
            String line = ""; 
            String terminalLine="";
  
            // reads message from client until "Over" is sent, prints "ok" to client after every message
            while (!line.equals("Over")) 
            { 
                try
                { 
                	terminalLine = ConsoleInput.readLine(); 
                    line = in.readUTF(); 
                    System.out.println(line); 
                    out.writeUTF(terminalLine); 
  
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
} 