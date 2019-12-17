package controllers.client.abstractclasses;

import controllers.client.interfaces.GameMember;
import controllers.server.*;
import java.net.*; 
import java.io.*; 

public abstract class Client extends Thread implements GameMember { 
	// initialize socket and input output streams 
	protected Socket socket = null; 
	protected DataInputStream input = null; 
	protected DataOutputStream output= null; 
	
	// constructor to put ip address and port 
	public Client(String address, int port) 
	{ 
		// establish a connection 
		try
		{ 
			socket = new Socket(address, port); 
			System.out.println("Connected"); 

			input = new DataInputStream( 
	                new BufferedInputStream(socket.getInputStream())); 

			// sends output to the socket 
			output = new DataOutputStream(socket.getOutputStream());
			
		} catch(IOException u) { 
			System.out.println(u); 
		}
	 
	} 
	
	protected void messageToServer(String mess) {
		try {
			output.writeUTF(mess);
		}catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
		
	}
	
	protected void shutDown() {
		// close the connection 
		try
		{ 
			input.close(); 
			output.close(); 
			socket.close(); 
		} catch(IOException i) { 
			System.out.println(i); 
		} 		
		System.exit(0);
	}
	
	@Override
	public void run () {

		String line;
		
		while (true) 
		{ 
			try
			{ 
			   line = input.readUTF(); 
               System.out.println(line);
               
               if(!line.equals(null))
            	   commandInterpreter(line);
               
               
               Thread.yield();
               Thread.sleep(1);
			} 
			catch(IOException i) 
			{ 
				System.out.println(i); 
			} catch(InterruptedException i) { 
            	System.out.println(i); 
            }
		} 

		
	}
	
	
} 
