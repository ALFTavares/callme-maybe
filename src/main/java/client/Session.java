package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    private PrintWriter out;
    private BufferedReader in;

    private Session() {
        connect();
    }

    private void connect() {

        try {

            socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(String message) {
        out.println(message);
    }

    public String read() {

        String message;

        try {

            message = in.readLine();

        } catch (IOException e) {
            message = null;
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
