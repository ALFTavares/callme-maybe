package org.academiadecodigo.hackaton.client.service.game;

import org.academiadecodigo.hackaton.client.controller.Controller;
import org.academiadecodigo.hackaton.client.controller.ControllerGame1;
import org.academiadecodigo.hackaton.shared.Type;

/**
 * Created by joelalmeida on 13/07/17.
 */
public class GameService1 implements GameService {

    private ControllerGame1 controllerGame1;
    private int points;

    @Override
    public String getName() {
        return GameService.class.getSimpleName();

    }


    @Override
    public void moveCoin(String string) {
        controllerGame1.coinAnimation(Integer.valueOf(string), 2);
    }

    @Override
    public void setController(Controller controller) {
        this.controllerGame1 = (ControllerGame1) controller;
    }

    @Override
    public void processMsg(Type type, String content) {

        switch (type){

            case COMUNICATION_LVL1:

                double coin = Double.valueOf(content);

                controllerGame1.coinAnimation(coin, 2);
        }
    }

    public void addPoints(int points) {
        this.points = points * 100;
    }

}
