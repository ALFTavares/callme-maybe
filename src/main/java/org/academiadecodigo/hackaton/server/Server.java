package org.academiadecodigo.hackaton.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        socketMap = new HashMap<>();
    }

    public void start() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new ClientHandler(this, socket));
        }
    }

    public void addToMap(String name, Socket socket) {
        socketMap.put(name, socket);
    }

    public void sendToAll() {

    }

    public void sendTo() {

    }

    public void getMessageForPersistence(String message) {
        //TODO change the method name in persistenceHandler after creation
        persistenceHandler.sendForQuery(message);
    }
}
