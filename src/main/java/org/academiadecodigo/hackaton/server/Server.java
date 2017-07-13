package org.academiadecodigo.hackaton.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bob on 13-07-2017.
 */
public class Server {
    private ClientHandler clientHandler;
    private PersistenceHandler persistenceHandler;
    private ServerSocket serverSocket;
    private Socket socket;

    public Server() {
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientHandler = new ClientHandler(this);
        persistenceHandler = new PersistenceHandler(this);
    }

    public void start() throws IOException {
        while (true) {
            socket = serverSocket.accept();
            new Thread(new ClientHandler(this));
        }
    }

    public void sendToAll() {

    }

    public void sendTo() {

    }
}
