package org.academiadecodigo.hackaton.client.controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.academiadecodigo.hackaton.client.Navigation;
import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.client.service.Service;
import org.academiadecodigo.hackaton.client.service.ServiceLocator;
import org.academiadecodigo.hackaton.client.service.game.GameService;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;
import org.academiadecodigo.hackaton.client.utils.Counter;
import org.academiadecodigo.hackaton.sound.Sound;
import sun.security.krb5.SCDynamicStoreConfig;

import java.net.URL;
import java.util.*;

/**
 * Created by codecadet on 13/07/17.
 */
public class ControllerGame1 extends Controller implements Initializable {

    private GameService gameService;

    @FXML
    private Pane bgPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Text timeText;

    @FXML
    private Text coinsValue;

    @FXML
    private ProgressBar progressBar;

    private int coins;
    private boolean levelRun;

    //Player list
    private Map<Integer, Node> players = new HashMap<>();
    private Image playerImage;
    private Image coinImage;
    private ImageView playerView;
    private ImageView coinView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        levelRun = true;
        progressBar.setProgress(1);

        gameService = ServiceLocator.getInstance().get(GameService.class);
        gameService.setController(this);

        new Thread(new ProgressBarChanger()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Message message = (Message) Session.getInstance().read();

                gameService.processMsg(message.getType(), (String) message.getContent());
                System.out.println("---- " + message.getType() + " " + message.getContent());

            }
        }).start();


        addPlayer(3,1);
        addPlayer(6,2);

        bgPane.requestFocus();
        progressBar.requestFocus();

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Counter(60, timer, timeText), 0, 1000);
        new Thread(new CheckForTimeOut(timer)).start();

        Media media = new Media(getClass().getResource("/sounds/callme.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.4);
        mediaPlayer.play();

    }

    private void addPlayer(int col, int player) {
        int row = 6;
        playerImage = new Image("/images/profile1.png");
        playerView = new ImageView();
        playerView.setImage(playerImage);
        playerView.setFitWidth(50);
        playerView.setPreserveRatio(true);

        players.put(player, playerView);

        gridPane.add(playerView, col, row);
    }

    public void coinAnimation(double value, int player) {

        if (coinImage != null) {
            gridPane.getChildren().removeAll(coinView);
        }

        coinImage = new Image("/images/coin.png");
        coinView = new ImageView();
        coinView.setImage(coinImage);
        coinView.setFitWidth(20);
        coinView.setRotate(-90);
        coinView.setPreserveRatio(true);
        int playerRow = GridPane.getRowIndex(players.get(player));
        int playerColumn = GridPane.getColumnIndex(players.get(player));
        gridPane.add(coinView, playerColumn, playerRow);
        double finalValue = value * 500;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), coinView);
        translateTransition.setByY(coinView.getTranslateX() - finalValue);
        translateTransition.play();

    }

    @FXML
    public void onKeyPressed(KeyEvent event) {

        switch (event.getCode()) {

            case SPACE:
                Sound sound=new Sound();
                coinAnimation(progressBar.getProgress(), 1);
                System.out.println(progressBar.getProgress());
                if (progressBar.getProgress() >= 0.7 && progressBar.getProgress() <= 0.9) {
                    coins++;
                    sound.startSound("/sounds/coin_water.mp3");
                } else{
                    sound.startSound("/sounds/coin_floor.mp3");
                }
                coinsValue.setText(String.valueOf(coins));

                Session.getInstance().write(new Message<String>(Type.COMUNICATION_LVL1, String.valueOf(progressBar.getProgress())));


        }
    }

    private class ProgressBarChanger implements Runnable {

        @Override
        public void run() {

            boolean upDown = false;

            while (levelRun) {

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (progressBar.getProgress() >= 1) {
                    upDown = true;
                }
                if (progressBar.getProgress() <= 0.01) {
                    upDown = false;
                }

                double padding = 0.01;

                if (upDown) {
                    padding = -padding;
                }

                final double finalPadding = padding;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(progressBar.getProgress() + finalPadding);
                        bgPane.requestFocus();
                    }
                });

            }
        }

    }

    private class CheckForTimeOut implements Runnable {
        private final Timer timer;

        public CheckForTimeOut(Timer timer) {
            this.timer = timer;
        }

        @Override
        public void run() {
            synchronized (timer) {

                while (!timeText.getText().equals("0")) {
                    try {
                        timer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                levelRun = false;
                gameService.addPoints(coins);

                /*Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Navigation.getInstance().loadScreen("menu");
                    }
                });*/

            }
        }
    }

}
