package org.academiadecodigo.hackaton.server;



import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;

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

        System.out.println("aqui");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            try {
                Message message = (Message) in.readObject();

                processMsg(message.getType(), message.getContent());

                System.out.println(bufferedReader.readLine());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //process the msg received
    private void processMsg(Type type, String msg) {

       switch (type){
           case LOGIN:
               server.addToMap(msg, socket);

       }

        //TODO rest of the process message

    }
}
