package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by bob on 13-07-2017.
 */
public class ClientHandler implements Runnable {
    private Server server;
    private Socket socket;

    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket =  socket;
    }

    public void run() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg = bufferedReader.readLine();

            processMsg(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void processMsg(String msg) {

    }
}
