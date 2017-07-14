package org.academiadecodigo.hackaton.client;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.academiadecodigo.hackaton.client.service.ServiceLocator;
import org.academiadecodigo.hackaton.client.service.game.GameService;
import org.academiadecodigo.hackaton.client.service.game.GameService1;
import org.academiadecodigo.hackaton.client.service.login.LoginServiceImpl;
import org.academiadecodigo.hackaton.sound.Sound;

import java.util.Scanner;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class ClientStart extends Application {

    public static void main(String[] args) {
        if (args.length > 0) {
            String ip = args[0];
            Session.getInstance().setIP(ip);
        }
        launch(args);
    }

    @Override
    public void init() throws Exception {

        ServiceLocator.getInstance().add(new LoginServiceImpl());
        ServiceLocator.getInstance().add(new GameService1());

    }

    @Override
    public void stop() throws Exception {
        Session.getInstance().close();
        Navigation.getInstance().close();
        Sound.getInstance().stopSong();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("menu");

    }

}
