package org.academiadecodigo.hackaton.server;

import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Score;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bob on 13-07-2017.
 */
public class Server {
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
        if (((socketMap.size() % 2) == 0) && socketMap.size() != 0) {
            clientHandler.launchGame();
        }
    }

    public void sendToAll(Socket socket, Message message) {
        ObjectOutputStream out;
        for (Socket s : socketMap.values()) {
            if (socket.equals(s)) {
                continue;
            }
            try {
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendTo(String playerName, Message message) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socketMap.get(playerName).getOutputStream());
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessageForPersistence(String message) {
        //TODO change the method name in persistenceHandler after creation
        //persistenceHandler.sendForQuery(message);
    }

    public Map getSocketMap() {
        return socketMap;
    }

    public boolean checkName(String msg) {
        return socketMap.containsKey(msg);
    }

    public List<Score> persistenceList() {
        return persistenceHandler.getHighScores();
    }
}
