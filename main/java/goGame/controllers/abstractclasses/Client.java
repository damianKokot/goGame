package goGame.controllers.abstractclasses;

import goGame.controllers.server.*;
import java.net.*; 
import java.io.*; 

public abstract class Client { 
	// initialize socket and input output streams 
	protected Socket socket = null; 
	protected DataInputStream in=null, input = null; 
	protected DataOutputStream out	 = null; 
	
	// constructor to put ip address and port 
	public Client(String address, int port) 
	{ 
		// establish a connection 
		try
		{ 
			socket = new Socket(address, port); 
			System.out.println("Connected"); 

			// takes input from terminal 
			input = new DataInputStream(System.in); 
			in = new DataInputStream( 
	                new BufferedInputStream(socket.getInputStream())); 

			// sends output to the socket 
			out = new DataOutputStream(socket.getOutputStream());
			
		} catch(UnknownHostException u) { 
			System.out.println(u); 
		} catch(IOException i) { 
			System.out.println(i); 
		} 
	} 
	
	protected void messageToServer(String mess) {
		try {
			out.writeUTF(mess);
		}catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
		
	}
	
	public abstract void messageInterpreter(String mess);
	
	protected void shutDown() {
		// close the connection 
		try
		{ 
			input.close(); 
			out.close(); 
			socket.close(); 
		} catch(IOException i) { 
			System.out.println(i); 
		} 		
		System.exit(0);
	}
	
	protected void run () {

		String line;
		
		while (true) 
		{ 
			try
			{ 
			// line = in.readUTF(); 
            // System.out.println(line);
               line = input.readLine();
               
               if(!line.contentEquals(""))
                    messageToServer(line);
			} 
			catch(IOException i) 
			{ 
				System.out.println(i); 
			} 
		} 

		
	}
	
	
} 
