package org.academiadecodigo.hackaton.server;


import org.academiadecodigo.hackaton.client.Navigation;
import org.academiadecodigo.hackaton.client.controller.ControllerGame1;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Score;
import org.academiadecodigo.hackaton.shared.Type;
import org.academiadecodigo.hackaton.shared.Values;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by bob on 13-07-2017.
 */
public class ClientHandler implements Runnable {
    private Server server;
    private Socket socket;
    private String username;

    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    //loop waiting for instructions
    public void run() {

        try {

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Message<String> message;

            while (true) {
                try {
                    message = (Message) in.readObject();

                    //gprocessMsg(message.getType(), message.getContent());

                    if (message != null && !socket.isClosed()) {
                        processMsg(message.getType(), message.getContent());
                    } else {
                        server.removeFromMap(username);
                    }

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

                username = msg;
                writeMessage(new Message<String>(Type.LOGIN, Values.SUCCESS));
                server.addToMap(msg, socket);

                if (server.numPlayers() == 2) {
                    Message<String> message = new Message<>(Type.BEGIN, Values.BEGIN);
                    server.sendToAll(null, message);
                }

                break;

            case SCORELIST:
                writeMessage(new Message<List<Score>>(Type.SCORELIST, server.persistenceList()));
                break;

            case BEGIN:
                writeMessage(new Message<String>(Type.BEGIN, Values.BEGIN));
                break;

            case COMUNICATION_LVL1:
                server.sendToAll(socket, new Message<String>(type, msg));
        }
        //TODO rest of the process message
    }

    private void writeMessage(Message message) {
        server.sendTo(socket, message);
    }

}
