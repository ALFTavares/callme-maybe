package client;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by bob on 13-07-2017.
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
