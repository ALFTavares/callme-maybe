package org.academiadecodigo.hackaton.shared;

import java.io.Serializable;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Message implements Serializable {

    private static Long serialVersionUID = 10L;

    private Type type;
    private String content;

    public Message(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

}
