package org.academiadecodigo.hackaton.server;

import org.academiadecodigo.hackaton.shared.Score;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by bob on 13-07-2017.
 */
public class ServerStart {
    public static void main(String[] args) {
        Server server = new Server();

        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
