package org.academiadecodigo.hackaton.sound;

import javafx.scene.media.AudioClip;
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

    private static Sound instance = null;

    private AudioClip audioClip;

    private Sound(){}

    public synchronized static Sound getInstance() {

        if (instance == null) {

            synchronized (Sound.class) {

                if (instance == null) {
                    instance = new Sound();
                }

            }

        }

        return instance;

    }

    public void startSong(){
        audioClip=new AudioClip(getClass().getResource("/sounds/callme.mp3").toString());
        audioClip.play(0.4);
    }

    public void stopSong(){
        if(audioClip !=null) {
            audioClip.stop();
        }
    }

    public void startSound(String source){
        Media media = new Media(getClass().getResource(source).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
