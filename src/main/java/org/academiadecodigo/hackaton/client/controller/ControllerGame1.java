package org.academiadecodigo.hackaton.client.controller;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.academiadecodigo.hackaton.client.Navigation;
import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.client.service.ServiceLocator;
import org.academiadecodigo.hackaton.client.service.game.GameService;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;
import org.academiadecodigo.hackaton.client.utils.Counter;
import org.academiadecodigo.hackaton.sound.Sound;

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

    @FXML
    private VBox VBox_spaceBar;

    private int coins = 0;
    private boolean levelRun;
    private boolean nextClick;

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

        coinsValue.setText(String.valueOf(coins));

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    Message message = (Message) Session.getInstance().read();
                    gameService.processMsg(message.getType(), (String) message.getContent());
                }

            }
        }).start();


        addPlayer(3, 1, "/images/player1.png");
        addPlayer(6, 2, "/images/player2.png");

        bgPane.requestFocus();
        progressBar.requestFocus();

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Counter(10, timer, timeText), 0, 1000);
        timer.scheduleAtFixedRate(new ProgressBarChanger(), 0, 5);
        new Thread(new CheckForTimeOut(timer)).start();

        Sound.getInstance().startSong();

    }

    private void addPlayer(int col, int player, String imageUrl) {
        int row = 6;
        playerImage = new Image(imageUrl);
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
                coinAnimation(progressBar.getProgress(), 1);

                if (progressBar.getProgress() >= 0.7 && progressBar.getProgress() <= 0.8) {
                    showMessage("hit", 1);
                    coins++;
                    Sound.getInstance().startSound("/sounds/coin_water.mp3");
                } else {
                    Sound.getInstance().startSound("/sounds/coin_floor.mp3");
                }
                coinsValue.setText(String.valueOf(coins));
                VBox_spaceBar.setVisible(false);


                Session.getInstance().write(new Message<String>(Type.COMUNICATION_LVL1, String.valueOf(progressBar.getProgress())));


        }
    }

    private void showMessage(String message, int player) {
        BorderPane messagePane = new BorderPane(new Text(message));
        messagePane.setPadding(new Insets(5));
        messagePane.setId("ModalMessage");
        messagePane.setMaxHeight(20);
        gridPane.add(messagePane, GridPane.getColumnIndex(players.get(player)), GridPane.getRowIndex(players.get(player)));
        messagePane.setStyle("-fx-background-color: lawngreen;-fx-opacity: 0.8");
        messagePane.setTranslateY(messagePane.getLayoutY() + 100);
        messagePane.setTranslateX(messagePane.getLayoutX() + 50);
        PauseTransition animationPause = new PauseTransition(Duration.millis(500));
        PauseTransition delay = new PauseTransition(Duration.millis(1000));
        TranslateTransition appearAnimation = new TranslateTransition(Duration.millis(300), messagePane);
        appearAnimation.setByY(-130);
        TranslateTransition dissapearAnimation = new TranslateTransition(Duration.millis(300), messagePane);
        dissapearAnimation.setByY(100);
        SequentialTransition seqTransition = new SequentialTransition(delay, appearAnimation, animationPause, dissapearAnimation);
        seqTransition.play();
    }

    private class ProgressBarChanger extends TimerTask {

        boolean upDown = false;

        @Override
        public void run() {

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

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Navigation.getInstance().loadScreen("finalView");
                    }
                });
                // TODO next view

            }
        }
    }


}
