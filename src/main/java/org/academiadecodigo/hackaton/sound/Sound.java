package org.academiadecodigo.hackaton.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by codecadet on 14/07/17.
 */
public class Sound {

    public void startSound(String source){
        Media media = new Media(getClass().getResource(source).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
