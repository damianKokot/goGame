package controllers.client.abstractclasses;

import controllers.client.interfaces.GameMember;
import controllers.commandfacade.CommandMaker;
import controllers.server.*;
import java.net.*;
import java.io.*;

public abstract class Client extends Thread implements GameMember {
    // initialize socket and input output streams 
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    protected CommandMaker commander;
    private boolean started= false;


    public void connectToServer() {

        String address = "127.0.0.1";
        int port = 5000;

        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

            // sends output to the socket 
            output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException u) {
            System.out.println(u);
        }
        
        if(!started)
         start();
        
        started=true;
    }

    protected void messageToServer(String mess) {
        try {
            output.writeUTF(mess);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {

        String line;

        while (true) {
            try {
                line = input.readUTF();
                System.out.println(line);

                if (!line.equals(null))
                    commandInterpreter(line);


                Thread.yield();
                Thread.sleep(1);
            } catch (java.lang.NullPointerException | IOException i) {
                commandInterpreter(commander.serverDisconection().toString());
                stop();
            } catch (InterruptedException i) {
                System.out.println(i);
            } 
        }


    }


}