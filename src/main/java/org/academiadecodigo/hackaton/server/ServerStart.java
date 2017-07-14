package org.academiadecodigo.hackaton.server;

import org.academiadecodigo.hackaton.shared.Score;

import java.io.IOException;

/**
 * Created by bob on 13-07-2017.
 */
public class ServerStart {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            PersistenceHandler persistenceHandler = new PersistenceHandler(server);
            persistenceHandler.updateScore(new Score("Hello", 50));
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
