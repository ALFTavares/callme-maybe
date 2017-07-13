package org.academiadecodigo.hackaton.tests;

import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Tests {

    public static void main(String[] args) {

        Message message = new Message(Type.LOGIN, "Hello");

        message.getType();

        message.getContent();

    }

}
