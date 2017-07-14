package org.academiadecodigo.hackaton.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.academiadecodigo.hackaton.client.service.ServiceLocator;
import org.academiadecodigo.hackaton.client.service.game.GameService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 14/07/17.
 */
public class ControllerFinal extends Controller {
    private GameService gameService;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameService = ServiceLocator.getInstance().get(GameService.class);

        checkPoints();
    }

    @FXML
    void restart(ActionEvent event) {
        System.out.println("cens");
    }


    public void checkPoints() {

        int enemyPoints = gameService.getEnemyPoints();
        int playerPoints = gameService.getPoints();
        System.out.println(enemyPoints + " " + playerPoints);

        if (enemyPoints == playerPoints) {
            System.out.println("ty");
            gridPane.getStyleClass().add("lose");
            return;
        } else if (enemyPoints > playerPoints) {
            System.out.println("lose");
            gridPane.getStyleClass().add("lose");
            return;
        }
        System.out.println("win");
        gridPane.getStyleClass().add("winner");


    }
}
