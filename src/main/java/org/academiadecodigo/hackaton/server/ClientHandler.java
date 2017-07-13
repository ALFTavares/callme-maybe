package org.academiadecodigo.hackaton.server;


import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;
import org.academiadecodigo.hackaton.shared.Values;
import sun.awt.SunHints;

import java.io.*;
import java.net.Socket;

/**
 * Created by bob on 13-07-2017.
 */
public class ClientHandler implements Runnable {
    private Server server;
    private Socket socket;
    private ObjectOutputStream out;

    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //loop waiting for instructions
    public void run() {

        System.out.println("aqui");

        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                try {
                    Message message = (Message) in.readObject();

                    processMsg(message.getType(), message.getContent());

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //process the msg received
    private void processMsg(Type type, String msg) {

        switch (type) {
            case LOGIN:

                server.addToMap(msg, socket);
                if (!server.checkName(msg)) {
                    writeMessage(new Message(Type.LOGIN, Values.SUCCESS));
                }
        }
        //TODO rest of the process message

    }

    private void writeMessage(Message message) {
        try {
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
