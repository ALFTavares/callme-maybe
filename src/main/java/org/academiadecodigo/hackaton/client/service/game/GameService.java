package org.academiadecodigo.hackaton.client.service.game;

import org.academiadecodigo.hackaton.client.controller.Controller;
import org.academiadecodigo.hackaton.client.service.Service;
import org.academiadecodigo.hackaton.shared.Type;

/**
 * Created by joelalmeida on 13/07/17.
 */
public interface GameService extends Service {
    void moveCoin(String string);

    void setController(Controller controller);

    void processMsg(Type type, String content);

    void addPoints(int points);
}
