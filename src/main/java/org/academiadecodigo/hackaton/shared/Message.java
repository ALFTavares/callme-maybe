package org.academiadecodigo.hackaton.shared;

import java.io.Serializable;
import java.util.List;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Message<T> implements Serializable {

    private static Long serialVersionUID = 10L;

    private Type type;
    private T content;

    public Message(Type type, T content) {
        this.type = type;
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public T getContent() {
        return content;
    }
}
