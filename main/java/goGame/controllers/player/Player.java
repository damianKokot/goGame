package goGame.controllers.player;

import goGame.controllers.abstractclasses.*;
import goGame.controllers.interfaces.*;
import goGame.controllers.interfaces.GameMember;

import java.io.IOException;
import java.util.Scanner;

public class Player extends Client implements GameMember {
	
	private String ID;
	private int[][] plane;
	
	public Player(String adress, int port, String id) {
		super(adress,port);
		setID(id);
		run();
	}
	
	public String getID() {
		return this.ID;
	}
	
	public void setID(String id) {
		this.ID=id;
	}
	
	@Override
	protected void messageToServer(String mess) {
		try {
			out.writeUTF(this.ID+":"+mess);
		}catch(IOException i){ 
			System.out.println(i); 
		} 
		
	}
	
	public void messageInterpreter(String mess) {
		
	}
	
	public void doMove(int x, int y) {
		messageToServer("domove:"+ Integer.toString(x)+ ":" + Integer.toString(y));
	}
	
    public void gameUpdate(int[][] plane) {
    	this.plane=plane;
	}
	
	public static void main(String args[]) {         //finally this method will be in GUI
		Scanner scan = new Scanner(System.in);
		System.out.println("Set your nickname: ");
		String nickname=scan.nextLine();
		GameMember player = new Player("127.0.0.1", 5000, nickname); 
	} 

}
