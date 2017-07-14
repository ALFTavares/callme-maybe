package org.academiadecodigo.hackaton.client.service.login;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.academiadecodigo.hackaton.client.Navigation;
import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;
import org.academiadecodigo.hackaton.shared.Values;
import org.academiadecodigo.hackaton.sound.Sound;

import java.io.File;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class LoginServiceImpl implements LoginService {

    @Override
    public String getName() {
        return LoginService.class.getSimpleName();
    }

    public void submitNewClient(String username) {

        Session.getInstance().write(new Message(Type.LOGIN, username));

        Message<String> message = (Message<String>) Session.getInstance().read();

        if (message.getType().equals(Type.LOGIN) && message.getContent().equals(Values.SUCCESS)) {
            Media media = new Media(getClass().getResource("/sounds/callme.mp3").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.4);
            mediaPlayer.play();
            Navigation.getInstance().loadScreen("game1");
            return;
        }

        throw new IllegalArgumentException("Existent user. Please try again.");

    }

    /*
    private void startSong() {

        //Media media = new Media("resources/sounds/callme.wav");
        //String uri = new File("/resources/sounds/callme.wav").toURI().toString();
        //(System.out.println(uri);
        Media media = new Media(getClass().getResource("/sounds/callme.mp3").toString());
        System.out.println(media);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }
    */

}
