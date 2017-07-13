package org.academiadecodigo.hackaton.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 13/07/17.
 */
public class ControllerGame1 extends Controller implements Initializable {

    @FXML
    private HBox scoreBox;

    @FXML
    private Text timeText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("open");
    }
}
