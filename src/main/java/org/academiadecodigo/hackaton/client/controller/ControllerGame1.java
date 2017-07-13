package org.academiadecodigo.hackaton.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 13/07/17.
 */
public class ControllerGame1 extends Controller implements Initializable {

    @FXML
    private Pane bgPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Text timeText;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text coinsValue;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setProgress(1);

       Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {

                boolean upDown = false;

                while (true){

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (progressBar.getProgress() >= 1){
                        upDown = true;
                    }
                    if (progressBar.getProgress() <= 0.01){
                        upDown = false;
                    }

                    if (!upDown){
                        progressBar.setProgress(progressBar.getProgress() + 0.01);
                    }
                    if (upDown){
                        progressBar.setProgress(progressBar.getProgress() - 0.01);
                    }


                }
            }
        });

       thread.start();

    }



}
