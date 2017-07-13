package server;

import sun.plugin2.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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


    //loop waiting for instructions
    public void run() {

        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());



            processMsg();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //process the msg received
    private void processMsg(String msg) {

        if (msg.split(" ")[0].equals("username")){
            server.addToMap(msg.split(" ")[1], socket);
        }

        //TODO rest of the process message

    }
}
