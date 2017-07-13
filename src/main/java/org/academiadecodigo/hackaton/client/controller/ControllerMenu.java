package org.academiadecodigo.hackaton.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.academiadecodigo.hackaton.client.Navigation;
import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.client.service.ServiceLocator;
import org.academiadecodigo.hackaton.client.service.login.LoginService;
import org.academiadecodigo.hackaton.client.service.login.LoginServiceImpl;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by codecadet on 13/07/17.
 */
public class ControllerMenu extends Controller {
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

    private LoginService loginService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginService = ServiceLocator.getInstance().<LoginService>get(LoginService.class);
    }

    @FXML
    void submitToServer(ActionEvent event) {

        String username = inputText.getText();
        Pattern pattern = Pattern.compile("[\\w]+");
        Matcher matcher = pattern.matcher(username);

        if (!matcher.matches()) {
            errorText.setText("Invalid username: only letters and numbers are allowed");
            return;
        }

        errorText.setText("");

        try {
            loginService.submitNewClient(username);
        } catch (IllegalArgumentException e) {
            errorText.setText(e.getMessage());
        }
    }

}
