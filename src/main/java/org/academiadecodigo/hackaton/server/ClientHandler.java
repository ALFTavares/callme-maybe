package org.academiadecodigo.hackaton.server;


import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Score;
import org.academiadecodigo.hackaton.shared.Type;
import org.academiadecodigo.hackaton.shared.Values;
import sun.awt.SunHints;

import java.io.*;
import java.net.Socket;
import java.util.List;

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
            Message<String> message;

            while (true) {
                try {
                    message = (Message) in.readObject();

                    System.out.println(message.getType());
                    System.out.println(message.getContent());

                    processMsg(message.getType(), message.getContent());

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //process the msg received and send back msg to client
    private void processMsg(Type type, String msg) {

        switch (type) {
            case LOGIN:

                if (server.checkName(msg)) {
                    writeMessage(new Message<String>(Type.LOGIN, Values.UNSUCCESS));
                    return;
                }

                writeMessage(new Message<String>(Type.LOGIN, Values.SUCCESS));
                server.addToMap(msg, socket);
                break;

            case SCORELIST:
                writeMessage(new Message<List<Score>>(Type.SCORELIST, server.persistenceList()));
                break;
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
