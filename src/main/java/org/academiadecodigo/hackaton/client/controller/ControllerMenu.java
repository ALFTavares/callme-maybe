package org.academiadecodigo.hackaton.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.academiadecodigo.hackaton.client.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 13/07/17.
 */
public class ControllerMenu extends Controller implements Initializable {
    @FXML
    private Pane menuPane;

    @FXML
    private Text userText;

    @FXML
    private TextField inputText;

    @FXML
    private Text errorText;

    @FXML
    private Button submitBtn;

    private String userName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(menuPane.getPrefWidth());
    }

    @FXML
    void submitToServer(ActionEvent event) {
        userName = inputText.getText();

        userText.setText("Your player name is: " + userName);

        Navigation.getInstance().loadScreen("game1");
    }

}
