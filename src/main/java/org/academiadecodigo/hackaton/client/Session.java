package org.academiadecodigo.hackaton.client;

import org.academiadecodigo.hackaton.client.controller.ControllerGame1;
import org.academiadecodigo.hackaton.client.service.game.GameService;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Session {

    private static Session instance;
    private String ip = "localhost";
    private String username;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Session() {
        connect();
    }

    private void connect() {

        try {

            socket = new Socket(ip, 9999);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object read() {

        Object message = null;

        try {

            message = in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return message;

    }

    public void close() {

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Session getInstance() {

        if (instance == null) {

            synchronized (Session.class) {

                if (instance == null) {
                    instance = new Session();
                }

            }

        }

        return instance;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void reset() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance = null;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }
}
