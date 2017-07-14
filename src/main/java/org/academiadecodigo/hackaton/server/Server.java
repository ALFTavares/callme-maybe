package org.academiadecodigo.hackaton.server;

import javafx.scene.shape.VLineTo;
import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Score;
import org.academiadecodigo.hackaton.shared.Type;
import org.academiadecodigo.hackaton.shared.Values;

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
    private Map<Socket, ObjectOutputStream> objectOutputStreamMap;

    public Server() {
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        persistenceHandler = new PersistenceHandler(this);
        socketMap = new HashMap<>();
        objectOutputStreamMap = new HashMap<>();
    }

    public void start() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        int players = 0;

        while (players < 2) {

            System.out.println("waiting for players");

            Socket socket = serverSocket.accept();
            objectOutputStreamMap.put(socket, new ObjectOutputStream(socket.getOutputStream()));
            executorService.submit(new ClientHandler(this, socket));
            players++;
        }
        System.out.println("have all the players i need!");
    }

    public void addToMap(String name, Socket socket) {
        socketMap.put(name, socket);
    }

    public void removeFromMap(String s){
        socketMap.remove(s);
    }

    public void sendToAll(Socket socket, Message message) {
        for (Socket s : socketMap.values()) {
            if (s.equals(socket)) {
                continue;
            }
            sendTo(s, message);
        }
    }

    public void sendTo(Socket player, Message message) {
        try {
            ObjectOutputStream out = objectOutputStreamMap.get(player);
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkName(String msg) {
        return socketMap.containsKey(msg);
    }

    public List<Score> persistenceList() {
        return persistenceHandler.getHighScores();
    }

    public int updateScore(String username, int score){
        return persistenceHandler.updateScore(new Score(username,score));}

    public int numPlayers() {
        return socketMap.size();
    }
}
