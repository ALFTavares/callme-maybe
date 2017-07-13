package org.academiadecodigo.hackaton.client;

import org.academiadecodigo.hackaton.shared.Message;

import java.io.*;
import java.net.Socket;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Session {

    private static Session instance;

    private String username;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Session() {
        connect();
    }

    private void connect() {

        try {

            socket = new Socket("localhost", 1234);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(Message message) {
        try {
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message read() {

        Message message = null;

        try {

            message = (Message) in.readObject();

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
}
