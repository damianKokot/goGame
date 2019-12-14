package controllers.client.abstractclasses;

import controllers.server.*;
import java.net.*; 
import java.io.*; 

public abstract class Client extends Thread { 
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
	
	public abstract void  gameUpdate(String mess);
	
	@Override
	public void run () {

		String line;
		
		while (true) 
		{ 
			try
			{ 
			   line = in.readUTF(); 
               System.out.println(line);
               
               if(!line.equals(null))
            	   gameUpdate(line);
               
               
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
