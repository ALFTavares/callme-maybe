package org.academiadecodigo.hackaton.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Created by bob on 13-07-2017.
 */
public class Server {
    private ClientHandler clientHandler;
    private PersistenceHandler persistenceHandler;
    private ServerSocket serverSocket;
    private Map<String, Socket> socketMap;


    public Server() {
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        persistenceHandler = new PersistenceHandler(this);
    }

    public void start() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ClientHandler(this, socket)).start();


            //TODO add to map later after we receive player name
        }
    }

    public void sendToAll() {

    }

    public void sendTo() {

    }

    public void addToMap(String string, Socket socket) {

    }
}
