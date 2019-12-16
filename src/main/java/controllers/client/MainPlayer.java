package controllers.client;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controllers.client.interfaces.GameMember;
import controllers.client.player.Player;
import controllers.client.view.PlayerGUI;

public class MainPlayer extends Thread{
	
	private Player player;
	private PlayerGUI GUI; 
	private boolean status= false;
	

	public MainPlayer() {
		GUI=new PlayerGUI();   
	}
	

	public void connectToServer() {
		player = new Player("127.0.0.1", 5000);
		player.start();
	    GUI.serverStatus("Server status: CONNECTED");
	    status=true;
	}
	
	
	
    private void commandInterpreter(String command) {
		
		switch(command) {
		
		  case "1":
			  GUI.setMessage("You can't move like this! Repeat your move!");
			  GUI.makeMove();
		    break;
		    
		  case "2":
			  GUI.settingPlane();
			break;
			
		  case "3":
			  GUI.setMessage("Waiting for opponents move...");
			break;
			  
		  case "4":
			  GUI.setMessage("You won! c:");
			  stop();
			break;
			
		  case "5":
			  GUI.setMessage("You lost! :c");
			  stop();
			break;
			
		  case "6":
			  if(!GUI.serverComm.getText().equals("You can't move like this! Repeat your move!"))
				  GUI.setMessage("Your move!");	  
			  GUI.makeMove();
		    break;
		    
		  case "7":
			  GUI.setMessage("It is a tie!");
			  stop();
			 break;
			 
		  case "8":
			  GUI.setMessage("Opponent has disconnected!");
			  stop();
			 break;
				
		}
		
		
			
		
	}
    
    private void messInterpreter(String mess) {
		
		switch(mess) {
			 
		  case "move":
			  player.doMove(GUI.lastX , GUI.lastY);		  
		    break;
		  
		  case "skip":
			  player.skipRound();		  
		    break;
		    
		  case "pass":
			  player.endGame();		  
		    break;
		    
		  case "setplane":
			  player.setPlane(GUI.planeSize);
		    break;
		    
		  case "connect":
			  connectToServer();	  
		    break;
				
		}
			
		
	}
    
	
	@Override
	public void run() {
		
		String line="";
		boolean drawing=true;
		
		while(true) {
			
			if(status) {
				line=player.dispMessage();
			
				if(!line.equals(""))
         	       commandInterpreter(line);
			}
			
			if(line.equals("0")) {
				if(drawing)
			      GUI.drawPlane(player.getPlane());
			    
	     	    GUI.planeUpdate(player.getPlane());
	     	    drawing=false;
			}
			
            line=GUI.dispMessage();
			
			if(!line.equals(""))
         	   messInterpreter(line);
			
		    try {
		    	Thread.yield(); 
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
		}
		
	}
	
	public static void main(String args[]) {       
		 MainPlayer GoGamePlayer = new MainPlayer();
		 GoGamePlayer.start();
	}

     
}
