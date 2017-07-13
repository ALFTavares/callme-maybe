package org.academiadecodigo.hackaton.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.academiadecodigo.hackaton.client.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class ControllerHighScores extends Controller {

    @FXML
    private Pane menuPane;

    @FXML
    private Text userText;

    @FXML
    private Text errorText;

    @FXML
    private ListView<?> highScores;

    @FXML
    void onBack(ActionEvent event) {
        Navigation.getInstance().back();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
