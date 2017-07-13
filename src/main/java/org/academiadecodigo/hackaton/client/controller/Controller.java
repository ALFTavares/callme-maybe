package org.academiadecodigo.hackaton.client.controller;

import javafx.stage.Stage;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public abstract class Controller {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
