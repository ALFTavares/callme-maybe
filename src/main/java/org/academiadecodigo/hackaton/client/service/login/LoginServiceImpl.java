package org.academiadecodigo.hackaton.client.service.login;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.academiadecodigo.hackaton.client.Navigation;
import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;
import org.academiadecodigo.hackaton.shared.Values;
import org.academiadecodigo.hackaton.sound.Sound;

import java.io.File;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class LoginServiceImpl implements LoginService {

    @Override
    public String getName() {
        return LoginService.class.getSimpleName();
    }

    public void submitNewClient(String username) {

        Session.getInstance().write(new Message(Type.LOGIN, username));

        Message<String> message = (Message<String>) Session.getInstance().read();

        if (message.getType().equals(Type.LOGIN) && message.getContent().equals(Values.SUCCESS)) {
            Session.getInstance().setUsername(username);
            return;
        }

        throw new IllegalArgumentException("Existent user. Please try again.");

    }

}
