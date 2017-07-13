package org.academiadecodigo.hackaton.server;

import java.io.IOException;

/**
 * Created by bob on 13-07-2017.
 */
public class ServerStart {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
