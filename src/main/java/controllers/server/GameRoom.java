package controllers.server;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.commandfacade.CommandMaker;
import controllers.exception.*;
import controllers.server.*;
import models.GoGame;
import models.exceptions.*;
import models.factories.ConcreteFactory.*;
import models.interfaces.*;
import models.*;

public class GameRoom extends Thread {

    private PlayerHandler player1;
    private PlayerHandler player2;
    private GoGame game;
    private boolean isGameOn = false;
    private boolean turn = true;
    private int skips = 0;
    private CommandMaker commander;


    public GameRoom(PlayerHandler player1, PlayerHandler player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setID(1);
        player2.setID(2);
        commander = new CommandMaker();
    }

    private void createGame() {
        int plane = getPlaneSize();
        IPanel panel = null;

        switch (plane) {
            case 9:
                panel = new PanelSmall();
                break;
            case 13:
                panel = new PanelNormal();
                break;
            case 19:
                panel = new PanelLarge();
                break;

            default:
                panel = new PanelLarge();
                break;
        }

        try {
            game = new GoGame(player1.getID(), panel);
            game.setOpponent(player2.getID());

        } catch (UserExistsException i) {
            System.out.println(i);
        }
    }

    private int getPlaneSize() {
        player1.messageToClient(commander.getPlane().toString());
        player2.messageToClient(commander.waitForTurn().toString());

        String planesize = null;

        try {
            planesize = player1.waitForMove();
        } catch (ConnectionErr e) {
            System.out.println(e);
        }

        JsonParser jsonParser = new JsonParser();
        JsonObject command = jsonParser.parse(planesize).getAsJsonObject();
        int size = command.get("x").getAsInt();

        return size;
    }

    private void updateGame() {
        JsonObject command = commander.gameUpdate(game.getGameStatus());
        player1.messageToClient(command.toString());
        player2.messageToClient(command.toString());
    }

    private void makeMove(int id) {

        String move;
        JsonParser jsonParser = new JsonParser();

        try {
            if (id == 1) {
                player1.messageToClient(commander.yourTurn().toString());
                player2.messageToClient(commander.waitForTurn().toString());
                move = player1.waitForMove();
            } else {
                player2.messageToClient(commander.yourTurn().toString());
                player1.messageToClient(commander.waitForTurn().toString());
                move = player2.waitForMove();
            }

            if (move != "DISCONNECTED")
                commandInterpreter(id, jsonParser.parse(move).getAsJsonObject());
            else {
                player2.messageToClient(commander.playerDisconection().toString());
                player1.messageToClient(commander.playerDisconection().toString());
                stop();
            }


        } catch (ConnectionErr e) {
            System.out.println(e);
        } catch (NoMoveEx e) {
            repeatMove(id);
        }
    }

    private void commandInterpreter(int id, JsonObject command) throws NoMoveEx {

        if (command.get("command").getAsString().equals("move")) {
            try {
                game.push(id, command.get("x").getAsInt(), command.get("y").getAsInt());
                skips = 0;
            } catch (PushException ex) {
                repeatMove(id);
            }

        } else if (command.get("command").getAsString().equals("skip")) {

            skips++;

            if (skips == 2)
                gameSumUp(0);

        } else if (command.get("command").getAsString().equals("pass")) {
            if (id == 1)
                gameSumUp(2);
            else
                gameSumUp(1);
        } else {
            throw new NoMoveEx();
        }
    }

    private void repeatMove(int id) {

        turn = !turn;

        if (id == 1)
            player1.messageToClient(commander.repeatMove().toString());
        else
            player2.messageToClient(commander.repeatMove().toString());
    }

    private void gameSumUp(int id) {

        int looserId;
        int scores[] = game.getScores();

        if (id == 1) {
            looserId = 2;
        } else if (id == 2) {
            looserId = 1;
        } else {
            if (scores[0] > scores[1])
                looserId = 2;
            else if (scores[0] < scores[1])
                looserId = 1;
            else
                looserId = 0;
        }

        if (looserId==2) {
            player1.messageToClient(commander.win(scores[0], scores[1]).toString());
            player2.messageToClient(commander.loose(scores[0], scores[1]).toString());
        } else if (looserId == 1) {
            player1.messageToClient(commander.loose(scores[0], scores[1]).toString());
            player2.messageToClient(commander.win(scores[0], scores[1]).toString());
        } else {
            player1.messageToClient(commander.tie().toString());
            player2.messageToClient(commander.tie().toString());
        }
        
        stop();
    }

    @Override
    public void run() {

        createGame();
        isGameOn = true;
        updateGame();

        while (isGameOn) {

            try {

                if (turn)
                    makeMove(player1.getID());
                else
                    makeMove(player2.getID());

                turn = !turn;
                updateGame();

                Thread.yield();
                Thread.sleep(1);
            } catch (InterruptedException i) {
                System.out.println(i);
            }
        }

    }

}