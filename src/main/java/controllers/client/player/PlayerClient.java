package controllers.client.player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.client.abstractclasses.Client;
import controllers.client.interfaces.GameMember;
import controllers.client.interfaces.GoGui;
import controllers.commandfacade.CommandMaker;

public abstract class PlayerClient extends Client implements GoGui {

    protected int[][] plane;
    private int Move = 0;
    protected boolean myTurn, repeat=false;
    private String lastCommand;

    public PlayerClient() {
        commander = new CommandMaker();
    }

    @Override
    public void doMove(int x, int y) {
    	if(myTurn) 
    		messageToServer(commander.makeMove(x, y).toString());
    }

    @Override
    public void skipRound() {
    	if(myTurn)
    		messageToServer(commander.skipRound().toString());
    }

    @Override
    public void endGame() {
    	if(myTurn)
    		messageToServer(commander.pass().toString());
    }

    @Override
    public void commandInterpreter(String mess) {

        JsonParser jsonParser = new JsonParser();
        JsonObject command = jsonParser.parse(mess).getAsJsonObject();
        serverStatus(true);

        String commandStr = command.get("command").getAsString();
        
        switch (commandStr) {

            case "gameupdate":
                gameUpdate(command);
                if (Move == 1)
                    nextStage(plane.length);
                break;

            case "repeatmove":
                setMessage("You can't move like this! Repeat your move!");  
                repeat=true;
                break;

            case "chooseplane":
                setMessage("Choose board size!");
                nextStage(1);
                break;

            case "wait":
            	myTurn=false;
                setMessage("Waiting for opponents move...");
                if (Move == 0)
                    nextStage(0);
                break;

            case "winner":
                setMessage("You have won!");
                stop();
                break;

            case "looser":
                setMessage("You have lost!");
                stop();
                break;

            case "yourmove":
            	myTurn=true;
            	
                if(!repeat)
            	    setMessage("It is your move!");
            	
                if (Move == 1)
                    nextStage(plane.length);
                break;

            case "tie":
                setMessage("It is a tie!");
                stop();
                break;

            case "playerdisconnected":
                setMessage("Opponent has disconnected!");
                break;

            case "serverdied":
                setMessage("Server connection error!");
                serverStatus(false);
                break;

        }
        Move++;
    }

    protected void gameUpdate(JsonObject command) {
        Gson gson = new Gson();
        int[][] arr = gson.fromJson(command.get("plane").getAsString(), int[][].class);

        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr.length; j++)
                if (this.plane == null || this.plane[i][j] != arr[i][j])
                    refresh(i, j, arr[i][j]);

        this.plane = arr;
    }

    public void setPlane(int size) {
        messageToServer(commander.setPlane(size).toString());
    }

}