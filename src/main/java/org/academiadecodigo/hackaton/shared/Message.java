package org.academiadecodigo.hackaton.shared;

import java.io.Serializable;
import java.util.List;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Message implements Serializable {

    private static Long serialVersionUID = 10L;

    private Type type;
    private String content;
    private List list;

    public Message(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    public Message(Type type, List list) {
        this.type = type;
        this.list = list;
    }

    public Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

}
