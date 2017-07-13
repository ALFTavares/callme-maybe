package org.academiadecodigo.hackaton.client;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class ClientStart extends Application {

    public static void main(String[] args) {

    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        Session.getInstance().close();
        Navigation.getInstance().close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Navigation.getInstance().setStage(primaryStage);

    }

}
