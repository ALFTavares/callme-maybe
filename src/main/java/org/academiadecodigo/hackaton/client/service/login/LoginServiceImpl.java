package org.academiadecodigo.hackaton.client.service.login;

import org.academiadecodigo.hackaton.client.Navigation;
import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;

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

        Message message = Session.getInstance().read();

        if (message.getType().equals(Type.LOGIN) && message.getContent().equals("OK")) {
            Navigation.getInstance().loadScreen("game1");
            return;
        }

        throw new IllegalArgumentException("Existent user. Please try again.");

    }

}
