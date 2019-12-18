package controllers.server;

import controllers.server.*;
import controllers.client.abstractclasses.*;
import controllers.exception.ConnectionErr;

import java.net.*;
import java.io.*;


public class PlayerHandler extends Thread {

    final DataInputStream inputstream;
    final DataOutputStream outputstream;
    final Socket socket;
    private int playerID;
    private String message = null;

    // Constructor 
    public PlayerHandler(Socket socket, DataInputStream dis, DataOutputStream dos) {
        this.socket = socket;
        this.inputstream = dis;
        this.outputstream = dos;
    }

    public void messageToClient(String mess) {
        try {
            outputstream.writeUTF(mess);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public String waitForMove() throws ConnectionErr {

        try {
            String line;

            while (true) {
                line = inputstream.readUTF();

                if (!line.equals(null))
                    return line;

                Thread.yield();
                Thread.sleep(1);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return "DISCONNECTED";
        }

        throw new ConnectionErr();
    }

    public int getID() {
        return this.playerID;
    }

    public void setID(int id) {
        this.playerID = id;
    }

    public void shutDown() {

        try {

            this.socket.close();
            this.outputstream.close();
            this.inputstream.close();

        } catch (IOException i) {
            System.out.println(i);
        }
        System.exit(0);
    }

}