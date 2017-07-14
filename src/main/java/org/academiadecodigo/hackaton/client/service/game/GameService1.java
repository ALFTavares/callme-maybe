package org.academiadecodigo.hackaton.client.service.game;

import javafx.application.Platform;
import org.academiadecodigo.hackaton.client.controller.Controller;
import org.academiadecodigo.hackaton.client.controller.ControllerGame1;
import org.academiadecodigo.hackaton.shared.Type;

/**
 * Created by joelalmeida on 13/07/17.
 */
public class GameService1 implements GameService {

    private ControllerGame1 controllerGame1;
    private int points;
    private int enemyPoints;

    @Override
    public String getName() {
        return GameService.class.getSimpleName();
    }

    @Override
    public void moveCoin(String string) {
        if (Double.parseDouble(string) >= 0.7 && Double.parseDouble(string) <= 0.8) {
            enemyPoints++;
        }

    }

    @Override
    public void setController(Controller controller) {
        this.controllerGame1 = (ControllerGame1) controller;
    }

    @Override
    public void processMsg(Type type, final String content) {
        System.out.println("content" + content);
        switch (type){

            case COMUNICATION_LVL1:

                final double coin = Double.valueOf(content);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        moveCoin(content);
                        controllerGame1.coinAnimation(coin, 2);
                    }
                });
        }
    }

    public void addPoints(int points) {
        this.points = points;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getEnemyPoints() {
        return enemyPoints;
    }
}
