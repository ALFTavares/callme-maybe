package org.academiadecodigo.hackaton.client.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

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

        addPlayer();
        coinAnimation(0.9);
    }

    private void addPlayer() {
        playerImage = new Image("/images/profile1.png");
        playerView = new ImageView();
        playerView.setImage(playerImage);
        playerView.setFitWidth(50);
        playerView.setPreserveRatio(true);

        players.put(1, playerView);

        gridPane.add(playerView, 3, 6);
    }

    private void coinAnimation(double value) {

        coinImage = new Image("/images/coin.png");
        coinView = new ImageView();
        coinView.setImage(coinImage);
        coinView.setFitWidth(50);
        coinView.setPreserveRatio(true);
        int playerRow = gridPane.getRowIndex(players.get(1));
        int playerColumn = gridPane.getColumnIndex(players.get(1));
        gridPane.add(coinView, playerColumn, playerRow);
        double finalValue = value * 500;
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), coinView);
        tt.setByY(coinView.getTranslateX() - finalValue);

        tt.play();


    }
}
