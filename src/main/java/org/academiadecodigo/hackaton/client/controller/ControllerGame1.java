package org.academiadecodigo.hackaton.client.controller;

import javafx.animation.TranslateTransition;
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
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.client.service.Service;
import org.academiadecodigo.hackaton.client.service.ServiceLocator;
import org.academiadecodigo.hackaton.client.service.game.GameService;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;
import sun.security.krb5.SCDynamicStoreConfig;

import java.net.URL;
import java.util.*;

/**
 * Created by codecadet on 13/07/17.
 */
public class ControllerGame1 extends Controller implements Initializable {

    private int coins;

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

    //Player list
    Map<Integer, Node> players = new HashMap<>();
    Image playerImage;
    Image coinImage;
    ImageView playerView;
    ImageView coinView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setProgress(1);

        gameService = ServiceLocator.getInstance().get(GameService.class);
        gameService.setController(this);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                boolean upDown = false;

                while (true) {

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

                    if (!upDown) {
                        progressBar.setProgress(progressBar.getProgress() + 0.01);
                    }
                    if (upDown) {
                        progressBar.setProgress(progressBar.getProgress() - 0.01);
                    }

                    bgPane.requestFocus();


                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {


                Message message = Session.getInstance().read();

                gameService.processMsg(message.getType(), message.getContent());

            }
        });

        thread2.start();

        thread.start();

        addPlayer(3, 6, 1);
        addPlayer(6, 6, 2);

        bgPane.requestFocus();
        progressBar.requestFocus();
        System.out.println(progressBar.isFocused());
    }

    private void addPlayer(int col, int row, int player) {
        playerImage = new Image("/images/profile1.png");
        playerView = new ImageView();
        playerView.setImage(playerImage);
        playerView.setFitWidth(50);
        playerView.setPreserveRatio(true);

        players.put(player, playerView);

        gridPane.add(playerView, col, row);
    }

    public void coinAnimation(double value, int player) {

        coinImage = new Image("/images/coin.png");
        coinView = new ImageView();
        coinView.setImage(coinImage);
        coinView.setFitWidth(50);
        coinView.setPreserveRatio(true);
        int playerRow = gridPane.getRowIndex(players.get(player));
        int playerColumn = gridPane.getColumnIndex(players.get(player));
        gridPane.add(coinView, playerColumn, playerRow);
        double finalValue = value * 500;
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), coinView);
        tt.setByY(coinView.getTranslateX() - finalValue);

        tt.play();


    }

    @FXML
    public void onKeyPressed(KeyEvent event) {

        switch (event.getCode()) {

            case SPACE:
                coinAnimation(progressBar.getProgress(), 1);
                System.out.println(progressBar.getProgress());
                if (progressBar.getProgress() >= 0.7 && progressBar.getProgress() <= 0.9) {
                    coins++;
                }
                coinsValue.setText(String.valueOf(coins));

                Session.getInstance().write(new Message(Type.COMUNICATION_LVL1, String.valueOf(progressBar.getProgress())));


        }


    }

}
