package org.academiadecodigo.hackaton.client;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.academiadecodigo.hackaton.client.service.ServiceLocator;
import org.academiadecodigo.hackaton.client.service.game.GameService;
import org.academiadecodigo.hackaton.client.service.game.GameService1;
import org.academiadecodigo.hackaton.client.service.login.LoginServiceImpl;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class ClientStart extends Application {

    private static AudioClip audioClip = new AudioClip(ClientStart.class.getResource("/sounds/callme.mp3").toString());

    public static void main(String[] args) {
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
        audioClip.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("menu");
        audioClip.setCycleCount(10);
        audioClip.play(0.4);

    }

}
